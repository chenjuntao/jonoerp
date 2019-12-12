<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<!-- 
作者：吴鹏
时间：2014-07-18
说明：显示餐厅原材料、半成品的库存信息
    -->
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

<body bgcolor="white">

<!--此界面分为两部分，左边是一个树形控件，显示原料、半成品的类别，选取后右方区域是该类别的原料、半成品库存信息列表；
目前queryTheoryStock.jsp文件仅列出了右方区域的内容，左方树形控件还未实现
如遇详细问题不清楚，请与小陈协商解决-->

<!--左方区域，选择示特定类型的原料、半成品，如不选表示查看所有库存，未实现-->


<!--右方区域，显示特定类型的原料、半成品的库存信息列表-->

		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800">
				<div align="Left">
				  <b>餐厅原材料、半成品库存信息</b>				  </div></td>
			</tr>
		</table>
		
	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th width="10%" style="text-align:center;">序号</th>
			<th width="10%" style="text-align:center;">编码</th>
			<th width="10%" style="text-align:center;">名称</th>
			<th width="10%" style="text-align:center;">助记码</th>
			<th width="10%" style="text-align:center;">单位</th>
			<th width="10%" style="text-align:center;">类别</th>
			<th width="10%" style="text-align:center;">库存数量</th>
			<th width="10%" style="text-align:center;">批次</th>
			<th width="10%" style="text-align:center;">保质期</th>
			<th width="10%" style="text-align:center;">供应商</th>
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
				</tr>
	</table>

	
</body>
</html>
