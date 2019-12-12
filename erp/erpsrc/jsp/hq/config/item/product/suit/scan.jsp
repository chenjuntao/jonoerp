<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan food suit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/product/suit/scan.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/item/product/suit/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var g_foodId = "${foodId }";
	    var g_foodName = "${food.itemName }";
	</script>
</head>

<body class="claro">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="3">
				<div style="padding-left: 8px;">
					<b>套餐信息</b>
				</div>
			</td>
		</tr>
		<tr valign="middle">
			<td style="width: 220px;">套餐编码：${food.itemId}</td>
			<td colspan="2">套餐名称：${food.itemName}</td>
		</tr>
		<tr>
			<td>单位：${food.itemDimension}</td>
			<td style="width: 220px;">助记码：${food.queryCode}</td>
			<td>总售价：<span id="productPrice"></span></td>
		</tr>
		<tr>
			<td colspan="3">
				<b style="padding-left: 8px;">子项</b>
			</td>
		</tr>
	</table>
	<div id="subItemGrid">
	</div>
	
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="3">
				<b style="padding-left: 8px;">换品</b>
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="optionGrid">
	</div>
</body>

</html>
