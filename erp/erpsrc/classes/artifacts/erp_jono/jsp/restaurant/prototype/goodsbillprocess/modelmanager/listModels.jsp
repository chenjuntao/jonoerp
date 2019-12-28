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

<html>
<head>
</head>

<%
	String appRoot = request.getContextPath();
%>

<body bgcolor="white">

		<table class="hovertable" width="100%" border="1" 
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			<tr>
				<td width="120"class="label_right">门店选择：</td>
				<td class="text_left">
				</td>
			</tr>
			
		</table>
		
	    <p>
	      <input name="newModel" type="submit" id="newModel" value="新增模板">
	      （弹出窗口，树形控件多选原料/半成品，输入保存模板名，生成新的模板）
	    </p>
		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800">
				<div align="Left"><b>要货单模板列表</b></div></td>
			</tr>
	
		</table>
	    <table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th style="text-align:center;">序号</th>
			<th style="text-align:center;">模板编号</th>
			<th style="text-align:center;">模板名称</th>
			<th style="text-align:center;">模板所属部门</th>
			<th style="text-align:center;">模板要货类别</th>
			<th style="text-align:center;">模板查看</th>
			<th style="text-align:center;">模板管理</th>
		</tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>
				<a
					href='javascript: parent.addTab("模板查看-模板名", "<%=appRoot %>/jsp/restaurant/prototype/goodsbillprocess/modelmanager/queryModel.jsp");'
					>查看</a>
				</td>

				<td>
				<a
					href='javascript: parent.addTab("模板管理-模板名", "<%=appRoot %>/jsp/restaurant/prototype/goodsbillprocess/modelmanager/modifyModel.jsp");'
					>管理</a>
				</td>
	</table>
    <label></label>
</body>
</html>
