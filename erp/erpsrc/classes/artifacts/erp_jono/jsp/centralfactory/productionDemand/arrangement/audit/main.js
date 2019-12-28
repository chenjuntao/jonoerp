dojo.ready(function() {
	initGrid();
	initTooTip();
});

function doQuery() {
	grid.set('query', getQuery());
}

var myTooltipDialog = null;

function initTooTip() {
	// displays a tooltip that contains form elements(like a dialog)
	require([ "dijit/TooltipDialog" ], function(TooltipDialog) {
		myTooltipDialog = new TooltipDialog({
			id : 'myTooltipDialog',
			style : "color: red;width:100;height:auto;",
			onMouseLeave : function() {
				dijit.popup.close(myTooltipDialog);
			}
		});
	});
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/doQuery.action";
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare", "dgrid/extensions/ColumnResizer",
			"dojo/domReady!" ], function(OnDemandGrid, Server, Observable, Cache, Memory, ColumnSet, declare,
			ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid, ColumnSet, ColumnResizer ]);
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
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doArrangeScan, object);
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
		label : '采购单编码',
		field : 'formRefId',
		renderCell : function(object, data) {
			if (object.formRefId.split(",").length == 1) {
				return hrefFmt(object.formRefId.split(",")[0], doScan, object);
			} else {
				return hrefFmt(object.formRefId.split(",")[0] + "更多", doRelScan, object);
			}
		},
		sortable : false
	}, {
		label : '制单人员',
		field : 'formMaker',
		sortable : false
	}, {
		label : '制单日期',
		field : 'formTime',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '单据状态',
		field : 'formStatus',
		sortable : false
	}, {
		label : '',
		field : 'none',
		sortable : false
	} ] ] ];
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if (isInOperation(data.operationContent)) {
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}

	return true;
}

function doScan(row) {
	var _url = appRoot + "/centralfactory/productionDemand/summary/scanView.action?formId=" + row.formRefId;
	_url = getUrl(_url);

	addTab("查看采购单-" + row.formRefId, _url);
}

function doMoreScan(_formRefId) {
	var _url = appRoot + "/centralfactory/productionDemand/summary/scanView.action?formId=" + _formRefId;
	_url = getUrl(_url);

	addTab(_formRefId + "查看", _url);
}

function doRelScan(row) {
	require([ "dijit/popup", "dojo/query" ], function(popup, query) {
		myTooltipDialog.set("content", createContent(row, "doMoreScan"));
		popup.open({
			popup : myTooltipDialog,
			around : query(".field-formRefId", dojo.byId('"dataGrid-row-' + row.id))[0]
		});
	});

}

function doArrangeScan(row) {
	var data = getCurrentVersion(row.formId);
	if (data.operationContent == "已删除") {
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return;
	}

	var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);

	addTab("查看计划单-" + row.formId, _url);
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}

	var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/auditView.action?formId=" + row.formId
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);

	addTab("审核计划单-" + row.formId, _url);
}