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

function doOrder(cellData, rowId) {
	return "<a href='#' onclick='dofinish(\"" + cellData + "\",\"" + rowId + "\")'><b>" + '结案' + "</b></a>";
}

function dofinish(formId, rowId) {
	var row = grid.row(rowId).rawData();
	if (row.status == "已结案") {
		alert("此工单已结案！");
		return;
	}
	var _url = appRoot + "/centralfactory/productionDemand/orderSummary/create/doOrder.action?formId=" + formId;
	_url = getUrl(_url);
	
	if (confirm("确定生产工单结案？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("结案成功！");
				doQuery();
			}, function(err) {
			});
		});
	}
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

	var appendValue = "";

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(h.FORM_TIME,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	return appendValue;
}

function initCondition() {
	dojo.byId('inputJsonString').value = "";

	dojo.byId('append').value = getAppendValue();
}

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

		initCondition();

		initGrid();
		createColumnSelection();

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

	require([ "dojo/dom", "gridx/Grid", "gridx/core/model/cache/Async", "gridx/modules/Pagination",
			"gridx/modules/pagination/PaginationBar", "gridx/modules/Filter", "gridx/modules/filter/FilterBar",
			"custom/store/Server", "gridx/modules/NestedSort", "gridx/modules/VirtualVScroller",
			"gridx/modules/ColumnResizer", "gridx/modules/ColumnLock", "dojo/date/locale", "dojo/domReady!" ],
			function(dom, Grid, Cache, Pagination, PaginationBar, Filter, FilterBar, Server, NestedSort,
					VirtualVScroller, ColumnResizer, ColumnLock, locale) {
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
						colId : "form_time",
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
								id : "form_time",
								field : "form_time",
								name : "制单日期",
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ],
								decorator : function(cellData, rowId, rowIndex) {
									if (isEmpty(cellData)) {
										return "";
									} else {
										var dt = new Date(cellData.substring(0, 10));
										var dateString = locale.format(dt, {
											selector : "date",
											datePattern : "yyyy-MM-dd"
										});
										return dateString;
									}
								}
							},
							{
								id : "form_id",
								field : "form_id",
								name : "工单编号",
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
								name : "计划单编号",
								autoComplete : false,
								sortable : false,
								filterable : false,
								decorator : function(cellData, rowId, rowIndex) {
									if (!cellData) {
										return '';
									}
									return getHref(cellData);
								}
							},
							{
								id : "order",
								field : "form_id",
								name : "工单结案",
								autoComplete : false,
								decorator : function(cellData, rowId, rowIndex) {
									if (!cellData) {
										return '';
									}
									return doOrder(cellData, rowId);
								}
							},
							{
								id : "form_time_actual",
								field : "form_time_actual",
								name : "操作时间",
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ]
							},
							{
								id : "complete_time",
								field : "complete_time",
								name : "完工日期",
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ],
								decorator : function(cellData, rowId, rowIndex) {
									if (isEmpty(cellData)) {
										return "";
									} else {
										var dt = new Date(cellData.substring(0, 10));
										var dateString = locale.format(dt, {
											selector : "date",
											datePattern : "yyyy-MM-dd"
										});
										return dateString;
									}
								}
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
								id : "item_specification",
								field : "item_specification",
								name : "规格",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_dimension2",
								field : "item_dimension2",
								name : "包装单位",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_count2",
								field : "item_count2",
								name : "计划生产数量",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "actual_count",
								field : "actual_count",
								name : "实际生产数量",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "item_dimension",
								field : "item_dimension",
								name : "库存单位",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_count",
								field : "item_count",
								name : "计划生产库存数量",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							},
							{
								id : "form_maker",
								field : "form_maker",
								name : "制单人员",
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
								id : "audit_time",
								field : "audit_time",
								name : "审核日期",
								autoComplete : false,
								dataType : 'date',
								disabledConditions : [ 'before', 'after', 'isEmpty', 'past' ],
								decorator : function(cellData, rowId, rowIndex) {
									if (isEmpty(cellData)) {
										return "";
									} else {
										var dt = new Date(cellData.substring(0, 10));
										var dateString = locale.format(dt, {
											selector : "date",
											datePattern : "yyyy-MM-dd"
										});
										return dateString;
									}
								}
							},
							{
								id : "form_note",
								field : "form_note",
								name : "备注",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							} ],
					modules : [ Pagination, PaginationBar, Filter, FilterBar, NestedSort, VirtualVScroller,
							ColumnResizer, ColumnLock ]
				});

				grid.placeAt("gridContainer");
				grid.startup();
			});
}
