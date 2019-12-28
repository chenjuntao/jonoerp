require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		initData();
		arrangementGrid.init();
		doQuery();
		initTooTip();
	});
});

var myTooltipDialog = null;

function initTooTip() {
	// displays a tooltip that contains form elements(like a dialog)
	require([ "dijit/TooltipDialog" ], function(TooltipDialog) {
		myTooltipDialog = new TooltipDialog({
			id : 'myTooltipDialog',
			style : "color: red;width:100;height:auto;",
			onMouseLeave : function() {
				dijit.popup.close(myTooltipDialog);
			}
		});
	});
}

function initData() {
	require([ "dojo/dom" ], function(dom) {
		dom.byId('startDate').value = dom.byId('businessDate').value;
	});
}

function doQuery() {
	require([ "dojo/dom", "dojo/date", "dojo/date/locale", "dojo/request/xhr" ], function(dom, date, locale, xhr) {
		/**
		 * 开始日期不能小于当前营业日期
		 */
		function checkDate() {
			var parseOption = {
				datePattern : "yyyy-MM-dd",
				selector : "date"
			};
			var startDate = locale.parse(dom.byId('startDate').value, parseOption);
			var businessDate = locale.parse(dom.byId('businessDate').value, parseOption);
			var result = date.compare(startDate, businessDate, "date");
			if (result < 0) {
				alert('开始日期不能小于当前营业日期！');
				return false;
			}
			return true;
		}
		if (!checkDate()) {
			return;
		}

		var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/doCFQuery.action";
		_url = getUrl(_url);
		
		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			if (data.msg == 'ok') {
				arrangementGrid.loadData(data.arrange);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function getQuery() {
	return {
		businessDate : dojo.byId('businessDate').value,
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value
	}
}

var grid = null;
var dataStore = null;

function doRelScan(row) {
	require([ "dijit/popup", "dojo/query" ], function(popup, query) {
		myTooltipDialog.set("content", createContent(row, "doMoreScan"));
		popup.open({
			popup : myTooltipDialog,
			around : query(".field-formRefId", dojo.byId('"dataGrid-row-' + row.id))[0]
		});
	});

}

function doScan(row) {
	var _url = appRoot + "/centralfactory/productionDemand/arrangement/audit/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);
	
	addTab("中央工厂生产计划单查看", _url);
};

function doMoreScan(_formRefId) {
	var _url = appRoot + "/centralfactory/productionDemand/summary/scanView.action?formId=" + _formRefId;
	_url = getUrl(_url);
	
	addTab(_formRefId + "查看", _url);
}

function doAggregate() {
	var ids = arrangementGrid.getData();
	if (ids.length <= 0) {
		alert("数据为空！");
		return;
	}
	var _url = appRoot + "/centralfactory/productionDemand/purchaseBill/treeCreate/estimateView.action?parentTabId="
			+ tabId;
	_url = getUrl(_url);
	
	require([ "dojo/_base/array", "dojo/dom" ], function(array, dom) {
		dom.byId('ids').value = ids.join(",");
		addPostTab('dataForm', '汇总订货数据', _url);
	});
}