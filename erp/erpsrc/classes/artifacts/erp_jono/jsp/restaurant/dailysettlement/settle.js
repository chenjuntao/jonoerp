require([ "dojo/ready", "dojo/parser", "dijit/ProgressBar" ], function(ready, parser) {
	ready(function() {
		parser.parse();
	});
});

var seconds = 0;
var t;

function timedCount() {
	seconds++;
	t = setTimeout("timedCount()", 1000)
}

function stopCount() {
	clearTimeout(t)
}

function doSubmit() {
	dojo.byId("btnSubmit").disabled = 'disabled';

	timedCount();

	settleRestaurant();
}

function getSecondsStr() {
	if (seconds < 60) {
		return seconds + '秒';
	} else {
		return parseInt(seconds / 60) + "分" + seconds % 60 + "秒";
	}
}

function progressBar() {// 进度条
	var _url = appRoot + "/common/function/isFinish.action";

	require([ "dojo/request/xhr" ], function(xhr) {
		xhr.post(_url, {
			handleAs : "json",
			sync : true
		}).then(function(data) {
			if (seconds < 60 * 20) {
				myProgressBar.set({
					value : data.msg
				});
			} else {
				dojo.byId("waitingDiv").innerHTML = "日结失败！"
				clearInterval(int);
			}
		}, function(err) {
		});
	});
}

function settleRestaurant() {
	dojo.byId("setbusinessDate").value = dojo.byId("afterbusinessDate").value;
	var _url = appRoot + '/restaurant/dailysettlement/settleRestaurant.action';

	int = setInterval("progressBar()", 1000);

	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			data : domForm.toObject("settleForm"),
			handleAs : "json"
		}).then(function(data) {
			stopCount();
			if (data.msg == 'ok') {
				myProgressBar.set({
					value : 10
				});
				dojo.byId("waitingDiv").innerHTML = "日结成功，总耗时" + getSecondsStr() + "！"
				freshBusinessTop();
				alert("日结成功！");
				doClose();
			} else {
				dojo.byId("waitingDiv").innerHTML = "日结失败！"
			}
			clearInterval(int);

		}, function(err) {
			stopCount();
			alert('操作失败！');
			clearInterval(int);
		});
	});
}

function freshBusinessTop() {
	require([ "dojo/date", "dojo/date/locale" ], function(date, locale) {
		var myDateStr = dojo.byId('afterbusinessDate').value;
		var parseOption = {
			datePattern : "yyyy-MM-dd",
			selector : "date"
		};
		var myDate = locale.parse(myDateStr, parseOption);
		// myDate = date.add(myDate, "day", 1);
		top.frames[0].document.getElementById('businessTop').innerHTML = locale.format(myDate, {
			selector : 'date',
			datePattern : 'yyyy-MM-dd'
		})
	});
}

function freshTime() {
	require([ "dojo/date", "dojo/date/locale" ], function(date, locale) {
		var myDateStr = dojo.byId('businessDate').value;
		var parseOption = {
			datePattern : "yyyy-MM-dd",
			selector : "date"
		};
		var myDate = locale.parse(myDateStr, parseOption);
		myDate = date.add(myDate, "day", 1);
		dojo.byId('afterbusinessDate').innerHTML = locale.format(myDate, {
			selector : 'date',
			datePattern : 'yyyy-MM-dd'
		})
	});
}

function doClose() {
	closeTab(tabId, 'doRefresh');
}
