<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>

    <link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">

    <link rel="stylesheet" href="<%=dojoBase %>/dojox/grid/enhanced/resources/claro/EnhancedGrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css">
	<style type="text/css">
 
        /*Grid needs an explicit height by default*/
        #gridDiv {
            height: 40em;
        }
    </style>
    
    <link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
    <script src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:true"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/billCodeQuery/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/billCodeQuery/export.js?Version=<%=currenttime %>"></script>
</head>

<body class="claro">

<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="5">
					<div style="padding-left: 8px;">
						<b>按单据号查看</b>
					</div>
				</td>
			</tr>
			<tr valign="middle">
				<td class="label_right" style="width: 120px;">查询开始日期：</td>
				<td class="text_left" style="width: 200px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
							 onFocus="WdatePicker()" />
				</td>
				<td class="label_right" style="width: 120px;">查询结束日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
						onFocus="WdatePicker()" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="label_right">门店：</td>
				<td class="text_left">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
						 style="width: 120px;" id="shopC" name="shopC"></s:select>
				</td>
				
					<td class="label_right">单据号：</td>
				<td class="text_left">
					<input type="text" id="billCode" name="billCode" value="">
				</td>
				
				<td>
					<span style="padding-left: 100px;"> 
						<input type="button" onclick="doQuery();" value="查询">
					</span>
					<input type="button" onclick="exportXls();" value="导出EXCEL">
					<input type="button" onclick="exportCsv();" value="导出文本">
				</td>	
			</tr>
		</table>
	</form>
	
	
    <div id="gridDiv"></div>
</body>

</html>
