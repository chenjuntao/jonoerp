dojo.ready(function() {
	initGrid();
});

var grid = null;
function initGrid() {
	var _url = appRoot
			+ "/centralfactory/productionDemand/orderSummary/create/queryDetail.action?formId="
			+ formId;
	_url = getUrl(_url);
	
	require([ 
	          "dojo/window",
	          "dgrid/OnDemandGrid", 
	          "dojo/store/JsonRest",
	          "dojo/_base/declare", 
	          "dojo/query", 
	          "dgrid/Keyboard",
	          "dojo/domReady!" 
	          ], function(win,OnDemandGrid, JsonRest, declare,	query, Keyboard) {
		//先实现功能，再优化代码
		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 170;
		var gridNode = dojo.byId("dataGrid");
		gridNode.style.height = gridHeight + "px";
		
		var dataStore = new JsonRest({
			target : _url
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
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
	field : "rownumber"
}, {
	label : '商品编码',
	field : 'itemId'
}, {
	label : '商品名称',
	field : 'itemName'
}, {
	label : '单位',
	field : 'itemDimension'
}, {
	label : '规格',
	field : 'itemSpecification'
}, {
	label : '类别',
	field : 'itemCategory'
}, {
	label : '数量',
	field : 'itemCount'
}, {
	label : '标准单价',
	field : 'itemUnitPrice'
}, {
	label : '金额',
	field : 'payAmt'
}, {
	label : '',
	field : 'expiredTime'
}
];
