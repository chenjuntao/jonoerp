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
		supplierId : dojo.byId('supplierId').value,
		branchId : dojo.byId('branchId').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/outer/orderstatement/doInQuery.action";
	_url = getUrl(_url);

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dojo/_base/declare", "dgrid/extensions/Pagination", "dojo/domReady!" ], function(
			OnDemandGrid, Server, Observable, Cache, Memory, declare, Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
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
		field : 'form_id',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt(object.form_id, doScan, object);
		}
	}, {
		label : '关联单号',
		field : 'form_ref_id',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt(object.form_ref_id, doRefScan, object);
		}
	}, {
		label : '物流中心',
		field : 'provider'
	}, {
		label : '配送日期',
		field : 'receive_time'
	}, {
		label : '单据日期',
		field : 'form_time'
	}, {
		label : '订货部门',
		field : 'requester'
	}, {
		label : '备注',
		field : 'form_note'
	}, {
		label : '确认状态',
		field : 'status',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "";
			}
			if (field != '外部订货方已确认') {
				return "未确认";
			} else {
				return "已确认";
			}

		}
	}, {
		label : '总额',
		field : 'all_pay_amt',
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	} ];
}

function doScan(row) {
	doDetailScan(row.form_id);
}

function doRefScan(row) {
	doDetailScan(row.form_ref_id);
}

function createBill() {
	var ids = [];
	var rows = grid.get('store').data;
	rows.pop();

	require([ "dojo/_base/array" ], function(arrayUtil) {
		arrayUtil.forEach(rows, function(item) {
			ids.push(item.form_id);
		});
	})

	dojo.byId("jsonData").value = JSON.stringify(grid.get('store').getData());

	var _url = appRoot + "/outer/orderstatement/createBill.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			alert("对账单生成成功！");
			closeTab(tabId, 'doQuery');
		}, function(err) {
			alert("操作失败！");
		});
	});
}
