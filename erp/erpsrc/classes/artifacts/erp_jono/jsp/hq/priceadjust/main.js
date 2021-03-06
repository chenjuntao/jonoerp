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

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/hq/priceadjust/doQuery.action?adjustType=" + g_adjustType;
	
	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dojo/parser", "dgrid/extensions/Pagination", "dgrid/extensions/ColumnResizer",
			"dojo/domReady!" ], function(Grid, Server, Observable, Cache, Memory, declare, parser, Pagination,
			ColumnResizer) {
		parser.parse();
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination, ColumnResizer ]);

		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doFormScan(row) {
	doDetailScan(row.formId);
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "单据编号",
		field : "formId",
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	}, {
		label : '修改/删除/作废',
		field : 'operate',
		renderCell : function(object, data) {
			return hrefFmt("修改/删除/作废", doEdit, object);
		},
		sortable : false
	}, {
		label : "单据日期",
		field : "formTime",
		sortable : false
	}, {
		label : "调价范围",
		field : "adjustScope",
		// formatter : scopeFmt,
		sortable : false
	}, {
		label : "生效时间",
		field : "effectTime",
		sortable : false
	}, {
		label : "制表人员",
		field : "formMaker",
		sortable : false
	}, {
		label : "审核人员",
		field : "auditor",
		sortable : false
	}, {
		label : "审核日期",
		field : "auditTime",
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
	} ];
}

function scopeFmt(value, rowData) {
	if (value == '0') {
		return '是';
	}
	return '所有门店';
}

function doScan(row) {
	var _url = appRoot + "/hq/priceadjust/scanView.action?formId=" + row.formId;
	
	addTab("查看", _url);
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);

	if (data.hasLock) {
		alert("单据正在编辑或确认中！")
		return false;
	}

	var status = data.formStatus;
	if (status == '已作废') {
		alert("单据已作废！");
		return false;
	} else if (status == '已审核') {
		alert("单据已审核！");
		return false;
	}
	return true;
}

function doAdd() {
	var g_adjustType = dojo.byId('adjustType').value;
	hideDialog();
	var _url = appRoot + "/hq/priceadjust/addView.action?adjustType=" + g_adjustType + "&parentTabId=" + tabId;
	
	addTab("新增调价单", _url);
}

function doEdit(row) {
	if (!checkStatus(row.formId)) {
		return;
	}
	var _url = appRoot + "/hq/priceadjust/editView.action?formId=" + row.formId + "&parentTabId=" + tabId;
	
	addTab("修改调价单-" + row.formId, _url);
}