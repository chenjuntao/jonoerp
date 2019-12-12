dojo.ready(function() {
	addEvent();
	initGrid();
	
	dojo.byId('operateButton').value = operate;
});

var grid = null;
var dataStore = null;

function addEvent() {
	//进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(formId);
	};
}

function getStatus() {
	if (status == '已发货') {
		return '已收货';
	} else if (status == '已收货') {
		return '外部订货方已对账';
	} else if (status == '总部已对账') {
		return '已付款';
	}
}

function getSuccessMsg() {
	if (status == '已发货') {
		return '外部订货方收货成功！';
	} else if (status == '已收货') {
		return '外部订货方对账成功！';
	}else if (status == '总部已对账') {
		return '付款成功！';
	}
}

function getErrorMsg() {
	if (status == '已收货') {
		return '外部订货方收货失败！';
	} else if (status == '已收货') {
		return '外部订货方对账失败！';
	}else if (status == '总部已对账') {
		return '付款失败！';
	}
}

function doOperate(){
	var rows = dataStore.query();

	var _url = 	appRoot + "/outerorder/handle/doOperate.action?formId="+formId;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr"], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {status:getStatus()}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert(getSuccessMsg()); 
				closeTab(tabId,"doQuery");
			} else {
				alert(getErrorMsg()); 
			}
		}, function(err) {
		});
	});
}

function initGrid() {
	
	require([ 
	         "dojo/request/xhr" ,
	          "dojo/store/Memory",
	          "dojo/store/Observable",
	          "dojo/window",
	          "dgrid/OnDemandGrid", 
	          "dojo/store/JsonRest",
	          "dojo/_base/declare", 
	          "dojo/query", 
	          "dgrid/Keyboard",
	          "dojo/domReady!" 
	          ], function(xhr,Memory,Observable,win,OnDemandGrid, JsonRest, declare,	query, Keyboard) {
		//先实现功能，再优化代码
		// calculate the grid height for avoid the outside scrollbar
		var vs = win.getBox();
		var gridHeight = vs.h - 135;
		var gridNode = dojo.byId("dataGrid");
		gridNode.style.height = gridHeight + "px";

		var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
		
		grid = new CustomGrid({
			store : new Memory(),
			columns : getColumn(),
			cellNavigation : false,
			loadingMessage : '加载中...'
		}, "dataGrid");
		

		grid.startup();
		
		var _url = appRoot
		+ "/restaurant/goodsbill/query/queryDetail.action?formId=" + formId;
		_url = getUrl(_url);
		
		xhr(_url, {
			handleAs : "json"
		}).then(function(data) {
			dataStore =  new Observable(new Memory({data:data,idProperty : "rownumber"}));
			grid.set('store',dataStore);
		});
	});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "原料编码",
		field : "itemId"
	}, {
		label : "原料名称",
		field : "itemName"
	}, {
		label : "类别",
		field : "itemCategory"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}
//	, {
//		label : "库存量",
//		field : "inventory"
//	}
	,{
		label : "订货量",
		field : "orderCount"
	}, {
		label : "标准单价",
		field : "itemUnitPrice"
	}, {
		label : "标准金额",
		field : "payAmt"
	}, {
		label : "",
		field : "none"
	} ];

}
