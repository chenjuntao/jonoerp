require([ "dojo", "dojo/ready", "dojo/parser" ], function(dojo, ready, parser) {
	ready(function() {
		parser.parse();
		addEvent();
		initGrid();
	});
});

function addEvent() {

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

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

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/checkstorage/manage/queryDetail.action?formId=" + formId+"&hasSum=Y";
	_url = getUrl(_url);
	
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
						columns : getColumn(),
						cellNavigation : false,
						loadingMessage : '加载中...'
					}, "dataGrid");

					grid.startup();
				}, function(err) {
					alert("load error");
				})
			});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		}
	}, {
		label : "原料编码",
		field : "itemId",
		className:'text-center',
		sortable : true
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
		label : '盘点数量',
		field : 'checkCount',
		className:'text-right',
		sortable : true	,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		}
	}, {
		label : '系统库存',
		field : 'theoryCount',
		className:'text-right',
		sortable : true	,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		}
	}, {
		label : '盘盈',
		field : 'diffCount',
		className:'text-right',
		sortable : true	,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		}
	}, {
		label : '盘盈金额',
		field : 'diffAmt',
		className:'text-right',
		sortable : true	,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		}
	}, {
		label : '单价',
		field : 'itemPrice',
		className:'text-right',
		sortable : true
	}, {
		label : '金额',
		field : 'itemAmt',
		className:'text-right',
		sortable : true	,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return getBoldText(field);
			}
			
			return field;
		}
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
