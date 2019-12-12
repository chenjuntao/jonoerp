<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create distribution bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/distribution/create.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/distribution/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formRefId = '${formRefId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' name='formRefId' value='${formRefId }' />
		<input type='hidden' name='shippingHeader.requesterId' value='${shippingHeader.requesterId }' />
		<input type='hidden' name='shippingHeader.requester' value='${shippingHeader.requester }' />
		<input type='hidden' name='shippingHeader.requestAddress' value='${shippingHeader.requestAddress }' />
		<input type='hidden' name='shippingHeader.providerId' value='${shippingHeader.providerId }' />
		<input type='hidden' name='shippingHeader.provider' value='${shippingHeader.provider }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">原始单据编号：</td>
				<td class="text_left" colspan="5">
					<span>${formRefId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 100px;">订货部门：</td>
				<td class="text_left" style="width: 150px;">
					<span>${shippingHeader.requester }</span>
				</td>
				<td class="label_right" style="width: 90px;">订货地址：</td>
				<td class="text_left" colspan="3">
					<span>${shippingHeader.requestAddress }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">订货人员：</td>
				<td class="text_left">
				</td>
				<td class="label_right">订货日期：</td>
				<td class="text_left" colspan="3">
				</td>
			</tr>
			
			<tr>
				<td class="label_right">配送部门：</td>
				<td class="text_left">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right">配送日期：</td>
				<td class="text_left" style="width: 120px;">
					<input type="text" id="receiveTime" class="Wdate" name="shippingHeader.receiveTime" value="${receiveTime}" />
				</td>
				
				<td class="label_right" style="width: 90px;">出库仓库：</td>
				<td class="text_left">
					<s:select list="storeLst" listKey="storageId" listValue="storageName" theme="simple"
						style="width: 180px;" id="storageId" name="shippingHeader.outStorageId"></s:select>
					<input type="hidden" id="storage" name="shippingHeader.outStorage" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="shippingHeader.formNote" value="${shippingHeader.formNote}" style="width: 380px;" />
	  				
				    <input type="button" id="btn_submit" value="提交配送单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<table class="hovertable" width="100%">
			<tr>
				<td colspan="4">
					<b style="padding-left: 8px;">统配原料</b>
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="commonGrid" style="top:185px"></div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	</form>
</body>

</html>
