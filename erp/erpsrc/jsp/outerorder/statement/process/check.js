dojo.ready(function() {
	addEvent();
	initGrid();
});

function addEvent() {
	dojo.byId('btn_submit').onclick = doSubmit;
}

function doSubmit() {
	var _url = appRoot + "/sp/inscan/doConfirm.action";
	_url = getUrl(_url);
	
	if (status == 'wbSure') {
		status = '外部订货方已确认';
	} else {
		status = '';
	}
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				subFormIds : formId,
				formType : 'W',
				status : status,
				formId : parentFormId
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("配送单确认成功！");
				if (status == '') {
					closeTab(tabId, "doQuery");
				} else {
					closeTab(tabId, "close");
				}
			} else {
				alert("配送单确认失败！");
			}
		}, function(err) {
		});
	});
}

var grid = null;

function initGrid() {
	var _url = appRoot + "/restaurant/inoutquery/shipping/queryDetail.action?formId=" + formId;
	_url = getUrl(_url);
	
	require([ "dgrid/OnDemandGrid", "dojo/store/JsonRest", "dojo/_base/declare", "dgrid/Keyboard", "dojo/domReady!" ],
			function(OnDemandGrid, JsonRest, declare, Keyboard) {
				var dataStore = new JsonRest({
					target : _url
				});
				var CustomGrid = declare([ OnDemandGrid, Keyboard ]);
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
	}, {
		label : "要货数量",
		field : "requestCount",
		className : 'grid-number'
	}, {
		label : "实收数量",
		field : "receiveCount",
		className : 'grid-number'
	}, {
		label : "实收差异",
		field : "differentCount",
		className : 'grid-number'
	}, {
		label : "标准单价",
		field : "itemUnitPrice",
		className : 'grid-number'
	}, {
		label : "标准金额",
		field : "payAmt",
		className : 'grid-number'
	}, {
		label : "有效期",
		field : "expiredTime"
	}, {
		label : "",
		field : "none"
	} ];
}
