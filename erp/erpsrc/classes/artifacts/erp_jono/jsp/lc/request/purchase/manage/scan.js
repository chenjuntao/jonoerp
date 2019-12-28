require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
}

function doQuery(_formId, _deliveryType) {
	if (grid == null) {
		initGrid();
	}
	var _url = appRoot + "/restaurant/putinstorage/create/queryBill.action?formId=" + _formId;
	if (_deliveryType == 'DIRECT') {
		_url = appRoot + "/lc/request/purchase/manage/queryDirect.action?formId=" + _formId;
	}
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			loadData(data);
		}, function(err) {
			alert("load error");
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare", "dgrid/editor",
			"dijit/form/NumberTextBox", "dgrid/Keyboard", "dojo/domReady!" ], function(OnDemandGrid, Observable,
			Memory, declare, editor, NumberTextBox, Keyboard) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.startup();

		doQuery(formId, deliveryType);
	});
}

function loadData(data) {
	var header = data.header;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_provider').innerHTML = "[" + header.providerId + "]" + header.provider;
	dojo.byId('l_receiveTime').innerHTML = header.receiveTime;
	dojo.byId('l_requester').innerHTML = header.requester;
	// dojo.byId('l_receiver').innerHTML = header.receiver;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;

	var deliveryType = header.deliveryType;
	var dType = '直配';
	if (deliveryType == 'CROSS') {
		dType = '越库';
		hideCols([ "receiveAddress" ], [ "receiver" ]);
	} else if (deliveryType == 'UNIFIED') {
		dType = '统配';
		hideCols([ "receiver", "receiveAddress" ], []);
	} else {
		hideCols([], [ "receiver", "receiveAddress" ]);
	}

	// 兼容中央工厂采购单
	if (!deliveryType) {
		dType = "";
	}

	dojo.byId('l_deliveryType').innerHTML = dType;

	dataStore.setData([]);
	grid.set("store", dataStore);
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data.detailLst, function(row, i) {
			dataStore.put(row);
		});
	});
}

function hideCols(hideArr, showArr) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(hideArr, function(columnId, i) {
			// to hide column with id="name"
			grid.styleColumn(columnId, "display: none;");
		});
		array.forEach(showArr, function(columnId, i) {
			// to show it
			grid.styleColumn(columnId, "display: table-cell;");
		});
	});
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		id : 'receiver',
		label : "收货部门",
		field : "receiver",
		sortable : false
	}, {
		id : 'receiveAddress',
		label : "收货地址",
		field : "receiveAddress",
		sortable : false
	}, {
		label : "订货量",
		field : "itemCount",
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "原料有效期",
		field : "expiredTime",
		sortable : false
	} ];
}

function validateGrid() {
	if (!validateColumn(grid, 'itemCount')) {
		return false;
	}
	return true;
}

function doAudit() {
	if (!validateGrid()) {
		return;
	}
	grid.save();
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/lc/request/purchase/audit/doAudit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
