<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Logistics Center estimate</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/handsontable/jquery.min.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/jquery/isloading/jquery.isloading.min.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/handsontable/handsontable.full.min.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/jquery/common.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/in/create/handsontable/create.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
    
	<link rel="stylesheet" href="<%=dojoBase %>/handsontable/handsontable.full.min.css">
	<link rel="stylesheet" href="<%=dojoBase %>/jquery/isloading/isloading.css">
	<link rel="stylesheet" href="<%=dojoBase %>/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/summary/estimate.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
		<script type="text/javascript">
		var deliveryType = '${deliveryType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
		<form id="billForm" method="post">
		<input type='hidden' id="formId" name="formId" value='${formId }' />
		<input type='hidden' id="branchId" name="branchId" value='${logCode}' />
		<input type='hidden' id="supplierId" name="supplierId" value='${supplierId }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 90px;">入库仓库：</td>
				<td class="text_left">
					<s:select list="storeLst" listKey="storageId" listValue="storageName" theme="simple" style="width: 180px;" id="storageId" name="inputHeader.storageId"></s:select>
					<input type="hidden" id="storage" name="inputHeader.storage" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="inputHeader.formNote" 	value="${purchasingHeader.formNote}" style="width: 380px;" />
			    	<input type="button" value="确认生成入库单" onclick="doSubmit();" style="margin-left: 58px;"/>
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="handsontable htColumnHeaders"></div>
	</form>
</body>

</html>
