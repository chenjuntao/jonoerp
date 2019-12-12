<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit request bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/goodsbill/confirm/audit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var preVersion = '${preVersion }';
		var branchType = '${branchType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input id="currentVersion" name="currentVersion" type="hidden" />
		<input id="preVersion" type="hidden" />
		<input type='hidden' name='requestHeader.formId'  value='${requestHeader.formId }' />
		<input type='hidden' name='requestHeader.auditor' value='${requestHeader.auditor }' />
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requestHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">要货部门：</td>
				<td class="text_left" colspan="3">
					<span >${requestHeader.buyerName }</span>
					<input type="hidden" id="shopId" name="requestHeader.buyerId" value='${requestHeader.buyerId }' />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">订货地址：</td>
				<td class="text_left">
					<span>${requestHeader.buyerAddress }</span>
				</td>
				
				<td class="label_right">到货日期：</td>
				<td class="text_left" colspan="3">
				<span>${receiveTime }</span>
				<input type="hidden" id="receiveTime" name="requestHeader.receiveTime" value='${receiveTime}' />
<%-- 					<input type="text" class="Wdate" name="requestHeader.receiveTime" value="${receiveTime}" /> --%>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${requestHeader.formMaker}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left" colspan="3">
					<span>${formTime }</span>
				</td>
			</tr>
				<tr>
				<td class="label_right" style="width: 120px;">审核人：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requestHeader.auditor }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">审核日期：</td>
				<td class="text_left" colspan="3">
					<span>${auditTime}</span>
					<input type="hidden" class="Wdate" id="auditTime"
						name="requestHeader.auditTime" value="${auditTime}" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px; border-top: 0px;">参考日期区间1：</td>
				
				<td class="text_left" style="width: 240px; border-top: 0px;">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart1" name="requestHeader.refDateStart1" value="${refDateStart1}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd1" name="requestHeader.refDateEnd1" value="${refDateEnd1}" />
				</td>
				
				<td class="label_right" style="width: 95px; border-top: 0px;">参考日期区间2：</td>
				
				<td class="text_left" style="border-top: 0px;" colspan="3">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart2" name="requestHeader.refDateStart2" value="${refDateStart2}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd2" name="requestHeader.refDateEnd2" value="${refDateEnd2}" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">参考日期区间3：</td>
				<td class="text_left">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart3" name="requestHeader.refDateStart3" value="${refDateStart3}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd3" name="requestHeader.refDateEnd3" value="${refDateEnd3}" />
				</td>
				<td class="label_right">延滞期金额：</td>
				<td class="text_left" style="width: 180px;" colspan="3">
		 			<input type="text" id="delayPredict" name="requestHeader.delayPredict"
		 				 value="${requestHeader.delayPredict}" style="width: 80px;" />（万元）
				</td>
				</tr>
				
			<tr>
				<td class="label_right" style="width: 120px;">进货周期金额：</td>
				<td class="text_left">
		 			<input type="text" id="purchasePredict" name="requestHeader.purchasePredict" 
		 				value="${requestHeader.purchasePredict}" style="width: 80px;" />（万元）
				</td>
				
				<td class="label_right">安全存量：</td>
				<td class="text_left">
		 			<input type="text" id="safetyStock" name="requestHeader.safetyStock" 
		 				value="${requestHeader.safetyStock}" style="width: 80px;" />（万元）
				</td>
					<td class="label_right">预估销售额：</td>
				<td class="text_left">
					<span id="sellPredictText">${requestHeader.sellPredict}</span>（万元）
					<input type='hidden' id="sellPredict" name='requestHeader.sellPredict' 	value='${requestHeader.sellPredict }' />
					</td>
				</tr>
				
				<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="1">
	  				<input type="text" name="requestHeader.formNote" value="${requestHeader.formNote}" style="width: 380px;" />
				</td>
			<td  colspan="4">
				<input type="button" onclick="calcSuggest();" value="重新计算建议值" style="margin-left: 50px;">
			    <input type="button" onclick="doAudit();"id="btn_submit" value="要货单审核确认" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid" class="commonGrid" style="top:241px"></div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<input type='hidden' id="allPayAmt" name='requestHeader.allPayAmt' 	value='${requestHeader.allPayAmt }' />
		<input type='hidden' id="maxPayItem" name='requestHeader.maxPayItem' value='${requestHeader.maxPayItem }' />
	</form>
</body>

</html>
