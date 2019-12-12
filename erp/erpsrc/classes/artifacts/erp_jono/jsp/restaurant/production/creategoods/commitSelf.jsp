<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm before create dish loss bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/production/creategoods/commitSelf.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/production/creategoods/main.css">

	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	    var rawGridData = JSON.parse('${rawJsonData }');
	</script>
</head>

<body class="claro" style="overflow: hidden;">
	<form id="createSelfForm">
		<input type='hidden' name='selfSemisHeader.formId' value='${selfSemisHeader.formId }' />
		<input type='hidden' name='selfSemisHeader.branchId' value='${selfSemisHeader.branchId }' />
		<input type='hidden' name='selfSemisHeader.branch' value='${selfSemisHeader.branch }' />
		<input type='hidden' name='selfSemisHeader.creatorManId' value='${selfSemisHeader.creatorManId }' />
		<input type='hidden' name='selfSemisHeader.formMaker' value='${selfSemisHeader.formMaker }' />
		<input type='hidden' name='selfSemisHeader.formTime' value='${selfSemisHeader.formTime }' />
		<input type='hidden' name='selfSemisHeader.formTimeActual' value='${selfSemisHeader.formTimeActual }' />
		<input type='hidden' name='selfSemisHeader.auditorId' value='${selfSemisHeader.auditorId }' />
		<input type='hidden' name='selfSemisHeader.auditor' value='${selfSemisHeader.auditor }' />
		<input type='hidden' name='selfSemisHeader.auditTime' value='${selfSemisHeader.auditTime }' />
		<input type='hidden' name='selfSemisHeader.mainItem' value='${selfSemisHeader.mainItem }' />
		<input type='hidden' name='selfSemisHeader.formNote' value='${selfSemisHeader.formNote }' />
		<input type='hidden' name='formTime' value='${formTime }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<input type="hidden" id="rawJsonData" name="rawJsonData" value='${rawJsonData }' />
	
	
	<table class="hovertable" width="100%">
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
			
			<td colspan="2">
				备注：
				<span>${selfSemisHeader.formNote}</span>
			</td>
			<td class="text_left" colspan="1" style="text-align: left;">
				<input id="doCommit" type="button" value="确认生成半成品入库单" onclick="commit()"/>
			</td>
		</tr>
	</table>
	
	<div id="dishGrid" class="commonGrid" style="top: 94px;bottom: 0px;"></div>
	</form>
</body>

</html>
