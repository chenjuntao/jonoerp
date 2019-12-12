<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm before creating anti audit bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/antiaudit/create/check.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${shippingHeader.formId }';
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />

	<form id="billForm" method="post" >
		<input type='hidden' name='antiauditHeader.formRefId' value='${shippingHeader.formId }' />
		<input type='hidden' name='antiauditHeader.antiauditorId' value='${antiauditHeader.antiauditorId }' />
		<input type='hidden' name='antiauditHeader.antiauditor' value='${antiauditHeader.antiauditor }' />
		<input type='hidden' name='antiauditHeader.antiauditBranchId' value='${antiauditHeader.antiauditBranchId }' />
		<input type='hidden' name='antiauditHeader.antiauditBranch' value='${antiauditHeader.antiauditBranch }' />
		<input type='hidden' name='antiauditHeader.antiauditTime' value='${antiauditTime }' />
		<input type='hidden' name='antiauditHeader.formNote' value='${antiauditHeader.formNote }' />
		<input type='hidden' name='shippingHeader.providerId' value='${shippingHeader.providerId}' />
		<input type='hidden' name='shippingHeader.provider' value='${shippingHeader.provider}' />
		
	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">配送单编号：</td>
				<td class="text_left" colspan="5">
					<span>${shippingHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">配送部门：</td>
				<td class="text_left" style="width: 150px;">
					<span>${shippingHeader.providerId }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">出库仓库：</td>
				<td class="text_left">
					<span>${shippingHeader.outStorage }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">配送日期：</td>
				<td class="text_left" >
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">订货部门：</td>
				<td class="text_left">
					<span>${shippingHeader.requester }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">入库仓库：</td>
				<td class="text_left">
					<span>${shippingHeader.inStorage }</span>
				</td>
				
				<td class="label_right">订货地址：</td>
				<td class="text_left" >
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
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
					<span id="l_formNote">${shippingHeader.formNote }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">反审核人员：</td>
				<td class="text_left">					
					<span>${antiauditHeader.antiauditor }</span>
				</td>
				
				<td class="label_right">反审核部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${antiauditHeader.antiauditBranch }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">反审核日期：</td>
				<td class="text_left">
					<span>${antiauditTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">反审核备注：</td>
				<td class="text_left" colspan="2">
					<span id="l_formNote">${antiauditHeader.formNote }</span>
				</td>
				<td style="text-align: left;" colspan="3">
			    <input type="button" id="btn_submit" value="确认生成配送反审核记录" style="padding-left: 8px;" />
			</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top: 273px;">	</div>
		
	</form>	
</body>

</html>
