<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/shopdish/main.js?Version=<%=currenttime %>"></script>
	
	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
	<!-- required: the default dijit theme: -->
	<link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	
	<!-- ensure that dgrid.css is loaded before your rules 
	so that your rules can override those in dgrid.css without requiring higher specificity -->
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/shopdish/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 100px;">出品名称或编码：</td>
			<td class="text_left">
	 			<input type="text" id="itemName" onkeydown="if(event.keyCode==13)doQuery();" />
	 			
				<span style="display: inline-block;width: 20px;"></span>
				<input id="queryBtn" type="button" onclick="doQuery()" value="查询原料" />
	 		</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="categoryTree">
	</div>
	
	<div id="dataGrid">
	</div>
</body>

</html>
