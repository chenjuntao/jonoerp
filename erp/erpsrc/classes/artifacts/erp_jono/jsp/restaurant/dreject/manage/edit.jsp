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
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/dreject/manage/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/dreject/create/edit.css">
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='returnHeader.formId' value='${returnHeader.formId }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">退货单据编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${returnHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 100px;">配送单编号：</td>
				<td class="text_left" colspan="3">
					<span>${shippingHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">配送部门：</td>
				<td class="text_left">
					<span>${shippingHeader.providerId }</span>
				</td>
				
				<td class="label_right">配送日期：</td>
				<td class="text_left" colspan="3">
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">入库部门：</td>
				<td class="text_left">
				</td>
				
				<td class="label_right">入库地址：</td>
				<td class="text_left" colspan="3">
				</td>
			</tr>
			
			<tr>
				<td class="label_right">入库人员：</td>
				<td class="text_left">
					<span>${shippingHeader.inputer }</span>
				</td>
				
				<td class="label_right">入库日期：</td>
				<td class="text_left" colspan="3">
					<span>${inputTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货人员：</td>
				<td class="text_left">					
					<span>${returnHeader.returner }</span>
				</td>
				
				<td class="label_right">退货日期：</td>
				<td class="text_left" colspan="3">
					<span>${returnTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货审核人员：</td>
				<td class="text_left">					
				</td>
				
				<td class="label_right">退货审核日期：</td>
				<td class="text_left" colspan="3">
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货总金额：</td>
				<td><span id="sumMoney"></span></td>
				
				<td class="label_right">退货单备注：</td>
				<td class="text_left" colspan="4">
	  				<input type="text" name="returnHeader.formNote" value="${returnHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  	
		<div id="dataGrid" class="commonGrid" style="top:212px;bottom:33px">
		</div>
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
