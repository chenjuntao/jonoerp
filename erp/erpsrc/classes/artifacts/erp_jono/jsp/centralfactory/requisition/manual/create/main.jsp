<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>manual requisition</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/requisition/manual/create/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/requisition/manual/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 90px;">领料仓库：</td>
				<td class="text_left" style="width: 270px;">
					<s:select list="storeLst" listKey="storageId" listValue="storageName" theme="simple" style="width: 190px;" id="storageId" name="requisitionHeader.storageId">
					</s:select>
				</td>
				<td class="label_right" style="width: 90px;">领料单位：</td>
				<td class="text_left">
				<s:select list="workshopLst" listKey="workshop" listValue="workshop"  value="bean.workshop" theme="simple"	 style="width: 150px;" id="workshop" name="requisitionHeader.workshop"></s:select>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" style="width: 270px;">
	  				<input type="text" name="requisitionHeader.formNote" value="" style="width: 260px;" />
				</td>
				<td class="text_left" colspan="3">
			    	<input type="button" onclick="selMaterial();" value="自选原料" style="margin-left: 58px;" />
				    <input type="button" onclick="doSubmit()" value="生成领料单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"></div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='' />
	  	<input type="hidden" name="requisitionHeader.formType" value='manual' />
	</form>
</body>

</html>