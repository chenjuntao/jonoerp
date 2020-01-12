require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		dojo.byId('formId').value = '';// clear cache
		addEvent();
		initGrid();
		doQuery('init');
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
							queryDetail(item.id);
						}
					},
					checkBoxes : false
				}, "billTree");

				billTree.startup();
			});
}

function doQuery(_param) {
	var startDate = dojo.byId("startDate").value;
	var endDate = dojo.byId("endDate").value;
	var g_itemName = dojo.byId('itemName').value.trim();
	var _url = appRoot + "/cf/inventory/in/create/loadTreeCF.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.post(_url, {
			handleAs : 'json',
			data : {
				startDate:startDate,
				endDate : endDate,
				itemName:g_itemName},
			sync : 'true',
		}).then(function(data) {
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
		
		}, function(err) {
			alert("load error");
		});

	});
	
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
					idProperty : "rownumber",
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
	dojo.byId('deliveryType').value = header.deliveryType;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_provider').innerHTML = "["+header.providerId+"]"+header.provider;
	dojo.byId('supplierId').value = header.providerId;
	dojo.byId('l_receiveTime').innerHTML = header.receiveTime;
	dojo.byId('l_requester').innerHTML = header.requester;
	dojo.byId('l_receiveAddress').innerHTML = header.receiveAddress;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;
	dojo.byId('l_tager').innerHTML = header.tager;
	require([ "dojo/store/Memory" ], function(Memory) {
		dataStore.setData(data.detailLst);
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
		label : "进货单价",
		field : "receivePrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "进货金额",
		field : "receiveAmt",
		className : 'grid-number',
		sortable : false
	} ];
}

function doPrint() {
}

// function checkStatus(_formId) {
// var data = getCurrentStatus(_formId);
// var status = data.formStatus;
// if (status == '已结案') {
// alert("单据已结案！");
// return false;
// } else if (status == '未审核') {
// alert("单据不能入库！");
// return false;
// }
// return true;
// }

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
	var supplierId = dojo.byId('supplierId').value;
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择采购单！');
		return;
	}
	if (!checkStatus(formId)) {
		return;
	}
	var deliveryType = dojo.byId('deliveryType').value;
	var _url = appRoot + '/cf/inventory/in/create/createView.action?formId=' + formId + "&supplierId=" + supplierId
			+ "&deliveryType=" + deliveryType + "&branchId=200";
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

	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var deliveryType = dojo.byId('deliveryType').value;
	var _url = appRoot + "/restaurant/putinstorage/create/doFinish.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
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
