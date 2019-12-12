dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	standby.show();
	var _url = appRoot + "/restaurant/goodsbill/create/doCommit.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			if (data.msg == 'ok') {
				alert("生成单据号为：" + data.formId + "，提交成功！");
				closeTab(tabId, 'doClose');
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
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard",
			"dojox/widget/Standby", "dgrid/extensions/ColumnResizer", "dojo/domReady!" ], function(OnDemandGrid,
			Memory, declare, query, Keyboard, Standby, ColumnResizer) {
		var dataStore = new Memory({
			data : gridData
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
	label : "订货量",
	field : "orderCount",
	className : 'grid-number',
	sortable : false
}, {
	label : "零售单价",
	field : "itemUnitPrice",
	className : 'grid-number',
	sortable : false
// }, {
// label : "标准金额",
// field : "payAmt",
// className : 'grid-number',
// sortable:false
}, {
	label : "",
	field : "none",
	sortable : false
} ];
