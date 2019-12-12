<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>audit page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/outerorder/audit/audit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
        @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
    <!-- 引用新增要货单页面create的样式 -->
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var preVersion = '${preVersion }'; 
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		 <input type='hidden' id="formId" name='requestHeader.formId' value='${requestHeader.formId }' />
		 <input id="currentVersion" name="currentVersion" type="hidden" / >
		 <input id = "preVersion"type = "hidden" / >
	 	 <input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	
		 <input type='hidden' id="allPayAmt" name='requestHeader.allPayAmt' 	value='${requestHeader.allPayAmt }' />
		 <input type='hidden' id="maxPayItem" name='requestHeader.maxPayItem' value='${requestHeader.maxPayItem }' />		 
			<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requestHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">外部订货方：</td>
				<td class="text_left">
					<span>${requestHeader.buyerName }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">订货地址：</td>
				<td class="text_left">
					<span>${requestHeader.buyerAddress }</span>
				</td>
				
				<td class="label_right">到货日期：</td>
				<td class="text_left">
				<span>${receiveTime }</span>
				<input type="hidden" id="receiveTime" name="requestHeader.receiveTime" value='${receiveTime }' />
				</td>
			</tr>
			
			<tr>
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
				<td class="label_right" style="width: 120px;">审核人：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requestHeader.auditor }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">审核日期：</td>
				<td class="text_left">
				<span>${auditTime }</span>
					<input type="hidden" id="auditTime" name="requestHeader.auditTime" value="${auditTime}" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="requestHeader.formNote" value="${requestHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
			
			<tr >
				<td  colspan="5">
				    <input type="button" onclick="doAudit();" id="btn_submit" value="审核确认" style="margin-left: 50px;" />
				    <input type="button" onclick="doClose();" value="取消" style="margin-left: 20px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid" class="outerGrid" ></div>
	</form>
		
</body>

</html>
