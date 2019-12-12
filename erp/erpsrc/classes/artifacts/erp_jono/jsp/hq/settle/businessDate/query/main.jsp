<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>monthly settle</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=dojoBase%>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/hq/settle/businessDate/query/main.js?Version=<%=currenttime %>"></script>

	<style type="text/css">
	@import "<%=dojoBase%>/dojo/resources/dojo.css";
	@import "<%=dojoBase%>/dgrid/css/dgrid.css";
	@import "<%=dojoBase%>/dgrid/css/skins/claro.css";
	</style>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/hq/settle/businessDate/query/main.css">
</head>

<body class="claro" style="overflow-y: auto; overflow-x: hidden;">
	<p class="area_blank">&nbsp;</p>

	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="2">
				<div style="padding-left: 8px;">
					<strong>所有部门的基本信息：</strong>
				</div>
			</td>
			<td class="text_left">
				<input type="button" id="refreshBtn" onclick="doQuery();" value="刷新" style="margin-left: 50px;">
			</td>
		</tr>
	</table>
	
	<div id="dataGrid"></div>
</body>

</html>
