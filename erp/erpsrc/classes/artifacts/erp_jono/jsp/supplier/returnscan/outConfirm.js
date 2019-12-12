require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initGrid();
	});
});
function addEvent() {
	// 进入页面时在后台加锁，离开页面时通过前台发送解锁请求
	window.onbeforeunload = function() {
		releaseLock(formId);
	};
}

function doConfirm() {
	if (status == 'wbSure') {
		status = '外部订货方已确认';
	} else {
		status = '';
	}
	dojo.byId('btn_submit').disabled = true;
	var _url = appRoot + "/sp/inscan/doConfirm.action";
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				subFormIds : formId,
				status : status,
				formId : parentFormId
			}
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("对账确认成功！");
				if (status == '') {
					closeTab(tabId, "doQuery");
				} else {
					closeTab(tabId, "close");
				}
			} else {
				alert("对账确认失败！");
			}
		}, function(err) {
		});
	});
}

var grid = null;

function initGrid() {
	var _url = appRoot + "/restaurant/dreject/manage/queryDetail.action?formId=" + formId;
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
		field : "rownumber",
		sortable : false
	}, {
		label : "原料编码",
		field : "itemId",
		className : 'text-center',
		sortable : false
	}, {
		label : "原料名称",
		field : "itemName",
		sortable : false
	}, {
		label : "单位",
		field : "itemDimension",
		className : 'text-center',
		sortable : false
	}, {
		label : "规格",
		field : "itemSpecification",
		sortable : false
	}, {
		label : "要货数量",
		field : "orderCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "实收数",
		field : "receiveCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "退货数",
		field : "returnCount",
		className : 'text-right',
		sortable : false
	}, {
		label : "退货原因",
		field : "returnReason",
		sortable : false
	} ];
}
