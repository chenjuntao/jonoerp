<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/dreject/create/check.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/dreject/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
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
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">退货单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${returnHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">配送单编号：</td>
				<td class="text_left" colspan="3">
					<span>${shippingHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">配送部门：</td>
				<td class="text_left">
					<span>${shippingHeader.providerId }</span>
				</td>
				
				<td class="label_right">配送日期：</td>
				<td class="text_left" colspan="3">
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">入库部门：</td>
				<td class="text_left">
				</td>
				
				<td class="label_right">入库地址：</td>
				<td class="text_left" colspan="3">
				</td>
			</tr>
			
			<tr>
				<td class="label_right">入库人员：</td>
				<td class="text_left">
					<span>${shippingHeader.inputer }</span>
				</td>
				
				<td class="label_right">入库日期：</td>
				<td class="text_left" colspan="3">
					<span>${inputTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货人员：</td>
				<td class="text_left">
					<span>${returnHeader.returner }</span>
				</td>
				
				<td class="label_right">退货日期：</td>
				<td class="text_left" colspan="3">
					<span>${returnTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货总金额：</td>
				<td><span id="sumMoney">0</span></td>
				
				<td class="label_right">退货单备注：</td>
				<td class="text_left" colspan="2">
					<span id="l_formNote">${returnHeader.formNote }</span>
				</td>
			</tr>
			
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid" class="commonGrid"  style="top: 185px;bottom:32px"></div>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable"  width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" id="btn_submit" value="确认生成配送退货单" style="padding-left: 8px;" />
			</td>
		</tr>
	</table>

</body>

</html>
