var branchType;

require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		branchType = dojo.byId('branchType').value;

		var query = getQuery();
		inputGrid.init(query);
		antiauditGrid.init(query);
		lossGrid.init(query);
	});
});

function doQuery() {
	var query = getQuery();
	inputGrid.query(query);
	antiauditGrid.query(query);
	lossGrid.query(query);
}

function getQuery() {
	return {
		branchId : dojo.byId('branchId').value
	}
}

function checkBill() {
	var _url = appRoot + '/restaurant/dailysettlement/checkBill.action?branchId=' + dojo.byId('branchId').value
			+ '&branchType=' + branchType;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (!data.finished) {
				alert('还存在未审核或处理的单据，不能进行月结!');
			} else {
				doSettle();
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function doSettle() {
	var _url = appRoot + '/lc/settle/month/settleView.action?branchId=' + dojo.byId('branchId').value + "&parentTabId="
			+ tabId + '&branchType=' + branchType;
	_url = getUrl(_url);
	
	_title = '月结';
	addTab(_title, _url);
}
