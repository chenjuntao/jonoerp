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
	period = dojo.byId("period").value;
	shift = dojo.byId("shift").value;

	setCols();

	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/restaurant/billConditions/freeBillInfo.action";

		if (type == 'zk') {
			_url = appRoot + "/restaurant/billConditions/discountInfo.action";
		} else if (type == 'zs') {
			_url = appRoot + "/restaurant/billConditions/presentInfo.action";
		} else if (type == 'tp') {
			_url = appRoot + "/restaurant/billConditions/returnInfo.action";
		}

		_url += "?startDate=" + startDate + "&endDate=" + endDate + "&period=" + period + "&shift=" + shift
				+ "&shopC=" + shopC;
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

	cols.push(startCols);
	if (type == "md") {
		cols.push(mdCols);
	} else if (type == "zs") {
		cols.push(zsCols);
	} else if (type == "tp") {
		cols.push(tpCols);
	} else if (type == "zk") {
		cols.push(zkCols);
	}
}

var grid;

function initGrid(_url) {
	require([ "dgrid/Grid", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/ColumnSet", "dgrid/Selection",
			"dgrid/extensions/Pagination", "custom/store/Server" ], function(Grid, declare, Keyboard, ColumnSet,
			Selection, Pagination, Server) {

		var store = new Server({
			target : _url
		});

		var CustomGrid = declare([ Grid, Keyboard, ColumnSet, Selection, Pagination ]);
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

var startCols = [ [ {
	label : "序号",
	field : "rownumber",
	formatter : function(field) {
		if (field == "合计") {
			return getBoldText(field);
		}
		return field;
	},
	sortable:false
}, {
	label : "营业日期",
	field : "businessDate",
	className : "text-center",
	sortable:false
} ] ];

var zkCols = [ [ {
	label : "台卡号",
	field : ""
}, {
	label : "台位",
	field : "table"
}, {
	label : "出品单号",
	field : "billC",
	renderCell : function(object, data) {
		if (object.rownumber == "合计") {
			return "";
		}

		return hrefFmt(object.billC, doShow, object);
	}
}, {
	label : "班次",
	field : "shift"
}, {
	label : "市别",
	field : "period"
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
	label : "折扣原因",
	field : "disWhy"
}, {
	label : "折扣人",
	field : "disMan"
} ] ];

var zsCols = [ [ {
	label : "台卡号",
	field : ""
}, {
	label : "台位",
	field : "table"
}, {
	label : "出品单号",
	field : "billC",
	renderCell : function(object, data) {
		if (object.rownumber == "合计") {
			return "";
		}

		return hrefFmt(object.billC, doShow, object);
	}
}, {
	label : "班次",
	field : "shift"
}, {
	label : "市别",
	field : "period"
}, {
	label : "出品名称",
	field : "foodName"
}, {
	label : "单位",
	field : "unit"
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
	label : "价格",
	field : "price"
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
	label : "加价",
	field : "extracPrice",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "赠送授权人",
	field : "presentMan"
}, {
	label : "赠送原因",
	field : "presentWhy"
}, {
	label : "大类编码",
	field : "bigC"
}, {
	label : "大类名称",
	field : "bigN"
}, {
	label : "小类编码",
	field : "smallC"
}, {
	label : "小类名称",
	field : "smallN"
}, {
	label : "门店编码",
	field : "shopC"
}, {
	label : "门店名称",
	field : "shopN"
} ] ];

function doShow(row) {
	var _url = appRoot + "/jsp/businessquery/foodlist.action?billC=" + row.billC;
	addTab(row.billC, _url);
}

var mdCols = [ [ {
	label : "出品单号",
	field : "billC",
	className : "text-center",
	renderCell : function(object, data) {
		return hrefFmt(object.billC, doShow, object);
	},
	sortable:false
}, {
	label : "台位",
	field : "table",
	sortable:false
}, {
	label : "班次",
	field : "shift",
	sortable:false
}, {
	label : "市别",
	field : "period",
	sortable:false
}, {
	label : "门店编码",
	field : "shopC",
	sortable:false
}, {
	label : "门店名称",
	field : "shopN",
	sortable:false
}, {
	label : "出品名称",
	field : "foodN",
	sortable:false
}, {
	label : "标准单价",
	field : "price",
	className : "text-right",
	sortable:false
}, {
	label : "数量",
	field : "qty",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		return field;
	},
	className : "text-right",
	sortable:false
}, {
	label : "消费金额",
	field : "amt",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return getBoldText(field);
		}
		return field;
	},
	className : "text-right",
	sortable:false

}, {
	label : "单位",
	field : "unit",
	className : "text-center",
	sortable:false
}, {
	label : "大类编码",
	field : "bigC",
	sortable:false
}, {
	label : "大类名称",
	field : "bigN",
	sortable:false
}, {
	label : "小类编码",
	field : "smallC",
	sortable:false
}, {
	label : "小类名称",
	field : "smallN",
	sortable:false
} ] ];

var tpCols = [ [ {
	label : "台卡号",
	field : ""
}, {
	label : "出品单号",
	field : "billC",
	renderCell : function(object, data) {
		if (object.rownumber == "合计") {
			return "";
		}

		return hrefFmt(object.billC, doShow, object);
	}
}, {
	label : "台位",
	field : "table"
}, {
	label : "班次",
	field : "shift"
}, {
	label : "市别",
	field : "period",
}, {
	label : "点单号",
	field : "foodBill"
}, {
	label : "出品码",
	field : "foodC"
}, {
	label : "出品名称",
	field : "foodN"
}, {
	label : "单位",
	field : "unit"
}, {
	label : "价格",
	field : "price",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
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
	label : "退品原因",
	field : "returnWhy"
}, {
	label : "退品时间",
	field : "returnTime",
	formatter : function(field) {
		if (field == undefined) {
			return "";
		}
		if (field.indexOf(".") != -1) {
			return field.substring(0, field.indexOf("."));
		}

		return field;
	}
}, {
	label : "退品授权人",
	field : "backMan"
}, {
	label : "套餐标志",
	field : "suitFlag"
},{
	label : "小类编码",
	field : "smallC"
}, {
	label : "小类名称",
	field : "smallN"
},  {
	label : "门店编码",
	field : "shopC"
}, {
	label : "门店名称",
	field : "shopN"
} ] ];
var cols;
