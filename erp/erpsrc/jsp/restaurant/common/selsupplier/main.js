require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function doQuery() {
	grid.set('query', {
		branchName : dojo.byId('supplierName').value
	});
}

var grid = null;
var dataStore = null;
function initGrid(_id) {
	if (g_branchType == '') {
		g_branchType = 'SUPPLIER';
	}
	var _url = appRoot + "/restaurant/shopInfoQuery/listShopInfo.action?branchType=" + g_branchType;

	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection",
			"dgrid/extensions/ColumnResizer", "dgrid/ColumnSet" ], function(OnDemandGrid, Server, declare, selector,
			Selection, ColumnResizer, ColumnSet) {
		dataStore = new Server({
			target : _url,
			idProperty : "branchId"
		});

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
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
		label : "编号",
		field : "branchId",
		sortable : false
	}, {
		label : "名称",
		field : "branchName",
		sortable : false
	} ] ], [ [ {
		label : "助记码",
		field : "queryCode",
		sortable : false
	}, {
		label : "位置",
		field : "branchAddress",
		sortable : false
	}, {
		label : "电子邮箱",
		field : "email",
		sortable : false
	}, {
		label : "电话",
		field : "telephone",
		sortable : false
	}, {
		label : "传真",
		field : "fax",
		sortable : false
	}, {
		label : "备注",
		field : "remark",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
}

function doSelect() {
	var selId = null;
	for ( var supplierId in grid.selection) {
		selId = supplierId;
	}
	if (selId == null) {
		alert('请选择记录！');
	}
	parent.closeSupplierDlg(dataStore.get(selId));
}