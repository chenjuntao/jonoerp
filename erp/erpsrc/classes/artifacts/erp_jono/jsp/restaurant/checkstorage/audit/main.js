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
	var _url = appRoot + "/restaurant/checkstorage/manage/doQuery.action?queryType=unaudit";
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/Grid", 
	          "custom/store/Server",
	          "dojo/store/Observable",
	          "dojo/store/Cache", 
	          "dojo/store/Memory",
	          "dojo/_base/declare",
	          "dgrid/extensions/Pagination",
	          "dgrid/extensions/ColumnResizer",
	          "dgrid/ColumnSet" ,
	          "dojo/domReady!"
          ], function(Grid, Server, Observable,	Cache, Memory,declare,Pagination,ColumnResizer,ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([Grid,Pagination,ColumnResizer,ColumnSet]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doScan(row) {
	var _url = appRoot + "/restaurant/inoutquery/checkstorage/scanView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '查看盘点单-' + row.formId;
	addTab(_title, _url);
}

function getColumn() {
	return  [
	 		[ [ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		},
		sortable:false
	}, {
		label : "单据号",
		field : "formId",
		className:'text-center',
		sortable:false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			
			return hrefFmt(object.formId, doScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			
			return hrefFmtId("审核", doAudit, object, "operate"+object.rownumber);
		},
		sortable:false
	}] ], [ [  {
		label : "盘点部门",
		field : "checkBranch",
		sortable:false
	}, {
		label : "盘点批次",
		field : "checkBatchId",
		className:'text-center',
		sortable:false
	}, {
		label : "制单人员",
		field : "formMaker",
		className:'text-center',
		sortable:false
	}, {
		label : "制单日期",
		field : "formTime",
		className:'text-center',
		sortable:false
	}, {
		label : "备注",
		field : "formNote",
		sortable:false
	}, {
		label : "主要盘点物",
		field : "maxPayItem",
		sortable:false
	}, {
		label : "盘点总额",
		field : "allPayAmt",
		className:'text-right',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		},
		sortable:false
	}, {
		label : "单据状态",
		field : "formStatus",
		className:'text-center',
		sortable:false
	} ] ] ];
}


function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已审核') {
		alert("盘点单已审核！")
		return false;
	}
	if (data.hasLock) {
		alert("单据正在编辑或处理中！")
		return false;
	}
	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/checkstorage/audit/auditView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	var _title = '审核盘点单-' + row.formId;
	_url = getUrl(_url);
	
	addTab(_title, _url);
}