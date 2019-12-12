dojo.ready(function() {
	initTree();
});

function initTree() {
	var _url = appRoot + "/restaurant/shopSemisQuery/loadTree.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Memory", "cbtree/Tree", "cbtree/model/TreeStoreModel" ], function(xhr,
			Memory, Tree, TreeStoreModel) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			var treeStore = new Memory({
				data : data,
				idProperty : 'id',
				getChildren : function(object) {
					return this.query({
						parent : object.id
					});
				}
			});
			var treeModel = new TreeStoreModel({
				store : treeStore,
				query : {
					id : 'root'
				}
			});
			var categoryTree = new Tree({
				model : treeModel,
				showRoot : false,
				onClick : function(item, node, evt) {
					queryDetail(item.id);
				},
				checkBoxes : false
			}, "categoryTree");

			categoryTree.startup();
		}, function(err) {
			alert("load error");
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
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
	          "dgrid/extensions/ColumnResizer",	"dojo/domReady!" ], function(OnDemandGrid, JsonRest, declare, query, Keyboard,ColumnResizer) {
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

function gotoTherapy(_data) {
	var _url = appRoot + '/restaurant/costcard/mainView.action?foodId=' + _data.itemId;
	_url = getUrl(_url);
	
	var _title = '查看成本卡-' + _data.itemName;
	addTab(_title, _url);
}

function gotoProcess(_data) {
	var _url = appRoot + '/cf/basic/product/processView.action?foodId=' + _data.itemId;
	_url = getUrl(_url);
	
	var _title = '查看工序-：' + _data.itemName;
	addTab(_title, _url);
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable:false
}, {
	label : "产品编码",
	field : "itemId",
	sortable:false
}, {
	label : "产品名称",
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
	label : "类别编码",
	field : "categoryId",
	sortable:false
}, {
	label : "成本卡",
	field : "therapy",
	renderCell : function(object) {
		return hrefFmt("查看", gotoTherapy, object);// hrefFmt(_text, _handler,
		// _data)
	},
	sortable:false
}, {
	label : "工序",
	field : "therapy",
	renderCell : function(object) {
		return hrefFmt("查看", gotoProcess, object);
	},
	sortable:false
}, {
	label : "",
	field : "none",
	sortable:false
} ];
