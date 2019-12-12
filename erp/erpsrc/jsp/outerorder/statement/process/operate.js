dojo.ready(function() {
	initGrid();

	dojo.byId("operateButton").value = getOperateCn(operate);
});

var grid = null;

function getOperateCn(operate) {
	if (operate == 'pay') {
		return '付款';
	}

	if (operate == 'confirm') {
		return '收款确认';
	}

	return '对账确认';
}

function doQuery() {
	location.reload();// 刷新页面
}

function close() {
	closeTab(tabId, "doQuery");
}

function initGrid() {
	var _url = appRoot + "/hq/acscan/queryInBySForm.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dojo/_base/declare", "dojo/domReady!" ], function(OnDemandGrid, Server, Observable,
			Cache, Memory, declare) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ OnDemandGrid ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
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
		label : '单号',
		field : 'subFormId',
		renderCell : function(item, data) {
			if (item.rownumber == "合计") {
				return hrefFmt("", doScan, item);
			}

			return hrefFmt(item.subFormId, doScan, item);
		}

	}, {
		label : '关联单号',
		field : 'formRefId',
		renderCell : function(item, data) {
			if (item.rownumber == "合计") {
				return hrefFmt("", doRelInScan, item);
			}

			return hrefFmt(item.formRefId, doRelInScan, item);
		}
	}, {
		label : '操作',
		field : 'confirm',
		renderCell : function(item, data) {
			if (item.rownumber == "合计") {
				return hrefFmt("", doConfirm, item);
			}

			if (item.status == '外部订货方已确认') {
				return hrefFmt("", doConfirm, item);
			}

			return hrefFmt("确认", doConfirm, item);
		}
	}, {
		label : '操作人员',
		field : 'formOperate'
	}, {
		label : '操作日期',
		field : 'formOperateTime'
	}, {
		label : '入库总额',
		field : 'allPayAmt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	}, {
		label : '确认状态',
		field : 'status',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}

			if (field == '外部订货方已确认') {
				return "已确认";
			} else {
				return "未确认";
			}
		}
	}, {
		label : '备注',
		field : 'formNote'
	} ];
}

function getStatus() {
	if (status == 'out_confirmed') {
		return "已付款";
	}

	if (status == 'payed') {
		return "对账已完结";
	}

	return "外部订货方已确认";
}

function getSuccessMsg() {
	if (status == 'out_confirmed') {
		return "付款成功！";
	}

	if (status == 'payed') {
		return "收款确认成功！";
	}

	return "外部订货方确认成功!";
}

function getErrorMsg() {
	if (status == 'out_confirmed') {
		return "付款失败！";
	}

	if (status == 'payed') {
		return "收款失败！";
	}

	return "外部订货方确认失败!";
}

function doScan(row) {
	doDetailScan(row.subFormId);
}

function doRelInScan(row) {
	doDetailScan(row.formRefId);
}

function doOperate() {
	var idsArray = [];
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(grid.store.data, function(item, i) {
			if (item.status != '外部订货方已确认') {
				idsArray.push(item.subFormId);
			}
		});
	});

	if (!confirm("所有单据都将确认？")) {
		return;
	}
	var _url = appRoot + "/hq/acscan/doConfirm.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				formId : formId,
				status : getStatus(),
				subFormIds : idsArray.join(","),
				formType : formType
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert(getSuccessMsg());
				closeTab(tabId, "doQuery");
			} else {
				alert(getErrorMsg());
			}
		}, function(err) {
		});
	});

}
function doConfirm(row) {
	var idsArray = [];
	var status = '';
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(grid.store.data, function(item, i) {
			if (item.status != '外部订货方已确认' && row.subFormId != item.subFormId) {
				idsArray.push(item.subFormId);
			}
		});
	});
	if (idsArray.length == 0) {
		alert("对账单明细将全部确认！");
		status = 'wbSure';
	}

	var subFormId = row.subFormId.toUpperCase();

	var _url = appRoot + '/outer/orderstatement/process/checkView.action?formId=' + row.subFormId + '&status=' + status
			+ '&parentFormId=' + formId + '&parentTabId=' + tabId;
	
	var _title = '出货单确认' + row.subFormId;

	if (subFormId.indexOf("PTH") != -1) {
		_url = appRoot + "/sp/return/scan/outConfirm.action?formId=" + row.subFormId + '&status=' + status
				+ '&parentFormId=' + formId + "&parentTabId=" + tabId + "&form_ref_id=" + row.form_ref_id;
		_title = '退货单确认' + row.subFormId;
	}

	if (subFormId.indexOf("PS") != -1) {
		_title = '配送单确认' + row.subFormId;
	}
	_url = getUrl(_url);
	addTab(_title, _url);
}
