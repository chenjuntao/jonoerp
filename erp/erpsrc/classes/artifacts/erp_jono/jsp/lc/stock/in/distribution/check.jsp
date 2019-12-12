<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>stock in check</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/in/distribution/check.js?Version=<%=currenttime %>"></script>
	
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
		<input type='hidden' id="formId" name='shippingHeader.formId' value='${shippingHeader.formId }' />
		<input type='hidden' name='shippingHeader.requesterId' value='${shippingHeader.requesterId }' />
		<input type='hidden' name='shippingHeader.providerId' value='${shippingHeader.providerId }' />
		<input type='hidden' name='shippingHeader.formNote' value='${shippingHeader.formNote }' />
		<input type='hidden' name='shippingHeader.allPayAmt' value='${shippingHeader.allPayAmt }' />
		<input type='hidden' name='shippingHeader.maxPayItem' value='${shippingHeader.maxPayItem }' />
		
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
	  	
		<div id="dataGrid" class="editGrid" style="top: 92px;"></div>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable"  width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" id="btn_submit" value="确认填充配送单" style="padding-left: 8px;" />
			</td>
		</tr>
	</table>

</body>

</html>
