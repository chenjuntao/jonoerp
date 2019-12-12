<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/distribution/audit/audit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/distribution/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
	
		<input type='hidden' id="formId" name="shippingHeader.formId" value='${formId }' />
		<input type='hidden' name='shippingHeader.auditTime' value='${auditTime }' />
		<input type='hidden' name='shippingHeader.auditor' value='${shippingHeader.auditor }' />
		<input type='hidden' name='shippingHeader.auditorId' value='${shippingHeader.auditorId }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">配送单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${shippingHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">配送部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">配送日期：</td>
				<td class="text_left">
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">订货部门：</td>
				<td class="text_left">
					<span>${shippingHeader.requester }</span>
				</td>
				
				<td class="label_right">订货地址：</td>
				<td class="text_left" colspan="3">
					<span>${shippingHeader.requestAddress }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${shippingHeader.formMaker }</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left" colspan="3">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">审核人员：</td>
				<td class="text_left">
					<span>${shippingHeader.auditor }</span>
				</td>
				
				<td class="label_right">审核日期：</td>
				<td class="text_left" colspan="3">
					<span>${auditTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="shippingHeader.formNote" 
	  					value="${shippingHeader.formNote}" style="width: 380px;" />
					<span id="l_formNote">${shippingHeader.formNote }</span>
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="editGrid" ></div>
	</form>
		
	<table id="commandTable" class="hovertable">
		<tr>
			<td class="text_left" colspan="4" style="text-align: center;">
			    <input type="button" value="确认审核" id="auditBtn" onclick="doAudit();" />
			</td>
		</tr>
	</table>
</body>

</html>
