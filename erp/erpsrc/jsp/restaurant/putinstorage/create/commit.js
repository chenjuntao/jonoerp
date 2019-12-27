dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;
	if (status == '已结案') {
		alert("采购单已结案！")
		return false;
	}
	return true;
}

function doSubmit() {
	var formRefId = dojo.byId('formRefId').value;
	if (!checkStatus(formRefId)) {
		return;
	}

	standby.show();

	var _url = appRoot + "/restaurant/putinstorage/create/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				standby.hide();
				alert("生成单据号为：" + data.formId + "，提交成功！");
				closeTab(tabId, 'doClose');
			} else {
				standby.hide();
				alert("操作失败！");
			}
		}, function(err) {
			standby.hide();
			alert("操作失败！");
		});
	});
}

var grid = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard",
			"dgrid/extensions/ColumnResizer", "dojox/widget/Standby", "dojo/store/Observable", "dojo/domReady!" ],
			function(OnDemandGrid, Memory, declare, Keyboard, ColumnResizer, Standby, Observable) {
				var dataStore = new Observable(new Memory({
					idProperty : "rownumber",
					data : gridData
				}));
				var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnResizer ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : getColumn(),
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();

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
		label : "要货数量",
		field : "orderCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "已入库数量",
		field : "receivedCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实收数量",
		field : "nums",
		className : 'grid-number',
		sortable : false
	}, {
		label : "实收差异",
		field : "differentCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable : false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable : false
	}, {
		label : "",
		field : "none",
		sortable : false
	} ];
}
