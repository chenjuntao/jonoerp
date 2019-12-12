<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>import check item</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

<script type="text/javascript" src='<%=dojoBase%>/dojo/dojo.js' data-dojo-config="async:1"></script>
<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime%>"></script>
    <script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
<script type="text/javascript" src="<%=appRoot%>/jsp/setting/import/newImport.js?Version=<%=currenttime%>"></script>

<style type="text/css">
@import "<%=dojoBase%>/dojo/resources/dojo.css";

@import "<%=dojoBase%>/dgrid/css/dgrid.css";

@import "<%=dojoBase%>/dgrid/css/skins/claro.css";

@import "<%=dojoBase%>/dijit/themes/claro/claro.css";
</style>
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />

<script type="text/javascript">
	
</script>
</head>

<body class="claro" style="overflow-y: auto; overflow-x: hidden;">

	<form id="uploadForm" method="post" enctype="multipart/form-data">
		<table class="hovertable" width="100%" border="1">
	    <input type="hidden" id="downloadTokenValue" name="downloadTokenValue"/>
		<tr>
		<td class="label_right" style="width: 90px;">初始化选择</td>
		<td>
			<s:select  id="type" style="width: 150px;"  theme="simple" list="#{1:'基本物料信息',2:'半成品信息',3:'部门信息',4:'价格信息'}" labelposition="lable_right" listKey="key" listValue="value"  headerKey="1" 
								onchange="categoryChange()"></s:select>
								</td>
				<td >
					<span style="width:100%" ></span>
					<input type="button" onclick="exportXls();" value="导出模板">
				</td>
				<td class="label_right" style="width: 90px;">文件：</td>
				<td class="text_left"><input type="file" id="fileurl" name="attachment" /> 
					<input type="button" onclick="importMaterial();" value="还原" style="margin-left: 58px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 220px; font-size: 16px;">
					表单备份内容：</td>
				<td class="text_left" colspan="4">
					<div
						style="padding-left: 8px; font-weight: 900; font-size: 15px; color: blue;">
						<span id="tableText" style="display: inline-block;">我是基本信息表</span>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>

</html>