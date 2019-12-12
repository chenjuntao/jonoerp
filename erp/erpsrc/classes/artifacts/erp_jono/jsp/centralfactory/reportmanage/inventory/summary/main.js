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
	var startDate='', endDate='';
	if (!isEmpty(dojo.byId("startDate").value)) {
		startDate = dojo.byId("startDate").value;
	}

	if (!isEmpty(dojo.byId("endDate").value)) {
		endDate = dojo.byId("endDate").value;
	}

	var storageCondition = dijit.byId('storageCondition').get('value');

	//-----------------------------------------------------------------------------
	var appendValue0 = "   AND io.BRANCH_ID = '" + loginBranchId + "' ";

	if (!isEmpty(startDate)) {
		appendValue0 += "  AND TO_CHAR(io.BUSINESS_DATE,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue0 += "  AND TO_CHAR(io.BUSINESS_DATE,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	if (!isEmpty(storageCondition)) {
		appendValue0 += " AND io.STORAGE_ID = '" + storageCondition + "' "
	}

	//---------------------------------------------------------------------
	
	var appendValue1 = " AND io.BRANCH_ID = '" + loginBranchId + "' ";

	if (!isEmpty(startDate)) {
		appendValue1 += "  AND TO_CHAR(io.BUSINESS_DATE,'YYYY-MM-DD') <= '" + getPreDayTime(startDate) + "' ";
	}

	if (!isEmpty(storageCondition)) {
		appendValue1 += " AND io.STORAGE_ID = '" + storageCondition + "' "
	}
	
	//--------------------------------------------------------------------
	var appendValue2 = " AND io.BRANCH_ID = '" + loginBranchId + "' ";

	if (!isEmpty(storageCondition)) {
		appendValue2 += " AND io.STORAGE_ID = '" + storageCondition + "' "
	}
	
	if (!isEmpty(endDate)) {
		appendValue2 += "  AND TO_CHAR(io.BUSINESS_DATE,'YYYY-MM-DD') <= '" + endDate + "' ";
	}
	
	//--------------------------------------------------------------------------------

	var appendValue = appendValue0 + "|_|" + appendValue1 + "|_| "+appendValue2+ "|_| ";

	return appendValue;
}

function initCondition() {
	dojo.byId('inputJsonString').value = "";
	dijit.byId('branchCondition').set('value', "");
	dijit.byId('storageCondition').set('value', "");

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
	          "dojo/date", 
	          "dojo/date/locale",
	          "dijit/form/Button",
	          "dijit/Dialog", 
	          "dojo/domReady!"
          ], function(dom, xhr, array, domForm, parser, Select, ObjectStore, Memory,FilteringSelect,date,locale) {
		
		window.getPreDayTime = function (_startDate) {
			var parseOption = {
				datePattern : "yyyy-MM-dd",
				selector : "date"
			};
			
			var startDate = locale.parse(_startDate, parseOption);
			var preDateTime = date.add(startDate, "day", -1);
			return locale.format( preDateTime, parseOption);
		}
		
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
									}, {
										id : "item_dimension",
										field : "item_dimension",
										name : "库存单位",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "branch_name",
										field : "branch_name",
										name : "门店",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "orgiCount",
										field : "orgiCount",
										name : "期初数量",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "orgicountmoney",
										field : "orgicountmoney",
										name : "期初金额",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "itemcountin",
										field : "itemcountin",
										name : "入库数量合计",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "itemcountinmoney",
										field : "itemcountinmoney",
										name : "入库金额合计",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "itemcountout",
										field : "itemcountout",
										name : "出库数量合计",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "itemcountoutmoney",
										field : "itemcountoutmoney",
										name : "出库金额合计",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "resultcount",
										field : "resultcount",
										name : "结存数量",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "resultcountmoney",
										field : "resultcountmoney",
										name : "结存金额",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "rawloss",
										field : "rawloss",
										name : "原料报损(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "dishloss",
										field : "dishloss",
										name : "出品报损(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "checkstorageout",
										field : "checkstorageout",
										name : "盘亏数(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "checkstoragein",
										field : "checkstoragein",
										name : "盘盈数(入)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "publicstorageout",
										field : "publicstorageout",
										name : "产品出库(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "publicstoragein",
										field : "publicstoragein",
										name : "产品入库(入)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "preject2",
										field : "preject2",
										name : "采购退货(入)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "preject",
										field : "preject",
										name : "采购退货(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "putinstorage",
										field : "putinstorage",
										name : "采购入库(入)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "allocateitemout",
										field : "allocateitemout",
										name : "调拨出库(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "allocateitemin",
										field : "allocateitemin",
										name : "调拨入库(入)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "scll",
										field : "scll",
										name : "生产领料(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}
									, {
										id : "sccl",
										field : "sccl",
										name : "生产超领(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "sctl",
										field : "sctl",
										name : "生产退料(入)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}, {
										id : "fgll",
										field : "fgll",
										name : "非工单领料(出)",
										autoComplete : false,
										filterable : false,
										sortable : false
									}],
					modules : [ Pagination, PaginationBar, Filter, FilterBar, NestedSort, VirtualVScroller,
							ColumnResizer, ColumnLock ]
				});

				grid.placeAt("gridContainer");
				grid.startup();
			});
}
