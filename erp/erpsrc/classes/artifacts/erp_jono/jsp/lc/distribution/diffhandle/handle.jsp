<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>handle distribution difference</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/distribution/diffhandle/handle.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/distribution/diffhandle/handle.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		
		<input type='hidden' name='shippingHeader.formId' value='${shippingHeader.formId }' />
		<input type='hidden' name='shippingHeader.providerId'  value='${shippingHeader.providerId }' />
		<input type='hidden' name='shippingHeader.outStorageId'  value='${shippingHeader.outStorageId }' />
		<input type='hidden' name='shippingHeader.provider'  value='${shippingHeader.provider }' />
		<input type='hidden' name='shippingHeader.outStorage'  value='${shippingHeader.outStorage}' />
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
			 
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" colspan="7">
					<span>${shippingHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">配送部门：</td>
				<td class="text_left" style="width: 100px;">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">配送日期：</td>
				<td class="text_left" style="width: 150px;">
					<span>${receiveTime }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">订货部门：</td>
				<td class="text_left" style="width: 120px;">
					<span>${shippingHeader.requester}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">订货地址：</td>
				<td class="text_left">
					<span>${shippingHeader.requestAddress }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${shippingHeader.formMaker }</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime}</span>
				</td>
				
				<td class="label_right">入库人员：</td>
				<td class="text_left">
					<span>${shippingHeader.inputer }</span>
				</td>
				
				<td class="label_right">入库时间：</td>
				<td class="text_left">
					<span>${inputTime}</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="4" style="border-right: 0px;">
	  				<span>${shippingHeader.formNote }</span>
				</td>
				
				<td class="text_left" colspan="3" style="border-left: 0px;">
			    	<input type="button" value="确认处理差异" onclick="doHandle();" />
				</td>
			</tr>
		</table>
	  
	  	<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid"></div>
	</form>
</body>

</html>
