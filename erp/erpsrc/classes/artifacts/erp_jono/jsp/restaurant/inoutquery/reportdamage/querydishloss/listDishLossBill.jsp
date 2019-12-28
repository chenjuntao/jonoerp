<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/inoutquery/reportdamage/querydishloss/listDishLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/inoutquery/reportdamage/querydishloss/export.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/inoutquery/reportdamage/querydishloss/main.css">
</head>

<body class="claro" style="overflow:hidden">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
<form id="listLossForm">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<th colspan="6">
				<b>${brandWord}出品报损单列表</b>
			</th>
		</tr>
		<tr>
			<td>开始日期：
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
							 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" />
			</td>
			<td>结束日期：
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
							onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
			</td>
			<td>
				<b>选择门店:</b>
				<s:select list="shopList" listKey="code" listValue="name" theme="simple"
				style="width: 140px;" id="branchId" name="branchId" headerKey="" headerValue="--请选择--" onchange="refreshStorage()"></s:select>
				<input type="hidden" id="branch" name="branch" />
			</td>
			<td>
				<b>选择仓库：</b>
				<s:select list="storageList" listKey="storageId" listValue="storageName" theme="simple"
				style="width: 140px;" id="storageId" name="storageId"  headerKey="" headerValue="--请选择--"></s:select>
				<input type="hidden" id="storageName" name="storageName" />
			</td>
			<td>
					<input type="button" onclick="doQuery()" value="查询" />
					<input type="button" onclick="exportXls();" value="导出EXCEL">
					<input type="button" onclick="exportCsv();" value="导出文本">
			</td>
		</tr>
	</table>
	<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	<div id="listLossGrid"></div>
</form>
</body>

</html>
