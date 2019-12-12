<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>manual stock in</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/in/manual/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/purchase/dlg.js?Version=<%=currenttime %>"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/in/manual/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<script type="text/javascript">
		var g_adjustType = '${adjustType }';
		var branchType ="S_Center";
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
			<td class="label_right" style="width: 80px;">供应商：</td>
				<td class="text_left" colspan="3">
					<input type='hidden' id="supplierId" name='inputHeader.providerId' value='' />
					<input type='hidden' id="supplier" name='inputHeader.provider' value='' />
					<input type='hidden' id="branchType" value='' />
					<span id="supplierText" style="display: inline-block;"></span>
					<input type="button" onclick="selSupplier()" value="选择供应商" />
		 		</td>
		 		
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="inputHeader.formNote" value="" style="width: 380px;" />
	  				
			    	<input type="button" onclick="selMaterial();" value="自选原料" style="margin-left: 58px;" />
				    <input type="button" onclick="doSubmit()" value="生成入库单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 32px;"></div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='' />
	</form>
</body>

</html>
