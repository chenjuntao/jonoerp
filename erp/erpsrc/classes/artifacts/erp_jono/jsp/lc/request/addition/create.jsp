<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/addition/create.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/addition/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >	
		<input type='hidden' name='requestHeader.formId' value='${requestHeader.formId }' />
		<input type='hidden' name='requestHeader.buyerId' value='${requestHeader.buyerId }' />
		<input type='hidden' name='requestHeader.buyerName' value='${requestHeader.buyerName }' />
		<input type='hidden' name='requestHeader.buyerAddress' value='${requestHeader.buyerAddress }' />
		<input type='hidden' name='requestHeader.deliveryType' value='${requestHeader.deliveryType }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">单据编号：</td>
				<td class="text_left" colspan="3">
					<span>${requestHeader.formId }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left" style="width: 150px;">
					<span>${requestHeader.formMaker }</span>
				</td>
				<td class="label_right" style="width: 60px;">制单日期：</td>
				<td class="text_left" colspan="3">
					<span>${formTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
					<span style="display: inline-block;width: 300px;">${requestHeader.formNote }</span>
	  				
				    <input type="button" id="btn_submit" value="确认生成采购单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 92px;">
		</div>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	</form>
</body>

</html>
