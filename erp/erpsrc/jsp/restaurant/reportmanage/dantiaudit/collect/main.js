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
	} else {
		startDate = '';
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	} else {
		endDate = '';
	}

	var branchCondition2 = dijit.byId('branchCondition2').get('value');
	var storageCondition2 = dijit.byId('storageCondition2').get('value');

	var branchFlag = dojo.byId('branchFlag').value;
	var appendValue = "  AND ph.REQUESTER_ID = '" + loginBranchId + "' ";
	if (!isEmpty(branchFlag)) {
		appendValue = "";
	}

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(h.antiaudit_time,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(h.antiaudit_time,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	if (!isEmpty(branchCondition2)) {
		appendValue += " AND ph.REQUESTER_ID = '" + branchCondition2 + "' "
	}

	if (!isEmpty(storageCondition2)) {
		appendValue += " AND ph.IN_STORAGE_ID = '" + storageCondition2 + "' "
	}

	return appendValue;
}

function initCondition() {
	dojo.byId('inputJsonString').value = "";
	dijit.byId('branchCondition2').set('value', "");
	dijit.byId('storageCondition2').set('value', "");

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
				id : "storageCondition2",
				store : new Memory({
					idProperty : "storageId",
					data : storageArr2
				}),
				required : false,
				value : "storageId",
				autoComplete : false,
				query : {
					branchId : /.*/
				},
				style : "width: 200px;",
				labelAttr : 'storageName',
				queryExpr : "*${0}*",
				displayValueAttr : "storageName",
				searchAttr : "storageName"
			}, "storageCondition2").startup();

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
				style : "width: 150px;",
				onChange : function(current) {
					if (isEmpty(current)) {
						dijit.byId('storageCondition2').query.branchId = /.*/;
					} else {
						dijit.byId('storageCondition2').query.branchId = this.item.code || /.*/;
					}
					dijit.byId('storageCondition2').set('value', "");
				}
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
				colId : "antiaudit_branch_id",
				descending : false
			}, {
				colId : "item_id",
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
						id : "antiaudit_branch_id",
						field : "antiaudit_branch_id",
						name : "反审核部门编码",
						autoComplete : false,
						filterable : false
					},
					{
						id : "antiaudit_branch",
						field : "antiaudit_branch",
						name : "反审核部门",
						autoComplete : false,
						sortable : false,
						filterable : false
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
						id : "in_storage",
						field : "in_storage",
						name : "入库仓库",
						autoComplete : false,
						sortable : false,
						filterable : false
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
					}, {
						id : "antiaudit_receive_count",
						field : "antiaudit_receive_count",
						name : "反审核实收数",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					}, {
						id : "antiaudit_pay_amt",
						field : "antiaudit_pay_amt",
						name : "反审核金额",
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
