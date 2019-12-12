<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>select supplier radio box</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/branch_s_items/main.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript">
		var branchId = "${branchId}";
	</script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/common/branch_s_items/main.css" />
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css" />
</head>

<body class="claro">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="width: 450px">
				<span style="color:red;font-size: 15px;">停用该门店系统会默认删除它与供应商以及商品的对应关系！</span>
			</td>
			
			<td>
		   	 	<input type="button" onclick="doConfirm();" value="确定停用" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
	
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid"></div>
</body>

</html>
