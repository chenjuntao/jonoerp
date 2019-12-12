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
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	}

	var branchCondition = dijit.byId('branchCondition').get('value');
	var storageCondition = dijit.byId('storageCondition').get('value');

	var appendValue = " AND b.BRANCH_TYPE = 'CENTRALFACTORY' ";

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(h.AUDIT_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(h.AUDIT_TIME,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	if (!isEmpty(branchCondition)) {
		appendValue += " AND h.INPUT_DEPARTMENT_ID = '" + branchCondition + "' "
	}

	if (!isEmpty(storageCondition)) {
		appendValue += " AND h.STORAGE_ID = '" + storageCondition + "' "
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
				value : "storageId",
				required : false,
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
				style : "width: 130px;",
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
				colId : "audit_time",
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
						id : "audit_time",
						field : "audit_time",
						name : "审核日期",
						autoComplete : false,
						dataType : 'date',
						autoComplete : false,
						dataType : 'date',
						disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ]
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
						id : "input_time",
						field : "input_time",
						name : "入库日期",
						autoComplete : false,
						filterable : false
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
							return getHref(cellData);
						}
					},
					{
						id : "input_department_id",
						field : "input_department_id",
						name : "部门编码",
						autoComplete : false,
						filterable : false
					},
					{
						id : "input_department",
						field : "input_department",
						name : "部门名称",
						autoComplete : false,
						sortable : false,
						filterable : false
					},
					{
						id : "storage_id",
						field : "storage_id",
						name : "仓库编码",
						autoComplete : false,
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
						id : "provider_id",
						field : "provider_id",
						name : "供应商编码",
						autoComplete : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "provider",
						field : "provider",
						name : "供应商",
						autoComplete : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "item_id",
						field : "item_id",
						name : "商品编码",
						autoComplete : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "item_name",
						field : "item_name",
						name : "商品名称",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "item_category",
						field : "item_category",
						name : "类别编码",
						autoComplete : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "category_name",
						field : "category_name",
						name : "类别名称",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "item_dimension",
						field : "item_dimension",
						name : "单位",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					},
					{
						id : "item_specification",
						field : "item_specification",
						name : "规格",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					}, {
						id : "order_count",
						field : "order_count",
						name : "订货量",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "receive_count",
						field : "receive_count",
						name : "实收数",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "different_count",
						field : "different_count",
						name : "实收差异",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "item_unit_price",
						field : "item_unit_price",
						name : "标准单价",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "pay_amt",
						field : "pay_amt",
						name : "标准金额",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "receive_price",
						field : "receive_price",
						name : "进货单价",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "receive_amt",
						field : "receive_amt",
						name : "进货金额",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "different_amt",
						field : "different_amt",
						name : "采购价差",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					} ],
			modules : [ Pagination, PaginationBar, Filter, FilterBar, NestedSort, VirtualVScroller, ColumnResizer,
					ColumnLock ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
