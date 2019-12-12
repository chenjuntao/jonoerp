var grid = null;
var dataStore = null;

function initGird() {
	var _url = appRoot + "/sp/poscan/doQuery.action";
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
		label : "查看状态",
		field : "status",
		formatter : function(currentValue) {
			return currentValue == 'Y' ? '已查看' : "<font color='red'>未查看</font>";
		}
	}, {
		label : "单据编号",
		field : "form_id",
		renderCell : function(item, data) {
			return hrefFmtId(item.form_id, doScan, item, "formId" + item.rowNumber);
		}
	}, {
		label : "供应商",
		field : "provider"
	}, {
		label : "订单类型",
		field : "delivery_type",
		formatter : function(currentValue) {
			return getDeliveryType(currentValue);
		}
	}, {
		label : "订货部门",
		field : "requester"
	}, {
		label : "单据日期",
		field : "form_time"
	}, {
		label : "制单人",
		field : "form_maker"
	}, {
		label : "主要采购品",
		field : "max_pay_item"
	}, {
		label : "备注说明",
		field : "form_note"
	} ];
};

