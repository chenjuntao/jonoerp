dojo.ready(function() {
	initGrid();
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value,
		branchId : '-1',// dojo.byId('branchId').value,
		supplierId : dojo.byId('supplierId').value,
		itemName : dojo.byId('itemName').value
	}
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/lc/basic/material/doQuery.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory" ], function(OnDemandGrid, Server, Observable, Cache, Memory) {
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				if (query.branchId == undefined) {
					query = getQuery();
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		grid = new OnDemandGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "原料编码",
		field : "itemId"
	}, {
		label : "原料名称",
		field : "itemName"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	} ];
}
