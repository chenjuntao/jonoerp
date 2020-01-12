dojo.ready(function() {
	branchId = dojo.byId("shopC").value;
	initTree();
	initButton();
	initStorageGrid();
});

var branchId = '';
function branchChange(branchId, storageId) {
	branchId = dojo.byId("shopC").value;

	refreshStorage(shopC, storageId);
}

// ItemCategoryTree-----------------------------------------------------------------------------------
var treeStore = null;
var treeModel = null;
function initTree() {
	require([ "dojo/_base/xhr", "dojo/store/Memory", "cbtree/Tree",
			"cbtree/model/TreeStoreModel", "cbtree/model/StoreModel-EXT" ],
			function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
				var _url = appRoot + "/restaurant/selmaterial/loadTree.action";
				_url = getUrl(_url);
				
				xhr.get({
					url : _url,
					handleAs : "json",
					load : function(data) {
						treeStore = new Memory({
							data : data
						});
						// Create the model
						treeModel = new TreeStoreModel({
							store : treeStore,
							query : {
								id : 'root'
							}
						});
						var tree = new Tree({
							model : treeModel,
							showRoot : false,
							onClick : function(item, node, evt) {
								queryDetail(item.id);
							},
							clickEventCheckBox : false
						}, "categoryTree");

						tree.startup();
					},
					error : function(error) {
						alert("load error");
					}
				});
			});
}

function refreshStorage(branchId, storageId) {
	var _url = appRoot
			+ '/restaurant/reportdamage/queryloss/refreshStorage.action?branchType='
			+ dojo.byId('branchType').value;
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr" ],
			function(xhr, domForm) {
				xhr
						.post(_url, {
							handleAs : "json",
							data : {
								branchId : dojo.byId(branchId).value
							}
						})
						.then(
								function(data) {
									if (data.msg) {
										var storageSelector = dojo
												.byId(storageId);
										storageSelector.length = 0;
										for (var i = 0, length = data.msg.length; i < length; i++) {
											var item = data.msg[i];
											storageSelector.options
													.add(new Option(
															item.storageName,
															item.storageId));
										}
									} else {
										// do something
									}
								}, function(err) {
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
		categoryId : _id,
		branchId : branchId
	}
}

var grid = null;

function initGrid(_id, _itemType) {
	var _url = appRoot
			+ "/restaurant/shopSourceQuery/listItemMetaQuickly.action?storageId="
			+ dojo.byId('storageId').value;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection",
			"dojo/store/JsonRest", "dojo/store/Cache", "dojo/store/Memory",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojo/_base/array", "dojo/domReady!" ], function(OnDemandGrid,
			selector, Selection, JsonRest, Cache, Memory, declare, query,
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
			field : 'checkId'
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
	label : "编码",
	field : "itemId"
}, {
	label : "名称",
	field : "itemName"
} ];

/**
 * 获取选择的原材料，包括树中的选择和表格中的选择
 */
function getMaterial() {
	var selCateArr = treeStore.query({
		checked : true
	});
	var idArr = [];
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selCateArr, function(cate, i) {
			idArr.push(cate.id);
		});
	});
	getMaterialByCategory(idArr, setResult);
}

function setResult(data) {
	require(
			[ "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/array" ],
			function(Observable, Memory, array) {
				array.forEach(data, function(row, i) {
					row.rownumber = i + 1;
				});
				dataStore = new Observable(new Memory({
					data : data,
					idProperty : "itemId"
				}));
				storage_grid.set("store", dataStore);
				busyButton.cancel();
			});
}

/**
 * 根据树控件选择的类别查询相应的原材料信息
 * 
 * @param idArr
 * @param callback
 */
function getMaterialByCategory(idArr, callback) {
	var _url = appRoot
			+ "/restaurant/shopSourceQuery/listMaterial.action?branchId="
			+ branchId + "&storageId=" + dojo.byId('storageId').value;
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/_base/array" ],
			function(xhr, array) {
				xhr
						.post(_url, {
							handleAs : "json",
							data : {
								jsonData : JSON.stringify(idArr)
							}
						})
						.then(
								function(data) {
									var uniqueArr = [];
									array.forEach(data, function(row, i) {// 根据类别树获取的记录
										uniqueArr.push(row);
									});

									var gridSelectedIdArr = [];// 根据表格选中的记录
									array.forEach(selectedRows,
											function(row, i) {// 根据表格获取的记录（包括历史记录）
												var itemId = row.itemId;
												if (!exist(itemId)) {// 排除重复的原材料
													gridSelectedIdArr
															.push(itemId);
												}
											});

									var _url1 = appRoot
											+ "/restaurant/shopSourceQuery/listItemByIds.action?branchId="
											+ branchId + "&storageId="
											+ dojo.byId('storageId').value;
									_url = getUrl(_url);
									
									xhr
											.post(
													_url1,
													{
														handleAs : "json",
														data : {
															jsonData : JSON
																	.stringify(gridSelectedIdArr)
														}
													})
											.then(
													function(data) {
														array
																.forEach(
																		data,
																		function(
																				row,
																				i) {
																			uniqueArr
																					.push(row);
																		})
														callback(uniqueArr);
													}, function(err) {
													});

									function exist(itemId) {
										return array.some(uniqueArr, function(
												row, i) {
											return itemId == row.itemId;
										});
									}
								}, function(err) {
								});
			});
}

// busybutton------------------------------------------------------------------------------------------
var busyButton;
function initButton() {
	dojo.require("dojox.form.BusyButton");

	busyButton = new dojox.form.BusyButton({
		id : "submit",
		busyLabel : "正在查询...",
		label : "查询库存",
		timeout : 10000
	}, "placeHolder");

	dojo.connect(dijit.byId("submit"), "_onClick", function() {
		getMaterial();
	});

	require([ "dojo/ready", "dijit/form/Button", "dojo/store/Memory" ],
			function(ready, Button, Memory) {
				ready(function() {
					var myButton = new Button({
						label : "清空查询结果",
						onClick : function() {
							dataStore = new Memory({
								data : []
							});
							storage_grid.set("store", dataStore);

							// clear middle grid and cache data;
							queryDetail(g_cateId);
							selectedRows = [];

							treeModel.uncheck({
								checked : true
							});
						}
					}, "clearItemBtn");
				});
			});
}

var storage_grid;
var dataStore = null;

function initStorageGrid() {
	require([ "dojo/request/xhr","dgrid/editor", "dgrid/OnDemandGrid", "dojo/_base/declare",
			"dgrid/Keyboard", "dojo/window", "dgrid/Selection",
			"dojo/store/Memory" ], function(xhr,editor, OnDemandGrid, declare,
			Keyboard, win, Selection, Memory) {

		dataStore = new Memory({
			data : []
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard, Selection ]);
		storage_grid = new CustomGrid({
			data : dataStore,
			columns : getCols(editor),
			cellNavigation : false,
			loadingMessage : '加载中...',
			farOffRemoval : 10000, // larger than total height of data;
			// never remove rows
			minRowsPerPage : 10000
		// request more data at a time
		}, "storageGrid");

		
		storage_grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'shelf') {
				storage_grid.save();// very important
				var shelf = event.value;
				var storageId = dojo.byId("storageId").value;
				var itemId = event.cell.row.data.itemId;
				
				var _url = appRoot +  "/restaurant/shopSourceQuery/updateShelf.action";
				_url = getUrl(_url);
				
				xhr.post(_url,
						{handleAs : "json",
							data : {
								shelf : shelf,
								itemId : itemId,
								storageId : storageId
							}})
						.then(
						function(data) {
							console.log(data);
						}, function(err) {
						});
			}
		});
		
		storage_grid.startup();
	});
}

function getCols(editor) {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "编码",
		field : "itemId"
	}, {
		label : "名称",
		field : "itemName"
	}, {
		label : "简称",
		field : "itemNameEng"
	}, {
		label : "助记码",
		field : "queryCode"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "供应商",
		field : "supplier"
	}, {
		label : "配送价",
		field : "itemUnitPrice"
	}, editor({
		label : "库位",
		field : "shelf",
		editor : "text",
		editOn : "dblclick"
	}), {
		label : "库存量",
		field : "inventory"
	} ];
}
