dojo.ready(function() {
	initGrid();

	var operate_cn = getOperateCn(operate);
	dojo.byId("operateButton").value = operate_cn;
});

function getOperateCn(operate) {
	if (operate == 'pay') {
		return '付款';
	}

	if (operate == 'confirm') {
		return '收款确认';
	}

	return '供应商对账确认';
}

var grid = null;

function initGrid() {
	var _url = appRoot + "/hq/acscan/queryInBySForm.action?formId=" + formId;
	
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

			if (item.status == '供应商已确认') {
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

			if (field == '供应商已确认') {
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
	if (status == 'supplier_confirmed') {
		return "已付款";
	}

	if (status == 'payed') {
		return "对账已完结";
	}

	return "供应商已确认";
}

function getSuccessMsg() {
	if (status == 'supplier_confirmed') {
		return "付款成功！";
	}

	if (status == 'payed') {
		return "收款确认成功！";
	}

	return "供应商对账确认成功!";
}

function getErrorMsg() {
	if (status == 'supplier_confirmed') {
		return "付款失败！";
	}

	if (status == 'payed') {
		return "收款失败！";
	}

	return "供应商对账失败!";
}

function doOperate() {
	var idsArray = [];
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(grid.store.data, function(item, i) {
			if (item.status != '供应商已确认') {
				idsArray.push(item.subFormId);
			}
		});
	});

	var _url = appRoot + "/hq/acscan/doConfirm.action";
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				formId : formId,
				status : getStatus(),
				subFormIds : idsArray.join(",")
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
			if (item.status != '供应商已确认' && row.subFormId != item.subFormId) {
				idsArray.push(item.subFormId);
			}
		});
	});
	if (idsArray.length == 0) {
		alert("对账单明细将全部确认！");
		status = 'gysSure';
	}
	var subFormId = row.subFormId.toUpperCase();
	var _url = appRoot + "/sp/return/scan/confirm.action?formId=" + row.subFormId + '&status=' + status
			+ '&parentFormId=' + formId + "&parentTabId=" + tabId + "&form_ref_id=" + row.form_ref_id;
		
	var _title = '退货单确认' + row.subFormId;
	if (subFormId.indexOf("LK") != -1) {
		_url = appRoot + '/sp/inscan/checkView.action?formId=' + row.subFormId + '&status=' + status + '&parentFormId='
				+ formId + '&parentTabId=' + tabId;
		_title = '入库单确认' + row.subFormId;
	}
	addTab(_title, _url);
}

function doScan(row) {
	doDetailScan(row.subFormId);
}

function doRelInScan(row) {
	doDetailScan(row.formRefId);
}

function doQuery() {
	location.reload();// 刷新页面
}

function close() {
	closeTab(tabId, "doQuery");
}