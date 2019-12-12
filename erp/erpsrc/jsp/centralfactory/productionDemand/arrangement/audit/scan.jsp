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
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/audit/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/audit/export.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/audit/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>	
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/audit/audit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
		<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/audit/print.css">
	
	<script type="text/javascript">
		var formId = '${formId }';
		var loginUsername = '${loginUsername }';
		var formTime = '${formTime }';
		var auditor = '${arrangmentHeader.auditor }';
		var auditTime = '${auditTime }';
		var formNote = '${arrangmentHeader.formNote }';
		var formRefId = '${arrangmentHeader.formRefId  }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<!-- 导出 打印对话框 -->
<div id="customDialog"  data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
		<table style="position:relative;">
			<tr><td><label for="type">导出格式: </label></td>
				<td><select id="typeSelection" data-dojo-id="typeSelection" 
						data-dojo-type="dijit/form/Select" style="width: 162px;"
						name="typeSelection" required="true">
					<option value="excel">excel</option>
					<option value="csv">csv</option>
				</select></td>
			</tr>
			<tr><td colSpan=2><label for="columns">选择列项: </label></td></tr>
			<tr><td colSpan=2><table id="columnSelection"></table></td></tr>
			<tr><td colspan="2" style="text-align:right;">
				<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
				<button id="print" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: print'>打印</button>
				<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hide' >取消</button></td>
			</tr>
				</table>
</div>

	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='arrangmentHeader.formId' value='${arrangmentHeader.formId }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>中央工厂生产计划单查看</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;" colspan="7">
					<span>${arrangmentHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${arrangmentHeader.formMaker}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">审核人：</td>
				<td class="text_left" style="width: 180px;">
					<span>${arrangmentHeader.auditor }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">审核日期：</td>
				<td class="text_left">
					<span>${auditTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="2">
	  				<span>${arrangmentHeader.formNote }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">关联单号：</td>
				<td class="text_left" colspan="2">
					<span>${arrangmentHeader.formRefId }</span>
						<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: customPrint'>分周期打印</button>
						<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: directPrint'>直接打印</button>
<!-- 				<input type="button" value="打印设计" onclick="myDesign();" /> -->
				</td>
				<td  colspan="2"><button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: show'>导出/打印</button></td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		<div id="scanDataGrid"></div>
		<!-- 		打印 -->
		<div id="grid_print" class="print-only">
		</div>
	</form>
</body>

</html>
