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
	var _url = appRoot + "/restaurant/allocateitem/audit/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory","dgrid/ColumnSet","dojo/_base/declare",
			"dojo/domReady!" ], function(OnDemandGrid, Server, Observable,
			Cache, Memory,ColumnSet,declare) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([OnDemandGrid, ColumnSet]);
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
	return [[[ {
		label : "序号",
		field : "rownumber"
	}, {
		label : '调拨单编号',
		field : 'formId'
	}]], [[{
		label : '审核操作',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("审核", doAudit, object);
		}
	}, {
		label : '调入部门',
		field : 'inStore'
	}, {
		label : '调出部门',
		field : 'outStore'
	}, {
		label : '制单人员',
		field : 'fromMaker'
	}, {
		label : '制单日期',
		field : 'formTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '主要调拨品',
		field : 'maxPayItem'
	}, {
		label : '调拨总额',
		field : 'allPayAmt'
	}, {
		label : '单据状态',
		field : 'formStatus'
	} ]]];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑或审核中！")
		return false;
	}
	
	if (status == '未确认') {
		alert("单据已审核！")
		return false;
	}
	if (status == '未拨付') {
		alert("单据已审核！")
		return false;
	}
	if (status == '已拨付') {
		alert("单据已审核！")
		return false;
	}
	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot
			+ "/restaurant/allocateitem/audit/auditView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("审核", _url);
}