<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create check bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/create/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/checkstorage/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var g_batchId = '${checkHeader.checkBatchId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' name='checkHeader.formId' value='${checkHeader.formId }' />
		<input type="hidden" name="checkHeader.formType" value='form' />
		<input type="hidden" name="checkHeader.checkBatchId" value='${checkHeader.checkBatchId }' />
		<input type="hidden" name="checkHeader.checkBranchId" value='${checkHeader.checkBranchId }' />
		<input type="hidden" name="checkHeader.checkBranch" value='${checkHeader.checkBranch }' />
		<input type='hidden' name='checkHeader.formMakerId' value='${checkHeader.formMakerId }' />
		<input type='hidden' name='checkHeader.formMaker' value='${checkHeader.formMaker }' />
		<input type='hidden' name='checkHeader.checkStorageId' id="checkStorageId" value='${checkHeader.checkStorageId}' />
		<input type='hidden' name='checkHeader.formTime' value='${formTime }' />
		<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<s:token/>
			
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">盘点部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${checkHeader.checkBranch }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">对应盘点批次：</td>
				<td class="text_left">
					<span>${checkHeader.checkBatchId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="checkHeader.formNote" value="${checkHeader.formNote}" style="width: 380px;" />
	  				
			    	<input type="button" onclick="doSave();" id="btn_submit" value="确认生成盘点单" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
	
		<div id="dataGrid" class="commonGrid" style="top:95px"></div>
	</form>
</body>

</html>
