init();

var grid;

function customExport(){
	updateCopy(getExportArgs());
	exportXls(); 
}

function doQuery(){
	dojo.byId('append').value = getAppendValue();
	
	grid.filter.refresh();
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

function getAppendValue(){
	if(!isEmpty(dojo.byId("startDate").value)){
		startDate = dojo.byId("startDate").value;
	}else{
		startDate = '';
	}
	
	if(!isEmpty(dojo.byId("endDate").value)){
		endDate = dojo.byId("endDate").value;
	}else{
		endDate = '';
	}
	
	var appendValue = "";
	
	if(!isEmpty(startDate)){
		appendValue += "  AND TO_CHAR(h.form_time,'YYYY-MM-DD') >= '" + startDate + "' " ;
	}
	
	if(!isEmpty(endDate)){
		appendValue += "  AND TO_CHAR(h.form_time,'YYYY-MM-DD') <= '" + endDate + "' " 
	}
	
	
	return appendValue;
}

function initCondition(){
	dojo.byId('inputJsonString').value = "";
	
	dojo.byId('append').value = getAppendValue();
}

function init() {
	require([ 
	          "dojo/dom", 
	          "dojo/request/xhr", 
	          "dojo/_base/array",
	          "dojo/dom-form",
	          "dojo/parser",
	          "dijit/form/Select",
	          "dojo/data/ObjectStore",
	          "dojo/store/Memory",
	          "dijit/form/FilteringSelect",
	          "dijit/form/Button",
	          "dijit/Dialog",
	          "dojo/domReady!" ], function(dom, xhr, array,
			domForm,parser,Select,ObjectStore,Memory,FilteringSelect) {
		  
		parser.parse();
		
		var branchType = dom.byId('branchType').value;
		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		xhr.post(appRoot + "/common/listBs.action", {
			handleAs : "json",
			data : {
				branchType1 : branchType
			}
		}).then(function(data) {
			    new FilteringSelect({
			        id: "branchCondition",
			        value:"code",
			        labelAttr : 'name',
			        displayValueAttr:"name",
			        searchAttr: "name",
			        required : false,
					autoComplete : false,
					queryExpr: "*${0}*",
			        store: new Memory({ idProperty: "code", data: data.branch }),
			        style: "width: 150px;"
			    }, "branchCondition").startup();
			    
			    initCondition();
			    
				initGrid();
				createColumnSelection();
		}, function(err) {
					console.log(err);
				});

	});
}

var columns,names;

var getExportArgs = function(){
		columns = [],names = [];
	for(var i = 0; i < c.length; i++){
		if(c[i].checked){
			columns.push(c[i].id);
			names.push(c[i].name);
		}
	}
	
	return {
		columns: columns,
		names : names
	};
};

var c = [], createColumnSelection = function (){
	if(grid){
		var columns = grid.columns(), i = 0, label, tr, td;
		for(; i < columns.length; i++){
			if(i%2 == 0){
				tr = dojo.create("tr");
				dojo.byId('columnSelection').appendChild(tr);
			}
			td = dojo.create("td");
			c[i] = new dijit.form.CheckBox({
				id: columns[i].id,
				checked: true,
				name: columns[i].name()
			});
			c[i].startup();
			td.appendChild(c[i].domNode);
			label = dojo.create("label", {innerHTML: columns[i].name() + "  "});
			td.appendChild(label);
			tr.appendChild(td);
		}
	}
};

function initGrid() {
	var _url = appRoot + "/reportmanage/common/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "gridx/Grid", "gridx/core/model/cache/Async",
			"gridx/modules/Pagination",
			"gridx/modules/pagination/PaginationBar", "gridx/modules/Filter",
			"gridx/modules/filter/FilterBar", "custom/store/Server",
			"gridx/modules/NestedSort",
			"gridx/modules/VirtualVScroller", "gridx/modules/ColumnResizer",
			"gridx/modules/ColumnLock", "dojo/domReady!" ], function(dom, Grid,
			Cache, Pagination, PaginationBar, Filter, FilterBar, Server,
			NestedSort, VirtualVScroller, ColumnResizer,
			ColumnLock) {
		var store = Server({
			idProperty : 'rownumber',
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		});

		grid = new Grid({
			id : 'dataGrid',
			store : store,
			cacheClass : Cache,
			columnLockCount : 3,
			paginationBarShowRange : true,
			paginationInitialPageSize : 25,
			pageSize : 25,
			sortInitialOrder: [{colId: "form_time", descending: false},{colId: "form_id", descending: false}],
			filterServerMode : true,
			filterSetupQuery : function(expr) {
				dom.byId("inputJsonString").value = JSON.stringify(expr);
				return {
					query : {}
				};
			},
			structure : [
							{
								id : "rownumber",
								field : "rownumber",
								name : "序号",
								filterable : false,
								sortable : false,
								decorator : function(cellData, rowId, rowIndex) {
									if('total' == cellData){
										return '合计';
									}
									return cellData;
								}
							},
							{
								id : "form_time",
								field : "form_time",
								name : "制单日期",
								autoComplete : false,
								filterable : false
							},
							{
								id : "form_id",
								field : "form_id",
								name : "生产计划单编号",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ],
								decorator : function(cellData, rowId, rowIndex) {
									if(!cellData){
										return '';
									}
									return getHref(cellData);
								}
							},
							{
								id : "audit_time",
								field : "audit_time",
								name : "审核日期",
								dateParsePattern : 'yyyy-MM-dd',
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty',
										'past' ]
							},
							{
								id : "item_id",
								field : "item_id",
								name : "商品编码",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_name",
								field : "item_name",
								name : "商品名称",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_dimension",
								field : "item_dimension",
								name : "单位",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_specification",
								field : "item_specification",
								name : "规格",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "produce_count",
								field : "produce_count",
								name : "计划生产量",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "work_order_id",
								field : "work_order_id",
								name : "工单编号",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ],
								decorator : function(cellData, rowId, rowIndex) {
									if(!cellData){
										return '';
									}
									return getHref(cellData);
								}
							},
							{
								id : "workshop",
								field : "workshop",
								name : "生产车间",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "produce_time",
								field : "produce_time",
								name : "生产日期",
								dateParsePattern : 'yyyy-MM-dd',
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty',
										'past' ]
							},
							{
								id : "production_cycle",
								field : "production_cycle",
								name : "生产周期",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "complete_time",
								field : "complete_time",
								name : "完工日期",
								dateParsePattern : 'yyyy-MM-dd',
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty',
										'past' ]
							},{
							id : "note",
							field : "note",
							name : "备注",
							autoComplete : false,
							sortable : false,
							disabledConditions : [ 'equal', 'startWith', 'endWith',
													'notEqual', 'notContain', 'notStartWith',
													'notEndWith', 'isEmpty' ]
							}
						],
			modules : [ Pagination, PaginationBar, Filter, FilterBar,
			            NestedSort, VirtualVScroller,
					ColumnResizer, ColumnLock ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
