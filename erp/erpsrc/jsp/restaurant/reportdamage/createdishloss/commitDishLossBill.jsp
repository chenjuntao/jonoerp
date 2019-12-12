<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm before create dish loss bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/createdishloss/commitDishLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/createdishloss/main.css">

	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	    var rawGridData = JSON.parse('${rawJsonData }');
	</script>
</head>

<body class="claro" style="overflow: hidden;">
	<form id="createLossForm">
		<input type='hidden' name='lossHeader.formId' value='${lossHeader.formId }' />
		<input type='hidden' name='lossHeader.lossBranchId' value='${lossHeader.lossBranchId }' />
		<input type='hidden' name='lossHeader.lossBranch' value='${lossHeader.lossBranch }' />
		<input type='hidden' name='lossHeader.lossManId' value='${lossHeader.lossManId }' />
		<input type='hidden' name='lossHeader.lossMan' value='${lossHeader.lossMan }' />
		<input type='hidden' name='lossHeader.storageId' value='${lossHeader.storageId }' />
		<input type='hidden' name='lossHeader.lossType' value='DISHLOSS' />
		<input type='hidden' name='lossHeader.storage' value='${lossHeader.storage }' />
		<input type='hidden' name='lossTime' value='${lossTime }' />
		<input type='hidden' name='lossHeader.formNote' value='${lossHeader.formNote }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<input type="hidden" id="rawJsonData" name="rawJsonData" value='${rawJsonData }' />
		<s:token/>
	
	<table class="hovertable" width="100%">
		<tr>
			<th colspan="3"><b>${brandWord}出品报损单信息</b></th>
		</tr>
		
		<tr>
			<td>
				报损部门：
				<span>${lossHeader.lossBranch}</span>
			</td>
			
			<td>
				报损仓库：
				<span>${lossHeader.storage}</span>
			</td>
			
			<td>
				报损人员：
				<span>${lossHeader.lossMan}</span>
			</td>
		</tr>
		
		<tr>
			<td>
				报损日期：
				<span>${lossTime}</span>
			</td>
			
			<td colspan="1">
				备注：
				<span>${lossHeader.formNote}</span>
			</td>
			<td class="text_left" colspan="1" style="text-align: left;">
				<input id="doCommit" type="button" value="确认生成出品报损单" onclick="commitLoss()"/>
			</td>
		</tr>
	</table>
	
	<div id="rawLossGrid" class="commonGrid" style="top: 95px;bottom:0px"></div>
	
	</form>
</body>

</html>
