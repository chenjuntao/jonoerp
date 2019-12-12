require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
		loadBatch();
	});
});

function printList() {
	prn1_preview(getExportArgs());
}

function addEvent() {
	dojo.byId('branchId').onchange = loadBatch;
	dojo.byId('checkBatchId').onchange = loadCheckList;
	dojo.byId('formId').onchange = loadGridData;
	dojo.byId('btn_submit').onclick = doSubmit;
}

function loadBatch() {
	var _url = appRoot + "/restaurant/checkstorage/imanage/queryBatch.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId('branchId').value
			}
		}).then(function(data) {
			loadSelData('checkBatchId', data, '', loadCheckList);
		}, function(err) {
		});
	});
}

function loadCheckList() {
	var _url = appRoot + "/restaurant/checkstorage/imanage/queryCheckList.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				batchId : dojo.byId('checkBatchId').value
			}
		}).then(function(data) {
			loadSelData('formId', data, '', loadGridData);
		}, function(err) {
		});
	});
}

function doValidate() {
	if (!validateColumn(grid, 'checkCount')) {
		return false;
	}
	return true;
}

function doSubmit() {
	if (!doValidate()) {
		return;
	}

	grid.save();

	var rows = dataStore.query();
	if (rows.length > 0) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].checkCount == 0) {
				if (!confirm(rows[i].itemName + "输入为零，确定提交吗？")) {
					return;
				}
			}
		}
	}
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var $branchId = dojo.byId('branchId');
	dojo.byId('checkBranch').value = $branchId.options[$branchId.selectedIndex].text;

	var _url = appRoot + '/restaurant/checkstorage/iteminput/checkView.action';
	_url = getUrl(_url);

	addPostTab('billForm', '餐厅盘点清单输入确认', _url);

	// grid.save().then(function(value) {
	//		
	// }, function(err) {
	// console.log(err);
	// });
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/editor",
			"dijit/form/NumberTextBox", "dojo/keys", "dojo/on", "dgrid/Selection", "dgrid/extensions/ColumnResizer",
			"dojo/query" ], function(OnDemandGrid, Memory, declare, Keyboard, editor, NumberTextBox, keys, on,
			Selection, ColumnResizer, query) {
		dataStore = new Memory({
			data : []
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, Selection, ColumnResizer ]);
		grid = new CustomGrid({
			columns : getColumn(editor, NumberTextBox),
			loadingMessage : '加载中...',
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
	});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/checkstorage/manage/queryDetail.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ], function(xhr, Observable, Memory) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				formId : dojo.byId('formId').value
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
		label : "类别",
		field : "itemCategory",
		sortable : false
	}, {
		label : "原料有效期",
		field : "expiredTime",
		sortable : false
	}, {
		label : "标准单价",
		field : "itemPrice",
		sortable : false
	}, editor({
		label : '盘点数量',
		field : 'checkCount',
		className : 'grid-number',
		editorArgs : {
			style : 'width: 5em;text-align: right;',
			constraints : {
				max : 99550,
				places : '0,3'
			},
			required : true,
			invalidMessage : '请输入不多于三位小数的数字。'
		},
		sortable : false
	}, NumberTextBox), {
		label : "",
		field : "none",
		sortable : false
	} ];
}
