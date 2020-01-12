dojo.ready(function() {
	initSelectRows();
	initTree();
	queryDetail("1011");
});

function initTree() {
	require([ "dojo/_base/xhr", "dojo/store/Memory", "dijit/Tree", "dijit/tree/ObjectStoreModel", "dojo/domReady!" ],
			function(xhr, Memory, Tree, ObjectStoreModel) {
				var _url = appRoot + "/restaurant/shopdish/loadTree.action";

				xhr.get({
					url : _url,
					handleAs : "json",
					load : function(data) {
						myStore = new Memory({
							data : data,
							getChildren : function(object) {
								return this.query({
									parent : object.id
								});
							}
						});

						// Create the model
						var myModel = new ObjectStoreModel({
							store : myStore,
							query : {
								id : 'root'
							},
							mayHaveChildren : function(item) {
								if (item.parent != 'root') {
									return false;
								}
								return true;
							}
						});

						var tree = new Tree({
							model : myModel,
							showRoot : false,
							autoExpand : true,
							onClick : function(item, node, evt) {
								queryDetail(item.id);
							}
						}, "categoryTree");
						tree.startup();
					},
					error : function(error) {
						alert("load error");
					}
				});

			});
}

var g_cateId = null;
function queryDetail(_id) {
	g_cateId = _id;
	if (grid == null) {
		initGrid(_id);
	} else {
		grid.set('query', getQuery(_id));
	}
}

function getQuery(_id) {
	return {
		categoryId : _id
	}
}

var grid = null;

function initGrid(_id, _itemType) {
	var _url = appRoot + "/restaurant/shopdish/listItem.action";

	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dojo/store/JsonRest", "dojo/store/Cache",
			"dojo/store/Memory", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard", "dojo/_base/array",
			"dojo/domReady!" ], function(OnDemandGrid, selector, Selection, JsonRest, Cache, Memory, declare, query,
			Keyboard, array) {
		var jStore = new JsonRest({
			target : _url,
			idProperty : "itemId",
			query : function(query, options) {
				if (query.categoryId == undefined) {
					query = getQuery(_id, _itemType);
				}
				return JsonRest.prototype.query.call(this, query, options);
			}
		});
		cStore = new Cache(jStore, new Memory());

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard ]);
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
				array.forEach(event.rows, function(row, i) {
					var record = row.data;
					record.itemCategory = record.categoryId;// 数据对接
					selectedRows.push(record);
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
			checkItem(g_cateId);
		});
		grid.startup();
	});
}

/**
 * remember which item has been checked before
 * 
 * @param cateId
 */
function checkItem(cateId) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selectedRows, function(item, i) {
			if (item.itemCategory == cateId) {
				grid.select(grid.row(item.itemId));
			}
		});
	});
}

var selectedRows = [];
/**
 * 初始化被选择的记录
 */
function initSelectRows() {
	if (parent.selectedRows != undefined) {
		selectedRows = parent.selectedRows;
	}
}

/**
 * 根据itemId增加记录，防止重复
 * 
 * @param row
 */
function selectRow(row) {
	var itemId = row.itemId;
	require([ "dojo/_base/array" ], function(array) {
		var exist = array.some(selectedRows, function(item, i) {
			return item.itemId == itemId;
		});
		if (!exist) {
			selectedRows.push(row);
		}
	});
}

/**
 * 取消选择一条记录时，在全局变量数组中移除这条记录（根据itemId)
 * 
 * @param row
 */
function deselectRow(row) {
	var itemId = row.itemId;
	require([ "dojo/_base/array" ], function(array) {
		array.some(selectedRows, function(item, i) {
			if (item.itemId == itemId) {
				selectedRows.splice(i, 1);// 移除不是用pop
				return true;
			}
		});
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber"
}, {
	label : "出品编码",
	field : "itemId"
}, {
	label : "出品名称",
	field : "itemName"
}, {
	label : "助记码",
	field : "queryCode"
}, {
	label : "单位",
	field : "itemDimension"
}, {
	label : "类别编码",
	field : "categoryId"
}, {
	label : "",
	field : "blank"
} ];

/**
 * 获取选择的出品
 */
function doSelect() {
	parent.closeProductDlg(selectedRows);
}
