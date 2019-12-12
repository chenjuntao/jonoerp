dojo.ready(function() {
	initGrid();
});

var grid = null;
function initGrid() {
	var _url = appRoot
			+ "/centralfactory/productionDemand/summary/queryDetail.action?formId="
			+ formId;
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/JsonRest",
	          "dojo/_base/declare", 
	          "dojo/query", 
	          "dgrid/Keyboard",
	          "dgrid/extensions/ColumnResizer",
	          "dojo/domReady!" 
	          ], function(OnDemandGrid, JsonRest, declare,	query, Keyboard,ColumnResizer) {
		var dataStore = new JsonRest({
			target : _url,
			idProperty:'rownumber'
		});
		
		var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
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
	sortable:false
}, {
	label : '商品编码',
	field : 'itemId',
	sortable:false
}, {
	label : '商品名称',
	field : 'itemName',
	sortable:false
}, {
	label : '单位',
	field : 'itemDimension',
	sortable:false
}, {
	label : '规格',
	field : 'itemSpecification',
	sortable:false
}, {
	label : '类别',
	field : 'itemCategory',
	sortable:false
}, {
	label : '收货部门',
	field : 'receiver',
	sortable:false
}, {
	label : '数量',
	field : 'itemCount',
	sortable:false
}
//, {
//	label : '标准单价',
//	field : 'itemUnitPrice'
//}, {
//	label : '金额',
//	field : 'payAmt'
//}
, {
	label : '',
	field : 'none',
	sortable:false
}
];
