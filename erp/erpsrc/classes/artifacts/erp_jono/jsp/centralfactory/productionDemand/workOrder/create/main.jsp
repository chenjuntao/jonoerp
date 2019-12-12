<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
		<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/create/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/create/order.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/create/workOrderGrid.js"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/workOrder/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">审核日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" value="" style="width: 120px;"	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
					<span style="padding: 0 5px;">至</span> 
					<input	type="text" class="Wdate" id="endDate" value=""	style="width: 120px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
					
					<input type="button" onclick="doQuery();" value="查询" style="margin-left: 50px;">
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>已审核的生产计划单列表</strong></div>
				</td>
			</tr>
  		</table>
		<div id="orderGrid" class="dataGrid">
		</div>
		
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="transform();" value="转化为生产工单" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
	
	<input type='hidden' id="arrangementFormId" name='arrangementFormId'  value='' />
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>生产工单列表</strong></div>
				</td>
			</tr>
  		</table>
  			<input type="hidden" id="jsonData" name="jsonData" value='' />
  			
		<div id="workOrderGrid" class="dataGrid" style="height: 190px">
		</div>

	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="batchCreate();" value="批量生成" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
	</form>
</body>

</html>
