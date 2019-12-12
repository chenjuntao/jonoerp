<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>

    <link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">

    <link rel="stylesheet" href="<%=dojoBase %>/dojox/grid/enhanced/resources/claro/EnhancedGrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
    <script src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:true"></script>
    
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/backup/userExport.js?Version=<%=currenttime %>"></script>
</head>
<body class="claro">
	<form id="queryForm" method="post">
		<table class="hovertable" width="100%" border="2" style="text-align:center">
		  <input type="hidden" id="downloadTokenValue" name="downloadTokenValue"/>
		  
		<tr>
			<td class="label_right" style="width: 220px;">
				系统信息备份内容：
			</td>
			
			<td class="text_left" colspan="3">
				<div style="padding-left: 8px; font-weight: 900; font-size: 15px; color: blue;">
						<b>1. 系统角色表  2. 系统菜单表   3. 系统用户表  4.系统权限表 5.系统用户与角色关联表   6.系统用户与部门关联表 </b>
				</div>
			</td>
		</tr>
		
		<tr valign="middle">
			<td  colspan="3"><span style="width:30%">
				<input type="button" onclick="exportXls();" value="备份">
			</td>
		</tr>
		</table>
	</form>
	
    <div id="gridDiv"></div>
</body>

</html>