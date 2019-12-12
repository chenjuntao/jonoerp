require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

function addEvent() {
	require([ "dojo/dom" ], function(dom) {
		dom.byId('btn_submit').onclick = doSubmit;

		// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
		window.onbeforeunload = function() {
			releaseLock(formId);
		};
	});
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已入库') {
		alert("配送单已入库！")
		return false;
	}
	return true;
}

function doSubmit() {
	var _url = appRoot + "/lc/request/addition/createPurchaseBill.action";
	_url = getUrl(_url);
	
	require([ "dojo/dom", "dojo/request/xhr", "dojo/dom-form" ], function(dom,
			xhr, domForm) {
		if (!checkStatus(formId)) {
			return;
		}
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

var grid = null;
var dataStore = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dgrid/selector", "dgrid/Selection",
			"dojo/store/Observable", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard", "dgrid/editor" ],
			function(OnDemandGrid, selector, Selection, Observable, Memory,
					declare, Keyboard, editor) {
				execute(OnDemandGrid, selector, Selection, Observable, Memory,
						declare, Keyboard, editor);
			});

	function execute(OnDemandGrid, selector, Selection, Observable, Memory,
			declare, Keyboard, editor) {
		dataStore = new Observable(new Memory({
			idProperty : "itemId",
			data : []
		}));
		var CustomGrid = declare([ OnDemandGrid, Selection, Keyboard ]);
		grid = new CustomGrid({
			columns : getColumn(editor, selector),
			cellNavigation : false,
			selectionMode : "toggle",
			allowSelectAll : true,
			loadingMessage : '加载中...',
		}, "dataGrid");

		grid.on("dgrid-datachange", function(event) {
			var field = event.cell.column.field;
			if (field == 'orderCount') {
				grid.save();// very important
				var row = dataStore.get(event.rowId);
				row[field] = event.value;// necessary
				row.payAmt = parseFloat((event.value * row.itemUnitPrice)
						.toFixed(2)); // 计算金额
				dataStore.put(row);
			}
		});

		grid.startup();
		loadGridData();
	}
}

function loadGridData() {
	var _url = appRoot + "/lc/request/addition/queryDetail.action?formId="
			+ formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/_base/array" ], function(xhr, array) {
		xhr.get(_url, {
			handleAs : "json"
		}).then(function(data) {
			array.forEach(data, function(row, i) {
				row.receiveCount = 0;
				row.differentCount = 0;
			});
			dataStore.setData(data);
			grid.set("store", dataStore);
		}, function(err) {
		});
	});
}

function getColumn(editor, selector) {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "供应商",
		field : "provider"
	}, {
		label : "原料编码",
		field : "itemId"
	}, {
		label : "原料名称",
		field : "itemName"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : "订货量",
		field : "itemCount",
		className : 'grid-number'
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number'
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number'
	}, {
		label : "",
		field : "none"
	} ];
}
