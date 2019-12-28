require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var grid = null;
function initGrid() {
	var _url = appRoot + "/cf/requisition/manage/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard", ],
			function(OnDemandGrid, JsonRest, declare, query, Keyboard) {
				var dataStore = new JsonRest({
					target : _url
				});
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					store : dataStore,
					columns : getColumn(),
					cellNavigation : false,
					loadingMessage : '加载中...'
				}, "detailDataGrid");

				grid.startup();
			});
}

function getColumn() {
	return [ {
		label : "序号",
		field : "rownumber"
	}, {
		label : "商品编码",
		field : "itemId"
	}, {
		label : "商品名称",
		field : "itemName"
	}, {
		label : "单位",
		field : "itemDimension"
	}, {
		label : "规格",
		field : "itemSpecification"
	}, {
		label : "领料数",
		field : "receiveCount",
		className : 'grid-number'
	}, {
		label : "",
		field : "none"
	} ];
}

function doConfirm() {
	var _url = appRoot + "/cf/requisition/confirm/doConfirm.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("billForm")
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("确认领料成功！");
				doClose();
			} else {
				alert("确认领料失败！");
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId);
}
