dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;

	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(formId);
	};
}

function doSubmit() {
	standby.show();
	var _url = appRoot + "/restaurant/antiaudit/create/doSave.action?branchType=" + dojo.byId('branchType').value;
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				standby.hide();
				closeTab(tabId, 'doClose');
			} else {
				alert("操作失败！");
				standby.hide();
			}
		}, function(err) {
			alert("操作失败！");
			standby.hide();
		});
	});
}

var grid = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dojox/widget/Standby",
			"dgrid/Keyboard", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Memory,
			declare, Standby, Keyboard, ColumnResizer) {
		var dataStore = new Memory({
			data : gridData
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
		// 初始化遮罩层
		standby = new Standby({
			target : "billForm"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
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
		className : 'text-right',
		sortable : false
	}, {
		label : "实发数",
		field : "deliveryCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "原实收数",
		field : "receiveCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "反审核实收数",
		field : "antiauditReceiveCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'text-right',
		sortable : false
	}, {
		label : "原金额",
		field : "payAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "反审核金额",
		field : "antiauditPayAmt",
		className : 'text-right',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
