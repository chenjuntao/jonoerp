<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/orderSummary/audit/audit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/orderSummary/audit/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		
		<input type='hidden' id="formId" name='collectHeader.formId' value='${collectHeader.formId }' />
		<input type='hidden' id="branchId" name='branchId' value='${cfCode }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>中央工厂汇总单审核</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;" colspan="7">
					<span>${collectHeader.formId }</span>
				</td>
			</tr>
			
				<td class="label_right" style="width: 120px;">汇总部门：</td>
				<td class="text_left">
					<span>${branch }</span>
				</td>
				
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
				
				<td class="label_right">总额：</td>
				<td class="text_left">
					<span>${collectHeader.allPayAmt }</span>
				</td>
			<tr>
				<td class="label_right">主要汇总品：</td>
				<td class="text_left" >
					<span>${collectHeader.maxPayItem }</span>
				</td>
				
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<span>${collectHeader.formNote }</span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<div id="auditDataGrid">
		</div>
		
		<input type='hidden' id="allPayAmt" name='collectHeader.allPayAmt' 
			value='${collectHeader.allPayAmt }' />
		<input type='hidden' id="maxPayItem" name='collectHeader.maxPayItem' 
			value='${collectHeader.maxPayItem }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">审核人：</td>
				<td class="text_left" style="width: 180px;">
					<span>${collectHeader.auditor }</span>
				</td>
				<td class="label_right" style="width: 120px;">审核日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="auditTime"
						name="collectHeader.auditTime" value="${auditTime}" />
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doAudit();" value="中央工厂汇总单审核通过" style="margin-right: 50px;" />
			    <input type="button" onclick="doClose();" value="取消"  style="margin-right: 100px;"/>
			</td>
		</tr>
	</table>
</body>

</html>
