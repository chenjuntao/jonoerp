dojo.ready(function() {
});

function doTestGoodsBill() {
	var numGoodsBill = dojo.byId("numGoodsBill").value;
	var numEGoodsBill = dojo.byId("numEGoodsBill").value;
	var _url = appRoot + "/main/test/doTestGoodsBill.action?numGoodsBill=" + numGoodsBill + "&numEGoodsBill="
			+ numEGoodsBill;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("测试成功！");
			} else {
				alert("测试成功！");
			}
		}, function(err) {
		});
	});

}

function doBack() {
	var startDate = dojo.byId('startDate').value;
	var endDate = dojo.byId('endDate').value;
	var branchId = dojo.byId('branchId').value;
	var _url = appRoot + "/main/test/doBack.action?startDate=" + startDate + "&endDate=" + endDate + "&branchId="
			+ branchId;
	// _url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("恢复成功！");
			} else {
				alert("恢复成功！");
			}
		}, function(err) {
		});
	});

}

function doTest() {
	var _url = appRoot + "/main/test/doTest.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				dojo.byId('contents').innerHTML = data.contents;
				alert("测试成功！");
			} else {
				alert("测试失败！");
			}
		}, function(err) {
		});
	});

}

function timerBack() {
	var startDate = dojo.byId('startDate').value;
	var endDate = dojo.byId('endDate').value;
	var branchId = dojo.byId('branchId').value;
	var _url = appRoot + "/setting/backup/timerBackup.action?startDate=" + startDate + "&endDate=" + endDate
			+ "&branchId=" + branchId;
	// _url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			// if (data.msg == 'ok') {
			// alert("恢复成功！");
			// } else {
			// alert("恢复成功！");
			// }
		}, function(err) {
		});
	});

}

function doTestLoss() {
	var numGoodsBill = dojo.byId("numGoodsBill").value;
	var numEGoodsBill = dojo.byId("numEGoodsBill").value;
	var _url = appRoot + "/main/test/doTestLoss.action?numGoodsBill=" + numGoodsBill + "&numEGoodsBill="
			+ numEGoodsBill;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("测试成功！");
			} else {
				alert("测试成功！");
			}
		}, function(err) {
		});
	});

}

function doSaveMenu() {
	var _url = appRoot + "/main/test/doSaveMenu.action";
	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("测试成功！");
			} else {
				alert("测试成功！");
			}
		}, function(err) {
		});
	});

}

function doDeleteGoodsBill() {
	var _url = appRoot + "/main/test/doDeleteGoodsBill.action";
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("测试成功！");
			} else {
				alert("测试成功！");
			}
		}, function(err) {
		});
	});

}
var Id = "";
function click1() {
	Id = "01";
	doTestSettle()
}
function click2() {
	Id = "02";
	doTestSettle()
}
function click3() {
	Id = "03";
	doTestSettle()
}
function click4() {
	Id = "04";
	doTestSettle()
}
function click5() {
	Id = "05";
	doTestSettle()
}
function click6() {
	Id = "06";
	doTestSettle()
}
function click7() {
	Id = "07";
	doTestSettle()
}
function click8() {
	Id = "08";
	doTestSettle()
}
function click9() {
	Id = "09";
	doTestSettle()
}
function click10() {
	Id = "10";
	doTestSettle()
}
function doTestSettle() {
	var _url = appRoot + "/main/test/doTestSettle.action?Id=" + Id;
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("测试成功！");
			} else {
				alert("测试成功！");
			}
		}, function(err) {
		});
	});

}
