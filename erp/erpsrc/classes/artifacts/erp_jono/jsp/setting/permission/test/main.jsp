<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Menu Transfer</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type='text/javascript' src="<%=appRoot %>/jsp/setting/permission/test/main.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript'>
		var _url = appRoot + "/trans/export.action";
	</script>
</head>

<body class="claro" style="overflow: hidden; margin: 0px;">
	<div id="userTree">
	<input type="button" value="transfer" onclick="transfer();" style="margin: 200px;height: 100px;"/>
	</div>
	
	<form id="dataForm" method="post" target="_blank">
		<input id="jsonData" name="jsonData" type="hidden" value="" />
	</form>
</body>
</html>
