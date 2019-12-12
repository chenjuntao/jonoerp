init();

var grid;

function customExport() {
	updateCopy(getExportArgs());
	exportXls();
}

function doQuery() {
	dojo.byId('append').value = getAppendValue();

	grid.filter.refresh();
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
	}else{
		startDate = '';
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	}else{
		endDate = '';
	}

	var branchCondition = dijit.byId('branchCondition').get('value');
	var storageCondition = dijit.byId('storageCondition').get('value');

	//var appendValue = " AND b.BRANCH_TYPE = 'RESTAURANT' AND H.INPUT_DEPARTMENT_ID = '" + loginBranchId + "' ";
	var appendValue = " AND C.INPUT_DEPARTMENT_ID = '" + loginBranchId + "' ";
	
	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(C.INPUT_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(C.INPUT_TIME,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	if (!isEmpty(branchCondition)) {
		appendValue += " AND C.INPUT_DEPARTMENT_ID = '" + branchCondition + "' "
	}

	if (!isEmpty(storageCondition)) {
		appendValue += " AND C.STORAGE_ID = '" + storageCondition + "' "
	}

	return appendValue;
}

function initCondition() {
	dojo.byId('inputJsonString').value = "";
	dijit.byId('branchCondition').set('value', "");
	dijit.byId('storageCondition').set('value', "");

	dojo.byId('append').value = getAppendValue();
}

function init() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/_base/array", "dojo/dom-form", "dojo/parser", "dijit/form/Select",
			"dojo/data/ObjectStore", "dojo/store/Memory", "dijit/form/FilteringSelect", "dijit/form/Button",
			"dijit/Dialog", "dojo/domReady!" ], function(dom, xhr, array, domForm, parser, Select, ObjectStore, Memory,
			FilteringSelect) {

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
				branchType1 : branchType,
				branchType2 : branchType
			}
		}).then(function(data) {
			new FilteringSelect({
				id : "storageCondition",
				store : new Memory({
					idProperty : "storageId",
					data : data.storage
				}),
				required : false,
				value : "storageId",
				autoComplete : false,
				query : {
					branchId : /.*/
				},
				style : "width: 150px;",
				labelAttr : 'storageName',
				queryExpr : "*${0}*",
				displayValueAttr : "storageName",
				searchAttr : "storageName"
			}, "storageCondition").startup();

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
				style : "width: 150px;",
				onChange : function(current) {
					if (isEmpty(current)) {
						dijit.byId('storageCondition').query.branchId = /.*/;
					} else {
						dijit.byId('storageCondition').query.branchId = this.item.code || /.*/;
					}
					dijit.byId('storageCondition').set('value', "");
				}
			}, "branchCondition").startup();

			initCondition();

			initGrid();
			createColumnSelection();
		}, function(err) {
			console.log(err);
		});

	});
}

var columns, names;

var getExportArgs = function() {
	columns = [], names = [];
	for (var i = 0; i < c.length; i++) {
		if (c[i].checked) {
			columns.push(c[i].id);
			names.push(c[i].name);
		}
	}

	return {
		columns : columns,
		names : names
	};
};

var c = [], createColumnSelection = function() {
	if (grid) {
		var columns = grid.columns(), i = 0, label, tr, td;
		for (; i < columns.length; i++) {
			if (i % 2 == 0) {
				tr = dojo.create("tr");
				dojo.byId('columnSelection').appendChild(tr);
			}
			td = dojo.create("td");
			c[i] = new dijit.form.CheckBox({
				id : columns[i].id,
				checked : true,
				name : columns[i].name()
			});
			c[i].startup();
			td.appendChild(c[i].domNode);
			label = dojo.create("label", {
				innerHTML : columns[i].name() + "  "
			});
			td.appendChild(label);
			tr.appendChild(td);
		}
	}
};

function initGrid() {
	var _url = appRoot + "/reportmanage/common/doirQuery.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "gridx/Grid", "gridx/core/model/cache/Async", "gridx/modules/Pagination",
			"gridx/modules/pagination/PaginationBar", "gridx/modules/Filter", "gridx/modules/filter/FilterBar",
			"custom/store/Server", "gridx/modules/NestedSort", "gridx/modules/VirtualVScroller",
			"gridx/modules/ColumnResizer", "gridx/modules/ColumnLock", "dojo/domReady!" ], function(dom, Grid, Cache,
			Pagination, PaginationBar, Filter, FilterBar, Server, NestedSort, VirtualVScroller, ColumnResizer,
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
			sortInitialOrder : [ {
				colId : "input_time",
				descending : false
			}, {
				colId : "form_id",
				descending : false
			} ],
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
							if ('total' == cellData) {
								return '合计';
							}
							return cellData;
						}
					},
					{
						id : "input_time",
						field : "input_time",
						name : "入库日期",
						autoComplete : false,
						filterable : false
					},
					{
						id : "form_id",
						field : "form_id",
						name : "入库单号",
						autoComplete : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ],
						decorator : function(cellData, rowId, rowIndex) {
							if (!cellData) {
								return '';
							}
							return getHref(cellData);
						}
					},
					{
						id : "form_ref_id",
						field : "form_ref_id",
						name : "采购单号",
						autoComplete : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ],
						decorator : function(cellData, rowId, rowIndex) {
							if (!cellData) {
								return '';
							}
							return getHref(cellData, 'LITTLE');
						}
					},
					{
						id : "audit_time",
						field : "audit_time",
						name : "审核日期",
						autoComplete : false,
						dataType : 'date',
						disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ]
					},
					{
						id : "input_department",
						field : "input_department",
						name : "入库部门",
						autoComplete : false,
						sortable : false,
						filterable : false
					},
					{
						id : "storage",
						field : "storage",
						name : "仓库名称",
						autoComplete : false,
						sortable : false,
						filterable : false
					},
					{
						id : "provider",
						field : "provider",
						name : "供应商",
						autoComplete : false,
						sortable : false,
						filterable : false
					},
					{
						id : "inputer",
						field : "inputer",
						name : "入库人员",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "auditor",
						field : "auditor",
						name : "审核人员",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "max_pay_item",
						field : "max_pay_item",
						name : "主要货品",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
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
						id : "status",
						field : "status",
						name : "单据状态",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "form_note",
						field : "form_note",
						name : "备注信息",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					} ],
			modules : [ Pagination, PaginationBar, Filter, FilterBar, NestedSort, VirtualVScroller, ColumnResizer,
					ColumnLock ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
