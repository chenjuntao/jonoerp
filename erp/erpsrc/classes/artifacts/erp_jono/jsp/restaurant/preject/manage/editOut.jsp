<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/manage/editOut.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/dreject/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='returnHeader.formId'	 value='${returnHeader.formId }' />
		<input type='hidden' name='returnHeader.formRefId' value='${shippingHeader.formId }' />
		<input type='hidden' name='returnHeader.returnerId' value='${returnHeader.returnerId }' />
		<input type='hidden' name='returnHeader.returner' value='${returnHeader.returner }' />
		<input type='hidden' name='returnHeader.returnTime' value='${returnTime }' />
		<input type='hidden' name='returnHeader.formNote' value='${returnHeader.formNote }' />
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">退货单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${returnHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">出货单编号：</td>
				<td class="text_left" colspan="5">
					<span>${shippingHeader.formId}</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">出货部门：</td>
				<td class="text_left" style="width: 100px;">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">出货日期：</td>
				<td class="text_left" style="width: 100px;">
					<span>${receiveTime }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">订货部门：</td>
				<td class="text_left" style="width: 160px;">
					<span>${shippingHeader.requester}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">订货地址：</td>
				<td class="text_left">
					<span>${shippingHeader.requestAddress }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货人员：</td>
				<td class="text_left">					
					<span>${returnHeader.returner }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">退货日期：</td>
				<td class="text_left" style="width: 100px;">
					<span>${returnTime }</span>
				</td>
				<td class="label_right">退货审核人员：</td>
				<td class="text_left">					
<%-- 					<span>${antiauditHeader.antiauditor }</span> --%>
				</td>
				
				<td class="label_right">退货审核日期：</td>
				<td class="text_left" colspan="3">
<%-- 					<span>${antiauditTime }</span> --%>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货单备注：</td>
				<td class="text_left" colspan="7">
	  				<input type="text" name="returnHeader.formNote" value="${returnHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid" class="auditGrid4" ></div>
	</form>	
		
	<p class="area_blank">&nbsp;</p>
	
<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doDelete();" value="删除单据" style="margin-left: 8px;" />
			    <input type="button" onclick="doInvalid('${formId }', 'doQuery');" value="作废单据" style="margin-left: 8px;" />
			    <input type="button" onclick="doSave();" value="确认修改" style="margin-left: 8px;" />
			    <input type="button" onclick="doClose();" value="取消修改" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
