<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>supply cycle config</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript">
		var lcCode = '${lcCode }';
		var cfCode = '${cfCode }';
		var g_busFlag = ${busFlag };
	</script>
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/period/supplier/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/period/supplier/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<table class="hovertable" width="100%">
		<tr>
			<td class="label_right" style="width: 80px;">原料名称：</td>
			<td class="text_left">
	 			<input type="text" id="itemName" />
	 			
				<span style="display: inline-block;width: 20px;"></span>
				<input id="queryBtn" type="button" onclick="doQuery()" value="查询原料" />
				<span style="display: inline-block;width: 5px;"></span>
				<input id="saveBtn" type="button" onclick="doSave()" value="保    存" />
	 		</td>
		</tr>
	</table>
		
	<div id="branchTree"></div>
	<div id="categoryTree"></div>
	<div id="dataGrid"></div>
</body>

</html>
