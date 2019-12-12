require([ "dojo", "dojo/ready", "dojo/parser", "dijit/form/Select", "dijit/form/Button", "dijit/Dialog" ], function(
		dojo, ready, parser) {
	ready(function() {
		parser.parse();

		initGrid();
		doQuery();
	});
});

function addEvent() {
}

function showDialog() {
	if (isEmpty(grid)) {
		alert("请先选择一条单据！");
		return;
	}

	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('store').data);
}

function customPreview() {
	var status = dojo.byId('l_status').value;
	if (status == "未审核") {
		alert("单据未审核，禁止打印！");
		return;
	}
	var times = dojo.byId('l_times').innerHTML;
	if (times.length == 0) {
		dojo.byId('l_times').innerHTML = '1';
	} else {
		times = parseInt(dojo.byId('l_times').innerHTML) + 1;
		dojo.byId('l_times').innerHTML = times;
	}
	var formId = dojo.byId('formId').value;
	var _url = appRoot + "/lc/request/purchase/manage/preview.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query", "dojo/_base/array" ], function(xhr, Memory, query,
			array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {

			prn1_preview(grid.get('columns'), grid.get('store').data);
		}, function(err) {
		});
	});
}

function customPrint() {
	var status = dojo.byId('l_status').value;
	if (status == "未审核") {
		alert("单据未审核，禁止打印！");
		return;
	}
	var times = dojo.byId('l_times').innerHTML;
	if (times.length == 0) {
		dojo.byId('l_times').innerHTML = '1';
	} else {
		times = parseInt(dojo.byId('l_times').innerHTML) + 1;
		dojo.byId('l_times').innerHTML = times;
	}
	var formId = dojo.byId('formId').value;
	var _url = appRoot + "/lc/request/purchase/manage/preview.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dojo/query" ], function(xhr, Memory, query) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			rows = grid.get('store').data;
			rows.sort(function(a, b) {
				return b.supplyCycle - a.supplyCycle;
			});
			var oldCycle = -1.0;
			var newRows = [];
			for (var i = 0; i < rows.length - 1; i++) {
				var row = rows[i];
				var nextRow = rows[i + 1];
				if (i == rows.length - 2) {
					nextRow.supplyCycle = 1000;
				}
				if (row.supplyCycle != oldCycle) {
					oldCycle = row.supplyCycle;
					newRows = [];
				}
				newRows.push(row);
				if (row.supplyCycle != nextRow.supplyCycle) {
					prn1_print(grid.get('columns'), newRows);
				}
			}
		}, function(err) {
		});
	});
}

function customPrintDesign() {
	myDesign(grid.get('columns'), grid.get('store').data);
}

var billTree = null, treeStore = null;
/**
 * function
 */
var doQuery = null;

var modelConfig = {
	store : null,
	query : {
		id : 'root'
	},
	rootLabel : '全部单据',
	mayHaveChildren : function(item) {
		if (item.parent != 'root') {
			return false;
		}
		return true;
	}
};

require([ "dojo/request/xhr", "dojo/_base/array", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
		"cbtree/model/StoreModel-EXT", "cbtree/extensions/TreeStyling" ], function(xhr, array, Memory, Tree,
		TreeStoreModel, StoreModelExt) {
	var statusArr = [ "audited", "unaudit", "settled", "stored", "aggregated", "distributed", "canceled" ];
	var statusTextArr = [ "已审核", "未审核", "已结案", "已入库", "已汇总", "已配送处理", "已作废" ];

	var treeConfig = {
		model : null,
		id : "billTree",
		checkBoxes : false,
		openOnClick : true,
		valueToIconMap : {
			status : statusArr
		},
		showRoot : false,
		onClick : function(item, node, evt) {
			if (item.parent != 'root') {
				bigFormId = item.id;
				
				if (!checkFormScan(item)) {
					return;
				}

				queryDetail(item.id, item.type);

				setStatus(item.status);
			}
		}
	};

	/**
	 * 若单据被删除，则不能查看
	 */
	function checkFormScan(item) {
		var data = getCurrentVersion(item.id);

		if (data.operationContent == "已删除") {
			alert(data.operationName + "在" + data.operationTime + data.operationContent);
			return false;
		}

		return true;
	}

	function setStatus(_status) {
		array.some(statusArr, function(fstatus, i) {
			if (fstatus == _status) {
				dojo.byId('l_status').value = statusTextArr[i];
				dojo.byId('statusSign').innerHTML = "<b style='border: 1px green solid;'>" + statusTextArr[i] + "</b>";
				return true;
			}
		});
	}

	treeStore = new Memory({
		data : []
	});

	buildTree = function() {
		if (billTree) {
			var treeModel = billTree.model;
			if (treeModel) {
				treeModel.destroy();
				delete billTree.model;
			}
			billTree.destroy();
			delete billTree;
		}

		treeConfig.model = new TreeStoreModel(modelConfig);
		billTree = new Tree(treeConfig);
		billTree.placeAt("treeWrapper");
		billTree.startup();
	}

	doQuery = function() {
		var startDate = dojo.byId("startDate").value;
		var endDate = dojo.byId("endDate").value;
		var branchType = dojo.byId('branchType').value;
		var _url = appRoot + "/lc/request/purchase/manage/loadTree.action?branchType=" + branchType + "&startDate="
				+ startDate + "&endDate=" + endDate;
		_url = getUrl(_url);
		
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			treeStore.setData(data);

			modelConfig['store'] = treeStore;
			buildTree();
		}, function(err) {
			errorHandler(err);
		});
	}
});

var tableData;
var status;
function queryDetail(_formId, _deliveryType) {
	if (grid == null) {
		initGrid();
	}
	var _url = appRoot + "/lc/request/purchase/manage/queryBill.action?formId=" + _formId + "&hasSum=Y";
	if (_deliveryType == 'direct') {
		_url = appRoot + "/lc/request/purchase/manage/queryDirect.action?formId=" + _formId;
	}
	_url = getUrl(_url);
	
	require([ "dojo/_base/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				if (tableData) {
					tableData.splice(0, tableData.length);
					tableData.concat(data.detailLst);
				} else {
					tableData = data.detailLst;
				}
				status = data.status;
				loadData(data);
			},
			error : function(error) {
				alert("load error");
			}
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dojo/domReady!" ], function(OnDemandGrid, Observable, Memory, declare, Keyboard) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : directCols,
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.startup();
	});
}

function loadData(data) {
	var header = data.header;
	dojo.byId('formId').value = header.formId;
	dojo.byId('deliveryType').value = header.deliveryType;

	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_provider').innerHTML = "[" + header.providerId + "]" + header.provider;
	dojo.byId('l_receiveTime').innerHTML = header.receiveTime;
	dojo.byId('l_requester').innerHTML = header.requester;

	dojo.byId('l_formRefId').innerHTML = data.formRefId.length == 0 ? '' : data.formRefId;

	// dojo.byId('l_receiver').innerHTML = header.receiver;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_formTimeActual').innerHTML = header.formTimeActual;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;
	dojo.byId('l_times').innerHTML = header.times;

	var deliveryType = header.deliveryType;
	var dType = '直配';
	// 动态更新列很容易出现列错位问题，暂时不用
	if (deliveryType == 'CROSS') {
		dType = '越库';
		// grid.set("columns", crossCols);// 刷新列定义
		// // hideCols([ "receiveAddress" ], [ "receiver" ]);
	} else if (deliveryType == 'UNIFIED') {
		dType = '统配';
		// grid.set("columns", unifiedCols);// 刷新列定义
		// // hideCols([ "receiver", "receiveAddress" ], []);
		// } else {
		// grid.set("columns", directCols);// 刷新列定义
		// // hideCols([], [ "receiver", "receiveAddress" ]);
	}
	dojo.byId('l_deliveryType').innerHTML = dType;

	dataStore.setData([]);
	grid.set("store", dataStore);
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data.detailLst, function(row, i) {
			dataStore.put(row);
		});
	});
}

function hideCols(hideArr, showArr) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(hideArr, function(columnId, i) {
			// to hide column with id="name"
			grid.styleColumn(columnId, "display: none;");
		});
		array.forEach(showArr, function(columnId, i) {
			// to show it
			grid.styleColumn(columnId, "display: table-cell;");
		});
	});
}

var directCols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "原料编码",
	field : "itemId"
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
	id : 'receiver',
	label : "收货部门",
	field : "receiver"
}, {
	id : 'receiveAddress',
	label : "收货地址",
	field : "receiveAddress",
	sortable : false
}, {
	label : "订货量",
	field : "itemCount",
	className : 'grid-number',
	sortable : false
}, {
	label : "标准价",
	field : "itemUnitPrice",
	className : 'grid-number',
	sortable : false
}, {
	label : "标准金额",
	field : "payAmt",
	className : 'grid-number',
	sortable : false
}, {
	label : "进货价",
	field : "receivePrice",
	className : 'grid-number',
	sortable : false
}, {
	label : "进货金额",
	field : "receiveAmt",
	className : 'grid-number',
	sortable : false
}, {
	label : "原料有效期",
	field : "expiredTime",
	sortable : false
} ];

var unifiedCols = directCols.slice();// 复制一份
unifiedCols.splice(7, 2);// 隐藏收货部门和收货地址

var crossCols = directCols.slice();
crossCols.splice(8, 1);// 隐藏收货地址

function doPrint() {
}

function checkStatus(_formId) {
	var data = getCurrentVersion(_formId);
	if (isInOperation(data.operationContent)) {
		alert(data.operationName + "在" + data.operationTime + data.operationContent);
		return false;
	}

	return true;
}

function manage() {
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择采购单！');
		return;
	}
	if (!checkStatus(formId)) {
		return;
	}

	var deliveryType = dojo.byId('deliveryType').value;
	var _url = appRoot + '/lc/request/purchase/manage/editView.action?formId=' + formId + "&deliveryType="
			+ deliveryType + "&parentId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '管理采购单-' + formId;
	addTab(_title, _url);
}

function doFinish() {
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择采购单！');
		return;
	}

	if (!checkNewStatus(formId)) {
		return;
	}

	if (!confirm("是否确认采购单结案？")) {
		return;
	}

	var _url = appRoot + "/restaurant/putinstorage/create/doFinish.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

function scanRef(_formId, _itemId) {
	var _url = appRoot + "/restaurant/goodsbill/query/scanView.action?formId=" + _formId;
	_url = getUrl(_url);
	
	addTab("查看要货单-" + _formId, _url);
}

/**
 * 保留验证单据状态方法
 */
function checkNewStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已结案') {
		alert("单据已结案！");
		return false;
	} else if (status == '未审核') {
		alert("单据未审核，不能结案！");
		return false;
	}
	return true;
}