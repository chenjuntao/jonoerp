var grid = null;
var dataStore = null;

function initGird() {
	var _url = appRoot + "/sp/return/query.action";
	_url = getUrl(_url);

	require([ "dojo/_base/lang", "dojo/request/xhr", "dojo/_base/array", "dojo/_base/declare", "dgrid/Selection",
			"dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dgrid/extensions/Pagination" ], function(lang, xhr, arrayUtil, declare, Selection, Grid, Server,
			Observable, Cache, Memory, Pagination) {
		dataStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Selection, Pagination ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			pageSizeOptions : [ 10, 20, 50 ],
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！"
		}, 'dataGrid');

		grid.startup();
	});
};

function getColumn(selector) {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "退货编号",
		field : "form_id",
		renderCell : function(item, data) {
			return hrefFmtId(item.form_id, doScan, item, "formId" + item.rownumber);
		}
	}, {
		label : "入库单号",
		field : "form_ref_id",
		renderCell : function(item, data) {
			return hrefFmtId(item.form_ref_id, doRelInScan, item, "formRefId" + item.rownumber);
		}
	}, {
		label : "操作",
		field : "operate",
		renderCell : function(item, data) {
			if (item.status == '供应商已确认') {
				return hrefFmt('', doConfirm, item);
			}
			return hrefFmtId('对账确认', doConfirm, item, "operate" + item.rownumber);
		}
	}, {
		label : "供应商",
		field : "provider"
	}, {
		label : "退货部门",
		field : "input_department"
	}, {
		label : "退货人员",
		field : "returner"
	}, {
		label : "退货时间",
		field : "return_time"
	}, {
		label : "总金额",
		field : "all_pay_amt"
	}, {
		label : "确认状态",
		field : "status",
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
		label : "备注说明",
		field : "form_note"
	} ];
};

