<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>output record</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/production/output/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/requisition/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var startDate = '${startDate}';
		var endDate = '${endDate}';
		var loginBranchId = '${loginBranchId}';
	</script>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<input type="hidden" id="jsonData" name="jsonData" value='' />
	<input type="hidden" id="ids" name="ids" value='' />	
	<input type="hidden" id="queryStr" value=" s.status = '已审核' "/>
	
	<table class="hovertable" id="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 100px;">制单日期：</td>
			<td class="text_left" style="width: 285px;">
				<input type="text" class="Wdate" id="startDate" value="${startDate}" style="width: 120px;"	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
				<span style="padding: 0 5px;">至</span> 
				<input	type="text" class="Wdate" id="endDate" value="${endDate}"	style="width: 120px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />	
			</td>
			
			<td class="label_right" style="width: 100px;">商品名称或编码：</td>
			<td class="text_left">
	 			<input type="text" id="itemName" style="width: 120px;" 	onkeydown="if(event.keyCode==13){doQuery();return false;}" />
				<span style="padding-left: 58px;">
					<input type="button" onclick="doQuery();" value="查    询" style="width: 60px;" >
					<input type="button" onclick="putinStorage();" value="批 量 入 库" style="width: 80px;margin-left: 30px" >
				</span>
			</td>
		</tr>
	</table>
	
	<p class="area_blank">&nbsp;</p>
	
	<div id="requisitionGrid" class="dataGrid">	</div>
</body>

</html>
