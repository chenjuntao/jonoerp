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
		src="<%=appRoot %>/jsp/centralfactory/requisition/manage/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/requisition/manage/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/requisition/manage/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
    <link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/requisition/manage/print.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/requisition/create/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var fId = '${requisitionHeader.formId }';
		var formRefId = '${requisitionHeader.formRefId }';
		var formMaker = '${requisitionHeader.formMaker }';
		var formTime = '${formTime }';
		var formNote = '${requisitionHeader.formNote }';
		var status = '${status }';
		var itemName = '${requisitionHeader.itemName}';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<!-- 导出 打印对话框 -->
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection" data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
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
		<input type='hidden' id="formId" name='requisitionHeader.formId' value='${requisitionHeader.formId }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requisitionHeader.formId }</span>
					<span style="margin-left: 30px"><b style='border: 1px green solid;'>${status }</b></span>
				</td>
				
				<td class="label_right" style="width: 120px;">工单编号：</td>
				<td class="text_left" >
					<span>${requisitionHeader.formRefId }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">商品名称：</td>
				<td class="text_left" style="width: 200px;">
	  				<span>${requisitionHeader.itemName }</span>
	  			</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${requisitionHeader.formMaker}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left" style="width: 150px;"><span>${formTime }</span></td>
				
				<td class="label_right" style="width: 80px;">备注：</td>
				<td class="text_left" style="width: 200px;">
	  				<span>${requisitionHeader.formNote }</span>
	  			</td>
			</tr>
			
			<tr>
				<td class="label_right">操作：</td>
				<td colspan="5">
					<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: showDialog'>导出明细</button>
					<button  data-dojo-type="dijit/form/Button"   style="margin-left: 30px;" data-dojo-props='onClick: prn1_preview'>打印预览</button>
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 30px;" data-dojo-props='onClick: doClose'>关闭页面</button>
<!-- 				<input type="button" value="打印设计" onclick="myDesign();" /> -->
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="scanGrid"></div>
		
		<div id="grid_print" class="print-only"></div>
	</form>
</body>

</html>
