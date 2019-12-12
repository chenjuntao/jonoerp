require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var grid = null;
var dataStore = null;
function initGrid() {
	var _url = appRoot + "/restaurant/antiaudit/manage/queryDetail.action?formRefId=" + formRefId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dgrid/Keyboard", "dojo/domReady!" ],
			function(OnDemandGrid, Server, declare, Keyboard) {
				dataStore = new Server({
					target : _url,
					idProperty : 'rownumber'
				});
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					store : dataStore,
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
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实发数",
		field : "deliveryCount",
		className : 'grid-number',
		sortable : false
	// }, {
	// label : "原实收数",
	// field : "receiveCount",
	// className : 'grid-number',
	// sortable:false
	}, {
		label : "反审核实收数",
		field : "antiauditReceiveCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "原金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "反审核金额",
		field : "antiauditPayAmt",
		className : 'grid-number',
		sortable : false
	} ];
}
