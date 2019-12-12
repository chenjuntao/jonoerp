<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/gridx-util.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/reportmanage/jono/cost/detail/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/reportmanage/jono/cost/detail/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/reportmanage/jono/cost/detail/main.css">
	
	<script type="text/javascript">
		var startDate = '${startDate}';
		var endDate = '${endDate}';
		var tagId = '${tagId}';
		var tagName = '${tagName}';
	</script>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<!-- 导出 打印对话框 -->
	<div id="customDialog"  data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection" 
							data-dojo-type="dijit/form/Select" style="width: 162px;"
							name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colSpan=2>
					<table id="columnSelection"></table>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:right;">
					<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
					<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hide' >取消</button>
				</td>
			</tr>
		</table>
	</div>

<!-- 查询条件区域 -->
	<form id="dataForm" method="post" >
		<input name="branchId" type="hidden" value="${branchId}"/>
		<input name="startDate" type="hidden" value="${startDate}"/>
		<input name="tagId" type="hidden" value="${tagId}"/>
		<input name="endDate" type="hidden" value="${endDate}"/>
		
	   	<table class="hovertable" width="100%" border="1" style="height: 35px;">
	   		<tr>
	   			<td style="width: 500px">
	   				<span>营业日期&nbsp&nbsp ${startDate}&nbsp至&nbsp${endDate}&nbsp标签名称：${tagName}<span/>
	   			</td>
				<td>
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: show'>导出</button>
				</td>
	   		</tr>
		</table>

		<div id="gridContainer">
			<div id="dataGrid"></div>
		</div>
	</form>
</body>

</html>
