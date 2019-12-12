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
	var _url = appRoot + "/restaurant/goodsbill/query/doQuery.action?formType=outer&queryType=handled";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/extensions/Pagination", "dgrid/ColumnSet" ], function(Grid, Server,
			Observable, Cache, Memory, declare, Pagination, ColumnSet) {
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

		var CustomGrid = declare([ Grid, Pagination, ColumnSet ]);

		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			rowsPerPage : 30,
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
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		},
		sortable : false
	}, {
		label : '订货单编号',
		field : 'formId',
		sortable : false
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmtId("生成出货单", doCreate, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '订货部门',
		field : 'buyerName',
		sortable : false
	}, {
		label : '制单人',
		field : 'formMaker',
		sortable : false
	}, {
		label : '制单日期',
		field : 'formTime',
		sortable : false
	}, {
		label : '到货日期',
		field : 'receiveTime',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '主要订货',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '订货总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable : false
	}, {
		label : '单据状态',
		field : 'formStatus',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function checkStatus(_formId) {
	var hasLock = false;
	var status = '';
	var _url = appRoot + "/restaurant/goodsbill/query/getCurrentStatus.action?formId=" + _formId;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			hasLock = data.hasLock;
			status = data.formStatus;
		}, function(err) {
		});
	});
	return true;
}

function doCreate(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/lc/request/delivery/createView.action?formRefId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("生成出货单-" + row.formId, _url);
}