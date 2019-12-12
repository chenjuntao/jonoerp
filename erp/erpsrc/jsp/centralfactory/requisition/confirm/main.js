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
	var _url = appRoot + "/cf/requisition/manage/doQuery.action?queryType=unreceive&formType=produce";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/ColumnSet", "dojo/_base/declare", "dgrid/extensions/Pagination", "dojo/domReady!" ], function(Grid,
			Server, Observable, Cache, Memory, ColumnSet, declare, Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, ColumnSet, Pagination ]);
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

function doScan(row) {
	doDetailScan(row.formRefId)
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
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmtId("审核", checkFinish, object, "operate" + object.rownumber);
		},
		sortable : false
	} ] ], [ [ {
		label : '工单编号',
		field : 'formRefId',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formRefId, doScan, object);
		}
	}, {
		label : "生产车间",
		field : "workshop",
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
		label : '单据状态',
		field : 'formStatus',
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

function checkFinish(row) {
	var _url = appRoot + "/cf/stock/in/confirm/checkFinish.action?queryType=unconfirm&formRefId=" + row.formRefId;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (!data.finished) {
				alert('还存在未审核或未处理的退料单或超领单，不能生产领料单审核!');
			} else {
				doConfirm(row);
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function doConfirm(row) {
	if (!checkStatus(row.formId)) {
		return;
	}

	var _url = appRoot + "/cf/requisition/confirm/confirmView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	addTab("审核生产领料单-" + row.formId, _url);
}