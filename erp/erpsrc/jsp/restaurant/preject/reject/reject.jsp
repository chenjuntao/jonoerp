<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/manage/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/reject/reject.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/dreject/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<input type="hidden" id="branchType" value='${branchType}'/>
	
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='returnHeader.formId'	 value='${returnHeader.formId }' />
		<input type='hidden' name='returnHeader.formRefId'	 value='${returnHeader.formRefId }' />
		<input type='hidden' name='returnHeader.storageId' value='${returnHeader.storageId }' />
		<input type='hidden' name='returnHeader.providerId' value='${inputHeader.providerId}' />
		<input name="branchFlag" type="hidden" value="${branchFlag }" id="branchFlag"/>
		<input type="hidden" class="Wdate" id="auditTime" name="returnHeader.auditTime" value="${auditTime}" />
			<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">退货单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${returnHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">入库单编号：</td>
				<td class="text_left" colspan="3">
					<span>${inputHeader.formId }</span>
				</td>
			</tr>
			
			<td class="label_right">入库部门：</td>
			<td class="text_left">
				<span>${inputHeader.inputDepartment }</span>
			</td>
			
			<td class="label_right" >仓库：</td>
				<td class="text_left" colspan="3">
				<span>${inputHeader.storage }</span>
			</td>
			</tr>
			
			<tr>
				<td class="label_right">供应商：</td>
				<td class="text_left" >
					<span>[${inputHeader.providerId }]${inputHeader.provider }</span>
				</td>
				
				<td class="label_right">入库人员：</td>
				<td class="text_left" style="width: 150px;">
					<span>${inputHeader.inputer }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">入库日期：</td>
				<td class="text_left">
					<span>${inputTime }</span>
				</td>
			</tr>

			<tr>
				<td class="label_right">退货人员：</td>
				<td class="text_left">					
					<span>${returnHeader.returner }</span>
				</td>
				
				<td class="label_right">退货制单日期：</td>
				<td class="text_left" colspan="3">
					<span>${returnTime }</span>
				</td>
			</tr>
			
			<tr>
				
				<td class="label_right">退货单备注：</td>
				<td class="text_left" colspan="5">
					<span id="l_formNote">${returnHeader.formNote }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="auditGrid"></div>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doProcess();" value="采购退货单实施" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>

</body>

</html>
