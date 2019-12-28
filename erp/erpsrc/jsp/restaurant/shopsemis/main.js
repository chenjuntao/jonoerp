dojo.ready(function() {
	initTree();
});

function initTree() {
	require([ 
	          "dojo/_base/xhr", 
	          "dojo/store/Memory", 
	          "dijit/Tree",
	          "dijit/tree/ObjectStoreModel", 
	          "dojo/domReady!" 
	        ], function(xhr,Memory, Tree, ObjectStoreModel) {

		var _url = appRoot + "/restaurant/shopSemisQuery/loadTree.action";
		_url = getUrl(_url);
		
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
						return item.isLeaf == 'N';
					}
				});

				var tree = new Tree({
					model : myModel,
					showRoot : false,
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

function queryDetail(_id) {
	if (grid == null) {
		initGrid(_id);
	} else {
		grid.set('query', {
			categoryId : _id
		});
	}
}

var grid = null;

function initGrid(_id) {
	var _url = appRoot + "/restaurant/shopSemisQuery/listItemMeta.action";
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest",
			"dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare,
			query, Keyboard) {
		var myStore = new JsonRest({
			target : _url,
			query : function(query, options) {
				if (query.categoryId == undefined) {
					query.categoryId = _id;
				}
				return JsonRest.prototype.query.call(this, query, options);
			}
		});

		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			store : myStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...',
			noDataMessage : "无数据！" //好像没有效果
		}, "dataGrid");

		grid.startup();
	});
}

function gotoTherapy(_data) {
	console.log(_data, _data.itemId);
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable:false
},{
	label : "原材料编码",
	field : "itemId",
	sortable:false
}, {
	label : "原材料名称",
	field : "itemName",
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
	label : "标准价",
	field : "itemUnitPrice",
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
