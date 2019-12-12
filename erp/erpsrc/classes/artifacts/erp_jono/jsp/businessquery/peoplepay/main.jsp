<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/peoplepay/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/peoplepay/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/businessquery/peoplepay/main.css"/>
	
</head>

<body class="claro">
	<form id="queryForm">
		<table class="hovertable" width="100%" align="center">
			<tr valign="middle">
				<td  colspan="7">
					<div align="left">
						<b>按人员付款方式查询</b>
					</div>
				</td>
			</tr>

			<tr>
				<td class="label_right" style="width: 50px;">门店：</td>
				<td class="text_left"  style="width: 160px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
						 style="width: 160px;" id="branchId" name="branchId" > </s:select>
				</td>
				
				<td style="width: 200px;">
					<label>&nbsp;结帐人: <input id="peopleId" name="peopleId" />
					</label>
				</td>
				

				<td class="label_right" style="width: 80px;">营业日期：</td>
				<td class="text_left" style="width: 300px;">
					<input type="text" class="Wdate" id="startDate" value="${startDate}" style="width: 120px;"		onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" /> 
				<span style="padding: 0 5px;">至</span> 
					<input	type="text" class="Wdate" id="endDate" value="${endDate}"	style="width: 120px;" 		  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />				
				</td>
					
				<td >
					<input type="button" onclick="doQuery();" value="查询">
<!-- 					<input type="button" onclick="exportXls();" value="导出EXCEL"> -->
<!-- 					<input type="button" onclick="exportCsv();" value="导出文本"> -->
				</td>
			</tr>
		</table>
	</form>
	
	<div id="dataGrid">
	</div>
</body>

</html>
