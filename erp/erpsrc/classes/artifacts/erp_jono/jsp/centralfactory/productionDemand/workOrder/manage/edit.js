dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
	
	//进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid",
	          "dojo/_base/declare",
	          "dgrid/Keyboard",
	          "dojo/domReady!" 
	        ], function(OnDemandGrid, declare, Keyboard) {
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
	var _url = appRoot
			+ "/centralfactory/productionDemand/workOrder/audit/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ],
			function(xhr, Observable, Memory) {
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
	return [{
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
	}];
}

function doValidate() {
	var productionTime = dojo.byId('productionTime').value;
	if (productionTime.trim() == '') {
		alert("制程日期不能为空！");
		return false;
	}
	return true;
}

function doSubmit() {
	if (!doValidate()) {
		return;
	}
	
	var _url = appRoot + "/cf/workorder/manage/saveStep.action";
	
	_url = getUrl(_url);
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("操作成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}


function doClose() {
	closeTab(tabId, "doQuery");
}

