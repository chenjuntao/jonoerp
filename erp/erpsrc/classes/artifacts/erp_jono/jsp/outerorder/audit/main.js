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
	var _url = appRoot + "/restaurant/goodsbill/query/doQuery.action?formType=outer&queryType=unaudit";
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/extensions/ColumnResizer", "dojo/_base/declare", "dgrid/ColumnSet",
			"dojo/domReady!" ], function(OnDemandGrid, Server, Observable, Cache, Memory, ColumnResizer, declare,
			ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(editor) {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '订货单编号',
		field : 'formId',
		sortable : false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt(object.formId, doScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmtId("审核", doAudit, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '外部订货方',
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

function doScan(row) {
	doDetailScan(row.formId);
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if (isInOperation(data.operationContent)) {
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}

	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/outerorder/audit/auditView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("审核外部订货单-" + row.formId, _url);
}