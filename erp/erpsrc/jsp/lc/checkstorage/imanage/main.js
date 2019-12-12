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
	var _url = appRoot
			+ "/restaurant/checkstorage/manage/doQuery.action?formType=list";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable",
			"dojo/store/Cache", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/extensions/Pagination", "dojo/domReady!" ], function(Grid,
			Server, Observable, Cache, Memory, declare, Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : "清单编号",
		field : "formId"
	}, {
		label : "盘点部门",
		field : "checkBranch"
	}, {
		label : "盘点批次",
		field : "checkBatchId"
	}, {
		label : "制单人员",
		field : "formMaker"
	}, {
		label : "制单日期",
		field : "formTime"
	}, {
		label : "备注",
		field : "formNote"
	}, {
		label : "主要盘点物",
		field : "maxPayItem"
	}, {
		label : "盘点总额",
		field : "allPayAmt",
		className : 'grid-number',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	// }, {
	// label : "批次状态",
	// field : "batchStatus"
	}, {
		label : "清单状态",
		field : "formStatus"
	}, {
		label : '查看',
		field : 'scan',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt("查看", doScan, object);
		}
	}, {
		label : '修改/删除/作废',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt("修改/删除/作废", checkFinish, object);
		}
	}, {
		label : "",
		field : "none"
	} ];
}

function doScan(row) {
	var data = getCurrentStatus(row.formId);
	if (data.formStatus == '已删除') {
		alert("单据已删除！")
		return false;
	}
	var _url = appRoot
			+ "/restaurant/checkstorage/imanage/scanView.action?formId="
			+ row.formId;
	_url = getUrl(_url);
	
	var _title = '盘点清单查看' + row.formId;
	addTab(_title, _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var hasLock = data.hasLock;
	var status = data.formStatus;
	if (hasLock) {
		alert("单据正在编辑中！")
		return false;
	}
	if (status == '已删除') {
		alert("单据已删除！")
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！")
		return false;
	}
	return true;
}

function checkFinish(row) {
	var _url = appRoot
			+ '/restaurant/checkstorage/lock/checkFinish.action?checkBatchId='
			+ row.checkBatchId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.finished) {
				alert('当前批次已盘点结束!');
			} else {
				doEdit(row);
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot
			+ "/restaurant/checkstorage/imanage/editView.action?formId="
			+ row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '盘点清单修改' + row.formId;
	addTab(_title, _url);
}