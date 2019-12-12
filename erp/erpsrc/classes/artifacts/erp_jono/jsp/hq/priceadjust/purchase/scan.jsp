<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan price adjust bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/scan.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/purchase/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/priceadjust/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right">单据编号：</td>
				<td class="text_left" style="width: 200px">
					<span>${adjustHeader.formId }</span>
					<span style="margin-left: 50px"><b style='border: 1px green solid;'>${status }</b></span>
				</td>
				
				<td class="label_right">供应商：</td>
				<td class="text_left">
					<span>${adjustHeader.supplierName }</span>
		 		</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 90px;">调价范围：</td>
				<td class="text_left" style="width: 150px;">
					<span>${adjustHeader.adjustType }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">生效时间：</td>
				<td class="text_left">
					<span>${effectTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${adjustHeader.formMaker}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="adjustHeader.formNote" value="${adjustHeader.formNote}" style="width: 380px;" />
	  				<input type="button" onclick="myExport();" value="导  出" style="margin-left: 58px;width: 60px" />
				</td>
			</tr>
		</table>
	  
		<div id="dataGrid" style="top: 122px;"></div>
	</form>	
</body>

</html>
