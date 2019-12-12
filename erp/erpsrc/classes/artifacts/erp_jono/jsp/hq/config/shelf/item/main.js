require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();

		initShelfTree();
	});
});

var g_shelfId = '001';
var g_itemId = null;
function initShelfTree() {
	var _url = appRoot + "/hq/shelf/treeQuery.action";
	
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
					g_shelfId = item.id;
					queryItem();
				}
			}, "shelfTree");

			tree.startup();

			// 默认选择第一个库位
			g_shelfId = data[1].shelfId;
			tree.set('paths', [ [ 'root', g_shelfId ] ]);
			queryItem();
		}, function(err) {
			alert("load error");
		});
	});
}

function queryItem() {
	var _url = appRoot + "/hq/shelfitem/queryItem.action?shelfId=" + g_shelfId;

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
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection","dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, Observable, Memory,
			ObjectStore, declare, selector, Selection,ColumnResizer) {
		itemStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection,ColumnResizer ]);
		grid = new CustomGrid({
			store : itemStore,
			columns : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(selector) {
	return [ selector({
		field : 'rownumber',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编码",
		field : "itemId",
		sortable : false
	}, {
		label : "名称",
		field : "itemName",
		sortable : false
	}, {
		label : "助记码",
		field : "queryCode",
		sortable : false
	}, {
		label : "类别",
		field : "categoryName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "主库位",
		field : "priority",
		formatter : priorityFmt,
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ];
}

function priorityFmt(value, rowData) {
	if (value == '0') {
		return '是';
	}
	return '';
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	var _url = appRoot + "/hq/shelfitem/deleteRelation.action";
	
	if (confirm("确定删除选定的商品吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					shelfId : g_shelfId,
					itemIds : selectArr.join(',')
				}
			}).then(function(data) {
				alert("删除成功！");
				queryItem();
			}, function(err) {
			});
		});
	}
}
