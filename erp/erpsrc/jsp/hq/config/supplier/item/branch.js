function queryBranch() {
	var _url = appRoot + "/hq/supplier/item/queryBranch.action?supplierId=" + g_supplierId;
	_url += "&itemId=" + g_itemId;
	
	branchStore.setData([]);
	branchGrid.set("store", branchStore);
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				branchStore.put(row);
			});
		}, function(err) {
		});
	});
}

var branchGrid = null;
var branchStore = null;
function initBranchGrid(_id) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/lang","dgrid/extensions/ColumnResizer" ,"dgrid/ColumnSet"], function(OnDemandGrid, Observable, Memory, ObjectStore, declare, selector, Selection,
			editor, NumberTextBox, lang,ColumnResizer,ColumnSet) {
		branchStore = new Observable(new Memory({
			idProperty : "branchId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection ,ColumnResizer,ColumnSet]);
		branchGrid = new CustomGrid({
			store : branchStore,
			columnSets : getSupplierColumn(selector, editor, NumberTextBox, lang),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "branchGrid");

		branchGrid.startup();
	});
}

function getSupplierColumn(selector, editor, NumberTextBox, lang) {
	return  [[ [
	 {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "编码",
		field : "branchId",
		sortable:false
	}, {
		label : "名称",
		field : "branchName",
		sortable:false
	}] ], [ [ {
		label : "助记码",
		field : "queryCode",
		sortable:false
	}, {
		label : "地址",
		field : "address",
		sortable:false
	}, {
		label : "联系人",
		field : "contactMan",
		sortable:false
	}, {
		label : "联系电话",
		field : "telephone",
		sortable:false
	}, {
		label : "主供应商",
		field : "priority",
		formatter : priorityFmt,
		sortable:false
	}, {
		label : "操作",
		field : "delete",
		className : 'text-center',
		sortable : false,
		renderCell : function(object, data) {
			return hrefFmt("删除", doDelete, object);
		}
	} ] ] ];
}

function doDelete(item) {
	var _url = appRoot + "/hq/item/supplier/deleteRelation.action";
	
	if (confirm('确认删除吗？')) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					branchId : item.branchId,
					itemId : g_itemId,
					supplierId : g_supplierId
				}
			}).then(function(data) {
				alert("刪除成功！");
				queryBranch();
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
