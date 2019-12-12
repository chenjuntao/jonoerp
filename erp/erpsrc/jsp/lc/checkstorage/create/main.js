require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();

		loadBatch();
	});
});

function addEvent() {
	dojo.byId('branchId').onchange = loadBatch;
}

function loadBatch() {
	var _url = appRoot + "/restaurant/checkstorage/imanage/queryBatch.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId('branchId').value
			}
		}).then(function(data) {
			loadSelData('checkBatchId', data);
			doQuery();
		}, function(err) {
		});
	});
}

function doQuery() {
	queryData();
}

var grid = null;
var dataStore = null;
function initGrid() {
	require([ "dgrid/OnDemandGrid" ], function(OnDemandGrid, Server,
			Observable, Cache, Memory) {
		grid = new OnDemandGrid({
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function queryData() {
	var _url = appRoot
			+ "/restaurant/checkstorage/imanage/queryCheckHeader.action?batchId="
			+ dojo.byId('checkBatchId').value;
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ],
			function(xhr, Observable, Memory) {
				xhr.get(_url, {
					handleAs : "json"
				}).then(function(data) {
					dataStore = new Observable(new Memory({
						idProperty : "formId",
						data : data
					}));
					grid.set("store", dataStore);
				}, function(err) {
				});
			});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "清单编号",
		field : "formId",
		className:'text-center',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doScan, object);
		},
		sortable:false
	}, {
		label : '操作',
		field : 'operate',
		className:'text-center',
		renderCell : function(object, data) {
			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable:false
	}, {
		label : "盘点部门",
		field : "checkBranch",
		className:'text-center',
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
		sortable:false
	}, {
		label : "清单状态",
		className:'text-center',
		field : "formStatus",
		sortable:false
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
		alert("单据正在编辑或审核中！")
		return false;
	}
	if (status == '已删除') {
		alert("单据已删除！")
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！")
		return false;
	} else if (status == '已处理') {
		alert("单据已处理！")
		return false;
	}
	return true;
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

function doSubmit() {
	var batchId = dojo.byId('checkBatchId').value;
	var _url = appRoot
			+ '/restaurant/checkstorage/lock/checkFinish.action?checkBatchId='
			+ batchId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.finished) {
				alert('当前批次已盘点结束!');
			} else {
				createBill(batchId);
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
	return true;
}

function checkInput() {
	var rows = dataStore.query();
	var notExist = true;
	require([ "dojo/_base/array" ], function(array) {
		var formId = "";
		var exist = array.some(rows, function(item, i) {
			if (item.formStatus == '未输入') {
				formId = item.formId;
				return true;
			}
		});
		if (exist) {
			notExist = false;
			alert("清单" + formId + "未输入数据!");
		}
	});
	return notExist;
}

function createBill(batchId) {
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("清单不存在!");
		return;
	}

	if (!checkInput()) {
		return;
	}

	var _url = appRoot
			+ "/restaurant/checkstorage/create/editView.action?parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	var _title = '生成盘点单-' + batchId;
	addPostTab('billForm', _title, _url);
}
