require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();

		dojo.byId('itemName').focus();
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
	var _url = appRoot + "/hq/item/product/seltherapyitem/queryItem.action?itemType=" + g_itemType;
	_url += "&itemName=" + itemName;
	
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
		label : "商品编码",
		field : "itemId"
	}, {
		label : "商品名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		}
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : "库存单位",
		field : "stockDimension"
	}, {
		label : "配方单位",
		field : "itemDimension"
	}, {
		label : "转换因子",
		field : "unitConvertFactor"
	}, {
		label : "标准价",
		field : "benchmarkPrice"
	}, {
		label : "进货价",
		field : "purchasePrice"
	}, {
		label : "",
		field : "blank"
	} ];
}

function doSelect() {
	var selArr = [];
	for ( var itemId in grid.selection) {
		if (oldItemId == itemId) {
			alert("请不要替换相同的基本物料！");
			selArr = [];
			return;
		}
		selArr.push(itemStore.get(itemId));
	}
	if (selArr.length == 0) {
		alert('请选择记录！');
		return;
	}
	if (!isEmpty(oldItemId)) {
		parent.delItemId(oldItemId, selArr);
		return;
	}
	parent.closeItemDlg(selArr);
}
