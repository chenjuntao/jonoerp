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
		branchType : dojo.byId('branchType').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/preject/manage/doOutQuery.action?formType=OUTER";
	_url = getUrl(_url);
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/extensions/Pagination", "dgrid/extensions/ColumnResizer", "dgrid/ColumnSet",
			"dojo/domReady!" ], function(Grid, Server, Observable, Cache, Memory, declare, Pagination, ColumnResizer,
			ColumnSet) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : myStore,
			columnSets : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doThScan(row) {
	var _title = "查看出货退货单-";
	var _url = appRoot + "/restaurant/preject/manage/scanOutView.action?formId=" + row.formId;
	_url = getUrl(_url);
	
	_title += row.formId;
	addTab(_title, _url);
}

function doRefScan(row) {
	doDetailScan(row.formRefId)
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false,
		formatter : function(field) {
			if (field == "合计") {
				return "<b>合计</b>";
			}
			return field;
		}
	}, {
		label : '退货单号',
		field : 'formId',
		sortable : false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return hrefFmt('', doThScan, object);
			}

			return hrefFmt(object.formId, doThScan, object);
		}
	}, {
		label : '操作',
		field : 'operate',
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return "";
			}

			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable : false
	} ] ], [ [ {
		label : '单据状态',
		field : 'formStatus',
		sortable : false
	}, {
		label : '出货单编号',
		field : 'formRefId',
		sortable : false,
		renderCell : function(object, data) {
			if (object.rownumber == "合计") {
				return hrefFmt("", doRefScan, object);
			}

			return hrefFmt(object.formRefId, doRefScan, object);
		}

	}, {
		label : '出货部门',
		field : 'provider',
		sortable : false
	}, {
		label : '出货日期',
		field : 'receiveTime',
		sortable : false
	}, {
		label : '订货部门',
		field : 'requester',
		sortable : false
	}, {
		label : '订货地址',
		field : 'requestAddress',
		sortable : false
	}, {
		label : '退货人员',
		field : 'returner',
		sortable : false
	}, {
		label : '退货日期',
		field : 'returnTime',
		sortable : false
	}, {
		label : '备注',
		field : 'formNote',
		sortable : false
	}, {
		label : '主要退货',
		field : 'maxPayItem',
		sortable : false
	}, {
		label : '退货总额',
		field : 'allPayAmt',
		className : 'grid-number',
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
		alert("单据正在编辑或审核中！");
		return false;
	}
	if (status == '已审核') {
		alert("出货退货单已审核！");
		return false;
	} else if (status == '已删除') {
		alert("出货退货单已删除！");
		return false;
	} else if (status == '已作废') {
		alert("出货退货单已作废！");
		return false;
	} else if (status == '未处理') {
		alert("出货退货单已审核！");
		return false;
	} else if (status == '已处理') {
		alert("出货退货单已处理！");
		return false;
	} else if (status == '已确认') {
		alert("出货退货单已确认！");
		return false;
	}
	return true;
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/preject/manage/editOutView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '出货退货单修改' + row.formId;
	addTab(_title, _url);
}