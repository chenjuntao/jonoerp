var branchType;

dojo.ready(function() {
	branchType = dojo.byId('branchType').value;
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value,
		itemName : dojo.byId('itemName').value.trim()
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/putinstorage/outside/doQuery.action?queryType=unreturn&branchType=" + branchType;
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dgrid/extensions/Pagination", "dojo/store/Observable",
			"dojo/store/Cache", "dojo/store/Memory", "dgrid/extensions/ColumnResizer", "dojo/_base/declare",
			"dgrid/ColumnSet", "dojo/domReady!" ], function(Grid, Server, Pagination, Observable, Cache, Memory,
			ColumnResizer, declare, ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'rownumber',
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doInScan(row) {
	doDetailScan(row.formId)
}

function doRefScan(row) {
	doDetailScan(row.formRefId)
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '入库单号',
		field : 'formId',
		sortable : false,
		className : 'text-center',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doInScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		className : 'text-center',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmtId("退货", doEdit, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '供应商',
		field : 'provider',
		sortable : false
	}, {
		label : '采购单/出货单',
		field : 'formRefId',
		className : 'text-center',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formRefId, doRefScan, object);
		}
	}, {
		label : '入库部门',
		field : 'inputDepartment',
		sortable : false
	}, {
		label : '仓库',
		field : 'storage',
		sortable : false
	}, {
		label : '入库人员',
		field : 'inputer',
		className : 'text-center',
		sortable : false
	}, {
		label : '入库日期',
		field : 'inputTime',
		className : 'text-center',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '主要入库品',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '入库总额',
		field : 'allPayAmt',
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function doEdit(row) {
	var _url = appRoot + "/restaurant/preject/create/editView.action?formId=" + row.formId + "&parentTabId=" + tabId
			+ "&branchType=" + branchType;
	_url = getUrl(_url);

	var _title = '入库单退货-' + row.formId;
	addTab(_title, _url);
}