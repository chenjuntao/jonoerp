<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>add price adjust bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/purchase/add.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/priceadjust/purchase/dlg.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/priceadjust/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var g_adjustType = '${adjustType }';
		var branchType ="S_Center";
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' name='adjustHeader.adjustType'	 value='${adjustType }' />
		<input type='hidden' name='adjustHeader.adjustScope' value="all" />
		<input type='hidden' name='adjustHeader.formTime' value='${formTime }' />
		<input type='hidden' name='adjustHeader.formMaker' value='${loginUsername }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">供应商：</td>
				<td class="text_left" colspan="3">
					<input type='hidden' id="supplierId" name='adjustHeader.supplierId' value='' />
					<input type='hidden' id="supplier" name='adjustHeader.supplier' value='' />
					<input type='hidden' id="branchType" value='' />
					<span id="supplierText" style="display: inline-block;"></span>
					<input type="button" onclick="selSupplier()" value="选择供应商" />
		 		</td>
		 		
				<td class="label_right" style="width: 90px;">生效时间：</td>
				<td class="text_left">
					<input type="text" class="Wdate" name="adjustHeader.effectTime" value="${effectTime}" onFocus="WdatePicker({minDate:'${formTime }'})"/>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left" style="width: 150px;">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right" style="width: 90px;">制单日期：</td>
				<td class="text_left" colspan="3">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="adjustHeader.formNote" value="${adjustHeader.formNote}" style="width: 380px;" />
	  				
			   	 	<input type="button" onclick="addItem();" value="选择商品" style="margin-left: 58px;" />
			   	 	<input type="button" onclick="delItem();" value="删除商品" style="margin-left: 8px;" />
				    <input type="button" onclick="doSave();" value="保存" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  	
		<div id="dataGrid"></div>
	</form>
</body>

</html>
