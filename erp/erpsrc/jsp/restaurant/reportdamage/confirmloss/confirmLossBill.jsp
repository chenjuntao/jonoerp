<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>main page</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/confirmloss/confirmLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/confirmloss/confirm.css">

	<script type="text/javascript">
		var formId = '${formId }';
		var preVersion = '${preVersion }'; 
	</script>
</head>

<body class="claro" style="overflow:hidden;">
	<form id="confirmLossForm">
		<input id="currentVersion" name="currentVersion" type="hidden" / >
		<input id = "preVersion"type = "hidden" / >
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<th colspan="6">
					<b>${brandWord}原料报损单信息</b>
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
				
				<td>
					报损金额：
					<span id="lossMoney"></span>
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
				</td>
				
				<td>
					审核日期：
						<span>${auditTime}</span>
				</td>
				<td colspan="2">
					备注信息：
					<span>${form_note}</span>
				</td>
			</tr>
		</table>
		
		<div id="confirmLossGrid" class="commonGrid" style="top: 95px;bottom: 25px;"></div>
		
		<input type='hidden' name='lossHeader.formId' value='${lossHeader.formId }' />
		<input type='hidden' name='lossHeader.auditor' value='${lossHeader.auditor }' />
		<input type='hidden' name='lossHeader.auditorId' value='${lossHeader.auditorId }' />
		<input type='hidden' name='lossHeader.auditTime' value='${auditTime }' />
		<input type='hidden' name='lossHeader.lossBranchId' value='${lossHeader.lossBranchId }' />
		<input type='hidden' name='lossHeader.storageId' value='${lossHeader.storageId }' />
		<input type='hidden' id="allPayAmt" name='lossHeader.allPayAmt' value='${lossHeader.allPayAmt }' />
		<input type='hidden' id="maxPayItem" name='lossHeader.maxPayItem' value='${lossHeader.maxPayItem }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table id="commandTable" class="hovertable" width="100%" border="1">
			<tr>
				<td style="text-align: center;">
				    <input type="button" onclick="doAudit();" id="auditBtn" value="${brandWord}原料报损单审核通过" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
