dojo.ready(function() {
	addEvent();
	initGrid();
	initData();
});

function initData() {
	dojo.byId('queryStr').value = " h.FORM_ID = '" + formId + "'  AND i.ITEM_COUNT > i.RECEIVED_COUNT ";
}

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
function initGrid() {
	require([ "dijit/form/NumberTextBox", "dgrid/editor", "dgrid/OnDemandGrid", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query", "dojox/widget/Standby",
			"dojo/domReady!" ], function(NumberTextBox, editor, OnDemandGrid, declare, Keyboard, ColumnResizer, on,
			keys, query, Standby) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
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

		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();

		on(grid, "dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'receiveCount') {
				var row = dataStore.get(event.rowId);
				var differentCount = new Number(row.itemCount - row.receivedCount);
				grid.save();// very important
				var rows = dataStore.query();
				for (var i = 0; i < rows.length; i++) {
					var otherCount = new Number(rows[i].itemCount - rows[i].receivedCount);
					var rate = parseFloat((otherCount / differentCount).toFixed(5));// 计算比率
					console.log(rate);
					console.log(event.value);
					rows[i].receiveCount = event.value * rate;// 每条数据都按比率刷新一遍
					// alert(event.value * rate)
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
	var _url = appRoot + "/cf/requisition/create/transformToDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory", "dojo/dom-form" ], function(xhr,
			Observable, Memory, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
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
		sortable : false
	}, {
		label : "已领料数量",
		field : "receivedCount",
		sortable : false
	}, {
		label : "未领料数",
		field : "differentCount",
		sortable : false,
		formatter : function(value, rowData) {
			var num = new Number(rowData.itemCount - rowData.receivedCount);
			if (num < 0) {
				return 0;
			}
			return num.toFixed(4);
		}
	}, editor({
		label : "领料数",
		field : "receiveCount",
		className : 'grid-number',
		editorArgs : {
			style : 'width:70px; text-align: right ;',
			constraints : {
				min : 0,
				max : 1999999999,
				pattern : '#.####',
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

function validateGrid() {
	if (!validateColumn(grid, 'receiveCount')) {
		return false;
	}
	return true;
}

function doCommit(row) {
	if (!validateGrid()) {
		return;
	}

	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("没有成本卡信息，请先补全！");
		return;
	}
	var flag = true;
	for (var i = 0; i < rows.length; i++) {
		if (rows[i].receiveCount == 0) {
			flag = false;
		}
	}
	if (!flag) {
		if (!confirm("含有物料领料数为0，是否继续提交？")) {
			return;
		}
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);
	standby.show();
	var _url = appRoot + "/cf/requisition/create/doCommit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("领料单生成成功！");
				standby.hide();
				doClose();
			} else {
				alert("领料单生成失败！");
				standby.hide();
			}
		}, function(err) {
			alert("领料单生成失败！");
			standby.hide();
		});
	});
}

function doClose() {
	closeTab(tabId, "doQuery");
}
