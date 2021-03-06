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
	}else{
		startDate = '';
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	}else{
		endDate = '';
	}

	var requestCondition = dijit.byId('requestCondition').get('value');

	var branchFlag = dojo.byId('branchFlag').value;
	var appendValue = " AND h.FORM_TYPE in ('INNER_UNIFIED','INNER_CROSS')  AND h.REQUESTER_ID = '" + loginBranchId
			+ "' ";
	if (!isEmpty(branchFlag)) {
		appendValue = " AND h.FORM_TYPE in ('INNER_UNIFIED','INNER_CROSS') ";
	}

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(h.audit_time,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(h.audit_time,'YYYY-MM-DD') <= '" + endDate + "' "
	}


	if (!isEmpty(requestCondition)) {
		appendValue += " AND h.REQUESTER_ID = '" + requestCondition + "' "
	}

	return appendValue;
}

function initCondition() {
	dojo.byId('inputJsonString').value = "";
	dijit.byId('requestCondition').set('value', "");

	dojo.byId('append').value = getAppendValue();
}

var providerArr = [];
var requesterArr = [];

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

		var branchFlag = dom.byId('branchFlag').value;

		xhr.post(appRoot + "/common/listBs.action", {
			handleAs : "json",
			data : {
				branchType1 : branchType,
				branchType2 : branchType,
				branchFlag : branchFlag
			}
		}).then(function(data) {
			array.forEach(data.branch, function(item) {
				if (item.branchType == "LOGISTICSCENTER") {
					providerArr.push(item);
				}

				if (item.branchType == "RESTAURANT") {
					requesterArr.push(item);
				}
			});

			new FilteringSelect({
				id : "requestCondition",
				value : "code",
				labelAttr : 'name',
				displayValueAttr : "name",
				searchAttr : "name",
				required : false,
				autoComplete : false,
				queryExpr : "*${0}*",
				store : new Memory({
					idProperty : "code",
					data : requesterArr
				}),
				style : "width: 150px;"
			}, "requestCondition").startup();

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
					},
					{
						id : "packaging_factor",
						field : "packaging_factor",
						name : "包装因子",
						autoComplete : false,
						dataType : 'number',
						disabledConditions : [ 'notEqual', 'isEmpty' ]
					},
					{
						id : "packaging_unit",
						field : "packaging_unit",
						name : "包装单位",
						autoComplete : false,
						sortable : false,
						disabledConditions : [ 'equal', 'startWith', 'endWith', 'notEqual', 'notContain',
								'notStartWith', 'notEndWith', 'isEmpty' ]
					}, {
						id : "packaging_count",
						field : "packaging_count",
						name : "包装数量",
						autoComplete : false,
						sortable : false,
						filterable : false
					}, {
						id : "request_count",
						field : "request_count",
						name : "订货数",
						autoComplete : false,
						sortable : false,
						filterable : false
					}, {
						id : "shipping_count",
						field : "shipping_count",
						name : "配送数",
						autoComplete : false,
						sortable : false,
						filterable : false
					}, {
						id : "delivery_count",
						field : "delivery_count",
						name : "实发数",
						autoComplete : false,
						sortable : false,
						filterable : false
					}, {
						id : "receive_count",
						field : "receive_count",
						name : "实收数",
						autoComplete : false,
						sortable : false,
						filterable : false
					}, {
						id : "different_count",
						field : "different_count",
						name : "差异数",
						autoComplete : false,
						sortable : false,
						filterable : false
					}, {
						id : "pay_amt",
						field : "pay_amt",
						name : "标准金额",
						autoComplete : false,
						sortable : false,
						filterable : false
					} ],
			modules : [ Pagination, PaginationBar, Filter, FilterBar, NestedSort, VirtualVScroller, ColumnResizer,
					ColumnLock ]
		});

		grid.placeAt("gridContainer");
		grid.startup();
	});
}
