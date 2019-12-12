var grid = null;
var dataStore = null;

require([ "dojo/request/xhr", "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/Selection",
		"dojo", "dojo/ready" ], function(xhr, OnDemandGrid, Server, declare, Selection, dojo, ready) {
	ready(function() {
		initGrid();
	});

	function initGrid() {
		var _url = appRoot + "/settle/month/query/doQuery.action";
		_url = getUrl(_url);
		
		require([], function() {
			dataStore = new Server({
				target : _url,
				idProperty : "branchId"
			});

			var CustomGrid = declare([ OnDemandGrid, Selection ]);
			grid = new CustomGrid({
				store : dataStore,
				columns : getColumn(),
				allowSelectAll : true,
				cellNavigation : false,
				selectionMode : "single",
				loadingMessage : '加载中...'
			}, "dataGrid");

			grid.startup();
		});
	}
});

function doQuery() {
	grid.set('query', {});
}

function getColumn() {
	return [ {
		label : "序号",
		sortable:false,
		field : "rownumber"
	}, {
		label : "月结日期",
		sortable:false,
		field : "monthDate"
	}, {
		label : "月结人",
		sortable:false,
		field : "operatorName"
	}, {
		label : "操作日期",
		sortable:false,
		field : "operatingTime"
	}, {
		label : "备注",
		sortable:false,
		field : "settleNote"
	}, {
		label : "",
		sortable:false,
		field : "none"
	} ];
}
