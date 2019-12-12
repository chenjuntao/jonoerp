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
	src="<%=appRoot%>/jsp/restaurant/prototype/allocateitem/createallocateitembill/createAllocateItemBill.js?Version=<%=currenttime %>"></script>

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
	href="<%=appRoot%>/jsp/allocateitem/createallocateitembill/main.css">
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" /></head>

<body class="claro">
	<form id="damageReportCreateForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><b>调拨原料选择</b>					</div>
				</td>
			</tr>
		</table>
		
	    <label>
	    <input name="DIYchoose" type="submit" id="DIYchoose" value="根据原料自选" />
	    </label>
	（注：弹出窗口显示原料列表，可多选原料）
	<label></label>
	<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<strong>输入调拨单信息</strong></td>
			</tr>
	  </table>

	<p class="area_blank">调入部门
	  <label>
	  <select name="allocateToDepartment" id="allocateToDepartment">
      </select>
	  </label>
	调出部门
	<label>
	<select name="allocateFromDepartment" id="allocateFromDepartment">
    </select>
	</label>
	备注信息输入
	<label>
	<input name="remarks" type="text" id="remarks" />
	</label>
	</p>
	<div id="dataGrid"></div>
	

    （注：输入调拨数量）
    <p>
      <label><a
					href='javascript: parent.addTab("餐厅调拨单生成确认", "<%=appRoot %>/jsp/restaurant/prototype/allocateitem/createallocateitembill/commitAllocateItemBill.jsp");'
					>生成餐厅调拨单</a> </label>
</p>
	</form>
</body>

</html>
