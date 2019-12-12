require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
}

function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;
var changeIdObj = {};
function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid",
	          "dojo/store/Memory",
	          "dojo/_base/declare",
	          "dgrid/Keyboard" 
          ], function(	OnDemandGrid, Memory, declare, Keyboard, editor, NumberTextBox) {
		dataStore = new Memory({
			data : []
		});
		
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.startup();

		queryData();
	});
}

function queryData() {
	var _url = appRoot + "/restaurant/checkstorage/create/queryDetail.action";
	_url = getUrl(_url);
	
	require([
	         "dojo/request/xhr", 
	         "dojo/store/Observable", 
	         "dojo/store/Memory" 
         ], function(xhr, Observable, Memory) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				batchId : batchId
			}
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				data : data,
				idProperty : "itemId"
			}));
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn() {
	return [ {
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
		label : '系统库存',
		field : 'theoryCount',
		className : 'grid-number'
	}, {
		label : '盘盈',
		field : 'diffCount',
		className : 'grid-number',
		formatter : function(value, rowData) {
			return rowData.checkCount - rowData.theoryCount;
		}
	}, {
		label : "盘点状态",
		field : "itemStatus",
		formatter : function(value, rowData) {
			if (rowData.itemStatus == 'LEAK') {
				return '漏盘';
			}
			return '';
		}
	} ];
}

function doSave() {
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/restaurant/checkstorage/create/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("提交成功！");
				closeTab(tabId);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
