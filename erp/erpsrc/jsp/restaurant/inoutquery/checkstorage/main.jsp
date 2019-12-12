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
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/checkstorage/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/checkstorage/export.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/inoutquery/checkstorage/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">制单日期：</td>
				<td class="text_left" style="width: 260px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}" style="width: 110px;"	 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" />
					<span style="padding: 0 5px;">至</span>
					
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"	style="width: 110px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				</td>
				
				<td class="label_right" style="width: 80px;">盘点部门：</td>
				<td class="text_left">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 style="width: 150px;" id="branchId"></s:select>
						 
					<span style="padding-left: 50px;">
						<input type="button" onclick="doQuery();" value="查    询" style="width: 60px;">
						<input type="button" onclick="exportXls();" value="导出EXCEL" style="margin-left: 10px" >
						<input type="button" onclick="exportCsv();" value="导出文本" style="margin-left: 10px" >
					</span>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"  class="commonGrid" style="top: 30px;"></div>
	</form>
</body>

</html>
