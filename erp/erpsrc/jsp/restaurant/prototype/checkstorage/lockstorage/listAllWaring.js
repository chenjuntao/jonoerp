dojo.ready(function() {
	initGrid();
});


var grid = null;
var grid2 = null;
var grid3 = null;
var grid4 = null;

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
		}, "putInStorageBillDataGrid");

		grid.startup();
	});
	var _url2 = appRoot + "";
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare,
			query, Keyboard) {
		var myStore2 = new JsonRest({
			target : _url2,
		});

		var CustomGrid2 = declare([ OnDemandGrid, Keyboard ]);
		grid2 = new CustomGrid2({
//			store : myStore2,
			columns : cols2,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "distributionAntiAuditBillDataGrid");

		grid2.startup();
	});
	var _url3 = appRoot + "";
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare,
			query, Keyboard) {
		var myStore3 = new JsonRest({
			target : _url3,
		});

		var CustomGrid3 = declare([ OnDemandGrid, Keyboard ]);
		grid3 = new CustomGrid3({
//			store : myStore3,
			columns : cols3,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "itemDamageReportBillDataGrid");

		grid3.startup();
	});
	var _url4 = appRoot + "";
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare,
			query, Keyboard) {
		var myStore4 = new JsonRest({
			target : _url4,
		});

		var CustomGrid4 = declare([ OnDemandGrid, Keyboard ]);
		grid4 = new CustomGrid4({
//			store : myStore4,
			columns : cols3,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dishDamageReportBillDataGrid");

		grid4.startup();
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber"
},{
	label : "入库单编号",
	field : "billId"
}, {
	label : "供应商",
	field : "supplier"
}, {
	label : "采购单号",
	field : "originalBillId"
}, {
	label : "入库部门",
	field : "putInStorageDepartment"
}, {
	label : "入库人员",
	field : "putInStoragePeople"
}, {
	label : "入库日期",
	field : "putInStorageDate"
}, {
	label : "备注",
	field : "remark"
}, {
	label : "单据状态",
	field : "billState"
}, {
	label : "主要入库品",
	field : "importantItemName"
}, {
	label : "入库总额",
	field : "TotalPrice"
} ];

var cols2 = [ {
	label : "序号",
	field : "rownumber"
},{
	label : "配送反审核单号",
	field : "billId"
}, {
	label : "配送反审核部门",
	field : "createBillDepartment"
}, {
	label : "配送反审核人员",
	field : "createBillPeople"
}, {
	label : "配送反审核日期",
	field : "createBillDate"
}, {
	label : "备注",
	field : "remark"
}, {
	label : "配送部门",
	field : "distributionDepartment"
}, {
	label : "配送日期",
	field : "distributionDate"
}, {
	label : "入库部门",
	field : "putInStorageDepartment"
}, {
	label : "入库人员",
	field : "putInStoragePeople"
}, {
	label : "入库日期",
	field : "putInStorageDate"
},  {
	label : "主要配送品",
	field : "importantItemName"
}, {
	label : "配送总额",
	field : "TotalPrice"
} ,{
	label : "单据状态",
	field : "billState"
}];
var cols3 = [ {
	label : "序号",
	field : "rownumber"
},{
	label : "报损单编号",
	field : "billId"
}, {
	label : "报损部门",
	field : "damageReportDepartment"
}, {
	label : "报损人员",
	field : "damageReportPeople"
}, {
	label : "报损日期",
	field : "damageReportDate"
}, {
	label : "审核人员",
	field : "confirmPeople"
}, {
	label : "审核日期",
	field : "confirmReportDate"
}, {
	label : "处理人员",
	field : "processPeople"
}, {
	label : "处理日期",
	field : "processReportDate"
}, {
	label : "备注",
	field : "remark"
}, {
	label : "单据状态",
	field : "billState"
}, {
	label : "主要报损品",
	field : "importantDamageName"
}, {
	label : "报损总额",
	field : "damageTotalPrice"
}, {
	label : "查看操作",
	field : "queryBill"
} ];
