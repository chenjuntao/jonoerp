<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/basic/product/process.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/basic/product/process.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var itemId = '${foodId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>产品信息</b>
					</div>
				</td>
			</tr>
			
			<tr valign="middle">
				<td style="width: 320px;">产品编码：${food.itemId}</td>
				<td>产品名称：${food.itemName}</td>
			</tr>
			
			<tr>
				<td>单位：${food.itemDimension}</td>
				<td>助记码：${food.queryCode}</td>
			</tr>
			
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>工序信息</b>
					</div>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"></div>
	</form>
</body>

</html>
