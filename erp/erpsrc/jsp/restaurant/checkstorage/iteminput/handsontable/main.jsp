<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Logistics Center estimate</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type="text/javascript" src="<%=dojoBase %>/handsontable/jquery.min.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/jquery/isloading/jquery.isloading.min.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/handsontable/handsontable.full.min.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/jquery/common.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/iteminput/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/iteminput/handsontable/main.js?Version=<%=currenttime %>"></script>
	
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
    
	<link rel="stylesheet" href="<%=dojoBase %>/handsontable/handsontable.full.min.css">
	<link rel="stylesheet" href="<%=dojoBase %>/jquery/isloading/isloading.css">
	<link rel="stylesheet" href="<%=dojoBase %>/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/checkstorage/iteminput/handsontable/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="6">
					<div style="padding-left: 8px;">
						<b>盘点清单选择</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">餐厅：</td>
				<td class="text_left" style="width: 120px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 style="width: 150px;" id="branchId" name="checkHeader.checkBranchId"></s:select>
					<input type="hidden" id="checkBranch" name="checkHeader.checkBranch" value='' />
				</td>
				
				<td class="label_right" style="width: 90px;">盘点批次：</td>
				<td class="text_left" style="width: 120px;">
				
				<s:select list="batchLst" theme="simple" onchange="loadCheckList(checkBatchId,checkId);"  style="width: 150px;" id="checkBatchId" name="checkHeader.checkBatchId"></s:select>
				</td>
				
				<td class="label_right" style="width: 90px;">盘点清单：</td>
				<td class="text_left">
				<s:select list="formLst" theme="simple" listKey="formId" listValue="formId" onchange="loadGridData();" style="width: 150px;" id="checkId" name="checkHeader.formId"></s:select>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>盘点清单输入</b>
					</div>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid">
		</div>
		
		<div id="grid_print" class="print-only">
		</div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  	
	  	<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="text_left" colspan="4" style="text-align: center;">
				    <input type="button" id="btn_submit" value="餐厅盘点清单输入确认" style="margin-left: 8px;" />
			    	<input type="button" value="空白盘点清单打印" style="margin-left: 8px;" onClick="prn1_preview();"/>
	<!-- 		    	<input type="button" value="空白盘点清单设计" style="margin-left: 8px;" onClick="myDesign();"/> -->
				</td>
			</tr>
		</table>
	</form>

</body>

</html>
