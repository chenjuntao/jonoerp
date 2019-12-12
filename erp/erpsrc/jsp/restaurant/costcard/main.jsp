<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan therapy</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/costcard/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/costcard/tree.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/costcard/exportDetail.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
	
	<link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css"	id="themeStyles" />
	
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/costcard/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var g_foodId = "${foodId }";
	    var itemName = "${food.itemName }";
	    var itemId = "${food.itemId }";
	</script>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
		<table style="position:relative;">
			<tr>
				<td><label for="type">导出格式: </label></td>
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection"  data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:right;">
					<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
					<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button>
				</td>
			</tr>
		</table>
	</div>
	
	<form id="queryForm">
		<input type="hidden" name="food.itemId" value='${food.itemId}'/>
		<input type="hidden" name="food.itemName" value='${food.itemName}'/>
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="3">
					<div style="padding-left: 8px;">
						<b>出品信息</b>
					</div>
				</td>
			</tr>
			
			<tr valign="middle">
				<td style="width: 220px;">出品编码：${food.itemId}</td>
				<td colspan="2">出品名称：${food.itemName}</td>
			</tr>
			
			<tr>
				<td>单位：${food.itemDimension}</td>
				<td style="width: 220px;">助记码：${food.queryCode}</td>
			</tr>
			
			<tr>
				<td colspan="3">
					<div style="padding-left: 8px;">
						<b>配方明细信息</b>
						<button data-dojo-type="dijit/form/Button" data-dojo-props='onClick: showDialog' style="margin-left: 50px;">导出出品信息</button>
						<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doClose'>关闭页面</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
	
	<p class="area_blank">&nbsp;</p>
	
	<div  style="top: 120px;">
		<div id="dataGrid" style="width: 80%"></div>
		<div id="myTree" style="width: 20%" ></div>
	</div>
</body>

</html>
