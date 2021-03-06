<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>main page</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

<script src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
<script type="text/javascript"
	src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
<script type="text/javascript"
	src="<%=appRoot%>/jsp/restaurant/prototype/checkstorage/confirmcheckbill/listCheckBill.js?Version=<%=currenttime %>"></script>

<link rel="stylesheet"
	href="<%=appRoot%>/jsp/common/lib/dojo/resources/dojo.css">
<!-- required: the default dijit theme: -->
<link id="themeStyles" rel="stylesheet"
	href="<%=appRoot%>/jsp/common/lib/dijit/themes/claro/claro.css" />
<link rel="stylesheet"
	href="<%=appRoot%>/jsp/common/lib/dgrid/css/skins/claro.css">

<!-- ensure that dgrid.css is loaded before your rules 
	so that your rules can override those in dgrid.css without requiring higher specificity -->
<link rel="stylesheet"
	href="<%=appRoot%>/jsp/common/lib/dgrid/css/dgrid.css" />
<link rel="stylesheet"
	href="<%=appRoot%>/jsp/restaurant/prototype/checkstorage/main.css">
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" /></head>

<body class="claro">
	<form id="damageReportCreateForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><b>盘点单范围选择</b>					</div>
				</td>
			</tr>
			<tr>
				<td width="9%">
						对应餐厅选择	
						  <label>
						  <select name="selectShop" id="selectShop">
					      </select>
		      </label>				</td>
			  <td width="28%">查询开始时间
			    <label>
			    <input name="beginTime" type="text" id="beginTime" />
			    </label></td>
			  <td width="63%">查询结束时间
			    <label>
			    <input name="beginTime" type="text" id="endTime" />
		      </label></td>
			</tr>
		</table>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>盘点单列表</b>					</div>
				</td>
			</tr>
		</table>


	<div id="dataGrid"><label><a	href='javascript: parent.addTab("餐厅盘点单审核", "<%=appRoot %>/jsp/restaurant/prototype/checkstorage/confirmcheckbill/confirmCheckBill.jsp");'
					>餐厅盘点单审核</a> </label></div>	
   
	</form>
</body>

</html>
