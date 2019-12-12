<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ include file="/jsp/common/jsp/path.jsp"%>

<script src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
<script type="text/javascript"	src="<%=appRoot%>/jsp/common/lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>	
<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/shopListQuery/dayquery.js"></script>
<script type="text/javascript"	src="<%=appRoot%>/jsp/businessquery/shopListQuery/main.js?Version=<%=currenttime %>"></script>
	
<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/shopListQuery/export.js?Version=<%=currenttime %>"></script>
	

<link id="themeStyles" rel="stylesheet"	href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" />
<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body bgcolor="white">

	<form id="queryForm">
		<table class="hovertable" width="100%" align="center">
			<tr valign="middle">
				<td colspan="8">
					<div align="left">
						<b>门店收入明细信息</b>
					</div>
				</td>
			</tr>

			<tr valign="middle">
				<td width="150">&nbsp;请选择开始日期：</td>
				<td><input type="text" class="Wdate" id="startDate"
					name="startDate" value="${startDate}"
					onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1});}'})" /></td>


				<td width="150">&nbsp;请选择结束日期：</td>
				<td><input type="text" class="Wdate" id="endDate"
					name="endDate" value="${endDate}"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" /></td>

				<td width="80">&nbsp;统计方式：</td>
				<td><select id="type">
						<option value="1">累计</option>
						<option value="2">按天</option>
				</select></td>

				<td colspan="2">
					<div align="center">
						<input type="button" onclick="doQuery();" value="查询">
						<input type="button" onclick="exportXls();" value="导出EXCEL">
						<input type="button" onclick="exportCsv();" value="导出文本">
					</div>
				</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>

	<div id="dataGrid"></div>
</body>
</html>
