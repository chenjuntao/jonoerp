require([ "dojox/widget/Standby", "dojo/domReady!" ], function(Standby) {
	standby = new Standby({
		target : "settleForm"
	});

	document.body.appendChild(standby.domNode);
	standby.startup();
});

var standby = null;

function getWaittingMsg(waittingMsg) {
	var imgSrc = appRoot + "/jsp/common/img/wait.gif";
	var msg = '<div style="text-align: center; padding-top: 50px; height: 100px;">'
			+ '<div style="vertical-align: middle; font-size:20px;">' + waittingMsg + '</div>' + '<img src="' + imgSrc
			+ '"> </div>';
	return msg;
}

function doSubmit() {
	dojo.byId("btnSubmit").disabled = 'disabled';
	saveSettleRecord();
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

function saveSettleRecord() {
	standby.show();
	dojo.byId("setbusinessDate").value = dojo.byId("afterbusinessDate").value;
	var _url = appRoot + '/restaurant/dailysettlement/saveSettleRecord.action';
	_url = getUrl(_url);
	
	require([ "dojo/request/xhr", "dojo/dom-form" ], function(xhr, domForm) {
		xhr.post(_url, {
			handleAs : "json",
			data : domForm.toObject("settleForm")
		}).then(function(data) {
			standby.hide();

			if (data.msg == 'ok') {
				dojo.byId("waitingDiv").innerHTML = "日结成功！";
				freshBusinessTop();
				alert("日结成功！");
				doClose();
			} else {
				dojo.byId("waitingDiv").innerHTML = "日结失败！"
			}
		}, function(err) {
			alert(err);
			standby.hide();
		});
	});
}

function doClose() {
	closeTab(tabId, 'doRefresh');
}
