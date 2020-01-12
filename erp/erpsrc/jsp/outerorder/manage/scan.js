dojo.ready(function() {
	initGrid();
});

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/goodsbill/query/queryDetail.action?formId=" + formId;
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare, query,
			Keyboard, ColumnResizer) {
		var dataStore = new JsonRest({
			target : _url
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "原料编码",
	field : "itemId",
	className:'text-center',
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
	label : "库存量",
	field : "inventory",
	className:'text-right',
	sortable : false
}, {
	label : "订货量",
	field : "orderCount",
	className:'text-right',
	sortable : false
}, {
	label : "零售单价",
	field : "itemUnitPrice",
	className:'text-right',
	sortable : false
}, {
	label : "零售金额",
	field : "payAmt",
	className:'text-right',
	sortable : false
}, {
	label : "",
	field : "none",
	sortable : false
} ];
