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
	
<%-- 	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script> --%>
<!-- 	<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0 style="position:absolute;left:0px;top:-10px;"> </object>  -->
<%-- 	<object id="LODOP_EM" type="application/x-print-lodop" pluginspage="<%=appRoot %>/jsp/common/plugins/install_lodop32.exe" width=0 height=0 style="position:absolute;left:0px;top:-10px;"></object> --%>

	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
<!-- 	<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>  -->
<%-- 		<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="<%=appRoot %>/jsp/common/plugins/install_lodop32.exe"></embed> --%>
<!-- 	</object>  -->

	
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/query.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/create/print.js?Version=<%=currenttime %>"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/print.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">订货部门：</td>
				<td class="text_left" colspan="3">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
						 style="width: 180px;" id="branchId" headerKey="-1" headerValue="--请选择--"></s:select>
					<input type="hidden" id="branchName" name="branchName" value='' />
					
					<span style="padding-left: 100px;">
						<input type="button" onclick="doQuery();" value="查询">
					</span>
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="billTree">
		</div>
		
		<div id="billInfo">
			<input type='hidden' id="formId" value='' />
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td class="label_right" style="width: 80px;">单据编号：</td>
					<td class="text_left" style="width: 180px;">
						<span id="l_formId">${requestHeader.formId }</span>
					</td>
					<td class="label_right" style="width: 70px;">供应商：</td>
					<td class="text_left" style="width: 120px;">
						<span id="l_provider"></span>
					</td>
					<td class="label_right" style="width: 70px;">到货日期：</td>
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
					<td class="text_left">
						<span id="l_formTime"></span>
					</td>
					<td class="label_right">配送方式：</td>
					<td class="text_left">
						<span id="l_deliveryType"></span>
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
			<div id="dataGrid">
			</div>
			<div id="grid_print" class="print-only">
			</div>
			
		</div>
		
		<table id="commandTable">
			<tr>
				<td class="text_left" colspan="4" style="text-align: center;">
				    <input type="button" value="打印预览" onclick="prn1_preview();" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
