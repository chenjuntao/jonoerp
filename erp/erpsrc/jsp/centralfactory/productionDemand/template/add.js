require([ "dojo/ready" ], function(ready) {
	ready(function() {
		initGlobal();
		addEvent();
		initGrid();
	});
});

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
	});
}

/**
 * 初始化全局函数
 */
function initGlobal() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		window.getRegionId = function() {// 用于传递到子窗口使用
			return dom.byId("regionId").value;
		};
		window.getDeliveryType = function() {
			return dom.byId("deliveryType").value;
		};
	});
}

function loadArrivePeriod() {
	var _url = appRoot + "/common/queryArrivePeriod.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "dojo/request/xhr" ], function(dom, xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				deliveryType : dom.byId("deliveryType").value
			}
		}).then(function(data) {
			loadSelData('arrivePeriod', data);
		}, function(err) {
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dojo/domReady!" ], function(OnDemandGrid,
			Memory, declare, Keyboard) {
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
	});
}

function loadData(data) {
	selectedRows = data;
	require([ "dojo/store/Memory", "dojo/_base/array" ],
			function(Memory, array) {
				array.forEach(data, function(row, i) {
					row.rownumber = i + 1;
				});
				dataStore = new Memory({
					data : data
				});
				grid.set("store", dataStore);
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
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : "",
		field : "none"
	} ];
}

var selectedRows = [];
var materialDlg = null;
function selMaterial() {
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot
				+ "/restaurant/selmaterial/mainView.action";
		_url = getUrl(_url);
		
		var option = {
			title : "自选原料",
			frameId : frameId,
			url : _url,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			materialDlg = iDlg;
		});
	} else {
		require([ "dojo/dom" ], function(dom) {
			var ifrWindow = dom.byId(frameId).contentWindow;
			ifrWindow.loadData();
			materialDlg.show();
		});
	}
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}

function doSave() {
	var _url = appRoot + "/restaurant/common/template/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom" ], function(xhr, dom) {
		var templateName = dom.byId('templateName').value;
		if (templateName.trim() == '') {
			alert("模板名称不能为空！");
			return;
		}

		var rows = dataStore.query();
		if (rows.length == 0) {
			alert("请选择原料！");
			return;
		}
		xhr.post(_url, {
			handleAs : "json",
			data : {
				'templateMeta.branchId' : cfCode,// 制作模板的部门
				'templateMeta.templateName' : dom.byId('templateName').value,
				'templateMeta.templateType' : templateType,
				jsonData : JSON.stringify(rows)
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("保存成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId);
}