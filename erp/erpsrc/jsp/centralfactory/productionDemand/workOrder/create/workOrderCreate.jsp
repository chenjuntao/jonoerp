<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/create/workOrderCreate.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		
		<input type="hidden" id="workOrderHeader.formId" name="workOrderHeader.formId" value='${arrangmentDetail.workOrderId }' />
		<input type="hidden" id="workOrderHeader.itemId" name="workOrderHeader.itemId" value='${arrangmentDetail.itemId }' />
		<input type="hidden" id="workOrderHeader.itemName" name="workOrderHeader.itemName" value='${arrangmentDetail.itemName }' />
		<input type="hidden" id="workOrderHeader.itemDimension" name="workOrderHeader.itemDimension" value='${arrangmentDetail.itemDimension }' />
		<input type="hidden" id="workOrderHeader.itemSpecification" name="workOrderHeader.itemSpecification" value='${arrangmentDetail.itemSpecification }' />
		<input type="hidden" id="productionCycle" name="workOrderHeader.productionCycle" value='${arrangmentDetail.productionCycle }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="11">
					<div style="padding-left: 8px;">
						<b>中央工厂生产工单生成</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;" colspan="11">
					<span>${formId}</span>
				</td>
				
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">商品编码：</td>
				<td class="text_left" >
					<span>${arrangmentDetail.itemId }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">商品名称：</td>
				<td class="text_left" style="width: 180px;">
					<span>${arrangmentDetail.itemName }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">商品单位：</td>
				<td class="text_left" style="width: 80px;">
					<span>${arrangmentDetail.itemDimension }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">商品规格：</td>
				<td class="text_left" style="width: 180px;">
					<span>${arrangmentDetail.itemSpecification }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">生产车间：</td>
				<td class="text_left" style="width: 180px;">
					<span>${workOrderHeader.workshop }</span>
				</td>
			</tr>
				
			<tr>
				<td class="label_right" style="width: 120px;">生产周期：</td>
				<td class="text_left" style="width: 180px;">
					<span>${arrangmentDetail.productionCycle }</span>
				</td>
			
				<td class="label_right" >生产数量：</td>
				<td class="text_left" >
				  <span>${arrangmentDetail.produceCount  }</span>
					<input type="hidden" id="itemCountValue" name="workOrderHeader.itemCount" value='${arrangmentDetail.produceCount }'/>
				</td>
				
				<input type='hidden' name='arrangementFormId'  value='${arrangementFormId }' />
					
				<td class="label_right" style="width: 120px;">备注：</td>
				<td class="text_left" style="width: 180px;" colspan="9">
				</td>
			</tr>
		<tr>
			<td class="label_right">操作：</td>
				<td colspan="9">
			    <input type="button" onclick="doCommit();" value="中央工厂生产工单生成" style="margin-right: 100px;" />
			    <input type="button" onclick="doClose();" value="取消" style="margin-right: 100px;" />
			</td>
		</tr>
	</table>
	  
		<p class="area_blank">&nbsp;</p>
	 	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<div id="detailDataGrid">
		</div>
		
	</form>
		
	<p class="area_blank">&nbsp;</p>
	
		
</body>

</html>
