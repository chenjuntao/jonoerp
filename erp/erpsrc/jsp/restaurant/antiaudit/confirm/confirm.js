require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doAudit;

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formRefId').value);
	};
}

function doAudit() {
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	dojo.byId('btn_submit').disabled = true;
	var _url = appRoot + "/restaurant/antiaudit/confirm/doConfirm.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId, "doQuery");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

var grid = null;
function initGrid() {
	require(
			[ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard" ],
			function(OnDemandGrid, editor, NumberTextBox, declare, Keyboard) {
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					columns : getColumn(),
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
				queryData();
			});
}

function queryData() {
	var _url = appRoot + "/restaurant/antiaudit/manage/queryDetail.action?formRefId=" + dojo.byId('formRefId').value;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "itemId",
				data : data
			}));
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "原料编码",
		field : "itemId"
	}, {
		label : "原料名称",
		field : "itemName"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number'
	}, {
		label : "实发数",
		field : "deliveryCount",
		className : 'grid-number'
	}, {
		label : "原实收数",
		field : "receiveCount",
		className : 'grid-number'
	}, {
		label : "反审核实收数",
		field : "antiauditReceiveCount",
		className : 'grid-number'
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number'
	}, {
		label : "原金额",
		field : "payAmt",
		className : 'grid-number'
	}, {
		label : "反审核金额",
		field : "antiauditPayAmt",
		className : 'grid-number'
	} ];
}
