<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/poscan/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/poscan/order.js"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/query/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/supplier/poscan/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<form id="dataForm" method="post" >

<input type="hidden" name="branchType" value="${branchType}"/>

	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 60px;">供应商：</td>
			<td class="text_left" >
				<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
					 style="width: 180px;" id="supplierId" name="supplierId" headerKey="" headerValue="------请选择供应商------"> </s:select>
			</td>
			
			<td class="label_right" style="width: 70px;">单据编号：</td>
			<td class="text_left" >
				<input type="text" id="formIdText" name="formIdText"/>
			</td>
				
			<td class="label_right" style="width: 80px;">单据日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" value="${startDate }" name="startDate" style="width: 100px;"	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
					<span style="padding: 0 5px;">至</span> 
					<input	type="text" class="Wdate" id="endDate" value="${endDate }" name="endDate" style="width: 100px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
					
					<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;" />
			</td>
		</tr>
		
		<tr>
			<td class="label_right" style="width: 60px;">订单类型：</td>
			<td class="text_left" colspan="6" >
				<input type="radio" name="orderType" value="UNIFIED">统配</input>
				<input type="radio" name="orderType" value="DIRECT">直配</input>
				<input type="radio" name="orderType" value="CROSS">越库</input>
				<input type="radio" name="orderType" value="" checked="checked" >全部</input>
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid" style="top: 60px;">
	</div>
	
	</form>
</body>

</html>
