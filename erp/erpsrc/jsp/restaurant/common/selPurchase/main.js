require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doQuery() {
	grid.set('query', {
		formId : dojo.byId('formId').value
	});
}

var grid = null;
var dataStore = null;
function initGrid(_id) {
	if (g_branchType == '') {
		g_branchType = 'SUPPLIER';
	}
	var _url = appRoot + "/lc/request/purchase/manage/queryPurch.action";

	require([ "dgrid/Grid", "custom/store/Server", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection",
			"dgrid/extensions/ColumnResizer", "dgrid/extensions/Pagination", "dgrid/ColumnSet" ], function(Grid,
			Server, declare, selector, Selection, ColumnResizer, Pagination, ColumnSet) {
		dataStore = new Server({
			target : _url,
			idProperty : "formId"
		});

		var CustomGrid = declare([ Grid, Pagination, Selection, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();
	});
}

function getColumn(selector) {
	return [ [ [ selector({
		label : "",
		field : 'rownumber',
		selectorType : "radio",
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "单据号",
		field : "formId",
		sortable : false
	}, {
		label : "供应商Id",
		field : "providerId",
		sortable : false
	} ] ], [ [ {
		label : "供应商",
		field : "provider",
		sortable : false
	}, {
		label : "主要采购品",
		field : "maxPayItem",
		sortable : false
	}, {
		label : "制单时间",
		field : "formTime",
		sortable : false
	} ] ] ];
}

function doSelect() {
	var selId = null;
	for ( var formId in grid.selection) {
		selId = formId;
	}
	if (selId == null) {
		alert('请选择记录！');
	}
	parent.closePurchaseDlg(dataStore.get(selId));
}