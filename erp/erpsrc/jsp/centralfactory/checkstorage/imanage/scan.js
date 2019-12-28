dojo.ready(function() {
	initGrid();
});

var grid = null;

function initGrid() {
	var _url = appRoot
			+ "/restaurant/checkstorage/manage/queryDetail.action?formId="
			+ formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest",
			"dojo/_base/declare", "dgrid/Keyboard", "dojo/domReady!" ],
			function(OnDemandGrid, JsonRest, declare, Keyboard) {
				var dataStore = new JsonRest({
					target : _url
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

var cols = [ {
	label : "序号",
	field : "rownumber"
}, {
	label : "原料编码",
	field : "itemId"
}, {
	label : "原料名称",
	field : "itemName"
}, {
	label : "单位",
	field : "itemDimension"
}, {
	label : "规格",
	field : "itemSpecification"
}, {
	label : "类别",
	field : "itemCategory"
}, {
	label : "原料有效期",
	field : "expiredTime"
}, {
	label : '盘点数量',
	field : 'checkCount',
	className : 'grid-number'
}, {
	label : "",
	field : "none"
} ];
