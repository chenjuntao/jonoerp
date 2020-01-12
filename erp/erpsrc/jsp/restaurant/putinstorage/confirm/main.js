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
	var _url = appRoot + "/restaurant/putinstorage/outside/doQuery.action?branchType=RESTAURANT&queryType=unaudit";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory","dgrid/extensions/ColumnResizer",
			"dojo/_base/declare","dgrid/ColumnSet" ,"dojo/domReady!" ]
			, function(OnDemandGrid, Server, Observable,Cache, Memory,ColumnResizer,declare,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty:'rownumber',
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid ,ColumnResizer,ColumnSet]);
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
	return [
			[ [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '入库单号',
		field : 'formId',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		},
		sortable:false
	},{
		label : '操作',
		field : 'audit',
		renderCell : function(object, data) {
			return hrefFmtId("审核", doAudit, object, "operate"+object.rownumber);
		},
		sortable:false
	}] ], [ [ {
		label : '供应商',
		field : 'provider',
		sortable:false
	}, {
		label : '采购单号',
		field : 'formRefId',
		renderCell : function(object, data) {
			return hrefFmt(object.formRefId, doRefFormScan, object);
		},
		sortable:false
	}, {
		label : '入库部门',
		field : 'inputDepartment',
		sortable:false
	}, {
		label : '入库人员',
		field : 'inputer',
		sortable:false
	}, {
		label : '入库日期',
		field : 'inputTime',
		sortable:false
	}, {
		label : '备注',
		field : 'formNote',
		sortable:false
	}, {
		label : '主要入库品',
		field : 'maxPayItem',
		sortable:false
	}, {
		label : '入库总额',
		field : 'allPayAmt',
		sortable:false
	}, {
		label : '单据状态',
		field : 'status',
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	}] ] ];
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if(isInOperation(data.operationContent)){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}
	
	return true;
}


function doFormScan(row) {
	var data = getCurrentVersion(row.formId);
	if(data.operationContent == "已删除"){
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return;
	}
	
	doDetailScan(row.formId);
}

//前置单据已审核，不可能被删除
function doRefFormScan(row) {
	doDetailScan(row.formRefId);
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot
			+ "/restaurant/putinstorage/confirm/auditView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '审核入库单-' + row.formId;
	addTab(_title, _url);
}