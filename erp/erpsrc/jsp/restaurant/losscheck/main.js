require([ 
          "dojo/parser", 
          "dijit/form/Button", 
          "dijit/Dialog",
          "dojo/domReady!"
      ], function( parser) {
	parser.parse();

	initTree();
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
	storage_grid.set('query', getQuery());
}

var storage_grid;


function initGrid() {
	var _url = appRoot + "/reportmanage/special/common/doQuery.action"
	
	require([
	         	"dgrid/Grid", 
	         	"custom/store/Server", 
	         	"dojo/store/Observable", 
	         	"dojo/store/Cache",
	         	"dojo/store/Memory", 
	         	"dojo/_base/declare",
	         	"dgrid/extensions/Pagination",
	         	"dgrid/ColumnSet" ,
		        "dgrid/extensions/ColumnResizer", 
		        "dojo/_base/array",
	         	"dojo/domReady!"
         	], function(Grid, Server, Observable, Cache, Memory,declare,Pagination,ColumnSet,ColumnResizer,array) {
		
		window.getQuery = function(){
			var  str1 = " h.AUDIT_TIME IS NOT NULL AND h.BRANCH_ID = '"+ branchId +"' ";
			var str2 = "  io.BRANCH_ID = '"+ branchId +"' ";
			
			var selCateArr = treeStore.query({
				checked : true
			});

			var idArr = [];
			var append = [];

			array.forEach(selCateArr, function(cate, i) {
				idArr.push(cate.id);
			});
			
			var cateIds = idArr.join(",");
			var startDate = dojo.byId('startDate').value;
			var endDate = dojo.byId('endDate').value;
			var itemCondition = dojo.byId('itemCondition').value;
			
			if(startDate.length != 0){
				str1 += " AND h.AUDIT_TIME >= to_date('" + startDate +"','YYYY-MM-DD') ";
				str2 += " AND io.BUSINESS_DATE >= to_date('"+ startDate +"','YYYY-MM-DD') ";
			}
			
			if(endDate.length != 0){
				str1 += " AND h.AUDIT_TIME <= to_date('" + endDate +"','YYYY-MM-DD') ";
				str2 += " AND io.BUSINESS_DATE <= to_date('"+ endDate +"','YYYY-MM-DD') ";
			}
			
			
			if(itemCondition.length != 0){
				str1 += " AND (  m.ITEM_NAME LIKE '%"+ itemCondition +"%' or m.ITEM_ID LIKE '%"+ itemCondition +"%') ";
				str2 += " AND (  m.ITEM_NAME LIKE '%"+ itemCondition +"%' or m.ITEM_ID LIKE '%"+ itemCondition +"%') ";
			}
			
			String.prototype.replaceAll = function(s1,s2) { 
			    return this.replace(new RegExp(s1,"gm"),s2); 
			}
			
			if(cateIds.length != 0){
				cateIds = "('"+cateIds.replaceAll(",", "','")+"')";
				str1 += "  AND m.CATEGORY_ID IN  " + cateIds + " ";
				str2 += "  AND m.CATEGORY_ID IN  " + cateIds + " ";
			}
			
			append.push(str1);
			append.push(str2);
			
			return {
				append : append.join('|_|'),
				flag :dojo.byId('flag').value
			};
		}
		
		var myStore = Observable(Cache(Server({
			target : _url,
			idProperty : 'rownumber',
			query : function(query, options) {
				return Server.prototype.query.call(this, getQuery()	, options);
			}
		}), Memory()));

		var CustomGrid = declare([ Grid, ColumnSet,Pagination,ColumnResizer ]);
		storage_grid = new CustomGrid({
			store : myStore,
			columnSets : storage_cols,
			cellNavigation : false,
			pageSizeOptions : [ 10, 30, 100 ],
			loadingMessage : '加载中...'
		}, "storageGrid");

		storage_grid.startup();
	});
}

var storage_cols = [[[{
	label : "序号",
	field : "rownumber",
	sortable : false
},{
	label : "商品编码",
	field : "item_id",
	className:'text-center',
	sortable : false
}, {
	label : "商品名称",
	field : "item_name",
	sortable : false
}] ], [ [ {
	label : "门店",
	field : "branch_name",
	sortable : false,
	formatter : function(value, rowData) {
		return '['+rowData.branch_id+']'+rowData.branch_name;
	},
},{
	label : "标准价",
	field : "item_price",
	className:'text-right',
	sortable : false
}, {
	label : "原料报损",
	field : "ylbs",
	className:'text-right',
	sortable : false
}, {
	label : "出品报损",
	field : "cpbs",
	className:'text-right',
	sortable : false
}, {
	label : "盘盈",
	field : "pdi",
	className:'text-right',
	sortable : false
}, {
	label : "盘亏",
	field : "pdo",
	className:'text-right',
	sortable : false
}, {
	label : "自产损耗",
	field : "sshl2",
	className:'text-right',
	sortable : false
}, {
	label : "自产理论扣仓",
	field : "sshl",
	className:'text-right',
	sortable : false
}, {
	label : "销售扣仓",
	field : "llku",
	className:'text-right',
	sortable : false
}, {
	label : "理论消耗量",
	field : "theory",
	className:'text-right',
	sortable : false
}, {
	label : "理论消耗金额",
	field : "theory_amt",
	className:'text-right',
	sortable : false
}, {
	label : "实际消耗量",
	field : "actual",
	className:'text-right',
	sortable : false
}, {
	label : "实际消耗金额",
	field : "actual_amt",
	className:'text-right',
	sortable : false
}] ] ];