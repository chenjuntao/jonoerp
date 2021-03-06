<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/inventory/summary/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/inventory/summary/export.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/inoutquery/inventory/summary/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
	<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>仓库汇总帐</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" >门店编码：</td>
				<td class="text_left" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple" onchange="refreshStorage1()"
						 style="width: 150px;" id="branchId" name="branchId" ></s:select>
				</td>
				
				<td class="label_right" >选择仓库：</td>
				<td>
					<s:select list="storageList" listKey="storageId" listValue="storageName" theme="simple"
					style="width: 180px;" id="storageId" name="storageId"  headerKey="" headerValue="--请选择--"></s:select>
					<input type="hidden" id="storageName" name="storageName" />
				</td>
				<td style="width:100px;">
					<input type="button" id="DIYwithnoModel" onclick="selMaterial();" value="选择原料" style="margin-left: 50px;" />
					<input type="hidden" id=selectedItemId  name="selectedItemId" value=""/>
					<input type="hidden" id=selectedItemName  name="selectedItemName" value=""/>
					
				</td>
				<td style="width:250px;">
					<span id="displayItemName" ></span>
				</td>
			</tr>
			
<!-- 			开始日期和结束日期默认为当天的前一天 -->
			<tr>
				<td class="label_right" style="width: 150px;">开始日期：</td>
				<td class="text_left" style="width: 200px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
							 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" />
				</td>
				<td class="label_right" style="width: 150px;">结束日期：</td>
				<td class="text_left" style="width: 200px;">
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
							onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="6">
			   	 	<input type="button" onclick="doQuery();" value="查询" />
			   	 	<input type="button" onclick="exportXls();" value="导出EXCEL">
					<input type="button" onclick="exportCsv();" value="导出文本">
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid">
		</div>
	</form>
</body>

</html>
