dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function checkStatus(_formId) {
	return true;
}

function doSubmit() {
	var formId = dojo.byId('formId').value;
	if (!checkStatus(formId)) {
		return;
	}
	var _url = appRoot + "/lc/request/distribution/doSave.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("生成单据号为：" + data.formId + "，提交成功！");
				closeTab(tabId, 'doClose');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

var grid = null;

function initGrid() {
	require([ "dgrid/OnDemandGrid", "dojo/store/Memory", "dojo/_base/declare", "dgrid/Keyboard", "dgrid/ColumnSet",
			"dojo/domReady!" ], function(OnDemandGrid, Memory, declare, Keyboard, ColumnSet) {
		var dataStore = new Memory({
			data : gridData,
			idProperty : 'rownumber'
		});
		var CustomGrid = declare([ OnDemandGrid, Keyboard, ColumnSet ]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");

		grid.startup();
	});
}

function getColumn() {
	return [ [ [ {
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
	} ] ], [ [ {
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
		label : "包装因子",
		field : "packagingFactor",
		sortable : false
	}, {
		label : "包装单位",
		field : "packagingUnit",
		sortable : false
	}, {
		label : "包装数量",
		field : "packagingCount",
		sortable : false
	}, {
		label : "订货数",
		field : "requestCount",
		className : 'grid-number',
		sortable : false
	}, {
		label : "出货数",
		field : "deliveryCount",
		className : 'grid-number',
		sortable : false
	}, {
		 label : "单价",
		 field : "itemUnitPrice",
		 className : 'grid-number',
		 sortable : false
	 }, {
		 label : "金额",
		 field : "payAmt",
		 className : 'grid-number',
		 sortable : false
	}] ] ];
}
