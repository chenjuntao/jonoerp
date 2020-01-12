<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>modify password</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/password/main.js?Version=<%=currenttime %>"></script>
	
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<form id="dataForm" method="post">
		<input type='hidden' id='userId' value='${loginUserId}' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">现在的密码：</td>
				<td class="text_left">
		 			<input type="password" id="oldPwd" value="" style="width: 180px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">设置新的密码：</td>
				<td class="text_left">
		 			<input type="password" id="password" value="" style="width: 180px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">重复新的密码：</td>
				<td class="text_left">
		 			<input type="password" id="confirmPwd" value="" style="width: 180px;" />
				</td>
			</tr>
			
			<tr>
				<td style="text-align: center;" colspan="2">
				    <input type="button" onclick="doModify();" value="确定" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
