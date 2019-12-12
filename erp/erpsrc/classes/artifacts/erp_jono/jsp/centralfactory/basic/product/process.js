dojo.ready(function() {
	initGrid();
});

var grid = null;

function initGrid() {
	var _url = appRoot + '/cf/basic/product/queryProcess.action?itemId=' + itemId;
	_url = getUrl(_url);
	
	require([ "dojo/store/JsonRest", "dojo/_base/declare", "dgrid/OnDemandGrid", "dgrid/Keyboard" ,"dgrid/extensions/ColumnResizer"  ]
	, function(JsonRest,declare, OnDemandGrid, Keyboard,ColumnResizer) {
		var myStore = new JsonRest({
			idProperty : "step",
			target : _url
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard,ColumnResizer ]);
		grid = new CustomGrid({
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
		label : "编号",
		field : "step",
		sortable : false
	}, {
		label : "制程",
		field : "stepOperation",
		sortable : false
	}, {
		label : "备注",
		field : "stepNote",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
