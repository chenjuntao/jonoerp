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
	if (status == '已入库') {
		alert("配送单已入库！")
		return false;
	}
	return true;
}

function doSubmit() {
	var formId = dojo.byId('formId').value;
	if (!checkStatus(formId)) {
		return;
	}
	var _url = appRoot + "/restaurant/putinstorage/distribution/fillBill.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ,"dojox/widget/Standby"], function(xhr, domForm,Standby) {
		
		standby = new Standby({
			target : "commandTable"
		});

		document.body.appendChild(standby.domNode);
		standby.startup();
		// 显示遮罩层
		standby.show();
		
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			standby.hide();
			
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId, 'doQuery');
			} else {
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
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare",
			"dgrid/Keyboard","dgrid/extensions/ColumnResizer" , "dojo/domReady!" ], function(OnDemandGrid,
			Memory, declare, Keyboard,ColumnResizer) {
		var dataStore = new Memory({
			data : gridData,
			idProperty:'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
		grid = new CustomGrid({
			store : dataStore,
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber",
		sortable:false
	}, {
		label : "原料编码",
		field : "itemId",
		sortable:false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable:false
	}, {
		label : "类别",
		field : "itemCategory",
		sortable:false
	}, {
		label : "单位",
		field : "itemDimension",
		sortable:false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable:false
	}, {
		label : "订货数",
		field : "requestCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "实发数",
		field : "deliveryCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "实收数",
		field : "receiveCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "差异数",
		field : "differentCount",
		className : 'grid-number',
		sortable:false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number',
		sortable:false
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number',
		sortable:false
	}, {
		label : "",
		field : "none",
		sortable:false
	} ];
}
