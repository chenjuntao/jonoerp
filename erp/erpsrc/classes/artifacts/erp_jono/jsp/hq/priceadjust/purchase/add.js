require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {
		addEvent();
		initGrid();

		if (dojo.byId('supplierId').value > 0) {
			dojo.byId('supplierId').value = '';
		}

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

var standby = null;

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
				// dgrid 视图和数据源相分离
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				row.differentPrice = (row.newPrice - row.oldPrice).toFixed(4); // 计算金额差异
				dataStore.put(row);
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
		// 保证鼠标焦点与键盘焦点的连贯性
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
		style : 'width: 100px;text-align: right;',
		constraints : {
			min : 0,
			max : 1999999999,
			places : '0,4',
			pattern: "#.####"
		},
		required : true,
		invalidMessage : '请输入不多于三位小数的数字。'
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
		label : "包装因子",
		field : "itemPackager",
		sortable : false
	}, {
		label : "原价",
		field : "oldPrice",
		className:'text-right',
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "新价",
		field : "newPrice",
		className:'text-right',
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

var itemDlg = null;
function addItem() {
	var supplierId = dojo.byId('supplierId').value;
	if (supplierId == '') {
		alert('请选择供应商！');
		return;
	}
	var _url = appRoot + "/hq/priceadjust/selitem/mainView.action";
	_url += "?adjustType=" + g_adjustType + "&supplierId=" + supplierId;
	var branchType = dojo.byId('branchType').value;
	if (branchType == 'CENTRALFACTORY') {
		_url += "&itemType=SEMIS"; // 中央工厂调整半成品的售卖价
	} else {
		_url += "&itemType=R_S"; // 其它供应商可以调整原料和半成品（外部采购的情况）的售卖价
	}
	
	var frameId = 'ifr_selItem';
	if (itemDlg == null) {
		var option = {
			title : "选择商品",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			itemDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.location = _url;
		itemDlg.show();
	}
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
			dataStore.put(row);
		});
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
	var supplierId = dojo.byId('supplierId').value;
	if (supplierId == '') {
		alert('请选择供应商！');
		return;
	}
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert('请选择原料！');
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
