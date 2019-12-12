<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>central factory create purchase input bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/inventory/in/create/main.js?Version=<%=currenttime %>"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/in/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 60px;">制单日期：</td>
			<td class="text_left" style="width: 270px;">
				<input type="text" class="Wdate" id="startDate" value="${startDate}"	style="width: 110px;" onFocus="WdatePicker()" />
				<span style="padding: 0 5px;">至</span>
				<input type="text" class="Wdate" id="endDate" value="${endDate}" style="width: 110px;" onFocus="WdatePicker()" />
			</td>
				<td class="label_right" style="width: 100px;">商品名称或编码：</td>
				<td class="text_left" style="width: 100px;">
	 				<input type="text" id="itemName" name="itemName" style="width: 80px;"	onkeydown="if(event.keyCode==13){doQuery();return false;}" />
	 			</td>
	 			<td>
	 			<span style="padding-left: 100px;">
						<input type="button" onclick="doQuery();" value="查    询" style="width: 60px">
					</span>
	 			</td>
		</tr>
	</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="billTree"></div>
		
		<div id="billInfo">
			<input type='hidden' id="formId" value='' />
			<input type='hidden' name='deliveryType' id='deliveryType' value=""/>
			<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td class="label_right" style="width: 80px;">单据编号：</td>
					<td class="text_left" style="width: 180px;">
						<span id="l_formId">${requestHeader.formId }</span>
					</td>
					
					<td class="label_right" style="width: 70px;">供应商：</td>
					<td class="text_left" style="width: 160px;">
						<span id="l_provider"></span>
						<input type='hidden' id="supplierId" value='' />
					</td>
					
					<td class="label_right" style="width: 60px;">到货日期：</td>
					<td class="text_left">
						<span id="l_receiveTime">${receiveTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">订货部门：</td>
					<td class="text_left">
						<span id="l_requester"></span>
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
					<td class="text_left" >
						<span id="l_formNote">${requestHeader.formNote }</span>
					</td>
					<td class="label_right">是否生成：</td>
					<td class="text_left" colspan="3">
						<font color=red size= 5><span id="l_tager" ></span></font>
					</td>
				</tr>
			</table>
			
			<div id="dataGrid" style="top:0px"></div>
			<div id="grid_print" class="print-only"></div>
			
			<table id="commandTable" class="hovertable" >
				<tr>
					<td class="text_left" colspan="4" style="text-align: center;">
					    <input type="button" value="根据采购单入库" onclick="putinStorage();" />
					    <input type="button" value="采购单结束" onclick="doFinish();" style="margin-left: 50px" />
					</td>
				</tr>
			</table>
			
		</div>
	
	</form>
</body>

</html>
