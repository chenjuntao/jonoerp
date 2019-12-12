<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create stock in</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/in/create/create.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/in/create/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var deliveryType = '${deliveryType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post">
		<input type='hidden' id="formId" name="formId" value='${formId }' />
		<input type='hidden' id="branchId" name="branchId" value='${lcCode }' />
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
		
		<div id="dataGrid"></div>
	</form>
</body>

</html>
