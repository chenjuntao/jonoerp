require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		doQuery();
	});
});

function doValidate() {
	return true;
}

var grid = null;
var dataStore = null;
var branchIdArr = [];

function validateGrid() {
	for (var i = 0; i < branchIdArr.length; i++) {
		if (!validateColumn(grid, branchIdArr[i])) {
			return false;
		}
	}
	return true;
}

function doAudit() {
	if (!validateGrid()) {
		busyButton.cancel();
		return;
	}

	timedCount();
	dojo.byId('submit').disabled = true;
	grid.save().then(function(value) {
		require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
			var rows = dataStore.query();
			dom.byId('jsonData').value = JSON.stringify(rows);
			var _url = appRoot + "/lc/stock/picking/audit/doAudit.action";
			_url = getUrl(_url);

			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				if (data.msg == 'ok') {
					busyButton.cancel();
					alert("提交成功！");
					doClose();
				} else {
					busyButton.cancel();
					alert("操作失败！");
				}
			}, function(err) {
				busyButton.cancel();
			});
		});

	}, function(err) {
	});
}

function doSave() {
	if (!validateGrid()) {
		saveButton.cancel();
		return;
	}

	savedCount();
	dojo.byId('save').disabled = true;

	grid.save().then(function(value) {
		require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom, xhr, domForm) {
			var rows = dataStore.query();
			dom.byId('jsonData').value = JSON.stringify(rows);
			var _url = appRoot + "/lc/stock/picking/audit/doSave.action";
			_url = getUrl(_url);

			xhr.post(_url, {
				handleAs : "json",
				data : domForm.toObject("billForm")
			}).then(function(data) {
				if (data.msg == 'ok') {
					saveButton.cancel();
					alert("提交成功！");
					// doClose();
				} else {
					saveButton.cancel();
					alert("操作失败！");
				}
			}, function(err) {
				saveButton.cancel();
			});
		});

	}, function(err) {
	});
}

function doClose(_param) {
	closeTab(tabId, 'doQuery');
}

function doQuery() {
	var _url = appRoot + "/lc/stock/picking/manage/queryById.action?formId=" + formId;
	_url = getUrl(_url);

	require([ "dojo/dom", "dojo/request/xhr", "dojo/_base/array", "dgrid/editor", "dijit/form/NumberTextBox" ],
			function(dom, xhr, array, editor, NumberTextBox) {
				xhr.post(_url, {
					handleAs : "json"
				}).then(function(data) {
					branchIdArr = data.branchIds;
					dom.byId('branchIds').value = data.branchIds.join(',');
					array.forEach(data.branchCols, function(bCol, i) {
						bCol.editorArgs = {
							style : 'width: 5em;text-align: right;',
							constraints : {
								min : 0,
								max : 1999999999,
								places : '0,3'
							},
							invalidMessage : '请输入不多于三位小数的数字。'
						};

						bCol.sortable = false;

						editor(bCol, NumberTextBox);
					});

					data.branchCols.push({
						'label' : '',
						'field' : 'none'
					})

					initGrid(data);
					loadData(data);
				}, function(err) {
				});
			});
}

function loadData(data) {
	var header = data.header;
	dojo.byId('pickingBranchId').value = header.pickingBranchId;
	dojo.byId('storageId').value = header.storageId;
	dojo.byId('l_formId').innerHTML = header.formId;
	dojo.byId('l_formMaker').innerHTML = header.formMaker;
	dojo.byId('l_formTime').innerHTML = header.formTime;
	dojo.byId('l_storage').innerHTML = header.storage;
	dojo.byId('formNote').value = header.formNote;
}

var grid = null;
var dataStore = null;
var busyButton = null;
var saveButton = null;
var countdown = 120;
var t = null;

function stopCount() {
	clearTimeout(t)
}

function timedCount() {
	if (countdown == 0) {
		stopCount();
		busyButton.setLabel("确认审核");
	} else {
		busyButton.setLabel("正在提交数据..." + countdown);

		countdown--;
		t = setTimeout("timedCount()", 1000);
	}
}

var countdown2 = 5;
function savedCount() {
	if (countdown2 == 0) {
		stopCount();
		saveButton.setLabel("数据暂存");
	} else {
		saveButton.setLabel("正在提交数据..." + countdown2);

		countdown2--;
		t = setTimeout("savedCount()", 1000);
	}
}

function initGrid(_data) {
	require([ "dgrid/OnDemandGrid", "dgrid/Selection", "dgrid/selector", "dojo/_base/declare", "custom/store/Server",
			"dgrid/ColumnSet", "dojo/_base/array", "dijit/form/NumberTextBox", "dojo/store/Observable",
			"dojo/store/Cache", "dojo/store/Memory", "dojo/on", "dojo/keys", "dgrid/Keyboard", "dojo/query",
			'dojox/form/BusyButton' ], function(OnDemandGrid, Selection, selector, declare, Server, ColumnSet, array,
			NumberTextBox, Observable, Cache, Memory, on, keys, Keyboard, query, BusyButton) {
		dataStore = new Observable(new Memory({
			idProperty : "rownumber",
			data : _data.itemLst
		}));

		busyButton = BusyButton({
			id : "submit",
			label : "确认审核",
			timeout : 1000 * 120
		}, "placeHolder");

		dojo.connect(dijit.byId("submit"), "_onClick", function() {
			doAudit();
		});

		saveButton = BusyButton({
			id : "save",
			label : "数据暂存",
			timeout : 1000 * 120
		}, "saveHolder");

		dojo.connect(dijit.byId("save"), "_onClick", function() {
			doSave();
		});

		var CustomGrid = declare([ OnDemandGrid, ColumnSet, Selection, Keyboard ]);
		cols = cols.concat(_data.branchCols);
		grid = new CustomGrid({
			store : dataStore,
			sort : [ {
				attribute : "rownumber",
				descending : false
			} ],
			columnSets : getColumn(),
			loadingMessage : '加载中...'
		}, "dataGrid");

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
	});
}

function getColumn() {
	return [ [ [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "库位",
		field : "shelfName",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	} ] ], [ cols ] ];
}

var cols = [ {
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
} ];
