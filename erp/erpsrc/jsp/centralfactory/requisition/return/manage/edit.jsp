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
		src="<%=appRoot %>/jsp/centralfactory/requisition/return/manage/edit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	
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
		var preVersion = '${preVersion }';
		var fId = '${requisitionHeader.formId }';
		var formRefId = '${requisitionHeader.formRefId }';
		var formMaker = '${requisitionHeader.formMaker }';
		var formTime = '${formTime }';
		var formNote = '${requisitionHeader.formNote }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
		 <input id="currentVersion" name="currentVersion" type="hidden" />
		<input type="hidden" name="requisitionHeader.formRefId" value='${formId}'/>
	<input type="hidden" name="requisitionHeader.workshop" value=""/>
	<input type="hidden" name="requisitionHeader.formId" value="${requisitionHeader.formId}"/>
	<input type="hidden" name="requisitionHeader.storageId" value="${requisitionHeader.storageId}"/>
	<input type="hidden" name="requisitionHeader.storage" value=" ${requisitionHeader.storage}"/>
	<input type="hidden" name="requisitionHeader.formMaker" value=" ${requisitionHeader.formMaker}"/> 
	<input type="hidden" name="requisitionHeader.formMakerId" value="${requisitionHeader.formMakerId}"/>  
	<input type="hidden" name="formTime" value="${formTime}"/>
		 <input id="preVersion" type="hidden" />
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" style="width: 180px;"colspan="1">
					<span>${requisitionHeader.formId }</span>
				</td>
				<td class="label_right" style="width: 120px;">关联单据编号：</td>
				<td class="text_left" colspan="1">
					<span>${requisitionHeader.formRefId }</span>
				</td>
				<td class="label_right" style="width: 80px;">商品名称：</td>
				<td class="text_left" style="width: 200px;">
	  				<span>${requisitionHeader.itemName }</span>
	  			</td>
			</tr>
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left"><span>${requisitionHeader.formMaker}</span></td>

				<td class="label_right">制单日期：</td>
				<td class="text_left" style="width: 150px;"><span>${formTime }</span></td>
				
				<td class="label_right" style="width: 80px;">备注：</td>
				<td class="text_left" style="width: 200px;"  colspan="2">
	  				<span>${requisitionHeader.formNote }</span>
	  			</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		<div id="detailDataGrid">
		</div>
		<div id="grid_print" class="print-only">
		</div>
 	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1" style=" position: absolute;  bottom: 0px; width: 100%;">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doDelete();" value="删除单据" style="margin-left: 8px;" />
			    <input type="button" onclick="doInvalid();" value="作废单据" style="margin-left: 8px;" />
			    <input type="button" onclick="doSave();" value="确认修改" style="margin-left: 8px;" />
			    <input type="button" onclick="doClose();" value="取消修改" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
