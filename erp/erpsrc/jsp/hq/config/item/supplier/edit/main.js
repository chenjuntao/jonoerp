require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {// 初始化遮罩层
		standby = new Standby({
			target : "dataForm"
		});
		document.body.appendChild(standby.domNode);
		standby.startup();

		initGrid();
	});
});

var grid = null;
var itemStore = null;
function initGrid(_id) {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
			"dojo/_base/declare", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/lang", "dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, Observable, Memory,
			ObjectStore, declare, selector, Selection, editor, NumberTextBox, lang, ColumnResizer) {
		itemStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			store : itemStore,
			columns : getColumn(selector, editor, NumberTextBox, lang),
			allowSelectAll : true,
			cellNavigation : false,
			selectionMode : "single",
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn(selector, editor, NumberTextBox, lang) {
	return [ selector({
		field : 'rownumber',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "编码",
		field : "itemId",
		sortable : false
	}, {
		label : "名称",
		field : "itemName",
		sortable : false
	}, {
		label : "助记码",
		field : "queryCode",
		sortable : false
	}, {
		label : "类别",
		field : "categoryId",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
			itemStore.remove(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
}

function doSave() {
	var supplierId = dojo.byId('supplierId').value;
	var supplier = dojo.byId('supplierText').value;
	if (supplierId == '') {
		alert('请选择供应商！');
		return;
	}
	var branchIds = dojo.byId('branchId').value;
	var branchNames = dojo.byId('branchText').value;
	if (branchIds == '') {
		alert('请选择门店！');
		return;
	}

	var supplierType = dojo.byId('supplierType').value;
	var branchTypes = dojo.byId('branchTypes').value;
	if (supplierType == 'CENTRALFACTORY' && branchTypes.indexOf('RESTAURANT') >= 0) {
		alert('中央工厂不能作为餐厅的供应商！');
		return;
	}
	
	if (branchIds.indexOf(supplierId) >= 0) {
		alert('中央工厂不能为自身供货！');
		return;
	}
	
	//中央工厂只向物流中心供应半成品，不能供应原料
	if (supplierType == 'CENTRALFACTORY') {
		var itemArrs = itemStore.query();
		for(var i=0,len = itemArrs.length;i<len;i++){
			if(itemArrs[i].itemType == 'RAW'){
				alert('中央工厂不能向物流中心供应原料！');
				return;
			}
		}
	}
	
	var rows = itemStore.query();
	if (rows.length == 0) {
		alert('请选择原料！');
		return;
	}
	
	var json = {
		branchIds : branchIds,
		branchNames : branchNames,
		items : itemStore.query()
	}
	
	
	grid.save().then(function(value) {
		// 显示遮罩层
		standby.show();

		var _url = appRoot + "/hq/item/supplier/edit/saveRelation.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					supplierId : supplierId,
					supplier : supplier,
					jsonData : JSON.stringify(json)
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("保存成功！");
					// 隐藏遮罩层
					standby.hide();
				} else {
					alert("操作失败！");
				}
			}, function(err) {
			});
		});
		// do something when resolved
	}, function(err) {
		console.log(err);
		// do something when rejected
	}, function(update) {
		console.log(update);
		// do something on progress
	});
}
