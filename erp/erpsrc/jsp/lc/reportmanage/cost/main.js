init();

var grid;

function customExport(){
	exportXls(); 
}

function doQuery(){
	var startDate = dojo.byId('startDate').value;
	var endDate = dojo.byId('endDate').value;
	
	if(isEmpty(startDate)){
		alert("请选择开始日期！");
		return;
	}
	
	if(isEmpty(endDate)){
		alert("请选择结束日期！");
		return;
	}
	
	grid.set('query', getQuery());	
}

function print(){
	prn1_preview(getExportArgs());
}

function show(){
	dijit.byId('customDialog').show();
}

function hide(){
	dijit.byId('customDialog').hide();
}

function init() {
	require([ 
	          "dojo/dom", 
	          "dojo/dom-form",
	          "dojo/parser",
	          "dijit/form/Button",
	          "dijit/Dialog",
	          "dojo/domReady!" ], function(dom,domForm,parser) {
		  
		parser.parse();
		
		var branchType = dom.byId('branchType').value;
		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}
		
		initGrid();
	});
}

function getColumn() {
	return [[[
	         {
				label : "物流中心",
				field : "branchName",
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			},{
				label : "仓库名称",
				field : "storageName",
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}]], [[{
				label : "期初库存",
				field : "beginAmt",
			    className:'text-right',
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "本期进货",
				field : "inAmt",
			    className:'text-right',
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "期末库存",
				field : "resultAmt",
			    className:'text-right',
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "实际成本",
				field : "actualCost",
			    className:'text-right',
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			},{
				label : "原料损耗",
				field : "rawAmt",
			    className:'text-right',
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}
			,{
				label : "餐厅收货",
				field : "receiveAmt",
			    className:'text-right',
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			},{
				label : "配送差异",
				field : "diffAmt",
			    className:'text-right',
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.branchName == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}]]];
};


function initGrid() {
	var _url = appRoot + "/reportmanage/lc/cost/doQuery.action";
	_url = getUrl(_url);
	
	require([ 
	          "dojo/_base/lang", 
	          "dojo/_base/declare", 
	          "dgrid/Selection", 
	          "dgrid/OnDemandGrid", 
	          "custom/store/Server",
	          "dojo/store/Observable", 
	          "dojo/store/Cache",
	          "dojo/store/Memory",
	          "dgrid/ColumnSet",
	          "dgrid/Keyboard"
	         ], function(lang, declare,	Selection, OnDemandGrid, Server, Observable, Cache,Memory,ColumnSet,Keyboard) {
		var dataStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));
		
		var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnSet,Keyboard]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, 'dataGrid');

		grid.startup();
	});
};
