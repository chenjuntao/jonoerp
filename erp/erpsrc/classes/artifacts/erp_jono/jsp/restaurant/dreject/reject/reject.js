require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		addEvent();
		initGrid();
	});
});

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


function addEvent() {
	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(dojo.byId('formId').value);
	};
}

function doProcess() {
	var _url = appRoot + "/restaurant/dreject/reject/doReturn.action";
	_url = getUrl(_url);
	dojo.byId('btn_submit').disabled = true;
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("提交成功！");
				closeTab(tabId, 'doQuery');
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}
