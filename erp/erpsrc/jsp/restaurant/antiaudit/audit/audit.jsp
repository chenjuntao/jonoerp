<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit antiaudit bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/antiaudit/audit/audit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript">
		var g_formRefId = '${formRefId }';
	</script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formRefId" name='antiauditHeader.formRefId' value='${antiauditHeader.formRefId }' />
		<input type='hidden' name='branchId' value='${shippingHeader.requesterId }' />	<!-- 传递到后台，用于确认反审核时对物流中心实行扣库处理 -->
		<input type='hidden' name='antiauditHeader.providerId' value='${shippingHeader.providerId }' />
		<s:token/>
	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		 	
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
				<td class="label_right">反审核人员：</td>
				<td class="text_left">					
					<span>${antiauditHeader.antiauditor }</span>
				</td>
				
				<td class="label_right">反审核部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${antiauditHeader.antiauditBranch }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">反审核日期：</td>
				<input type="hidden" id="antiauditTime" name="antiauditTime" value='${antiauditTime }' />
				<input type="hidden" id="lastDate" name="lastDate" value='${lastDate }' />
				<td class="text_left">
					<span>${antiauditTime }</span>
				</td>
			</tr>
			<tr>
				
				<td class="label_right">审核日期：</td>
				<td class="text_left" colspan="1">
					<span>${auditTime }</span>
				<input type="hidden" id="auditTime" name="antiauditHeader.auditTime" value='${auditTime }' />
				</td>
				<td class="label_right">反审核备注：</td>
				<td class="text_left" colspan="5">
					<span id="l_formNote">${antiauditHeader.formNote }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid" class="commonGrid" style="top: 208px;bottom:30px"></div>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" id="btn_submit" value="确认进行配送反审核" style="margin-right: 20px;"  />
			    <input type="button" id="btn_repeal" value="撤消配送反审核" style="margin-left: 20px;" />
			</td>
		</tr>
	</table>

</body>

</html>
