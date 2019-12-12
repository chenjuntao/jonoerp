<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit user</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/user/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var oldUserId = '${userId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post">
		<input type='hidden' name='user.branchId' value='${branchId}' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">用户名：</td>
				<td class="text_left" style="width: 120px;">
					<span>${user.userId}</span>
		 			<input type="hidden" id="userId" name="user.userId"	 value="${user.userId}" />
				</td>
				
				<td class="label_right" style="width: 80px;">姓名：</td>
				<td class="text_left">
		 			<input type="text" id="userName" name="user.userName" value="${user.userName}" style="width: 180px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">性别：</td>
				<td class="text_left">
		 			<input type="text" id="gender" name="user.gender" value="${user.gender}" style="width: 80px;" />
				</td>
				
				<td class="label_right">电子邮件：</td>
				<td class="text_left">
		 			<input type="text" id="email" name="user.email"  value="${user.email}" style="width: 180px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">电话：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" id="telephone" name="user.telephone" value="${user.telephone}" style="width: 80px;" />
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
