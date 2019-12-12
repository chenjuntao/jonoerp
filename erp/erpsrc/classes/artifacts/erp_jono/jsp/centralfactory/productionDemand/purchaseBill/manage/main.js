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

function customPrintDesign() {
	myDesign(grid.get('columns'), grid.get('store').data);
}

function customPreview() {
	var status = dojo.byId('l_status').innerHTML;
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
			prn1_preview(grid.get('columns'), grid.get('store').data);
		}, function(err) {
		});
	});
}

function customPrint() {
	var status = dojo.byId('l_status').innerHTML;
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
			prn1_print(grid.get('columns'), grid.get('store').data);
		}, function(err) {
		});
	});
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
		"cbtree/model/StoreModel-EXT", "cbtree/extensions/TreeStyling", ], function(xhr, array, Memory, Tree,
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
				if (!checkFormScan(item)) {
					return;
				}

				queryDetail(item.id, item.type);
				// setStatus(item.status);
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
		var _url = appRoot + "/lc/request/purchase/manage/loadTree.action?branchType=CENTRALFACTORY&startDate="
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

function queryDetail(_formId, _deliveryType) {
	if (grid == null) {
		initGrid();
	}
	var _url = appRoot + "/lc/request/purchase/manage/queryBill.action?formId=" + _formId;
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
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_provider').innerHTML = "[" + header.providerId + "]" + header.provider;
	dojo.byId('l_receiveTime').innerHTML = header.receiveTime;
	dojo.byId('l_requester').innerHTML = header.requester;
	dojo.byId('l_times').innerHTML = header.times;
	// dojo.byId('l_receiver').innerHTML = header.receiver;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_formTimeActual').innerHTML = header.formTimeActual;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;
	dojo.byId('l_status').innerHTML = header.status;
	dataStore.setData([]);
	grid.set("store", dataStore);
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data.detailLst, function(row, i) {
			dataStore.put(row);
		});
	});
}

var directCols = [ {
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
	label : "单位",
	field : "itemDimension",
	sortable : false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable : false
}, {
	label : "订货量",
	field : "itemCount",
	className : 'grid-number',
	sortable : false
}, {
	label : "标准单价",
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

function doPrint() {
}

/**
 * 如果该单据被删除、作废、审核以及结案，那么则不允许用户进行下一步操作了
 */
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

	// 按通配处理
	var deliveryType = "UNIFIED";
	var _url = appRoot + '/lc/request/purchase/manage/editView.action?formId=' + formId + "&deliveryType="
			+ deliveryType + "&parentId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '管理采购单-' + formId;
	addTab(_title, _url);
}

function scanRef(_formId, _itemId) {
	var _url = appRoot + "/restaurant/goodsbill/query/scanView.action?formId=" + _formId;
	_url = getUrl(_url);
	
	addTab("查看要货单-" + _formId, _url);
}
