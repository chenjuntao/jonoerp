require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
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
	var _url = appRoot + "/restaurant/production/querySelf/doQuery.action?branchType=" + dojo.byId('branchType').value
			+ "&statusType=unaudit";
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/extensions/ColumnResizer", "dojo/_base/declare", "dgrid/ColumnSet",
			"dojo/domReady!" ], function(OnDemandGrid, Server, Observable, Cache, Memory, ColumnResizer, declare,
			ColumnSet) {
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

		var CustomGrid = declare([ OnDemandGrid, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, "listSelfGrid");

		grid.startup();
	});
}

function getColumn(editor) {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "单据编号",
		field : "formId",
		sortable : false,
		className : 'text-center',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	}, {
		label : "操作",
		field : "operate",
		className : 'text-center',
		renderCell : function(object) {
			return hrefFmtId("审核", doAudit, object, "operate"+object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : "入库部门",
		field : "branch",
		sortable : false
	}, {
		label : "入库人员",
		field : "formMaker",
		className : 'text-center',
		sortable : false
	}, {
		label : "入库日期",
		field : "formTime",
		className : 'text-center',
		sortable : false
	}, {
		label : "备注",
		field : "formNote",
		sortable : false
	}, {
		label : "主要入库品",
		field : "mainItem",
		sortable : false
	}, {
		label : '单据状态',
		field : 'formStatus',
		className : 'text-center',
		sortable : false
	}, {
		label : '',
		field : 'none',
		sortable : false
	} ] ] ];
}

function doFormScan(row) {
	var data = getCurrentVersion(row.formId);
	if (data.operationContent == "已删除") {
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return;
	}

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

	var _url = appRoot + "/restaurant/production/confirmSelf/auditView.action?formId=" + row.formId + "&parentTabId="
			+ tabId + "&branchType=" + dojo.byId('branchType').value;
	_url = getUrl(_url);

	addTab("审核半成品入库单-" + row.formId, _url);
}