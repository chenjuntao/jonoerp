require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		var query = getQuery();
		inputGrid.init(query);
		antiauditGrid.init(query);
		lossGrid.init(query);
		dishLossGrid.init(query);
		checkstorageGrid.init(query);
	});
});

function doQuery() {
	var query = getQuery();
	inputGrid.query(query);
	antiauditGrid.query(query);
	lossGrid.query(query);
	dishLossGrid.query(query);
	checkstorageGrid.query(query);
}

function getQuery() {
	return {
		branchId : dojo.byId('branchId').value,
		startDate : dojo.byId('startDate').value,
		endDate : dojo.byId('endDate').value
	}
}

function checkBill() {
	var _url = appRoot + '/restaurant/checkstorage/lock/checkBill.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			if (!data.finished) {
				alert('还存在未审核或未处理的单据，不能进行日结!');
			} else {
				checkSettle();
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function checkSettle() {
	var _url = appRoot + '/restaurant/checkstorage/lock/checkSettle.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			if (!data.finished) {
				alert("请先日结前台，传输营业数据后再日结本系统！");
			} else {
				doSettle();
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function doSettle() {
	var _url = appRoot + '/restaurant/dailysettlement/settleView.action?branchId=' + dojo.byId('branchId').value
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '餐厅日结';
	addTab(_title, _url);
}

function doRefresh() {
	location.reload();// 刷新页面
}
