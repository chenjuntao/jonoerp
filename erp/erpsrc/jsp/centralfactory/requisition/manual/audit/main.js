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
	var _url = appRoot + "/cf/requisition/manage/doQuery.action?queryType=unaudit&formType=manual";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare", "dojo/domReady!" ], function(OnDemandGrid,
			Server, Observable, Cache, Memory, ColumnSet, declare) {
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
		field : "rownumber",
		sortable : false
	}, {
		label : '单据编号',
		field : 'formId',
		sortable : false
	}, {
		label : '审核',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("审核", doConfirm, object);
		},
		sortable : false
	} ] ], [ [ {
		label : '单据状态',
		field : 'formStatus',
		sortable : false
	}, {
		label : "制单人员",
		field : "formMaker",
		sortable : false
	}, {
		label : "制单日期",
		field : "formTime",
		sortable : false
	}, {
		label : "备注说明",
		field : "formNote",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
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
	var _url = appRoot + "/cf/requisition/manual/audit/auditView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("审核", _url);
}