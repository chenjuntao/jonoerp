dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(formId);
	};
}

var dataStore = null;
var grid = null;
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard", "dojo/domReady!" ], function(OnDemandGrid,
			declare, Keyboard) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "auditDataGrid");

		grid.startup();
		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/centralfactory/productionDemand/workOrder/audit/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "itemId",
				data : data
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
		label : "制程",
		field : "productionName"
	}, {
		label : "日期",
		field : "productionTime"
	}, {
		label : "产量",
		field : "productionCount"
	}, {
		label : "生产人员",
		field : "productionMan"
	}, {
		label : "备注",
		field : "productionNote"
	}, {
		label : "",
		field : "none"
	} ];
}

function doValidate() {
	if (!validateNum('actualCount', '产出数量', true)) {
		return false;
	}
	return true;
}

function putinStorage() {
	dojo.byId('putinBtn').disabled = true;
	
	var _url = appRoot + "/cf/production/output/putinStorage.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("入库单生成成功！");
			} else {
				alert("入库单生成失败！");
			}
		}, function(err) {
		});
	});
}

function doReturn() {
	var _url = appRoot + "/cf/production/output/returnView.action?formId=" + formId
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	addTab("退料", _url);
}
