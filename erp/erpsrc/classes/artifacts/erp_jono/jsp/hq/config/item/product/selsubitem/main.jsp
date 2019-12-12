<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>select sub item</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/product/selsubitem/main.js?Version=<%=currenttime %>"></script>
	
	<style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/item/product/selsubitem/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<script type="text/javascript">
		var g_selType = '${selType }';
		var g_subItemId = '${subItemId }';
	</script>
</head>

<body class="claro">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 80px;">出品名称：</td>
			<td>
	 			<input type="text" id="itemName" onkeydown="if(event.keyCode==13)doQuery();" />
	 			
		   	 	<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;" />
		   	 	<input type="button" onclick="doSelect();" value="确定" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="categoryTree">
	</div>
	
	<div id="dataGrid">
	</div>
</body>

</html>
