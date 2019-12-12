<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/stock/in/confirm/confirm.js?Version=<%=currenttime %>"></script>
	
   <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var receivedCount = '${receivedCount }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		<input type='hidden' id="formRefId" name='formRefId' value='${inputHeader.formRefId }' />
		<input type='hidden' name='formId' value='${formId }' />
		<input type='hidden' id="storageId" name='storageId' value='${inputHeader.storageId }' />
		<input type='hidden' id="storage" name='inputHeader.storage' value='${inputHeader.storage}' />
		 <input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		 
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">工单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${inputHeader.formRefId }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">入库单编号：</td>
				<td class="text_left">
					<span>${inputHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">入库部门：</td>
				<td class="text_left">
					<span>${inputHeader.inputDepartment }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">入库人员：</td>
				<td class="text_left">
					<span>${inputHeader.inputer }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">入库日期：</td>
				<td class="text_left">
					<span>${inputTime }</span>
				</td>
				
				<td class="label_right">备注：</td>
				<td class="text_left">
					<span id="l_formNote">${inputHeader.formNote }</span>
					
			    	<input type="button" onclick="doConfirm();" id="btnSubmit" value="确认入库"	 style="margin-left: 58px;" />
			    		 
	    		   <span style="padding-left: 30px;">
						<input name="nextTag" type="checkbox" style="vertical-align: middle; margin: 0px;padding:0px;" />
						允许下次入库
					</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="scanGrid" style="top: 92px;"></div>
	</form>	
</body>

</html>
