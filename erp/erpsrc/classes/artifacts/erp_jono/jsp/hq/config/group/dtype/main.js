dojo.ready(function() {
	initGroupTree();
	initCateTree();
	initGrid();
});

var g_groupId = 'r01';
var g_groupName = '';
var g_cateId = '20101';

function initGroupTree() {
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		var _url = appRoot + "/common/selbranch/queryGroupTree.action";
		_url = getUrl(_url);
		
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			treeStore = new Memory({
				data : data
			});

			treeModel = new TreeStoreModel({
				store : treeStore,
				query : {
					id : 'root'
				}
			});

			var tree = new Tree({
				model : treeModel,
				showRoot : false,
				autoExpand : true,
				checkBoxes : false,
				clickEventCheckBox : false,
				onClick : function(item, node, evt) {
					if (item.parent == 'root') {// 如果是分组
						if (hasUnsaved()) {
							alert("请先保存！");
							return;
						}
						g_groupId = item.id;
						g_groupName = item.name;
						queryItem();
					}
				}
			}, "groupTree");

			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

function initCateTree() {
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		var _url = appRoot + "/restaurant/selmaterial/loadTree.action?itemType=RAW";
		
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			treeStore = new Memory({
				data : data
			});

			treeModel = new TreeStoreModel({
				store : treeStore,
				query : {
					id : 'root'
				}
			});

			var tree = new Tree({
				model : treeModel,
				showRoot : false,
				autoExpand : true,
				checkBoxes : false,
				clickEventCheckBox : false,
				onClick : function(item, node, evt) {
					if (!node.isExpandable) {// 叶子节点
						if (hasUnsaved()) {
							alert("请先保存！");
							return;
						}
						g_cateId = item.id;
						queryItem();
					}
				}
			}, "categoryTree");

			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

function hasUnsaved() {
	var count = 0;
	for ( var itemId in grid.dirty) {
		count++;
	}
	if (count == 0) {
		return false;
	}
	return true;
}
/**
 * 点击查询按钮时调用
 */
function doQuery() {
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		alert('请输入原料名称或编码！');
		return;
	}
	queryItem();
}

function queryItem() {
	var _url = appRoot + "/hq/dtype/doQuery.action?regionId=" + g_groupId;
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		_url += "&categoryId=" + g_cateId;
	} else {
		_url += "&itemName=" + encodeURI(itemName);
	}
	
	itemStore.setData([]);
	grid.set("store", itemStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				itemStore.put(row);
			});
		}, function(err) {
		});
	});
}

var dtypeData = [ {
	"key" : "UNIFIED",
	"value" : "统配"
}, {
	"key" : "DIRECT",
	"value" : "直配"
}, {
	"key" : "CROSS",
	"value" : "越库"
} ];

function dtypeFmt(value, rowData) {
	for (var i = 0; i < dtypeData.length; i++) {
		if (value == dtypeData[i].key) {
			return dtypeData[i].value;
		}
	}
	return value;
}

var grid = null;
var itemStore = null;
var dtypeStore = null;
function initGrid(_id) {
	var _url = appRoot + "/hq/group/doQuery.action";
	
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/Select",
			"dojo/_base/lang","dgrid/extensions/ColumnResizer" ],
			function(OnDemandGrid, Observable, Memory, ObjectStore, declare, selector, Selection,
			editor, Select, lang,ColumnResizer) {
		itemStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));
		dtypeStore = new ObjectStore({
			objectStore : new Observable(new Memory({
				idProperty : "key",
				data : dtypeData,
				sort : [ {// 这里排序不起作用，要何如做才行，以后再研究
					attribute : "key",
					descending : false
				} ]
			})),
			labelProperty : "value"
		});

		var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
		grid = new CustomGrid({
			store : itemStore,
			columns : getColumn(selector, editor, Select, lang),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(selector, editor, Select, lang) {
	function getSelEditor(_store, _width) {
		return editor({
			editorArgs : {
				store : _store,
				maxHeight : 150,
				style : "width: " + _width + "px;"
			},
			className : 'edit-note'
		}, Select, 'click');
	}
	return [
	/*
	 * selector({ field : 'rownumber' }),
	 */{
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "编码",
		field : "itemId",
		sortable:false
	}, {
		label : "名称",
		field : "itemName",
		sortable:false
	}, {
		label : "助记码",
		field : "queryCode",
		sortable:false
	}, {
		label : "类别",
		field : "categoryName",
		sortable:false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable:false
	}, lang.mixin(getSelEditor(dtypeStore, 70), {
		label : "配送方式",
		field : "dtype",
		formatter : dtypeFmt,
		sortable:false
	}), {
		label : "",
		field : "none",
		sortable:false
	} ];
}

function doSave() {
	if (!hasUnsaved()) {
		return;
	}
	var rows = [];
	for ( var itemId in grid.dirty) {// 只获取修改过的记录
		rows.push(itemStore.get(itemId));
	}
	grid.save().then(function(value) {
		var _url = appRoot + "/hq/dtype/doSave.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					regionId : g_groupId,
					regionName :g_groupName,
					jsonData : JSON.stringify(rows)
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("保存成功！");
				} else {
					alert("操作失败！");
				}
			}, function(err) {
			});
		});
	}, function(err) {
		console.log(err);
	}, function(update) {
		console.log(update);
	});
}
