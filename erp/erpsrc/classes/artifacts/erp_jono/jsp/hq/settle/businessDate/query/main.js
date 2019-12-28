var grid = null;
var dataStore = null;

require([ "dojo/request/xhr", "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/Selection",
		"dojo", "dojo/date", "dojo/ready" ],
		function(xhr, OnDemandGrid, Server, declare, Selection, dojo, date, ready) {
			ready(function() {
				initGrid();
			});

			function initGrid() {
				var _url = appRoot + "/hq/branch/a/queryAll.action";
				
				dataStore = new Server({
					target : _url,
					idProperty : "branchId",
					query : function(query, options) {
						return Server.prototype.query.call(this, query, options);
					}
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
			}

			doQuery = function() {
				grid.set('query', {});
			}

		});

function getColumn() {
	return [ {
		label : "序号",
		sortable:false,
		field : "rownumber"
	}, {
		label : "编号",
		sortable:false,
		field : "branchId"
	}, {
		label : "名称",
		sortable:false,
		field : "branchName"
	}, {
		label : "营业日期",
		sortable:false,
		field : "businessDate"
	}, {
		label : "助记码",
		sortable:false,
		field : "queryCode"
	}, {
		label : "位置",
		sortable:false,
		field : "branchAddress"
	}, {
		label : "联系人",
		sortable:false,
		field : "contactMan"
	}, {
		label : "电子邮箱",
		sortable:false,
		field : "email"
	}, {
		label : "电话",
		sortable:false,
		field : "telephone"
	}, {
		label : "传真",
		sortable:false,
		field : "fax"
	}, {
		label : "备注",
		sortable:false,
		field : "remark"
	}, {
		label : "",
		sortable:false,
		field : "none"
	} ];
}
