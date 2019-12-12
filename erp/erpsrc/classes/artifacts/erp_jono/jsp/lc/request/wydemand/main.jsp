<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/wydemand/main.js?Version=<%=currenttime %>"></script>
	
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/> 
</head>

<body bgcolor="white" style="overflow: hidden;">
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr valign="middle">
				<td class="label_right" style="width: 100px;">营业日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
						style="width: 150px;" onFocus="WdatePicker()" />
					<span style="padding: 0px 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
						 style="width: 150px;" onFocus="WdatePicker()" />
						
					<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;">
				</td>
			</tr>
		</table>
	</form>
	
	<p class="area_blank">&nbsp;</p>
	<div id="dataGrid" ></div>

	<div id="detailGrid" style="overflow: auto"></div>
	
</body>

</html>
