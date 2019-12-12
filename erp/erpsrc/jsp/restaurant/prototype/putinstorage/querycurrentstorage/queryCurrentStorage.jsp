<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
<%-- 	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/shopsource/main.js?Version=<%=currenttime %>"></script> --%>
	
	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
	<!-- required: the default dijit theme: -->
	<link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	
	<!-- ensure that dgrid.css is loaded before your rules 
	so that your rules can override those in dgrid.css without requiring higher specificity -->
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css"/>
<%-- 	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/shopsource/main.css"> --%>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
		<table class="hovertable" border="1" 
			bordercolor="#FEB5AE" bgcolor="#E9EFF5">
			<tr>
				<td width="140"class="label_right">门店选择：</td>
				<td width="275" class="text_left"><label>
				  <select name="shopSelect" id="shopSelect">
			      </select>
				</label></td>
				<td class="text_left"><label>
				  <input type="submit" name="Submit3" value="查询">
				</label></td>
			</tr>
		</table>
	<p>注：仿照餐厅原材料信息查询界面，左方categoryChooseTree显示所有的原料（按类别），可以全选、多选、按类别选择；右方storageDataGrid显示当前库存，显示字段包括原材料查询中的所有字段再加上库存量、批次</p>
	<p class="area_blank">&nbsp;</p>
	<div id="categoryChooseTree">
	</div>
	
	<div id="storageDataGrid">
	</div>
</body>

</html>
