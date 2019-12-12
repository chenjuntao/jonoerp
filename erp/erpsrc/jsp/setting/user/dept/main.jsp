<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理某个部门下的用户（店长）</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/user/dept/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/user/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var g_branchId = "${loginBranchId}";
	</script>
</head>

<body class="claro">
	<table class="hovertable" width="100%">
		<tr>
			<td class="text_left">
		   	 	<input type="button" onclick="doAdd();" value="新增" style="margin-left: 8px;" />
		   	 	<input type="button" onclick="doDelete();" value="删除" style="margin-left: 8px;" />
	 		</td>
		</tr>
	</table>
		
	<div id="dataGrid" style="width: 100%;"></div>
</body>

</html>