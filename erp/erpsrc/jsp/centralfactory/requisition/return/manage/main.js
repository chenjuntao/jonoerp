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
	var _url = appRoot + "/cf/requisition/manage/doQuery.action?formType=return";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache","dgrid/extensions/Pagination",
			"dojo/store/Memory", "dgrid/ColumnSet", "dojo/_base/declare", "dojo/domReady!" ], function(Grid,
			Server, Observable, Cache,Pagination, Memory, ColumnSet, declare) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty:'rownumber',
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid,Pagination, ColumnSet ]);
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

function doMyScan(row) {
	doDetailScan(row.formRefId)
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : '单据编号',
		field : 'formId',
		sortable:false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable:false
	} ] ], [ [ {
		label : '单据状态',
		field : 'formStatus',
		sortable:false
	}, {
		label : '工单编号',
		field : 'formRefId',
		sortable:false,
		renderCell : function(object, data) {
			return hrefFmt(object.formRefId, doMyScan, object);
		}
	}, {
		label : "生产车间",
		field : "workshop",
		sortable:false
	}, {
		label : "制单人员",
		field : "formMaker",
		sortable:false
	}, {
		label : "制单日期",
		field : "formTime",
		sortable:false
	}, {
		label : "操作时间",
		field : "formTimeActual",
		sortable:false
	}, {
		label : "备注说明",
		field : "formNote",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
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
	if (status == '已作废') {
		alert("单据已作废！")
		return false;
	} 
	return true;
}
function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot
			+ "/cf/requisition/return/manage/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	var _title = '管理退料单-' + row.formId;
	addTab(_title, _url);
}
function doScan(row) {
	var _url = appRoot + "/cf/requisition/return/manage/scanView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
		
	addTab("查看退料单-"+row.formId , _url);
}