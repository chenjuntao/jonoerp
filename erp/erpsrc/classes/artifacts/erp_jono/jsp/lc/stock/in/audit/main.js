dojo.ready(function() {
	dojo.byId('condition').value = getAppendValue();
	initGrid();
});

function doQuery() {
	dojo.byId('condition').value = getAppendValue();
	grid.set('query', getQuery());
}

function getAppendValue() {
	if (dojo.byId("startDate").value.length != 0) {
		startDate = dojo.byId("startDate").value;
	} else {
		startDate = "";
	}

	if (dojo.byId("endDate").value.length != 0) {
		endDate = dojo.byId("endDate").value;
	} else {
		endDate = "";
	}

	var appendValue = " AND s.STATUS = '未审核'  AND b.BRANCH_TYPE = 'LOGISTICSCENTER'  ";

	if (!isEmpty(startDate)) {
		appendValue += "  AND TO_CHAR(h.INPUT_TIME,'YYYY-MM-DD') >= '" + startDate + "' ";
	}

	if (!isEmpty(endDate)) {
		appendValue += "  AND TO_CHAR(h.INPUT_TIME,'YYYY-MM-DD') <= '" + endDate + "' "
	}

	return appendValue;
}

function getQuery() {
	return {
		condition : dojo.byId('condition').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/putinstorage/outside/doQueryNew.action";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/extensions/Pagination", "dgrid/extensions/ColumnResizer", "dgrid/ColumnSet",
			"dojo/domReady!" ], function(Grid, Server, Observable, Cache, Memory, declare, Pagination, ColumnResizer,
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

		var CustomGrid = declare([ Grid, Pagination, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doLkScan(row) {
	doDetailScan(row.formId)
}

function doRefScan(row) {
	doDetailScan(row.formRefId)
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
		label : '入库单号',
		field : 'formId',
		sortable : false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return hrefFmt('', doLkScan, object);
			}

			return hrefFmt(object.formId, doLkScan, object);
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
		label : '供应商',
		field : 'provider',
		sortable : false
	}, {
		label : '采购单号',
		field : 'formRefId',
		sortable : false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return hrefFmt('', doRefScan, object);
			}
			return hrefFmt(object.formRefId, doRefScan, object);
		}
	}, {
		label : '入库部门',
		field : 'inputDepartment',
		sortable : false
	}, {
		label : '入库人员',
		field : 'inputer',
		sortable : false
	}, {
		label : '制单日期',
		field : 'inputTime',
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
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable : false
	}, {
		label : '单据状态',
		field : 'status',
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
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}

	if (status == '已作废') {
		alert("单据已作废！")
		return false;
	} else if (status == '已审核') {
		alert("单据已审核！")
		return false;
	}
	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/putinstorage/confirm/auditView.action?formId=" + row.formId + "&parentTabId="
			+ tabId;
	if (row.formType == 'MANUAL') {
		_url = appRoot + "/lc/stock/in/audit/mauditView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	}
	_url = getUrl(_url);

	var _title = '审核入库单-' + row.formId;
	addTab(_title, _url);
}