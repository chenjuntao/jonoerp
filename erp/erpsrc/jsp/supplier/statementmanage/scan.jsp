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
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/statementmanage/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/statementmanage/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">	
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
	<link rel="stylesheet" href="<%=appRoot %>/jsp/supplier/statementmanage/scan.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
  	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/supplier/statementmanage/main.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var operate = '${operate }';
		var status = '${status }';
	</script>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

		<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出"'>
			<table style="position:relative;">
				<tr>
					<td>
						<label for="type">导出格式: </label>
					</td>
					
					<td>
						<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;"name="typeSelection" required="true">
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
		
		<table class="hovertable" width="100%" border="1">
			<input type='hidden' name='provider' id='provider' value='${statementHead.provider}' />
			<input type='hidden' name='branchName' id='branchName'  value='${statementHead.branchName}' />
			<tr>
				<td class="label_right" style="width: 80px;">对账单编号：</td>
				<td class="text_left" style="width: 100px;">
					<span>${statementHead.formId}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">对账开始时间：</td>
				<td class="text_left" style="width: 100px;">
					<span>${startDate}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">对账结束时间：</td>
				<td class="text_left" style="width: 100px;">
					<span>${endDate}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">总额：</td>
				<td class="text_left" style="width: 100px;">
					<span>${statementHead.allPayAmt}</span>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 80px;">供应商：</td>
				<td class="text_left" style="width: 100px;">
					<span>${statementHead.provider}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">门店：</td>
				<td class="text_left" style="width: 100px;">
					<span>${statementHead.branchName}</span>
				</td>
				
				<td class="label_right" style="width: 50px;">备注：</td>
				<td  colspan="2" >
					<span>${statementHead.formNote}</span>
				</td>
				
				<td>
<!-- 					<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: showDialog'>导出</button> -->
					<input id="exportBtn" type="button" onclick="showDialog()" value="导出对账单" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid">
		</div>
		
	</form>
		
	<p class="area_blank">&nbsp;</p>
</body>

</html>
