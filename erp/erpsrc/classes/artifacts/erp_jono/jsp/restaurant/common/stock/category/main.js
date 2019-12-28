require([ "dojo", "dojo/ready", "dojo/parser", "dijit/form/Button", "dijit/Dialog" ], function(dojo, ready, parser) {
	ready(function() {
		parser.parse();

		initTree();
		initButton();
		initGrid();
	});
});

function customExport() {
	exportXls();
}

function show() {
	dijit.byId('customDialog').show();
}

function hide() {
	dijit.byId('customDialog').hide();
}

function initButton() {
	require([ "dijit/form/Button", "dojo/store/Memory" ], function(Button, Memory) {

		new Button({
			label : "查询库存",
			onClick : function() {
				queryStock();
			}
		}, "queryBtn");

		new Button({
			label : "清空查询结果",
			onClick : function() {
				// 首先清除选择，再查询（达到清除查询结果的效果）
				treeModel.uncheck({
					checked : true
				});

				queryStock();
			}
		}, "clearItemBtn");

	});
}

// ItemCategoryTree-----------------------------------------------------------------------------------
var treeStore = null;
var treeModel = null;
function initTree() {
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel",
			"cbtree/model/StoreModel-EXT" ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		var _url = appRoot + "/restaurant/selmaterial/loadTree.action";
		
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
				showRoot : false, // 隐藏根节点
				autoExpand : true, // 展开所有节点
				clickEventCheckBox : false
			}, "categoryTree");

			tree.startup();
		}, function(err) {
			alert("load error");
		});
	});
}

/**
 * 根据选择的原材料类别查询原料及库存，虚拟分页
 */
function queryStock() {
	var selCateArr = treeStore.query({
		checked : true
	});

	var idArr = [];

	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selCateArr, function(cate, i) {
			// arguments[0] 当前迭代对象; arguments[1] 当前索引位; arguments[1] 被迭代的所有对象
			// idArr 存入被选中项的id
			idArr.push(cate.id);
		});
	});

	storage_grid.set('query', {
		cateIds : idArr.join(","), // 以指定的连接符连接数组中所有的元素，并返回一个字符串
		storageId : dojo.byId('storageId').value,
		itemName : dojo.byId('itemName').value.trim(),
		types : dojo.byId('types').value
	});
}

var storage_grid;

function getElementValue(element) {
	return dojo.byId(element).value;
}

function changeStorage() {
	refreshStorage({
		query : {
			branchType : getElementValue("branchType"),
			branchId : getElementValue("branchId")
		},
		storageId : "storageId",
		types : dojo.byId('types').value
	});
}

function initGrid() {
	var branchFlag = dojo.byId('branchFlag').value;
	
	var _url = appRoot + "/restaurant/common/stock/category/doQuery.action?branchId=" + getElementValue("branchId")
			+ "&branchType=" + getElementValue("branchType");
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/store/Observable", "dojo/store/Cache",
			"dojo/store/Memory", "dojo/domReady!" ], function(OnDemandGrid, Server, Observable, Cache, Memory) {
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'rownumber',
			query : function(query, options) {
				if (query.cateIds == undefined) {
					query.cateIds = '';
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		storage_grid = new OnDemandGrid({
			store : myStore,
			columns : storage_cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "storageGrid");

		storage_grid.startup();
	});
}

var storage_cols = [ {
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
	label : "供应商",
	field : "supplierName",
	sortable : false
}, {
	label : "库存量",
	field : "itemCount",
	sortable : false
} ];