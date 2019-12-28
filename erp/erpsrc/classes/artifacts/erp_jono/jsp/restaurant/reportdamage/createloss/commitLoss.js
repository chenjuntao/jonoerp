dojo.ready(function() {
	initGrid();
});

var grid = null;

function initGrid() {
	require([ 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/Memory", 
	          "dojo/_base/declare", 
	          "dgrid/Keyboard", 
	          "dgrid/extensions/ColumnResizer",
	          "dojo/domReady!"
	          ],function(OnDemandGrid, Memory, declare, Keyboard,ColumnResizer) {
				var dataStore = new Memory({
					data : gridData
				});
				
				var CustomGrid = declare([ OnDemandGrid, Keyboard ,ColumnResizer]);
				grid = new CustomGrid({
					store : dataStore,
					columns : cols,
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "dataGrid");

				grid.startup();
				
				calcLossMoney(dataStore);
			});
}

function calcLossMoney(dataStore) {
	var sum = 0;
	require([ "dojo/_base/array" ], function(array) {
			array.forEach(dataStore.query(), function(item) {
				sum += Number(item.payAmt);
		});

		dojo.byId("lossMoney").innerHTML = sum.toFixed(2);
	});
}

var cols = [ {
	label : "序号",
	field : "rownumber",
	sortable:false
}, {
	label : "原料编码",
	field : "itemId",
	className:'text-center',
	sortable:false
}, {
	label : "原料名称",
	field : "itemName",
	sortable:false
}, {
	label : "单位",
	className:'text-center',
	field : "itemDimension",
	sortable:false
}, {
	label : "规格",
	field : "itemSpecification",
	sortable:false
}, {
	label : "类别",
	field : "categoryId",
	sortable:false
}, {
	label : "报损数量",
	field : "lossNum",
	className:'text-right',
	sortable:false
}, {
	label : "标准单价",
	field : "itemUnitPrice",
	className:'text-right',
	sortable:false
}, {
	label : "标准金额",
	field : "payAmt",
	className:'text-right',
	sortable:false
}, {
	label : "库存数量",
	field : "inventory",
	className:'text-right',
	sortable:false
}, {
	label : "报损原因",
	field : "reason",
	sortable:false
}, {
	label : "",
	field : "blank",
	sortable:false
} ];

function commitLoss() {
	var _url = appRoot + "/restaurant/reportdamage/createloss/doCommit.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("createLossForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				closeTab(parentTabId);
				alert("生成单据号为：" + data.formId + "，提交成功！");
				dojo.byId("doCommit").value = "关闭当前页面";
				dojo.byId("doCommit").onclick = function() {
					closeTab(tabId);
				};
			} else {
				alert("操作失败！");
			}
		}, function(err) {
			alert("操作失败！");
		});
	});
}