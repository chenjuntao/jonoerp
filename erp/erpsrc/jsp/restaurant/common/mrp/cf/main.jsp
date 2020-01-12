<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>query stock</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/mrp/cf/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/common/mrp/cf/export.js?Version=<%=currenttime %>"></script>

    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/cbtree/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/common/mrp/lc/main.css">
</head>

<body class="claro">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />

<!-- 导出 打印对话框 -->
<div id="customDialog"  data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出"'>
	<table style="position:relative;">
		<tr>
			<td>
				<label for="type">导出格式: </label>
			</td>
			
			<td>
				<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
					<option value="excel">excel</option>
					<option value="csv">csv</option>
				</select>
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

	<div id="selectItemDiv">
		<table class="hovertable" width="100%">
			<tr>
				<td class="label_right" style="width: 120px;">原料编码或名称：</td>
				<td class="text_left">
		 			<input type="text" id="itemName" />
		 		</td>
			</tr>
		</table>
		
		<div id="categoryTree"></div>
	</div>
	
	<div id="queryStorageGrid">
		<table class="hovertable" width="100%">
			<tr>
				<td class="label_right" style="width: 60px;">部门：</td>
				<td class="text_left" style="width: 120px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
						 id="branchId" name="branchId" onchange= "changeStorage()"></s:select>
				</td>
				
				<td class="text_left">
					
					<span style="display: inline-block;width: 20px;"></span>
					<input id="queryBtn" type="button" value="查询" />
					
					<span style="display: inline-block;width: 5px;"></span>
					<input id="clearItemBtn" type="button" value="清空查询结果" />
					
<%-- 					<span style="display: inline-block;width: 5px;"></span> --%>
<!-- 					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 20px;" data-dojo-props='onClick: show'>导出</button> -->
				</td>
			</tr>
		</table>
		
		<div id="storageGrid" class="commonGrid" style="top:32px;"></div>
	</div>
</body>

</html>