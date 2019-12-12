require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var grid = null;
var dataStore = null;
function initGrid() {
	var _url = appRoot + "/hq/priceadjust/queryDetail.action?formId=" + formId;
	
	require([ 
	          "dgrid/OnDemandGrid",
	          "dojo/store/Observable",
	          "dojo/store/Cache", 
	          "dojo/store/Memory",
	          "dojo/request/xhr",
	          "custom/store/Server", 
	          "dojo/_base/declare",
	          "dgrid/Keyboard", 
	          "dgrid/extensions/Pagination",
	          "dojo/domReady!"
         ],function(OnDemandGrid, Observable, Cache, Memory, xhr, Server, declare, Keyboard,Pagination) {
				xhr(_url, {
					handleAs : 'json'
				}).then(function(data) {
					dataStore = new Memory({
						data : data,
						idProperty : 'rownumber'
					});

					var CustomGrid = declare([ OnDemandGrid, Keyboard,Pagination ]);
					grid = new CustomGrid({
						store : dataStore,
						columns : cols,
						cellNavigation : false,
						loadingMessage : '加载中...'
					}, "dataGrid");

					grid.startup();
				}, function(err) {
					alert("load error");
				})
			});
}

function myExport() {
	fillData(grid.get('columns'), grid.get('store').data);
}

var cols = [ {
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
	renderCell : function(object, data) {
		return imageFmt(data, object.itemId);
	},
	sortable : false
}, {
	label : "单位",
	field : "itemDimension",
	className:'text-center',
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable : false
}, {
	label : "包装因子",
	field : "itemPackager",
	sortable : false
}, {
	label : "原价",
	field : "oldPrice",
	className:'text-right',
	sortable : false
}, {
	label : "新价",
	field : "newPrice",
	className:'text-right',
	sortable : false
}, {
	label : "差价",
	field : "differentPrice",
	className:'text-right',
	sortable : false
}, {
	label : "",
	field : "none",
	sortable : false
} ];
