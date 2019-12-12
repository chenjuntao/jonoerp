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
	var startDate, endDate;
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

	var branchCondition = dijit.byId('branchCondition').get('value');
	var storageCondition = dijit.byId('storageCondition').get('value');
	var reasonType = dijit.byId('reasonType').get('value');
	var branchFlag = dojo.byId('branchFlag').value;
	var appendValue = " AND b.BRANCH_TYPE = 'RESTAURANT' AND io.BRANCH_ID = '" + loginBranchId + "' ";
	if (!isEmpty(branchFlag)) {
		appendValue = " AND b.BRANCH_TYPE = 'RESTAURANT' ";
	}

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(io.BUSINESS_DATE,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(io.BUSINESS_DATE,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	if (!isEmpty(branchCondition)) {
		appendValue += " AND io.BRANCH_ID = '" + branchCondition + "' "
	}

	if (!isEmpty(storageCondition)) {
		appendValue += " AND io.STORAGE_ID = '" + storageCondition + "' "
	}

	if (!isEmpty(reasonType)) {
		appendValue += " AND io.REASON = '" + reasonType + "' "
	}

	return appendValue;
}

function initCondition() {
	dojo.byId('inputJsonString').value = "";
	dijit.byId('branchCondition').set('value', "");
	dijit.byId('storageCondition').set('value', "");
	dijit.byId('reasonType').set('value', "");

	dojo.byId('append').value = getAppendValue();
}

var typeArr = [ {
	id : 'CGRK',
	displayName : '餐厅采购入库'
}, {
	id : 'PSRK',
	displayName : '餐厅配送入库'
}, {
	id : 'PSFSH',
	displayName : '餐厅配送反审核'
}, {
	id : 'PSTH',
	displayName : '餐厅配送退货'
}, {
	id : 'CGTH',
	displayName : '餐厅采购退货'
}, {
	id : 'CPBS',
	displayName : '餐厅出品报损'
}, {
	id : 'YLBS',
	displayName : '餐厅原料报损'
}, {
	id : 'DB',
	displayName : '餐厅调拨'
}, {
	id : 'PD',
	displayName : '餐厅盘点'
}, {
	id : 'LLKU',
	displayName : '餐厅销售扣仓'
}, {
	id : 'SSRK',
	displayName : '自产入库'
}, {
	id : 'SSHL',
	displayName : '自产耗料'
} ];

function getDisplayName(reason) {
	for (var i = 0, len = typeArr.length; i < len; i++) {
		var temp = typeArr[i];
		if (temp.id == reason) {
			return temp.displayName;
		}
	}
}

var enumOptionsArr = [ {
	value : 'CGRK',
	label : '餐厅采购入库'
}, {
	value : 'PSRK',
	label : '餐厅配送入库'
}, {
	value : 'PSFSH',
	label : '餐厅配送反审核'
}, {
	value : 'PSTH',
	label : '餐厅配送退货'
}, {
	value : 'CGTH',
	label : '餐厅采购退货'
}, {
	value : 'CPBS',
	label : '餐厅出品报损'
}, {
	value : 'YLBS',
	label : '餐厅原料报损'
}, {
	value : 'DB',
	label : '餐厅调拨'
}, {
	value : 'PD',
	label : '餐厅盘点'
}, {
	value : 'LLKU',
	label : '餐厅销售扣仓'
}, {
	value : 'SSRK',
	label : '自产入库'
}, {
	value : 'SSHL',
	label : '自产耗料'
} ];

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
				branchType2 : branchType,
				branchFlag : dojo.byId('branchFlag').value
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
				style : "width: 200px;",
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

			new FilteringSelect({
				id : "reasonType",
				value : "id",
				labelAttr : 'displayName',
				displayValueAttr : "displayName",
				searchAttr : "displayName",
				required : false,
				autoComplete : false,
				queryExpr : "*${0}*",
				store : new Memory({
					idProperty : "id",
					data : typeArr
				}),
				style : "width: 150px;"
			}, "reasonType").startup();

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
			if (c[i].id == 'reason') {
				columns.push('reason_text');
			} else {
				columns.push(c[i].id);
			}
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
					columnLockCount : 4,
					paginationBarShowRange : true,
					paginationInitialPageSize : 25,
					pageSize : 25,
					sortInitialOrder : [ {
						colId : "operation_time",
						descending : true
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
								id : "storage_name",
								field : "storage_name",
								name : "仓库",
								autoComplete : false,
								filterable : false,
								sortable : false
							},
							{
								id : "item_id",
								field : "item_id",
								name : "商品编码",
								sortable : false,
								autoComplete : false,
								disabledConditions : [ 'startWith', 'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_name",
								field : "item_name",
								name : "商品名称",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'startWith', 'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ]
							},
							{
								id : "item_dimension",
								field : "item_dimension",
								name : "库存单位",
								autoComplete : false,
								filterable : false,
								sortable : false
							},
							{
								id : "operation_time",
								field : "operation_time",
								name : "操作时间",
								autoComplete : false,
								sortable : false,
								filterable : false
							},
							{
								id : "business_date",
								field : "business_date",
								name : "单据日期",
								autoComplete : false,
								sortable : false,
								filterable : false,
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
								id : "reason",
								field : "reason",
								name : "业务类型",
								autoComplete : false,
								filterable : false,
								sortable : false,
								dataType : 'enum',
								enumOptions : enumOptionsArr,
								decorator : function(cellData, rowId, rowIndex) {
									if (isEmpty(cellData)) {
										return "";
									} else {
										return getDisplayName(cellData);
									}
								}
							},
							{
								id : "branch_name",
								field : "branch_name",
								name : "门店",
								autoComplete : false,
								filterable : false,
								sortable : false
							},
							{
								id : "form_id",
								field : "form_id",
								name : "单据编码",
								autoComplete : false,
								sortable : false,
								disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
										'notStartWith', 'notEndWith', 'isEmpty' ],
								decorator : function(cellData, rowId, rowIndex) {
									if (!cellData) {
										return '';
									}
									return getHref(cellData);
								}
							}, {
								id : "orgi_count",
								field : "orgi_count",
								name : "期初数量",
								autoComplete : false,
								dataType : 'number',
								sortable : false,
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							}, {
								id : "orgiCountMoney",
								field : "orgiCountMoney",
								name : "期初金额",
								sortable : false,
								autoComplete : false,
								filterable : false
							}, {
								id : "item_count_in",
								field : "item_count_in",
								name : "入库数量（盘盈）",
								autoComplete : false,
								dataType : 'number',
								sortable : false,
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							}, {
								id : "itemCountInMoney",
								field : "itemCountInMoney",
								name : "入库金额",
								autoComplete : false,
								sortable : false,
								filterable : false
							}, {
								id : "item_count_out",
								field : "item_count_out",
								name : "出库数量（盘亏）",
								sortable : false,
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							}, {
								id : "itemCountOutMoney",
								field : "itemCountOutMoney",
								name : "出库金额",
								autoComplete : false,
								sortable : false,
								filterable : false
							}, {
								id : "result_count",
								field : "result_count",
								sortable : false,
								name : "结存数量",
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							}, {
								id : "item_unit_price",
								field : "item_unit_price",
								name : "结存单价",
								sortable : false,
								autoComplete : false,
								dataType : 'number',
								disabledConditions : [ 'notEqual', 'isEmpty' ]
							}, {
								id : "resultCountMoney",
								field : "resultCountMoney",
								name : "结存金额",
								sortable : false,
								autoComplete : false,
								filterable : false
							} ],
					modules : [ Pagination, PaginationBar, Filter, FilterBar, NestedSort, VirtualVScroller,
							ColumnResizer, ColumnLock ]
				});

				grid.placeAt("gridContainer");
				grid.startup();
			});
}
