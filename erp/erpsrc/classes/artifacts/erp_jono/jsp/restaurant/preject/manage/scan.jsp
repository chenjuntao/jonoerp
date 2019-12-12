<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script language="javascript" type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/manage/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/manage/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/manage/exportDetail.js"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/dreject/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/preject/manage/print.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var rformId = '${returnHeader.formId }';
		var iformId = '${inputHeader.formId }';
		var inputDepartment = '${inputHeader.inputDepartment }';
		var storage = '${inputHeader.storage }';
		var provider = '${inputHeader.provider }';
		var inputer = '${inputHeader.inputer }';
		var inputTime = '${inputTime }';
		var returner = '${returnHeader.returner }';
		var returnTime = '${returnTime }';
		var formNote = '${returnHeader.formNote }';
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
					<select id="typeSelection" data-dojo-id="typeSelection"  data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:right;">
					<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
					<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button>
				</td>
			</tr>
		</table>
	</div>
	
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">退货单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${returnHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">入库单编号：</td>
				<td class="text_left" colspan="3">
					<span>${inputHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">入库部门：</td>
					<td class="text_left">
					<span>${inputHeader.inputDepartment }</span>
				</td>
			
				<td class="label_right" >仓库：</td>
					<td class="text_left" colspan="3">
					<span>${inputHeader.storage }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">供应商：</td>
				<td class="text_left" >
					<span>${inputHeader.provider }</span>
				</td>
				
				<td class="label_right">入库人员：</td>
				<td class="text_left" style="width: 150px;">
					<span>${inputHeader.inputer }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">入库日期：</td>
				<td class="text_left">
					<span>${inputTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货人员：</td>
				<td class="text_left">					
					<span>${returnHeader.returner }</span>
				</td>
				
				<td class="label_right">退货制单日期：</td>
				<td class="text_left" colspan="3">
					<span>${returnTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货审核人员：</td>
				<td class="text_left">					
<%-- 					<span>${antiauditHeader.antiauditor }</span> --%>
				</td>
				
				<td class="label_right">退货审核日期：</td>
				<td class="text_left" colspan="3">
<%-- 					<span>${antiauditTime }</span> --%>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货单备注：</td>
				<td class="text_left" colspan="6" >
					<span id="l_formNote">${returnHeader.formNote }</span>
			<!--	<td width=400px><input type="button" value="打印预览" onclick="prn1_preview();" /></td>
 				<td width=400px><input type="button" value="打印设计" onclick="myDesign();" /></td> -->
				</td>
			</tr>
			
			<tr>
				<td class="label_right">操作：</td>
				<td colspan="7">
					<button data-dojo-type="dijit/form/Button" data-dojo-props='onClick: showDialog'>导出明细</button>
					<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: prn1_preview'>打印预览</button>
					<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doClose'>关闭页面</button>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top: 212px;"></div>
		<input name="branchFlag" type="hidden" value="${branchFlag }" id="branchFlag"/>
		<div id="grid_print" class="print-only"></div>
	</form>	
</body>

</html>
