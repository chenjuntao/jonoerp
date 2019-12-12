<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/create/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
		<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">制单日期：</td>
				<td class="text_left" style="width: 180px;">
					<span>${formTime }</span>
					<input type="hidden" id="formTime" name="requestHeader.formTime" value='${formTime }' />
				</td>
				<td class="label_right" style="width: 60px;">到货日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" style="width: 100px;" id="receiveTime" name="requestHeader.receiveTime" value="" />
				</td>
			</tr>
		</table>
		<table class="hovertable" width="100%">
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="requestHeader.formNote" value="${requestHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
			<tr>
				<td colspan="6">
			   	 	<input type="button" id="useHistoryModel" onclick="pickModel();" value="使用模板" style="margin-left: 8px;" />
					<input type="button" onclick="delMaterial();" value="删除原料" style="margin-left: 10px;">
				    <input type="button" id="btn_submit" value="提交原料采购单" style="margin-left: 10px;" />
				</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="createGrid" style="top: 92px;">
		</div>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	</form>
</body>

</html>
