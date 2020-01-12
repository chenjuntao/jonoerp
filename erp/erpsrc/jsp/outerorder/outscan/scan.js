dojo.ready(function() {
	initGrid();

	addEvent();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	var _url = appRoot + "/sp/inscan/doConfirm.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				subFormIds : formId,
				formType : 'W'
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("出货单确认成功！");
				closeTab(tabId, "doQuery");
			} else {
				alert("出货单确认失败！");
			}
		}, function(err) {
		});
	});
}

var grid = null;
function initGrid() {
	var _url = appRoot + "/restaurant/inoutquery/shipping/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dgrid/ColumnSet", "dojo/domReady!" ],
			function(OnDemandGrid, JsonRest, declare, query, Keyboard, ColumnSet) {
				var dataStore = new JsonRest({
					target : _url
				});
				var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnSet ]);
				grid = new CustomGrid({
					store : dataStore,
					columnSets : cols,
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
			});
}

var cols = [ [ [ {
	label : "序号",
	field : "rownumber"
}, {
	label : "商品编码",
	field : "itemId"
}, {
	label : "商品名称",
	field : "itemName"
} ] ], [ [ {
	label : "类别",
	field : "itemCategory"
}, {
	label : "单位",
	field : "itemDimension"
}, {
	label : "规格",
	field : "itemSpecification"
}, {
	label : "包装因子",
	field : "packagingFactor",
	className : 'grid-number'
}, {
	label : "包装单位",
	field : "packagingUnit"
}, {
	label : "订货数",
	field : "requestCount",
	className : 'grid-number'
}, {
	label : "配送数",
	field : "shippingCount",
	className : 'grid-number'
// }, {
// label : "实发数",
// field : "deliveryCount",
// className : 'grid-number'
// }, {
// label : "实收数",
// field : "receiveCount",
// className : 'grid-number'
// }, {
// label : "差异数",
// field : "differentCount",
// className : 'grid-number'
// }, {
// label : "退货数",
// field : "returnCount",
// className : 'grid-number'
}, {
	label : "零售单价",
	field : "itemUnitPrice",
	className : 'grid-number'
}, {
	label : "零售金额",
	field : "payAmt",
	className : 'grid-number'
}, {
	label : "",
	field : "none"
} ] ] ];
