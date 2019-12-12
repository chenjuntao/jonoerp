require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initBranchTree();
		initCateTree();
		initGrid();
		initSupplierGrid();
	});
});

var g_branchId = lcCode;
var g_cateId = '20101';
var g_itemId = null;

function initBranchTree() {
	var _url = appRoot + "/common/selbranch/queryAllTree.action";

	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
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
					g_branchId = item.id;
					querySupplier();
				}
			}, "branchTree");

			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

function initCateTree() {
	var _url = appRoot + "/restaurant/shopSourceQuery/loadTree.action";
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
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
				checkBoxes : false,
				autoExpand : true,
				clickEventCheckBox : false,
				onClick : function(item, node, evt) {
					if (!node.isExpandable) {// 叶子节点
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
	var _url = appRoot + "/hq/item/supplier/queryItem.action";
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		_url += "?categoryId=" + g_cateId;
	} else {
		_url += "?itemName=" + encodeURI(itemName);
	}
	
	// 清空供应商列表
	g_itemId = null;
	supplierStore.setData([]);
	supplierGrid.set("store", supplierStore);

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

var grid = null;
var itemStore = null;
function initGrid(_id) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/lang", "dojo/_base/array","dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, Observable, Memory, ObjectStore, declare,
			selector, Selection, editor, NumberTextBox, lang, array,ColumnResizer) {
		itemStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
		grid = new CustomGrid({
			store : itemStore,
			columns : getColumn(selector, editor, NumberTextBox, lang),
			allowSelectAll : true,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on("dgrid-select", function(event) {
			if (event.parentType != undefined) {// 好像表格render事件时也会触发，排除这一情况
				array.forEach(event.rows, function(row, i) {// 应对全选
					var record = row.data;
					g_itemId = record.itemId;
					querySupplier(viewType);
				});
			}
		});
		grid.startup();
	});
}

function getColumn(selector, editor, NumberTextBox, lang) {
	return [
       {
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
		field : "categoryId",
		sortable:false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ];
}
