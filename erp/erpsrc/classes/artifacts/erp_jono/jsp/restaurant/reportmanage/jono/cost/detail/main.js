init();

var grid;

function customExport(){
	exportXls(); 
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
	          "dojo/_base/array",
	          "dojo/dom-form",
	          "dojo/parser",
	          "dijit/form/FilteringSelect",
	          "dijit/form/Button",
	          "dijit/Dialog",
	          "dojo/domReady!" 
	  ], function(dom, array,domForm,parser,FilteringSelect) {
		parser.parse();
		
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
				label : "序号",
				className:'text-center',
				field : "rownumber",
				sortable:false,
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			},
	        {
				label : "商品编码",
				className:'text-center',
				field : "item_id",
			}, {
				label : "商品名称",
				field : "item_name",
				sortable:false
			}]], [[{
				label : "库存单位",
				className:'text-center',
				field : "item_dimension",
				sortable:false
			}, {
				label : "标准单价",
				className:'text-right',
				field : "item_price",
			}, {
				label : "期初库存数量",
				className:'text-right',
				field : "begin_count",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "期初库存金额",
				className:'text-right',
				field : "begin_amt",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "本期进货",
				className:'text-right',
				field : "sum1",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "本期进货金额",
				className:'text-right',
				field : "amt1",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "调入数量",
				className:'text-right',
				field : "sum2",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "调入金额",
				className:'text-right',
				field : "amt2",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "调出数量",
				className:'text-right',
				field : "sum3",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "调出金额",
				className:'text-right',
				field : "amt3",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "期末库存数量",
				className:'text-right',
				field : "end_count",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "期末库存金额",
				className:'text-right',
				field : "end_amt",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "消耗数量",
				className:'text-right',
				field : "sum7",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "消耗金额",
				className:'text-right',
				field : "amt7",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "标准数量",
				className:'text-right',
				field : "sum4",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "标准金额",
				className:'text-right',
				field : "amt4",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "原料报损数量",
				className:'text-right',
				field : "sum5",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "原料报损金额",
				className:'text-right',
				field : "amt5",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "出品报损数量",
				className:'text-right',
				field : "sum6",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "出品报损金额",
				className:'text-right',
				field : "amt6",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "差异数量",
				className:'text-right',
				field : "sum8",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}, {
				label : "差异金额",
				className:'text-right',
				field : "amt8",
				formatter : function(field, renderCell) {
					if (renderCell.rownumber == "合计") {
						return getBoldText(field);
					}
					
					return field;
				}
			}]]];
};


function initGrid() {
	var _url = appRoot + "/reportmanage/restaurant/cost/jono/doDetailQuery.action";
	_url = getUrl(_url);
	
	require([ 
	          "dojo/_base/lang", 
	          "dojo/_base/declare", 
	          "dgrid/Selection", 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/Observable", 
	          "dojo/store/Cache",
	          "dojo/store/Memory",
	          "dgrid/ColumnSet",
	          "dgrid/Keyboard",
	          "dojo/request/xhr", 
	          "dgrid/extensions/Pagination",
	          "dojo/domReady!" 
	         ], function(lang, declare,	Selection, OnDemandGrid, Observable, Cache,Memory,ColumnSet,Keyboard,xhr,Pagination) {
		xhr.post(_url, {
            handleAs : "json",
            data:getQuery()
        }).then(function(data) {
        	var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnSet,Keyboard,Pagination]);
    		grid = new CustomGrid({
    			store :  new Memory({
					data : data,
					idProperty : 'rownumber'
				}),
    			columnSets : getColumn(),
    			cellNavigation : false,
    			loadingMessage : '加载中...',
    			noDataMessage : "无数据！"
    		}, 'dataGrid');

    		grid.startup();
        }, function(err) {
            console.log(err);
        });
		
	});
};
