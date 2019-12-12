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
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/orderhandle/operate.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/summary/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var status = '${status }';
		var operate = '${operate }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		
		<input type='hidden' id="formId" name='requestHeader.formId'
			 value='${requestHeader.formId }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px; " colspan="7">
					<span>${requestHeader.formId }</span>
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
				</td>
				<td class="label_right" style="width: 120px;">外部订货方：</td>
				<td class="text_left" colspan="3">
					<span>${requestHeader.buyerName }</span>
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
				<td class="label_right" style="width: 120px;">审核人：</td>
				<td class="text_left" style="width: 180px;">
					<span>${requestHeader.auditor }</span>
				</td>
				<td class="label_right" style="width: 120px;">审核日期：</td>
				<td class="text_left">
						<span>${auditTime}</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="7">
	  				<span>${requestHeader.formNote }</span>
	  				<input type="button" id="operateButton"  onclick="doOperate();"  style="margin-left: 200px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<div id="dataGrid" style="height: 200px;">
		</div>
		
		
		<table class="hovertable" width="100%" border="1">
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
</body>

</html>
