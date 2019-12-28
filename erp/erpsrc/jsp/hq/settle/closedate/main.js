require([ "dojo", "dojo/ready" ], function(dojo, ready) {
	ready(function() {
	});
});

function doSave() {
	var closeDate = dojo.byId('closeDate').value;
	if (closeDate > 28) {
		if (!confirm("月份最后一天小于关账日的月份，系统将关账月默认此月最后一天！")) {
			return;
		}
	}
	var _url = appRoot + '/settle/closedate/doSave.action?closeDate=' + closeDate;
	
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			alert('保存成功!');
		}, function(err) {
			errorHandler(err);
		});
	});
}
