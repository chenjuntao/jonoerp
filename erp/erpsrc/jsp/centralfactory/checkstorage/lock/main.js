require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
		var query = getQuery();
		inputGrid.init(query);
		lossGrid.init(query);
		// antiauditGrid.init(query);
		requisitionGrid.init(query);
		dishLossGrid.init(query);
		unRequisitionGrid.init(query);
	});
});

function doQuery() {
	var query = getQuery();
	inputGrid.query(query);
	lossGrid.query(query);
	// antiauditGrid.query(query);
	requisitionGrid.query(query);
	dishLossGrid.query(query);
	unRequisitionGrid.query(query);
}

function getQuery() {
	return {
		branchId : dojo.byId('branchId').value
	}
}

function doLock() {
	var _url = appRoot + '/cf/checkstorage/lock/checkBill.action?branchId=' + dojo.byId('branchId').value;
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (!data.finished) {
				alert('还存在未审核或未处理的单据，不能盘点锁库!');
			} else {
				addBatch();
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function addBatch() {
	var _url = appRoot + '/cf/checkstorage/lock/lockView.action?branchId=' + dojo.byId('branchId').value
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '盘点锁库记录生成';
	addTab(_title, _url);
}
