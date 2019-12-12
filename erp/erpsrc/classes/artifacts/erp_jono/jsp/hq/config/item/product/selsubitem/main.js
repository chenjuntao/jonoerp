require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

/**
 * 点击查询按钮时调用
 */
function doQuery() {
	var itemName = dojo.byId('itemName').value.trim();
	if (itemName == '') {
		alert('请输入原料名称或编码！');
		return;
	}
	var _url = appRoot + "/hq/item/suit/queryProduct.action?exceptId=" + g_subItemId;
	_url += "&itemName=" + encodeURI(itemName);
	
	itemStore.setData([]);
	grid.set("store", itemStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				itemStore.put(row);
			});
		}, function(err) {
		});
	});
}

var grid = null;
var itemStore = null;
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/selector", "dgrid/Selection" ], function(OnDemandGrid, Observable, Memory, declare, selector,
			Selection) {
		itemStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		grid = new CustomGrid({
			store : itemStore,
			columns : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(selector) {
	return [ selector({
		label : "",
		field : 'rownumber'
	}), {
		label : "序号",
		field : "rownumber"
	}, {
		label : "原材料编码",
		field : "itemId"
	}, {
		label : "原材料名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		}
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "售价",
		field : "salePrice",
	}, {
		label : "",
		field : "blank"
	} ];
}

function doSelect() {
	var selArr = [];
	for ( var itemId in grid.selection) {
		selArr.push(itemStore.get(itemId))
	}
	if (selArr.length == 0) {
		alert('请选择记录！');
		return;
	}
	parent.closeSubItemDlg(selArr, g_selType);
}
