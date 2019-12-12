<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create distribution reject bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/dreject/create/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/dreject/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name="formId" value='${formId }' />
		<input type='hidden' name='returnHeader.antiauditorId' value='${returnHeader.antiauditorId }' />
		<input type='hidden' name='returnHeader.antiauditor' value='${returnHeader.antiauditor }' />
		<input type='hidden' name='returnHeader.antiauditBranchId' value='${returnHeader.antiauditBranchId }' />
		<input type='hidden' name='returnHeader.antiauditBranch' value='${returnHeader.antiauditBranch }' />
		<input type='hidden' name='returnHeader.antiauditTime' value='${antiauditTime }' />
		
	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">配送单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${shippingHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">配送部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">配送日期：</td>
				<td class="text_left">
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
				<td class="label_right">退货单备注：</td>
				<td class="text_left" colspan="4">
	  				<input type="text" name="returnHeader.formNote" 
	  					value="${returnHeader.formNote}" style="width: 380px;" />
				</td>
				
				<td>
					退货总金额：
					<span id="sumMoney">0</span>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top: 125px;bottom:32px"></div>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	
	<table id="commandTable" class="hovertable">
		<tr>
			<td class="text_left" colspan="4" style="text-align: center;">
			    <input type="button" value="生成配送退货单" onclick="doSubmit();" />
			</td>
		</tr>
	</table>
</body>

</html>
