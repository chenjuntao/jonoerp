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
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/foodAmtQuery/main.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body bgcolor="white" style="overflow: hidden;">
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="7">
					<div style="padding-left: 8px;">
						<b>万元出品分析</b>
					</div>
				</td>
			</tr>
			<tr valign="middle">
				<td class="label_right" style="width: 100px;">选择门店：</td>
				<td class="text_left">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple" name="shopC" />
				</td>
				<td class="label_right" style="width: 120px;">开始日期：</td>
				<td class="text_left" style="width: 200px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
							 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:-1});}'})" />
				</td>
				<td class="label_right" style="width: 120px;">结束日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
						onFocus="WdatePicker({onpicked:doCategoryQuery,minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				</td>
				
				<td class="text_left">
					<span style="padding-left: 100px;">
						<input type="button" onclick="doCategoryQuery();" value="查询">
					</span>
				</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>

	<div id="dataGrid" style="height: 135px;"></div>
	
	<div id="detailDataGrid" style="overflow: auto"></div>
	
</body>

</html>
