dojo.ready(function() {
	initBranchTree();
	initCateTree();
	initGrid();
});

var branchData = [ {
	"id" : "root",
	"name" : "root"
}, {
	"id" : lcCode,
	"name" : "物流中心",
	"parent" : "root"
// }, {
// "id" : cfCode,
// "name" : "中央工厂",
// "parent" : "root"
} ];

var g_branchId = lcCode;
var g_cateId = '20101';
function initBranchTree() {
	require([ "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel", "cbtree/model/StoreModel-EXT" ],
			function(Memory, Tree, TreeStoreModel, StoreModelExt) {
				treeStore = new Memory({
					data : branchData
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
					clickEventCheckBox : false,
					onClick : function(item, node, evt) {
						if (hasUnsaved()) {
							alert("请先保存！");
							return;
						}
						g_branchId = item.id;
						queryItem();
					}
				}, "branchTree");

				tree.startup();
			});
}

function initCateTree() {
	var _url = appRoot + "/restaurant/shopSourceQuery/loadTree.action?itemType=SEMIS";

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
	var _url = appRoot + "/hq/period/supplier/doQuery.action?itemType=SEMIS&branchId=" + g_branchId;

	var itemName = dojo.byId('itemName').value.trim();
	var queryItem;

	if (itemName == '') {
		queryItem = {
			categoryId : g_cateId
		};
	} else {
		queryItem = {
			itemName : itemName
		};
	}

	itemStore.setData([]);
	grid.set("store", itemStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json",
			data : queryItem
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
	var _url = appRoot + "/hq/period/supplier/doQuery.action";

	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/lang", "dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, Observable, Memory,
			ObjectStore, declare, selector, Selection, editor, NumberTextBox, lang, ColumnResizer) {
		itemStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : itemStore,
			columns : getColumn(selector, editor, NumberTextBox, lang),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
		
		grid.on('dgrid-error', function(event) {
			errorHandler(event.error);
		});

	});
}

var numArgs = {
	style : 'width: 5em;',
	constraints : {
		min : 0,
		max : 99550,
		places : '0,2'
	},
	required : true,
	invalidMessage : '请输入数字，并且不能超过两位小数！'
};

function getColumn(selector, editor, NumberTextBox, lang) {
	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
	}
	var labelName = "库存周期(天)";
	if (g_busFlag) {
		labelName = "安全库存";
	}

	return [ {
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
		field : "categoryId",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}
	// , lang.mixin(getNumEditor(), {
	// label : "供货周期(天)",
	// field : "scycle",
	// sortable : false
	// })
	, lang.mixin(getNumEditor(), {
		label : labelName,
		field : "inventorysCycle",
		sortable : false
	}), {
		label : "",
		field : "none",
		sortable : false
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
		var _url = appRoot + "/hq/period/supplier/doSave.action";

		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					branchId : g_branchId,
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
		// do something when resolved
	}, function(err) {
		console.log(err);
		// do something when rejected
	}, function(update) {
		console.log(update);
		// do something on progress
	});
}
