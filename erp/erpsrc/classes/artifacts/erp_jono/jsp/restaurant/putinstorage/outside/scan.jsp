<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/outside/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
		<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>	
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/outside/export.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/outside/print.js?Version=<%=currenttime %>"></script>
    <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/print.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var status = '${status}';
		var inputDepartment= '${inputHeader.inputDepartment }';
		var inputer= '${inputHeader.inputer }';
		var inputTime= '${inputTime }';
		var provider='${inputHeader.provider }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
			<table style="position:relative;">
				<tr>
					<td>
						<label for="type">导出格式: </label>
					</td>
					
					<td>
						<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;"name="typeSelection" required="true">
							<option value="excel">excel</option>
							<option value="csv">csv</option>
						</select>
					</td>
				</tr>
				
				<tr><td colspan="2" style="text-align:right;">
				<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
				<button id="print" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customPreview'>打印预览</button>
<!-- 				<button id="print_design" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customPrintDesign'>打印设计</button> -->
				<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button></td>
			</tr>
			</table>
	</div>
	
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">前置单据：</td>
				<td class="text_left" style="width: 180px;">
					<span>${inputHeader.formRefId }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">入库单编号：</td>
				<td class="text_left">
					<span>${inputHeader.formId }</span>
					<span style="margin-left: 50px"><b style='border: 1px green solid;'>${status }</b></span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">入库部门：</td>
				<td class="text_left">
					<span>${inputHeader.inputDepartment }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">制单人员：</td>
				<td class="text_left">
					<span>${inputHeader.inputer }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${inputTime }</span>
				</td>
				
				<td class="label_right">供应商：</td>
				<td class="text_left">
				<span>[${inputHeader.providerId }]${inputHeader.provider }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="1">
					<span id="l_formNote">${inputHeader.formNote }</span>
				</td>
				
				<td class="label_right">打印次数：</td>
					<td class="text_left" colspan="1">
						<span id="l_times">${inputHeader.times }</span>
					</td>
			</tr>
			
			<tr>
				<td class="label_right">操作：</td>
				<td colspan="3">
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: customPrint'>直接打印</button>
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: showDialog'>导出/打印预览</button>
<!-- 				<button id="print_design" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customPrintDesign'>打印设计</button>  -->
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 50px;" data-dojo-props='onClick: doClose'>关闭页面</button>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="scanGrid1"></div>
		<div id="grid_print" class="print-only"></div>
		<input name="branchFlag" type="hidden" value="${branchFlag }" id="branchFlag"/>
		<input name="branchType" type="hidden" value="${branchType }" id="branchType"/>
	</form>	
</body>

</html>
