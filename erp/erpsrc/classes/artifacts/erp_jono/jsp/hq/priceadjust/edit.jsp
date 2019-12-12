<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit price adjust bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/priceadjust/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var g_adjustType = '${adjustHeader.adjustType }';
		var tag = 1;
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='adjustHeader.formId' value='${formId }' />
		<input type='hidden' name='adjustHeader.adjustScope' value="all" />
		
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;" colspan="3">
					<span>${adjustHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 90px;">调价范围：</td>
				<td class="text_left" style="width: 150px;">
					<span>${adjustHeader.adjustTypeName}</span>
				</td>
				
				<td class="label_right" style="width: 90px;">生效时间：</td>
				<td class="text_left">
					<input type="text" class="Wdate" name="adjustHeader.effectTime" value="${effectTime}" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="adjustHeader.formNote" value="${adjustHeader.formNote}" style="width: 380px;" />
	  				
			   	 	<input type="button" onclick="addItem();" value="新增商品" style="margin-left: 58px;" />
			   	 	<input type="button" onclick="delItem();" value="删除商品" style="margin-left: 8px;" />
			   	 	<input type="button" onclick="doInvalid();" value="作废单据" style="margin-left: 8px;" />
			   	 	<input type="button" onclick="delForm();" value="删除单据" style="margin-left: 8px;" />
				    <input type="button" onclick="doSave();" value="保存" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	  
		<div id="dataGrid" style="top: 122px;"></div>
	</form>
</body>

</html>
