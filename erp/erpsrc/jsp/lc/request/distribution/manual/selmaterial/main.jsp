<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>select material for creating manual distribution bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/distribution/manual/selmaterial/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
   <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/distribution/manual/selmaterial/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>	

<body class="claro">
<table class="hovertable" width="100%">
		<tr>
			<td class="label_right" style="width: 100px;">原料名称或编码：</td>
			<td class="text_left">
	 			<input type="text" id="itemName" onkeydown="if(event.keyCode==13)doQuery();" />
	 			
				<span style="display: inline-block;width: 20px;"></span>
				<input id="queryBtn" type="button" onclick="doQuery()" value="查询原料" />
				<span style="display: inline-block;width: 5px;"></span>
				
			    <input type="button" value="确     定" onclick="doSelect();"style="width: 60px;margin-left: 20px" />
	 		</td>
		</tr>
	</table>
	
	<div id="dataGrid" style="top:32px"></div>
</body>

</html>
