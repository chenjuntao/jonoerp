dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot
			+ "/restaurant/putinstorage/distribution/doQuery.action?branchType=LOGISTICSCENTER&isAuditTime=true";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/extensions/ColumnResizer", "dojo/_base/declare", "dgrid/ColumnSet", "dgrid/extensions/Pagination",
			"dojo/domReady!" ], function(Grid, Server, Observable, Cache, Memory, ColumnResizer, declare, ColumnSet,
			Pagination) {
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

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '单据编号',
		field : 'formId',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}

	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmtId("半成品入库", doEdit, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '订货部门',
		field : 'requester',
		sortable : false
	}, {
		label : '配送部门',
		field : 'provider',
		sortable : false
	}, {
		label : '审核日期',
		field : 'auditTime',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '主要配送品',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '配送总额',
		field : 'allPayAmt',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function doFormScan(row) {
	var _url = appRoot + "/cf/stock/out/query/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);

	addTab("查看", _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已入库') {
		alert("配送单已入库！")
		return false;
	}
	return true;
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/lc/stock/in/distribution/inputView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	var _title = '半成品入库-' + row.formId;
	addTab(_title, _url);
}