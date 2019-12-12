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
时间：2014-07-16
说明：显示餐厅特定出品的成本卡信息
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
		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800"><strong>出品信息</strong></td>
			</tr>
		</table>

	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th width="9%" style="text-align:center;">出品编码</th>
			<th width="15%" style="text-align:center;">出品名称</th>
			<th width="13%" style="text-align:center;">单位</th>
			<th width="13%" style="text-align:center;">助记码</th>
			<th width="13%" style="text-align:center;">大类</th>
			<th width="13%" style="text-align:center;">小类</th>
			<th width="13%" style="text-align:center;">成本价</th>
			<th width="13%" style="text-align:center;">销售价</th>
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
	  </tr>
	</table>
		<br/>
		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800"><strong>成本卡组成</strong></td>
			</tr>
		</table>
		
	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th width="9%" style="text-align:center;">序号</th>
			<th width="15%" style="text-align:center;">编号</th>
			<th width="16%" style="text-align:center;">名称</th>
			<th width="10%" style="text-align:center;">类型</th>
			<th width="10%" style="text-align:center;">单位</th>
			<th width="10%" style="text-align:center;">原料/半成品</th>
			<th width="10%" style="text-align:center;">净料耗量</th>
		</tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>

	  </tr>
	</table>
			<br/>
		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800"><strong>出品配方说明</strong></td>
			</tr>
		</table>

	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th width="21%" style="text-align:center;">工艺说明</th>
			<th width="12%" style="text-align:center;">菜式口感</th>
			<th width="12%" style="text-align:center;">菜式类型</th>
			<th width="55%" style="text-align:center;">工艺流程</th>
		</tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>

	  </tr>
	</table>
	
	
</body>
</html>
