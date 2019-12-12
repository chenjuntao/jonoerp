require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
}

function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;
var changeIdObj = {};
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox" ],
			function(OnDemandGrid, Memory, declare, Keyboard, editor,
					NumberTextBox) {
				dataStore = new Memory({
					data : []
				});
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					columns : getColumn(editor, NumberTextBox),
					cellNavigation : false,
					loadingMessage : '加载中...',
				}, "dataGrid");

				grid.on("dgrid-datachange", dataChangeHandler);
				grid.startup();

				queryData();
			});
}

function queryData() {
	var _url = appRoot + "/restaurant/checkstorage/manage/queryDetail.action";
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ],
			function(xhr, Observable, Memory) {
				xhr.post(_url, {
					handleAs : "json",
					data : {
						formId : formId
					}
				}).then(function(data) {
					dataStore = new Observable(new Memory({
						data : data,
						idProperty : "itemId"
					}));
					grid.set("store", dataStore);
				}, function(err) {
				});
			});
}

function dataChangeHandler(event) {
	var field = event.cell.column.field;
	if (field == 'checkCount') {
	}
	changeIdObj[event.rowId] = event.rowId;
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
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "原料有效期",
		field : "expiredTime"
	}, editor({
		label : '盘点数量',
		field : 'checkCount',
		className : 'grid-number',
		editorArgs : {
			style : 'width: 4em;',
			constraints : {
				min : 0,
				max : 1999999999,
				places : '0,3',
				row : this
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		}
	}, NumberTextBox), {
		label : "",
		field : "none"
	} ];
}

function validateGrid() {
	if (!validateColumn(grid, 'checkCount')) {
		return false;
	}
	return true;
}

function doSave() {
	if (!validateGrid()) {
		return;
	}
	grid.save();
	var rows = [];
	for ( var id in changeIdObj) {// 只获取修改过的记录
		rows.push(dataStore.get(id));
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/restaurant/checkstorage/imanage/fillBill.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("提交成功！");
				doClose();
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doDelete() {
	var _url = appRoot
			+ "/restaurant/checkstorage/manage/doDelete.action?formId="
			+ formId;
	_url = getUrl(_url);
	
	if (confirm("确定删除单据吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("删除成功！");
				closeTab(tabId, 'doQuery');
			}, function(err) {
			});
		});
	}
}

function doClose() {
	closeTab(tabId, 'doQuery');
}