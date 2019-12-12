<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/create/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/create/print.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/print.css" id="print">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">门店：</td>
				<td class="text_left"  style="width: 190px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"  style="width: 180px;" id="branchId" name="requestHeader.buyerId"></s:select>
					<input type="hidden" id="branchName" name="branchName" value='' />
					
				</td>
				
				<td class="label_right" style="width: 150px;">商品名称或编码：</td>
				<td class="text_left" style="width: 150px;">
	 				<input type="text" id="itemName" name="itemName" style="width: 80px;"	onkeydown="if(event.keyCode==13){doQuery();return false;}" />
	 			</td>
	 			<td>
	 			<span style="padding-left: 50px;">
						<input type="button" onclick="doQuery();" value="查    询" style="width: 60px">
					</span>
	 			</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="billTree"></div>
		
		<div id="billInfo">
			<input type='hidden' id="formId" value='' />
			
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td class="label_right" style="width: 100px;">单据编号：</td>
					<td class="text_left" style="width: 180px;">
						<span id="l_formId">${requestHeader.formId }</span>
					</td>
					
					<td class="label_right" style="width: 70px;">供应商：</td>
					<td class="text_left" style="width: 180px;">
						<span id="l_providerId"></span>
						<input type='hidden' id="supplierId" value='' />
					</td>
					
					<td class="label_right" style="width: 80px;">到货日期：</td>
					<td class="text_left">
						<span id="l_receiveTime">${receiveTime }</span>
					</td>
				</tr
				>
				<tr>
					<td class="label_right">收货部门：</td>
					<td class="text_left">
						<span id="l_receiver"></span>
					</td>
					
					<td class="label_right">收货地址：</td>
					<td class="text_left" colspan="3">
						<span id="l_receiveAddress"></span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">制单人员：</td>
					<td class="text_left">
						<span id="l_formMaker"></span>
					</td>
					
					<td class="label_right">制单日期：</td>
					<td class="text_left" colspan="3">
						<span id="l_formTime"></span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">审核人员：</td>
					<td class="text_left">
						<span id="l_auditor"></span>
					</td>
					
					<td class="label_right">审核日期：</td>
					<td class="text_left" colspan="3">
						<span id="l_auditTime"></span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">备注：</td>
					<td class="text_left" colspan="5">
						<span id="l_formNote">${requestHeader.formNote }</span>
					</td>
				</tr>
			</table>
			
			<div id="dataGrid" class="commonGrid" style="top: 152px;"></div>
			
			<div id="grid_print" class="print-only"></div>
			
			<table id="commandTable" class="hovertable">
				<tr>
					<td class="text_left" colspan="4" style="text-align: center;">
					    <input type="button" value="打印预览" onclick="prn1_preview();" />
					    <input type="button" value="根据采购单入库" onclick="putinStorage();" style="margin-left: 20px"/>
					    <input type="button" value="采购单结束" onclick="doFinish();"  style="margin-left: 20px"/>
					</td>
				</tr>
		  </table>
		</div>
	</form>
</body>

</html>
