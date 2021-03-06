<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>main page</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript"	src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript"	src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript"	src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript"	src="<%=appRoot%>/jsp/restaurant/reportdamage/querydishloss/showDishLossBill.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/reportdamage/querydishloss/exportDetail.js"></script>

	<link rel="stylesheet"	href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet"	href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css"	id="themeStyles" />
	<link rel="stylesheet"	href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet"	href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	
	<link rel="stylesheet"  href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet"	href="<%=appRoot%>/jsp/restaurant/reportdamage/querydishloss/main.css">

	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<!-- 导出 打印对话框 -->
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
			<table style="position:relative;">
				<tr><td><label for="type">导出格式: </label></td>
					<td><select id="typeSelection" data-dojo-id="typeSelection" 
							data-dojo-type="dijit/form/Select" style="width: 162px;"
							name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select></td>
				</tr>
				<tr><td colspan="2" style="text-align:right;">
					<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
					<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button></td>
				</tr>
			</table>
	</div>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<th colspan="4"><b>${brandWord}出品报损单信息</b></th>
		</tr>
		
		<tr>
			<td>单据编号： <span>${lossHeader.formId}</span>
			</td>
			<td>报损部门： <span>${lossHeader.lossBranch}</span>
			</td>
			<td>报损人员： <span>${lossHeader.lossMan}</span>
			</td>
			<td>报损日期： <span>${lossTime}</span>
			</td>
		</tr>
		
		<tr>
			<td>报损仓库： <span>${lossHeader.storage}</span>
			</td>
			<td>审核人员： <span>${lossHeader.auditor}</span>
			</td>
			<td>审核日期： <span>${auditTime}</span>
			</td>
			<td>备注信息： <span>${lossHeader.formNote}</span>
			</td>
		</tr>
		
		<tr>
			<td >操作：</td>
			<td colspan="3">
				<button data-dojo-type="dijit/form/Button" data-dojo-props='onClick: showDialog'>导出明细</button>
				<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doClose'>关闭页面</button>
			</td>
		</tr>
	</table>
		
	<div id="rawLossGrid" class="commonGrid" style="top: 125px;"></div>
</body>

</html>
