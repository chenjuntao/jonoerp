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

var grid = null;
var dataStore = null;
function initGrid() {

	var columns = getColumn();
	var _url = appRoot + "/cf/requisition/manage/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dojo/query", "dojo/parser",
			"dgrid/Keyboard", "dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, Server, declare, query,
			parser, Keyboard, ColumnResizer) {
		parser.parse();
		dataStore = new Server({
			target : _url
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : columns,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "scanGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
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
		label : "计划领料数",
		field : "itemCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "已领料数量",
		field : "receivedCount",
		sortable : false
	}, {
		label : "未领料数",
		field : "differentCount",
		className : 'grid-number',
		sortable : false
	// ,//后台已处理
	// formatter : function(value, rowData) {
	// var num = new Number(rowData.itemCount - rowData.receivedCount);
	// if(num < 0){
	// return 0;
	// }
	// return num.toFixed(2);
	// }
	}, {
		label : "领料数",
		field : "receiveCount",
		className : 'grid-number',
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
