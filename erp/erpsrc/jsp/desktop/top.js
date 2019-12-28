// setTimeout(showTime, 3000);
setTimeout(keepSession, 600000);

function keepSession() {
	sendRequest(appRoot + '/jsp/common/jsp/dummy.jsp', '');
}

function onReadyStateChange() {
	var ready = req.readyState;
	if (ready == READY_STATE_COMPLETE && req.status == 200) {
		onResetTimer();
	}
}

function onResetTimer() {
	setTimeout(keepSession, 600000);
}

function showTime() {
	var timer = document.getElementById('timer');
	var currenttime = new Date();

	year = currenttime.getYear();
	if (year < 1000) {
		// firefox
		year += 1900;
	}
	month = currenttime.getMonth() + 1;
	cdate = currenttime.getDate();
	hour = currenttime.getHours();
	minute = currenttime.getMinutes();
	second = currenttime.getSeconds();

	if (currenttime.getDay() == 0) {
		day = '日';
	} else if (currenttime.getDay() == 1) {
		day = '一';
	} else if (currenttime.getDay() == 2) {
		day = '二';
	} else if (currenttime.getDay() == 3) {
		day = '三';
	} else if (currenttime.getDay() == 4) {
		day = '四';
	} else if (currenttime.getDay() == 5) {
		day = '五';
	} else if (currenttime.getDay() == 6) {
		day = '六';
	}

	currenttime = null;

	var strcurrenttime = '<font color=#ffffff>' + year + '年' + month + '月' + cdate + '日&nbsp;<br/>星期' + day + '&nbsp;';
	if (hour <= 9) {
		strcurrenttime += '0';
	}
	strcurrenttime += hour + ':';
	if (minute <= 9) {
		strcurrenttime += '0';
	}
	strcurrenttime += minute + ':';
	if (second <= 9) {
		strcurrenttime += '0';
	}
	strcurrenttime += second + '</font>';

	timer.innerHTML = strcurrenttime;
	setTimeout(showTime, 1000);
}

function MM_swapImgRestore() { // v3.0
	var i, x, a = document.MM_sr;
	for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
		x.src = x.oSrc;
}

function MM_findObj(n, d) { // v4.01
	var p, i, x;
	if (!d)
		d = document;
	if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
		d = parent.frames[n.substring(p + 1)].document;
		n = n.substring(0, p);
	}
	if (!(x = d[n]) && d.all)
		x = d.all[n];
	for (i = 0; !x && i < d.forms.length; i++)
		x = d.forms[i][n];
	for (i = 0; !x && d.layers && i < d.layers.length; i++)
		x = MM_findObj(n, d.layers[i].document);
	if (!x && d.getElementById)
		x = d.getElementById(n);
	return x;
}

function MM_swapImage() { // v3.0
	var i, j = 0, x, a = MM_swapImage.arguments;
	document.MM_sr = new Array;
	for (i = 0; i < (a.length - 2); i += 3)
		if ((x = MM_findObj(a[i])) != null) {
			document.MM_sr[j++] = x;
			if (!x.oSrc)
				x.oSrc = x.src;
			x.src = a[i + 2];
		}
}

function MM_preloadImages() { // v3.0
	var d = document;
	if (d.images) {
		if (!d.MM_p)
			d.MM_p = new Array();
		var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
		for (i = 0; i < a.length; i++)
			if (a[i].indexOf("#") != 0) {
				d.MM_p[j] = new Image;
				d.MM_p[j++].src = a[i];
			}
	}
}

function navigate(index, moduleId) {
	document.getElementById('moduleText' + index).style.background = '#003377'; // 设置当前选中的模块高亮
	for (var i = 0; i < totalMenu; i++) {
		if (i != index) {
			document.getElementById('moduleText' + i).style.background = '#2C577F';
		}
	}
	parent.frames['navigation'].loadData(moduleId);
}

function doLogout() {
	top.document.location.href = appRoot + '/logout.action';
}

function doExchange() {
	var _url = appRoot + "/setting/user/password/mainView.action";
	top.frames[2].addTab("修改密码", _url);
}