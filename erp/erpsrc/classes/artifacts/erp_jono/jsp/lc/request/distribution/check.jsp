<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm create</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/distribution/check.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/distribution/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
	
		<input type='hidden' name='formRefId' value='${formRefId }' />
		<input type='hidden' id="formId" name='shippingHeader.formId' value='${formId }' />
		<input type='hidden' name='shippingHeader.formType' value='INNER_UNIFIED' />
		<input type='hidden' name='shippingHeader.requesterId' value='${shippingHeader.requesterId }' />
		<input type='hidden' name='shippingHeader.requester' value='${shippingHeader.requester }' />
		<input type='hidden' name='shippingHeader.requestAddress' value='${shippingHeader.requestAddress }' />
		<input type='hidden' name='shippingHeader.outStorageId' value='${shippingHeader.outStorageId }' />
		<input type='hidden' name='shippingHeader.outStorage' value='${shippingHeader.outStorage }' />
		<input type='hidden' name='shippingHeader.providerId' value='${shippingHeader.providerId }' />
		<input type='hidden' name='shippingHeader.provider' value='${shippingHeader.provider }' />
		<input type='hidden' name='shippingHeader.receiveTime' value='${receiveTime }' />
		<input type='hidden' name='shippingHeader.formNote' value='${shippingHeader.formNote }' />
		<input type='hidden' name='shippingHeader.formMakerId' value='${shippingHeader.formMakerId }' />
		<input type='hidden' name='shippingHeader.formMaker' value='${shippingHeader.formMaker }' />
		<input type='hidden' name='shippingHeader.formTime' value='${formTime }' />
		
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 90px;">配送部门：</td>
				<td class="text_left" style="width: 150px;">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">配送日期：</td>
				<td class="text_left" colspan="3">
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 100px;">订货部门：</td>
				<td class="text_left">
					<span>${shippingHeader.requester }</span>
				</td>
				
				<td class="label_right">订货地址：</td>
				<td class="text_left">
					<span>${shippingHeader.requestAddress }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">出库仓库：</td>
				<td class="text_left">
					<span>${shippingHeader.outStorage }</span>
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
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
					<span id="l_formNote">${shippingHeader.formNote }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top:122px;bottom:33px" ></div>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" id="btn_submit" value="确认生成配送单" style="padding-left: 8px;" />
			</td>
		</tr>
	</table>

</body>

</html>
