dojo.ready(function() {
	initGrid();
});


var grid = null;

function initGrid(_id) {
	var _url = appRoot + "";
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare,
			query, Keyboard) {
		var myStore = new JsonRest({
			target : _url,
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
//			store : myStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber"
},{
	label : "清单编号",
	field : "billId"
}, {
	label : "盘点部门",
	field : "checkDepartment"
}, {
	label : "盘点批次",
	field : "check"
}, {
	label : "制单人员",
	field : "billPeople"
}, {
	label : "制单日期",
	field : "billDate"
}, {
	label : "备注",
	field : "remarks"
} , {
	label : "主要盘点物",
	field : "mainCheckItem"
} , {
	label : "盘点总额",
	field : "checkPrice"
} , {
	label : "清单状态",
	field : "billState"
}];
