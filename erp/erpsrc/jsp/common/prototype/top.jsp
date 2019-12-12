<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!--
<meta http-equiv="refresh" name="content-type" content="3600" />
-->
	<title>连锁餐饮ERP系统</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<link href="<%=appRoot %>/jsp/common/leftmenu/rico.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript' src='<%=appRoot %>/jsp/common/ajax/request.js'></script>

<!-- 增加时钟，黄杰 2007.01.11 -->
<script type="text/JavaScript">
<!--
	setTimeout(showTime, 3000);
	setTimeout(keepSession, 600000);

	function keepSession() {
		sendRequest('<%=appRoot %>/jsp/common/jsp/dummy.jsp', '');
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
			//firefox
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

		var strcurrenttime = '<font color=#ffffff>' + year + '年' + month + '月'
				+ cdate + '日&nbsp;<br/>星期' + day + '&nbsp;';
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
	-->
</script>

<script type="text/JavaScript">
<!--
	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}

	function MM_findObj(n, d) { //v4.01
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

	function MM_swapImage() { //v3.0
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

	function MM_preloadImages() { //v3.0
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

	function navigate(module) {
		parent.frames['navigation'].location.href = appRoot + '/jsp/' + module + "/navigation.jsp";
	}
	
	function pnavigate(module) {
		parent.frames['navigation'].location.href = appRoot + '/jsp/common/prototype/' +  module + ".jsp";
	}
//-->
</script>
</head>

<body leftmargin="1" topmargin="0" rightmargin="0"
	style="overflow: hidden;">
	<div id="top_display_big">
		<table width="100%" height="70" border="0" cellpadding="0"
			cellspacing="0">
			<!--DWLayoutTable-->
			<tr bgcolor="#2C577F" class="a1">


				<td width="200" align="center" valign="bottom">数据查询</td>
				<td width="200" align="center" valign="bottom">餐厅管理</td>
				<td width="200" align="center" valign="bottom">物流中心</td>
				<td width="200" align="center" valign="bottom">中央工厂</td>
				<td width="200" align="center" valign="bottom">总部功能</td>
				<td width="200" align="center" valign="bottom">供应管理</td>
				<td width="220" align="center" valign="bottom">外部订货</td>
				<td width="200" align="center" valign="bottom">系统服务</td>
				<td width="360" align="right"><img src="<%=appRoot %>/jsp/common/img/nudt.gif"
					width="280" height="25" /></td>
			</tr>

			<tr>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="navigate('businessquery');return false;"><img
							src="<%=appRoot %>/jsp/common/img/query.png"
							onmouseout="this.src='<%=appRoot %>/jsp/common/img/query.png';" name="image1"
							width="50" height="50" border="0" id="image1" /></a>
					</div></td>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="pnavigate('restaurant');return false;"><img
							src="<%=appRoot %>/jsp/common/img/restaurant_management.png" name="image2"
							width="50" height="50" border="0" id="image2" /></a>
					</div></td>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="pnavigate('lc');return false;"><img
							src="<%=appRoot %>/jsp/common/img/logistics_center.png" name="image3" width="50"
							height="50" border="0" id="image3" /></a>
					</div></td>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="navigate('centralfactory');return false;"><img
							src="<%=appRoot %>/jsp/common/img/centre_factory.png" name="image4" width="50"
							height="50" border="0" id="image4" /></a>
					</div></td>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="navigate('headquarters');return false;"><img
							src="<%=appRoot %>/jsp/common/img/headquarters.png" name="image5" width="50"
							height="50" border="0" id="image5" /></a>
					</div></td>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="navigate('supplier');return false;"><img
							src="<%=appRoot %>/jsp/common/img/provider.png" name="image5" width="50"
							height="50" border="0" id="image5" /></a>
					</div></td>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="navigate('outerorderparty');return false;"><img
							src="<%=appRoot %>/jsp/common/img/external.png" name="image5" width="50"
							height="50" border="0" id="image5" /></a>
					</div></td>
				<td width="180" background="<%=appRoot %>/jsp/common/img/d.png"><div
						align="center">
						<a href="#" onclick="navigate('setting');return false;"><img
							src="<%=appRoot %>/jsp/common/img/system.png"
							onmouseout="this.src='<%=appRoot %>/jsp/common/img/system.png';" name="image5"
							width="50" height="50" border="0" id="image5" /></a>
					</div></td>
				<td align="center" valign="bottom" background="<%=appRoot %>/jsp/common/img/d.png"
					class="label">
					<!--    <%
    String curUser = (String)(session.getAttribute("currentUser"));
    %>
    <span id="user">当前用户:<%=curUser%>&nbsp;&nbsp;&nbsp;&nbsp;</span>    -->
					<span id="timer">正在获取时间...</span>
				</td>
			</tr>
		</table>



	</div>


	<div id="top_display_small"
		style="display: none; width: 100%; height: 100%;">

		<table width="100%" height="30" border="0" cellpadding="0"
			cellspacing="0">
			<!--DWLayoutTable-->
			<tr bgcolor="#2C577F" class="a1">

				<td width="30" align="left"><a href="javascript:displayBig();">
						<img width="22" height="17" border="0" alt="折叠导航框架"
						src="<%=appRoot %>/jsp/common/img/toc.gif">
				</a></td>

			</tr>

			<tr>
				<td width="30" background="<%=appRoot %>/jsp/common/img/d.png"></td>
				</td>

			</tr>
		</table>
	</div>
</body>
</html>
