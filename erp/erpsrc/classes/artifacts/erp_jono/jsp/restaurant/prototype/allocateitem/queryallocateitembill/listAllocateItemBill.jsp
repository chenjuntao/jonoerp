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
	src="<%=appRoot%>/jsp/restaurant/prototype/allocateitem/queryallocateitembill/listAllocateItemBill.js?Version=<%=currenttime %>"></script>

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
	href="<%=appRoot%>/jsp/restaurant/prototype/allocateitem/queryallocateitembill/main.css">
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" /></head>

<body class="claro">
<form id="damageReportCreateForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td width="10%">
					<div style="padding-left: 8px;">
						<b>门店选择</b>
					</div>
			  </td>
				<td width="12%"><label>
				  <select name="selectShop" id="selectShop">
			      </select>
				</label></td>
				<td width="10%">
					<div style="padding-left: 8px;"><strong>查询开始时间</strong></div>
			  </td>
				<td width="12%"><label>时间控件</label></td>
				<td width="12%">
					<div style="padding-left: 8px;"><strong>查询结束时间</strong></div>
			  </td>
				<td width="11%"><label>时间控件</label></td>
				<td width="33%"><label>
				  <input name="queryBegin" type="submit" id="queryBegin" value="查询" />
				</label></td>
			</tr>
		</table>
		
<label>（注：显示该门店时间段内的所有调拨单，调入或调出部门是该门店）</label>
	    

<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>餐厅调拨单列表</strong></div>
				</td>
			</tr>
  </table>
	
  <div id="dataGrid">
    <label>     <a
					href='javascript: parent.addTab("餐厅调拨单查询+编号", "<%=appRoot %>/jsp/restaurant/prototype/allocateitem/queryallocateitembill/queryAllocateItemBill.jsp");'
					>查看餐厅调拨单</a> </label>
  </div>
</form>
</body>

</html>
