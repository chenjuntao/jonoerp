function doQuery() {
	require([ "dojo/_base/xhr" ], function(xhr) {

		startDate = dojo.byId("startDate").value;
		endDate = dojo.byId("endDate").value;
		type = dojo.byId("type").value;

		xhr.post({
			url : appRoot + "/businessquery/shoppayment/getColumn.action",
			form : 'queryForm',
			handleAs : "json",
			load : function(data) {
				getColumn(data);
				if (type == '1') {
					getData(xhr);
				} else {
					getDataByDay(xhr);
				}
			},
			error : function(error) {
				alert(error);
			}
		});
	});
	return false;
}

function getTotals(data) {
	var totals = {};

	// the total for shop
	totals.foodAmt = totals.billNum = totals.guestNum = totals.tableNum = totals.disAmt = 0;
	totals.roundAmt = totals.presentAmt = totals.oughtAmt = totals.payTypeAmt = totals.payAmt = 0;

	// the total for pay
	totals.pay_01 = totals.pay_02 = totals.pay_03 = totals.pay_04 = totals.pay_05 = 0;
	totals.pay_06 = totals.pay_07 = totals.pay_08 = totals.pay_09 = totals.pay_14 = 0;
	totals.pay_15 = totals.pay_16 = totals.pay_17 = totals.pay_19 = totals.pay_20 = 0;
	totals.pay_21 = totals.pay_22 = totals.pay_23 = totals.pay_24 = totals.pay_25 = 0;
	totals.pay_26 = totals.pay_27 = 0;

	for (var i = data.length; i--;) {
		for ( var k in totals) {
			var elem = data[i][k];

			if (elem == undefined || elem == null || elem == "") {
				totals[k] += 0;
			} else if (typeof elem == 'object') {
				totals[k] += parseFloat(elem.payTypeAmt);
			} else {
				totals[k] += elem;
			}
		}
	}

	for ( var k in totals) {
		if (totals[k] != Math.round(totals[k])) {
			totals[k] = totals[k].toFixed(2);
		}
	}

	if (type == '1') {
		totals.shopC = '合计';
	} else {
		totals.businessDate = '合计';
	}
	return totals;
}

function getDataByDay(xhr) {
	xhr.post({
		url : appRoot + "/businessquery/shoppayment/getDataByDay.action",
		form : 'queryForm',
		handleAs : "json",
		load : function(data) {
			queryDetail(data);
		},
		error : function(error) {
			alert(error);
		}
	});
}

function getData(xhr) {
	xhr.post({
		url : appRoot + "/businessquery/shoppayment/getData.action",
		form : 'queryForm',
		handleAs : "json",
		load : function(data) {
			queryDetail(data);
		},
		error : function(error) {
			alert(error);
		}
	});
}

var cols;
var startDate, endDate;

function getColumn(data) {

	if (cols != undefined) {
		cols.splice(0, cols.length);
	} else {
		cols = [];
	}

	require([ "dojo/store/Memory" ], function(Memory) {
		var store = new Memory({
			data : data
		});

		var innerSets = [];
		var columnSets = [
				{
					label : "日期",
					field : "businessDate"
				},
				{
					label : "门店编码",
					field : "shopC"
				},
				{
					label : "门店名称",
					field : "shopN",
					formatter : function(field, renderCell) {
						return "<a href='javascript: parent.addTab(\"" + field + "\","
								+ "\"../businessquery/tableListQuery/listTable.action?shopC=" + renderCell.shopC
								+ "&startDate=" + startDate + "&endDate=" + endDate + "\");'>" + field + "</a>";
					}
				} ];
		if (type == '1') {
			columnSets.shift();
		}

		var columnSubs = [];
		var innerSubs = [ {
			label : "消费金额",
			field : "foodAmt"
		}, {
			label : "单数",
			field : "billNum"
		}, {
			label : "单均消费金额	",
			field : "amtPerBill"
		}, {
			label : "人数",
			field : "guestNum"
		}, {
			label : "人均消费",
			field : "amtPerGuest"
		}, {
			label : "台位数",
			field : "tableNum"
		}, {
			label : "台均消费",
			field : "amtPerTable"
		}, {
			label : "台位周转率",
			field : "guestPerTable"
		}, {
			label : "总折扣额",
			field : "disAmt"
		}, {
			label : "舍尾金额",
			field : "roundAmt"
		}, {
			label : "赠送金额",
			field : "presentAmt"
		}, {
			label : "应收款金额",
			field : "oughtAmt"
		}, {
			label : "收款金额",
			field : "payAmt"
		} ];

		store.query().forEach(
				function(item) {
					for ( var k in item) {
						var label = item[k] + "";

						innerSubs[innerSubs.length] = {
							field : k,
							label : label,
							formatter : function(field, renderCell) {
								if (field == undefined) {
									return "";
								}

								return "<a href='javascript: parent.addTab(\"" + renderCell.shopN + "\","
										+ "\"../jsp/businessquery/payShopBillList.action?shopC=" + renderCell.shopC
										+ "&type=" + field.payCode + "&currentPage=0&startDate=" + startDate
										+ "&endDate=" + endDate + "\");'>" + parseFloat(field.payTypeAmt) + "</a>";
							}
						};
					}
					;
				});

		innerSets.push(columnSets);
		columnSubs.push(innerSubs);

		cols.push(innerSets);
		cols.push(columnSubs);
	});
}

var grid;

function queryDetail(data) {
	if (grid == undefined) {
		initGrid(data);
	} else {
		require([ "dojo/store/Memory" ], function(Memory) {
			var store = new Memory({
				data : data
			});
			grid.set('columnSets', cols);
			grid.set('store', store);
			grid.set('summary', getTotals(data));
		});
	}
}

function initGrid(data) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dojo/window",
			'custom/SummaryRow', 'dojo/_base/lang', "dgrid/ColumnSet" ], function(OnDemandGrid, Memory, declare,
			Keyboard, win, SummaryRow, lang, ColumnSet) {

		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 110;
		var gridNode = dojo.byId("dataGrid");
		gridNode.style.height = gridHeight + "px";

		var CustomGrid = declare([ OnDemandGrid, Keyboard, SummaryRow, ColumnSet ]);
		grid = new CustomGrid(lang.mixin({
			store : new Memory({
				data : data
			}),
			columnSets : cols,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...',
			farOffRemoval : 10000, // larger than total height of data; never
			// remove rows
			minRowsPerPage : 10000
		// request more data at a time
		}), "dataGrid");

		grid.set('summary', getTotals(data));

		grid.startup();
	});
}
