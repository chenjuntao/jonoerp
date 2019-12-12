dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	var _url = appRoot + "/restaurant/checkstorage/icreate/doSave.action";
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
			"dojo/query", "dgrid/Keyboard", "dgrid/extensions/ColumnResizer" ,"dojo/domReady!" ], function(
			OnDemandGrid, Memory, declare, query, Keyboard,ColumnResizer) {
		var dataStore = new Memory({
			data : gridData
		});
		
		var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
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
