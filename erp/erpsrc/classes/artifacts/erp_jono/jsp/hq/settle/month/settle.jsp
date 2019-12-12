<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>monthly settle</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/settle/month/settle.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript">
		var monthDate = "${monthDate}";
	</script>
	
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<input type='hidden' id="branchType" name='branchType' value='${branchType}' />
	<form id="settleForm" method="post" >
		<input type='hidden' name='settleRecord.operatorId' value='${settleRecord.operatorId }' />
		<input type='hidden' name='settleRecord.operatorName' value='${settleRecord.operatorName }' />
		<input type='hidden' name='settleRecord.businessDate' value='${businessDate }' />
		<input type='hidden' name='monthDate' value='${monthDate }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">人员编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${settleRecord.operatorId }</span>
				</td>
				<td class="label_right" style="width: 80px;">人员姓名：</td>
				<td class="text_left">
					<span>${settleRecord.operatorName }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">月结日：</td>
				<td class="text_left">
					<span><b>${monthDate }</b></span>
				</td>
				<td class="label_right">操作日期：</td>
				<td class="text_left">
					<span>${operatingTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="settleRecord.settleNote" 
	  					value="${settleRecord.settleNote}" style="width: 70%;" />
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;">
			    	<input type="button" id="btnSubmit" value="确认进行月结" onclick="doSubmit();" />
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
