var type, startDate, endDate, shopC, period, shift;
var busyButton;

dojo.require("dojox.form.BusyButton");

dojo.ready(function() {
	busyButton = new dojox.form.BusyButton({
		id : "submit",
		busyLabel : "正在查询...",
		label : "查询",
		timeout : 10000
	}, "placeHolder");
	
	dojo.connect(dijit.byId("submit"), "_onClick", function() {
		doQuery();
	});
});

function doQuery() {
	startDate = dojo.byId("startDate").value;
	endDate = dojo.byId("endDate").value;
	shopC = dojo.byId("shopC").value;
	period = dojo.byId("period").value;
	shift = dojo.byId("shift").value;

	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/businessquery/billPayDetail/billPay.action";

		_url += "?startDate=" + startDate + "&endDate=" + endDate + "&period="
				+ period + "&shift=" + shift + "&shopC=" + shopC;
		_url = getUrl(_url);
		
		dojo.empty("gridDiv");
		initGrid(_url);
	});
	return false;
}

var grid;

function initGrid(_url) {
	require([ "dgrid/Grid", "dojo/_base/declare", "dgrid/Keyboard",
			"dojo/window", "dgrid/ColumnSet", "dgrid/Selection",
			"dgrid/extensions/Pagination", "custom/store/Server" ], function(
			Grid, declare, Keyboard, win, ColumnSet, Selection,
			Pagination, Server) {

		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 100;
		var gridNode = dojo.byId("gridDiv");
		gridNode.style.height = gridHeight + "px";

		var store = new Server({
			target : _url,
			idProperty : "id"
		});

		var CustomGrid = declare([ Grid, Keyboard, ColumnSet,
				Selection, Pagination ]);
		grid = new CustomGrid({
			store : store,
			columnSets : cols,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...',
			pageSizeOptions : [ 10, 15, 25, 50 ]
		}, "gridDiv");

		grid.on("dgrid-refresh-complete", function(event) {
			if (grid.store.data.length != 0) {
				busyButton.cancel();
			}

		});

		grid.startup();
	});
}

var cols = [
		[ [
				{
					label : "序号",
					field : "rownumber",
					formatter : function(field) {
						if (field == "合计") {
							return "<b>合计</b>";
						}
						return field;
					}
				},
				{
					label : "出品单号",
					field : "billC",
					formatter : function(field, renderCell) {
						if (field == undefined) {
							return "";
						}
						return "<a href='javascript: parent.addTab(\"" + field
								+ "\","
								+ "\"businessquery/foodlist.action?billC="
								+ field + "\");'>" + field + "</a>";
					}
				} ] ], [ [ {
			label : "台卡号",
			field : ""
		}, {
			label : "付款方式",
			field : "payN"
		}, {
			label : "单位",
			field : "unit"
		}, {
			label : "本币金额",
			field : "payAmt",
			formatter : function(field, renderCell) {
				if (renderCell.rownumber == "合计") {
					return "<b>" + field + "</b>";
				}
				return field;
			}
		}, {
			label : "营业日期",
			field : "businessDate"
		}, {
			label : "台位",
			field : "table"
		}, {
			label : "班次",
			field : "shift"
		}, {
			label : "市别",
			field : "period"
		}, {
			label : "门店编号",
			field : "shopC"
		}, {
			label : "门店名称",
			field : "shopN"
		}, {
			label : "会员卡号",
			field : "vipC"
		}, {
			label : "会员名称",
			field : "vipN"
		}, {
			label : "消费人",
			field : ""
		} ] ] ];
