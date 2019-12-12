dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	standby.show();
	var _url = appRoot + "/restaurant/allocateitem/create/doCommit.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("生成单据号为：" + data.formId + "，提交成功！");
				standby.hide();
				closeTab(tabId, "closeMainTab");
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
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dojox/widget/Standby", "dojo/query",
			"dgrid/Keyboard", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid, Memory,
			declare, Standby, query, Keyboard, ColumnResizer) {
		var dataStore = new Memory({
			data : gridData,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
		grid = new CustomGrid({
			store : dataStore,
			columns : cols,
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

var cols = [ {
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
	label : "申请调拨数量",
	field : "applyCount",
	sortable : false
}, {
	label : "标准单价",
	field : "itemUnitPrice",
	sortable : false
}, {
	label : "标准金额",
	field : "payAmt",
	sortable : false
}, {
	label : "",
	field : "none",
	sortable : false
} ];
