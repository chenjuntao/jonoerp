<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit dish loss bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/confirmdishloss/confirmDishLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/confirmdishloss/main.css">

	<script type="text/javascript">
		var formId = '${lossHeader.formId }';
		var brandWord = '${brandWord}';
		
		var preVersion = '${preVersion }';
	</script>
</head>

<body class="claro" style="overflow:hidden;">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	<form id="confirmLossForm">
	
		<input id="currentVersion" name="currentVersion" type="hidden" / >
		<input id = "preVersion" type = "hidden" / >
		
		<input type='hidden' id="allPayAmt" name='allPayAmt' value='${lossHeader.allPayAmt }' />
		<input type='hidden' id="maxPayItem" name='maxPayItem' value='${lossHeader.maxPayItem }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<input type="hidden" id="dishJsonData" name="dishJsonData" value='${dishJsonData }' />
		<input type="hidden" id="parentTabId" name="parentTabId" value="${tabId}" />
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<th colspan="4">
					<b>${brandWord}出品报损单信息</b>
				</th>
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
					报损仓库：
					<span>${lossHeader.storage}</span>
				</td>
				<td>
					审核人员：
					<span>${lossHeader.auditor}</span>
					<span>${lossHeader.auditorId}</span>
				</td>
				<td>
					审核日期：
				<span>${auditTime}</span>
				<input  id="auditTime" type="hidden" name="auditTime" value="${auditTime}" ></input>
				</td>
				<td colspan="2">
					备注信息：
					<span>${lossHeader.formNote}</span>
				</td>
			</tr>
		</table>
	</form>
	
	<div id="confirmLossGrid" class="commonGrid" style="top: 95px;bottom: 30px;"></div>
	
	<table id="commandTable" class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doAudit();" id="auditBtn" value="审核出品报损单" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
