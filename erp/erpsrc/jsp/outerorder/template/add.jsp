<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/outerorder/template/add.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/common/template/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var templateType = '${templateType }';
		var lcCode = '${lcCode }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="templateForm" method="post" >
		<input type="hidden" id="regionId" value='r03' /><!-- 外部订货方 -->
		<input type="hidden" id="deliveryType" value='UNIFIED' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 90px;">到货周期：</td>
				<td class="text_left" style="width: 60px;">
					<s:select list="periodLst" listKey="key" listValue="value"	 theme="simple" id="arrivePeriod" ></s:select>
				</td>
				
				<td class="label_right" style="width: 60px;">模板名称：</td>
				<td class="text_left" style="width: 120px;">
	 				<input type="text" id="templateName" name="templateMeta.templateName" value="${template.templateName }" style="width: 150px;" />
				</td>
				
					<td class="label_right" style="width: 90px;">按物料编码排序:</td>
				<td class="text_left" ><input name="isSord" id="isSord"
					type="checkbox"
					style="vertical-align: middle; margin: 0px; padding: 0px;" /></td>
			</tr>
			
			<tr>
				<td colspan="6">
			   	 	<input type="button" onclick="selMaterial();" value="新增原料" style="margin-left: 8px;" />
			    	<input type="button" onclick="doSave();" value="保存模板" style="margin-left: 8px;" />
			    	<input type="button" onclick="doClose();" value="取消" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 62px;"></div>
	</form>
</body>

</html>
