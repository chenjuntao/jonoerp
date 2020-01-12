<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>item supplier config</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/supplier/edit/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/supplier/edit/dlg.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/item/supplier/edit/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<table id="dataForm" class="hovertable" width="100%">
		<tr>
			<td class="label_right" style="width: 80px;">供应商：</td>
			<td class="text_left">
				<input type='hidden' id="supplierId" value='' />
				<input type='hidden' id="supplierType" value='' />
				<span id="supplierText" style="display: inline-block;"></span>
				<input type="button" onclick="selSupplier()" value="选择供应商" />
	 		</td>
	 	</tr>
	 	
	 	<tr>
			<td class="label_right">门店：</td>
			<td class="text_left">
				<input type='hidden' id="branchId" value='' />
				<input type='hidden' id="branchTypes" value='' />
				<span id="branchText" style="display: inline-block; height: 58px;">&nbsp;</span>
			</td>
	 	</tr>
	 	
	 	<tr>
			<td class="text_left" colspan="2">		
				<input type="button" onclick="selBranch()" value="选择门店" style="margin-left: 58px;" />
				<input type="button" onclick="selMaterial()" value="选择原料" style="margin-left: 8px;" />
		   	 	<input type="button" onclick="delMaterial();" value="删除原料" style="margin-left: 8px;" />
				<input type="button" onclick="doSave()" value="保存" style="margin-left: 8px;" />
	 		</td>
		</tr>
	</table>
		
	<div id="dataGrid"></div>
</body>

</html>
