dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.query('.Wdate').onfocus(function(e) {
		WdatePicker();
	});

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dojo/_base/declare", "dgrid/Keyboard", "dijit/form/NumberTextBox",
			"dojo/domReady!" ], function(OnDemandGrid, editor, declare, Keyboard, NumberTextBox) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.on("dgrid-datachange", function(event) {
			changeIdObj[event.rowId] = event.rowId;

			var field = event.cell.column.field;
			if (field == 'applyCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;
				row.payAmt = reserve2Decimal(event.value * row.unitPrice); // 计算金额
				dataStore.put(row);
			}
		});
		grid.startup();
		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/allocateitem/query/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore = new Observable(new Memory({
				idProperty : "itemId",
				data : data
			}));
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "原料编码",
		field : "itemId"
	}, {
		label : "原料名称",
		field : "itemName"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, editor({
		label : "申请调拨数量",
		field : "applyCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		}
	}, NumberTextBox), {
		label : "标准单价",
		field : "unitPrice",
		className : 'grid-number'
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number'
	}, {
		label : "",
		field : "none"
	} ];
}
function validateGrid() {
	if (!validateColumn(grid, 'applyCount')) {
		return false;
	}
	return true;
}

function doValidate() {
	var auditTime = dojo.byId('auditTime').value;
	if (auditTime.trim() == '') {
		alert("审核日期不能为空！");
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

/**
 * 保存之前计算要货总额和主要要货
 */
function calcAllPayAmt() {
	var rows = dataStore.query();
	var allPayAmt = 0.0, maxPayAmt = 0.0;
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

function reserve2Decimal(number) {
	return Math.round(number * 100) / 100;
}

function doAudit() {
	if (!doValidate()) {
		return;
	}

	if (!validateGrid()) {
		return;
	}

	grid.save();
	var rows = [];
	for ( var id in changeIdObj) {// 只获取修改过的记录
		rows.push(dataStore.get(id));
	}
	// var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	if (rows.length > 0) { // 如果有数据修改，则重新计算要货总额和主要要货
		calcAllPayAmt();
	}
	dojo.byId('btn_submit').disabled = true;
	var _url = appRoot + "/restaurant/allocateitem/audit/doAudit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("审核成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId);
}
