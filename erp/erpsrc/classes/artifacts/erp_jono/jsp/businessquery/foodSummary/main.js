var startDate, endDate, shopC;
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

	require([ "dojo/_base/xhr" ], function(xhr) {
		var _url = appRoot + "/businessquery/foodSummary/foodSummary.action";

		_url += "?startDate=" + startDate + "&endDate=" + endDate + "&shopC="+ shopC;

		dojo.empty("gridDiv");
		_url = getUrl(_url);
		initGrid(_url);
	});
	return false;
}

var grid;

function initGrid(_url) {
	require([ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard",
	          "dgrid/Selection", "custom/store/Server"],
			function(OnDemandGrid, declare, Keyboard, Selection, Server) {
				var store = new Server({
					target : _url,
					idProperty : "id"
				});

				var CustomGrid = declare([ OnDemandGrid, Keyboard, Selection]);
				grid = new CustomGrid({
					store : store,
					columns : cols,
					cellNavigation : false,
					selectionMode : "single",
					loadingMessage : '加载中...',
					farOffRemoval : 10000, 
					minRowsPerPage : 10000
				}, "gridDiv");

				grid.on("dgrid-refresh-complete", function(event) {
					if (grid.store.data.length != 0) {
						busyButton.cancel();
					}

				});

				grid.startup();
			});
}

var cols = [  {
	label : "序号",
	field : "rownumber",
	sortable:false,
	formatter : function(field) {
		if (field == "合计") {
			return "<b>合计</b>";
		}
		return field;
	}
},{
	label : "出品编码",
	className:'text-center',
	sortable:false,
	field : "foodC"
},{
	label : "出品名称",
	sortable:false,
	field : "foodN"
}, {
	label : "单位",
	sortable:false,
	className:'text-center',
	field : "unit"
},{
	label : "数量",
	sortable:false,
	className:'text-right',
	field : "qty",
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "赠送数量",
	sortable:false,
	field : "pesentQty",
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "退品数量",
	sortable:false,
	field : "returnQty",
	className:'text-right',
	formatter : function(field, renderCell) {
		if (renderCell.rownumber == "合计") {
			return "<b>" + field + "</b>";
		}
		return field;
	}
}, {
	label : "",
	sortable:false,
	field : "placehodler"
}];
var cols;
