<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>delivery type config</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/group/dtype/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/group/dtype/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var branchType = '${branchType }';
	</script>
</head>

<body class="claro">
	<table class="hovertable" width="100%">
		<tr>
			<td class="label_right" style="width: 100px;">原料名称或编码：</td>
			<td class="text_left">
	 			<input type="text" id="itemName" style="width: 120px;" 	onkeydown="if(event.keyCode==13){doQuery();return false;}" />
				<span style="display: inline-block;width: 20px;"></span>
				
				<input id="queryBtn" type="button" onclick="doQuery()" value="查询原料" />
				<span style="display: inline-block;width: 5px;"></span>
				
				<input id="saveBtn" type="button" onclick="doSave()" value="保存" />
	 		</td>
		</tr>
	</table>
		
	<div id="groupTree"></div>
	<div id="categoryTree"></div>
	<div id="dataGrid"></div>
</body>

</html>
