<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>stock in</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" 
		src="<%=appRoot %>/jsp/centralfactory/production/output/stockin.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='inputHeader.formId' value='${inputHeader.formId }' />
		<input type='hidden' id="formRefId" name='inputHeader.formRefId' value='${inputHeader.formRefId }' />
		<input type='hidden' name='inputHeader.formType' value='PRODUCE' />
		<input type='hidden' name='inputHeader.formName' value='${inputHeader.formName }' />
		<input type='hidden' name='inputHeader.inputDepartmentId' value='${inputHeader.inputDepartmentId }' />
		<input type='hidden' name='inputHeader.inputDepartment' value='${inputHeader.inputDepartment }' />
		<input type='hidden' name='inputHeader.inputerId' value='${inputHeader.inputerId }' />
		<input type='hidden' name='inputHeader.inputer' value='${inputHeader.inputer }' />
		<input type='hidden' name='inputHeader.inputTime' value='${inputTime }' />
		<input type='hidden' name='inputDetail.itemId' value='${inputDetail.itemId }' />
		<input type='hidden' name='inputDetail.itemName' value='${inputDetail.itemName }' />
		<input type='hidden' name='inputDetail.itemDimension' value='${inputDetail.itemDimension }' />
		<input type='hidden' name='inputDetail.itemSpecification' value='${inputDetail.itemSpecification }' />
		
		<input type="hidden" id="jsonData" name="jsonData" value='' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">对应工单编号：</td>
				<td class="text_left" colspan="5">
					<span>${inputHeader.formRefId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">商品编码：</td>
				<td class="text_left" style="width: 150px;">
					<span>${inputDetail.itemId }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">商品名称：</td>
				<td class="text_left" colspan="3">
					<span>${inputDetail.itemName }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">商品单位：</td>
				<td class="text_left">
					<span>${inputDetail.itemDimension }</span>
				</td>
				
				<td class="label_right">商品规格：</td>
				<td class="text_left" style="width: 150px;">
					<span>${inputDetail.itemSpecification }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">入库数量：</td>
				<td class="text_left">
	  				<input type="text" name="inputDetail.receiveCount" 
	  					value="${inputDetail.receiveCount }" style="width: 80px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">入库人员：</td>
				<td class="text_left">
					<span>${inputHeader.inputer }</span>
				</td>
				
				<td class="label_right">入库日期：</td>
				<td class="text_left" colspan="3">
					<span>${inputTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="inputHeader.formNote" 	value="" style="width: 380px;" />
				</td>
			</tr>
		</table>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="putinStorage()" value="确认生成入库单" style="padding-left: 8px;" />
			</td>
		</tr>
	</table>

</body>

</html>
