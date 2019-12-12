var typeData = [ {
	"key" : "10",
	"value" : "主料"
}, {
	"key" : "20",
	"value" : "辅料"
}, {
	"key" : "30",
	"value" : "配料"
} ];

var grid = null;
var dataStore = null;
var typeStore = null;
var queryData = null;

require([ "dojo", "dojo/ready", "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore",
		"dojo/_base/declare", "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dgrid/editor",
		"dijit/form/NumberTextBox", "dijit/form/Select", "dojo/_base/lang", "dgrid/extensions/ColumnResizer",
		"dojo/request/xhr", "dojo/_base/array", 'dgrid/extensions/ColumnHider' ], function(dojo, ready, Observable,
		Memory, ObjectStore, declare, OnDemandGrid, selector, Selection, editor, NumberTextBox, Select, lang,
		ColumnResizer, xhr, array, ColumnHider) {
	ready(function() {
		initGrid();
	});

	function initGrid() {
		var _url = appRoot + "/restaurant/costcard/doQuery.action?foodId=" + g_foodId;

		dataStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));
		typeStore = new ObjectStore({
			objectStore : new Observable(new Memory({
				idProperty : "value",
				data : typeData,
				sort : [ {// 这里排序不起作用，要何如做才行，以后再研究
					attribute : "key",
					descending : false
				} ]
			})),
			labelProperty : "value"
		});

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer, ColumnHider ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on("dgrid-refresh-complete", function(evt) {
			countPrice(dataStore);
		});

		/**
		 * 进入页面时初始化，暂时不支持动态计算；求和原料的金额，算出出品的价格（成本价）
		 */
		function countPrice(dataStore) {
			var rows = dataStore.query();
			require([ "dojo/dom", "dojo/_base/array" ], function(dom, array) {
				var productPrice = 0;
				array.forEach(rows, function(row, i) {
					productPrice += row.purchaseAmt;
				});
				dom.byId('productPrice').innerHTML = parseFloat(productPrice).toFixed(4);
			});
		}

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;

			if (field == 'itemCount' || field == 'itemUsageRate') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary

				row.itemGrossCount = countGrossCount(row);// 动态计算原料耗量
				// 动态计算金额
				row.purchaseAmt = countPurchaseAmt(row);
				row.benchmarkAmt = countBenchmarkAmt(row);
				dataStore.put(row);
				countPrice(dataStore);
			}

			// 原料耗量 = 净料耗量/(净料耗率/100)
			function countGrossCount(row) {
				return parseFloat((row.itemCount / (row.itemUsageRate / 100)).toFixed(4));
			}

			// 进货金额 = (原料耗量/转换因子) * 单价
			function countPurchaseAmt(row) {
				return parseFloat((row.itemGrossCount / row.unitConvertFactor * row.purchasePrice).toFixed(4));
			}

			// 标准金额
			function countBenchmarkAmt(row) {
				return parseFloat((row.itemGrossCount / row.unitConvertFactor * row.benchmarkPrice).toFixed(4));
			}

		});

		grid.startup();
		queryData();
	}

	function getColumn() {
		function getNumEditor() {
			return editor({
				className : 'grid-number edit-note',
				editorArgs : numArgs
			}, NumberTextBox, 'click');
		}
		function getSelEditor(_store, _width) {
			return editor({
				editorArgs : {
					store : _store,
					maxHeight : 150,
					style : "width: " + _width + "px;"
				},
				className : 'edit-note'
			}, Select, 'click');
		}
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
			renderCell : function(object, data) {
				return hrefFmt(object.itemId, doChange, object);
			},
			sortable : false
		}, {
			label : "名称",
			field : "itemName",
			sortable : false
		}, {
			label : "库存单位",
			field : "stockDimension",
			sortable : false
		}, {
			label : "配方单位",
			field : "itemDimension",// 数据库中只存了这个字段名，其实有原料id，其它字段都动态获取是最准确的
			sortable : false
		}, {
			label : "转换因子",
			field : "unitConvertFactor",
			sortable : false
		}, lang.mixin(getNumEditor(), {// 净料耗量=(原料耗量)*净料耗率
			label : "净料耗量(配方单位)",
			field : "itemCount",
			sortable : false
		}), lang.mixin(getNumEditor(), {
			label : "净耗料率(%)",// 净料耗率=净料耗量*100/(原料耗量)
			field : "itemUsageRate",
			sortable : false
		}), ({
			label : "净料耗量(配方单位)",// old净料耗量
			field : "itemCount1",
			hidden : true,
			sortable : false
		}), ({
			label : "净耗料率(%)",// old净料耗率
			field : "itemUsageRate1",
			hidden : true,
			sortable : false
		}), {
			label : "毛耗料量(配方单位)",
			field : "itemGrossCount",
			sortable : false
		}, {
			label : "进货价(库存单位)",
			field : "purchasePrice",
			sortable : false
		}, {
			label : "配方金额(进货价)",
			field : "purchaseAmt",
			sortable : false
		}, {
			label : "标准价(库存单位)",
			field : "benchmarkPrice",
			sortable : false
		}, {
			label : "配方金额(标准价)",
			field : "benchmarkAmt",
			sortable : false
		} ];
	}

	queryData = function() {
		var _url = appRoot + "/restaurant/costcard/doQuery.action?foodId=" + g_foodId;

		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			// clear first
			dataStore.setData([]);
			grid.set("store", dataStore);

			addData(data);
			setProductPrice(data);
		}, function(err) {
		});
	}
});

/**
 * 进入页面时初始化，暂时不支持动态计算；求和原料的金额，算出出品的价格（成本价）
 */
function setProductPrice(data) {
	require([ "dojo/dom", "dojo/_base/array" ], function(dom, array) {
		var productPrice = 0;
		array.forEach(data, function(row, i) {
			productPrice += row.itemAmt;
		});
		dom.byId('productPrice').innerHTML = parseFloat(productPrice).toFixed(4);
	});
}

function addData(data) {
	require([ "dojo/dom", "dojo/_base/array" ], function(dom, array) {
		array.forEach(data, function(row, i) {
			row.rownumber = dataStore.query().length + 1;
			if (row.itemCount == undefined) {
				row.itemCount = 0;
			}
			if (row.itemUsageRate == undefined) {
				row.itemUsageRate = 100;
			}

			if (row.itemCount1 == undefined) {
				row.itemCount1 = 0;
			}
			if (row.itemUsageRate1 == undefined) {
				row.itemUsageRate1 = 100;
			}
			if (row.itemGrossCount == undefined) {
				row.itemGrossCount = 0;
			}
			if (row.purchasePrice == undefined) {
				row.purchasePrice = 0;
			}
			if (row.purchaseAmt == undefined) {
				row.purchaseAmt = 0;
			}
			if (row.benchmarkPrice == undefined) {
				row.benchmarkPrice = 0;
			}
			if (row.benchmarkAmt == undefined) {
				row.benchmarkAmt = 0;
			}
			dataStore.put(row);
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

var materialDlg = null;
var selectedRows = [];
function selMaterial() {
	selectedRows = dataStore.query();// 初始化被选择的记录
	var frameId = 'ifr_selMaterial';
	var _url = appRoot + "/hq/item/product/seltherapyitem/mainView.action?itemType=R_S";

	if (materialDlg == null) {
		var option = {
			title : "选择原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		materialDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		materialDlg.show();
	}
}

function closeItemDlg(data) {
	require([ "dojo/_base/array" ], function(array) {
		var isRepeat = array.some(data, function(item, i) {
			var _itemId = item.itemId;
			var rows = dataStore.query();
			return array.some(rows, function(row, i) {
				if (row.itemId == _itemId) {
					alert(row.itemName + "已存在！");
					return true;
				}
			});
		});

		if (!isRepeat) {
			addData(data);
			materialDlg.hide();
		}
	});
}

var changeDlg = null;
var oldItemId = null;
function doChange(row) {
	selectedRows = dataStore.query();// 初始化被选择的记录
	var frameId = 'ifr_selMaterial';
	var itemId = row.itemId;
	var itemName = row.itemName;

	var _url = appRoot + "/hq/item/product/seltherapyitem/mainView.action?itemType=R_S&itemId=" + itemId;

	if (changeDlg == null) {
		var option = {
			title : "替换原料[" + itemId + "]" + itemName,
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		changeDlg = createDialog(option);
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		changeDlg.show();
	}
}

function delMaterial() {
	var selectArr = [];
	for ( var itemId in grid.selection) {
		if (grid.selection[itemId]) {
			selectArr.push(itemId);
			dataStore.remove(itemId);
		}
	}
	if (selectArr.length == 0) {
		alert("请选择记录！");
		return;
	}
}

function delItemId(itemId, data) {
	dataStore.remove(itemId);
	require([ "dojo/_base/array" ], function(array) {
		var isRepeat = array.some(data, function(item, i) {
			var _itemId = item.itemId;
			var rows = dataStore.query();
			return array.some(rows, function(row, i) {
				if (row.itemId == _itemId) {
					alert(row.itemName + "已存在！");
					return true;
				}
			});
		});

		if (!isRepeat) {
			addData(data);
			changeDlg.hide();
		}
	});
}

function doSave() {
	var rows = dataStore.query();
	// if (rows.length == 0) {
	// alert("原料为空，请添加原料！");
	// return;
	// }

	var change = "";
	for (var i = 0; i < rows.length; i++) {
		var item = rows[i];
		if (item.itemId == g_foodId) {
			alert("不能添加自己作为成本卡的原料！");
			return;
		}
		if (item.itemCount == 0) {
			alert(item.itemName + "净料耗量为0！");
			return;
		}

		if (item.itemCount != item.itemCount1) {
			change += "原料[" + item.itemId + "]净料耗量，由" + item.itemCount1 + "修改成" + item.itemCount + "，";
		}

		if (item.itemUsageRate != item.itemUsageRate1) {
			change += "原料[" + item.itemId + "]净料耗率，由" + item.itemUsageRate1 + "修改成" + item.itemUsageRate + "，";
		}
	}
	if (change == "") {
		change = "删除原料";
	}
	if (!confirm(change)) {
		return;
	}
	grid.save().then(function(value) {
		var _url = appRoot + "/hq/item/product/saveTherapy.action";

		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					therapyId : g_foodId,
					therapyName : g_foodName,
					change : change,
					productPrice : dojo.byId('productPrice').innerHTML,
					jsonData : JSON.stringify(dataStore.query())
				}
			}).then(function(data) {
				if (data.msg == 'ok') {
					queryData();
					alert("提交成功！");
					closeTab(tabId);
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
