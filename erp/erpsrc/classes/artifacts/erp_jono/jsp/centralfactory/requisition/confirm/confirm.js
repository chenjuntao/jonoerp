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
		releaseLock(formId);
	};
}

var dataStore = null;
var grid = null;
var changeIdObj = {};
var standby = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query", "dojox/widget/Standby",
			"dojo/domReady!" ],

	function(OnDemandGrid, editor, NumberTextBox, declare, Keyboard, ColumnResizer, on, keys, query, Standby) {
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			columns : getColumn(editor, NumberTextBox),
			loadingMessage : '加载中...'
		}, "detailDataGrid");

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

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'receiveCount') {
				var row = dataStore.get(event.rowId);
				var differentCount = new Number(row.itemCount - row.receivedCount);
				grid.save();// very important
				var rows = dataStore.query();
				for (var i = 0; i < rows.length; i++) {
					var otherCount = new Number(rows[i].itemCount - rows[i].receivedCount);
					var rate = parseFloat((otherCount / differentCount).toFixed(4));// 计算比率
					rows[i].receiveCount = parseFloat((event.value * rate).toFixed(4));// 每条数据都按比率刷新一遍
					var num = new Number(otherCount - rows[i].receivedCount);
					rows[i].differentCount = num.toFixed(4); // 计算实领差异
					dataStore.put(rows[i]);
				}
			}
		});

		grid.startup();
		loadGridData();
	});
}

function loadGridData() {
	var _url = appRoot + "/cf/requisition/manage/queryDetail.action?formId=" + formId;
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

function getColumn(editor, NumberTextBox) {
	return [ {
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
		label : "计划领料数",
		field : "itemCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "已领料数",
		field : "receivedCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "未领料数",
		field : "differentCount",
		className : 'grid-number',
		sortable : false,
		formatter : function(value, rowData) {
			var num = new Number(rowData.itemCount - rowData.receivedCount);
			if (num < 0) {
				return 0;
			}
			return num.toFixed(2);
		}
	}, editor({
		label : "领料数",
		field : "receiveCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				min : 0,
				max : 1999999999,
				pattern : '###.####',
				row : this
			},
			required : true,
			invalidMessage : '请输入不多于四位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
		label : "",
		field : "none",
		sortable : false
	} ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;

	if (status == '已结案') {
		alert("工单已结案，不能进行领料！");
		return false;
	}
	return true;
}

function validateGrid() {
	if (!validateColumn(grid, 'receiveCount')) {
		return false;
	}
	return true;
}

function doConfirm() {
	if (!checkStatus(dojo.byId('formRefId').value)) {
		return;
	}
	if (!validateGrid()) {
		return;
	}
	grid.save();
	var rows = dataStore.query();
	var flag = true;
	var flag2 = false;
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].receiveCount == 0) {
			flag = false;
		} else {
			flag2 = true;
		}
	}
	if (!flag) {
		if (!confirm("含有物料领料数为0，是否继续提交？")) {
			return;
		}
		if (!flag2) {
			alert("所有原料领料数都为0，请修改领料数再提交！");
			return;
		}
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/cf/requisition/confirm/doConfirm.action";
	_url = getUrl(_url);
	
	standby.show();

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();

			if (data.msg == 'ok') {
				alert("确认领料成功！");
				doClose();
			} else {
				alert("确认领料失败！");
			}
		}, function(err) {
			alert("确认领料失败！");
			standby.hide();
		});
	});
}

function doClose() {
	closeTab(tabId, 'doQuery');
}
