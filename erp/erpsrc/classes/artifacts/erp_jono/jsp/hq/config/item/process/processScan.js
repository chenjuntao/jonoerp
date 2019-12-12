dojo.ready(function() {
	initGrid();
});

var grid = null;
function initGrid() {
	var _url = appRoot + "/hq/item/process/doQuery.action?itemId=" + g_itemId;
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dgrid/selector", "dgrid/Selection","dgrid/extensions/ColumnResizer"  ],
			function(OnDemandGrid, JsonRest, declare, selector, Selection,ColumnResizer) {
				var myStore = new JsonRest({
					target : _url,
					idProperty : 'step'
				});

				var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
				grid = new CustomGrid({
					store : myStore,
					columns : getColumn(selector),
					cellNavigation : false,
					allowSelectAll : true,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
			});
}

function refresh() {
	grid.set('query', {});
}

function getColumn(selector) {
	return [ selector({
		field : 'rownumber'
	}), {
		label : "顺序",
		field : "step",
		sortable:false
	}, {
		label : "具体操作",
		field : "stepOperation",
		sortable:false
	}, {
		label : "备注",
		field : "stepNote",
		sortable:false
	}, {
		label : "",
		field : "blank",
		sortable:false
	} ];
}
