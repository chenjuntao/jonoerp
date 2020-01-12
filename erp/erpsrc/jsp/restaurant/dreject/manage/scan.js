require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

function showDialog(){
	dijit.byId('customDialog').show();
}

function hideDialog(){
	dijit.byId('customDialog').hide();
}

function customExport(){
	fillData(grid.get('store').getData());
}
function doClose() {
	closeTab(tabId);
}

var grid = null;
var dataStore = null;
function initGrid() {
	var _url = appRoot
			+ "/restaurant/dreject/manage/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ 
	          "dgrid/OnDemandGrid",
	          "custom/store/Server",
	          "dojo/parser",
	          "dojo/_base/declare", 
	          "dgrid/Keyboard", 
	          "dgrid/extensions/ColumnResizer" ,
	          "dojo/domReady!" 
          ],function(OnDemandGrid, Server,parser, declare, Keyboard,ColumnResizer) {
		  parser.parse();
		  
		dataStore = new Server({
			target : _url,
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
		
		grid.on("dgrid-refresh-complete", function(evt) {
			//若直接在事件外调用dataStore.getData()，可能无数据 
			//因为grid加载是异步的
			calcMoney(dataStore.getData());
		});
		
	});
}

//计算总金额
function calcMoney(data){
	var sumMoney = 0;
	
	require([ "dojo/_base/array","dojo/dom" ], function(array,dom) {
		array.forEach(data, function(item) {
			if(!isNaN(sumMoney)){
				sumMoney += parseFloat(item.returnPayAmt);
			}else{
				sumMoney += 0;
			}
		});
		
		dom.byId("sumMoney").innerHTML = sumMoney;
		
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
		className:'text-center',
		sortable:false
	}, {
		label : "原料名称",
		field : "itemName",
		className:'text-center',
		sortable:false
	}, {
		label : "单位",
		field : "itemDimension",
		className:'text-center',
		sortable:false
	}, {
		label : "规格",
		field : "itemSpecification",
		className:'text-right',
		sortable:false
	}, {
		label : "配送数",
		field : "shippingCount",
		className:'text-right',
		sortable:false
	}, {
		label : "实发数",
		field : "deliveryCount",
		className:'text-right',
		sortable:false
	}, {
		label : "实收数",
		field : "receiveCount",
		className:'text-right',
		sortable:false
	}, {
		label : "退货数",
		field : "returnCount",
		className:'text-right',
		sortable:false
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className:'text-right',
		sortable:false
	}, {
		label : "标准金额",
		field : "returnPayAmt",
		className:'text-right',
		sortable:false
	}, {
		label : "退货原因",
		field : "returnReason",
		sortable:false
	}];
}
