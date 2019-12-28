require([ "dojo", "dojo/_base/array", "dojo/ready" ], function(dojo, array, ready) {
	ready(function() {
		dojo.byId('formId').value = '';// clear cache
		addEvent();
		initGrid();
		doQuery('init');

		window.updateColumns = function(hiddenColumns) {
			var gridCoulmns = grid.get('columns');

			array.forEach(gridCoulmns, function(outItem) {
				array.forEach(hiddenColumns, function(inItem) {
					if (outItem.field == inItem) {
						outItem.hidden = true;
					} else {
						outItem.hidden = false;
					}
				});
			});

			grid.set('columns', gridCoulmns);
		}
	});
});

function addEvent() {
}

var treeModel = null;
var billTree = null;
function initTree(_treeStore) {
	require([ "cbtree/Tree", "cbtree/model/TreeStoreModel", "cbtree/extensions/TreeStyling", "dojo/domReady!" ],
			function(Tree, TreeStoreModel, TreeStyling) {
				// Create the model
				treeModel = new TreeStoreModel({
					store : _treeStore,
					query : {
						id : 'root'
					}
				});
				billTree = new Tree({
					model : treeModel,
					valueToIconMap : {
						type : [ "direct", "cross", "unified" ]
					},
					showRoot : false,
					openOnClick : true,
					onClick : function(item, node, evt) {
						if (item.parent != 'root') {
							if (!checkFormScan(item)) {
								return;
							}

							queryDetail(item.id, item.type);
						}
					},
					checkBoxes : false
				}, "billTree");

				billTree.startup();
			});
}

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

function doQuery(_param) {
	var startDate = dojo.byId("startDate").value;
	var endDate = dojo.byId("endDate").value;
	var _url = appRoot + "/lc/request/purchase/manage/loadTree.action?queryType=unaudit&branchType="
			+ dojo.byId('branchType').value + "&startDate=" + startDate + "&endDate=" + endDate;
	_url = getUrl(_url);

	require([ "dojo/_base/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				var treeStore = new Memory({
					data : data,
					idProperty : 'id'
				});

				if (_param == 'init') {
					initTree(treeStore);
					return;
				}

				treeModel.set('store', treeStore);
				billTree.set('model', treeModel);
				refreshTree(billTree);
			},
			error : function(error) {
				alert("load error");
			}
		});
	});
}

var tableData;

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
			"dgrid/Keyboard", "dgrid/extensions/ColumnHider", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ],
			function(OnDemandGrid, Observable, Memory, declare, Keyboard, ColumnHider, ColumnResizer) {
				dataStore = new Observable(new Memory({
					idProperty : "rownumber",
					data : []
				}));
				var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnHider, ColumnResizer ]);
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
	dojo.byId('deliveryType').value = header.deliveryType;
	dojo.byId('l_formId').innerHTML = header.formId;

	dojo.byId('l_formRefId').innerHTML = data.formRefId.length == 0 ? '' : data.formRefId;

	dojo.byId('l_provider').innerHTML = '[' + header.providerId + ']' + header.provider;
	dojo.byId('l_receiveTime').innerHTML = header.receiveTime;
	dojo.byId('l_requester').innerHTML = header.requester;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;

	var deliveryType = header.deliveryType;
	var dType = '直配';
	if (deliveryType == 'CROSS') {
		dType = '越库';
		updateColumns([ "receiveAddress", "receiver" ]);
	} else if (deliveryType == 'UNIFIED') {
		dType = '统配';
		updateColumns([ "receiver", "receiveAddress" ]);
	} else {
		updateColumns([ "receiver", "receiveAddress" ]);
	}

	// 兼容中央工厂采购单
	if (!deliveryType) {
		dType = "";
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
		id : 'receiver',
		label : "收货部门",
		field : "receiver",
		sortable : false
	}, {
		id : 'receiveAddress',
		label : "收货地址",
		field : "receiveAddress",
		sortable : false
	}, {
		label : "订货量",
		field : "itemCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "标准价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "进货价",
		field : "receivePrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "进货金额",
		field : "receiveAmt",
		className : 'text-right',
		sortable : false
	}
	// , {
	// label : "原料有效期",
	// field : "expiredTime",
	// sortable:false
	// }
	];
}

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

function doAudit() {
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择采购单！');
		return;
	}
	if (!checkStatus(formId)) {
		return;
	}

	var deliveryType = dojo.byId('deliveryType').value;
	var _url = appRoot + '/lc/request/purchase/audit/auditView.action?formId=' + formId + "&deliveryType="
			+ deliveryType + "&parentTabId=" + tabId;
	_url = getUrl(_url);

	var _title = '审核采购单-' + formId;
	addTab(_title, _url);
}

function batchAudit() {

}
