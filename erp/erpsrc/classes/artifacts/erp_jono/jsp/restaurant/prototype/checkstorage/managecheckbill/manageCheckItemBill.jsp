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
	src="<%=appRoot%>/jsp/restaurant/prototype/checkstorage/managecheckbill/manageCheckItemBill.js?Version=<%=currenttime %>"></script>

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
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
</head>

<body class="claro">
	<form id="commitDamageReportForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>餐厅盘点清单基本信息</b>
					</div>
				</td>
			</tr>
		</table>
		
	餐厅盘点清单编号
	<label>
	<input name="billNumber" type="text" id="billNumber" />
	</label>
	<p class="area_blank">盘点部门
      <label>
      <input name="checkDepartment" type="text" id="checkDepartment" />
      </label>
	对应盘点批次
      <label>
      <input name="checkRecord" type="text" id="checkRecord" />
      </label>
</p>
	<p class="area_blank">制单人员
	  <label>
	  <input name="createBillPeople" type="text" id="createBillPeople" />
	  </label>
	  制单日期
	  <label>
	<input name="createBillDate" type="text" id="createBillDate" />
	</label>
	</p>
	<p class="area_blank">备注
      <label>
<input name="remarks" type="text" id="remarks" />
      </label>
    </p>
			<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>餐厅盘点清单输入修改</b>					</div>
				</td>
			</tr>
		</table>
		

	<div id="dataGrid"></div>
	
		<label>
		<input name="modifyBill" type="submit" id="modifyBill" value="确认修改" />
		</label>
	    <label>
	    <input name="deleteBill" type="submit" id="deleteBill" value="删除清单" />
	    </label>
	    <label>
	    <input name="invalidBill" type="submit" id="invalidBill" value="作废清单" />
	    </label>
	</form>
</body>

</html>
