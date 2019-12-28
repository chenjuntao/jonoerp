require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var subItemGrid = null;
var optionGrid = null;
var subStore = null;
var optionStore = null;

var g_subItemId = null;
function initGrid() {
	var _url = appRoot + "/restaurant/costcard/doQuery.action?foodId=" + g_foodId;
	
	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore", "dojo/_base/declare",
			"dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dijit/form/Select", "dojo/_base/lang", "dojo/_base/array" ], function(Observable, Memory, ObjectStore,
			declare, OnDemandGrid, selector, Selection, editor, NumberTextBox, Select, lang, array) {
		subStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		optionStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection ]);
		subItemGrid = new CustomGrid({
			store : subStore,
			columns : getColumn(selector, editor, NumberTextBox, Select, lang),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "subItemGrid");

		subItemGrid.on("dgrid-select", function(event) {
			if (event.parentType != undefined) {// 好像表格render事件时也会触发，排除这一情况
				array.forEach(event.rows, function(row, i) {// 应对全选
					var record = row.data;
					queryOption(record.subItemId);
				});
			}
		});
		subItemGrid.on("dgrid-refresh-complete", function(evt) {
			var firstSubItem = subStore.get("1");
			if (firstSubItem != undefined) {
				subItemGrid.select(subItemGrid.row("1"));
				queryOption(firstSubItem.subItemId);
			}
		});

		optionGrid = new CustomGrid({
			store : optionStore,
			columns : getOptionColumn(selector, editor, NumberTextBox, Select, lang),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "optionGrid");

		subItemGrid.startup();
		optionGrid.startup();
		querySubItem();
	});
}

function querySubItem() {
	var _url = appRoot + "/hq/item/suit/querySubItem.action?suitId=" + g_foodId;
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			reloadData(data);
			setProductPrice(data);
		}, function(err) {
		});
	});
}
/**
 * 进入页面时初始化，暂时不支持动态计算；求和各子项的金额，算出套餐的总售价（作为参考）
 */
function setProductPrice(data) {
	require([ "dojo/dom", "dojo/_base/array" ], function(dom, array) {
		var productPrice = 0;
		array.forEach(data, function(row, i) {
			productPrice += row.itemCount * row.salePrice;
		});
		dom.byId('productPrice').innerHTML = productPrice;
	});
}


function queryOption(_subItemId) {
	g_subItemId = _subItemId;
	var _url = appRoot + "/hq/item/suit/queryOption.action?suitId=" + g_foodId + "&subItemId=" + _subItemId;
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			reloadData(data, 'option');
		}, function(err) {
		});
	});
}

function reloadData(data, _type) {
	if (_type == 'option') {
		optionStore.setData([]);
		optionGrid.set("store", optionStore);
	} else {
		subStore.setData([]);
		subItemGrid.set("store", subStore);
	}
	addData(data, _type);
}

function addData(data, _type) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			if (_type == 'option') {
				if (row.optionId == undefined) {
					row.optionId = row.itemId;
					row.optionName = row.itemName;
				}
				row.rownumber = optionStore.query().length + 1;
				optionStore.put(row);
			} else {
				if (row.subItemId == undefined) {
					row.subItemId = row.itemId;
					row.subItemName = row.itemName;
				}
				row.rownumber = subStore.query().length + 1;
				subStore.put(row);
			}
		});
	});
}

var numArgs = {
	style : 'width: 5em;',
	constraints : {
		min : 0,
		max : 99550,
		places : '0,3'
	},
	required : true,
	invalidMessage : '请输入不多于三位小数的数字。'
};

function getColumn(selector, editor, NumberTextBox, Select, lang) {
	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
	}
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "编码",
		field : "subItemId"
	}, {
		label : "名称",
		field : "subItemName"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "数量",
		field : "itemCount"
	}, {
		label : "价格",
		field : "salePrice",
	}, {
		label : "",
		field : "blank"
	} ];
}

function getOptionColumn(selector, editor, NumberTextBox, Select, lang) {
	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
	}
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "编码",
		field : "optionId"
	}, {
		label : "名称",
		field : "optionName"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "数量",
		field : "itemCount"
	}, {
		label : "价格",
		field : "salePrice",
	}, {
		label : "",
		field : "blank"
	} ];
}
