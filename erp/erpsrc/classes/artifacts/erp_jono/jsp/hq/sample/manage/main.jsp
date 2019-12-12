<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>sample info</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/sample/manage/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/sample/manage/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 100px;">产品名称：</td>
			<td class="text_left">
	 			<input type="text" id="productName" onkeydown="if(event.keyCode==13){doQuery();return false;}" />
	 			
		   	 	<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;" />
		   	 	<input type="button" onclick="doAdd();" value="新增" style="margin-left: 8px;" />
		   	 	<input type="button" onclick="doDelete();" value="删除" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid" style="top: 32px;">
	</div>
</body>

</html>
