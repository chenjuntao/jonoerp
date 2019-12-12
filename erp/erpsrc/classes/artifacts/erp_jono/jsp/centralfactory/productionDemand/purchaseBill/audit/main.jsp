<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit purchase bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/audit/main.js?Version=<%=currenttime %>"></script>
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
				<td class="text_left" colspan="3">
					<span style="padding-left: 100px;">
						<input type="button" onclick="doQuery();" value="刷新">
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
					<td class="label_right" style="width: 80px;">单据编号：</td>
					<td class="text_left" style="width: 180px;">
						<span id="l_formId"></span>
						<span style="margin-left: 10px" ><b style='border: 1px green solid;' id="l_status"></b></span>
					</td>
					
					<td class="label_right" style="width: 70px;">供应商：</td>
					<td class="text_left" style="width: 150px;">
						<span id="l_provider"></span>
					</td>
					
					<td class="label_right" style="width: 70px;">到货日期：</td>
					<td class="text_left">
						<span id="l_receiveTime"></span>
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
					<td class="label_right">备注：</td>
					<td class="text_left" colspan="5">
						<span id="l_formNote">${requestHeader.formNote }</span>
					</td>
				</tr>
			</table>
			
			<div id="dataGrid" style="top: 0px;"></div>
<!-- 			<div id="dataGrid" style="top: 122px;"></div> 央厂原料采购单审核界面优化-->
			<div id="grid_print" class="print-only"></div>
			
			<table id="commandTable" class="hovertable">
				<tr>
					<td class="text_left" colspan="4" style="text-align: center;">
					    <input type="button" value="审    核" onclick="doAudit();" style="width: 60px" />
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>

</html>
