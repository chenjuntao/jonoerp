<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/requisition/create/batchRequisitionGrid.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/requisition/create/batchMain.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/requisition/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<input type="hidden" id="formType" name="formType" value='produce' />
		<input type="hidden" id="queryStr" value="  h.RECEIVED_STATUS in('default','receiving') and s.status = '已审核' "/>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">制单日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" value="${businessDate}"	style="width: 110px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" value="${businessDate}" style="width: 110px;" onFocus="WdatePicker()" />
				</td>
				
				<td class="label_right" style="width: 60px;">车间：</td>
				<td class="text_left">
					<s:select list="workshopLst" listKey="workshop" listValue="workshop"  value="bean.workshop" theme="simple"	 style="width: 150px;" id="workshop"></s:select>
						<span style="padding-left: 58px;">
						<input type="button" onclick="doQuery();" value="查    询" style="width: 60px">
						
						<input type="button" onclick="putinBatch();" value="批 量 领 料" style="width: 80px;margin-left: 20px">
					</span>
				</td>
			</tr>
		</table>
	
		<p class="area_blank">&nbsp;</p>
		
		<div id="requisitionGrid" class="dataGrid"></div>
	</form>
</body>

</html>
