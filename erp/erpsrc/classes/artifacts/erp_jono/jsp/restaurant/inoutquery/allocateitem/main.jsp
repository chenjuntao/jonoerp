<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/allocateitem/query/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/allocateitem/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/allocateitem/query/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="6">
					<div style="padding-left: 8px;">
						<b>调拨单列表</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">调入部门：</td>
				<td class="text_left" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple" headerKey="" headerValue="--请选择--"
						 style="width: 150px;" id="inBranchId" name="transferHeader.inBranchId" onchange= "refreshStorage1(inBranchId,inStorageId)"></s:select>
					<input type="hidden" id="inBranch" name="transferHeader.inBranch"  />
				</td>
				
				<td class="label_right" style="width: 120px;">调入仓库：</td>
				<td>
					<s:select list="inStorageList" listKey="storageId" listValue="storageName" theme="simple"
					style="width: 180px;" id="inStorageId" name="transferHeader.inStorageId" headerKey="" headerValue="--请选择--"></s:select>
					<input type="hidden" id="inStorage" name="transferHeader.inStorage" />
				</td>
				
				<td class="label_right" style="width: 120px;">开始日期：</td>
				<td class="text_left" style="width: 200px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
							 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" />
				</td>
			</tr>
			<tr>
				
				<td class="label_right" style="width: 100px;">调出部门：</td>
				<td class="text_left" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"  onchange= "refreshStorage1(outBranchId,outStorageId)"
						 style="width: 150px;" id="outBranchId" name="transferHeader.outBranchId" headerKey="" headerValue="--请选择--"></s:select>
					<input type="hidden" id="outBranch" name="transferHeader.outBranch"  />
				</td>
				
				<td class="label_right" style="width: 120px;">调出仓库：</td>
				<td>
					<s:select list="outStorageList" listKey="storageId" listValue="storageName" theme="simple"
					style="width: 180px;" id="outStorageId" name="transferHeader.outStorageId"  headerKey="" headerValue="--请选择--"></s:select>
					<input type="hidden" id="outStorage" name="transferHeader.outStorage" />
				</td>
				
				<td class="label_right" style="width: 120px;">结束日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
							onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				</td>
			</tr>
			<tr>
				<td colspan="6">
			   	 	<input type="button" onclick="doQuery();" value="查询" />
			   	 	<input type="button" onclick="exportXls();" value="导出EXCEL">
					<input type="button" onclick="exportCsv();" value="导出文本">
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid">
		</div>
	</form>
</body>

</html>
