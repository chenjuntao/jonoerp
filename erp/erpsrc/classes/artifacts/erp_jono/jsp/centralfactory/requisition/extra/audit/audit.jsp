<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" 
		src="<%=appRoot %>/jsp/centralfactory/requisition/extra/audit/audit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/requisition/create/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='requisitionHeader.formId' value='${requisitionHeader.formId }' />
		<input type='hidden'  name='requisitionHeader.formRefId' value='${requisitionHeader.formRefId}' />
		<input type='hidden'  name='requisitionHeader.formTime' value='${formTime}' />
		<input type='hidden'  name='requisitionHeader.formType' value='extra' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 110px;">单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requisitionHeader.formId }</span>
				</td>
				<td class="label_right" style="width: 90px;">关联单据编号：</td>
				<td class="text_left" colspan="3">
					<span>${requisitionHeader.formRefId }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left"><span>${requisitionHeader.formMaker}</span></td>

				<td class="label_right">制单日期：</td>
				<td class="text_left" style="width: 150px;"><span>${formTime }</span></td>
				
				<td class="label_right" style="width: 80px;">备注：</td>
				<td class="text_left">
	  				<span>${requisitionHeader.formNote }</span>
	  				
			    	<input type="button" onclick="doAudit();" value="审核通过"
			    		 style="margin-left: 58px;" />
			    	<input type="button" onclick="doClose();" value="取消"
			    		 style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	  
	   <input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  
		<p class="area_blank">&nbsp;</p>
		<div id="detailDataGrid">
		</div>
	</form>
</body>

</html>
