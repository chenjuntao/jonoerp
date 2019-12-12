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
	<script type="text/javascript" 
		src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/paymentquery/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/paymentquery/dayquery.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/paymentquery/export.js?Version=<%=currenttime %>"></script>
	
	<!-- required: the default dijit theme: -->
	<link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body bgcolor="white" class="claro">
	<form id="queryForm">
		<table class="hovertable" width="100%" align="center">
				<tr valign="middle">
				<td  colspan="7">
				<div align="left">
						<b>付款方式查询</b>
					</div></td>
			</tr>
			
			<c:set var="now" value="<%=new java.util.Date()%>" />

			<tr>
				<td style="width: 120px;">&nbsp;请选择开始日期：</td>
				<td style="width: 180px;">
					<input type="text" class="Wdate" id="startDate" name="startDate"
							 value=" <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
						onFocus="WdatePicker()" /></td>

			
				<td style="width: 120px;">&nbsp;请选择结束日期：</td>
				<td>
					<input type="text" class="Wdate" id="endDate"	name="endDate" 
				 		value=" <fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
						onFocus="WdatePicker()" /></td>
			</tr>
			<tr>
				<td>&nbsp;统计方式：</td>
				<td>
					<select id="type">
					  <option value ="1">累计</option>
					  <option value ="2">按天</option>
					</select>
				</td>
				<td colspan="2">
					<input type="button" onclick="doQuery();" value="查询">
					<input type="button" onclick="exportXls();" value="导出EXCEL">
					<input type="button" onclick="exportCsv();" value="导出文本">
				</td>
			</tr>
		</table>
	</form>
	
	<div id="dataGrid">
	</div>
</body>

</html>
