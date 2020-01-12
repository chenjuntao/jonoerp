require([ "dojo/ready" ], function(ready) {
	ready(function() {
		initGlobal();
		addEvent();
		loadArrivePeriod();
		initGrid();
	});
});

/**
 * 在grid中增加原料记录的函数
 */
var addData = null;

addData = function(data) {
	for (var i = 0, len = data.length; i < len; i++) {
		data[i].rownumber = i + 1;
		dataStore.put(data[i]);
	}
}

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		dom.byId('regionId').onchange = changeRegionId;
	});
}

// 导入原料
function importMaterial() {
	var fileurl = document.getElementById("fileurl").value;
	if (fileurl == "") {
		alert("请选择文件！");
		return;
	}

	hide();

	var _url = appRoot + "/restaurant/goodsbill/template/doImport.action";
	_url = getUrl(_url);

	require([ "dojo/io/iframe", "dojox/widget/Standby" ], function(ioIframe, Standby) {
		standby = new Standby({
			target : "templateForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
		standby.show();

		ioIframe.send({
			form : "uploadForm",
			url : _url,
			handleAs : "json"
		}).then(function(data) {
			reloadData(data.existLst);
			standby.hide();
		}, function(err) {
			standby.hide();
			alert(err);
		});
	});
}

function reloadData(data) {
	// 首先清空原料数据
	dataStore.setData([]);
	grid.set('store', dataStore);
	addData(data);
}

/**
 * 初始化全局函数
 */
function initGlobal() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		window.getRegionId = function() {// 用于传递到子窗口使用
			return dom.byId("regionId").value;
		};
		window.getArrivePeriod = function() {
			return dom.byId("arrivePeriod").value;
		};
	});
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

function changeRegionId() {
	loadData([]);// 清空表格

	loadArrivePeriod();
}

function loadArrivePeriod() {
	var _url = appRoot + "/common/queryDeliveryCycle.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom" ], function(xhr, dom) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				'regionId' : dom.byId('regionId').value
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				dom.byId("arrivePeriod").value = data.dCycle;
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dojo/parser",
			"dojo/store/Observable", "dijit/Dialog", "dojo/domReady!" ], function(OnDemandGrid, Memory, declare,
			Keyboard, parser, Observable) {
		parser.parse();

		dataStore = new Observable(new Memory({
			// data : gridData
			idProperty : "itemId",
			data : []
		}));

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
	require([ "dojo/store/Memory", "dojo/_base/array" ], function(Memory, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
		});

		dataStore.setData(data);
		grid.set("store", dataStore);
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
		label : "主库位",
		field : "shelfName",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var selectedRows = [];
var materialDlg = null;
function selMaterial() {
	if (!validateNum('arrivePeriod', '最大到货周期', true)) {
		return false;
	}

	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterial/mainView.action";
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

	require([ "dojo/request/xhr", "dojo/dom", "dojo/dom-form" ], function(xhr, dom, domForm) {
		if (!validateNum('arrivePeriod', '到货周期', true)) {
			return false;
		}

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

		var savedata = {
			isSord : dom.byId('isSord').checked,
			'templateMeta.templateName' : dom.byId('templateName').value,
			'templateMeta.templateType' : dom.byId('templateType').value,
			'templateMeta.branchId' : dom.byId('branchId').value,
			'templateMeta.categoryId' : dom.byId('categoryId').value,
			'templateMeta.deliveryRegion' : dom.byId('regionId').value,
			'templateMeta.arrivePeriod' : dom.byId('arrivePeriod').value,
			jsonData : JSON.stringify(rows)
		};

		xhr.post(_url, {
			handleAs : "json",
			data : savedata
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("生成模板号号为：" + data.templateId + "，生成成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

function doClose() {
	closeTab(tabId, 'doQuery');
}