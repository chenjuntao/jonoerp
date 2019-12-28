require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
}

function validateGrid() {
	if (!validateColumn(grid, 'receiveCount')) {
		return false;
	}
	return true;
}

function doSubmit() {
	// var select = dojo.byId('storageId');
	// dojo.byId('storage').value=select.options[select.selectedIndex].value;
	if (!validateGrid()) {
		return;
	}

	grid.save();
	var rows = dataStore.query();
	if (rows.length == 0) {
		alert("请选择原料！");
		return;
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);
	standby.show();
	var _url = appRoot + "/cf/requisition/create/doCommit.action";
	_url = getUrl(_url);

	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
				changeIdObj = {};
				alert("提交成功！");
				location.reload();// 刷新页面
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
			"dojox/widget/Standby", "dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox",
			"dgrid/extensions/ColumnResizer", "dojo/on", "dojo/keys", "dojo/query" ], function(OnDemandGrid,
			Observable, Memory, declare, Standby, Keyboard, editor, NumberTextBox, ColumnResizer, on, keys, query) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			columns : getColumn(editor, NumberTextBox),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");
		grid.startup();

		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
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

	});
}

function dataChangeHandler(event) {
	var field = event.cell.column.field;
	if (field == 'receiveCount') {
		grid.save();// very important
		var row = dataStore.get(event.rowId);
		row[field] = event.value;// necessary
		row.payAmt = parseFloat((event.value * row.itemUnitPrice).toFixed(2)); // 计算金额
		dataStore.put(row);
	}
}

function loadData(data) {
	require([ "dojo/_base/array" ], function(array) {
		array.forEach(data, function(row, i) {
			row.rownumber = i + 1;
			row.receiveCount = 0;
		});
		console.dir(data);
		dataStore.setData(data);
		grid.set("store", dataStore);
	});
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
		label : "单位",
		field : "itemDimension",
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "类别编码",
		field : "itemCategory",
		sortable : false
	}, {
		label : "供应商",
		field : "provider",
		sortable : false
	}, editor({
		label : "领料数",
		field : "receiveCount",
		className : 'grid-number',
		sortable : false,
		editorArgs : {
			style : 'width: 5em;text-align: right;',
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
		field : "none",
		sortable : false
	} ];
}

var materialDlg = null;
function selMaterial() {
	var frameId = 'ifr_selMaterial';
	if (materialDlg == null) {
		var _url = appRoot + "/lc/request/purchase/manual/selmaterial/mainView.action";
		_url = getUrl(_url);

		var option = {
			title : "自选原料",
			url : _url,
			frameId : frameId,
			width : "850px",
			height : "300px"
		}
		createDialog(option, function(iDlg) {
			materialDlg = iDlg;
		});
	} else {
		var ifrWindow = dojo.byId(frameId).contentWindow;
		ifrWindow.loadData();
		materialDlg.show();
	}
}

function closeMaterialDlg(data) {
	loadData(data);
	materialDlg.hide();
}
