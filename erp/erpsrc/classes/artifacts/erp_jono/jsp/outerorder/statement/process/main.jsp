<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill process</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/outerorder/statement/process/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/outside/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/outerorder/statement/process/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var initStatus = '${initStatus }';
		var formType = '${formType }';
	</script>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 60px;">物流中心：</td>
				<td class="text_left" style="width: 155px;" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple" headerKey="" headerValue="------请选择------"
						 style="width: 150px;" id="supplierId" name="supplierId"> </s:select>
				</td>
				
				<td class="label_right" style="width: 75px;">外部订货方：</td>
				<td class="text_left"  style="width: 155px;" >
					<s:select list="branchLst" listKey="code" listValue="name" theme="simple" 
						 style="width: 150px;" id="branchId" name="branchId"> </s:select>
				</td>
				
				<td class="label_right" style="width: 55px;">单据编号：</td>
				<td class="text_left"   style="width: 135px;">
					<input style="width: 130px;" type="text" id="formIdText" name="formIdText"/>
				</td>
			
				<td class="label_right" style="width: 55px;">制单日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" value="${businessDate}"
						style="width: 100px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" value="${businessDate}"
						style="width: 100px;" onFocus="WdatePicker()" />
						
					<span >
						<input type="button" onclick="doQuery();" style="margin-left: 10px;" value="查询">
					</span>
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 32px">
		</div>
	</form>
</body>

</html>
