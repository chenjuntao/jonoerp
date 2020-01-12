require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		loadBatch();
		addEvent();
		initGrid();
	});
});

function addEvent() {
	require([ "dojo/query", "dojo/dom" ], function(query, dom) {
		dom.byId('branchId').onchange = loadBatch;
		dom.byId('checkBatchId').onchange = setTip;
		dom.byId('btn_delete').onclick = deleteBatch;
		dom.byId('btn_submit').onclick = doSubmit;
	});
}

function loadBatch() {
	var _url = appRoot + "/restaurant/checkstorage/lock/queryBatch.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId('branchId').value
			}
		}).then(function(data) {
			loadSelect('checkBatchId', data, '', 'batchId', 'batchId', setTip, [ 'itemRepeatable' ]);
		}, function(err) {
		});
	});
}

function deleteBatch() {
	var _url = appRoot + "/restaurant/checkstorage/manage/deleteBatch.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom" ], function(xhr, dom) {
		var $batch = dom.byId('checkBatchId');
		if ($batch.value == '') {// 判断非空
			return;
		}
		if (!confirm("确定删除当前批次吗？")) {
			return;
		}
		xhr.post(_url, {
			handleAs : "json",
			data : {
				batchId : $batch.value
			}
		}).then(function(data) {
			$batch.remove($batch.selectedIndex);
			alert('删除成功！');
		}, function(err) {
		});
	});
}

var repeatable = false;
function setTip() {
	require([ "dojo/dom", "dojo/dom-attr" ], function(dom, domAttr) {
		var node = dom.byId("checkBatchId");
		if (node.options.length <= 0) {// 判断非空
			dom.byId('itemRepeatable').innerHTML = "";
			return;
		}
		var itemRepeatable = domAttr.get(node.options[node.selectedIndex], "itemRepeatable");
		if (itemRepeatable == "on") {
			dom.byId('itemRepeatable').innerHTML = "";
			repeatable = true;
		} else {
			dom.byId('itemRepeatable').innerHTML = "当前批次的多个盘点清单之间不允许出现重复原料";
			repeatable = false;
		}
	});
}

function doSubmit() {

	require([ "dojo/dom", "dojo/request/xhr" ], function(dom, xhr) {
		var batchId = dom.byId('checkBatchId').value;
		if (batchId == '') {
			return;
		}
		var _url = appRoot + "/restaurant/checkstorage/lock/checkFinish.action?checkBatchId=" + batchId
				+ "&parentTabId=" + tabId;
		_url = getUrl(_url);
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.finished) {
				alert('当前批次已盘点结束!');
			} else {
				doValidate();
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
	return true;
}

function doValidate() {
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}
	if (repeatable) {
		addList();
		data = null;
		loadData(data);
		return;
	}
	var _url = appRoot + "/restaurant/checkstorage/imanage/checkRepeat.action?batchId="
			+ dojo.byId('checkBatchId').value;
	_url = getUrl(_url);

	require([ "dojo/_base/array", "dojo/request/xhr" ], function(array, xhr) {
		var ids = [];
		array.forEach(rows, function(row, i) {
			ids.push(row.itemId);
		});
		xhr.post(_url, {
			handleAs : "json",
			data : {
				itemIds : ids.join()
			}
		}).then(function(data) {
			if (data.existItem != "") {
				alert("原料" + data.existItem + "已经在其它清单中存在!");
			} else {
				addList();
			}
			data = null;
			loadData(data);
		}, function(err) {
		});
	});
}

/**
 * 增加清单
 */
function addList() {
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var $branchId = dojo.byId('branchId');
	dojo.byId('checkBranch').value = $branchId.options[$branchId.selectedIndex].text;

	var _url = appRoot + '/restaurant/checkstorage/icreate/checkView.action';
	_url = getUrl(_url);

	addPostTab('billForm', '餐厅盘点清单生成确认', _url);
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dgrid/editor", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Memory,
			declare, query, Keyboard, editor, ColumnResizer) {
		dataStore = new Memory({
			// data : gridData
			data : []
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor),
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");
		grid.startup();
	});
}

function loadData(data) {
	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array" ], function(Observable, Memory, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
		});
		dataStore = new Observable(new Memory({
			data : data,
			idProperty : "itemId"
		}));
		grid.set("store", dataStore);
	});
}

function getColumn(editor) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : true
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "原料有效期",
		field : "expiredTime",
		className : 'text-center',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var materialDlg = null;
function selMaterial() {
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/restaurant/selmaterial/mainView.action";
		_url = getUrl(_url);

		var option = {
			title : "自选原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			materialDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.loadData();
		materialDlg.show();
	}
}

function getBranchId() {
	return dojo.byId('branchId').value;
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}

var pickModelDlg = null;
function pickModel() {
	if (pickModelDlg == null) {
		var _url = appRoot + "/restaurant/common/template/pickModelView.action?templateType=checkstorage";
		_url = getUrl(_url);

		var option = {
			title : "使用模板",
			url : _url,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			pickModelDlg = iDlg;
		});
	} else {
		pickModelDlg.show();
	}
}

function closePickModelDlg(data) {
	loadData(data);
	pickModelDlg.hide();
}

var saveModelDlg = null;
function saveAsModel() {
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}
	if (saveModelDlg == null) {
		var _url = appRoot + "/restaurant/common/template/saveModelView.action?templateType=checkstorage";
		_url = getUrl(_url);

		var option = {
			title : "存为模板",
			url : _url,
			frameId : 'saveAsModel',
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			saveModelDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId("saveAsModel").contentWindow;
		ifrWindow.loadData()
		saveModelDlg.show();
	}
}

/**
 * 获取主界面的数据，传递到子窗口保存为模板
 */
function getSaveModelInfo() {
	var rows = dataStore.query();
	var $shopId = dojo.byId('branchId');
	var result = {
		items : rows,
		branchId : $shopId.value,
		branchName : $shopId.options[$shopId.selectedIndex].text
	};
	return result;
}

function closeSaveModelDlg(data) {
	saveModelDlg.hide();
}

function doClose() {
	closeTab(tabId);
}
