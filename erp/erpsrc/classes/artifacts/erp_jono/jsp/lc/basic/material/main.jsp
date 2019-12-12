<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>material list</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/basic/material/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/basic/material/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<table class="hovertable" width="100%" border="1">
		<tr>
<!-- 			<td class="label_right" style="width: 100px;">订货方：</td> -->
<!-- 			<td class="text_left" style="width: 160px;"> -->
<%-- 				<s:select list="shopLst" listKey="code" listValue="name" theme="simple" --%>
<%-- 					 style="width: 150px;" id="branchId" headerKey="-1" headerValue="--请选择--" ></s:select> --%>
<!-- 			</td> -->
				<td class="label_right" style="width: 80px;">原料名称：</td>
				<td class="text_left" style="width: 160px;">
		 			<input type="text" id="itemName" style="width: 180px;" />
				</td>
			<td class="label_right" style="width: 80px;">供货方：</td>
			<td class="text_left">
				<s:select list="supplierLst" listKey="code" listValue="name" theme="simple"
					 style="width: 150px;" id="supplierId" headerKey="-1" headerValue="--请选择--" ></s:select>
			</td>
		</tr>
		<tr>
			<td class="label_right" style="width: 120px;">采购制单日期：</td>
			<td class="text_left" colspan="3">
				<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
					style="width: 150px;" onFocus="WdatePicker()" />
				<span style="padding: 0px 5px;">至</span>
				<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
					 style="width: 150px;" onFocus="WdatePicker()" />
					
				<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;">
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid" style="top: 62px;">
	</div>
</body>

</html>
