<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan shipping bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/shipping/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/shipping/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/inoutquery/shipping/exportDetail.js"></script>
     <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
    <link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/inoutquery/shipping/print.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/inoutquery/shipping/scan.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var fId = '${shippingHeader.formId }';
		var provider = '${shippingHeader.provider }';
		var receiveTime = '${receiveTime }';
		var requester = '${shippingHeader.requester }';
		var requestAddress = '${shippingHeader.requestAddress }';
		var formMaker = '${shippingHeader.formMaker }';
		var auditor = '${shippingHeader.auditor }';
		var auditTime = '${auditTime }';
		var inputer = '${shippingHeader.inputer }';
		var inputTime = '${inputTime }';
		var formNote = '${shippingHeader.formNote }';
		var formTime = '${formTime}';
		
		var formStatus = '${status }';
		
		var addFormFlag = '${addFormFlag }';
		addFormFlag = addFormFlag == 'Y'?"加单":'';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
			<table style="position:relative;">
				<tr><td><label for="type">导出格式: </label></td>
					<td><select id="typeSelection" data-dojo-id="typeSelection" 
							data-dojo-type="dijit/form/Select" style="width: 162px;"
							name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select></td>
				</tr>
				<tr><td colspan="2" style="text-align:right;">
					<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
					<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button></td>
				</tr>
			</table>
	</div>
	
	<form id="billForm" method="post" >
	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<input type='hidden' name='shippingHeader.formId' value='${shippingHeader.formId }' />
			 
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">单据编号：</td>
				<td class="text_left" colspan="7">
					<span>${shippingHeader.formId }</span>
					<span style="margin-left: 50px"><b style='border: 1px green solid;'>${status }</b></span>
					<span style="margin-left: 50px"><b id="addFormFlag" style='border: 1px green solid;'></b></span>
				</td>
				
			</tr>
			
			<tr>
				<td class="label_right">配送部门：</td>
				<td class="text_left">
					<span>${shippingHeader.provider }</span>
				</td>
				
				<td class="label_right">配送日期：</td>
				<td class="text_left">
					<span>${receiveTime }</span>
				</td>
				
				<td class="label_right">订货部门：</td>
				<td class="text_left">
					<span>${shippingHeader.requester}</span>
				</td>
				
				<td class="label_right">订货地址：</td>
				<td class="text_left">
					<span>${shippingHeader.requestAddress }</span>
				</td>
			</tr>
			
			<tr>
				
				<td class="label_right">制单人员：</td>
				<td class="text_left" style="width: 80px;">
					<span>${shippingHeader.formMaker }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">制单日期：</td>
				<td class="text_left" style="width: 150px;">
					<span>${formTime}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">捡货人员：</td>
				<td class="text_left" style="width: 120px;">
					<span>${shippingHeader.auditor }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">捡货日期：</td>
				<td class="text_left">
					<span>${auditTime}</span>
				</td>
				
			</tr>
			
			<tr>
				<td class="label_right" >入库人员：</td>
				<td class="text_left">
					<span>${shippingHeader.inputer }</span>
				</td>
				
				<td class="label_right">入库时间：</td>
				<td class="text_left">
					<span>${inputTime}</span>
				</td>
				
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<span>${shippingHeader.formNote }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">操作：</td>

				<td colspan="7">
					<button data-dojo-type="dijit/form/Button" data-dojo-props='onClick: showDialog'>导出明细</button>
					<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: prn1_preview'>打印预览</button>
					<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doClose'>关闭页面</button>
				</td>
			</tr>
		</table>
	  	
	  	<p class="area_blank">&nbsp;</p>

		<div id="dataGrid" style="top: 152px;"></div>
		<div id="grid_print" class="print-only"></div>
	</form>
</body>

</html>
