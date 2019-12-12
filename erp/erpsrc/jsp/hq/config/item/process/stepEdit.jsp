<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit step</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/process/stepEdit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var g_itemId = '${itemId }';
		var oldStep = '${step }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post">
		<input type='hidden' name='makingProcess.itemId' value='${itemId}' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">顺序：</td>
				<td class="text_left">
		 			<input type="text" id="step" name="makingProcess.step"
		 				 value="${makingProcess.step}" style="width: 80px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">具体操作：</td>
				<td class="text_left">
		 			<input type="text" id="stepOperation" name="makingProcess.stepOperation"
		 				 value="${makingProcess.stepOperation}" style="width: 280px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left">
		 			<input type="text" name="makingProcess.stepNote"
		 				 value="${makingProcess.stepNote}" style="width: 280px;" />
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doSave();" value="保存" />
			</td>
		</tr>
	</table>
</body>

</html>
