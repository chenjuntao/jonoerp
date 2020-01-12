init();
var grid, branchArr1 = [], branchArr2 = [],branchArr3 = [];

function init() {
	require([ 
	          "dojo/dom", 
	          "dojo/request/xhr", 
	          "dojo/_base/array",
	          "dojo/dom-form", 
	          "dojo/domReady!" 
	         ], function(dom, xhr, array, domForm) {
		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		xhr.post(appRoot + "/common/listShop.action", {
			handleAs : "json",
			data : {
				branchType : "ALL"
			}
		}).then(function(data) {
			array.forEach(data, function(object) {
				if (object.branchType == 'SUPPLIER') {
					branchArr1.push({label:object.name,value:object.code});
				}

				if (object.branchType == 'RESTAURANT') {
					branchArr2.push({label:object.name,value:object.code});
				}
				
				if (object.branchType == 'CENTRALFACTORY') {
					branchArr3.push({label:object.name,value:object.code});
				}
			});

			testInitGrid();
		}, function(err) {
			console.log(err);
		});

	});
}

function testInitGrid() {
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
			columnLockCount : 2,
			paginationBarShowRange : true,
			paginationInitialPageSize : 25,
			pageSize : 25,
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
								name : "采购单编号",
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
								id : "provider_id",
								field : "provider_id",
								name : "供应商编码",
								dataType : 'enum',
								enumOptions : branchArr1,
								disabledConditions : [ 'contain', 'startWith',
										'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							},
							{
								id : "provider",
								field : "provider",
								name : "供应商",
								autoComplete : false,
								filterable : false,
								sortable : false
							},
							{
								id : "requester_id",
								field : "requester_id",
								name : "订货部门编码",
								autoComplete : false,
								dataType : 'enum',
								enumOptions : branchArr3,
								disabledConditions : [ 'contain', 'startWith',
										'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							},
							{
								id : "requester",
								field : "requester",
								name : "订货部门",
								autoComplete : false,
								filterable : false,
								sortable : false
							},
							{
								id : "receive_address",
								field : "receive_address",
								name : "收货地址",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
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
								id : "form_maker_id",
								field : "form_maker_id",
								name : "制单人员编码",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							},
							{
								id : "form_maker",
								field : "form_maker",
								name : "制单人员",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "form_time",
								field : "form_time",
								name : "制单日期",
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty',
										'past' ]
							},
							{
								id : "auditor_id",
								field : "auditor_id",
								name : "审核人员编码",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
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
							},{
								id : "audit_time",
								field : "audit_time",
								name : "审核日期",
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty',
										'past' ]
							},
							{
								id : "form_note",
								field : "form_note",
								name : "备注说明",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							} ,
							{
								id : "storage_id",
								field : "storage_id",
								name : "收货仓库编码",
								autoComplete : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
										'notEqual', 'notContain', 'notStartWith',
										'notEndWith', 'isEmpty' ]
							},
							{
								id : "storage",
								field : "storage",
								name : "收货仓库名称",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "all_pay_amt",
								field : "all_pay_amt",
								name : "总金额",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "max_pay_item",
								field : "max_pay_item",
								name : "主要采购品",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith',
														'notEqual', 'notContain', 'notStartWith',
														'notEndWith', 'isEmpty' ]
							},
							{
								id : "status",
								field : "status",
								name : "单据状态",
								sortable : false,
								autoComplete : false,
								dataType : 'enum',
								enumOptions : [ '未审核', '已审核'],
								disabledConditions : [ 'contain', 'startWith',
										'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							}],
			modules : [ Pagination, PaginationBar, Filter, FilterBar,
			            NestedSort, VirtualVScroller,
					ColumnResizer, ColumnLock ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
