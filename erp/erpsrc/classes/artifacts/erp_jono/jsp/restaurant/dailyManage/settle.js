require([ "dojo/ready", "dojo/parser", "dijit/ProgressBar" ], function(ready, parser) {
	ready(function() {
		parser.parse();
	});
});

function doSubmit() {
	// dojo.byId("btnSubmit").disabled = 'disabled';
	settleRestaurant();
}

function settleRestaurant() {
	// 初始化时间格式
	var parseOption = {
		datePattern : "yyyy-MM-dd",
		selector : "date"
	};
	var _url = appRoot + '/restaurant/dailyManage/doSaveDaily.action';
	_url = getUrl(_url);

	require([ "dojo/request/xhr", "dojo/dom-form", "dojo/parser", "dojo/date", "dojo/date/locale" ], function(xhr,
			domForm, parser, date, locale) {
		parser.parse();
		var afterbusinessDate = locale.parse(dojo.byId('afterbusinessDate').value, parseOption);
		var operatingTime = locale.parse(dojo.byId('operatingTime').value, parseOption);
		var businessDate = locale.parse(dojo.byId('businessDate').innerHTML, parseOption);
		var result = date.compare(afterbusinessDate, operatingTime, "date");
		var result2 = date.compare(afterbusinessDate, businessDate, "date");
		if (result < 0) {
			alert("逆日结日期不能小于当前日期！");
			return false;
		}

		if (result2 >= 0) {
			alert("逆日结日期不能大于等于营业日期！");
			return false;
		}
		xhr.post(_url, {
			data : domForm.toObject("settleForm"),
			handleAs : "json"
		}).then(function(data) {
			if (data.msg == 'ok') {
				alert("逆日结成功！");
				dojo.byId("businessDate").value = data.businessDate;
				// location.reload();// 刷新页面
				freshBusinessTop();
				doClose();
			} else {
				alert("逆日结失败！");
			}

		}, function(err) {
			alert("操作失败！");
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
		top.frames[0].document.getElementById('businessTop').innerHTML = locale.format(myDate, {
			selector : 'date',
			datePattern : 'yyyy-MM-dd'
		})
	});
}

function refreshDate(branchId) {
	var $branchId = dojo.byId('branchId');
	dojo.byId('branchName').value = $branchId.options[$branchId.selectedIndex].text;
	var _url = appRoot + '/restaurant/dailyManage/queryDate.action';
	_url = getUrl(_url);

	require([ "dojo/request/xhr" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : {
				branchId : dojo.byId(branchId).value
			}
		}).then(function(data) {
			if (data.msg) {
				dojo.byId('businessDate').innerHTML = data.businessDate;
			} else {
				// do something
			}
		}, function(err) {
		});
	});
}

function doClose() {
	closeTab(tabId);
}
