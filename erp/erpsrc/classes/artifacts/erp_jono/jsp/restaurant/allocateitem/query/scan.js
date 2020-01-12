require([ "dojo/ready",
          "dojo/parser"], function(ready,parser) {
	ready(function() {
		parser.parse();
		initGrid();
	});
});

function showDialog(){
	dijit.byId('customDialog').show();
}

function hideDialog(){
	dijit.byId('customDialog').hide();
}

function customExport(){
	fillData(grid.get('store').getData());
}
function doClose() {
	closeTab(tabId);
}

var grid = null;
var myStore = null;
function initGrid() {
	var _url = appRoot
			+ "/restaurant/allocateitem/query/queryDetail.action?formId="
			+ formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer","dojo/domReady!" ], function(OnDemandGrid, Server, declare,
			query, Keyboard,ColumnResizer) {
		myStore = new Server({
			target : _url
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
		grid = new CustomGrid({
			store : myStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "scanGrid");

		grid.startup();
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable:false
}, {
	label : "原料编码",
	field : "itemId",
	sortable:false
}, {
	label : "原料名称",
	field : "itemName",
	sortable:false
}, {
	label : "类别",
	field : "itemCategory",
	sortable:false
}, {
	label : "单位",
	field : "	",
	sortable:false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable:false
}, {
	label : "申请调拨数量",
	field : "applyCount",
	className : 'grid-number',
	sortable:false
}, {
	label : "调拨数量",
	field : "actualCount",
	className : 'grid-number',
	sortable:false
}, {
	label : "调拨差异",
	field : "differentCount",
	className : 'grid-number',
	sortable:false
}, {
	label : "标准单价",
	field : "unitPrice",
	className : 'grid-number',
	sortable:false
}, {
	label : "标准金额",
	field : "payAmt",
	className : 'grid-number',
	sortable:false
}, {
	label : "",
	field : "none",
	sortable:false
} ];
