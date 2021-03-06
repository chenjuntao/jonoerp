<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>main page</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/queryloss/modifyLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/queryloss/listloss.css">

	<script type="text/javascript">
		var formId = '${formId }';
		var lossBranch = '${lossHeader.lossBranch }';
		var lossMan =  '${lossHeader.lossMan }';
		var lTime =  '${lossTime }';
		var storage =  '${lossHeader.storage}';
		var auditor =  '${lossHeader.auditor }';
		var aTime =  '${auditTime }';
		var fStatus = '${formStatus}';
		var formnote = '${form_note}';
		
		var preVersion = '${preVersion }'; 
	</script>
</head>

<body class="claro">
<form id="billForm" method="post" >
	<input id="currentVersion" name="currentVersion" type="hidden" / >
	<input id = "preVersion"type = "hidden" / >
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<th colspan="4">
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
			
			<td>
				单据状态：
				<span>${formStatus}</span>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				备注信息：
				<span>${form_note}</span>
			</td>
		</tr>
	</table>
	
	<div id="grid_print" class="print-only">
		<input type='hidden' id="formId" name='lossHeader.formId'	 value='${lossHeader.formId }' />
	</div>
	
 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	 	
	<div id="showLossGrid" class="commonGrid" style="top: 125px;"></div></form>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
				<input type="button" onclick="doDelete()" value="删除单据" style="margin-left: 8px;" id="btnDel"/>
				<input type="button" onclick="doInvalidate()" value="作废单据" style="margin-left: 8px;" id="btnInv"/>
			    <input type="button" onclick="doSave();" value="确认修改" style="margin-left: 8px;" />
				<input type="button" onclick="doClose()" value="取消操作" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
