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
			idProperty : "subItemId",
			data : []
		}));
		optionStore = new Observable(new Memory({
			idProperty : "optionId",
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
		subItemGrid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'itemCount') {
				subItemGrid.save();// very important
				var row = subStore.get(event.rowId);
				var lastPrice = row.allPrice;
				row[field] = event.value;// necessary
				row.allPrice = parseFloat((event.value * row.salePrice).toFixed(2)); // 计算金额
				subStore.put(row);
				setProductPrice(subStore.query());
			}
			
			if (field == 'salePrice') {
				subItemGrid.save();// very important
				var row = subStore.get(event.rowId);
				var lastPrice = row.allPrice;
				row[field] = event.value;// necessary
				row.allPrice = parseFloat((event.value * row.itemCount).toFixed(2)); // 计算金额
				subStore.put(row);
				setProductPrice(subStore.query());
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
		subStore
		subItemGrid.startup();
		optionGrid.startup();
		querySubItem();
	});
}

function querySubItem() {
	var _url = appRoot + "/hq/item/suit/querySubItem.action?suitId=" + g_foodId;
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json",
			preventCache : true
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
		dom.byId('productPrice').value = productPrice;
		dom.byId('productPrice').innerHTML = productPrice;
	});
}

function queryOption(_subItemId) {
	g_subItemId = _subItemId;
	var _url = appRoot + "/hq/item/suit/queryOption.action?suitId=" + g_foodId + "&subItemId=" + _subItemId;
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json",
			preventCache : true
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
					row.itemCount = 0;
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
	return [ selector({
		field : 'rownumber',
		sortable:false
	}), {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "编码",
		field : "subItemId",
		sortable:false
	}, {
		label : "名称",
		field : "subItemName",
		sortable:false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable:false
	}, lang.mixin(getNumEditor(), {
		label : "数量",
		field : "itemCount",
		sortable:false
	}), lang.mixin(getNumEditor(), {
		label : "单价",
		field : "salePrice",
		sortable:false
	}), {
		label : "售价",
		field : "allPrice",
		sortable:false
	}, {
		label : "",
		field : "blank",
		sortable:false
	} ];
}

function getOptionColumn(selector, editor, NumberTextBox, Select, lang) {
	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox, 'click');
	}
	return [ selector({
		field : 'rownumber'
	}), {
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
		label : "",
		field : "blank"
	} ];
}

var subItemDlg = null;
var selectedRows = [];
function selSubItem(_type) {
	var _url = appRoot + "/hq/item/suit/selsubitem/mainView.action";
	if (_type == 'option') {
		if (g_subItemId == null) {
			alert('请先选择一个套餐子项！');
			return;
		}
		_url += "?selType=option&subItemId=" + g_subItemId;
		
		selectedRows = optionStore.query();// 初始化被选择的记录
	} else {
		selectedRows = subStore.query();// 初始化被选择的记录
	}
	var frameId = 'ifr_selProduct';
	if (subItemDlg == null) {
		var option = {
			title : "选择出品",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			subItemDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		subItemDlg.show();
	}
}

function closeSubItemDlg(data, selType) {
	require([ "dojo/_base/array" ], function(array) {
		var isRepeat = array.some(data, function(item, i) {
			var _itemId = item.itemId;
			var repeat = false;
			if (selType == 'option') {
				var rows = optionStore.query();
				return array.some(rows, function(row, i) {
					if (row.optionId == _itemId) {
						alert(row.optionName + "已存在！");
						return true;
					}
				});
			} else {
				var rows = subStore.query();
				return array.some(rows, function(row, i) {
					if (row.subItemId == _itemId) {
						alert(row.subItemName + "已存在！");
						return true;
					}
				});
			}
		});

		if (!isRepeat) {
			addData(data, selType);
			subItemDlg.hide();
		}
	});
}

function delSubItem() {
	var selectArr = [];
	for ( var subItemId in subItemGrid.selection) {
		if (subItemGrid.selection[subItemId]) {
			selectArr.push(subItemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
	if (confirm("确定删除选定的子项吗？子项相关的换品也会被删除")) {
		for (var i = 0; i < selectArr.length; i++) {
			subStore.remove(selectArr[i]);
		}
		reloadData([], 'option');
		g_subItemId = null;
	}
}

function saveSubItem() {
	subItemGrid.save().then(function(value) {
		var _url = appRoot + "/hq/item/suit/saveSubItem.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					suitId : g_foodId,
					jsonData : JSON.stringify(subStore.query())
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("提交成功！");
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

function saveOption() {
	optionGrid.save().then(function(value) {
		var _url = appRoot + "/hq/item/suit/saveOption.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					suitId : g_foodId,
					subItemId : g_subItemId,
					jsonData : JSON.stringify(optionStore.query())
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("提交成功！");
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

function delOption() {
	var selectArr = [];
	for ( var optionId in optionGrid.selection) {
		if (optionGrid.selection[optionId]) {
			selectArr.push(optionId);
			optionStore.remove(optionId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
}