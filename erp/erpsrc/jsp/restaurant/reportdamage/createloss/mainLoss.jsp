<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create material loss bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/createloss/mainLoss.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/createloss/mainLoss.css">

	<script type="text/javascript">
		var jsonData = '${jsonData}';
		var brandWord = '${brandWord}';
	</script>
</head>

<body class="claro" style="overflow: hidden;">
	<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	
	<form id="createLossForm" method="post">
		<input type="hidden" id="jsonData" name="jsonData" />
		<input type="hidden" id="parentTabId" name="parentTabId" value="${tabId}" />
		
		<table class="hovertable" width="100%" border="0">
			<tr>
				<th colspan="5"><b>选择${brandWord}原料报损</b></th>
			</tr>
			
			<tr>
				<td>
					<input type="button" value="根据库存自选" onclick="selMaterial()"/>
					<input type="button" value="删除原料" onclick="delMaterial()" style="margin-left: 20px"/>
				</td>
				
				<td>
					报损部门：
					<s:select list="shopList" listKey="code" listValue="name" theme="simple"
					style="width: 180px;" id="lossBranchId" name="lossHeader.lossBranchId" onchange="changeStorage()"></s:select>
					<input type="hidden" id="lossBranch" name="lossHeader.lossBranch" />
				</td>
				
				<td>
					报损仓库：
					<s:select list="storageList" listKey="storageId" listValue="storageName" theme="simple"
					style="width: 180px;" id="storageId" name="lossHeader.storageId"></s:select>
					<input type="hidden" id="storage" name="lossHeader.storage" />
				</td>
				
				<td style="width: 150px">
					报损金额：
					<span id="lossMoney">0</span>
				</td>
				
				<td>
					备注：<input type="text" id="lossHeader.formNote" name="lossHeader.formNote" style="width:80%" />
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="commonGrid" style="top:65px;bottom: 25px;"></div>
		
		<table class="hovertable" border="0" id="commandTable">
			<tr>
				<td class="text_left" colspan="4" style="text-align: center;">
					<input type="button" value="生成${brandWord}原料报损单" onclick="createLoss()" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>