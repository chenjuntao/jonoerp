<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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

<html><head>
</head>

<%
	String appRoot = request.getContextPath();
%>

<body bgcolor="white">

		<table class="hovertable" width="88%" border="1" 
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="left">
			<tr>
				<td width="162"class="label_right">门店选择：</td>
				<td width="161" class="text_left"><label>
				  <select name="shopSelect" id="shopSelect">
			      </select>
			  </label></td>
				<td width="148"class="label_right">开始时间：</td>
				<td width="154"class="label_right">时间控件</td>
				<td width="154"class="label_right">结束时间：</td>
				<td width="152"class="label_right">时间控件</td>
				<td width="159" class="text_left"><label>
				  <input type="submit" name="Submit3" value="查询">
			  </label></td>
			</tr>
			
</table>		
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>注：查询并显示该门店符合时间区间的入库单</p>
		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
    <tr valign="middle">
      <td width="800"><div align="Left"><strong>入库单列表</strong></div></td>
    </tr>
  </table>
  <table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
    <tr  align="center" bgcolor="#006600">
      <th style="text-align:center;">序号</th>
      <th style="text-align:center;">入库单号</th>
      <th style="text-align:center;">供应商</th>
      <th style="text-align:center;">采购单号</th>
      <th style="text-align:center;">入库部门</th>
      <th style="text-align:center;">入库人员</th>
      <th style="text-align:center;">入库日期</th>
      <th style="text-align:center;">备注</th>
      <th style="text-align:center;">主要入库品</th>
      <th style="text-align:center;">入库总额</th>
      <th style="text-align:center;">单据状态</th>
      <th style="text-align:center;">查看操作</th>
    </tr>
    <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">1</th>
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
      <td><a
					href='javascript: parent.addTab("入库单审核-入库单+编号", "<%=appRoot %>/jsp/restaurant/prototype/putinstorage/queryputinstoragebill/queryPutInStorageBills.jsp");'
					>查看</a></td>
    </tr>
    <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">2</th>
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
      <td><a
					href='javascript: parent.addTab("入库单审核-入库单+编号", "<%=appRoot %>/jsp/restaurant/prototype/putinstorage/queryputinstoragebill/queryPutInStorageBills.jsp");'
					>查看</a></td>
    </tr>
    <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">3</th>
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
      <td><a
					href='javascript: parent.addTab("入库单审核-入库单+编号", "<%=appRoot %>/jsp/restaurant/prototype/putinstorage/queryputinstoragebill/queryPutInStorageBills.jsp");'
					>查看</a></td>
    </tr>
    <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">......</th>
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
      <td><a
					href='javascript: parent.addTab("入库单审核-入库单+编号", "<%=appRoot %>/jsp/restaurant/prototype/putinstorage/queryputinstoragebill/queryPutInStorageBills.jsp");'
					>查看</a></td>
    </tr>
  </table>
  </p>
  <p>

</p>
</body>
</html>
