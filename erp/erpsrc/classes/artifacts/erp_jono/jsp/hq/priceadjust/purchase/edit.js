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
	require([ "dojo/store/Observable", "dojo/store/Memory", "dojo/data/ObjectStore", "dojo/_base/declare",
			"dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection", "dgrid/editor", "dijit/form/NumberTextBox",
			"dojo/_base/lang", "dojo/query", "dojo/on", "dojo/keys", "dgrid/Keyboard" ], function(Observable, Memory,
			ObjectStore, declare, OnDemandGrid, selector, Selection, editor, NumberTextBox, lang, query, on, keys,
			Keyboard) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));

		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(selector, editor, NumberTextBox, lang),
			cellNavigation : false,
			allowSelectAll : true,
			loadingMessage : '加载中...'
		}, "dataGrid");

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
		
		on(grid,"dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'newPrice') {
				// dgrid 视图和数据源相分离
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				row.differentPrice = row.newPrice - row.oldPrice; // 计算金额差异
				dataStore.put(row);
			}
		});
		grid.startup();

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
				dataStore.put(row);
			});
		}, function(err) {
		});
	});
}

function getColumn(selector, editor, NumberTextBox, lang) {
	var numArgs = {
		style : 'width: 5em;text-align: right;',
		constraints : {
			min : 0,
			max : 1999999999,
			places : '0,3'
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
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "新价",
		field : "newPrice",
		sortable : false
	}), {
		label : "差价",
		field : "differentPrice",
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

var itemDlg = null;
function addItem(_type) {
	var _url = appRoot + "/hq/priceadjust/selitem/mainView.action";
	_url += "?adjustType=" + g_adjustType;

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

function doValidate() {
	return true;
}

function doSave() {
	if (!doValidate()) {
		return;
	}

	standby.show();

	grid.save().then(function(value) {
		var rows = dataStore.query();
		dojo.byId('jsonData').value = JSON.stringify(rows);

		var _url = appRoot + "/hq/priceadjust/updateBill.action";
		
		require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				standby.hide();

				if (data.msg == 'ok') {
					alert("提交成功！");
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

/**
 * 二级页面无论修改、删除还是作废操作，统一处理。若 版本号不一致，则不能继续操作
 */
function doDelete() {
	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}

	var _url = appRoot + "/hq/priceadjust/purchase/doDelete.action";
	
	if (confirm("确定删除单据吗？")) {
		standby.show();

		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					currentVersion : dojo.byId('currentVersion').value,
					formId : formId
				}
			}).then(function(data) {
				standby.hide();
				alert("刪除成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
				standby.hide();
			});
		});
	}
}

/**
 * 二级页面无论修改、删除还是作废操作，统一处理。若 版本号不一致，则不能继续操作
 */
function doInvalid() {
	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
		return;
	}

	var _url = appRoot + "/restaurant/goodsbill/query/doInvalid.action";

	if (confirm("确定作废单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			standby.show();
			xhr.post(_url, {
				handleAs : "json",
				data : {
					currentVersion : dojo.byId('currentVersion').value,
					formId : formId,
					cancelMsg : "作废调价单"
				}
			}).then(function(data) {
				standby.hide();
				alert("操作成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
				standby.hide();
			});
		});
	}
}

function doClose() {
	closeTab(tabId);
}
