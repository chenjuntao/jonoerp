var doQuery = null;
var grid = null;

require([ "dojo", "dojo/_base/lang", "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/JsonRest",
		"dojo/store/Cache", "dojo/store/Memory", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
		"dojo/_base/array", "dgrid/extensions/ColumnResizer", "dojo/ready" ], function(dojo, lang, OnDemandGrid,
		selector, Selection, JsonRest, Cache, Memory, declare, query, Keyboard, array, ColumnResizer, ready) {
	ready(function() {
		// 获取父窗口的数据
		loadData();
		initGrid();
	});

	/**
	 * 点击查询按钮时调用
	 */
	doQuery = function() {
		require([ "dojo/_base/lang" ], function(lang) {
			var itemName = dojo.byId('itemName').value.trim();
			itemName = lang.trim(itemName);
			if (itemName == '') {
				alert('请输入原料名称或编码！');
				return;
			}
			grid.set('query', {
				'storageId' : g_storageId,
				'itemName' : itemName
			});
		});
	}

	function initGrid() {
		var branchType = dojo.byId('branchType').value;
		var _url = appRoot + "/restaurant/reportdamage/selmaterial/queryItem.action";
		if (branchType == "CENTRALFACTORY") {
			_url = appRoot + "/restaurant/reportdamage/selmaterial/queryInventory.action";
		}

		var jStore = new JsonRest({
			target : _url,
			idProperty : "itemId",
			query : function(query, options) {
				return JsonRest.prototype.query.call(this, query, options);
			}
		});
		cStore = new Cache(jStore, new Memory());

		var CustomGrid = declare([ OnDemandGrid, ColumnResizer, Selection, Keyboard ]);
		cols.unshift(selector({
			field : 'rownumber'
		}));
		grid = new CustomGrid({
			store : cStore,
			columns : cols,
			selectionMode : "toggle",
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.on("dgrid-select", function(event) {
			if (event.parentType != undefined) {// 好像表格render事件时也会触发，排除这一情况
				array.forEach(event.rows, function(row, i) {// 应对全选
					var record = row.data;
					record.itemCategory = record.categoryId;// 数据对接
					selectRow(row.data);
				});
			}
		});
		grid.on("dgrid-deselect", function(event) {
			if (event.parentType != undefined) {// 好像表格render事件时也会触发，排除这一情况
				array.forEach(event.rows, function(row, i) {
					deselectRow(row.data);
				});
			}
		});
		grid.on("dgrid-refresh-complete", function(evt) {
			checkItem();
		});
		grid.startup();
	}

	/**
	 * remember which item has been checked before
	 */
	function checkItem() {
		array.forEach(selectedRows, function(item, i) {
			grid.select(grid.row(item.itemId));
		});
	}

	/**
	 * 根据itemId增加记录，防止重复
	 * 
	 * @param row
	 */
	function selectRow(row) {
		var itemId = row.itemId;
		var exist = array.some(selectedRows, function(item, i) {
			return item.itemId == itemId;
		});
		if (!exist) {
			selectedRows.push(row);
		}
	}

	/**
	 * 取消选择一条记录时，在全局变量数组中移除这条记录（根据itemId)
	 * 
	 * @param row
	 */
	function deselectRow(row) {
		var itemId = row.itemId;
		array.some(selectedRows, function(item, i) {
			if (item.itemId == itemId) {
				selectedRows.splice(i, 1);// 移除不是用pop
				return true;
			}
		});
	}

});

var g_storageId = '';
function loadData() {
	if (parent.getStorageId != undefined) {
		g_storageId = parent.getStorageId();// 获取对应的仓库编号
	}
	initSelectRows();
}

var selectedRows = [];
/**
 * 初始化被选择的记录
 */
function initSelectRows() {
	if (parent.selectedRows != undefined) {
		selectedRows = parent.selectedRows;// 兼容两种操作方式，确定后重载主表格数据或者追加数据
	}
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "原材料编码",
	field : "itemId",
	sortable : false
}, {
	label : "原材料名称",
	field : "itemName",
	renderCell : function(object, data) {
		return imageFmt(data, object.itemId);
	},
	sortable : false
}, {
	label : "助记码",
	field : "queryCode",
	sortable : false
}, {
	label : "单位",
	field : "itemDimension",
	sortable : false
}, {
	label : "类别编码",
	field : "categoryId",
	sortable : false
}, {
	label : "标准单价",
	field : "itemUnitPrice",
	sortable : false
}, {
	label : "库存",
	field : "inventory",
	sortable : false
}, {
	label : "",
	field : "blank",
	sortable : false
} ];

/**
 * 获取选择的原材料，包括树中的选择和表格中的选择
 */
function doSelect() {
	parent.closeMaterialDlg(selectedRows);
}
