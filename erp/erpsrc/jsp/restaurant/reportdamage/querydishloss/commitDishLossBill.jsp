<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm before create dish loss bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/querydishloss/commitDishLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/createdishloss/main.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />

	<script type="text/javascript">
	    var dishGridData = JSON.parse('${dishJsonData }');
	    var rawGridData = JSON.parse('${rawJsonData }');
	    var formId = '${lossHeader.formId }';
	    var preVersion = '${preVersion }'; 
	</script>
</head>

<body class="claro" style="overflow: hidden;">
	<form id="commitLossForm">
		<input id="currentVersion" name="currentVersion" type="hidden" / >
		<input id = "preVersion"type = "hidden" / >
		
		<input type='hidden' name='lossHeader.formId' value='${lossHeader.formId }' />
		<input type='hidden' name='lossHeader.lossBranchId' value='${lossHeader.lossBranchId }' />
		<input type='hidden' name='lossHeader.storageId' value='${lossHeader.storageId }' />
		<input type='hidden' name='lossHeader.auditor' value='${lossHeader.auditor }' />
		<input type='hidden' name='lossHeader.auditorId' value='${lossHeader.auditorId }' />
		<input type='hidden' name='lossHeader.auditTime' value='${auditTime }' />
		<input type='hidden' name='lossHeader.lossType' value='DISHLOSS' />
		<input type='hidden' id="allPayAmt" name='lossHeader.allPayAmt' value='${lossHeader.allPayAmt }' />
		<input type='hidden' id="maxPayItem" name='lossHeader.maxPayItem' value='${lossHeader.maxPayItem }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${dishJsonData }' />
		<input type="hidden" id="rawJsonData" name="rawJsonData" value='${rawJsonData }' />
	</form>
	
	<table class="hovertable" width="100%">
		<tr>
			<th colspan="4"><b>${brandWord}出品报损单信息</b></th>
		</tr>
		<tr>
			<td>
				单据编号：
				<span>${lossHeader.formId}</span>
			</td>
			<td>
				报损部门：
				<span>${lossHeader.lossBranch}</span>
			</td>
			<td>
				报损人员：
				<span>${lossHeader.lossMan}</span>
			</td>
			<td>
				报损日期：
				<span>${lossTime}</span>
			</td>
		</tr>
		
		<tr>
			<td>
				审核人员：
				<span>${lossHeader.auditor}</span>
				<span>${lossHeader.auditorId}</span>
			</td>
			<td>
				审核日期：
				<span>${auditTime}</span>
			</td>
			<td colspan="2">
				备注信息：
				<span>${lossHeader.formNote}</span>
			</td>
		</tr>
	</table>
	
	<div id="rawLossGrid" class="commonGrid" style="top: 95px;bottom: 45px;"></div>
	
	<table class="hovertable" id="commandTable">
		<tr>
			<th>
				<input id="doCommit" type="button" value="确认修改出品报损单" onclick="commitLoss()"/>
			</th>
		</tr>
	</table>
</body>

</html>

