<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!--
	<meta http-equiv="refresh" name="content-type" content="3600" />
	-->
	<title>连锁餐饮ERP系统</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type='text/javascript' src='<%=appRoot %>/jsp/common/ajax/request.js'></script>
	<script type='text/javascript' src="<%=appRoot %>/jsp/desktop/top.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript'>
	 	var totalMenu = '${fn:length(moduleLst)}';
	 	var firstModuleId = '${firstModuleId}';
	 	var space = '    ';
	</script>
	
	<style type="text/css">
		.moduleBg {
		 	vertical-align: bottom;
			background: url(<%=appRoot %>/jsp/common/img/d.png);
		}
    </style>
    
    <script type="text/javascript">
	var stuta ='${stuta}';
	if (stuta== "less") { 
		alert("营业日期小于当前时间，请先日结再操作！");
	}
	
// 	window.tab.location.href ="/erp/restaurant/dailysettlement/mainView.action?branchType=RESTAURANT";
	</script>
</head>

<body style="overflow: hidden; margin: 0px;">
	<input type="hidden" id="loginUserId" value="${loginUserId}"/>
	
	<table style="width: 100%; height: 70px; border: 0px;" cellpadding="0" cellspacing="0">
		<tr style="background: #2C577F; color: #fff; vertical-align: bottom;">
			<c:forEach var="module" items="${moduleLst}" varStatus="status" >
				<td id="moduleText${status.index }" style="width: 120px; text-align: center;">${module.name}</td>
			</c:forEach>
			<td style="text-align: right;">
				<img src="<%=appRoot %>/jsp/common/img/nudt.gif" />
			</td>
		</tr>
		
		<tr class="moduleBg" style="height: 50px;">
			<c:forEach var="module" items="${moduleLst}" varStatus="status">
				<td>
					<div style="text-align: center;">
						<a style="display: inline-block; height: 50px;" id="${module.id}"
							href="#" onclick="navigate(${status.index}, '${module.id}');return false;">
							<img style="border: 0px;" src="<%=appRoot %>/jsp/common/img/${module.imageName}" />
						</a>
					</div>
				</td>
			</c:forEach>
			
			<td style="color: #fff; text-align:right; padding-right: 30px;">
				<div id="user">
					<span style="padding-left: 15px;">
						${loginUsername}
					</span>
					<span style="padding-left: 15px;">
						${branchName}
					</span>
					<span style="padding-left: 15px;">
					<b id="businessTop">${businessDate}</b>
					</span>
				</div>
				<div> <a id="modifyPwd" style="color:white" href="#" onclick="doExchange();return false;">   修改密码</a>&nbsp&nbsp&nbsp
				<a id="exitSys" href="#" onclick="doLogout();return false;">   安全退出</a></div>
			</td>
		</tr>
	</table>
</body>
</html>
