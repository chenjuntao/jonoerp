require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {
		addEvent();
		initGrid();

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

var standby = null;
var changeIdObj = {};

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
}

function freshGrid() {
	for (;;) {
		var data = dataStore.data;

		if (data.length > 0) {
			dataStore.remove(data[0].itemId);
		} else {
			break;
		}
	}

}

var dataStore = null;
var grid = null;
function initGrid() {
	require([ 
	          "dojo/store/Observable", 
	          "dojo/store/Memory", 
	          "dojo/data/ObjectStore",
	          "dojo/_base/declare",
	          "dgrid/OnDemandGrid", 
	          "dgrid/selector",
	          "dgrid/Selection", 
	          "dgrid/Keyboard", 
	          "dgrid/ColumnSet",
	          "dgrid/editor",
	          "dijit/form/NumberTextBox",
	          "dojo/parser",
	          "dojo/query", 
	          "dojo/on", 
	          "dojo/keys",
	          "dojo/_base/lang", 
	          "dgrid/extensions/ColumnResizer" 
          ], function(Observable, Memory, ObjectStore, declare,	OnDemandGrid, selector, Selection, Keyboard, ColumnSet, editor, NumberTextBox, parser, query, on, keys,
			lang, ColumnResizer) {
		parser.parse();
		dataStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, ColumnResizer, Keyboard ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector, editor, NumberTextBox, lang),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'newPrice') {
				grid.save();
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				console.log(event.value);
				row.differentPrice = (row.newPrice - row.oldPrice).toFixed(4); // 计算金额差异
				dataStore.put(row);
				changeIdObj[event.rowId] = event.rowId;
			}
		});
		// TODO
		on(grid, 'keydown', function(e) {
			if (e.keyCode === keys.UP_ARROW) {
				Keyboard.moveFocusUp.call(grid, e);
			} else if (e.keyCode === keys.DOWN_ARROW) {
				Keyboard.moveFocusDown.call(grid, e);
			} else if (e.keyCode === keys.ENTER) {
				Keyboard.moveFocusDown.call(grid, e);
			}
		});
		
		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});
	});
}

function getColumn(selector, editor, NumberTextBox, lang) {
	var numArgs = {
		style : 'width: 105px;text-align: right;',
		constraints : {
			min : 0,
			max : 1999999999,
			places : '0,4',
			pattern: "#.####"
		},
		required : true,
		invalidMessage : '请输入不多于四位小数的数字。'
	};

	function getNumEditor() {
		return editor({
			className : 'grid-number edit-note',
			editorArgs : numArgs
		}, NumberTextBox);
	}

	return [ selector({
		field : 'rownumber',
		sortable : false
	}), {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "商品编码",
		field : "itemId",
		className:'text-center',
		sortable : false
	}, {
		label : "商品名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		},
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		className:'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "原价",
		field : "oldPrice",
		className:'text-right',
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "新价",
		field : "newPrice",
		sortable : false
	}), {
		label : "差价",
		field : "differentPrice",
		className:'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function closeItemDlg(data, selType) {
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
			console.dir(data);
			addData(data);
			itemDlg.hide();
		}
	});
}

function addData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = 1;
			var items = dataStore.query();
			if (items.length > 0) {
				row.rownumber = items[items.length - 1].rownumber + 1;// 应对中间删除的情况
			}
			row.differentPrice = 0;
			row.newPrice = row.oldPrice = row.receivePrice;
			dataStore.put(row);
		});
		
		console.dir(dataStore);
	});
}

function delItem() {
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

function doSave() {
	var providerId = dojo.byId('providerId').value;
	if (providerId == '') {
		alert('请选择供应商！');
		return;
	}
	var rows = [];
	for ( var id in changeIdObj) {// 只获取修改过的记录
		rows.push(dataStore.get(id));
	}
	if (rows.length == 0) {
		alert('请修改原料价格！');
		return;
	}

	if (!validateColumn(grid, 'newPrice')) {
		return false;
	}

	standby.show();

	grid.save().then(function(value) {
		dojo.byId('jsonData').value = JSON.stringify(rows);

		var _url = appRoot + "/hq/priceadjust/createBill.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				standby.hide();

				if (data.msg == 'ok') {
					alert("提交成功！");
					changeIdObj = {};
					freshGrid();
				} else {
					alert("操作失败！");
				}
			}, function(err) {
				standby.hide();
			});
		});
	}, function(err) {
		console.log(err);
	}, function(update) {
		console.log(update);
	});
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
