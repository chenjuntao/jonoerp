<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit purchase bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/manage/edit.js?Version=<%=currenttime %>"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/in/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var deliveryType = '${deliveryType }';
		var preVersion = '${preVersion }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='purchasingHeader.formId' value='${formId }' />
		<input type='hidden' name='purchasingHeader.deliveryType' value='${deliveryType }' />
		<input type='hidden' name='purchasingHeader.auditor' value='${loginUsername }' />
		<input type='hidden' name='purchasingHeader.auditTime' value='${auditTime }' />
		
		<input id="currentVersion" name="currentVersion" type="hidden" />
		<input id="preVersion" type="hidden" />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span id="l_formId"></span>
				</td>
				
				<td class="label_right" style="width: 70px;">供应商：</td>
				<td class="text_left" style="width: 120px;">
					<span id="l_provider"></span>
				</td>
				
				<td class="label_right" style="width: 70px;">到货日期：</td>
				<td class="text_left">
						<input type="text" class="Wdate" id="receiveTime" value="${receiveTime}" name="purchasingHeader.receiveTime" style="width: 110px;" onFocus="WdatePicker()" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">订货部门：</td>
				<td class="text_left">
					<span id="l_requester"></span>
				</td>
				
				<td class="label_right">收货地址：</td>
				<td class="text_left" colspan="3">
					<span id="l_receiveAddress"></span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span id="l_formMaker"></span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span id="l_formTime"></span>
				</td>
				
				<td class="label_right">配送方式：</td>
				<td class="text_left">
					<span id="l_deliveryType"></span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">审核人员：</td>
				<td class="text_left">
					<span id="l_auditor">${loginUsername }</span>
				</td>
				
				<td class="label_right">审核日期：</td>
				<td class="text_left" colspan="3">
					<span id="l_auditTime">${auditTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" id="formNote" name="purchasingHeader.formNote" 	value="" style="width: 380px;" />
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" style="top: 0px"></div>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  	
	  	<p class="area_blank">&nbsp;</p>
	  	
		<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doDelete();" value="删除单据" style="margin-left: 8px;" />
			    <input type="button" onclick="doInvalid();" value="作废单据" style="margin-left: 8px;" />
			    <input type="button" onclick="doUpdate();" value="确认修改" style="margin-left: 8px;" />
			    <input type="button" onclick="doClose();" value="取消修改" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
	</form>
</body>

</html>
