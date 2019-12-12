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
			+ "/centralfactory/productionDemand/orderSummary/audit/queryDetail.action?formId=" + formId;
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
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "商品编码",
		field : "itemId"
	}, {
		label : "商品名称",
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
		label : "数量",
		field : "itemCount"
	}, {
		label : "标准单价",
		field : "itemUnitPrice"
	}, {
		label : "标准金额",
		field : "payAmt"
	}, {
		label : "订货部门",
		field : "requester"
	}, {
		label : "供应商",
		field : "provider"
	}, {
		label : "",
		field : "none"
	}	];
}

function doValidate() {
	var auditTime = dojo.byId('auditTime').value;
	if (auditTime.trim() == '') {
		alert("审核日期不能为空！");
		return false;
	}
	return true;
}

function doAudit(row) {
	if (!doValidate()) {
		return;
	}
	
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	
	var _url = appRoot + "/centralfactory/productionDemand/orderSummary/audit/doAudit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("审核成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}


function doClose() {
	closeTab(tabId, 'doQuery');
}

