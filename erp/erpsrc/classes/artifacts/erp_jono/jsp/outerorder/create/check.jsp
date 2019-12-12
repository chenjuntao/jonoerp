<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>check page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/outerorder/create/check.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/outerorder/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' name='requestHeader.formId' value='${requestHeader.formId }' />
		<input type='hidden' name='requestHeader.formType' value='outer' />
		<input type='hidden' name='requestHeader.buyerId' value='${requestHeader.buyerId }' />
		<input type='hidden' name='requestHeader.buyerName' value='${requestHeader.buyerName }' />
		<input type='hidden' name='requestHeader.buyerAddress' value='${requestHeader.buyerAddress }' />
		<input type='hidden' name='requestHeader.arrivePeriod' value='${requestHeader.arrivePeriod }' />
		<input type='hidden' name='requestHeader.receiveTime' value='${receiveTime }' />
		<input type='hidden' name='requestHeader.formMaker' value='${requestHeader.formMaker }' />
		<input type='hidden' name='requestHeader.formTime' value='${formTime }' />
		<input type='hidden' name='requestHeader.formNote' value='${requestHeader.formNote }' />
		<s:token/>
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 90px;">外部订货方：</td>
				<td class="text_left" style="width: 150px;">
					<span>${requestHeader.buyerName }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">订货地址：</td>
				<td class="text_left">
					<span>${requestHeader.buyerAddress }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">到货日期：</td>
				<td class="text_left" colspan="3">
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
					<span style="width: 280px;display: inline-block;">${requestHeader.formNote }</span>
					
			    	<input type="button" id="btn_submit" value="确定提交订货单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	
		<div id="dataGrid" class="checkGrid" style="top: 122px;"></div>
	</form>	
</body>

</html>
