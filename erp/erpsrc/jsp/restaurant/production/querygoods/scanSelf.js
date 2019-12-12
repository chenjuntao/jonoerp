dojo.ready(function() {
	initDishGrid();
	initRawGrid();
});

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	var newData = dishGrid.get('store').getData();
	newData = newData.concat(grid.get('store').getData());
	fillData(newData);
}

function doClose() {
	closeTab(tabId);
}

var dataStore = null;
var dishGrid = null;
function initDishGrid() {
	var _url = appRoot + "/restaurant/production/querySelf/queryDetail.action?formId=" + formId + "&queryType=SEMIS";
	_url = getUrl(_url);
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Server, declare, Keyboard,
			ColumnResizer) {
		dataStore = new Server({
			target : _url,
			idProperty : "rownumber"
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		dishGrid = new CustomGrid({
			store : dataStore,
			columns : dishCols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dishGrid");

		dishGrid.startup();
	});
}

var grid = null;
var myStore = null;
function initRawGrid() {
	var _url = appRoot + "/restaurant/production/querySelf/queryDetail.action?formId=" + formId + "&queryType=RAW";
	_url = getUrl(_url);
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/parser", "dojo/domReady!" ], function(OnDemandGrid, Server,
			declare, Keyboard, ColumnResizer, parser) {
		parser.parse();
		myStore = new Server({
			target : _url,
			idProperty : "rownumber"
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columns : rawCols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "rawGrid");

		grid.startup();
	});
}

var rawCols = [ {
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
	label : "例牌",
	field : "itemDimension",
	className : 'text-center',
	sortable : false
}, {
	label : "类别",
	field : "itemCategory",
	className : 'text-right',
	sortable : false
}, {
	label : "计划耗料数量",
	className : 'text-right',
	field : "itemCountPlan",
	sortable : false
}, {
	label : "实际耗料数量",
	field : "itemCountActual",
	className : 'text-right',
	sortable : false
}, {
	label : "",
	field : "blank",
	sortable : false
} ];

var dishCols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "半成品编码",
	field : "itemId",
	className : 'text-center',
	sortable : false
}, {
	label : "半成品名称",
	field : "itemName",
	sortable : false
}, {
	label : "例牌",
	field : "itemDimension",
	className : 'text-center',
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	className : 'text-center',
	sortable : false
}, {
	label : "类别",
	field : "itemCategory",
	className : 'text-right',
	sortable : false
}, {
	label : "入库数量",
	field : "itemCountActual",
	className : 'text-right',
	sortable : false
}, {
	label : "",
	field : "blank",
	sortable : false
} ];
