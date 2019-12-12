<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>main page</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

<script type="text/javascript" src='<%=dojoBase%>/dojo/dojo.js'></script>
<script type="text/javascript"	src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
<script type="text/javascript"	src="<%=appRoot%>/jsp/restaurant/allocateitem/create/commit.js?Version=<%=currenttime %>"></script>
<script type="text/javascript"	src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>

<style type="text/css">
	@import "<%=dojoBase%>/dojo/resources/dojo.css";
	@import "<%=dojoBase%>/dgrid/css/dgrid.css";
	@import "<%=dojoBase%>/dgrid/css/skins/claro.css";
</style>

	<link rel="stylesheet"	href="<%=appRoot%>/jsp/restaurant/allocateitem/create/main.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />

	<script type="text/javascript">
		var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto; overflow-x: hidden;">
	<form id="billForm" method="post">
		<input type='hidden' name='transferHeader.inBranchId' value='${transferHeader.inBranchId }' /> 
		<input type='hidden' name='transferHeader.inBranch' value='${transferHeader.inBranch}' />
		<input type='hidden' name='transferHeader.inStorageId'	value='${transferHeader.inStorageId }' />
		<input type='hidden' name='transferHeader.inStorage' value='${transferHeader.inStorage}' />
		<input type='hidden' name='transferHeader.outBranchId'	value='${transferHeader.outBranchId }' /> 
		<input type='hidden' name='transferHeader.outBranch' value='${transferHeader.outBranch}' />
		<input type='hidden' name='transferHeader.outStorageId'	value='${transferHeader.outStorageId }' /> 
		<input type='hidden' name='transferHeader.outStorage' value='${transferHeader.outStorage}' />
		<input type='hidden' name='transferHeader.fromMakerId' value='${transferHeader.fromMakerId }' /> 
		<input type='hidden' name='transferHeader.fromMaker' value='${transferHeader.fromMaker }' />
		<input type='hidden' name='formTime' value='${formTime }' /> 
		<input	type='hidden' name='transferHeader.auditor'	value='${transferHeader.auditor }' />
		 <input type='hidden' name='transferHeader.auditTime' value='${auditTime }' /> 
		 <input	type='hidden' name='transferHeader.formNote' value='${transferHeader.formNote }' />
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>调拨单提交</b>
					</div>
				</td>
			</tr>

			<tr>
				<td class="label_right">调入部门：</td>
				<td class="text_left"><span>${transferHeader.inBranch}</span></td>

				<td class="label_right">调入仓库：</td>
				<td class="text_left"><span>${transferHeader.inStorage }</span>
				</td>
				
				<td class="label_right">制单人员：</td>
				<td class="text_left"><span>${transferHeader.fromMaker}</span>
				</td>
			</tr>

			<tr>
				<td class="label_right">调出部门：</td>
				<td class="text_left"><span>${transferHeader.outBranch }</span>
				</td>

				<td class="label_right">调出仓库：</td>
				<td class="text_left"><span>${transferHeader.outStorage }</span>
				</td>

				<td class="label_right">制单日期：</td>
				<td class="text_left"><span>${formTime }</span></td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="2"><span>${transferHeader.formNote }</span>
				</td>
				<td style="text-align: left;" colspan="3"><input type="button"
				id="btn_submit" value="确认生成调拨单" style="padding-left: 8px;" /></td>
			</tr>
		</table>

		<p class="area_blank">&nbsp;</p>

		<input type="hidden" id="jsonData" name="jsonData"
			value='${jsonData }' />

		<div id="dataGrid" class="commonGrid" style="top: 122px"></div>


		<p class="area_blank">&nbsp;</p>

	</form>
</body>

</html>
