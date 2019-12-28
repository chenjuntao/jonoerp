dojo.ready(function() {
	initTree();
	queryDetail(g_categoryId);
});

var g_categoryId = '20111';
function initTree() {
	var _url = appRoot + "/restaurant/shopSemisQuery/loadTree.action";
	if (itemType == 'PRODUCT') {
		_url = appRoot + "/restaurant/shopdish/loadTree.action";
	}
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "dijit/Tree", "dijit/tree/ObjectStoreModel", "dojo/domReady!" ],
			function(xhr, Memory, Tree, ObjectStoreModel) {

				xhr(_url, {
					handleAs : 'json'
				}).then(function(data) {
					// This store implements the new Dojo Object Store API.
					myStore = new Memory({
						data : data,
						getChildren : function(object) {
							return this.query({
								parent : object.id
							// cbtree默认支持parent
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
							var children = this.store.getChildren(item);
							if (children.length > 0) {
								return true;
							}
							return false;
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
				}, function(err) {
					alert("load error");
				});
			});
}

function queryDetail(_id) {
	g_categoryId = _id;
	if (grid == null) {
		initGrid(g_categoryId);
	} else {
		grid.set('query', {
			categoryId : g_categoryId
		});
	}
}

var grid = null;
function initGrid(_id) {
	var _url = appRoot + "/restaurant/shopSourceQuery/listItemMeta.action";
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard","dgrid/extensions/ColumnResizer"  ],
			function(OnDemandGrid, JsonRest, declare, query, Keyboard,ColumnResizer) {
				var myStore = new JsonRest({
					target : _url,
					query : function(query, options) {
						if (query.categoryId == undefined) {
							query.categoryId = _id;
						}
						return JsonRest.prototype.query.call(this, query, options);
					}
				});

				var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
				grid = new CustomGrid({
					store : myStore,
					columns : cols,
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
			});
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
	label : "工序",
	field : "therapy",
	renderCell : function(object) {
		return hrefFmt("查看", scanProcess, object);
	},
	sortable:false
}, {
	label : "工序",
	field : "therapy",
	renderCell : function(object) {
		return hrefFmt("修改", editProcess, object);
	},
	sortable:false
}, {
	label : "助记码",
	field : "queryCode",
	sortable:false
}, {
	label : "单位",
	field : "itemDimension",
	sortable:false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable:false
}, {
	label : "类别编码",
	field : "categoryId",
	sortable:false
}, {
	label : "",
	field : "none",
	sortable:false
} ];

function scanProcess(rowData) {
	var _url = appRoot + '/hq/item/process/processScanView.action?foodId=' + rowData.itemId;
	
	parent.addTab(rowData.itemName, _url);
}

function editProcess(rowData) {
	var _url = appRoot + '/hq/item/process/processEditView.action?foodId=' + rowData.itemId;
	
	parent.addTab(rowData.itemName, _url);
}
