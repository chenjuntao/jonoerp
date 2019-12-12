var doQuery = null;
var grid = null;

require([ "dojo", "dojo/_base/lang", "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/JsonRest",
		"dojo/store/Cache", "dojo/store/Memory", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
		"dojo/_base/array", "dojo/ready" ,"dgrid/extensions/ColumnResizer"], function(dojo, lang, OnDemandGrid, selector, Selection, JsonRest, Cache,
		Memory, declare, query, Keyboard, array, ready,ColumnResizer) {
	ready(function() {
		// 获取父窗口的数据
		loadData();
		initGrid();
	});

	/**
	 * 点击查询按钮时调用
	 */
	doQuery = function() {
		var itemName = dojo.byId('itemName').value.trim();

		itemName = lang.trim(itemName); // 去除参数两端的参数
		if (itemName == '') {
			alert('请输入原料名称或编码！');
			return;
		}
		g_cateId = null;
		grid.set('query', {
			'itemType' : 'R_S',
			'itemName' : itemName
		});
	}

	function initGrid() {
		var _url = appRoot + "/lc/request/distribution/manual/selmaterial/doQuery.action";
		_url = getUrl(_url);
		
		var jStore = new JsonRest({
			target : _url,
			idProperty : "rownumber",
			query : function(query, options) {
				return JsonRest.prototype.query.call(this, query, options);
			}
		});

		// 缓存数据
		cStore = new Cache(jStore, new Memory());

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard ,ColumnResizer]);

		// unshift() 方法可向数组的开头添加一个或更多元素
		cols.unshift(selector({
			field : 'rownumber'
		}));

		grid = new CustomGrid({
			store : cStore,
			columns : cols,
			selectionMode : "toggle", // 点击某一列勾选，再次点击那一列则取消勾选
			cellNavigation : false,
			allowSelectAll : true, // 允许全选
			loadingMessage : '加载中...'
		}, "dataGrid");

		// dgrid 选择事件
		grid.on("dgrid-select", function(event) {
			if (event.parentType != undefined) {// 好像表格render事件时也会触发，排除这一情况
				array.forEach(event.rows, function(row, i) {// 应对全选
					var record = row.data;
					
					if(!isEmpty(record.categoryId)){
						record.itemCategory = record.categoryId;// 数据对接
					}
					selectRow(row.data);
				});
			}
		});

		// dgrid 取消选择事件
		grid.on("dgrid-deselect", function(event) {
			if (event.parentType != undefined) {// 好像表格render事件时也会触发，排除这一情况
				array.forEach(event.rows, function(row, i) {
					deselectRow(row.data);
				});
			}
		});

		// dgrid 刷新完毕事件
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
			// 程序代码实现勾选一些行
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

		// 如果待添加itemId在selectedRows中已存在则返回 true，否则返回 false
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

		// splice() 方法向/从数组中添加/删除项目，然后返回被删除的项目。
		array.some(selectedRows, function(item, i) {
			if (item.itemId == itemId) {
				selectedRows.splice(i, 1);// 移除不是用pop
				return true;
			}
		});
	}

});

var branchId = '';
function loadData() {
	if (parent.getBranchId != undefined) {
		branchId = parent.getBranchId(); // 库存与门店相关
	}

	// 初始化被选择的记录
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
	sortable:false
}, {
	label : "原材料编码",
	field : "itemId",
	sortable:false
}, {
	label : "原材料名称",
	field : "itemName",
	renderCell : function(object, data) {
		return imageFmt(data, object.itemId);
	},
	sortable:false
}, {
	label : "单位",
	field : "itemDimension",
	sortable:false
}, {
	label : "类别编码",
	field : "itemCategory",
	sortable:false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable:false
}, {
	label : "包装因子",
	field : "packagingFactor",
	sortable:false
}, {
	label : "包装单位",
	field : "packagingUnit",
	sortable:false
}, {
	label : "标准单价",
	field : "itemUnitPrice",
	sortable:false
}, {
	label : "",
	field : "none",
	sortable:false
}];

/**
 * 获取选择的原材料，包括树中的选择和表格中的选择
 */
function doSelect() {
	parent.closeMaterialDlg(selectedRows);
}
