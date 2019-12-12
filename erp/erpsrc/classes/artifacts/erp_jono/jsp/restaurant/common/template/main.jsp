<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>template manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/template/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/common/template/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var templateType = '${templateType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="queryForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>要货单模板管理</b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 120px;">门店：</td>
				<td class="text_left" style="width: 180px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
						 style="width: 150px;" id="branchId" headerKey="-1" headerValue="--请选择--" ></s:select>
				</td>
				<td class="label_right" style="width: 80px;">原料名称：</td>
				<td class="text_left">
		 			<input type="text" id="itemName" style="width: 180px;" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
			   	 	<input type="button" onclick="doQuery();" value="查询" style="margin-left: 8px;" />
			   	 	<input type="button" onclick="doAdd();" value="新增模板" style="margin-left: 8px;" />
			   	 	<input type="button" onclick="doDelete();" value="删除" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid">
	</div>
</body>

</html>
