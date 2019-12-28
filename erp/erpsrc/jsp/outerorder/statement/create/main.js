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
		branchId : dojo.byId('branchId').value,
		formIdText : ""
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/outer/orderstatement/doInQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/extensions/Pagination", "dojo/domReady!" ], function(Grid, Server, Observable,
			Cache, Memory, declare, Pagination) {
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
		sortable:false,
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '单号',
		field : 'form_id',
		sortable:false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt(object.form_id, doScan, object);
		}
	}, {
		label : '关联单号',
		field : 'form_ref_id',
		sortable:false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}
			return hrefFmt(object.form_ref_id, doRefScan, object);
		}
	}, {
		label : '物流中心',
		sortable:false,
		field : 'provider'
	}, {
		label : '配送日期',
		sortable:false,
		field : 'receive_time'
	}, {
		label : '单据日期',
		sortable:false,
		field : 'form_time'
	}, {
		label : '订货部门',
		sortable:false,
		field : 'requester'
	}, {
		label : '备注',
		sortable:false,
		field : 'form_note'
	}, {
		label : '确认状态',
		sortable:false,
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
		sortable:false,
		formatter : function(field, renderCell) {
			if (renderCell.rownumber == "合计") {
				return "<b>" + field + "</b>";
			}
			return field;
		}
	} ];
}

function doScan(row) {
	var formId = row.form_id;
	if (formId.indexOf("CTH") != -1) {
		var _title = "查看出货退货单-";
		var _url = appRoot + "/restaurant/preject/manage/scanOutView.action?formId=" + formId;
		
		_title += formId;
		addTab(_title, _url);
		return;
	}

	if (formId.indexOf("CH") != -1) {
		var _title = "查看出货单-";
		var _url = appRoot + "/lc/request/delivery/manage/scanView.action?formId=" + formId;
		
		_title += formId;
		addTab(_title, _url);
		return;
	}
	doDetailScan(row.form_id);
}

function doRefScan(row) {
	doDetailScan(row.form_ref_id);
}

function createView() {
	var startDate = dojo.byId("startDate").value;
	var endDate = dojo.byId("endDate").value;

	var supplierId = dojo.byId("supplierId").value;
	var branchId = dojo.byId("branchId").value;
	var formType = dojo.byId("formType").value;
	if (!supplierId) {
		alert("请选择一个物流中心！");
		return;
	}

	if (!branchId) {
		alert("请选择一个订货方！");
		return;
	}

	if (!startDate || !endDate) {
		alert("单据日期不能为空！");
		return;
	}

	if (grid.get('store').data == 0) {
		alert("没有数据，请重新选取日期后生成对账单！");
		return;
	}

	var _url = appRoot + "/outer/orderstatement/createView.action?parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addPostTab('dataForm', '生成对账单', _url);
}
