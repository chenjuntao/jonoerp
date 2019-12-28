<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/reportdamage/confirmloss/listLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/confirmloss/main.css">
</head>

<body class="claro" style="overflow:hidden">

	<form id="listLossForm">
		<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<th colspan="6">
					<b>已创建未审核的${brandWord}原料报损单列表</b>
				</th>
			</tr>
			
			<tr>
				<td style="width: 350px">报损日期：
					<input type="text" class="Wdate" id="startDate" value="${startDate}" style="width: 110px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" value="${endDate}" style="width: 110px;" onFocus="WdatePicker()" />
				</td>
				
				<td style="width: 250px">
					门店：
					<s:select list="shopList" listKey="code" listValue="name" theme="simple" onchange="refreshStorage()" style="width: 180px;" id="branchId" name="branchId"></s:select>
				</td>
				
				<td style="width: 250px">
					仓库：
					<s:select list="storageList" listKey="storageId" listValue="storageName" theme="simple"	style="width: 180px;" id="storageId" name="storageId" headerKey="" headerValue="--请选择--"></s:select>
				</td>
				
				<td>
					<label>
						<input type="button" onclick="doQuery()" value="查    询" style="width: 60px;margin-left: 30px"  />
					</label>
				</td>
			</tr>
			
		</table>
		
		<div id="listLossGrid" class="commonGrid" style="top: 65px;"></div>
	</form>
</body>

</html>