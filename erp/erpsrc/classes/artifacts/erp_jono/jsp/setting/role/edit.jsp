<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit role</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/role/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var oldRoleId = '${roleId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post">
		<input type='hidden' name='role.branchId' value='${branchId}' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">编号：</td>
				<td class="text_left" style="width: 120px;">
					<span>${role.roleId}</span>
		 			<input type="hidden" id="roleId" name="role.roleId"
		 				 value="${role.roleId}" />
				</td>
				<td class="label_right" style="width: 80px;">名称：</td>
				<td class="text_left">
		 			<input type="text" id="roleName" name="role.roleName"
		 				 value="${role.roleName}" style="width: 180px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">部门类型：</td>
				<td class="text_left" colspan="3">
					<span>${role.branchTypeName}</span>
<%-- 					<s:select list="branchTypeLst" listKey="typeId" listValue="typeName" theme="simple" --%>
<%-- 						 style="width: 180px;" id="branchType" name="role.branchType"></s:select> --%>
				</td>
			</tr>
			<tr>
				<td class="label_right">描述：</td>
				<td class="text_left" colspan="3">
		 			<input type="text" id="description" name="role.description"
		 				 value="${role.description}" style="width: 280px;" />
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doSave();" value="保存" />
			</td>
		</tr>
	</table>
</body>

</html>
