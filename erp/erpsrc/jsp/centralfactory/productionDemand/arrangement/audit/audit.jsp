<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit arrangement bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/audit/audit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/arrangement/audit/audit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var preVersion = '${preVersion }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
	     <input id="currentVersion" name="currentVersion" type="hidden" />
	     <input id="preVersion" type="hidden" />
         <input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		  
		<input type='hidden' id="formId" name='arrangmentHeader.formId' value='${arrangmentHeader.formId }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>中央工厂生产计划单审核</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;" >
					<span>${arrangmentHeader.formId }</span>
				</td>
				
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">关联单号：</td>
				<td class="text_left"  colspan="7">
					<span>${arrangmentHeader.formRefId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">审核人：</td>
				<td class="text_left" style="width: 180px;">
					<span>${arrangmentHeader.auditor }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">审核日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="auditTime"	name="arrangmentHeader.auditTime" value="${auditTime}" />
				</td>
				
				<td class="label_right">备注：</td>
				<td class="text_left"  style="width: 280px;">
	  				<span>${arrangmentHeader.formNote }</span>
				</td>
			</tr>
			
			<tr>
			    <td class="label_right" style="width: 120px;">操作：</td>
				<td colspan="5" >
				    <input type="button" onclick="doAudit();" value="审 核 通 过" style="margin-left: 30px;width: 80px" />
				    <input type="button" onclick="doClose();" value="取      消" style="margin-left: 30px;width: 80px" />
				</td>
			</tr>
		</table>
		
		<div id="auditDataGrid"></div>
	</form>
	
</body>

</html>
