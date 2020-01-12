<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit semis bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/production/confirmgoods/commitSelf.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/production/confirmgoods/main.css">

	<script type="text/javascript">
	    var jsonData = JSON.parse('${jsonData }');
	    var rawJsonData = JSON.parse('${rawJsonData }');
		var formId = '${selfSemisHeader.formId }';
	</script>
</head>

<body class="claro" style="overflow:hidden;">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	<form id="billForm">
	
		<input id="currentVersion" name="currentVersion" type="hidden" / >
		<input id = "preVersion" type = "hidden" / >
		
		<input type='hidden' name='selfSemisHeader.formId' value='${selfSemisHeader.formId }' />
		<input type='hidden' name='selfSemisHeader.auditorId' value='${selfSemisHeader.auditorId }' />
		<input type='hidden' name='selfSemisHeader.auditor' value='${selfSemisHeader.auditor }' />
		<input type='hidden' name='selfSemisHeader.branchId' value='${selfSemisHeader.branchId }' />
<%-- 		<input type='hidden' name='selfSemisHeader.auditTime' value='${selfSemisHeader.auditTime }' /> --%>
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<input type="hidden" id="rawJsonData" name="rawJsonData" value='${rawJsonData }' />
		<input type="hidden" id="parentTabId" name="parentTabId" value="${tabId}" />
	
		<table class="hovertable" width="100%" border="1">
		<tr>
			<th colspan="3"><b>半成品生产入库单信息</b></th>
		</tr>
		
		<tr>
			<td>
				生产入库部门：
				<span>${selfSemisHeader.branch}</span>
			</td>
			
			<td>
				生产入库人员：
				<span>${selfSemisHeader.formMaker}</span>
			</td>
			<td>
				入库日期：
				<span>${formTime}</span>
			</td>
		</tr>
			<tr>
				
				<td>
					审核人员：
					<span>${selfSemisHeader.auditorId}</span>
					<span>${selfSemisHeader.auditor}</span>
				</td>
				<td>
					审核日期：
				<span>${auditTime}</span>
				<input  id="auditTime" type="hidden" name="auditTime" value="${auditTime}" ></input>
				</td>
				<td colspan="2">
					备注信息：
					<span>${selfSemisHeader.formNote}</span>
				</td>
			</tr>
		</table>
	
		<div id="dishGrid" style="height:180px;"></div>
	
	<table class="hovertable" width="100%">
		<tr>
			<th><b>转化为原料耗料信息</b></th>
		</tr>
	</table>
	
	<div id="rawGrid"></div>
	<table id="commandTable" class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doAudit();" id="auditBtn" value="审核半成品入库单" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
	</form>
</body>

</html>
