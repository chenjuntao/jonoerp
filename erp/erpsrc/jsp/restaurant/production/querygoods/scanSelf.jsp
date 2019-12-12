<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>confirm before create dish loss bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/production/querygoods/scanSelf.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/restaurant/production/querygoods/exportDetail.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>

	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" id="themeStyles" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
	
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/production/querygoods/main.css">
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow: hidden;">
<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
			<table style="position:relative;">
				<tr>
					<td>
						<label for="type">导出格式: </label>
					</td>
					
					<td>
						<select id="typeSelection" data-dojo-id="typeSelection" data-dojo-type="dijit/form/Select" style="width: 162px;"name="typeSelection" required="true">
							<option value="excel">excel</option>
							<option value="csv">csv</option>
						</select>
					</td>
				</tr>
				
			  <input type="hidden" id="downloadTokenValue" name="downloadTokenValue"/>
				<tr>
					<td colspan="2" style="text-align:right;">
						<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
						<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button>
					</td>
				</tr>
			</table>
	</div>
	
	<form id="billForm">
	<table class="hovertable" width="100%">
		<tr>
			<th colspan="3"><b>半成品生产入库单信息</b></th>
		</tr>
		
		<tr>
			<td>
				生产入库部门：
				<span>${selfSemisHeader.branch}</span>
			</td>
			
			<td>
				生产入库人员：
				<span>${selfSemisHeader.formMaker}</span>
			</td>
			<td>
				入库日期：
				<span>${formTime}</span>
			</td>
		</tr>
		
		<tr>
		<td>
					审核人员：
					<span>${selfSemisHeader.auditorId}</span>
					<span>${selfSemisHeader.auditor}</span>
				</td>
				<td>
					审核日期：
				<span>${auditTime}</span>
				</td>
				<td colspan="2">
					备注信息：
					<span>${selfSemisHeader.formNote}</span>
				</td>
		</tr>
			<tr>
			<td >操作：</td>
			<td colspan="3">
				<button data-dojo-type="dijit/form/Button" data-dojo-props='onClick: showDialog'>导出明细</button>
<!-- 				<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: prn1_preview'>打印预览</button> -->
				<button data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doClose'>关闭页面</button>
			</td>
		</tr>
		
	</table>
	
	<div id="dishGrid" style="height:180px;"></div>
	
	<table class="hovertable" width="100%">
		<tr>
			<th><b>转化为原料耗料信息</b></th>
		</tr>
	</table>
	
	<div id="rawGrid"></div>
	
	</form>
</body>

</html>
