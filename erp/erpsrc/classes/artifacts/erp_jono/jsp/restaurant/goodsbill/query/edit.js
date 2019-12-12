require([ "dojo/ready", "dojo/parser", "dijit/form/Select", "dijit/form/Button", "dijit/Dialog" ], function(ready,
		parser) {
	ready(function() {
		parser.parse();
		addEvent();
		initGrid();
	});
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});

	dojo.query('#delayPredict, #purchasePredict, #safetyStock').onchange(function(e) {
		calcSellPredict();
	});
}

function calcSellPredict() {
	function parseValue(_id) {
		var value = dojo.byId(_id).value;
		if (value == '') {
			value = 0;
		} else {
			value = parseFloat(value);
		}
		return value;
	}

	var sellPredict = parseValue('delayPredict') + parseValue('purchasePredict') + parseValue('safetyStock');
	dojo.byId('sellPredict').value = sellPredict;
	dojo.byId('sellPredictText').innerHTML = sellPredict;
}

function calcSuggest() {
	var refDateStart1 = dojo.byId('refDateStart1').value;
	var refDateEnd1 = dojo.byId('refDateEnd1').value;
	var refDateStart2 = dojo.byId('refDateStart2').value;
	var refDateEnd2 = dojo.byId('refDateEnd2').value;
	var refDateStart3 = dojo.byId('refDateStart3').value;
	var refDateEnd3 = dojo.byId('refDateEnd3').value;

	// 将对一组字符串的判断转换为对一个数字的判断
	var count = 0;
	if (refDateStart1 != '' && refDateEnd1 != '') {
		count++;
	}
	if (refDateStart2 != '' && refDateEnd2 != '') {
		count++;
	}
	if (refDateStart3 != '' && refDateEnd3 != '') {
		count++;
	}

	grid.save().then(function(value) {
		if (count == 0) {
			alert('请先选择一组参考日期！');
			return;
		}

		// 若选择的原料为空，则给予提示
		var rows = dataStore.query();
		if (rows.length == 0) {
			alert("请选择原料！");
			return;
		}

		// 显示遮罩层
		standby.show();

		var _url = appRoot + "/restaurant/goodsbill/create/calcSuggest.action";
		_url = getUrl(_url);
		
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.post(_url, {
				handleAs : "json",
				data : {
					branchId : dojo.byId('shopId').value,
					jsonData : JSON.stringify(dataStore.query()),// dataStore.query()
					// 所选原料
					refDateStart1 : refDateStart1,
					refDateEnd1 : refDateEnd1,
					refDateStart2 : refDateStart2,
					refDateEnd2 : refDateEnd2,
					refDateStart3 : refDateStart3,
					refDateEnd3 : refDateEnd3
				}
			}).then(function(data) {
				reloadData(data);
				console.dir(data)
				// 计算建议值完毕，隐藏遮罩层
				standby.hide();
			}, function(err) {
			});
		});

	}, function(err) {
		console.log(err);
	});

}

// 装载数据 计算建议值 与 选择模板 会调用该方法
function reloadData(data) {
	// 首先清空原料数据
	dataStore.setData([]);
	grid.set('store', dataStore);

	addData(data);
}

function addData(data) {
	var rows = dataStore.query();
	var sellPredict = dojo.byId('sellPredict').value;
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			var inventory = row.inventory;
			if (inventory == undefined) {
				inventory = 0;
				row.inventory = 0;
			}
			// 万元用量平均值*预估销售额-库存量=建议值
			// alert(row.amtTTCNY1)
			if (row.amtTTCNY1 != undefined) {
				var requireCount1 = parseFloat((row.amtTTCNY1 * sellPredict).toFixed(2));
				row.requireCount1 = requireCount1;
				row.suggestCount1 = getSuggest(requireCount1);
			}
			if (row.amtTTCNY2 != undefined) {
				var requireCount2 = parseFloat((row.amtTTCNY2 * sellPredict).toFixed(2));
				row.requireCount2 = requireCount2;
				row.suggestCount2 = getSuggest(requireCount2);
			}
			if (row.amtTTCNY3 != undefined) {
				var requireCount3 = parseFloat((row.amtTTCNY3 * sellPredict).toFixed(2));
				row.requireCount3 = requireCount3;
				row.suggestCount3 = getSuggest(requireCount3);
			}

			function getSuggest(_require) {
				var suggest = parseFloat((_require - inventory).toFixed(2));
				if (suggest < 0) {
					return 0;
				}
				return suggest;
			}

			if (row.orderCount == undefined) {
				row.orderCount = 0;
			}
			if (row.itemUnitPrice == undefined) {
				row.itemUnitPrice = 0;
			}
			if (row.payAmt == undefined) {
				row.payAmt = 0;
			}
			dataStore.put(row);
		});
	});
}

function doValidate() {
	if (!validateNum('delayPredict', '延滞期金额')) {
		return false;
	}

	if (!validateNum('purchasePredict', '进货周期金额')) {
		return false;
	}

	if (!validateNum('safetyStock', '安全存量')) {
		return false;
	}

	return true;
}

var numReg = /^(-)?[0-9]{1,}\.{0,1}[0-9]{0,}$/g;
function validateNum(_id, _text) {
	var value = dojo.byId(_id).value.trim();
	if (value != '' && value.match(numReg) == null) {
		alert(_text + "只能为数字！");
		return false;
	}
	return true;
}

function validateGrid() {
	if (!validateColumn(grid, 'inventory')) {
		return false;
	}

	if (!validateColumn(grid, 'orderCount')) {
		return false;
	}
	return true;
}

/**
 * 保存之前计算要货总额和主要要货
 */
function calcAllPayAmt() {
	var rows = dataStore.query();
	var allPayAmt = 0.0, maxPayAmt = -1.0;
	var maxPayItem = "";
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(rows, function(item, i) {
			var payAmt = item.payAmt;
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + item.itemId + "]" + item.itemName;
			}
		});
	});
	dojo.byId('allPayAmt').value = allPayAmt;
	dojo.byId('maxPayItem').value = maxPayItem;
}

/**
 * 二级页面无论修改、删除还是作废操作，统一处理。若 版本号不一致，则不能继续操作
 */
function doSave() {
	if (!validateGrid()) {
		return;
	}
	if (!doValidate()) {
		return;
	}

	if (dojo.byId('preVersion').value.length == 0) {
		dojo.byId('preVersion').value = preVersion;
	}

	// if (!checkFormVersion(formId, 'preVersion', 'currentVersion')) {
	// return;
	// }

	standby.show();

	grid.save();
	// var rows = [];
	// for ( var id in changeIdObj) {// 只获取修改过的记录
	// rows.push(dataStore.get(id));
	// }
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	// 如果有数据修改，则重新计算要货总额和主要要货
	// calcAllPayAmt();

	var _url = appRoot + "/restaurant/goodsbill/query/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("提交成功！");
				// doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			standby.hide();
			alert("操作失败！");
		});
	});
}

var dataStore = null;
var grid = null;
var changeIdObj = {};

var standby = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dojo/_base/declare", "dgrid/Keyboard", "dijit/form/NumberTextBox",
			"dojo/_base/lang", "dojo/on", "dojo/keys", "dgrid/extensions/ColumnResizer", "dgrid/ColumnSet",
			"dojo/query", "dojox/widget/Standby", "dojo/domReady!" ], function(OnDemandGrid, editor, declare, Keyboard,
			NumberTextBox, lang, on, keys, ColumnResizer, ColumnSet, query, Standby) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, ColumnSet ]);
		grid = new CustomGrid({
			columnSets : getColumn(editor, NumberTextBox, lang),
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'orderCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
				dataStore.put(row);
			}
			changeIdObj[event.rowId] = event.rowId;
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

		on(grid, 'dgrid-cellfocusin', function(e) {
			if (e.parentType != undefined) {// 鼠标事件不予处理
				var $input = query('.dijitInputField input[type=text]', e.target);

				if (!isEmpty($input[0])) {
					$input[0].select();
				}
			}
		});

		on(grid, 'mousedown', function(e) {
			grid.focus(e.target);
		});

		grid.startup();
		loadGridData();

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/goodsbill/query/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "rownumber",
				data : data
			}));

			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn(editor, NumberTextBox, lang) {
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

	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		renderCell : function(object, data) {
			return imageFmt(data, object.itemId);
		},
		sortable : false
	} ] ], [ [ lang.mixin(getNumEditor(), {
		label : "库存量",
		field : "inventory",
		className : 'text-right',
		sortable : false
	}), {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, lang.mixin(getNumEditor(), {
		label : "订货量",
		field : "orderCount",
		className : 'text-right',
		sortable : false
	}), {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : branchType == 'FRANCHISEE' ? "加盟价" : "标准价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "建议1",
		field : "suggestCount1",
		className : 'text-right',
		sortable : false
	}, {
		label : "建议2",
		field : "suggestCount2",
		className : 'text-right',
		sortable : false
	}, {
		label : "建议3",
		field : "suggestCount3",
		className : 'text-right',
		sortable : false
	}, {
		label : "需求1",
		field : "requireCount1",
		className : 'text-right',
		sortable : false
	}, {
		label : "需求2",
		field : "requireCount2",
		className : 'text-right',
		sortable : false
	}, {
		label : "需求3",
		field : "requireCount3",
		className : 'text-right',
		sortable : false
	}, {
		label : "万用1",
		field : "amtTTCNY1",
		className : 'text-right',
		sortable : false
	}, {
		label : "万用2",
		field : "amtTTCNY2",
		className : 'text-right',
		sortable : false
	}, {
		label : "万用3",
		field : "amtTTCNY3",
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ] ] ];
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

	var _url = appRoot + "/restaurant/goodsbill/query/doDelete.action";
	_url = getUrl(_url);
	
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
				alert("刪除失败！");
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
	_url = getUrl(_url);
	
	if (confirm("确定作废单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			standby.show();
			xhr.post(_url, {
				handleAs : "json",
				data : {
					currentVersion : dojo.byId('currentVersion').value,
					formId : formId,
					cancelMsg : "作废要货单"
				}
			}).then(function(data) {
				standby.hide();
				alert("操作成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
				standby.hide();
				alert("操作失败！");
			});
		});
	}
}

function doClose() {
	closeTab(tabId);
}
