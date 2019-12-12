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
	var supplierId=  dojo.byId("supplierId").value;
	var _url = appRoot + "/hq/priceadjust/selitem/queryBySupplier.action?adjustType=" + g_adjustType + "&itemType="
			+ g_itemType+"&supplierId=" +supplierId;
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
			"dgrid/selector", "dgrid/Selection" ,"dgrid/extensions/ColumnResizer"]
			, function(OnDemandGrid, Observable, Memory, declare, selector,	Selection,ColumnResizer) {
		itemStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer]);
		grid = new CustomGrid({
			store : itemStore,
			columns : getColumn(selector),
			allowSelectAll : true,
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");
//		doQuery();
		grid.startup();
	});
}

function getColumn(selector) {
	return [ selector({
		label : "",
		field : 'rownumber',
		sortable:false
	}), {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "商品编码",
		field : "itemId",
		sortable:false
	}, {
		label : "商品名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		},
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
		label : "包装因子",
		field : "itemPackager",
		sortable:false
	}, {
		label : "原价",
		field : "oldPrice",
		sortable:false
	}, {
		label : "",
		field : "blank",
		sortable:false
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
	
	parent.closeItemDlg(selArr);
}
