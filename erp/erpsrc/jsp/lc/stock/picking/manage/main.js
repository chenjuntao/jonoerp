require([ "dojo", "dojo/ready", "dojo/parser", "dijit/form/Select", "dijit/form/Button", "dijit/Dialog" ], function(
		dojo, ready, parser) {
	ready(function() {
		parser.parse();

		addEvent();
		doQuery('init');
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
	fillData(cols, grid.get('store').data);
}

function customPreview() {
	prn1_preview(grid.get('columns'), grid.get('store').data);
}

function customPrint() {
	prn1_print(grid.get('columns'), grid.get('store').data);
}

function customPrintDesign() {
	myDesign(grid.get('columns'), grid.get('store').data);
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
						status : [ "audit", "unaudit" ]
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
	var _url = appRoot + "/lc/stock/picking/manage/queryTree.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.post(_url, {
			handleAs : 'json',
			data : {
				branchId : -1,
				startDate : startDate,
				endDate : endDate,
				itemName : g_itemName
			},
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

var status;

function queryDetail(_formId) {
	var _url = appRoot + "/lc/stock/picking/manage/queryById.action?formId=" + _formId;
	_url = getUrl(_url);
	
	require([ "dojo/_base/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				status = data.status;
				setStatus(status);
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

function loadData(data) {
	var header = data.header;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('formId').value = header.formId;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_formTimeActual').innerHTML = header.formTimeActual;
	dojo.byId('l_auditor').innerHTML = header.auditor;
	dojo.byId('l_auditTime').innerHTML = header.auditTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;

	initGrid(data);
}
var colSum = [ {
	label : "物流主仓库存",
	field : "storageCount",
	className : 'grid-number',
	sortable : false
}, {
	label : "小计",
	field : "sumCount",
	className : 'grid-number',
	sortable : false
}, {
	label : "库存",
	field : "extra1",
	className : 'grid-number',
	hidden : true,
	sortable : false
}, {
	label : "日期",
	field : "extra2",
	hidden : true,
	className : 'grid-number',
	sortable : false
} ];

var grid = null;
var dataStore = null;
var cols = null;
var shelfName = null;

function initGrid(_data) {
	var branchCols = _data.branchCols;

	for (var i = 0, length = branchCols.length; i < length; i++) {
		branchCols[i]["sortable"] = false;
		branchCols[i]["label"] = branchCols[i].label;
	}

	cols = getColumn().concat(branchCols);
	cols = cols.concat(colSum);
	require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector", "dojo/_base/declare", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", "dojo/_base/array",
			'dgrid/extensions/ColumnHider' ], function(OnDemandGrid, Selection, selector, declare, Server, Observable,
			Cache, Memory, array, ColumnHider) {
		if (grid == null) {
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : []
			}));
			var CustomGrid = declare([ OnDemandGrid, Selection, ColumnHider ]);
			grid = new CustomGrid({
				store : dataStore,
				columns : cols,
				cellNavigation : false,
				loadingMessage : '加载中...'
			}, "dataGrid");

			grid.startup();
		} else {
			grid.set("columns", cols);// 刷新列定义
			dataStore.setData([]);
			grid.set("store", dataStore);
		}

		array.forEach(_data.itemLst, function(row, i) {
			dataStore.put(row);
		});

		var myData = dataStore.data;
		for (var i = 0, length = myData.length; i < length; i++) {
			shelfName = myData[i].shelfName;
		}
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "库位",
		field : "shelfName",
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
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	} ];
}

function setStatus(_status) {
	dojo.byId('statusSign').innerHTML = _status;
	return true;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已结案') {
		alert("单据已结案！");
		return false;
	} else if (status == '已审核') {
		alert("单据已审核！");
		return false;
	} else if (status == '已删除') {
		alert("单据已删除！")
		return false;
	} else if (status == '已作废') {
		alert("单据已作废！")
		return false;
	}
	return true;
}

function manage() {
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择捡货单！');
		return;
	}
	if (!checkStatus(formId)) {
		return;
	}

	var _url = appRoot + '/lc/stock/picking/manage/editView.action?formId=' + formId + "&parentTabId=" + tabId;
	var _title = '管理捡货单单-' + formId;
	_url = getUrl(_url);
	
	addTab(_title, _url);
}
