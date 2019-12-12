<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create manual purchase bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
<%-- 	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script> --%>
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/production/output/manual/main.js?Version=<%=currenttime %>"></script>
		<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/purchase/dlg.js"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/manual/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<script type="text/javascript">
		var g_adjustType = '${adjustType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		<input type="hidden" id="jsonData" name="jsonData" value='' />
	   <input type="hidden" id="branchType" name="branchType" value="CENTRALFACTORY" />
	   	<input type="hidden" id="parentTabId" name="parentTabId" value="${tabId}" />
		<table class="hovertable" width="100%" border="1">
		<tr>
		<td class="label_right" style="width: 162px;">制单人员：</td>
				<td class="text_left" style="width: 100px;">
					<span>${inputHeader.inputer }</span>
					<input type="hidden"  name="inputHeader.inputer" value='${inputHeader.inputer }' />
					<input type="hidden"  name="inputHeader.inputerId" value='${inputHeader.inputerId }' />
				    <input type='hidden' id="supplierId" value='${supplierId }' />
				</td>
				<td class="label_right" style="width: 162px;">制单日期：</td>
			   <td class="text_left" >
					<span>${formTime }</span>
					<input type="hidden" id="formTime" name="inputHeader.inputTime" value='${formTime }' />
				</td>
		</tr>
		
			<tr>
			<td class="label_right">备注：</td>
				<td class="text_left" >
	  				<input type="text" name="inputHeader.formNote" value="" style="width: 380px;" />
		 		</td>
				<td class="text_left" colspan="2">
			    	<input type="button" onclick="selMaterial();" value="自选半成品" style="margin-left: 58px;" />
				    <input type="button" onclick="doSubmit();" value="生成入库单" style="margin-left:20px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"></div>
	</form>
</body>

</html>
