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
		endDate : dojo.byId('endDate').value,
		branchId : dojo.byId('branchId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/reportdamage/confirmloss/doQuery.action?branchType="
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dgrid/extensions/ColumnResizer", "dojo/_base/declare", "dojo/domReady!" ], function(
			OnDemandGrid, Server, Observable, Cache, Memory, ColumnResizer, declare) {
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

		var CustomGrid = declare([ OnDemandGrid, ColumnResizer ]);

		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, "listLossGrid");

		grid.startup();
	});
}

function refreshStorage() {
	var _url = appRoot + '/restaurant/reportdamage/queryloss/refreshStorage.action?branchType='
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId('branchId').value
			}
		}).then(function(data) {
			if (data.msg) {
				var storageSelector = dojo.byId('storageId');
				storageSelector.length = 0;
				storageSelector.options.add(new Option("--请选择--", ""));
				for (var i = 0, length = data.msg.length; i < length; i++) {
					var item = data.msg[i];
					storageSelector.options.add(new Option(item.storageName, item.storageId));
				}
			} else {
			}
		}, function(err) {
		});
	});
}

function doShow(row) {
	var data = getCurrentVersion(row.formId);
	if (data.operationContent == "已删除") {
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return;
	}

	doDetailScan(row.formId);
}

function getColumn(editor) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "单据编号",
		field : "formId",
		className : 'text-center',
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doShow, object);
		},
		sortable : false
	}, {
		label : "操作",
		field : "operate",
		className : 'text-center',
		renderCell : function(object) {
			return hrefFmtId("审核", doAudit, object, "operate" + object.rownumber);
		},
		sortable : false
	}, {
		label : "报损部门",
		field : "lossBranch",
		sortable : false
	}, {
		label : "报损人",
		field : "lossMan",
		className : 'text-center',
		sortable : false
	}, {
		label : "报损日期",
		field : "lossTime",
		className : 'text-center',
		sortable : false
	}, {
		label : "备注",
		field : "formNote",
		sortable : false
	}, {
		label : "主要报损品",
		field : "maxPayItem",
		sortable : false
	}, {
		label : "报损金额",
		field : "allPayAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : '单据状态',
		field : 'formStatus',
		className : 'text-center',
		sortable : false
	}, {
		label : '',
		field : 'none',
		sortable : false
	} ];
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if (isInOperation(data.operationContent)) {
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}

	return true;
}

function doAudit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}

	var _url = appRoot + "/restaurant/reportdamage/confirmloss/auditView.action?formId=" + row.formId + "&parentTabId="
			+ tabId + "&branchType=" + dojo.byId('branchType').value;
	_url = getUrl(_url);

	addTab("审核原料报损单-" + row.formId, _url);
}
