<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>purchase bill query</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/manage/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/manage/tip.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/manage/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/manage/export.js?Version=<%=currenttime %>"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/purchase/manage/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/in/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/purchase/manage/print.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<script type="text/javascript">
		var formId = '${requestHeader.formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<!-- 导出 打印对话框 -->
	<div id="customDialog"  data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'
		style="display: none; position: absolute;">
		<table>
			<tr>
				<td><label for="type">导出格式: </label></td>
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
						<option value="pdf">pdf</option>
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


	<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
	 <input type="hidden" id="downloadTokenValue" name="downloadTokenValue"/>
	
	<div id="treeWrapper"></div>
		
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="text_left" style="width: 60px;">
					制单日期：
				</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate" value="${startDate}"	style="width: 110px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" value="${endDate}" style="width: 110px;" onFocus="WdatePicker()" />
			   	 	<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 58px;width: 60px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="billInfo">
			<input type='hidden' id="formId" value='' />
			<input type='hidden' id="deliveryType" value='' />
			
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td class="label_right" style="width: 80px;">单据编号：</td>
					<td class="text_left" style="width: 180px;">
						<span id="l_formId">${requestHeader.formId }</span>
						<input type='hidden' id="l_status"/>
						<span style="margin-left: 10px" ><b style='border: 1px green solid;' id="statusSign"></b></span>
					</td>
					
					<td class="label_right" style="width: 78px;">要货单号：</td>
					<td class="text_left" >
						<textarea id="l_formRefId" rows="2" cols="35" readonly="readonly" >
						</textarea>
					</td>
					
					<td class="label_right" style="width: 70px;">供应商：</td>
					<td class="text_left" style="width: 150px;">
						<span id="l_provider"></span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">订货部门：</td>
					<td class="text_left">
						<span id="l_requester"></span>
					</td>
					
					<td class="label_right">收货地址：</td>
					<td class="text_left" colspan="1">
						<span id="l_receiveAddress"></span>
					</td>
					
					<td class="label_right" style="width: 70px;">到货日期：</td>
					<td class="text_left">
						<span id="l_receiveTime">${receiveTime }</span>
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
						<span id="l_auditor"></span>
					</td>
					
					<td class="label_right">审核日期：</td>
					<td class="text_left" >
						<span id="l_auditTime"></span>
					</td>
					<td class="label_right">操作时间：</td>
					<td class="text_left">
						<span id="l_formTimeActual"></span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">备注：</td>
					<td class="text_left" colspan="1">
						<span id="l_formNote">${requestHeader.formNote }</span>
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
			
			<div id="dataGrid" style="top:180px"></div>
			
			<div id="grid_print" class="print-only"></div>

			<table id="commandTable" class="hovertable">
				<tr>
					<td class="text_left" colspan="4" style="text-align: center;">
					    <input type="button" value="管    理" onclick="manage();" style="width: 60px;"  />
				       <input type="button" value="采购单结束" onclick="doFinish();"  style="margin-left: 20px"/>
					</td>
				</tr>
			</table>			
		</div>
	</form>
</body>

</html>
