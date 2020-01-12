dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	var _url = appRoot + "/centralfactory/productionDemand/purchaseBill/create/doCommit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

var grid = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare",
			"dojo/query", "dgrid/Keyboard", "dojo/domReady!" ], function(
			OnDemandGrid, Memory, declare, query, Keyboard) {
		var dataStore = new Memory({
			data : gridData
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

var cols = [{
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
},{
	label : '库存量',
	field : 'inventory',
	className : 'grid-number'
},{
	label : '在途',
	field : 'onTheWay',
	className : 'grid-number'
},{
	label : '安全库存',
	field : 'safeStorage',
	className : 'grid-number'
},{
	label : '计划生产量',
	field : 'itemCount',
	className : 'grid-number'
}, {
	label : "实际生产量",
	field : "realCount",
	className : 'grid-number'
}, {
	label : "标准单价",
	field : "itemUnitPrice",
	className : 'grid-number'
}, {
	label : "标准金额",
	field : "payAmt",
	className : 'grid-number'
}, {
	label : "",
	field : "none"
} ];
