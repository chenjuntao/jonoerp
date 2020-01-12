require([ "dojo", "dojo/ready", "dojox/widget/Standby" ], function(dojo, ready, Standby) {
	ready(function() {// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});
		document.body.appendChild(standby.domNode);
		standby.startup();

		// 显示遮罩层
		standby.show();

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

/**
 * 审核时速度比较慢，显示遮罩层
 */
var standby = null;
function doAudit() {
	var rows = dataStore.query();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	var _url = appRoot + "/restaurant/checkstorage/audit/doAudit.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		// 显示遮罩层
		standby.show();

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

var grid = null;
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dgrid/extensions/Pagination" ], function(OnDemandGrid, editor,
			NumberTextBox, declare, Keyboard, ColumnResizer, Pagination) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer, Pagination ]);
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

			// 数据获取完毕，隐藏遮罩层
			standby.hide();
		}, function(err) {
		});
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : true
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		className : 'text-center',
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
		label : '盘点数量',
		field : 'checkCount',
		className : 'text-right',
		sortable : true
	}, {
		label : '系统库存',
		field : 'theoryCount',
		className : 'text-right',
		sortable : true
	}, {
		label : '盘盈',
		field : 'diffCount',
		className : 'text-right',
		formatter : function(value, rowData) {
			return rowData.checkCount - rowData.theoryCount;
		},
		sortable : true
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
