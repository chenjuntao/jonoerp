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
	src="<%=appRoot%>/jsp/restaurant/prototype/allocateitem/agreeallocateitembill/agreeAllocateItemBill.js?Version=<%=currenttime %>"></script>

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
	href="<%=appRoot%>/jsp/restaurant/prototype/allocateitem/agreeallocateitembill/main.css">
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
</head>

<body class="claro">
	<form id="commitDamageReportForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>餐厅调拨单信息</b>
					</div>
				</td>
			</tr>
		</table>
	<div>	
	餐厅调拨单编号
	  <label>
	<input name="billNumber" type="text" id="billNumber" />
	</label>
	<p class="area_blank">调入部门
      <label>
      <input name="allocateToDepartment" type="text" id="allocateToDepartment" />
      </label>
调出部门
<label>
<input name="allocateFromDepartment" type="text" id="allocateFromDepartment" />
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
	<p class="area_blank">审核人员
      <label>
      <input name="confirmPeople" type="text" id="confirmPeople" />
      </label>
      审核日期
      <label>
    <input name="confirmDate" type="text" id="confirmDate" />
    </label>
	</p>
	<p class="area_blank">备注
      <label>
      <input name="remarks" type="text" id="remarks" />
      </label>
    </p>
	<p class="area_blank">确认人员
      <label>
      <input name="agreePeople" type="text" id="agreePeople" />
      </label>
（自动生成）确认日期输入
<label>
<input name="agreeDate" type="text" id="agreeDate" />
</label>
</p>
	</div>
	<div id="dataGrid"></div>
	
	<label>
	<input name="agreeAllocateItemBill" type="submit" id="agreeAllocateItemBill" value="餐厅调拨单确认通过" />
	</label>
	（注：餐厅调拨单状态从未确认变为未拨付）
	</form>
</body>

</html>
