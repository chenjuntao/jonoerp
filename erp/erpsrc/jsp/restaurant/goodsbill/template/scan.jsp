<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan request template</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/template/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/goodsbill/template/exportDetail.js"></script>
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
		var templateId = '${templateId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<!-- 导出 打印对话框 -->
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
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
	
	<form id="templateForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">模板编号：</td>
				<td class="text_left" style="width: 180px;">
					<span id="templateId">${templateMeta.templateId }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">模板名称：</td>
				<td class="text_left">
					<span>${templateMeta.templateName }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">模板要货类别：</td>
				<td class="text_left" colspan="3">
					<span>${categoryName }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">门店分组：</td>
				<td class="text_left">
					<span>${regionName }</span>
				</td>
				
				<td class="label_right">到货周期：</td>
				<td class="text_left">
					<span>${templateMeta.arrivePeriod }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">操作：</td>
				<td colspan="3">
					<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: showDialog'>导出明细</button>
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 50px;" data-dojo-props='onClick: doClose'>关闭页面</button>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 120px;"></div>
	</form>
</body>

</html>
