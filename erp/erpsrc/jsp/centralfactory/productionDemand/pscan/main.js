init();

var grid;

function doQuery(){
	dojo.byId('append').value = getAppendValue();
	
	grid.filter.refresh();
}

function getAppendValue(){
	if(!isEmpty(dojo.byId("startDate").value)){
		startDate = dojo.byId("startDate").value;
	}
	
	if(!isEmpty(dojo.byId("endDate").value)){
		endDate = dojo.byId("endDate").value;
	}
	
	
	var appendValue = "	AND h.REQUESTER_ID = 'L00'  AND h.PROVIDER_ID = 'F00' ";
	
	if(!isEmpty(startDate)){
		appendValue += "  AND TO_CHAR(h.AUDIT_TIME,'YYYY-MM-DD') >= '" + startDate + "' " ;
	}
	
	if(!isEmpty(endDate)){
		appendValue += "  AND TO_CHAR(h.AUDIT_TIME,'YYYY-MM-DD') <= '" + endDate + "' " 
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
		
		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		initCondition();
		    
		initGrid();
			
	});
}

var columns,names;

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
			sortInitialOrder: [{colId: "loss_time", descending: false},{colId: "form_id", descending: false}],
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
								id : "form_id",
								field : "form_id",
								name : "单据编号",
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
								id : "requester",
								field : "requester",
								name : "订货部门",
								autoComplete : false,
								sortable : false,
								filterable : false
							},
							{
								id : "receive_time",
								field : "receive_time",
								name : "到货日期",
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty',
										'past' ]
							},
							{
								id : "auditor",
								field : "auditor",
								name : "审核人员",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							},
							{
								id : "audit_time",
								field : "audit_time",
								name : "审核日期",
								autoComplete : false,
								filterable : false
							},
							{
								id : "form_note",
								field : "form_note",
								name : "备注信息",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							} ,
							{
								id : "max_pay_item",
								field : "max_pay_item",
								name : "主要采购品",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							} ,
							{
								id : "all_pay_amt",
								field : "all_pay_amt",
								name : "总金额",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							}],
							
			modules : [ Pagination, PaginationBar, Filter, FilterBar,
			            NestedSort, VirtualVScroller,
					ColumnResizer, ColumnLock ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
