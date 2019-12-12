<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan purchase bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/poscan/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/poscan/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/manage/export.js?Version=<%=currenttime %>"></script>
	
     <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/in/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/purchase/manage/print.css">
    
    <!-- 引用新增要货单页面create的样式 -->
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/summary/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var deliveryType = '${deliveryType}';
		var receiverId = '${purchasingHeader.receiverId}';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<div id="customDialog"  data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'
		style="display: none; position: absolute;">
		<table>
			<tr>
				<td><label for="type">导出格式: </label></td>
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			
			<tr><td colSpan=2><label for="columns">选择列项: </label></td></tr>
			<tr><td colSpan=2><table id="columnSelection"></table></td></tr>
			
			<tr><td colspan="2" style="text-align:right;">
				<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
				<button id="print" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customPreview'>打印预览</button>
<!-- 				<button id="print_design" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customPrintDesign'>打印设计</button> -->
				<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button></td>
			</tr>
		</table>
	</div>
	
	<form id="billForm" method="post">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="10">
					<div style="padding-left: 8px;">
						<b>采购单信息</b>
						<span style="padding-left: 50px;" id="flag">${flag}</span>
					<input type="button" onclick="doRefresh();" value="刷    新" style="margin-left: 50px;width: 60px">
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" >单据编号：</td>
				<td class="text_left"  colspan="9">
					<span>${purchasingHeader.formId }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">供应商：</td>
				<td class="text_left">
					<span id="l_provider">${purchasingHeader.provider}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">订货部门：</td>
				<td class="text_left">
					<span id="l_requester">${purchasingHeader.requester}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">收货地址：</td>
				<td class="text_left">
					<span>${purchasingHeader.receiveAddress}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">审核人员：</td>
				<td class="text_left">
					<span>${purchasingHeader.auditor}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">审核日期：</td>
				<td class="text_left">
					<span id="l_auditTime">${auditTime}</span>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">收货仓库：</td>
				<td class="text_left">
					<span>${purchasingHeader.storage}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">到货日期：</td>
				<td class="text_left">
					<spand id="l_receiveTime">${receiveTime}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">配送方式：</td>
				<td class="text_left">
				<span id="deliveryType"></span>
				</td>
				
				<td class="label_right" style="width: 100px;">制单人员：</td>
				<td class="text_left">
					<span>${purchasingHeader.formMaker}</span>
				</td>
				
				<td class="label_right" style="width: 100px;">制单日期：</td>
				<td class="text_left">
					<span id="l_formTime">${formTime}</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left"  colspan="5">
					<span>${purchasingHeader.formNote}</span>
				</td>
				<td  colspan="2">
						<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: showDialog'>导出/打印预览</button>
						<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: customPrint'>直接打印</button>
				</td>
				<td class="label_right">打印次数：</td>
				<td class="text_left" colspan="1">
					<span id="l_times"></span>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="scanGrid">
		<div id="grid_print" class="print-only"></div>
		</div>
		
	</form>
		
	<p class="area_blank">&nbsp;</p>
</body>

</html>
