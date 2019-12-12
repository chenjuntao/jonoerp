dojo.ready(function() {
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

function getTreeData() {
	var _url = appRoot + "/lc/request/purchase/loadTree.action?branchId=" + dojo.byId('branchId').value;
	_url = getUrl(_url);
		
	var treeStore = null;
	require([ "dojo/_base/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.get({
			url : _url,
			handleAs : "json",
			sync : 'true',
			load : function(data) {
				treeStore = new Memory({
					data : data,
					idProperty : 'id'
				});
			},
			error : function(error) {
				alert("load error");
			}
		});
	});
	return treeStore;
}

function doQuery() {
	treeModel.set('store', getTreeData());
	billTree.set('model', treeModel);
	refreshTree();
}

function refreshTree() {
	billTree.dndController.selectNone(); // As per the answer below
	// Credit to this discussion:
	// http://mail.dojotoolkit.org/pipermail/dojo-interest/2010-April/045180.html
	// Close the store (So that the store will do a new fetch()).
	console.log(billTree.model);
	billTree.model.store.clearOnClose = true;
	// billTree.model.store.close();

	// Completely delete every node from the dijit.Tree
	billTree._itemNodesMap = {};
	billTree.rootNode.state = "UNCHECKED";
	billTree.model.root.children = null;

	// Destroy the widget
	billTree.rootNode.destroyRecursive();

	// Recreate the model, (with the model again)
	billTree.model.constructor(billTree.model)

	// Rebuild the tree
	billTree.postMixInProperties();
	billTree._load();
}

var tableData;

function queryDetail(_formId) {
	if (grid == null) {
		initGrid();
	}
	var _url = appRoot + "/restaurant/putinstorage/create/queryBill.action?formId=" + _formId;
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
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dojo/domReady!" ],
			function(OnDemandGrid, Memory, declare, Keyboard) {
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
	var header = data.header;
	dojo.byId('formId').value = header.formId;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_provider').innerHTML = header.provider;
	dojo.byId('l_receiveTime').innerHTML = header.receiveTime;
	dojo.byId('l_requester').innerHTML = header.requester;
//	dojo.byId('l_receiver').innerHTML = header.receiver;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;

	var deliveryType = header.deliveryType;
	var dType = '统配';
	if (deliveryType == 'DIRECT') {
		dType = '直配';
	} else if (deliveryType == 'CROSS') {
		dType = '越库';
	}
	dojo.byId('l_deliveryType').innerHTML = dType;

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
		label : "收货部门",
		field : "receiver"
	}, {
		label : "订货量",
		field : "itemCount",
		className : 'grid-number'
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number'
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number'
	}, {
		label : "原料有效期",
		field : "expiredTime"
	}
	// , {
	// label : "",
	// field : "none"
	// }
	];
}

function doPrint() {
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已结案') {
		alert("单据已结案！")
		return false;
	} else if (status == '已审核') {
		alert("单据不能入库！")
		return false;
	}
	return true;
}

function putinStorage() {
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择采购单！');
		return;
	}
	if (!checkStatus(formId)) {
		return;
	}
	var _url = appRoot + '/restaurant/putinstorage/create/editView.action?formId=' + formId;
	_url = getUrl(_url);
	
	var _title = '采购单入库' + formId;
	addTab(_title, _url);
}
