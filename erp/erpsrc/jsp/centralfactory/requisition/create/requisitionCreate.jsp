<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/requisition/create/requisitionCreate.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/requisition/create/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
	
	<input type="hidden" name="requisitionHeader.formRefId" value='${formId}'/>
	<input type="hidden" name="requisitionHeader.workshop" value="${requisitionHeader.workshop}"/>
	<input type="hidden" name="requisitionHeader.storageId" value="${requisitionHeader.storageId}"/>
	<input type="hidden" name="requisitionHeader.storage" value=" ${requisitionHeader.storage}"/>
	<input type="hidden" name="requisitionHeader.formMaker" value=" ${requisitionHeader.formMaker}"/> 
	<input type="hidden" name="requisitionHeader.formMakerId" value="${requisitionHeader.formMakerId}"/>  
	<input type="hidden" name="formTime" value="${formTime}"/>
	<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">关联单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${formId}</span>
				</td>
				
				<td class="label_right" style="width: 120px;">收货仓库：</td>
				<td class="text_left" style="width: 180px;" >
					<span>${requisitionHeader.storage}</span>
				</td>
				
				<td class="label_right" style="width: 120px;">制单人员：</td>
				<td class="text_left" style="width: 180px;" >
					<span>${requisitionHeader.formMaker}</span>
				</td>
				
				<td class="label_right" style="width: 120px;">制单日期：</td>
				<td class="text_left" style="width: 180px;" >
					<span>${formTime}</span>
				</td>
			</tr>
			<tr>
			<td class="label_right" style="width: 80px;">商品：</td>
				<td class="text_left" style="width: 180px;"><span>${requisitionHeader.itemName }</span>
				</td>
			<td class="label_right" style="width: 120px;">生产车间：</td>
				<td class="text_left" style="width: 180px;" >
					<span>${requisitionHeader.workshop}</span>
				</td>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="7">
	  				<input type="text" name="requisitionHeader.formNote" value='' style="width: 200px;" />
	  				
				    <input type="button" onclick="doCommit();" value="领料单生成" style="margin-left: 58px;" />
			    	<input type="button" onclick="doClose();" value="取消" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<input type="hidden" id="queryStr" name="queryStr" />
		
 	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
 	 	<input type="hidden" name="requisitionHeader.formType" value='produce' />
		<div id="detailDataGrid">
		</div>
	</form>
</body>

</html>
