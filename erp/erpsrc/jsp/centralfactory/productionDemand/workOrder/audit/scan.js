dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
	
	//进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid",
	          "dojo/window",
	          "dojo/_base/declare",
	          "dgrid/Keyboard",
	          "dojo/domReady!" 
	        ], function(OnDemandGrid, win,declare, Keyboard) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "auditDataGrid");
		
		grid.startup();
		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot
			+ "/centralfactory/productionDemand/workOrder/audit/queryItemDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ],
			function(xhr, Observable, Memory) {
				xhr.get(_url, {
					handleAs : "json"
				}).then(function(data) {
					dataStore = new Observable(new Memory({
						idProperty : "itemId",
						data : data
					}));
					grid.set("store", dataStore);
				}, function(err) {
				});
			});
}

function getColumn() {
	return [  {
		label : "序号",
		field : "rownumber",
		sortable:false
	},{
		label : "商品编码",
		field : "itemId",
		sortable:false
	}, {
		label : "商品名称",
		field : "itemName",
		sortable:false
	}, {
		label : "库存单位",
		field : "itemDimension",
		sortable:false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable:false
	}, {
		label : "计划领料数",
		field : "itemCount",
		sortable:false
	} , {
		label : "已领料数",
		field : "receivedCount",
		sortable:false
	}, {
		label : "已退料数",
		field : "returnedCount",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}];
}

