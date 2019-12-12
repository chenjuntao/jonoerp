<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Logistics Center estimate</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/handsontable/jquery.min.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/jquery/isloading/jquery.isloading.min.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/handsontable/handsontable.full.min.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/jquery/common.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/handsontable/estimate.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
    
	<link rel="stylesheet" href="<%=dojoBase %>/handsontable/handsontable.full.min.css">
	<link rel="stylesheet" href="<%=dojoBase %>/jquery/isloading/isloading.css">
	<link rel="stylesheet" href="<%=dojoBase %>/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/estimate.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var g_ids = '${ids }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 90px;">营业日期：</td>
				<td class="text_left" style="width: 240px;">
					<span id="formTimeText">${businessDate }</span>
					<input type="hidden" name="businessDate" value='${businessDate }' />
				</td>
				<td class="label_right" style="width: 90px;">参考日期区间1：</td>
				<td class="text_left">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart1" value="${refDateStart1}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd1" value="${refDateEnd1}" />
				</td>
			</tr>
			<tr>
				<td class="label_right">参考日期区间2：</td>
				<td class="text_left">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart2" value="${refDateStart2}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd2" value="${refDateEnd2}" />
				</td>
				<td class="label_right">参考日期区间3：</td>
				<td class="text_left">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart3" value="${refDateStart3}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd3" value="${refDateEnd3}" />
				</td>
			</tr>
			<tr>
				<td class="text_left" colspan="4">
					<input type="button" onclick="calcSuggest();" value="计算建议值" style="margin-left: 58px;">
				    <input type="button" id="btn_submit" value="生成采购单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="handsontable htColumnHeaders"></div>
		
	  	<input type="hidden" id="ids" name="ids" value='${ids }' />
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	</form>
</body>

</html>
