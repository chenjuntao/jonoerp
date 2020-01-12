<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
    <script src="<%=dojoBase %>/dojo/dojo.js" ></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/foodSummary/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/foodSummary/export.js?Version=<%=currenttime %>"></script>
	
    <link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/businessquery/foodSummary/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
    <link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
</head>

<body class="claro">

<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="10">
					<div style="padding-left: 8px;">
						<b>出品销售统计汇总表</b>
					</div>
				</td>
			</tr>
			<tr valign="middle">
				<td class="label_right" >开始日期：</td>
				<td class="text_left" style="width: 120px" >
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
							 onFocus="WdatePicker()" />
				</td>
				<td class="label_right" >结束日期：</td>
				<td class="text_left"  style="width: 120px" >
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
						onFocus="WdatePicker()" />
				</td>
				<td class="label_right">门店：</td>
				<td class="text_left">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
						 style="width: 120px;" id="shopC" name="shopC"></s:select>
				</td>
				
				<td >
					<span> 
						<div id="placeHolder"></div>
						<input type="button" onclick="exportXls();" value="导出EXCEL">
						<input type="button" onclick="exportCsv();" value="导出文本">
					</span>
				</td>
				
			</tr>
		</table>
	</form>
	
	
    <div id="gridDiv" class="commonGrid" style="top: 65px;"></div>
</body>

</html>
