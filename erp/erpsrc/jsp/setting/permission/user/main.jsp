<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>连锁餐饮ERP系统</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript' src="<%=appRoot %>/jsp/setting/permission/user/main.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript'>
	</script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/cbtree/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/permission/user/main.css"/>
</head>

<body class="claro" style="overflow: hidden; margin: 0px;">
	<div id="userTree">
	</div>
	
	<iframe id="ifrMenu" frameborder="0">
	</iframe>
</body>
</html>
