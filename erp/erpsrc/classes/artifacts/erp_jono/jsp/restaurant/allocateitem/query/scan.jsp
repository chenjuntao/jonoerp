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
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/allocateitem/query/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/LodopFuncs.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/allocateitem/query/print.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/allocateitem/query/exportDetail.js"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
    <link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/allocateitem/query/print.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/allocateitem/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var fId = '${transferHeader.formId }';
		var formNote = '${transferHeader.formNote }';
		var inBranch = '${transferHeader.inBranch }';
		var inStorage = '${transferHeader.inStorage }';
		var confirmer = '${transferHeader.confirmer }';
		var cTime = '${confirmTime}';
		var outBranch = '${transferHeader.outBranch }';
		var outStorage = '${transferHeader.outStorage }';
		var fromMaker = '${transferHeader.fromMaker}';
		var formTime = '${formTime }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<!-- 导出 打印对话框 -->
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection" data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:right;">
					<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
					<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button>
				</td>
			</tr>
		</table>
	</div>
	
	<form id="billForm" method="post" >
		<input type='hidden' name='transferHeader.formId' value='${transferHeader.formId }' />
			 
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>调拨单信息</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 120px;" colspan="3">
					<span>${transferHeader.formId }</span>
				</td>
				
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<span>${transferHeader.formNote }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">调入部门：</td>
				<td class="text_left">
					<span>${transferHeader.inBranch }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">调入仓库：</td>
				<td class="text_left">
					<span>${transferHeader.inStorage }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">确认人员：</td>
				<td class="text_left" style="width: 120px;">
					<span>${transferHeader.confirmer }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">确认日期：</td>
				<td class="text_left">
					<span>${confirmTime}</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">调出部门：</td>
				<td class="text_left">
					<span>${transferHeader.outBranch }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">调出仓库：</td>
				<td class="text_left">
					<span>${transferHeader.outStorage }</span>
				</td>
				
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${transferHeader.fromMaker}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td >操作：</td>
				<td colspan="7">
					<button data-dojo-type="dijit/form/Button" data-dojo-props='onClick: showDialog'>导出明细</button>
					<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: prn1_preview'>打印预览</button>
					<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doClose'>关闭页面</button>
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  	
		<div id="scanGrid" class="commonGrid" style="top:152px"></div>
		
		<div id="grid_print" class="print-only"></div>
	</form>
		
	<p class="area_blank">&nbsp;</p>
</body>

</html>
