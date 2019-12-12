<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>连锁餐饮ERP系统</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript' src="<%=appRoot %>/jsp/centralfactory/workShop/main.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript'>
	</script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/cbtree/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/category/raw/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow: hidden; margin: 0px;">
	<div id="treeWrapper">
	</div>
	
	<div id="editArea">
		<form id="dataForm" method="post">
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td class="label_right" style="width: 100px;" >车间编码：</td>
					<td class="text_left" colspan="3">
			 			<input type="hidden" id="workOrderId" name="factoryWorkShop.workOrderId"
			 				 value="" style="width: 80px;" />
			 				 <input type="hidden" id="priority" name="factoryWorkShop.priority"
			 				 value="" style="width: 80px;" />
			 				 <input type="hidden" id="factoryId" name="factoryWorkShop.factoryId"
			 				 value="" style="width: 80px;" />
			 			<span id="idText"></span>
					</td>
				</tr>
				<tr>
					<td class="label_right">车间名称：</td>
					<td class="text_left">
			 			<input type="text" id="workshop" name="factoryWorkShop.workshop"
			 				 value="" style="width: 180px;" />
					</td>
					<td class="label_right" style="width: 15%;">是否为默认车间：</td>
						<td class="text_left"  style="width: 20%;">
						<input type="hidden" id="priority" name="priority" />
				 			<span id="typeText" style ="font-size:22px"></span>
						</td>
				</tr>
			</table>
		</form>
			
		<p class="area_blank">&nbsp;</p>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td style="text-align: center;">
				    <input id="saveBtn" type="button" onclick="doSave();" value="保存" />
				    <input id="addBtn" type="button" onclick="doAdd();" value="新增车间" />
				    <input id="delBtn" type="button" onclick="doDelete();" value="删除" />
				    <input id="setBtn" type="button" onclick="setMain();" value="设置为默认车间" style="margin-left: 10px "/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
