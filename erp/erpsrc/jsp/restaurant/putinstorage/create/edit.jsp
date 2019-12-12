<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/create/edit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var deliveryType = '${deliveryType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post">
		<input type='hidden' id="formId" name="formId" value='${formId }' />
		<input type='hidden' id="branchId" name="branchId" value='${branchId }' />
		<input type='hidden' id="supplierId" name="supplierId" value='${supplierId }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="inputHeader.formNote" 	value="${purchasingHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="editGrid" ></div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	</form>
		
	<table id="commandTable" class="hovertable" >
		<tr>
			<td class="text_left" colspan="4" style="text-align: center;">
			    <input type="button" value="确认生成入库单" onclick="doSubmit();" />
			</td>
		</tr>
	</table>
</body>

</html>
