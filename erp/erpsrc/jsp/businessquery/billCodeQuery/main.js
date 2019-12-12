var grid = null;

var startDate, endDate, billCode, shopC;

function test(field) {
	parent.addTab(field, "businessquery/foodlist.action?billC=" + field + "&startDate=" + startDate + "&endDate="
			+ endDate);
}

function doQuery() {
	require([ "dojo/json", "dojo/data/ObjectStore", "dojox/grid/enhanced/plugins/Pagination",
			"dojox/grid/enhanced/plugins/Filter", "dojox/grid/EnhancedGrid", "custom/store/Server", "dojo/dom",
			"dojo/domReady!" ], function(JSON, ObjectStore, Pagination, Filter, EnhancedGrid, Server, dom) {

		startDate = dom.byId("startDate").value;
		endDate = dom.byId("endDate").value;
		billCode = dom.byId("billCode").value;
		shopC = dom.byId("shopC").value;

		var _url = appRoot + "/businessquery/billCodeQuery/listBill.action?startDate=" + startDate + "&endDate="
				+ endDate + "&billCode=" + billCode + "&shopC=" + shopC;
		_url = getUrl(_url);
		var restStore = new Server({
			target : _url
		});

		var dataStore = dojo.data.ObjectStore({
			objectStore : restStore
		});

		// var setupFilter = function(commands, request) {
		// // the commands object here is the same as the POSTed commands
		// // object
		// // for stateful server, see below.
		// if (commands.filter && commands.enable) {
		// // some filter is defined and valid. You can modify the request
		// // object here.
		// request.query = {
		// "filter" : JSON.stringify(commands.filter)
		// };
		// } else {
		// // no filter is valid.
		// }
		// };

		if (grid) {
			grid.setStore(dataStore);
			return;
		}

		grid = new dojox.grid.EnhancedGrid({
			store : dataStore,
			structure : [
					{
						name : "营业日期",
						field : "dBusiness",
						width : 6
					},
					{
						name : "单据编号",
						field : "billC",
						formatter : function(field) {
							var _url = appRoot + "/jsp/businessquery/foodlist.action?billC=" + field;
							_url = getUrl(_url);
							var result = '<a href="javascript: parent.addTab(\'' + field + '\',' + '\'' + _url
									+ '\');">' + field + '</a>';
							return result;
						},
						width : 10
					}, {
						name : "市别",
						field : "period",
						width : 4
					}, {
						name : "班次",
						field : "shift",
						width : 4
					}, {
						name : "人数",
						field : "guestNum",
						width : 3
					}, {
						name : "开台时间",
						field : "billTime",
						width : 10
					}, {
						name : "结账时间",
						field : "settleTime",
						width : 10
					}, {
						name : "开台人",
						field : "createMan",
						width : 6
					}, {
						name : "结账人",
						field : "settleMan",
						width : 6
					}, {
						name : "消费金额",
						field : "foodAmt",
						width : 8
					}, {
						name : "舍尾金额",
						field : "roundAmt",
						width : 8
					}, {
						name : "赠送金额",
						field : "presentAmt",
						width : 8
					}, {
						name : "付款金额",
						field : "payAmt",
						width : 8
					}, {
						name : "总折扣额",
						field : "disAmt",
						width : 8
					} ],
			columnReordering : true,
			clientSort : true,
			rowSelector : '20px',
			rowsPerPage : 5,
			autoHeight : true,
			plugins : {
				pagination : {
					pageSizes : [ "10" ], // page length menu
					// options
					description : true, // display the current position
					sizeSwitch : true, // display the page length menu
					pageStepper : true, // display the page navigation choices
					gotoButton : true, // go to page button
					position : "bottom" // position of the pagination bar
				}

			// ,filter : {
			// // Show the closeFilterbarButton at the filter bar
			// closeFilterbarButton : true,
			// // Set the maximum rule count to 5
			// ruleCount : 5,
			// // Set the name of the items
			// itemsName : "items",
			// isServerSide : true ,
			// setupFilterQuery : setupFilter
			// }
			}
		}, "gridDiv");
		grid.startup();
	});
}
