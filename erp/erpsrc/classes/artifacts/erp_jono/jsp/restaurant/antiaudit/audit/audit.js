require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doAudit;
	dojo.byId('btn_repeal').onclick = doRepeal;

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formRefId').value);
	};
}

function compareTime() {
	var flag = true;
	require([ "dojo/date", "dojo/date/locale" ], function(date, locale) {
		var parseOption = {
			datePattern : "yyyy-MM-dd",
			selector : "date"
		};
		var lastDate = locale.parse(dojo.byId('lastDate').value, parseOption);
		var antiauditTime = locale.parse(dojo.byId('antiauditTime').value, parseOption);
		var result = date.compare(lastDate, antiauditTime, "date");
		if (result >= 0) {
			alert("已月结锁库，请撤销反审核！");
			flag = false;
		}
	});
	alert(flag)
	return flag;
}

function doAudit() {
	var rows = dataStore.query();

	dojo.byId('jsonData').value = JSON.stringify(rows);

	var _url = appRoot + "/restaurant/antiaudit/audit/doAudit.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form", "dojo/parser", "dojo/date", "dojo/date/locale" ], function(xhr,
			domForm, parser, date, locale) {
		parser.parse();
		var parseOption = {
			datePattern : "yyyy-MM-dd",
			selector : "date"
		};
		var lastDate = locale.parse(dojo.byId('lastDate').value, parseOption);
		var antiauditTime = locale.parse(dojo.byId('antiauditTime').value, parseOption);
		var result = date.compare(lastDate, antiauditTime, "date");
		if (result >= 0) {
			alert("已月结锁库，请撤销反审核！");
			return false;
		}
		dojo.byId('btn_submit').disabled = true;
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId, "doQuery");
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}

function doRepeal() {
	var _url = appRoot + "/restaurant/antiaudit/manage/doRevert.action?formRefId=" + g_formRefId;
	_url = getUrl(_url);

	if (confirm("确定撤消反审核吗？")) {
		require([ "dojo/request/xhr" ], function(xhr) {
			xhr.get(_url, {
				handleAs : "json"
			}).then(function(data) {
				alert("撤消成功！");
				closeTab(tabId, "doQuery");
			}, function(err) {
			});
		});
	}
}

var grid = null;
function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/editor", "dijit/form/NumberTextBox", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer" ], function(OnDemandGrid, editor, NumberTextBox, declare, Keyboard,
			ColumnResizer) {
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
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
	var _url = appRoot + "/restaurant/antiaudit/manage/queryDetail.action?formRefId=" + dojo.byId('formRefId').value;
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
	}, {
		label : "配送数",
		field : "shippingCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实发数",
		field : "deliveryCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "原实收数",
		field : "receiveCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "反审核实收数",
		field : "antiauditReceiveCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "原金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "反审核金额",
		field : "antiauditPayAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
