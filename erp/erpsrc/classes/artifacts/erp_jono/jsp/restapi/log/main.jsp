<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>transfer log view</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restapi/log/main.js?Version=<%=currenttime %>"></script>

 	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
  	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css">
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restapi/log/main.css">
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

<!-- 查询条件区域 -->
	<form id="logForm" method="post" >
		<table class="hovertable" width="100%" border="1">
	  	    <tr>
	  	    	<td>发送方：
					<s:select id="sender" name="sender" list="senderLst" listKey="code" listValue="name" theme="simple"/>
				</td>
	   			<td>传输时间：
					<input type="text" class="Wdate" id="startTime" name="startTime" value="${startTime}" 
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\',{d:0});}'})" /> 
					至
					<input type="text" class="Wdate" id="endTime" name="endTime" value="${endTime}" 
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\',{d:0});}'})" />
				</td>
				<td>API接口：
					<select id="api" name="api">
						<option value="">请选择</option>
						<option value="bill">订单信息</option>
						<option value="bills">订单菜品</option>
						<option value="pay">订单付款</option>
						<option value="food">菜品信息</option>
						<option value="foodCategory">菜品类别</option>
						<option value="foodPrice">菜品价格</option>
						<option value="restaurant">餐厅信息</option>
					</select>
				</td>
				<td>传输状态：
					<select id="code" name="code">
						<option value="">请选择</option>
						<option value="200">200全部传输成功</option>
						<option value="201">201全部成功有警告</option>
						<option value="202">202部分成功部分失败</option>
						<option value="203">203全部传输失败</option>
					</select>
				</td>
				<td>
			<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 50px;width: 60px;"/>
				</td>
			</tr>
	    </table>
	</form>
	
	<div id="dataGrid" />
		
	<div id="querySummary" />
</body>

</html>
