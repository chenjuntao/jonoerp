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
		var _url = appRoot + "/businessquery/businesscount/shift.action";

		if (type == 'sb') {
			_url = appRoot + "/businessquery/businesscount/period.action";
		} else if (type == 'lc') {
			_url = appRoot + "/businessquery/businesscount/floor.action";
		}

		_url += "?startDate=" + startDate + "&endDate=" + endDate + "&shopC="
				+ shopC;
		
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

	if (type == "bc") {
		cols.push(bcCols);
	} else if (type == "sb") {
		cols.push(sbCols);
	} else if (type == "lc") {
		cols.push(lcCols);
	}else if (type == "dl") {
		cols.push(dlCols);
	}else if (type == "xl") {
		cols.push(xlCols);
	}
	
	cols.push(endCols);
}

var grid;

function initGrid(_url) {
	require(
			[ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard",
					"dojo/window", "dgrid/Selection", "custom/store/Server","dgrid/ColumnSet" ],
			function(OnDemandGrid, declare, Keyboard, win, Selection, Server,ColumnSet) {

				// calculate the grid height for avoid the outside scrollbar
				var vs = win.getBox();
				var gridHeight = vs.h - 100;
				var gridNode = dojo.byId("gridDiv");
				gridNode.style.height = gridHeight + "px";

				var store = new Server({
					target : _url,
					idProperty : "id"
				});

				var CustomGrid = declare([ OnDemandGrid, Keyboard, Selection ,ColumnSet]);
				grid = new CustomGrid({
					store : store,
					columnSets : cols,
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

var bcCols = [ [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
}, {
	label : "班次",
	field : "shift"
} ] ];

var lcCols = [ [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
}] ];

var sbCols = [ [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
},{
	label : "市别",
	field : "period"
} ] ];


var endCols = [ [ {
	label : "单数",
	field : "billNum",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "人数",
	field : "guestNum",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "消费金额",
	field : "foodAmt",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "折扣金额",
	field : "disAmt",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "舍尾金额",
	field : "roundAmt",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "应收金额",
	field : "oughtAmt",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "付款金额",
	field : "payAmt",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "赠送金额",
	field : "presentAmt",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "门店编码",
	field : "shopC"
}, {
	label : "门店名称",
	field : "shopN"
} ] ];
var cols;
