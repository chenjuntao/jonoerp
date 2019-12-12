dojo.ready(function() {
	initGrid();
	initTooTip();
});

function doQuery() {
	grid.set('query', getQuery());
}

var myTooltipDialog = null;

function initTooTip() {
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
	var _url = appRoot + "/cf/arrangement/manage/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dgrid/extensions/Pagination", "dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(Grid, Server, Observable, Cache, Pagination,
			Memory, ColumnSet, declare, ColumnResizer) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination, ColumnSet, ColumnResizer ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
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
		sortable : false
	}, {
		label : '单据编号',
		field : 'formId',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doArrangeScan, object);
		},
		sortable : false
	} ] ], [ [ {
		label : '采购单编号',
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
		label : '操作时间',
		field : 'formTimeActual',
		sortable : false
	}, {
		label : "生产车间",
		field : "workShop",
		sortable : false
	}, {
		label : '单据状态',
		field : 'formStatus',
		sortable : false
	}, {
		label : '采购状态',
		field : 'purchaseStatus',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
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

function doScan(row) {
	var _url = appRoot + "/centralfactory/productionDemand/summary/scanView.action?formId=" + row.formRefId;
	_url = getUrl(_url);
	
	addTab("查看采购单-" + row.formRefId, _url);
}

function doMoreScan(_formRefId) {
	var _url = appRoot + "/centralfactory/productionDemand/summary/scanView.action?formId=" + _formRefId;
	_url = getUrl(_url);
	
	addTab("查看采购单-" + _formRefId, _url);
}

function doRelScan(row) {
	require([ "dijit/popup", "dojo/query" ], function(popup, query) {
		myTooltipDialog.set("content", createContent(row, "doMoreScan"));
		popup.open({
			popup : myTooltipDialog,
			// x : event.target.getClientRects()[0].x, // x
			// y定位myTooltipDialog显示的位置
			// y : event.target.getClientRects()[0].y
			around : query(".field-formRefId", dojo.byId('"dataGrid-row-' + row.id))[0]
		});
	});

}

function doArrangeScan(row) {
	var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);
	
	addTab("查看计划单-" + row.formId, _url);
}
