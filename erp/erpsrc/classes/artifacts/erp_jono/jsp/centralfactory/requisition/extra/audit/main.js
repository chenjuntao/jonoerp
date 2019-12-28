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
		endDate : dojo.byId('endDate').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/cf/requisition/manage/doQuery.action?queryType=unaudit&formType=extra";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare", "dojo/domReady!" ], function(OnDemandGrid,
			Server, Observable, Cache, Memory, ColumnSet, declare) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : '单据编号',
		field : 'formId'
	} ] ], [ [ {
		label : '单据状态',
		field : 'formStatus'
	}, {
		label : '对应工单号',
		field : 'formRefId'
	}, {
		label : '审核',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("审核", doConfirm, object);
		}
	}, {
		label : "生产车间",
		field : "workshop"
	}, {
		label : "制单人员",
		field : "formMaker"
	}, {
		label : "制单日期",
		field : "formTime"
	}, {
		label : "备注说明",
		field : "formNote"
	}, {
		label : "",
		field : "none"
	} ] ] ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}
	if (status == '已审核') {
		alert("单据已审核！");
		return false;
	}
	return true;
}

function doConfirm(row) {
	// if (!checkStatus(row.formId)) {
	// return;
	// }
	var _url = appRoot + "/cf/requisition/extra/audit/auditView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("审核", _url);
}