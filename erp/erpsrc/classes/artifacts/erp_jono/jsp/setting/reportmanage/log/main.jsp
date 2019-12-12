<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/gridx-util.js"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/reportmanage/log/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/reportmanage/log/export.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
       @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
  	  @import "<%=dojoBase %>/dijit/themes/claro/document.css";
       @import "<%=dojoBase %>/gridx/resources/claro/Gridx.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/reportmanage/log/main.css">
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
				<select id="typeSelection" data-dojo-id="typeSelection" data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
					<option value="excel">excel</option>
					<option value="csv">csv</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td colSpan=2>
				<label for="columns">选择列项: </label>
			</td>
		</tr>
		
		<tr>
			<td colSpan=2>
				<table id="columnSelection">
				</table>
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
	  	   <table class="hovertable" width="100%" border="1" style="height: 35px;">
	   			<td class="label_right" style="width: 80px;">操作时间：</td>
				<td class="text_left"  style="width: 380px;">
					<input type="text" class="Wdate" id="startDate" value="${startDate}" style="width: 160px;"	onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
					<span style="padding: 0 5px;">至</span> 
					<input	type="text" class="Wdate" id="endDate" value="${endDate}"	style="width: 160px;" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				</td>
				
				<td>
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: doQuery'>查询</button>
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: show'>导出</button>
				</td>
	    </table>

<!--         表格 数据展示 -->
		<div id="gridContainer"  >
			<div id="dataGrid">
		</div>
				
<!-- 		参数区域 -->
		<input id="inputJsonString"  name="inputJsonString" type="hidden"/>
		<input id="append"  name="append" type="hidden" value=""/>
		<input id="withOutSum"  name="withOutSum" type="hidden" value="true"/>
		<input id="dateFormat" name="dateFormat" type="hidden" value="yyyy-MM-dd HH:mm:ss"/>
		<input name="reportFlag" type="hidden" value="D_T0_OPERATELOG"/>
	</form>
</body>

</html>
