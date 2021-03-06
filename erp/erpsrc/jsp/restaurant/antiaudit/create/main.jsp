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
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/antiaudit/create/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/create/main.css">
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<input type="hidden" id="branchType" name="branchType" value="${branchType}" />

	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">入库日期：</td>
				<td class="text_left" colspan="3">
					<input type="text" class="Wdate" id="startDate" value="${lastDate}" style="width: 110px;" onFocus="WdatePicker({onclearing:function(){alert('入库时间不能清空');return ture;},dateFmt:'yyyy-MM-dd',maxDate:'${businessDate }',minDate:'${lastDate }'})" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" value="${businessDate}" style="width: 110px;" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${businessDate }',minDate:'${lastDate }'})" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">${branchCondition}：</td>
				<td class="text_left" style="width: 150px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple" onchange= "changeStorage()"	 style="width: 150px;" id="branchId"></s:select>
				</td>
				
			   	<td class="label_right" style="width: 60px;">仓库：</td>
				<td>
					<s:select list="storageList" listKey="storageId" listValue="storageName" theme="simple"	style="width: 180px;" id="storageId" name="storageId" headerKey="" headerValue="--请选择--"></s:select>
					
					<span style="padding-left: 58px;">
						<input type="button" onclick="doQuery();" style="width: 60px;"  value="查    询">
					</span>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"></div>
	</form>
</body>

</html>
