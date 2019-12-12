var branchType;

require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		branchType = dojo.byId('branchType').value;

		var query = getQuery();
		inputGrid.init(query);
		antiauditGrid.init(query);
		lossGrid.init(query);
		priceGrid.init(query);
		pickingGrid.init(query);
		checkstorageGrid.init(query);
	});
});

function doQuery() {
	var query = getQuery();
	inputGrid.query(query);
	antiauditGrid.query(query);
	lossGrid.query(query);
	priceGrid.query(query);
	pickingGrid.query(query);
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
	var _url = appRoot + '/restaurant/dailysettlement/checkBill.action?branchType=' + branchType;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			if (!data.finished) {
				alert('还存在未审核或处理的单据，不能进行日结!');
			} else {
				doSettle();
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function doSettle() {
	var _url = appRoot + '/restaurant/dailysettlement/lcSettleView.action?branchId=' + dojo.byId('branchId').value
			+ "&parentTabId=" + tabId + '&branchType=' + branchType;
	
	_url = getUrl(_url);
	_title = '日结';
	addTab(_title, _url);
}

function doRefresh() {
	location.reload();// 刷新页面
}