<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create anti audit bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/antiaudit/create/edit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<form id="billForm" method="post" >
		<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
		<input type='hidden' id="formId" name="formId" value='${formId }' />
		<input type='hidden' name='antiauditHeader.antiauditorId' value='${antiauditHeader.antiauditorId }' />
		<input type='hidden' name='antiauditHeader.antiauditor' value='${antiauditHeader.antiauditor }' />
		<input type='hidden' name='antiauditHeader.antiauditBranchId' value='${antiauditHeader.antiauditBranchId }' />
		<input type='hidden' name='antiauditHeader.antiauditBranch' value='${antiauditHeader.antiauditBranch }' />
		<input type='hidden' name='antiauditHeader.antiauditTime' value='${antiauditTime }' />
		
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
				
				<td class="label_right" style="width: 120px;">出库仓库：</td>
				<td class="text_left">
					<span>${shippingHeader.outStorage }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">订货部门：</td>
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
				<td class="text_left" >
					<span>${formTime }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">配送日期：</td>
				<td class="text_left">
					<span>${receiveTime }</span>
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
				<td class="label_right">配送单备注：</td>
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
				<td class="text_left">
					<span>${antiauditHeader.antiauditBranch }</span>
				</td>
				
				<td class="label_right">反审核日期：</td>
				<td class="text_left">
					<span>${antiauditTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">反审核备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="antiauditHeader.formNote" 
	  					value="${antiauditHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="commonGrid" style=" top: 243px;bottom: 25px"></div>
	</form>
		
	<table id="commandTable"  class="hovertable">
		<tr>
			<td class="text_left" colspan="4" style="text-align: center;">
			    <input type="button" value="确认生成反审核记录" onclick="doSubmit();" />
			</td>
		</tr>
	</table>
</body>

</html>
