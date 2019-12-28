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
	var _url = appRoot + "/lc/request/distribution/audit/doQuery.action";
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/extensions/ColumnResizer", "dojo/_base/declare", "dgrid/ColumnSet" ], function(
			OnDemandGrid, Server, Observable, Cache, Memory, ColumnResizer, declare, ColumnSet) {

		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, getQuery(), options);
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

function doFormScan(row) {
	doDetailScan(row.formId);
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : '单据编号',
		field : 'formId',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		},
		sortable : false
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmtId("审核", doAudit, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '关联单号',
		field : 'formRefId',
		renderCell : function(rowData, value) {
			return hrefFmt(value, scanRef, rowData);
		},
		sortable : false
	}, {
		label : '订货部门',
		field : 'requester',
		sortable : false
	}, {
		label : '订货地址',
		field : 'requestAddress',
		sortable : false
	}, {
		label : '配送部门',
		field : 'provider',
		sortable : false
	}, {
		label : '配送日期',
		field : 'receiveTime',
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
		label : '单据状态',
		field : 'formStatus',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function scanRef(row) {// 因为只有越库配送单进行审核，而越库配送单是根据越库采购单生成的
	var _url = appRoot + "/lc/request/purchase/manage/scanView.action?formId=" + row.formRefId;
	_url = getUrl(_url);

	addTab("查看越库采购单-" + row.formRefId, _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '未入库') {
		alert("配送单已审核！")
		return false;
	}
	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/lc/request/distribution/audit/auditView.action?formId=" + row.formId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);

	var _title = '审核越库配送单-' + row.formId;
	addTab(_title, _url);
}