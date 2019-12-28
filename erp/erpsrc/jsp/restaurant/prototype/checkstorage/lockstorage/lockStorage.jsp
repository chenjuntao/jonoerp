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
	src="<%=appRoot%>/jsp/restaurant/prototype/checkstorage/lockstorage/lockStorage.js?Version=<%=currenttime %>"></script>

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
						<b>盘点锁库信息</b>
					</div>
				</td>
			</tr>
		</table>
<div>
	  <p class="area_blank">盘点批次号
	    <label>
	    <input name="lockStorageID" type="text" id="lockStorageID" />
	    </label>
	    （自动生成的编号，与单据编号的编码规则一致）
    </p>
	  <p class="area_blank">锁库部门
        <label>
	    <input name="lockStorageDepartment" type="text" id="lockStorageDepartment" />
	    </label>
        <label></label>
	锁库人员
	<label>
	<input name="lockStoragePeople" type="text" id="lockStoragePeople" />
	<br />
	<br />
	</label>
	锁库日期
	<label>
	<input name="lockStorageDate" type="text" id="lockStorageDate" />
	</label>
	锁库时间
	<label>
	<input name="lockStorageTime" type="text" id="lockStorageTime" />
	</label>
	（注：此四项自动生成,锁库日期是指营业日期，锁库时间是服务器的实际时间）</p>
    <p class="area_blank">
	  <label></label>
	  备注输入
      <label>
      <input name="remarks" type="text" id="remarks" />
      </label>
	</p>
	</div>

	<label>
	<input name="lockStorage" type="submit" id="lockStorage" value="确定进行盘点锁库" />
	</label>
	</form>
</body>

</html>
