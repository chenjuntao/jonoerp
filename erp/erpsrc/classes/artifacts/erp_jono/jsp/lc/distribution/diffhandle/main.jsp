<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>handle distribution difference</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/distribution/diffhandle/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/distribution/diffhandle/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="text_left">
			   	 	<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 32px;"></div>
	</form>
</body>

</html>
