dojo.ready(function() {
	initTree();
	initGrid();
});

var g_cateId = '20101';
function initTree() {
	require([ 
	          "dojo/_base/xhr", 
	          "dojo/store/Memory", 
	          "dijit/Tree", 
	          "dijit/tree/ObjectStoreModel"
	         ], function(xhr,Memory, Tree, ObjectStoreModel) {
		var _url = appRoot + "/restaurant/shopdish/loadTree.action";
		
		xhr.get({
			url : _url,
			handleAs : "json",
			load : function(data) {
				myStore = new Memory({
					data : data,
					getChildren : function(object) {  //得到它的孩子节点
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
					mayHaveChildren : function(item) { // 基于store的数据可以通过getChildren方法查询可知是否有孩子节点
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
						g_cateId = item.id;
						queryItem();
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

/**
 * 点击查询按钮时调用
 */
function doQuery() {
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		alert('请输入出品名称或编码！');
		return;
	}
	queryItem();
}

function queryItem() {
	var _url = appRoot + "/restaurant/shopdish/listItem.action";
	var itemName = dojo.byId('itemName').value.trim(); // string trim()
	if (itemName == '') {
		_url += "?categoryId=" + g_cateId;
	} else {
		_url += "?itemName=" + encodeURI(itemName);
	}
	
	itemStore.setData([]); //清空数据
	grid.set("store", itemStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				itemStore.put(row); //itemStore若不经过Observable包装，itemStore发生变化不会体现在dgrid界面上
			});
		}, function(err) {
		});
	});
}

var grid = null;
var itemStore = null;

function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/Observable", 
	          "dojo/store/Memory", 
	          "dojo/_base/declare", 
	          "dojo/query",
	          "dgrid/Keyboard"
	         ], function(OnDemandGrid, Observable, Memory, declare, query, Keyboard) {
		itemStore = new Observable(new Memory({
			idProperty : "itemId", //指定键
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			store : itemStore,
			columns : cols,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function gotoTherapy(_data) {
	var _url = appRoot + '/restaurant/costcard/mainView.action?foodId=' + _data.itemId;
	
	parent.addTab(_data.itemName, _url);
}

function scanSuit(rowData) {
	var _url = appRoot + '/hq/item/suit/scanView.action?foodId=' + rowData.itemId;
	
	parent.addTab(rowData.itemName, _url);
}

function edit(_data) {
	var _url = appRoot + '/hq/station_observation/edit.action?itemId=' + _data.itemId;
	
	parent.addTab("编辑观察表-"+_data.itemName, _url);
}

function scan(_data) {
	var _url = appRoot + '/hq/station_observation/scan.action?itemId=' + _data.itemId;
	
	parent.addTab("查看观察表-"+_data.itemName, _url);
}
var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable: false
}, {
	label : "出品编码",
	field : "itemId",
	sortable: false
}, {
	label : "出品名称",
	field : "itemName",
	sortable: false
}, {
	label : "单位",
	field : "itemDimension",
	sortable: false
}, {
	label : "助记码",
	field : "queryCode",
	sortable: false
// }, {
// label : "大类",
// field : "none"
// }, {
// label : "小类",
// field : "none"
// }, {
// label : "成本价",
// field : "itemAmt" //餐厅不需要看到成本价
}, {
	label : "售卖价",
	field : "salePrice",
	sortable: false
}, {
	label : "查看",
	field : "operate",
	renderCell : function(rowData) {
		if (rowData.categoryId == '3') {
			return hrefFmt("套餐", scanSuit, rowData);
		}
		return hrefFmt("成本卡", gotoTherapy, rowData);
	},
	sortable: false
}, {
	label : "观察表",
	field : "operate",
	renderCell : function(object) {
		return hrefFmt("查看", scan, object);
	},
	sortable: false
}, {
	label : "观察表",
	field : "operate",
	renderCell : function(object) {
		return hrefFmt("编辑", edit, object);
	},
	sortable: false
}
, {
	label : "",
	field : "none",
	sortable: false
} ];
