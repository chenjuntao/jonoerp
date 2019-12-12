require([ "dojo/ready", "dojo/parser" ], function(ready, parser) {
	ready(function() {
		parser.parse();
		initGrid();
	});
});

var grid = null;

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('store').data);
}
function doClose() {
	closeTab(tabId);
}

function initGrid() {
	var _url = appRoot + "/restaurant/checkstorage/manage/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/Observable", 
	          "dojo/store/Cache", 
	          "dojo/store/Memory",
	          "dojo/request/xhr", 
	          "dojo/_base/declare", 
	          "dgrid/Keyboard", 
	          "dgrid/extensions/Pagination",
	          "dojo/domReady!" 
          ], function(OnDemandGrid,	Observable, Cache, Memory, xhr, declare, Keyboard,Pagination) {
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
var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "原料编码",
	field : "itemId",
	className:'text-center',
	sortable : false
}, {
	label : "原料名称",
	field : "itemName",
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
	label : "类别",
	field : "itemCategory",
	sortable : false
}, {
	label : '盘点数量',
	field : 'checkCount',
	className:'text-right',
	sortable : true
}, {
	label : "",
	field : "none",
	sortable : false
} ];
