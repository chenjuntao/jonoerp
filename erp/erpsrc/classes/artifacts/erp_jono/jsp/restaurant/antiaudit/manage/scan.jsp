<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/antiaudit/manage/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>	
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/antiaudit/manage/print.js?Version=<%=currenttime %>"></script>
	
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/manage/print.css"/>
	
	<script type="text/javascript">
// 		var currenttime = new Date();
// var currenttime = 1;
		var formRefId = '${formRefId }';
		var sformId = '${shippingHeader.formId }';
		var provider = '${shippingHeader.provider }';
		var outStorage = '${shippingHeader.outStorage }';
		var requester = '${shippingHeader.requester }';
		var inStorage = '${shippingHeader.inStorage }';
		var requestAddress = '${shippingHeader.requestAddress }';
		var formMaker = '${shippingHeader.formMaker }';
		var formTime = '${formTime }';
		var receiveTime = '${receiveTime }';
		var auditor = '${shippingHeader.auditor }';
		var auditTime = '${auditTime }';
		var inputer = '${shippingHeader.inputer }';
		var sformNote = '${shippingHeader.formNote }';
		var antiauditor = '${antiauditHeader.antiauditor }';
		var antiauditBranch = '${antiauditHeader.antiauditBranch }';
		var antiauditTime = '${antiauditTime}';
		var formNote = '${antiauditHeader.formNote }';
		var inputTime = '${inputTime}'
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
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
					
					<td class="label_right" style="width: 120px;">出库仓库：</td>
					<td class="text_left" colspan="2">
						<span>${shippingHeader.outStorage }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right" style="width: 120px;">订货部门：</td>
					<td class="text_left">
						<span>${shippingHeader.requester }</span>
					</td>
					
					<td class="label_right" style="width: 120px;">入库仓库：</td>
					<td class="text_left">
						<span>${shippingHeader.inStorage }</span>
					</td>
					
					<td class="label_right">订货地址：</td>
					<td class="text_left" colspan="2">
						<span>${shippingHeader.requestAddress }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">制单人员：</td>
					<td class="text_left">
						<span>${shippingHeader.formMaker }</span>
					</td>
					
					<td class="label_right">制单日期：</td>
					<td class="text_left" >
						<span>${formTime }</span>
					</td>
					
					<td class="label_right" style="width: 90px;">配送日期：</td>
					<td class="text_left" colspan="2">
						<span>${receiveTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">审核人员：</td>
					<td class="text_left">
						<span>${shippingHeader.auditor }</span>
					</td>
					
					<td class="label_right">审核日期：</td>
					<td class="text_left" colspan="4">
						<span>${auditTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">入库人员：</td>
					<td class="text_left">
						<span>${shippingHeader.inputer }</span>
					</td>
					
					<td class="label_right">入库日期：</td>
					<td class="text_left" colspan="4">
						<span>${inputTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">配送单备注：</td>
					<td class="text_left" colspan="6">
						<span id="l_formNote">${shippingHeader.formNote }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">反审核人员：</td>
					<td class="text_left">					
						<span>${antiauditHeader.antiauditor }</span>
					</td>
					
					<td class="label_right">反审核部门：</td>
					<td class="text_left">
						<span>${antiauditHeader.antiauditBranch }</span>
					</td>
					
					<td class="label_right">反审核日期：</td>
					<td class="text_left" colspan="2">
						<span>${antiauditTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">反审核备注：</td>
					<td class="text_left" colspan="5">
						<span id="l_formNote">${antiauditHeader.formNote }</span>
					</td>
					<td><input type="button" value="打印预览" onclick="prn1_preview();"/></td>
	<!-- 				<td width=40px><input type="button" value="打印设计" onclick="myDesign();" /></td> -->
				</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top: 242px;"></div>
		<div id="grid_print" class="print-only"></div>
	</form>	
</body>

</html>
