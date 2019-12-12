<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>review before submit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/create/commit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
	
		<input type='hidden' id="formId" name='inputHeader.formId' value='${inputHeader.formId }' />
		<input type='hidden' id="formRefId" name='inputHeader.formRefId' value='${inputHeader.formRefId }' />
		<input type='hidden' name='inputHeader.formType' value='PURCHASE' />
		<input type='hidden' name='inputHeader.inputDepartmentId' value='${inputHeader.inputDepartmentId }' />
		<input type='hidden' name='inputHeader.inputDepartment' value='${inputHeader.inputDepartment }' />
		<input type='hidden' name='inputHeader.inputerId' value='${inputHeader.inputerId }' />
		<input type='hidden' name='inputHeader.inputer' value='${inputHeader.inputer }' />
		<input type='hidden' name='inputHeader.storageId' value='${inputHeader.storageId }' />
		<input type='hidden' name='inputHeader.storage' value='${inputHeader.storage }' />
		<input type='hidden' name='inputHeader.inputTime' value='${inputTime }' />
		<input type='hidden' name='inputHeader.providerId' value='${inputHeader.providerId }' />
		<input type='hidden' name='inputHeader.provider' value='${inputHeader.provider }' />
		<input type='hidden' name='inputHeader.formNote' value='${inputHeader.formNote }' />
		<input type='hidden' name='inputHeader.allPayAmt' value='${inputHeader.allPayAmt }' />
		<input type='hidden' name='inputHeader.maxPayItem' value='${inputHeader.maxPayItem }' />
		
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">采购单编号：</td>
				<td class="text_left" colspan="5">
					<span>${inputHeader.formRefId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">入库部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${inputHeader.inputDepartment }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">入库人员：</td>
				<td class="text_left" style="width: 120px;">
					<span>${inputHeader.inputer }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">入库仓库：</td>
				<td class="text_left">
					<span>${inputHeader.storage }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">入库日期：</td>
				<td class="text_left">
					<span>${inputTime }</span>
				</td>
				
				<td class="label_right">供应商：</td>
				<td class="text_left" colspan="3">
					<span>[${inputHeader.providerId }]${inputHeader.provider }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
					<span id="l_formNote">${inputHeader.formNote }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="inCreateGrid"></div>
		
		<table class="hovertable" id="commandTable" width="100%" border="1">
			<tr>
				<td style="text-align: center;">
				    <input type="button" id="btn_submit" value="确认生成入库单" style="padding-left: 8px;" />
				</td>
			</tr>
	   </table>
	</form>	

</body>

</html>
