dojo.ready(function() {
	dojo.byId('formId').value = '';// clear cache
	addEvent();
	initTree();
});

function addEvent() {
}

var treeModel = null;
var billTree = null;
function initTree() {
	require([ "dijit/Tree", "cbtree/model/TreeStoreModel", "dojo/domReady!" ], function(Tree, TreeStoreModel) {
		// Create the model
		treeModel = new TreeStoreModel({
			store : getTreeData(),
			query : {
				id : 'root'
			},
			mayHaveChildren : function(item) {
				if (item.parent != 'root') {
					return false;
				}
				return true;
			}
		});

		billTree = new Tree({
			model : treeModel,
			showRoot : false,
			openOnClick : true,
			onClick : function(item, node, evt) {
				if (item.parent != 'root') {
					queryDetail(item.id);
				}
			},
			checkBoxes : false
		}, "billTree");

		billTree.startup();
	});
}

/**
 * 缓存部门编号，保证查明细时与单据编号保持一致
 */
var g_branchId = null;
function getTreeData() {
	g_branchId = dojo.byId('branchId').value;
	var g_itemName = dojo.byId('itemName').value.trim();
	var _url = appRoot + "/restaurant/putinstorage/create/loadTree.action";
	_url = getUrl(_url);
	
	var treeStore = null;
	require([ "dojo/request/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.post(_url, {
			handleAs : 'json',
			data : {
				branchId:g_branchId,
				itemName:g_itemName},
			sync : 'true',
		}).then(function(data) {
			treeStore = new Memory({
				data : data,
				idProperty : 'id'
			});
		}, function(err) {
			alert("load error");
		});

	});
	return treeStore;
}

function doQuery() {
	treeModel.set('store', getTreeData());
	billTree.set('model', treeModel);
	refreshTree(billTree);
}

var tableData;

function queryDetail(_formId) {
	if (grid == null) {
		initGrid();
	}
	var _url = appRoot + "/lc/request/purchase/manage/queryBill.action?formId=" + _formId;
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
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/domReady!" ],

	function(OnDemandGrid, Memory, declare, Keyboard, ColumnResizer) {
		dataStore = new Memory({
			data : [],
			idProperty : 'itemId'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.startup();
	});
}

var printHeader;

function loadData(data) {
	var header = data.header;
	printHeader = header;

	dojo.byId('formId').value = header.formId;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_providerId').innerHTML = '['+header.providerId+']'+header.provider;
	dojo.byId('supplierId').value = header.providerId;
	dojo.byId('l_receiveTime').innerHTML = header.receiveTime;
	dojo.byId('l_receiver').innerHTML = header.receiver;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;

	require([ "dojo/store/Memory" ], function(Memory) {
		dataStore = new Memory({
			data : data.detailLst,
			idProperty : "itemId"
		});
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
		label : "订货量",
		field : "itemCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准单价",// 餐厅
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "原料有效期",
		field : "expiredTime",
		sortable : false
	} ];
}

function doPrint() {
}

/**
 * 保留验证单据状态方法
 */
function checkStatus(_formId) {
	var _url = appRoot + "/restaurant/putinstorage/create/checkStatus.action?formId=" + _formId;
	_url = getUrl(_url);
	
	var isInAudit = false;
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.get(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			isInAudit = data.isInAudit;
		}, function(err) {
		});
	});
	if (isInAudit == true) {
		alert("采购单对应的入库单正在审核中！");
		return false;
	}

	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已结案') {
		alert("单据已结案！");
		return false;
	} else if (status == '未审核') {
		alert("单据不能入库！");
		return false;
	}
	return true;
}

function putinStorage() {
	var formId = dojo.byId('formId').value;
	var supplierId = dojo.byId('supplierId').value;
	if (formId == '') {
		alert('请选择采购单！');
		return;
	}
	if (!checkStatus(formId)) {
		return;
	}
	
	var _url = appRoot + '/restaurant/putinstorage/create/editView.action?formId=' + formId +"&supplierId=" + supplierId
	+ "&branchId=" + g_branchId;
	_url = getUrl(_url);
	
	var _title = '采购单入库-' + formId;
	addTab(_title, _url);
}

function doFinish() {
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择采购单！');
		return;
	}

	if (!checkStatus(formId)) {
		return;
	}

	if (!confirm("此操作系统不自动入库，是否确认结束单据？")) {
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
