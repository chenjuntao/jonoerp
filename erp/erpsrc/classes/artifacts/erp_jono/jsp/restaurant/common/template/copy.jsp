<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/template/copy.js?Version=<%=currenttime %>"></script>
	
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var templateId = '${templateId }';
		var templateType = '${templateType }';
	</script>
</head>

<body class="claro" style="margin: 1px;">
	<form id="templateForm" method="post" >
		<table class="hovertable" width="100%" border="1">
<!-- 			<tr> -->
<!-- 				<td class="label_right" style="width: 120px;">门店：</td> -->
<!-- 				<td class="text_left"> -->
<%-- 					<s:select list="shopLst" listKey="code" listValue="name" theme="simple" --%>
<%-- 						 style="width: 120px;" id="branchId" value="%{templateMeta.branchId }"></s:select> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>
				<td class="label_right" style="width: 100px;">门店分组：</td>
				<td class="text_left" style="width: 180px;">
					<s:select list="regionLst" listKey="id" listValue="name" theme="simple" style="width: 150px;" id="regionId"></s:select> 
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">模板名称：</td>
				<td class="text_left">
		 			<input type="text" id="templateName" name="templateMeta.templateName"
		 				 value="${templateMeta.templateName }" style="width: 180px;" />
					<input type='hidden' id="categoryId" value='${templateMeta.categoryId }' />
				</td>
			</tr>
			<tr>
				<td class="text_left" colspan="2" style="text-align: center;">
				    <input type="button" id="btn_ok" value="确定" onclick="doSave();" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
