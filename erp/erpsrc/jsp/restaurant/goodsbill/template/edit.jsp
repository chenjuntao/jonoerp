<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>edit request template</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

<script type="text/javascript" src='<%=dojoBase%>/dojo/dojo.js'></script>
<script type="text/javascript"
	src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime%>"></script>
<script type="text/javascript"
	src="<%=dojoBase%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=appRoot%>/jsp/restaurant/goodsbill/template/edit.js?Version=<%=currenttime%>"></script>
<script type="text/javascript"
	src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime%>"></script>

<style type="text/css">
@import "<%=dojoBase%>/dojo/resources/dojo.css";

@import "<%=dojoBase%>/dgrid/css/dgrid.css";

@import "<%=dojoBase%>/dgrid/css/skins/claro.css";

@import "<%=dojoBase%>/dijit/themes/claro/claro.css";
</style>
<link rel="stylesheet"
	href="<%=appRoot%>/jsp/restaurant/common/template/edit.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />

	<script type="text/javascript">
		var templateId = '${templateId }';
		var templateType = '${templateType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto; overflow-x: hidden;">
	<form id="templateForm" method="post">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">模板编号：</td>
				<td class="text_left" style="width: 180px;"><span
					id="templateId">${templateMeta.templateId }</span></td>
				<input type='hidden' id='templateType' value='${templateType }' />
				<input type='hidden' id='branchId' value='${templateMeta.branchId }' />
				<input type='hidden' id='templateNote' value='${templateMeta.templateNote }' />
				<input type='hidden' id='deliveryRegion' value='${templateMeta.deliveryRegion }' />
				<input type='hidden' id='deliveryType' value='${templateMeta.deliveryType }' />
				<td class="label_right" style="width: 90px;">模板名称：</td>
				<td class="text_left"><input type="text" id="templateName"
					name="templateMeta.templateName"
					value="${templateMeta.templateName }" style="width: 180px;" /></td>
			</tr>

			<tr>
				<td class="label_right">模板要货类别：</td>
				<td class="text_left" colspan="1"><span id="categoryId">${categoryName }</span>
				</td>

				<td class="label_right" style="width: 90px;">按物料编码排序:</td>
				<td class="text_left"><input name="isSord" id="isSord"
					type="checkbox"
					style="vertical-align: middle; margin: 0px; padding: 0px;" /></td>
			</tr>

			<tr>
				<td class="label_right">门店分组：</td>
				<td class="text_left"><span>${regionName }</span> <input
					type='hidden' id="deliveryRegion"
					value='${templateMeta.deliveryRegion }' /></td>

				<td class="label_right" style="width: 60px;">到货周期：</td>
				<td class="text_left" colspan="3"><input type="text"
					id="arrivePeriod" name="templateMeta.arrivePeriod"
					value="${templateMeta.arrivePeriod }" style="width: 80px;" /></td>
			</tr>

			<tr>
				<td colspan="6"><input type="button" onclick="selMaterial();"
					value="新增原料" /> <input type="button" onclick="delMaterial();"
					value="删除原料" style="margin-left: 8px;" /> <input type="button"
					onclick="doSave();" value="保存修改" style="margin-left: 8px;" /> <input
					type="button" onclick="doCopy();" value="复制模板"
					style="margin-left: 8px;" /> <input type="button"
					onclick="doClose();" value="取消修改" style="margin-left: 8px;" /></td>
			</tr>
		</table>

		<p class="area_blank">&nbsp;</p>

		<div id="dataGrid" style="top: 122px;"></div>
	</form>
</body>

</html>
