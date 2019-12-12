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
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/orderSummary/create/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/orderSummary/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="10">
					<div style="padding-left: 8px;">
						<b>采购单信息</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" >单据编号：</td>
				<td class="text_left"  colspan="9">
					<span>${purchasingHeader.formId }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">供应商：</td>
				<td class="text_left">
					<span>${purchasingHeader.provider}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">订货部门：</td>
				<td class="text_left">
					<span>${purchasingHeader.requester}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">收货地址：</td>
				<td class="text_left">
					<span>${purchasingHeader.receiveAddress}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">审核人员：</td>
				<td class="text_left">
					<span>${purchasingHeader.auditor}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">审核日期：</td>
				<td class="text_left">
					<span>${auditTime}</span>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">收货仓库：</td>
				<td class="text_left">
					<span>${purchasingHeader.storage}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">到货日期：</td>
				<td class="text_left">
					<span>${receiveTime}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">配送方式：</td>
				<td class="text_left">
					<span>${deliveryType}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">制单人员：</td>
				<td class="text_left">
					<span>${purchasingHeader.formMaker}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">制单日期：</td>
				<td class="text_left">
					<span>${formTime}</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left"  colspan="9">
					<span>${purchasingHeader.formNote}</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid">
		</div>
		
	</form>
		
	<p class="area_blank">&nbsp;</p>
</body>

</html>
