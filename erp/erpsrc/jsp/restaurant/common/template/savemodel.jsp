<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/template/savemodel.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/common/template/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="templateForm" method="post" >
		<input type='hidden' id='templateType' value='${templateType }' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">模板所属部门：</td>
				<td class="text_left" style="width: 180px;">
					<span id="branchName">${branchName }</span>
				</td>
				<td class="label_right" style="width: 120px;">模板名称：</td>
				<td class="text_left">
		 			<input type="text" id="templateName" name="templateMeta.templateName"
		 				 value="${templateMeta.templateName }" style="width: 180px;" />
				</td>
				<td class="text_left">
					<span style="padding-left: 30px;">
						<input name="isSord" id="isSord" type="checkbox" style="vertical-align: middle; margin: 0px;padding:0px;" />
							按物料编码排序
					</span>
					</td>
			</tr>
		</table>
		<p class="area_blank">&nbsp;</p>
		
		<div id="saveAsGrid" style="height: 205px;">
		</div>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			   	 	<input type="button" onclick="doSave();" value="确定保存模板" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
