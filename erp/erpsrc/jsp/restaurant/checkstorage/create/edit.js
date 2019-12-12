require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {
		addEvent();
		initGrid();

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

var standby = null;

function addEvent() {
}

function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;
function initGrid() {
	var branchType = dojo.byId('branchType').value;
	var storageId = dojo.byId('checkStorageId').value;

	var _url = appRoot + "/restaurant/checkstorage/create/queryDetail.action?batchId=" + g_batchId + "&branchType="
			+ branchType + "&storageId=" + storageId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/Pagination" ], function(OnDemandGrid, Memory, declare, Keyboard, Pagination) {
		dataStore = new Memory({
			idProperty : "rownumber",
			data : []
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard, Pagination ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.startup();

		loadGridData();
	});
}

function loadGridData() {
	var branchType = dojo.byId('branchType').value;
	var storageId = dojo.byId('checkStorageId').value;
	standby.show();
	var _url = appRoot + "/restaurant/checkstorage/create/queryDetail.action?batchId=" + g_batchId + "&branchType="
			+ branchType + "&storageId=" + storageId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore.setData(data);
			grid.set("store", dataStore);
			standby.hide();
		}, function(err) {
			standby.hide();
		});
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : true
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}
	// , {
	// label : "类别",
	// field : "itemCategory",
	// sortable : true
	// }
	// , {
	// label : "原料有效期",
	// field : "expiredTime",
	// sortable : false
	// }
	, {
		label : '盘点数量',
		field : 'checkCount',
		className : 'text-right',
		sortable : false
	}, {
		label : '系统库存',
		field : 'theoryCount',
		className : 'text-right',
		sortable : false
	}, {
		label : '盘盈',
		field : 'diffCount',
		className : 'text-right',
		formatter : function(value, rowData) {
			return parseFloat((rowData.checkCount - rowData.theoryCount).toFixed(2));
		},
		sortable : false
	}, {
		label : '标准价格',
		field : 'itemPrice',
		className : 'text-right',
		sortable : false
	}, {
		label : '盘盈金额',
		field : 'diffAnt',
		className : 'text-right',
		formatter : function(value, rowData) {
			return parseFloat(((rowData.checkCount - rowData.theoryCount) * rowData.itemPrice).toFixed(2));
		},
		sortable : false
	}, {
		label : "盘点状态",
		field : "itemStatus",
		className : 'text-center',
		formatter : function(value, rowData) {
			if (rowData.itemStatus == 'LEAK') {
				return '漏盘';
			}
			return '';
		},
		sortable : false
	} ];
}

function doSave() {
	standby.show();

	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	dojo.byId('btn_submit').disabled = true;
	var _url = appRoot + "/restaurant/checkstorage/create/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();

			if (data.msg == 'ok') {
				alert("生成单据号为：" + data.formId + "，提交成功！");
				closeTab(tabId);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
			standby.hide();
		});
	});
}
