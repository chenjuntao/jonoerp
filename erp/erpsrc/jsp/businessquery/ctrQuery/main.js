var grid;

function customExport() {
	var columns = getColumn();
	var newCols = [];

	var args1 = [ 'center', 'left', 'center','left' ];
	var args2 = [ 50, 120, 80 ,180];

	require([ "dojo/_base/array" ], function(array) {
		array.forEach(columns[0][0], function(item, i) {
			newCols.unshift({
				display : item.label,
				name : item.field,
				align : args1[i],
				width : args2[i]
			});
		});

		array.forEach(columns[1][0], function(item, i) {
			newCols.unshift({
				display : item.label,
				name : item.field,
				align : 'right',
				width : '120'
			});
		});
	
	});
	
	newCols.reverse();
	
	fillData(newCols);
}

function print() {
	prn1_preview(getExportArgs());
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

function getAppendValue() {
	if (!isEmpty(dojo.byId("startDate").value)) {
		startDate = dojo.byId("startDate").value;
	} else {
		startDate = '';
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	} else {
		endDate = '';
	}

	var appendValue2 = ' 1=1';

	if (!isEmpty(startDate)) {
		appendValue2 += " AND TO_CHAR(b.DBUSINESS,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue2 += "  AND TO_CHAR(b.DBUSINESS,'YYYY-MM-DD') <= '" + endDate + "' "
	}
	
	var branchCondition = dijit.byId('branchCondition').get('value');
	if(branchCondition.length >0){
		appendValue2 += "  AND b.CBRANCH_C = '"+ branchCondition +"' ";
	}
	
	//------------------------------------------------------------
	var appendValue1 = dijit.byId('tagCondition').get('value');
	//--------------------------------------------------------------
	var appendValue = '';
	var tagCondition = dijit.byId('tagCondition').get('value');
	if(tagCondition.length >0){
		appendValue = " AND d1.TAG_ID = '"+ tagCondition +"' ";
	}
	
	return appendValue+'|_|'+appendValue1+'|_|'+appendValue2;
}

dojo.ready(function() {
	initGrid();
});

function doQuery() {
	dojo.byId('append').value = getAppendValue();
	grid.set('query', getQuery());
}

var grid = null;

function setEmptyQuery(){
	var appendValue = " |_|  |_| 1=2";
	dojo.byId('append').value = appendValue;
}

function initGrid() {
	setEmptyQuery();
	
	var _url = appRoot + "/reportmanage/special/common/doQuery.action";
	
	require([ 
	          "dgrid/Grid", 
	          "custom/store/Server", 
	          "dojo/store/Observable", 
	          "dojo/store/Cache", 
	          "dojo/store/Memory",
	          "dojo/parser", 
	          "dojo/_base/declare",
	          "dgrid/extensions/ColumnResizer",
	          'dgrid/extensions/CompoundColumns',
	          "dgrid/ColumnSet",
	          "dojo/dom-form", 
	          "dgrid/extensions/Pagination",
	          "dojo/request/xhr",
	          "dijit/form/FilteringSelect",
	          "dojo/number",
	          "dojo/domReady!" 
          ], function(Grid, Server, Observable, Cache, Memory,	parser, declare, ColumnResizer, CompoundColumns,ColumnSet, domForm,Pagination,xhr,FilteringSelect,number) {
		parser.parse();
		
		xhr.post(appRoot + "/common/listBs.action", {
			handleAs : "json",
			data : {
				branchType1 : "RESTAURANT",
				branchFlag : "branchFlag"
			}
		}).then(function(data) {
			new FilteringSelect({
				id : "branchCondition",
				value : "code",
				labelAttr : 'name',
				displayValueAttr : "name",
				searchAttr : "name",
				required : false,
				autoComplete : false,
				queryExpr : "*${0}*",
				store : new Memory({
					idProperty : "code",
					data : data.branch
				}),
				style : "width: 140px;"
			}, "branchCondition").startup();

		}, function(err) {
			console.log(err);
		});
		
		xhr.post(appRoot + "/hq/config/tag/doQuery.action", {
			handleAs : "json",
			data : {
			}
		}).then(function(data) {
			new FilteringSelect({
				id : "tagCondition",
				value : "tagId",
				labelAttr : 'tagName',
				displayValueAttr : "tagName",
				searchAttr : "tagName",
				required : false,
				autoComplete : false,
				queryExpr : "*${0}*",
				store : new Memory({
					idProperty : "tagId",
					data : data
				}),
				style : "width: 140px;"
			}, "tagCondition").startup();

		}, function(err) {
			console.log(err);
		});
		
		window.getFormatNumber = function(numberText) {
			return number.format(numberText, {
			    pattern: "#.##%"
		  });;
		}
		
		window.getFormatNumber2 = function(numberText) {
			return number.format(numberText, {
			    pattern: "#.##"
		  });;
		}
		
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty: "rownumber",
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, ColumnResizer,CompoundColumns, ColumnSet,Pagination ]);

		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "小类名称",
		field : "clitcls_n",
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):field;
   		}
	} ,{
		label : "出品编码",
		field : "cfood_c",
		className : 'text-center',
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):field;
   		}
	}, {
		label : "出品名称",
		field : "cfood_n",
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):field;
   		}
	}] ], [ [ {
		label : "出品数",
		field : "nqty",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):field;
   		}
	}, {
		label : "桌数",
		field : "bills",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):field;
   		}
	}, {
		label : "点击率",
		field : "ctr",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):field;
   		}
	}, {
		label : "出品售卖价",
		field : "sprice",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):field;
   		}
	}, {
		label : "进货成本价",
		field : "pprice",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(field):!field?" ":getFormatNumber2(field);
   		}
	}, {
		label : "进货毛利率",
		field : "pper",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
			return !renderCell.cfood_c? getBoldText(field):!field?" ":getFormatNumber(field);
   		}
	}, {
		label : "标准成本价",
		field : "bprice",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
			return !renderCell.cfood_c? getBoldText(field):!field?" ":getFormatNumber2(field);
   		}
	}, {
		label : "标准毛利率",
		field : "bper",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
			return !renderCell.cfood_c? getBoldText(field):!field?" ":getFormatNumber(field);
   		}
	}, {
		label : "小类占比",
		field : "gdp",
		className : 'text-right',
		sortable : false,
		formatter : function(field, renderCell) {
   			return !renderCell.cfood_c? getBoldText(getFormatNumber(field)):field;
   		}
	}] ] ];
}

