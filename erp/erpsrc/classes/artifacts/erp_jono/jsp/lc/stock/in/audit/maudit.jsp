<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>manaul audit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/in/audit/maudit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
    <!-- 引用新增入库单页面create的样式 -->
	<link rel="stylesheet"  href="<%=appRoot %>/jsp/lc/stock/in/audit/edit.css"></link>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
			<input type='hidden' name='inputHeader.formId' value='${inputHeader.formId }' />
		<input type='hidden' id="formRefId" name='inputHeader.formRefId' value='${inputHeader.formRefId }' />
		<input type='hidden' name='inputHeader.formType' value='${inputHeader.formType }' />
		<input type='hidden' name='inputHeader.inputDepartmentId' value='${inputHeader.inputDepartmentId }' />
		<input type='hidden' name='inputHeader.inputTime' value='${inputTime }' />
		<input type='hidden' name='inputHeader.storageId' value='${inputHeader.storageId }' />
		<input type='hidden' name='inputHeader.storage' value='${inputHeader.storage }' />
			<input type='hidden' id="allPayAmt" name='inputHeader.allPayAmt' value='${inputHeader.allPayAmt }' />
		<input type='hidden' id="maxPayItem" name='inputHeader.maxPayItem'	value='${inputHeader.maxPayItem }' />
		
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">入库单单据编号：</td>
				<td class="text_left" style="width: 150px;">
					<span>${inputHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">入库部门：</td>
				<td class="text_left">
					<span>${inputHeader.inputDepartment }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${inputHeader.inputer }</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${inputTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
					<span id="l_formNote">${inputHeader.formNote }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid" class="auditGrid"></div>
		
	
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1" >
		<tr>
			<td class="label_right" style="width: 120px;">审核人：</td>
			<td class="text_left" style="width: 180px;">
				<span>${inputHeader.auditor }</span>
			</td>
			<input type='hidden' name='inputHeader.auditorId' value='${loginUserId }' />
			<input type='hidden' name='inputHeader.auditor' value='${inputHeader.auditor  }' />
			<td class="label_right" style="width: 120px;">审核日期：</td>
			<td class="text_left">
				<input type="text" class="Wdate" id="auditTime" name="inputHeader.auditTime" value="${auditTime}" />
			</td>
		</tr>
			
		<tr>
			<td style="text-align: center;" colspan="5">
			    <input type="button" onclick="doAudit();" id="btn_submit" value="入库单审核确认"  />
			    <input type="button" onclick="doClose();" value="取消" style="margin-left: 50px;" />
			</td>
		</tr>
	</table>
	</form>
</body>

</html>
