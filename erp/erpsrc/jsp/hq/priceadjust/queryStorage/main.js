require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doQuery() {
	grid.set('query', getQuery());
}

function getQuery() {
	return {
		startDate : dojo.byId('startDate').value,
		itemId : dojo.byId('itemId').value,
		branchId : dojo.byId('branchId').value
	}
}

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/hq/priceadjust/queryStorage/queryItemId.action";

	require([ "dgrid/Grid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dojo/parser", "dgrid/extensions/Pagination", "dgrid/extensions/ColumnResizer",
			"dojo/domReady!" ], function(Grid, Server, Observable, Cache, Memory, declare, parser, Pagination,
			ColumnResizer) {
		parser.parse();
		var myStore = Observable(Cache(Server({
			target : _url,
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery(), options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination, ColumnResizer ]);

		grid = new CustomGrid({
			store : myStore,
			columns : getColumn(),
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function doFormScan(row) {
	doDetailScan(row.formId);
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "单据编号",
		field : "formId",
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt(object.formId, doFormScan, object);
		}
	}, {
		label : "物料Id",
		field : "itemId",
		sortable : false
	}, {
		label : "物料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单据类型",
		field : "formType",
		sortable : false
	}, {
		label : "出入库数量",
		field : "itemCount",
		sortable : false
	} ];
}
