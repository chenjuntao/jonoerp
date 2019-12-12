require([ "dojo", "dojo/ready",
          "dojo/parser",
          "dijit/form/Select",
		  "dijit/form/Button",
          "dijit/Dialog" ], function(dojo, ready,parser) {
	ready(function() {
		parser.parse();
		addEvent();
		doQuery('init');
	});
});

function addEvent() {
}

function showDialog(){
	if(isEmpty(grid)){
		alert("请先选择一条单据！");
		return;
	}
	
	dijit.byId('customDialog').show();
}

function hideDialog(){
	dijit.byId('customDialog').hide();
}

function customExport(){
	fillData(grid.get('columns'),grid.get('store').data);
}

function customPrint(){
	prn1_preview(grid.get('columns'),grid.get('store').data);
}

function customPrintDesign(){
	myDesign(grid.get('columns'),grid.get('store').data);
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
	var _url = appRoot + "/lc/stock/packing/manage/queryTree.action?startDate="+startDate+"&endDate="+endDate;
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

function queryDetail(_formId) {
	var _url = appRoot + "/lc/stock/packing/manage/queryById.action?formId=" + _formId;
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

function loadData(data) {
	var header = data.header;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_formTimeActual').innerHTML = header.formTimeActual;
	dojo.byId('l_formNote').innerHTML = header.formNote;
	
	initGrid(data);
}

var grid = null;
var dataStore = null;
function initGrid(_data) {
	require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector", "dojo/_base/declare", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", "dgrid/extensions/ColumnResizer",
			"dgrid/ColumnSet","dojo/domReady!" ], function(
			OnDemandGrid, Selection, selector, declare, Server, Observable, Cache, Memory,ColumnResizer,ColumnSet) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : _data.detailLst
		}));

		if (grid != null) {
			grid.set("store", dataStore);
			return;
		}

		var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer ,ColumnSet]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [
			[ [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "箱子编号",
		field : "boxName",
		sortable:false
	}, {
		label : "门店",
		field : "branchId",
		sortable:false
	} ] ], [ [  {
		label : "原料编码",
		field : "itemId",
		sortable:false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable:false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable:false
	}, {
		label : "包装单位",
		field : "itemDimension",
		sortable:false
	}, {
		label : "单位体积",
		field : "unitVolume",
		className : 'grid-number',
		sortable:false
	}, {
		label : "单位重量",
		field : "unitWeight",
		className : 'grid-number',
		sortable:false
	}, {
		label : "数量",
		field : "itemCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "体积",
		field : "volume",
		className : 'grid-number',
		sortable:false
	}, {
		label : "重量",
		field : "weight",
		className : 'grid-number',
		sortable:false
	}] ] ];
}

function checkStatus(_formId) {
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
