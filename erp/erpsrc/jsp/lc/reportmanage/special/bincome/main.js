var grid;

function customExport() {
	var columns = getColumn();
	var newCols = [];

	var args1 = [ 'center', 'center', 'center','center','right','right' ];
	var args2 = [ 50, 100, 80 ,80,80,80];

	require([ "dojo/_base/array" ], function(array) {
		array.forEach(columns[0][0], function(item, i) {
			newCols.unshift({
				display : item.label,
				name : item.field,
				align : args1[i],
				width : args2[i]
			});
		});

		array.forEach(columns[1][0][0].children, function(item, i) {
			newCols.unshift({
				display : item.label,
				name : item.field,
				align : 'right',
				width : '120'
			});
		});
		
		array.forEach(columns[1][0][1].children, function(item, i) {
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


	var appendValue = ' 1=1';


	if (!isEmpty(startDate)) {
		appendValue += " AND TO_CHAR(b.DBUSINESS,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(b.DBUSINESS,'YYYY-MM-DD') <= '" + endDate + "' "
	}
	
	var branchCondition = dijit.byId('branchCondition').get('value');
	if(branchCondition.length >0){
		appendValue += "  AND b.CBRANCH_C = '"+ branchCondition +"' ";
	}

	return appendValue;
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
	var appendValue = ' 1=2';
	dojo.byId('append').value = appendValue;
}

function initGrid() {
	setEmptyQuery();
	
	var _url = appRoot + "/reportmanage/special/common/doQuery.action";
	_url = getUrl(_url);
	
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
	          "dojo/domReady!" 
          ], function(Grid, Server, Observable, Cache, Memory,	parser, declare, ColumnResizer, CompoundColumns,ColumnSet, domForm,Pagination,xhr,FilteringSelect) {
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

		var CustomGrid = declare([ Grid, ColumnResizer, CompoundColumns,ColumnSet,Pagination ]);

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
	return [ 
	[ [ {
		label : "序号",
		field : "rownumber",
		className:'text-center',
		sortable : false
	}, {
		label : '日期',
		field : 'dbusiness',
		sortable : false,
		className:'text-center',
		formatter : function(field, renderCell) {
			if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
				return getBoldText(!field?0:field);
			}
			
			return !field?0:field;
		}
	}, {
		label : '星期',
		field : 'dayinfo',
		sortable : false,
		className:'text-center',
		formatter : function(field, renderCell) {
			if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
				return getBoldText(!field?0:field);
			}
			
			return !field?0:field;
		}
	}
	,{
		label : '预充值',
		field : 'AMT1',
		sortable : false,
		className:'text-right',
		formatter : function(field, renderCell) {
			if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
				return getBoldText(!field?0:field);
			}
			
			return !field?0:field;
		}
	},
	{
		label : '总营业额',
		field : 'lsum',
		sortable : false,
		className:'text-right',
		formatter : function(field, renderCell) {
			return getBoldText(!field?0:field);
		}
	}] ], [[ 
	  {
          label: '实销营业额',
          field:'title1',
          sortable : false,
          children: [
		      {
				label : '现金',
				field : '01',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '微信现金',
				field : '04',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			} ,{
				label : '银行卡',
				field : '02',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '会员ID卡',
				field : '03',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '预充支付',
				field : '07',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '美团网团购',
				field : '16',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '招商银行代金',
				field : '23',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '签单',
				field : '18',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '汇总',
				field : 'psum',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					return getBoldText(!field?0:field);
				}
			}
          ]
      },
      {
	  	label : '非实销营业额',
	  	field:'title2',	
	  	sortable : false,
	    children: [
			{
				label : '微信代金券',
				field : '08',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			} ,{
				label : '微信礼品券',
				field : '09',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			} ,{
				label : '免单',
				field : '19',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			} ,{
				label : '赠送',
				field : 'AMT2',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '汇总',
				field : 'nsum',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					return getBoldText(!field?0:field);
				}
		}]
      }
    ]]];
}
