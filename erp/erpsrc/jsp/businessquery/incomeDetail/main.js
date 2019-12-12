var type, startDate, endDate, shopC;
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
	type = dojo.byId("type").value;
	startDate = dojo.byId("startDate").value;
	endDate = dojo.byId("endDate").value;
	shopC = dojo.byId("shopC").value;

	setCols();

	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/businessquery/incomeDetail/dayIncomeDetail.action";

		if (type == 'month') {
			_url = appRoot + "/businessquery/incomeDetail/monthIncomeDetail.action";
		} 

		_url += "?startDate=" + startDate + "&endDate=" + endDate + "&shopC="+ shopC;
		_url = getUrl(_url);
		
		dojo.empty("gridDiv");
		initGrid(_url);
	});
	return false;
}

function setCols() {
	if (cols != undefined) {
		cols.splice(0, cols.length);
	} else {
		cols = [];
	}

	if (type == "day") {
		cols = dayCols.concat(endCols);
	} else if (type == "month") {
		cols = monthCols.concat(endCols);
	} 
}

var grid;

function initGrid(_url) {
	require(
			[ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard",
					"dojo/window", "dgrid/Selection", "custom/store/Server" ],
			function(OnDemandGrid, declare, Keyboard, win, Selection, Server) {

				// calculate the grid height for avoid the outside scrollbar
				var vs = win.getBox();
				var gridHeight = vs.h - 100;
				var gridNode = dojo.byId("gridDiv");
				gridNode.style.height = gridHeight + "px";

				var store = new Server({
					target : _url,
					idProperty : "id"
				});

				var CustomGrid = declare([ OnDemandGrid, Keyboard, Selection ]);
				grid = new CustomGrid({
					store : store,
					columns : cols,
					cellNavigation : false,
					selectionMode : "single",
					loadingMessage : '加载中...',
					farOffRemoval : 10000, // larger than total height of data;
					// never
					// remove rows
					minRowsPerPage : 10000
				// request more data at a time
				}, "gridDiv");

				grid.on("dgrid-refresh-complete", function(event) {
					if (grid.store.data.length != 0) {
						busyButton.cancel();
					}

				});

				grid.startup();
			});
}

var dayCols = [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
}, {
	label : "日期",
	field : "businessDate"
}];

var monthCols = [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
}, {
	label : "月份",
	field : "businessMonth"
}];

var endCols = [ {
	label : "大类",
	field : "categoryN"
}, {
	label : "午餐",
	field : "period1",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "午茶",
	field : "period2",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "晚餐",
	field : "period3",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "晚茶",
	field : "period4",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "小计",
	field : "counts",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
} , {
	label : "",
	field : "placehodler"
} ];
var cols;
