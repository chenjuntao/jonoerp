require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dojo/store/Observable", "dojo/store/Memory", "dgrid/OnDemandGrid", "dojo/_base/declare",
			"dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/on", "dojo/keys", "dojo/query" ],
			function(Observable, Memory, OnDemandGrid, declare, Keyboard, editor, NumberTextBox, on, keys, query) {
				dataStore = new Observable(new Memory({
					idProperty : "rownumber",
					data : []
				}));

				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : getColumn(editor, NumberTextBox),
					sort : [ {
						attribute : "rownumber",
						descending : false
					} ],
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.on("dgrid-datachange", dataChangeHandler);
				grid.startup();

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

				queryData();
			});
}

function queryData() {
	var _url = appRoot + "/restaurant/putinstorage/distribution/queryDetail.action?formId=" + dojo.byId('formId').value;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				row.receiveCount = row.deliveryCount;
				dataStore.put(row);
			});
		}, function(err) {
		});
	});
}

function dataChangeHandler(event) {
	var field = event.cell.column.field;
	if (field == 'deliveryCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		row.differentCount = row.deliveryCount - event.value; // 计算差异数，实发数-实收数
		dataStore.put(row);
	}
}

function getColumn(editor, NumberTextBox) {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, editor({
		label : "配送数",
		field : "deliveryCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				min : 0,
				max : 99550,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
		label : "单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function validateGrid() {
	if (!validateColumn(grid, 'deliveryCount')) {
		return false;
	}
	return true;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已入库') {
		alert("配送单已入库！");
		return false;
	}
	return true;
}

function doAudit() {
	grid.save();
	if (!validateGrid()) {
		return;
	}
	
	var formId = dojo.byId('formId').value;
	if (!checkStatus(formId)) {
		return;
	}
	
//	dojo.byId('auditBtn').disabled = true;
	
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + '/lc/request/distribution/audit/doAudit.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId, 'doQuery');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}
