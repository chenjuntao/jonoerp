dojo.ready(function() {
	initGrid();
});


var grid = null;

function initGrid(_id) {
	var _url = appRoot + "";
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare,
			query, Keyboard) {
		var myStore = new JsonRest({
			target : _url,
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			store : myStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber"
},{
	label : "原料编码",
	field : "itemId"
}, {
	label : "原料名称",
	field : "itemName"
}, {
	label : "单位",
	field : "itemDimension"
}, {
	label : "规格",
	field : "formal"
}, {
	label : "类别",
	field : "categoryId"
}, {
	label : "申请调拨数量",
	field : "allocateNumber"
}, {
	label : "标准单价",
	field : "unitPrice"
}, {
	label : "标准金额",
	field : "totalPrice"
}, {
	label : "原料有效期",
	field : "batch"
} ];
