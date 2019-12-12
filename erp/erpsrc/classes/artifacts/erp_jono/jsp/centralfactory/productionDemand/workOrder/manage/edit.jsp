<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" 
		src="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/manage/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/manage/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='workOrderHeader.formId' value='${workOrderHeader.formId }' />
		<input type='hidden' name='currentStep.formId' value='${currentStep.formId }' />
		<input type='hidden' name='currentStep.productionStep' value='${currentStep.productionStep }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 110px;">单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${workOrderHeader.formId }</span>
				</td>
				<td class="label_right" style="width: 80px;">商品编码：</td>
				<td class="text_left" style="width: 180px;">
					<span>${workOrderHeader.itemId }</span>
				</td>
				<td class="label_right" style="width: 80px;">商品名称：</td>
				<td class="text_left">
					<span>${workOrderHeader.itemName }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">商品单位：</td>
				<td class="text_left"><span>${workOrderHeader.itemDimension }</span>
				</td>
				<td class="label_right">商品规格：</td>
				<td class="text_left"><span>${workOrderHeader.itemSpecification }</span>
				</td>
				<td class="label_right">生产数量：</td>
				<td class="text_left">
					<span>${workOrderHeader.itemCount }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left"><span>${loginUsername}</span></td>

				<td class="label_right">制单日期：</td>
				<td class="text_left"><span>${formTime }</span></td>
				
				<td class="label_right">备注：</td>
				<td class="text_left">
	  				<span>${workOrderHeader.formNote }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">审核人：</td>
				<td class="text_left">
					<span>${workOrderHeader.auditor }</span>
				</td>
				<td class="label_right">审核日期：</td>
				<td class="text_left" colspan="3">
					<span>${auditTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">当前制程：</td>
				<td class="text_left">
					<span>${currentStep.productionName }</span>
				</td>
				<td class="label_right">制程日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="productionTime"
						name="currentStep.productionTime" value="" />
				</td>
				<td class="label_right">制程产量：</td>
				<td class="text_left">
					<input type="text" id="productionCount" name="currentStep.productionCount" 
						value="" style="width: 60px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">生产人员：</td>
				<td class="text_left">
					<input type="text" name="currentStep.productionMan" 
						value="" />
				</td>
				<td class="label_right">制程备注：</td>
				<td class="text_left" colspan="3">
					<input type="text" name="currentStep.productionNote" 
						value="" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		<div id="auditDataGrid" style="height: 120px;">
		</div>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doSubmit();" value="完成当前制程" style="margin-left: 8px;" />
			    <input type="button" onclick="doClose();" value="取消" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
