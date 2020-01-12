<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>

<html>
<head>
</head>

<%
	String appRoot = request.getContextPath();
%>

<body bgcolor="white">

		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			<tr>
				<td width="120"class="label_right">门店选择：</td>
				<td class="text_left">
				</td>
			</tr>
			
				<tr valign="middle">
				<td width="800">
				<div align="Left"><b>未审核要货单列表（列出该门店所有未审核要货单）</b></div></td>
			</tr>
	
		</table>
		
	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th style="text-align:center;">序号</th>
			<th style="text-align:center;">要货单编号</th>
			<th style="text-align:center;">制单部门</th>
			<th style="text-align:center;">制单人</th>
			<th style="text-align:center;">制单日期</th>
			<th style="text-align:center;">到货日期</th>
			<th style="text-align:center;">备注</th>
			<th style="text-align:center;">要货模板</th>
			<th style="text-align:center;">主要要货</th>
			<th style="text-align:center;">要货总额</th>
			<th style="text-align:center;">单据状态</th>
			<th style="text-align:center;">审核操作</th>
		</tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>未审核</td>
				<td>
				<a
					href='javascript: parent.addTab("餐厅要货审核-单号", "<%=appRoot %>/jsp/restaurant/prototype/goodsbillprocess/confirmgoodsbill/confirmGoodsBill.jsp");'
					>审核</a>				</td>
	</table>
</body>
</html>
