require([ "dojo",  "dojo/parser","dojo/ready" ], function(dojo, parser, ready) {
	ready(function() {
		parser.parse();
		// 获取父窗口的数据
		initGrid();
	});
	
});
function initGrid() {
	var _url = appRoot + "/restaurant/common/template/queryItem.action?templateId=" + templateId;
	
	require(["dgrid/OnDemandGrid", "dojo/store/Cache", "dojo/store/Memory",
	 		"dojo/_base/declare", "dgrid/Keyboard", "custom/store/Server","dojo/domReady!" ], function(OnDemandGrid, Cache, Memory,
	 				declare, Keyboard,Server) {
		var dataStore = new Server({
			target : _url,
			idProperty : "itemId"
		});
	// 缓存数据
	var cStore = new Cache(dataStore, new Memory());
	var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
	grid = new CustomGrid({
		store : cStore,
		columns : getColumn(),
		cellNavigation : false,
		loadingMessage : '加载中...'
	}, "dataGrid");

	grid.startup();
	});
}
function showDialog(){
	dijit.byId('customDialog').show();
}

function hideDialog(){
	dijit.byId('customDialog').hide();
}

function customExport(){
	fillData(grid.get('store').getData());
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
		label : "单价",
		field : "itemUnitPrice",
		sortable : false
	}, {
		label : "主库位",
		field : "shelfName",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function doClose() {
	closeTab(tabId);
}
