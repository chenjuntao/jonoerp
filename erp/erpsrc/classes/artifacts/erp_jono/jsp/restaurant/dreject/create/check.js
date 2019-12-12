dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	var _url = appRoot + "/restaurant/dreject/create/doSave.action?formType=INNER";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId, 'doClose');
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

		calcMoney(dataStore.query());
	});
}

// 计算总金额
function calcMoney(data) {
	var sumMoney = 0;

	require([ "dojo/_base/array", "dojo/dom" ], function(array, dom) {
		array.forEach(data, function(item) {
			if (!isNaN(sumMoney)) {
				sumMoney += parseFloat(item.returnPayAmt);
			} else {
				sumMoney += 0;
			}
		});

		dom.byId("sumMoney").innerHTML = sumMoney.toFixed(2);

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
		label : "单位",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "实发数",
		field : "deliveryCount",
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
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "标准金额",
		field : "returnPayAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "退货原因",
		field : "returnReason",
		sortable : false
	} ];
}
