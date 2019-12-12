require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		dojo.byId('formId').value = '';// clear cache
		addEvent();
		doQuery('init');
	});
});

function addEvent() {
}
var colSum = [ {
	label : "小计",
	field : "sumCount",
	className : 'grid-number',
	sortable : false
} ];

var treeModel = null;
var billTree = null;
var treeStore = null;
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

function test(){
	alert("test");
};

function doQuery(_param) {
	var startDate = dojo.byId("startDate").value;
	var endDate = dojo.byId("endDate").value;
	var _url = appRoot + "/lc/stock/picking/manage/queryTree.action?queryType=unaudit&startDate="+startDate+"&endDate="+endDate;
	_url = getUrl(_url);
	
	require([ "dojo/_base/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				treeStore = new Memory({
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
	var _url = appRoot + "/lc/stock/picking/manage/queryById.action?formId=" + _formId;
	_url = getUrl(_url);
	
	require([ "dojo/_base/xhr", "dojo/store/Memory" ], function(xhr, Memory) {
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
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
	dojo.byId('formId').value = header.formId;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_formNote').innerHTML = header.formNote;

	initGrid(data);
}

var grid = null;
var dataStore = null;
function initGrid(_data) {
	var branchCols = _data.branchCols;
	for(var i=0,length = _data.branchCols.length;i<length;i++){
		branchCols[i]["sortable"] = false;
	}
	
	var cols = getColumn().concat(branchCols);
	cols = cols.concat(colSum);
	require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector", "dojo/_base/declare", "custom/store/Server",
			"dojo/store/Observable", "dojo/store/Cache", "dojo/store/Memory", "dojo/_base/array" ], function(
			OnDemandGrid, Selection, selector, declare, Server, Observable, Cache, Memory, array) {
		if (grid == null) {
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : []
			}));
			
			var CustomGrid = declare([ OnDemandGrid, Selection ]);
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
	});
}

function getColumn() {
	return [{
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "库位",
		field : "shelfName",
		sortable:false
	}, {
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
		label : "单位",
		field : "itemDimension",
		sortable:false
	} ];
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

function doAudit() {
	var formId = dojo.byId('formId').value;
	if (formId == '') {
		alert('请选择捡货单！');
		return;
	}
	
	var status = getCurrentStatus(formId);
	if(status.formStatus == "已审核"){
		alert(formId + '单据已审核');
		return;
	}
	console.log(status);
	
	var _url = appRoot + '/lc/stock/picking/audit/auditView.action?formId=' + formId+"&parentTabId="+tabId;
	_url = getUrl(_url);
	
	var _title = '审核捡货单-' + formId;
	addTab(_title, _url);
}

function batchAudit() {
	var selNodes = treeStore.query({
		checked : true
	});
	var idArr = [];
	var _url = appRoot + "/lc/stock/picking/audit/batchAudit.action";
	_url = getUrl(_url);
	
	require([ "dojo/_base/array", "dojo/request/xhr" ], function(array, xhr) {
		array.forEach(selNodes, function(node, i) {
			if (node.parent != 'root' && node.id != 'root') {
				idArr.push(node.id);
			}
		});
		
		if (idArr.length > 0) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					ids : idArr.join(',')
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("提交成功！");
				} else {
					alert("操作失败！");
				}
			}, function(err) {
				alert("操作失败！");
			});
		}
	});
}
