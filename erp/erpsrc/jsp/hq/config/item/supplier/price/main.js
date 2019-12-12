require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initSupplierTree();
		initGrid();
	});
});

function showDialog() {
	dijit.byId('customDialog').show();
}

function hideDialog() {
	dijit.byId('customDialog').hide();
}

function customExport() {
	fillData(grid.get('columns'), grid.get('store').data);
}

var g_supplierId = 'FC001';
var g_itemId = null;
function initSupplierTree() {
	var _url = appRoot + "/hq/branch/r/treeQuery.action?branchType=SUPPLIER";

	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			data.push({
				id : 'FC001',
				name : '中央工厂',
				parent : 'root'
			});
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
					queryItem(item.id, item.name);
				}
			}, "supplierTree");

			tree.startup();

			// 默认选择第一个供应商
			var firstNode = data[1].branchId;
			tree.set('paths', [ [ 'root', firstNode ] ]);
			queryItem(firstNode, data[1].branchName);
		}, function(err) {
			alert("load error");
		});
	});
}

function queryItem(_supplierId, _supName) {
	g_supplierId = _supplierId;
	dojo.byId('supName').value = _supName;
	var _url = appRoot + "/hq/item/supplier/price/queryItem.action?supplierId=" + _supplierId;

	itemStore.setData([]);
	grid.set("store", itemStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				row.oldPrice = row.itemPrice;
				row.newPrice = row.itemPrice;
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
			"dojo/_base/lang", "dgrid/extensions/ColumnResizer", "dojo/parser" ], function(OnDemandGrid, Observable,
			Memory, ObjectStore, declare, selector, Selection, editor, NumberTextBox, lang, ColumnResizer, parser) {
		parser.parse();
		itemStore = new Observable(new Memory({
			idProperty : "itemId",
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
	});
}

var numArgs = {
	style : 'width: 5em;',
	constraints : {
		min : 0,
		max : 99550,
		places : '0,3'
	},
	required : true,
	invalidMessage : '请输入不多于三位小数的数字。'
};
function getColumn(selector, editor, NumberTextBox, lang) {
	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
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
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "供应商价",
		field : "itemPrice",
		sortable : false
	}, {
		label : "进货价",
		field : "costPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
			itemStore.remove(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
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

function doSave() {
	if (!hasUnsaved()) {
		return;
	}
	var rows = itemStore.query();
	if (rows.length == 0) {
		alert('请选择原料！');
		return;
	}

	grid.save().then(function(value) {
		var items = itemStore.query();
		for (var i = 0; i < items.length; i++) {
			items[i].itemPrice = items[i].newPrice;
		}

		var _url = appRoot + "/hq/item/supplier/price/savePrice.action";

		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					supplierId : g_supplierId,
					jsonData : JSON.stringify(items)
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					items
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
