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
	src="<%=appRoot%>/jsp/restaurant/prototype/checkstorage/lockstorage/listAllWaring.js?Version=<%=currenttime %>"></script>

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
				<td width="10%">
					<div style="padding-left: 8px;">
						<b>门店选择</b>
					</div>
			  </td>
				<td width="12%"><label>
				  <select name="selectShop" id="selectShop">
			      </select>
				</label></td>
				<td width="33%"><label>
				  <input name="queryBegin" type="submit" id="queryBegin" value="查询" />
				</label></td>
			</tr>
		</table>
		
<label>（注：显示该门店七个营业日内目前还未审核或处理的单据,这些单据没有处理完原则上是不能盘点锁库的）</label>
	    

<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>餐厅未审核入库单列表</strong></div>
				</td>
			</tr>
  </table>
	
  <div id="putInStorageBillDataGrid" style="height: 100px;">
    <label>     <a
					href='javascript: parent.addTab("餐厅入库单审核", "<%=appRoot %>/jsp/restaurant/prototype/putinstorage/confirmputinstoragebill/listPutInStorageBills.jsp");'
					>进行审核</a> </label>
  </div>
 <table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>餐厅未处理配送反审核单列表</strong></div>
				</td>
			</tr>
  </table>
	
  <div id="distributionAntiAuditBillDataGrid" style="height: 100px;">
    <label>     <a
					href='javascript: parent.addTab("餐厅配送反审核处理", "<%=appRoot %>/jsp/restaurant/prototype/distributionantiaudit/processantiaudit/listDistributionAntiAuditBills.jsp");'
					>进行处理</a> </label>
  </div>
  <table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>餐厅未审核和未处理原料报损单列表</strong></div>
				</td>
			</tr>
  </table>
	
  <div id="itemDamageReportBillDataGrid" style="height: 100px;">
    <label>     <a
					href='javascript: parent.addTab("餐厅原料报损单审核", "<%=appRoot %>/jsp/restaurant/prototype/reportdamage/confirmdamagereportbill/listDamageReportBill.jsp");'
					>进行审核</a> </label>
  </div>
  <table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>餐厅未审核和未处理出品报损单列表</strong></div>
				</td>
			</tr>
  </table>
	
  <div id="dishDamageReportBillDataGrid" style="height: 100px;">
    <label>     <a
					href='javascript: parent.addTab("餐厅出品报损单审核", "<%=appRoot %>/jsp/restaurant/prototype/reportdamage/confirmdishdamagereportbill/listDishDamageReportBill.jsp");'
					>进行审核</a> </label>
  </div>
 
   <label>
   <a href='javascript: parent.addTab("餐厅盘点锁库记录生成", "<%=appRoot %>/jsp/restaurant/prototype/checkstorage/lockstorage/lockStorage.jsp");'
					>进行盘点锁库</a>
   </label>
</form>
</body>

</html>
