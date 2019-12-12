<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>direct distribution transfer to purchasing bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/purchase/direct.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/query/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 100px;">要货部门：</td>
			<td class="text_left" style="width: 180px;">
				<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
					 style="width: 150px;" id="branchId"></s:select>
			</td>
			<td class="label_right" style="width: 60px;">制单日期：</td>
			<td class="text_left">
				<input type="text" class="Wdate" id="startDate" value=""
					style="width: 110px;" onFocus="WdatePicker()" />
				<span style="padding: 0 5px;">至</span>
				<input type="text" class="Wdate" id="endDate" value=""
					style="width: 110px;" onFocus="WdatePicker()" />
				
		   	 	<input type="button" onclick="doQuery();" value="查询" style="margin-left: 58px;" />
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid" style="top: 32px;">
	</div>
</body>

</html>
