<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create manual purchase bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/manual/importMain.js?Version=<%=currenttime %>"></script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/purchase/manual/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<script type="text/javascript">
		var g_adjustType = '${adjustType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<div id="customDialog" style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"采购单原料导入"'>
			<form id="uploadForm" method="post" enctype="multipart/form-data">
				<table class="hovertable" width="100%" border="1">
					<tr>
						<td class="label_right" style="width: 60px;">文件：</td>
						
						<td class="text_left">
							<input type="file" id="fileurl"	name="attachment" />
							<input type="button" onclick="importMaterial();" value="导入确认" style="margin-left: 48px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>

	<form id="billForm" method="post" >
		<input type="hidden" id="jsonData" name="jsonData" value='' />
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="text_left" colspan="3">
			    		<input type="button" onclick="show();" value="导入原料" style="margin-left: 58px;" />
			    		<input type="button" onclick="delMaterial();" value="删除原料" style="margin-left: 8px;">
				    <input type="button" onclick="doSubmit()" value="生成采购单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"></div>
	</form>
</body>

</html>
