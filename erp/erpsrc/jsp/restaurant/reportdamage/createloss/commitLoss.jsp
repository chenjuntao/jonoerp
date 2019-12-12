<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm to create material loss bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/createloss/commitLoss.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/createloss/mainLoss.css">

	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow: hidden;">
	<form id="createLossForm">
		<input type='hidden' name='lossHeader.formId' value='${lossHeader.formId }' />
		<input type='hidden' name='lossHeader.lossBranchId' value='${lossHeader.lossBranchId }' />
		<input type='hidden' name='lossHeader.lossBranch' value='${lossHeader.lossBranch }' />
		<input type='hidden' name='lossHeader.lossManId' value='${lossHeader.lossManId }' />
		<input type='hidden' name='lossHeader.lossMan' value='${lossHeader.lossMan }' />
		<input type='hidden' name='lossTime' value='${lossTime }' />
		<input type='hidden' name='lossHeader.formNote' value='${lossHeader.formNote }' />
		<input type='hidden' name='lossHeader.lossType' value='RAWLOSS' />
		<input type='hidden' name='lossHeader.storageId' value='${lossHeader.storageId }' />
		<input type='hidden' name='lossHeader.storage' value='${lossHeader.storage }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<s:token/>
		<table class="hovertable" width="100%" border="0">
			<tr>
				<th colspan="4"><b>${brandWord}原料报损单信息</b></th>
			</tr>
			
			<tr>
				<td>
					报损部门：
					<span>${lossHeader.lossBranch}</span>
				</td>
				<td>
					报损人员：
					<span>${lossHeader.lossMan}</span>
				</td>
				<td>
						报损金额：
					<span id="lossMoney"></span>
				</td>
			</tr>
			
			<tr>
				<td>
					报损日期：
					<span>${lossTime}</span>
				</td>
				<td>
					报损仓库：
					<span>${lossHeader.storage }</span>
				</td>
				<td colspan="2">
					备注：
					<span>${lossHeader.formNote }</span>
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="commonGrid" style="top:95px;bottom: 25px;"></div>
		
		<table class="hovertable" border="0" id="commandTable">
			<tr>
				<td class="text_left" colspan="4" style="text-align: center;">
					<input id="doCommit" type="button" value="确认生成${brandWord}原料报损单" onclick="commitLoss()"/>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>