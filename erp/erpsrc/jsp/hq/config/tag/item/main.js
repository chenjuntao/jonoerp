require([ 
          "dojo/parser", 
          "dijit/form/Button", 
          "dijit/Dialog",
          "dojo/domReady!"
      ], function( parser) {
	parser.parse();

	initTree();
	initButton();
	initGrid();
	
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
	require([
	         "dijit/form/Button",
	         "dojo/store/Memory",
	         "dijit/form/FilteringSelect",
	         "dojo/request/xhr"
	         ], function(Button, Memory,FilteringSelect,xhr) {
		new Button({
			label : "查询",
			onClick : function() {
				queryStock();
			}
		}, "queryBtn");
		
		new Button({
			label : "批量绑定标签",
			onClick : function() {
				var idArr = getCheckedCate();
				if(idArr.length == 0){
					alert("请选择类别！");
					return;
				}
				
				setItemTag(idArr.join(),'bind');
			}
		}, "bindTagBtn");
		
		new Button({
			label : "批量解除标签",
			onClick : function() {
				var idArr = getCheckedCate();
				if(idArr.length == 0){
					alert("请选择类别！");
					return;
				}
				
				setItemTag(idArr.join(),'unbind');
			}
		}, "unBindTagBtn");
		
		xhr.post(appRoot + "/hq/config/tag/doQuery.action?itemCategoryType="+itemCategoryType, {
			handleAs : "json",
			data : {
			}
		}).then(function(data) {
			new FilteringSelect({
				id : "tagCondition",
				value : "tagId",
				labelAttr : 'tagName',
				displayValueAttr : "tagName",
				searchAttr : "tagName",
				required : false,
				autoComplete : false,
				queryExpr : "*${0}*",
				store : new Memory({
					idProperty : "tagId",
					data : data
				}),
				style : "width: 140px;"
			}, "tagCondition").startup();

		}, function(err) {
			console.log(err);
		});

	});
}

var treeStore = null;
var treeModel = null;

function initTree() {
	require([ 
	          "dojo/request/xhr", 
	          "dojo/store/Memory", 
	          "cbtree/Tree", 
	          "cbtree/model/TreeStoreModel",
	          "cbtree/model/StoreModel-EXT" 
          ], function(xhr, Memory, Tree, TreeStoreModel, StoreModelExt) {
		var _url = appRoot + "/restaurant/selmaterial/loadTree.action?itemType="+itemCategoryType;
		
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

function getCheckedCate(){
	var selCateArr = treeStore.query({
		checked : true
	});

	var idArr = [];

	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selCateArr, function(cate, i) {
			idArr.push(cate.id);
		});
	});
	
	return idArr;
}

function queryStock() {
	var selCateArr = treeStore.query({
		checked : true
	});

	var idArr = [];

	require([ "dojo/_base/array" ], function(array) {
		array.forEach(selCateArr, function(cate, i) {
			idArr.push(cate.id);
		});
	});
	
	storage_grid.set('query', {
		cateIds : idArr.join(","), 
		tagCondition : dijit.byId("tagCondition").get('value'),
		itemCondition : dojo.byId('itemCondition').value
	});
}

var storage_grid;
var myStore;


function initGrid() {
	itemCategoryType = !itemCategoryType?"RAW,SEMIS":itemCategoryType;
	var _url = appRoot + "/hq/config/tag/item/queryItems.action?itemCategoryType="+itemCategoryType;
	
	require([
	         	"dgrid/Grid", 
	         	"custom/store/Server", 
	         	"dojo/store/Observable", 
	         	"dojo/store/Cache",
	         	"dojo/store/Memory", 
	         	"dojo/_base/declare",
	         	"dgrid/extensions/Pagination",
		        "dgrid/extensions/ColumnResizer", 
	         	"dojo/domReady!"
         	], function(Grid, Server, Observable, Cache, Memory,declare,Pagination,ColumnResizer) {
			myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'rownumber',
			query : function(query, options) {
				if (query.cateIds == undefined) {
					query.cateIds = '';
				}
				return Server.prototype.query.call(this, query, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, Pagination,ColumnResizer ]);
		storage_grid = new CustomGrid({
			store : myStore,
			columns : storage_cols,
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "storageGrid");

		storage_grid.startup();
	});
}

function doBindTag(row){
	setItemTag(row.item_id,'singlebind',row.item_name);
}

var storage_cols = [ {
	label : "序号",
	field : "rownumber",
	sortable : false
}, {
	label : "商品编码",
	field : "item_id",
	className:'text-center',
	sortable : false
}, {
	label : "商品名称",
	field : "item_name",
	sortable : false
}, {
	label : "类别名称",
	field : "category_name",
	sortable : false
}, {
	label : "操作",
	field : "manage",
	className:'text-center',
	sortable : false,
	renderCell : function(object, data) {
		return hrefFmt("设置标签", doBindTag, object);
	}
}, {
	label : "标签",
	field : "tags",
	sortable : false
} ];