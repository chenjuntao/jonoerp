require([ "dojo/request/xhr", "dojo/domReady!" ], function(xhr) {
	initGrid();

	dojo.byId('deliveryType').innerHTML = getDeliveryType(deliveryType);

	xhr.post(appRoot + "/sp/poscan/deliveryStatus.action?formId=" + formId, {
		handleAs : "json"
	}).then(function(data) {
		dojo.byId("flag").innerHTML = data.msg;
	}, function(err) {
	});
});

function getDeliveryType(currentValue) {
	if (currentValue == 'DIRECT') {
		return "直配";
	}
	return "";
}

var grid = null;
var dataStore = null;
function initGrid() {
	require([ "dojo/window", "dojo/store/Observable", "dojo/store/Memory", "dgrid/OnDemandGrid", "dojo/_base/declare",
			"dgrid/Keyboard" ], function(win, Observable, Memory, OnDemandGrid, declare, Keyboard) {
		// 先实现功能，再优化代码
		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 170;
		var gridNode = dojo.byId("dataGrid");
		gridNode.style.height = gridHeight + "px";

		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();

		loadGridData();
	});
}

function loadGridData() {
	// 直配小单
	var _url = appRoot + "/centralfactory/productionDemand/summary/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(rows) {
			array.forEach(rows, function(row, i) {
				dataStore.put(row);
			});
		}, function(err) {
		});
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber"
}, {
	label : '商品编码',
	field : 'itemId'
}, {
	label : '商品名称',
	field : 'itemName'
}, {
	label : '单位',
	field : 'itemDimension'
}, {
	label : '规格',
	field : 'itemSpecification'
}, {
	label : '类别',
	field : 'itemCategory'
}, {
	label : '数量',
	field : 'itemCount'
}, {
	label : '',
	field : 'none'
} ];
