var grid = null;

require([ "dojo/dom-form", "dojo/domReady!" ], function(domForm) {

	initGrid();

	// 函数延迟声明
	window.getQuery = function() {

		// 单据编号转化为大写
		var query = domForm.toObject("dataForm");
		query.formIdText1 = query.formIdText1.toUpperCase();
		query.formIdText2 = query.formIdText2.toUpperCase();
		return query;
	}
});

function doQuery() {
	grid.set('query', getQuery());
}

function initGrid() {
	var _url = appRoot + "/sp/inscan/doQuery.action";
	_url = getUrl(_url);

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dgrid/extensions/Pagination", "dojo/domReady!" ], function(Grid, Server, Observable,
			Cache, Memory, declare, Pagination) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination ]);
		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 20, 50 ],
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
		renderCell : function(item, data) {
			if (item.rownumber == "合计") {
				return hrefFmt("", doScan, item);
			}
			return hrefFmtId(item.form_id, doScan, item, "formId" + item.rownumber);
		}
	}, {
		label : '供应商',
		field : 'provider'
	}, {
		label : '采购单号',
		field : 'form_ref_id',
		renderCell : function(item, data) {
			if (item.rownumber == "合计") {
				return hrefFmt("", doRelInScan, item);
			}
			return hrefFmtId(item.form_ref_id, doRelInScan, item, "formRefId" + item.rownumber);
		}
	}, {
		label : '操作',
		field : 'confirm',
		renderCell : function(item, data) {
			if (item.rownumber == "合计") {
				return hrefFmt("", doConfirm, item);
			}

			if (item.status == "供应商已确认") {
				return hrefFmt("", doConfirm, item);
			}

			return hrefFmtId("对账确认", doConfirm, item, "confirm" + item.rownumber);
		}
	}, {
		label : '入库部门',
		field : 'input_department'
	}, {
		label : '入库人员',
		field : 'inputer'
	}, {
		label : '审核日期',
		field : 'audit_time'
	}, {
		label : '入库日期',
		field : 'input_time'
	}, {
		label : '备注',
		field : 'form_note'
	// }, {
	// label : '总额',
	// field : 'all_pay_amt',
	// formatter : function(field, renderCell) {
	// if (renderCell.rownumber == "合计") {
	// return "<b>" + field + "</b>";
	// }
	// return field;
	// }
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
	} ];
}

function doScan(row) {
	var formId = row.form_id;
	var _url = appRoot + "/restaurant/putinstorage/outside/scanView.action?formId=" + formId + '&branchFlag=SP';
	_url = getUrl(_url);

	var _title = "查看入库单-";
	_title += formId;
	addTab(_title, _url);
	return;
}

function doRelInScan(row) {
	doDetailScan(row.form_ref_id);
}

function doConfirm(row) {
	var _url = appRoot + '/sp/inscan/checkView.action?formId=' + row.form_id + '&parentTabId=' + tabId;
	_url = getUrl(_url);

	var _title = '入库单确认' + row.form_id;
	addTab(_title, _url);
}
