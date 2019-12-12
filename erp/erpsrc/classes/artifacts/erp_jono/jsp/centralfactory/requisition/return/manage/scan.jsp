<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" 
		src="<%=appRoot %>/jsp/centralfactory/requisition/return/manage/scan.js?Version=<%=currenttime %>"></script>
	
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
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 110px;">单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requisitionHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">工单编号：</td>
				<td class="text_left" colspan="1">
					<span>${requisitionHeader.formRefId }</span>
				</td>
				<td class="label_right" style="width: 80px;">商品名称：</td>
				<td class="text_left" style="width: 200px;">
	  				<span>${requisitionHeader.itemName }</span>
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
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="detailDataGrid"></div>
	</form>
</body>

</html>
