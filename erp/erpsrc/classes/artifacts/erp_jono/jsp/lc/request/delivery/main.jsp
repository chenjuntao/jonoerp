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
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/delivery/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/delivery/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">订货部门：</td>
				
				<td class="text_left" colspan="3">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple" style="width: 150px;" id="branchId" headerKey="-1" headerValue="--请选择--" ></s:select>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">开始日期：</td>
				
				<td class="text_left" style="width: 180px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${businessDate}" onFocus="WdatePicker()" style="width: 150px;"/>
				</td>
				
				<td class="label_right" style="width: 60px;">结束日期：</td>
				
				<td class="text_left">
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${businessDate}"	onFocus="WdatePicker()" style="width: 150px;"/>
					
			   	 	<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 58px;width: 60px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 62px;"></div>
	</form>
</body>

</html>
