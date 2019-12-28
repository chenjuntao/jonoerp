require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
		loadBatch();
	});
});

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
	var _url = appRoot
			+ "/restaurant/checkstorage/imanage/queryCheckList.action";
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

	var _url = appRoot + '/restaurant/checkstorage/iteminput/checkView.action';
	_url = getUrl(_url);
	
	addPostTab('billForm', '盘点清单输入确认', _url);
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dgrid/editor", "dijit/form/NumberTextBox" ],
			function(OnDemandGrid, Memory, declare, Keyboard, editor,
					NumberTextBox) {
				dataStore = new Memory({
					// data : gridData
					data : []
				});
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					columns : getColumn(editor, NumberTextBox),
					cellNavigation : false,
					loadingMessage : '加载中...',
				}, "dataGrid");

				grid.startup();
			});
}

function loadGridData() {
	var _url = appRoot + "/restaurant/checkstorage/manage/queryDetail.action";
	_url = getUrl(_url);
	
	require(
			[ "dojo/request/xhr", "dojo/store/Observable", "dojo/store/Memory" ],
			function(xhr, Observable, Memory) {
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
			style : 'width: 5em;',
			constraints : {
				max : 99550,
				places : '0,1'
			},
			required : true,
			invalidMessage : '请输入数字。'
		}
	}, NumberTextBox), {
		label : "",
		field : "none"
	} ];
}
