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
	type = dojo.byId("type").value;
	startDate = dojo.byId("startDate").value;
	endDate = dojo.byId("endDate").value;
	shopC = dojo.byId("shopC").value;

	setCols();

	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/businessquery/billreason/discountReason.action";

		if (type == 'zs') {
			_url = appRoot + "/businessquery/billreason/presentReason.action";
		} else if (type == 'tp') {
			_url = appRoot + "/businessquery/billreason/returnReason.action";
		}

		_url = getUrl(_url);
		_url += "?startDate=" + startDate + "&endDate=" + endDate + "&shopC="
				+ shopC;

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

	if (type == "zs") {
		cols = cols.concat(zsCols);
	} else if (type == "tp") {
		cols = cols.concat(tpCols);
	} else if (type == "zk") {
		cols = cols.concat(zkCols);
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

var zkCols = [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
}, {
	label : "折扣原因",
	field : "disWhy"
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
	label : "付款金额",
	field : "payAmt",
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
}, {
	label : "",
	field : "placehodler"
} ];

var zsCols = [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
}, {
	label : "赠送原因",
	field : "presentWhy"
}, {
	label : "赠送数量",
	field : "presentAmount",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "赠送金额",
	field : "presentPrice",
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
}, {
	label : "",
	field : "placehodler"
} ];

var tpCols = [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
}, {
	label : "退品原因",
	field : "returnWhy"
}, {
	label : "退品数量",
	field : "returnAmount",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "退品金额",
	field : "returnAmt",
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
}, {
	label : "",
	field : "placehodler"
} ];
var cols;
