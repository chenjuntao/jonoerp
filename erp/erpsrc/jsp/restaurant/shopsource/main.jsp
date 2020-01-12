<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>restaurant query raw material</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	 <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript">
		var branchType = '${branchType}';
	</script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/shopsource/main.js?Version=<%=currenttime %>"></script>
	
	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
	<link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/shopsource/main.css">
</head>

<body class="claro">
	<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	<table class="hovertable" width="100%" border="1">
	
		<tr>
			<td class="label_right" style="width: 100px;">物料名称或编码：</td>
			<td class="text_left">
	 			<input type="text" id="itemName" onkeydown="if(event.keyCode==13){doQuery();return false;}" />
	 			
				<span style="display: inline-block;width: 20px;"></span>
				<button  data-dojo-type="dijit/form/Button"  id="queryBtn" data-dojo-props='onClick: doQuery'>查询物料</button>
	 		</td>
		</tr>
	</table>
	
	<p class="area_blank">&nbsp;</p>
	<div id="categoryTree"></div>
	<div id="dataGrid"></div>
</body>

</html>
