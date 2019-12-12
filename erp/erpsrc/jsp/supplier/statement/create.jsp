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
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/statement/create.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">	
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
	<link rel="stylesheet" href="<%=appRoot %>/jsp/supplier/statement/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<input type="hidden" name="startDate" value="${startDate}"></input>
		<input type="hidden" name="endDate" value="${endDate}"></input>
		<input type="hidden" name="supplierId" value="${supplierId}"></input>
		<input type="hidden" name="branchId" value="${branchId}"></input>
	</form>
	
	<form id="billForm" method="post" >
		
		<input id="startDate" type="hidden" name="statementHead.startDate" value="${startDate}"></input>
		<input id="endDate" type="hidden" name="statementHead.endDate" value="${endDate}"></input>
		<input id="supplierId" type="hidden" name="supplierId" value="${supplierId}"></input>
		<input id="branchId" type="hidden" name="branchId" value="${branchId}"></input>
		<input id="ids" type="hidden" name="ids" value="${ids}"></input>
		<input id="allPayAmt" type="hidden" name="statementHead.allPayAmt" value="${allPayAmt}"></input>
		<input id="providerId" type="hidden" name="statementHead.providerId" value="${supplierId}"></input>
		<input id="branchId" type="hidden" name="statementHead.branchId" value="${branchId}"></input>
		<input type="hidden" name="formType" value="G"></input>
		<input type="hidden" id="jsonData" name="jsonData" ></input>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="10">
					<div style="padding-left: 8px;">
						<b>对账单</b>
						<span style="padding-left: 500px;" id="flag"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 80px;">对账开始时间：</td>
				<td class="text_left" style="width: 80px;">
					<span>${startDate}</span>
				</td>
				
				<td class="label_right" style="width: 80px;">对账结束时间：</td>
				<td class="text_left" style="width: 100px;">
					<span>${endDate}</span>
				</td>
				
				<td class="label_right" style="width: 50px;">总额：</td>
				<td class="text_left" style="width: 50px;">
					<span>${allPayAmt}</span>
				</td>
				
				<td class="label_right" style="width: 50px;">备注：</td>
				<td  style="width: 150px;" >
					<input type="text" id="formNote" name="statementHead.formNote"/>
				</td>
				
				<td  style="width: 200px;" >
					<input type="button"  onclick="createBill()" value="确认生成对账单"/>
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
