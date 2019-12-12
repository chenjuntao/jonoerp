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
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/statementmanage/operate.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">	
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
	<link rel="stylesheet" href="<%=appRoot %>/jsp/supplier/statementmanage/scan.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var operate = '${operate }';
		var status = '${status }';
	</script>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		
		<table class="hovertable" width="100%" border="1">
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
					<span  id="allPayAmt">${statementHead.allPayAmt}</span>
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
				<td  colspan="3" >
					<span>${statementHead.formNote}</span>
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
