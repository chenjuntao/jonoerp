<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit picking bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/picking/audit/audit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/picking/audit/audit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro">
	<form id="billForm" method="post" >
		<s:token/>
		
		<input type='hidden' id="formId" name='pickingHeader.formId' value='${formId }' />
	  	<input type="hidden" id="branchIds" name="branchIds" value='' />
		<input type='hidden' id="pickingBranchId" name='pickingHeader.pickingBranchId' value='' />
		<input type='hidden' id="storageId" name='pickingHeader.storageId' value='' />
		<input type='hidden' name='pickingHeader.auditorId' value='${loginUserId }' />
		<input type='hidden' name='pickingHeader.auditor' value='${loginUsername }' />
		<input type='hidden' name='pickingHeader.auditTime' value='${businessDate }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" style="width: 150px;">
					<span id="l_formId"></span>
				</td>
				
				<td class="label_right" style="width: 70px;">制单人员：</td>
				<td class="text_left" style="width: 120px;">
					<span id="l_formMaker"></span>
				</td>
				
				<td class="label_right" style="width: 70px;">制单日期：</td>
				<td class="text_left">
					<span id="l_formTime"></span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">审核人员：</td>
				<td class="text_left">
					<span>${loginUsername }</span>
				</td>
				
				<td class="label_right">审核日期：</td>
				<td class="text_left">
					<span>${businessDate }</span>
				</td>
				
				<td class="label_right">捡货仓库：</td>
				<td class="text_left">
					<span id="l_storage"></span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" id="formNote" name="pickingHeader.formNote" 	value="" style="width: 380px;" />
					
					<div id="placeHolder"></div>
					<div id="saveHolder"></div>
<!-- 			    <button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: doSave'>暂存</button> -->
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" style="top: 92px;"></div>
	</form>
</body>

</html>
