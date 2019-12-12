dojo.ready(function() {
	initDishGrid();
	// initRawGrid();
});

function initDishGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojox/widget/Standby", "dojo/domReady!" ], function(OnDemandGrid,
			Memory, declare, Keyboard, ColumnResizer, Standby) {
		var dataStore = new Memory({
			data : gridData,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		var grid = new CustomGrid({
			store : dataStore,
			columns : dishCols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dishGrid");

		grid.startup();

		// 初始化遮罩层
		standby = new Standby({
			target : "createSelfForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
}

function initRawGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Memory, declare, Keyboard,
			ColumnResizer) {
		var dataStore = new Memory({
			data : rawGridData,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		var grid = new CustomGrid({
			store : dataStore,
			columns : rawCols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "rawGrid");

		grid.startup();
	});
}

var rawCols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "原料编码",
	field : "itemId",
	className : 'text-center',
	sortable : false
}, {
	label : "原料名称",
	field : "itemName",
	sortable : false
}, {
	label : "例牌",
	field : "itemDimension",
	className : 'text-center',
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	className : 'text-center',
	sortable : false
}, {
	label : "类别",
	field : "itemCategory",
	sortable : false
}, {
	label : "耗料数量",
	field : "itemCountActual",
	sortable : false
}, {
	label : "",
	field : "blank",
	sortable : false
} ];

var dishCols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "半成品编码",
	field : "itemId",
	className : 'text-center',
	sortable : false
}, {
	label : "半成品名称",
	field : "itemName",
	sortable : false
}, {
	label : "例牌",
	field : "itemDimension",
	className : 'text-center',
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	className : 'text-center',
	sortable : false
}, {
	label : "类别",
	field : "itemCategory",
	sortable : false
}, {
	label : "入库数量",
	field : "itemCountActual",
	sortable : false
}, {
	label : "",
	field : "blank",
	sortable : false
} ];

function commit() {
	standby.show();
	var _url = appRoot + "/restaurant/production/createself/doCommit.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("createSelfForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				closeTab(parentTabId);
				alert("生成单据号为：" + data.formId + "，提交成功！");
				standby.hide();
				dojo.byId("doCommit").value = "关闭当前页面";
				dojo.byId("doCommit").onclick = function() {
					closeTab(tabId);
				};
			} else {
				standby.hide();
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}