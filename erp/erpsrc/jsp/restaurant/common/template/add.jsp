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
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/template/add.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/common/template/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var templateType = '${templateType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="templateForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>新增要货单模板</b>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">
			   	 	<input type="button" onclick="selMaterial();" value="新增原料" style="margin-left: 8px;" />
			    	<input type="button" onclick="doSave();" value="保存模板" style="margin-left: 8px;" />
			    	<input type="button" onclick="doClose();" value="取消" style="margin-left: 8px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">门店：</td>
				<td class="text_left" colspan="3">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
						 style="width: 120px;" id="branchId"></s:select>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">模板名称：</td>
				<td class="text_left" style="width: 180px;">
		 			<input type="text" id="templateName" name="templateMeta.templateName"
		 				 value="${template.templateName }" style="width: 180px;" />
				</td>
				<td class="label_right" style="width: 120px;">模板要货类别：</td>
				<td class="text_left">
					<span id="categoryId">${templateMeta.categoryId }</span>
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid">
		</div>
	</form>
</body>

</html>
