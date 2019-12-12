dojo.ready(function() {
	initRawGrid();
});

function initRawGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dgrid/ColumnSet", "dojox/widget/Standby", "dojo/domReady!" ], function(
			OnDemandGrid, Memory, declare, Keyboard, ColumnResizer, ColumnSet, Standby) {
		var dataStore = new Memory({
			data : rawGridData
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, ColumnSet ]);
		var grid = new CustomGrid({
			store : dataStore,
			columnSets : rawCols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "rawLossGrid");

		grid.startup();
		// 初始化遮罩层
		standby = new Standby({
			target : "commitLossForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
}

var rawCols = [ [ [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "出品编码",
	field : "therapyId",
	className : 'text-center',
	sortable : false
}, {
	label : "出品名称",
	field : "therapyName",
	sortable : false
} ] ], [ [ {
	label : "例牌",
	field : "therapyDimension",
	className : 'text-center',
	sortable : false
}, {
	label : "出品报损数量",
	field : "therapyNum",
	className : 'text-right',
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
	label : "单位",
	field : "itemDimension",
	className : 'text-center',
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable : false
}, {
	label : "类别",
	field : "itemCategory",
	sortable : false
}, {
	label : "原料报损数量",
	field : "lossNum",
	className : 'text-right',
	sortable : false
}, {
	label : "标准单价",
	field : "itemUnitPrice",
	className : 'text-right',
	sortable : false
}, {
	label : "标准金额",
	field : "payAmt",
	className : 'text-right',
	sortable : false
}, {
	label : "",
	field : "blank",
	sortable : false
} ] ] ];

function commitLoss() {
	standby.show();
	var _url = appRoot + "/restaurant/reportdamage/querydishloss/doDishSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("commitLossForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("修改成功！");
				standby.hide();
				closeTab(tabId, "closeSelfTab");
			} else {
				alert("操作失败！");
				standby.hide();
			}
		}, function(err) {
		});
	});
}