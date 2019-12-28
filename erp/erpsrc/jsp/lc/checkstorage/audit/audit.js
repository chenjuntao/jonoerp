require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doAudit;

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

function doAudit() {
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	dojo.byId('btn_submit').disabled = true;
	var _url = appRoot + "/restaurant/checkstorage/audit/doAudit.action";
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
		});
	});
}

var grid = null;
function initGrid() {
	require(
			[ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard" ],
			function(OnDemandGrid, editor, NumberTextBox, declare, Keyboard) {
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					columns : getColumn(),
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
				queryData();
			});
}

function queryData() {
	var _url = appRoot + "/restaurant/checkstorage/manage/queryDetail.action?formId=" + formId;
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

function getColumn() {
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
	}, {
		label : '盘点数量',
		field : 'checkCount',
		className : 'grid-number'
	}, {
		label : '系统库存',
		field : 'theoryCount',
		className : 'grid-number'
	}, {
		label : '盘盈',
		field : 'diffCount',
		className : 'grid-number',
		formatter : function(value, rowData) {
			return rowData.checkCount - rowData.theoryCount;
		}
	}, {
		label : "",
		field : "none"
	} ];
}
