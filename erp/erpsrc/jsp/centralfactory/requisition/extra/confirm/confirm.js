require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});

var grid = null;
var dataStore = null;

function initGrid() {
	var _url = appRoot + "/cf/requisition/manage/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "custom/store/Server", "dojo/_base/declare", "dojo/query", "dgrid/Keyboard", ],
			function(OnDemandGrid, Server, declare, query, Keyboard) {
				dataStore = new Server({
					target : _url,
					idProperty : 'rownumber'
				});

				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
				grid = new CustomGrid({
					sort : [ {
						attribute : "rownumber",
						descending : false
					} ],
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
		label : "计划领料数",
		field : "itemCount",
		className : 'grid-number'
	}, {
		label : "实际领料数",
		field : "receiveCount",
		className : 'grid-number'
	}, {
		label : "实领差异",
		field : "differentCount",
		className : 'grid-number'
	}, {
		label : "",
		field : "none"
	} ];
}

function checkStatus(_formId) {
	var data = getCurrentStatus(_formId);
	var status = data.formStatus;

	if (status == '已结案') {
		alert("工单已结案，不能进行超领！");
		return false;
	}
	return true;
}

function doConfirm() {
	if (!checkStatus(dojo.byId('formRefId').value)) {
		return;
	}

	var rows = dataStore.getData();
	dojo.byId('jsonData').value = JSON.stringify(rows);
	dojo.byId('auditBtn').disabled = true;
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
