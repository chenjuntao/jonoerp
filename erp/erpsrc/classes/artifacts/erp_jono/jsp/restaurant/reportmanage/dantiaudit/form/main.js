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
	} else {
		startDate = '';
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	} else {
		endDate = '';
	}

	var branchCondition2 = dijit.byId('branchCondition2').get('value');

	var branchFlag = dojo.byId('branchFlag').value;
	var appendValue = "  AND ph.REQUESTER_ID = '" + loginBranchId + "' ";
	if (!isEmpty(branchFlag)) {
		appendValue = "";
	}

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(h.ANTIAUDIT_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	} else {
		startDate = '';
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(h.ANTIAUDIT_TIME,'YYYY-MM-DD') <= '" + endDate + "' "
	} else {
		endDate = '';
	}

	if (!isEmpty(branchCondition2)) {
		appendValue += " AND ph.REQUESTER_ID = '" + branchCondition2 + "' "
	}

	return appendValue;
}

function initCondition() {
	dojo.byId('inputJsonString').value = "";
	dijit.byId('branchCondition2').set('value', "");

	dojo.byId('append').value = getAppendValue();
}

var branchArr1 = [], branchArr2 = [], storageArr1 = [], storageArr2 = [];

function init() {
	require([ "dojo/dom", "dojo/request/xhr", "dojo/_base/array", "dojo/dom-form", "dojo/parser", "dijit/form/Select",
			"dojo/data/ObjectStore", "dojo/store/Memory", "dijit/form/FilteringSelect", "dijit/form/Button",
			"dijit/Dialog", "dojo/domReady!" ], function(dom, xhr, array, domForm, parser, Select, ObjectStore, Memory,
			FilteringSelect) {

		parser.parse();

		// 函数延迟声明
		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		var branchFlag = dom.byId('branchFlag').value;

		xhr.post(appRoot + "/common/listBs.action", {
			handleAs : "json",
			data : {
				branchType1 : "LOGISTICSCENTER,RESTAURANT",
				branchType2 : "LOGISTICSCENTER,RESTAURANT",
				branchFlag : branchFlag
			}
		}).then(function(data) {
			array.forEach(data.branch, function(item) {
				if (item.branchType == 'LOGISTICSCENTER') {
					branchArr1.push(item);
				} else {
					branchArr2.push(item);
				}
			});

			array.forEach(data.storage, function(item) {
				if (item.branchType == 'LOGISTICSCENTER') {
					storageArr1.push(item);
				} else {
					storageArr2.push(item);
				}
			});

			new FilteringSelect({
				id : "branchCondition2",
				value : "code",
				labelAttr : 'name',
				displayValueAttr : "name",
				searchAttr : "name",
				required : false,
				autoComplete : false,
				queryExpr : "*${0}*",
				store : new Memory({
					idProperty : "code",
					data : branchArr2
				}),
				style : "width: 150px;"
			}, "branchCondition2").startup();

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
	var _url = appRoot + "/reportmanage/common/doQuery.action";
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
				colId : "antiaudit_time",
				descending : false
			}, {
				colId : "form_ref_id",
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
						id : "antiaudit_time",
						field : "antiaudit_time",
						name : "反审核日期",
						autoComplete : false,
						filterable : false
					},
					{
						id : "form_ref_id",
						field : "form_ref_id",
						name : "配送单号",
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
						id : "antiauditor",
						field : "antiauditor",
						name : "反审核人员",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "antiaudit_branch",
						field : "antiaudit_branch",
						name : "反审核部门",
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
					},
					{
						id : "provider",
						field : "provider",
						name : "配送部门",
						autoComplete : false,
						sortable : false,
						filterable : false
					},
					{
						id : "out_storage",
						field : "out_storage",
						name : "出货仓库",
						autoComplete : false,
						sortable : false,
						filterable : false
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
						name : "配送日期",
						autoComplete : false,
						dataType : 'date',
						disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ]
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
						id : "input_time",
						field : "input_time",
						name : "入库日期",
						autoComplete : false,
						dataType : 'date',
						disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ]
					},
					{
						id : "max_pay_item",
						field : "max_pay_item",
						name : "主要配送品",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "status",
						field : "status",
						name : "单据状态",
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
