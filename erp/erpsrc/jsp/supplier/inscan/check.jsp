<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>review before submit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
		<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/inscan/check.js?Version=<%=currenttime %>"></script>
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
		var formId = '${formId }';
		var status = '${status}';
		var parentFormId ='${parentFormId}';
	</script>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='inputHeader.formId' value='${inputHeader.formId }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">对应采购单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${inputHeader.formRefId }</span>
				</td>
				<td class="label_right" style="width: 120px;">入库单单据编号：</td>
				<td class="text_left" colspan="5">
					<span>${inputHeader.formId }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">入库部门：</td>
				<td class="text_left">
					<span>${inputHeader.inputDepartment }</span>
				</td>
				<td class="label_right" style="width: 120px;">入库人员：</td>
				<td class="text_left" style="width: 90px;">
					<span>${inputHeader.inputer }</span>
				</td>
				<td class="label_right" style="width: 90px;">入库仓库：</td>
				<td class="text_left" colspan="5">
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
					<span>${inputHeader.provider }</span>
				</td>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
					<span id="l_formNote">${inputHeader.formNote }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<div id="dataGrid" >
		</div>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" id="btn_submit" value="入库单确认" style="padding-left: 8px;" />
			</td>
		</tr>
	</table>

</body>

</html>
