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
	require([ 
	          "dgrid/OnDemandGrid", 
	          "dojo/store/Memory",
	          "dojo/_base/declare",
	          "dojo/query", 
	          "dgrid/Keyboard",
	          "dgrid/extensions/ColumnResizer" ,
	          "dojox/widget/Standby",
	          "dgrid/ColumnSet" , 
	          "dojo/domReady!"
	          ], function(OnDemandGrid, Memory, declare, query, Keyboard,ColumnResizer,Standby,ColumnSet) {
		var dataStore = new Memory({
			data : gridData
		});
		
		var CustomGrid = declare([ OnDemandGrid, Keyboard,ColumnResizer,ColumnSet ]);
		grid = new CustomGrid({
			store : dataStore,
			columnSets : cols,
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

var cols = [
    		[ [ {
	label : "序号",
	field : "rownumber",
	sortable:false
}, {
	label : "原料编码",
	field : "itemId",
	sortable:false,
	className:'text-center'
}, {
	label : "原料名称",
	field : "itemName",
	sortable:false
} ] ], [ [  {
	label : "类别",
	field : "itemCategory",
	sortable:false
}, {
	label : "单位",
	field : "itemDimension",
	sortable:false,
	className:'text-center'
}, {
	label : "规格",
	field : "itemSpecification",
	sortable:false
}, {
	label : "订货量",
	field : "orderCount",
	sortable:false,
	className:'text-right'
}, {
	label : branchType=='FRANCHISEE'?"加盟价":"标准价", 
	field : "itemUnitPrice",
	sortable:false,
	className:'text-right'
}, {
	label : "万用1",
	field : "amtTTCNY1",
	sortable:false,
	className:'text-right'
}, {
	label : "万用2",
	field : "amtTTCNY2",
	sortable:false,
	className:'text-right'
}, {
	label : "万用3",
	field : "amtTTCNY3",
	sortable:false,
	className:'text-right'
}, {
	label : "需求1",
	field : "requireCount1",
	sortable:false,
	className:'text-right'
}, {
	label : "需求2",
	field : "requireCount2",
	sortable:false,
	className:'text-right'
}, {
	label : "需求3",
	field : "requireCount3",
	sortable:false,
	className:'text-right'
}, {
	label : "库存量",
	field : "inventory",
	sortable:false,
	className:'text-right'
}, {
	label : "建议1",
	field : "suggestCount1",
	sortable:false,
}, {
	label : "建议2",
	field : "suggestCount2",
	sortable:false,
	className:'text-right'
}, {
	label : "建议3",
	field : "suggestCount3",
	sortable:false,
	className:'text-right'
}, {
	label : "",
	field : "none",
	sortable:false
}] ] ];
