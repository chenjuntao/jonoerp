<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm before creating purchase return bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/create/checkOut.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/preject/create/editOut.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<input type="hidden" id="branchType" value='${branchType}'/>
	
	<form id="billForm" method="post" >
		<input type='hidden' name='returnHeader.formId' value='${returnHeader.formId }' />
		<input type='hidden' name='returnHeader.formRefId' value='${shippingHeader.formId }' />
		<input type='hidden' name='returnHeader.returnerId' value='${returnHeader.returnerId }' />
		<input type='hidden' name='returnHeader.returner' value='${returnHeader.returner }' />
		<input type='hidden' name='returnHeader.returnTime' value='${returnTime }' />
		<input type='hidden' name='returnHeader.formNote' value='${returnHeader.formNote }' />
		<input type='hidden' name='returnHeader.providerId' value='${shippingHeader.providerId }' />
		<input type='hidden' name='returnHeader.provider' value='${shippingHeader.provider }' />
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" colspan="7">
					<span>${shippingHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">出货部门：</td>
				<td class="text_left" style="width: 100px;">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">出货日期：</td>
				<td class="text_left" style="width: 100px;">
					<span>${receiveTime }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">订货部门：</td>
				<td class="text_left" style="width: 160px;">
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
				
				<td class="label_right">审核人员：</td>
				<td class="text_left">
<%-- 					<span>${shippingHeader.auditor }</span> --%>
				</td>
				
				<td class="label_right">审核日期：</td>
				<td class="text_left">
<%-- 					<span>${auditTime}</span> --%>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="7">
	  				<span>${shippingHeader.formNote }</span>
				</td>
			</tr>
		</table>
		
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="auditGrid3" ></div>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" id="btn_submit" value="确认生成出货退货单" style="padding-left: 8px;" />
			</td>
		</tr>
	</table>

</body>

</html>
