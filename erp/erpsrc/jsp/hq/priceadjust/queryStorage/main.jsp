<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>price adjust</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/queryStorage/main.js?Version=<%=currenttime %>"></script>
	
      <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/priceadjust/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<td class="label_right" style="width: 60px;">制单日期：</td>
			<td class="text_left">
				<input type="text" class="Wdate" id="startDate" value="${startDate}"	style="width: 110px;" onFocus="WdatePicker()" />
			</td>
			<td class="label_right" style="width: 100px;">门店：</td>
					<td class="text_left" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"  style="width: 150px;" id="branchId"></s:select>
				</td>
					<td class="label_right" style="width: 100px;">物料编码：</td>
				<td class="text_left" colSpan="2">
				<input id="itemId" onkeydown="if(event.keyCode==13){doQuery();return false;}" type="text">
				<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 58px;width: 60px;" />
				</td>
		</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 32px;"></div>
	</form>
</body>

</html>
