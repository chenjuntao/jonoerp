<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>连锁餐饮ERP系统</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript' src="<%=appRoot %>/jsp/hq/config/storage/main.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript'>
	</script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/cbtree/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/storage/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow: hidden; margin: 0px;">
	<div id="treeWrapper">
	</div>
	
	<div id="editArea">
		<form id="dataForm" method="post">
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td class="label_right" style="width: 10%;">仓库编码：</td>
					<td class="text_left" style="width: 15%;">
			 			<input type="hidden" id="storageId" name="storageId" value=""  />
			 			<input type="hidden" id="branchId" name="branchId" value=""  />
			 			<span id="idText"></span>
					</td>
					
					<td class="label_right" style="width: 10%;">仓库名称：</td>
					<td class="text_left"  style="width: 25%;">
			 			<input type="text" id="storageName" name="storageName"
			 				 value="" style="width: 150px;" />
					</td>
					
					<td class="label_right" style="width: 10%;">仓库类型：</td>
					<td class="text_left"  style="width: 30%;">
					<input type="hidden" id="priority" name="priority" />
			 			<span id="typeText"></span>
					</td>
				</tr>
			</table>
		</form>
			
		<p class="area_blank">&nbsp;</p>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td style="text-align: center;">
				    <input id="addBtn" type="button" onclick="doAdd();" value="新增仓库" />
				    <input id="deleteBtn" type="button"  onclick="deleteStorage();" value="删除仓库" style="margin-left: 10px "/>
				    <input id="saveBtn" type="button" onclick="doSave();" value="保存" style="margin-left: 10px "/>
				    <input id="setBtn" type="button" onclick="setMain();" value="设置为主仓" style="margin-left: 10px "/>
				    
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
