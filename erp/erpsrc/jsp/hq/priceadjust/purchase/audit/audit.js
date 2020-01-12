require([ "dojo", "dojo/date", "dojo/date/locale", "dojo/ready" ], function(dojo, date, locale, ready) {
	ready(function() {
		// 默认为不立即生效
		dojo.byId('effectType').value = '';

		addEvent();
		initGrid();
		window.checkEffectTime = function() {
			var effectTime = dojo.byId('effectTime').value;
			if (effectTime == '') {
				alert('生效时间不能为空！');
				return false;
			}

			var parseOption = {
				datePattern : "yyyy-MM-dd",
				selector : "date"
			};
			var startDate = locale.parse(dojo.byId('startDate').value, parseOption);
			var effectTime = dojo.byId('effectTime').value;
			effectTime = locale.parse(effectTime, parseOption);
			var result = date.compare(effectTime, startDate, "date");
			if (result < 0) {
				alert("生效时间小于等于当前营业日期,请设置生效时间！")
				return false;
			} else if (result == 0) {
				if (confirm("确定立即生效吗？")) {
					dojo.byId('effectType').value = 'immediate';
					return true;
				} else {
					return false;
				}
			}
			return true;
		}
	});
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};

}

var dataStore = null;
var grid = null;
function initGrid() {
	require(
			[ "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore", "dojo/_base/declare",
					"dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dgrid/editor",
					"dijit/form/NumberTextBox", "dojo/_base/lang", "dgrid/extensions/ColumnResizer", "dojo/query",
					"dojo/on", "dojo/keys", "dgrid/Keyboard" ], function(Observable, Memory, ObjectStore, declare,
					OnDemandGrid, selector, Selection, editor, NumberTextBox, lang, ColumnResizer, query, on, keys,
					Keyboard) {
				dataStore = new Observable(new Memory({
					idProperty : "rownumber",
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
						// 计算金额差异
						var diff = row.newPrice - row.oldPrice;
						row.differentPrice = parseFloat(diff.toFixed(4));
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
					if (e.parentType != undefined) { // 鼠标事件不予处理
						var $input = query('.dijitInputField input[type=text]', e.target);

						if (!isEmpty($input[0])) {
							$input[0].select();
						}
					}
				});

				loadGridData();
			});
}

function loadGridData() {
	var _url = appRoot + "/hq/priceadjust/queryDetail.action?formId=" + formId;
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				var diff = row.newPrice - row.oldPrice;
				row.differentPrice = parseFloat(diff.toFixed(4));
				dataStore.put(row);
			});
		}, function(err) {
		});
	});
}

var numArgs = {
	style : 'width: 5em;',
	constraints : {
		min : 0,
		max : 99550,
		places : '0,4',
		pattern : "#.####"
	},
	required : true,
	invalidMessage : '请输入不多于三位小数的数字。'
};

function getColumn(selector, editor, NumberTextBox, lang) {
	var numArgs = {
		style : 'width: 100px;text-align: right;',
		constraints : {
			min : 0,
			max : 1999999999,
			places : '0,4',
			pattern : "#.####"
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
		className : 'text-center',
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
		className : 'text-center',
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
		className : 'text-right',
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "新价",
		field : "newPrice",
		className : 'text-right',
		sortable : false
	}), {
		label : "差价",
		field : "differentPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var itemDlg = null;
function selItem(_type) {
	var _url = appRoot + "/hq/priceadjust/selitem/mainView.action";
	_url += "?adjustType=" + g_adjustType;
	
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
			row.rownumber = dataStore.query().length + 1;
			dataStore.put(row);
		});
		row.differentPrice = row.newPrice - row.oldPrice;
	});
}

function doAudit() {
	// if (!doValidate()) {
	// return;
	// }
	if (!checkEffectTime()) {
		return;
	}
	dojo.byId('btn_submit').disabled = true;
	grid.save().then(function(value) {
		var rows = dataStore.query();
		dojo.byId('jsonData').value = JSON.stringify(rows);

		var _url = appRoot + "/hq/priceadjust/audit/doAudit.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				if (data.msg == 'ok') {
					alert("提交成功！");
					doClose();
				} else {
					alert("操作失败！");
				}
			}, function(err) {
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
