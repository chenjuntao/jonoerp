<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>arrangement bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/manage/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/manage/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var jsonData = '${jsonData }';
		var gridData = [];
		if (jsonData != '') {
			gridData = JSON.parse('${jsonData }');
		}
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">制单日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" value="${startDate }" style="width: 120px;"	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
					<span style="padding: 0 5px;">至</span> 
					<input	type="text" class="Wdate" id="endDate" value="${endDate }"	style="width: 120px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
					
					<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 50px;width: 60px;"/>
				</td>
				
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 32px;"></div>
	</form>
</body>

</html>
