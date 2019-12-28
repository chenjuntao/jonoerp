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
	var _url = appRoot + "/restaurant/inoutquery/shipping/doQuery.action?formType=OUTER&queryType=uninput";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/ColumnSet", "dojo/_base/declare", "dgrid/extensions/Pagination", "dojo/domReady!" ], function(Grid,
			Server, Observable, Cache, Memory, ColumnSet, declare, Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : "rownumber",
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

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '单据编号',
		field : 'formId',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt(object.formId, doScan, object);// hrefFmt(_text,
			// _handler,
			// _data)
		}
	} ] ], [ [ {
		label : '操作',
		field : 'confirm',
		renderCell : function(item, data) {
			if (item.rownumber == "合计") {
				return hrefFmt("", doConfirm, item);
			}

			if (item.formStatus == "外部订货方已确认") {
				return hrefFmtId("", doConfirm, item, "confirm" + item.rownumber);
			}

			// return hrefFmt("确认", doConfirm, item);
			return hrefFmtId("入库", doEdit, item, "confirm" + item.rownumber);
		}
	}, {
		label : '确认状态',
		field : 'formStatus',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			if (field != '已入库') {
				return "未确认";
			} else {
				return "已确认";
			}

		}
	}, {
		label : '出货部门',
		field : 'provider'
	}, {
		label : '出货日期',
		field : 'receiveTime'
	}, {
		label : '订货部门',
		field : 'requester'
	}, {
		label : '订货地址',
		field : 'requestAddress'
	}, {
		label : '制单人员',
		field : 'formMaker'
	}, {
		label : '制单日期',
		field : 'formTime'
	}, {
		label : '审核人员',
		field : 'auditor'
	}, {
		label : '审核日期',
		field : 'auditTime'
	}, {
		label : '备注',
		field : 'formNote'
	}, {
		label : '金额最大的商品',
		field : 'maxPayItem'
	} ] ] ];
}

function doScan(row) {
	var _url = appRoot + "/lc/request/delivery/manage/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);

	addTab("查看", _url);
}

function doConfirm(row) {
	var _url = appRoot + '/outer/outscan/confirmView.action?formId=' + row.formId + '&parentTabId=' + tabId;
	_url = getUrl(_url);

	var _title = '出货单确认' + row.formId;
	addTab(_title, _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已入库') {
		alert("出货单已入库！")
		return false;
	}
	return true;
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/putinstorage/distribution/editView.action?formId=" + row.formId + "&parentTabId="
			+ tabId;
	_url = getUrl(_url);

	var _title = '出货单入库-' + row.formId;
	addTab(_title, _url);
}
