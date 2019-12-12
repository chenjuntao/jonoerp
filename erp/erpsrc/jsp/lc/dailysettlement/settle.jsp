<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/dailysettlement/settle.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<input type='hidden' id="branchType" name='branchType' value='${branchType}' />

	<form id="settleForm" method="post" >
		<input type='hidden' name='settleRecord.branchId' value='${settleRecord.branchId }' />
		<input type='hidden' name='branchId' value='${settleRecord.branchId }' />
		<input type='hidden' name='settleRecord.branchName' value='${settleRecord.branchName }' />
		<input type='hidden' name='settleRecord.operatorId' value='${settleRecord.operatorId }' />
		<input type='hidden' name='settleRecord.operatorName' value='${settleRecord.operatorName }' />
		<input type='hidden' name='settleRecord.businessDate' id="setbusinessDate" value='${afterbusinessDate }' />
		<input type='hidden' name='settleRecord.operatingTime' value='${operatingTime }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 20%">日结部门编号：</td>
				<td class="text_left" style="width: 30%">
					<span>${settleRecord.branchId }</span>
				</td>
				
				<td class="label_right" style="width:20%">日结部门名称：</td>
				<td class="text_left" style="width: 30%">
					<span>${settleRecord.branchName }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">日结人员编号：</td>
				<td class="text_left">
					<span>${settleRecord.operatorId }</span>
				</td>
				
				<td class="label_right">日结人员姓名：</td>
				<td class="text_left">
					<span>${settleRecord.operatorName }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">当前营业日期：</td>
				<td class="text_left">
				<span><b id="businessDate" name="businessDate">${businessDate }</b></span>
				</td>
				
				<td class="label_right">进行日结的操作时间：</td>
				<td class="text_left">
					<span>${operatingTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">日结后的营业日期：</td>
				<td class="text_left">
				<input type="text" class="Wdate" id="afterbusinessDate" name="afterbusinessDate" value="${afterbusinessDate}" style="width: 110px;" openChange="true" onFocus="WdatePicker({maxDate:'${maxTime }',minDate:'${afterbusinessDate }'})" />
				</td>
				
				<td class="label_right">备注：</td>
				<td class="text_left" >
	  				<input type="text" name="settleRecord.settleNote" 
	  					value="${settleRecord.settleNote}" style="width: 70%;" />
				</td>
			</tr>
			
			<tr>
				<td colspan="4" style="text-align: center;">
			    	<input type="button" id="btnSubmit" value="确认进行日结" onclick="doSubmit();" />
				</td>
			</tr>
			
			<tr>
				<td colspan="4"  style="text-align: center;">
					<div id="waitingDiv" style="font-size:20px;"></div>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
