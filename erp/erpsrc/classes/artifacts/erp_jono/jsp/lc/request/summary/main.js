require([ "dojo", "dojo/ready", "dojox/widget/Standby", "dojo/dom-form" ], function(dojo, ready, Standby, domForm) {
	ready(function() {
		initData();
		requestGrid.init();
		outerGrid.init();
		doQuery();

		standby = new Standby({
			target : "dataForm"
		});

		window.getQuery = function() {
			var query = domForm.toObject("dataForm");
			return query;
		}

		document.body.appendChild(standby.domNode);
		standby.startup();
	});
});

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

		var _url = appRoot + "/lc/request/summary/doQuery.action";
		_url = getUrl(_url);

		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			if (data.msg == 'ok') {
				requestGrid.loadData(data.request);
				outerGrid.loadData(data.outer);
			} else {
				alert("操作失败！");
			}
		}, function(err) {
		});
	});
}

function doInvalid() {
	var ids = requestGrid.getData().concat(outerGrid.getData());
	if (ids.length <= 0) {
		alert("数据为空！");
		return;
	}

	var _url = appRoot + "/restaurant/goodsbill/query/doInvalid.action";
	_url = getUrl(_url);

	if (confirm("确定作废所选单据吗？")) {
		standby.show();
		require([ "dojo/request/xhr" ], function(xhr) {
			standby.show();
			xhr.post(_url, {
				handleAs : "json",
				data : {
					formId : ids.join(","),
					cancelMsg : "物流中心作废要货单"
				}
			}).then(function(data) {
				doQuery();
				standby.hide();
				alert("操作成功！");
			}, function(err) {
				standby.hide();
			});
		});
	}
}

function doFormScan(row) {
	doDetailScan(row.formId);
}

var grid = null;
var dataStore = null;

function doScan(row) {
	var _url = appRoot + "/outerorder/manage/scanView.action?formId=" + row.formId;
	_url = getUrl(_url);

	addTab("查看", _url);
}

function doAggregate() {
	var ids = requestGrid.getData().concat(outerGrid.getData());
	if (ids.length <= 0) {
		alert("数据为空！");
		return;
	}
	var _url = appRoot + '/lc/request/summary/estimateView.action?parentTabId=' + tabId;
	if (g_busFlag) {
		_url = appRoot + '/lc/request/summary/securityView.action?parentTabId=' + tabId;
	}
	_url = getUrl(_url);

	require([ "dojo/_base/array", "dojo/dom" ], function(array, dom) {
		dom.byId('ids').value = ids.join(",");
		addPostTab('dataForm', '汇总预估', _url);
	});
}