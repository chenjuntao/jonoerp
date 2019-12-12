require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
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
	dishLossGrid.query(query);
}

var branchType = 'RESTAURANT';

function getQuery() {
	return {
		branchId : dojo.byId('branchId').value
	}
}

function doScan(row) {
	doDetailScan(row.formId);
}

function doLock() {
	var _url = appRoot + '/restaurant/checkstorage/lock/checkBill.action?branchId=' + dojo.byId('branchId').value;
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

function checkSettle() {
	var _url = appRoot + '/restaurant/checkstorage/lock/checkSettle.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			data : getQuery()
		}).then(function(data) {
			if (!data.finished) {
				alert("请先日结天天前台后，再继续盘点锁库！");
			} else {
				addBatch();
			}
		}, function(err) {
			alert("查询失败！");
		});
	});
}

function addBatch() {
	var _url = appRoot + '/restaurant/checkstorage/lock/lockView.action?branchId=' + dojo.byId('branchId').value
			+ "&parentTabId=" + tabId;
	_url = getUrl(_url);
	
	var _title = '盘点锁库记录生成';
	addTab(_title, _url);
}
