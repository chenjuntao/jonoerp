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
说明：显示餐厅相关某特定采购单信息
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
				<td width="800">
				<div align="Left">
				  <b>采购单表头信息</b>				  </div></td>
			</tr>
		</table>
		
	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th width="10%" style="text-align:center;">单据编号</th>
			<th width="10%" style="text-align:center;">采购部门</th>
			<th width="10%" style="text-align:center;">采购人员</th>
			<th width="10%" style="text-align:center;">制单人员</th>
			<th width="10%" style="text-align:center;">制单日期</th>
			<th width="10%" style="text-align:center;">审核人员</th>
			<th width="10%" style="text-align:center;">审核日期</th>
			<th width="10%" style="text-align:center;">供应商</th>
			<th width="10%" style="text-align:center;">收货地址</th>
			<th width="10%" style="text-align:center;">单据状态</th>
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

	
    <br/>
		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800">
				<div align="Left">
				  <b>采购单表单内容信息</b>				  </div></td>
			</tr>
		</table>
		
	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th width="10%" style="text-align:center;">序号</th>
			<th width="10%" style="text-align:center;">商品编码</th>
			<th width="10%" style="text-align:center;">商品名称</th>
			<th width="10%" style="text-align:center;">单位</th>
			<th width="10%" style="text-align:center;">规格</th>
			<th width="10%" style="text-align:center;">订货数</th>
			<th width="10%" style="text-align:center;">单价</th>
			<th width="10%" style="text-align:center;">金额</th>
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
</body>
</html>
