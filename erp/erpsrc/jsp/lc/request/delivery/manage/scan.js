dojo.ready(function() {
	initGrid();
});

var grid = null;
var myStore = null;
function initGrid() {
	var _url = appRoot + "/restaurant/inoutquery/shipping/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dgrid/extensions/ColumnHider", "dgrid/extensions/ColumnResizer", "dojo/parser", "dgrid/ColumnSet",
			"dijit/form/Select", "dijit/form/Button", "dijit/Dialog", "dojo/domReady!" ], function(OnDemandGrid,
			Server, declare, query, Keyboard, ColumnHider, ColumnResizer, parser, ColumnSet) {
		parser.parse();
		myStore = new Server({
			target : _url,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnHider, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getCols(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getCols() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		className:'text-center',
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
		sortable : false
	} ] ], [ [ {
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
		label : "包装因子",
		field : "packagingFactor",
		className : 'grid-number',
		sortable : false
	}, {
		label : "包装单位",
		field : "packagingUnit",
		sortable : false
	}, {
		label : "订货数",
		field : "requestCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number',
		sortable : false
	// }, {
	// label : "实发数",
	// field : "deliveryCount",
	// className : 'grid-number',
	// sortable:false
	}, {
		label : "实收数",
		field : "receiveCount",
		className : 'grid-number'
	}, {
		label : "差异数",
		field : "differentCount",
		className : 'grid-number'
	// }, {
	// label : "退货数",
	// field : "returnCount",
	// className : 'grid-number'
	}, {
		label : "单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('store').getData());
}

function customPrintDesign() {
	myDesign(grid.get('columns'), grid.get('store').data);
}

function doClose() {
	closeTab(tabId);
}
