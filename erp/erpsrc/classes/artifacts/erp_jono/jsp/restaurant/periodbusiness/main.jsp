<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
 	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
  	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/periodbusiness/main.css"/>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/periodbusiness/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/periodbusiness/export.js?Version=<%=currenttime %>"></script>
	
</head>

<body bgcolor="white" class="claro">
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr valign="middle">
				<td class="label_right" style="width: 80px;">开始日期：</td>
				<td class="text_left" style="width: 120px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}" onFocus="WdatePicker()" style="width: 110px;"/>
				</td>
				
				<td class="label_right" style="width: 80px;">结束日期：</td>
				<td class="text_left" style="width: 120px;">
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"	onFocus="WdatePicker()" style="width: 110px;"/>
				</td>
				
				<td class="label_right"  style="width: 60px;">门店：</td>
				<td class="text_left"  style="width: 150px;" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 style="width: 130px;" id="shopC" name="shopC"></s:select>
				</td>
				
				<td class="text_left">
						<div id="placeHolder"></div>
						<input type="button" onclick="exportXls();" value="导出EXCEL" style="margin-left: 10px">
						<input type="button" onclick="exportCsv();" value="导出文本" style="margin-left: 10px">
				</td>
			</tr>
		</table>
	</form>
	
	<p class="area_blank">&nbsp;</p>
 	
	<div id="dataGrid"></div>
</body>

</html>
