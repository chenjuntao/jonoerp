<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/supplier/inscan/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/supplier/inscan/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<form id="dataForm" method="post" >

	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="label_right" style="width: 50px;">供应商</td>
			<td class="text_left" >
				<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
					 style="width: 180px;" id="supplierId" name="supplierId"  headerKey="" headerValue="------请选择供应商------"> </s:select>
			</td>
			
			<td class="label_right" style="width: 60px;">入库部门</td>
			<td class="text_left" >
				<s:select list="branchLst" listKey="code" listValue="name" theme="simple"   headerKey="" headerValue="-----请选择门店-----"
					 style="width: 150px;" id="branchId" name="branchId"> </s:select>
			</td>
			
			<td class="label_right" style="width: 50px;">审核日期</td>
				<td class="text_left">
				<input type="text" class="Wdate" id="startDate" value="${startDate}" name="startDate" style="width: 100px;"	onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
					<span style="padding: 0 5px;">至</span> 
					<input	type="text" class="Wdate" id="endDate" value="${endDate}" name="endDate" style="width: 100px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
					
					<input type="button" onclick="doQuery();" value="查询" style="margin-left: 18px;" />
			</td>
			
			<tr>
				<td class="label_right" style="width: 60px;">入库单编号</td>
				<td class="text_left"  style="width: 150px;">
					<input type="text"  style="width: 155px;" name="formIdText1"/>
				</td>
				
				<td class="label_right" style="width: 60px;">采购单编号</td>
				<td class="text_left"  style="width: 150px;">
					<input type="text"  style="width: 155px;" name="formIdText2"/>
				</td>
				
				<td class="label_right" style="width: 60px;">确认状态</td>
				<td class="text_left"  >
					<input type="radio" name="status" value="供应商已确认">已确认</input>
					<input type="radio" name="status" value="未确认">未确认</input>
					<input type="radio" name="status" value="" checked="checked" >全部</input>
				</td>
			</tr>
			
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid" >
	</div>
	<input name="branchFlag" type="hidden" value="${branchFlag }" id="branchFlag"/>
	</form>
</body>

</html>
