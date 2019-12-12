<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/dailyManage/settle.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="settleForm" method="post" >
	<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 130px;">逆日结部门：</td>
				<td class="text_left" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"  onchange= "refreshDate(branchId)" style="width: 150px;" id="branchId" name ="branchId"></s:select>
					<input type="hidden" id="branchName" name="branchName"/>
				</td>
					
				<td class="label_right">当前的营业日期：</td>
					<td class="text_left">
					<span><b id="businessDate" >${businessDate }</b></span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">逆日结后的营业日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="afterbusinessDate" name="afterbusinessDate" value="${operatingTime}" style="width: 110px;"openChange="true" onFocus="WdatePicker({minDate:'${afterbusinessDate }'})" />
				</td>
				<td class="label_right">进行逆日结的操作时间：</td>
				<td class="text_left">
					<span><b >${operatingTime }</b></span>
						<input type="hidden" id="operatingTime" name="operatingTime" value="${afterbusinessDate}"/>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">逆日结人员编号：</td>
				<td class="text_left">
					<span>${operatorId }</span>
				</td>
				
				<td class="label_right">逆日结人员姓名：</td>
				<td class="text_left">
					<span>${operatorName }</span>
				</td>
			</tr>
			
				<tr>
				<td colspan="4" style="text-align: center;">
			    	<input type="button" id="btnSubmit" value="确认进行逆日结" onclick="doSubmit();" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
