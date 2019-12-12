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
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/audit/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/audit/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		
		<input type='hidden' id="formId" name='workOrderHeader.formId' value='${workOrderHeader.formId }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>中央工厂生产工单</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" style="width: 250px;" >
					<span>${workOrderHeader.formId }</span>
					<span style="margin-left: 30px"><b style='border: 1px green solid;'>${status }</b></span>
				</td>
				
				<td class="label_right">制单人员：</td>
				<td class="text_left"><span>${loginUsername}</span></td>

				<td class="label_right">制单日期：</td>
				<td class="text_left"><span>${formTime }</span></td>
				
				<td class="label_right">备注：</td>
				<td class="text_left" >
	  				<span>${workOrderHeader.formNote }</span>
				</td>
			</tr>

			<tr>
				<td class="label_right" style="width: 80px;">商品：</td>
				<td class="text_left" style="width: 180px;"><span>[${workOrderHeader.itemId }]${workOrderHeader.itemName }</span>
				</td>

				<td class="label_right" style="width: 80px;">商品单位：</td>
				<td class="text_left" style="width: 180px;"><span>${workOrderHeader.itemDimension2 }</span>
				</td>

				<td class="label_right" style="width: 80px;">商品规格：</td>
				<td class="text_left" style="width: 180px;"><span>${workOrderHeader.itemSpecification }</span>
				</td>

				<td class="label_right" style="width: 80px;">生产数量：</td>
				<td class="text_left" style="width: 180px;"><span>${workOrderHeader.itemCount2 }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		<div id="auditDataGrid"></div>
</body>

</html>
