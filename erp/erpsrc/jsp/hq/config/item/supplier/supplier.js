function querySupplier() {
	var _url = appRoot + "/hq/item/supplier/querySupplier.action?branchId=" + g_branchId;
	_url += "&itemId=" + g_itemId;
	
	supplierStore.setData([]);
	supplierGrid.set("store", supplierStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				supplierStore.put(row);
			});
		}, function(err) {
		});
	});
}

var supplierGrid = null;
var supplierStore = null;
function initSupplierGrid(_id) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/lang", "dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, Observable, Memory,
			ObjectStore, declare, selector, Selection, editor, NumberTextBox, lang, ColumnResizer) {
		supplierStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		supplierGrid = new CustomGrid({
			store : supplierStore,
			columns : getSupplierColumn(selector, editor, NumberTextBox, lang),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "supplierGrid");

		supplierGrid.startup();
	});
}

function getSupplierColumn(selector, editor, NumberTextBox, lang) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编码",
		field : "supplierId",
		sortable : false
	}, {
		label : "名称",
		field : "supplierName",
		sortable : false
	}, {
		label : "主供应商",
		field : "priority",
		formatter : priorityFmt,
		sortable : false
	}, {
		label : "单价",
		field : "itemPrice",
		sortable : false
	}, {
		label : "地址",
		field : "address",
		sortable : false
	}, {
		label : "联系人",
		field : "contactMan",
		sortable : false
	}, {
		label : "联系电话",
		field : "telephone",
		sortable : false
	}, {
		label : "操作",
		field : "delete",
		className : 'text-center',
		sortable : false,
		renderCell : function(object, data) {
			if (viewType == 'delete')
				return hrefFmt("", object);
			return hrefFmt("删除", doDelete, object);
		}
	} ];
}

function doDelete(item) {
	var _url = appRoot + "/hq/item/supplier/deleteRelation.action";
	
	if (confirm('确认删除吗？')) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					branchId : g_branchId,
					itemId : g_itemId,
					supplierId : item.supplierId
				}
			}).then(function(data) {
				alert("刪除成功！");
				querySupplier();
			}, function(err) {
				alert("刪除失败！");
			});
		});
	}
}

function priorityFmt(value, rowData) {
	if (value == '0') {
		return '是';
	}
	return '';
}
