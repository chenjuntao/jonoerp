require([ "dojo/request/xhr","dojo/domReady!"], function(xhr) {
	
	initGrid();

	dojo.byId('deliveryType').innerHTML = getDeliveryType(deliveryType);
});

function showDialog() {
	if (isEmpty(grid)) {
		alert("请先选择一条单据！");
		return;
	}

	dijit.byId('customDialog').show();
}

function customPrint() {
	var times = dojo.byId('l_times').innerHTML;
	if (times.length == 0) {
		dojo.byId('l_times').innerHTML = '1';
	} else {
		times = parseInt(dojo.byId('l_times').innerHTML) + 1;
		dojo.byId('l_times').innerHTML = times;
	}
	//var formId = dojo.byId('formId').value;
	alert(formId);
	var _url = appRoot + "/lc/request/purchase/manage/preview.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query" ], function(xhr, Memory, query) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			prn1_print(grid.get('columns'), grid.get('store').data);
		}, function(err) {
		});
	});
}

function customExport() {
	fillData(grid.get('store').data);
}

function customPreview() {
	var times = dojo.byId('l_times').innerHTML;
	if (times.length == 0) {
		dojo.byId('l_times').innerHTML = '1';
	} else {
		times = parseInt(dojo.byId('l_times').innerHTML) + 1;
		dojo.byId('l_times').innerHTML = times;
	}
	//var formId = dojo.byId('l_formId').value;
	var _url = appRoot + "/lc/request/purchase/manage/preview.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query" ], function(xhr, Memory, query) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			prn1_preview(grid.get('columns'), grid.get('store').data);
		}, function(err) {
		});
	});
}

function customPrintDesign() {
	myDesign(grid.get('columns'), grid.get('store').data);
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function getDeliveryType(currentValue) {
	if (currentValue == 'UNIFIED')
		return "统配";
	if (currentValue == 'DIRECT')
		return "直配";
	if (currentValue == 'CROSS')
		return "越库";

	return "";
}

var grid = null;
var dataStore = null;
function initGrid() {
	require([ "dojo/window", "dojo/store/Observable", "dojo/store/Memory", "dojo/parser","dgrid/OnDemandGrid", "dojo/_base/declare",
			"dgrid/Keyboard", "dojo/request/xhr", "dojo/_base/array" ], function(win, Observable, Memory, parser,OnDemandGrid,
			declare, Keyboard, xhr, array) {
		parser.parse();
		var _url = appRoot + "/centralfactory/productionDemand/summary/queryDetail.action?formId=" + formId;
		var _queryType = 'NORMAL';
		if (deliveryType == 'DIRECT' && receiverId == '') {// 只有直配大单的查询方式不一样
			_queryType = 'DIRECT';
			_url = appRoot + "/lc/request/purchase/manage/queryDirect.action?formId=" + formId;
		}
		_url = getUrl(_url);
		
		require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				var rows = data;
				if (_queryType == 'DIRECT') {
					rows = data.detailLst;
				}

				dataStore = new Observable(new Memory({
					idProperty : "rownumber",
					data : rows
				}));

				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : cols,
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();

			}, function(err) {
			});
		});
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
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
	label : '收货部门',
	field : 'receiver'
}, {
	label : '数量',
	field : 'itemCount'
}, {
	label : '',
	field : 'none',
	sortable : false
} ];
