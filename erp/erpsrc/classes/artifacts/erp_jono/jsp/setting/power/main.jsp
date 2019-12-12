<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
 	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
  	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/power/main.css"/>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/power/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/power/export.js?Version=<%=currenttime %>"></script>
	
</head>

<body bgcolor="white" class="claro">

	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出"'>
			<table style="position:relative;">
				<tr>
					<td>
						<label for="type">导出格式: </label>
					</td>
					
					<td>
						<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;"name="typeSelection" required="true">
							<option value="excel">excel</option>
							<option value="csv">csv</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" style="text-align:right;">
						<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
						<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button>
					</td>
				</tr>
			</table>
	</div>
	
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr valign="middle">
				<td>
					<div id="placeHolder"></div>
		   	 		<button style="margin-left: 50px" data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: showDialog'>导出</button>
		 		</td>
			</tr>
		</table>
	</form>
	
	<p class="area_blank">&nbsp;</p>
 	
	<div id="dataGrid"></div>
</body>

</html>
