require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('store').getData());
}
function doClose() {
	closeTab(tabId);
}

var grid = null;
var dataStore = null;

function initGrid() {
	var _url = appRoot + "/restaurant/preject/manage/queryOutDetail.action?formId=" + formId;
	+formId;
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/parser", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Server, parser, declare,
			Keyboard, ColumnResizer) {
		parser.parse();
		dataStore = new Server({
			target : _url
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
		label : "订货数",
		field : "requestCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "退货数",
		field : "returnCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "金额",
		field : "returnPayAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "退货原因",
		field : "returnReason",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
