<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/outerorder/orderscan/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/query/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/outerorder/orderscan/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<form id="dataForm" method="post" >
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 60px;">物流中心：</td>
			<td class="text_left" >
				<s:select list="branch_lc_Lst" listKey="code" listValue="name" theme="simple"
					 style="width: 180px;" id="branch_lc" name="branch_lc" headerKey="" headerValue="-----请选择物流中心-----"> </s:select>
			</td>
			
			<td class="label_right" style="width: 80px;">外部订货方：</td>
			<td class="text_left" >
				<s:select list="branch_out_Lst" listKey="code" listValue="name" theme="simple"
					 style="width: 180px;" id="branch_out" name="branch_out" > </s:select>
			</td>
			
				
			<td class="label_right" style="width: 80px;">审核日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" value="" style="width: 120px;"	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
					<span style="padding: 0 5px;">至</span> 
					<input	type="text" class="Wdate" id="endDate" value=""	style="width: 120px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
					
					<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;" />
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="4">
				<div style="padding-left: 8px;"><strong>外部订货单列表</strong></div>
			</td>
		</tr>
 		</table>
 		
	<div id="dataGrid" >
	</div>
	</form>
</body>

</html>
