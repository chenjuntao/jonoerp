<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>brand config product</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/brand/setproduct/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/brand/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var g_brandId = '${brandId }';
	</script>
</head>

<body class="claro">
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
			   	 	<input type="button" onclick="selProduct();" value="新增" style="margin-left: 8px;" />
			   	 	<input type="button" onclick="delProduct();" value="删除" style="margin-left: 8px;" />
			   	 	<input type="button" onclick="doSave();" value="保存" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>
	<div id="dataGrid"></div>
</body>

</html>
