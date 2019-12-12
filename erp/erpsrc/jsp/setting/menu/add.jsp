<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>add menu</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript' src="<%=appRoot %>/jsp/setting/menu/add.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript'>
	</script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow: hidden; margin: 0px;">
	<form id="dataForm" method="post">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">菜单编码：</td>
				<td class="text_left">
		 			<input type="hidden" name="menu.parentId" value="${parentId}" />
		 			<input type="text" id="menuId" name="menu.id"  value="${menu.id}" style="width: 80px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">菜单名称：</td>
				<td class="text_left">
		 			<input type="text" id="menuName" name="menu.name"
		 				 value="${menu.name}" style="width: 180px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">图片名称：</td>
				<td class="text_left">
		 			<input type="text" id="imageName" name="menu.imageName"	 value="${menu.imageName}" style="width: 280px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">菜单地址：</td>
				<td class="text_left">
		 			<input type="text" id="url" name="menu.url"	 value="${menu.url}" style="width: 480px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">排序：</td>
				<td class="text_left">
		 			<input type="text" id="priority" name="menu.priority" value="1" style="width: 50px;" />
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input id="saveBtn" type="button" onclick="doSave();" value="保存" />
			</td>
		</tr>
	</table>
</body>
</html>
