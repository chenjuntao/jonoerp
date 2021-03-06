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
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/create/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/preject/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<input type="hidden" id="branchType" value='${branchType}'/>
	
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">入库日期：</td>
				<td class="text_left" style="width: 260px;">
					<input type="text" class="Wdate" id="startDate" value="${businessDate}"	style="width: 100px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" value="${businessDate}" style="width: 100px;" onFocus="WdatePicker()" />
				</td>
				
				<td class="label_right" style="width: 120px;">商品名称或编码：</td>
				<td class="text_left" style="width: 120px;">
	 				<input type="text" id="itemName" name="itemName" style="width: 100px;"	onkeydown="if(event.keyCode==13){doQuery();return false;}" />
	 			</td>
	 			
				<td class="label_right" style="width: 60px;">门店：</td>
				<td class="text_left">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 style="width: 160px;" id="branchId"></s:select>
					
					<input type="button" onclick="doQuery();" value="查    询" style="width: 60px;margin-left: 30px">
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"  class="commonGrid"  style="top: 32px;"></div>
	</form>
</body>

</html>
