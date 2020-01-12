<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/putinstorage/querycurrentstorage/queryCurrentStorage.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
	<link rel="stylesheet" href="<%=dojoBase %>/cbtree/themes/claro/claro.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/querycurrentstorage/queryCurrentStorage.css">
</head>

<body class="claro">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	<div id="selectItemDiv">
		<table class="hovertable" border="1" bordercolor="#FEB5AE" bgcolor="#E9EFF5" width="100%">
			<tr>
				<td>按照种类选择</td>
			</tr>
		</table>
		<div id="categoryTree"></div>
		<div id="dataGrid"></div>
	</div>
	<div id="queryStorageGrid">
		<table class="hovertable" border="1" bordercolor="#FEB5AE" bgcolor="#E9EFF5" width="100%">
			<tr>
				<td  class="label_right">门店：</td>
				<td class="text_left">
					<s:select list="shopList" listKey="code" listValue="name" theme="simple"
					style="width: 150px;" id="shopC" name="shopC" onchange="branchChange(shopC,storageId)"></s:select>
				</td>
				
				<td class="label_right">仓库：</td>
				<td>
					<s:select list="storageList" listKey="storageId" listValue="storageName" theme="simple"
					style="width: 160px;" id="storageId" name="storageId" ></s:select>
				</td>
				
				<td >
					<div id="placeHolder"></div>
				</td>
				<td >
					<button id="clearItemBtn" type="button">清空查询结果</button>
				</td>
			</tr>
		</table>
		<div id="storageGrid"></div>
	</div>
</body>

</html>