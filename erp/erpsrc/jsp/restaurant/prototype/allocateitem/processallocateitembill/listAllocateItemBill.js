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
			store : myStore,
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
	label : "调拨单编号",
	field : "billId"
}, {
	label : "调入部门",
	field : "allocateToDepartment"
}, {
	label : "调出部门",
	field : "allocateFromDepartment"
}, {
	label : "制单人员",
	field : "createBillPeople"
}, {
	label : "制单日期",
	field : "createBillDate"
}, {
	label : "审核人员",
	field : "confirmBillPeople"
}, {
	label : "审核日期",
	field : "confirmBillDate"
}, {
	label : "确认人员",
	field : "agreeBillPeople"
}, {
	label : "确认日期",
	field : "agreeBillDate"
}, {
	label : "备注",
	field : "remark"
}, {
	label : "主要调拨品",
	field : "importantAllocateName"
}, {
	label : "调拨总额",
	field : "allocateTotalPrice"
}, {
	label : "拨付处理",
	field : "allocation"
} ];
