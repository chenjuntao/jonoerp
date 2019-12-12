dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	var _url = appRoot + "/restaurant/preject/create/doSave.action?formType=PURCHASE&branchType="
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("  已成功生成采购退货单 \n" + data.formId);
				closeTab(tabId, "doClose");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

var grid = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Memory, declare, Keyboard,
			ColumnResizer) {
		var dataStore = new Memory({
			data : gridData,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
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
		label : "要货数量",
		field : "orderCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "实收数",
		field : "receiveCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "退货数",
		field : "returnCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "单价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "金额",
		field : "returnPayAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "退货原因",
		field : "returnReason",
		sortable : false
	} ];
}
