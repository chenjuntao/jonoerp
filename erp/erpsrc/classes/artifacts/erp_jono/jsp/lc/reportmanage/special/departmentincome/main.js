var grid;

function customExport() {
	var columns = getColumn();
	var newCols = [];

	var args1 = [ 'center', 'center', 'center' ];
	var args2 = [ 50, 100, 80 ];

	require([ "dojo/_base/array" ], function(array) {
		array.forEach(columns[0][0][0].children, function(item, i) {
			newCols.unshift({
				display : item.label,
				name : item.field,
				align : args1[i],
				width : args2[i]
			});
		});
		
		for(var count=0;count<10;count++){
			var temp = columns[1][0][count];
			array.forEach(temp.children, function(item, i) {
				newCols.unshift({
					display : temp.label+item.label,
					name : item.field,
					align : 'right',
					width : '120'
				});
			});
		}
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
	var appendValue = ' 1=2 ';
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
	          "dojo/number",
	          "dojo/domReady!" 
          ], function(Grid, Server, Observable, Cache, Memory,	parser, declare, ColumnResizer,CompoundColumns, ColumnSet, domForm,Pagination,xhr,FilteringSelect,number) {
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
		
		window.getFormatNumber = function(numberText) {
			return number.format(numberText, {
			    pattern: "#.##%"
		  });;
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
	return [ [ [
    {
		label: '项目',
		field:'title0', 
		sortable : false,
		children: [
           {
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
          ] 
		}]],
	[[
	  {
		  label : '主食',
		  field:'title1',
		  sortable : false,
		  children: [
			{
				label : '金额',
				field : 'id_1011',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '占比',
				field : 'id_1011_per',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:getFormatNumber(field));
					}
					
					return !field?0:getFormatNumber(field);
				}
			}        
             ]
		  },
	  {
		  label : '中餐',
		  field:"title2",
		  sortable : false,
		  children: [
			{
				label : '金额',
				field : 'chinese',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:field);
					}
					
					return !field?0:field;
				}
			},{
				label : '占比',
				field : 'chinese_per',
				sortable : false,
				className:'text-right',
				formatter : function(field, renderCell) {
					if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
						return getBoldText(!field?0:getFormatNumber(field));
					}
					
					return !field?0:getFormatNumber(field);
				}
			}]         
		  },
		   {
			  label : '煲浇西式饭',
			  field:"title3",
			  sortable : false,
			  children: [
				{
					label : '金额',
					field : 'id_1017',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:field);
						}
						
						return !field?0:field;
					}
				},{
					label : '占比',
					field : 'id_1017_per',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:getFormatNumber(field));
						}
						
						return !field?0:getFormatNumber(field);
					}
				}       
			             ]
			  }, 
		  {
			  label : '凉菜',
			  field:"title4",
			  sortable : false,
			  children: [
				{
					label : '金额',
					field : 'id_1013',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:field);
						}
						
						return !field?0:field;
					}
				},{
					label : '占比',
					field : 'id_1013_per',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:getFormatNumber(field));
						}
						
						return !field?0:getFormatNumber(field);
					}
				}    
	             ]
			  },
		  {
			  label : '中式小吃',
			  field:"title5",
			  sortable : false,
			  children: [
				{
					label : '金额',
					field : 'id_1014',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:field);
						}
						
						return !field?0:field;
					}
				},{
					label : '占比',
					field : 'id_1014_per',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:getFormatNumber(field));
						}
						
						return !field?0:getFormatNumber(field);
					}
				}
			      ]
			  },
		  {	
			  label : '西式小吃',
			  field:"title6",
			  sortable : false,
			  children: [
				{
					label : '金额',
					field : 'id_2024',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:field);
						}
						
						return !field?0:field;
					}
				},{
					label : '占比',
					field : 'id_2024_per',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:getFormatNumber(field));
						}
						
						return !field?0:getFormatNumber(field);
					}
				}
			        ]
			  },
		  	{			
				  label : '水果',
				  field:"title7",
				  sortable : false,
				  children: [
					{
						label : '金额',
						field : 'id_2029',
						sortable : false,
						className:'text-right',
						formatter : function(field, renderCell) {
							if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
								return getBoldText(!field?0:field);
							}
							
							return !field?0:field;
						}
					},{
						label : '占比',
						field : 'id_2029_per',
						sortable : false,
						className:'text-right',
						formatter : function(field, renderCell) {
							if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
								return getBoldText(!field?0:getFormatNumber(field));
							}
							
							return !field?0:getFormatNumber(field);
						}
					}
				       ]
				  },
		  {
			  label : '酒水线',
			  field:"title8",
			  sortable : false,
			  children: [
				{
					label : '金额',
					field : 'drink',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:field);
						}
						
						return !field?0:field;
					}
				},{
					label : '占比',
					field : 'drink_per',
					sortable : false,
					className:'text-right',
					formatter : function(field, renderCell) {
						if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
							return getBoldText(!field?0:getFormatNumber(field));
						}
						
						return !field?0:getFormatNumber(field);
					}
				}
			        ]
			  },
			  {
				  label : '西线',
				  field:"title9",
				  sortable : false,
				  children: [
					{
						label : '金额',
						field : 'west',
						sortable : false,
						className:'text-right',
						formatter : function(field, renderCell) {
							if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
								return getBoldText(!field?0:field);
							}
							
							return !field?0:field;
						}
					},{
						label : '占比',
						field : 'west_per',
						sortable : false,
						className:'text-right',
						formatter : function(field, renderCell) {
							if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
								return getBoldText(!field?0:getFormatNumber(field));
							}
							
							return !field?0:getFormatNumber(field);
						}
					}
				             ]
				  },
			  {
				  label : '其他',
				  field:"title10",
				  sortable : false,
				  children: [
					{
						label : '金额',
						field : 'other',
						sortable : false,
						className:'text-right',
						formatter : function(field, renderCell) {
							if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
								return getBoldText(!field?0:field);
							}
							
							return !field?0:field;
						}
					},{
						label : '占比',
						field : 'other_per',
						sortable : false,
						className:'text-right',
						formatter : function(field, renderCell) {
							if (renderCell.dbusiness == "周汇总" || renderCell.dbusiness == "合计") {
								return getBoldText(!field?0:getFormatNumber(field));
							}
							
							return !field?0:getFormatNumber(field);
						}
					}
				             ]
				  }]]];
}
