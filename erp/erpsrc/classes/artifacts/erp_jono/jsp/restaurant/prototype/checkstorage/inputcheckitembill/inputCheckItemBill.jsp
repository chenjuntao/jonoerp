<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>main page</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

<script src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js' data-dojo-config="async:1"></script>
<script type="text/javascript"
	src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
<script type="text/javascript"
	src="<%=appRoot%>/jsp/restaurant/prototype/checkstorage/inputcheckitembill/inputCheckItemBill.js?Version=<%=currenttime %>"></script>

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
					<div style="padding-left: 8px;"><b>盘点清单选择</b>					</div>
				</td>
			</tr>
			<tr>
				<td>
						对应餐厅选择	
						  <label>
						  <select name="selectShop" id="selectShop">
					      </select>
			    </label>				</td>
				<td>
						对应盘点批次选择	
						  <label>
						  <select name="selectCheckRecord" id="selectCheckRecord">
					      </select>
			    </label>				
						  </td>
				<td>
						对应盘点清单选择	
						  <label>
						  <select name="selectCheckRecord" id="selectCheckRecord">
					      </select>
			    </label>				
						  （此处也可以采用树形控件来进行选择，三层：店名-〉盘点批次-〉盘点清单）</td>
			</tr>
		</table>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>盘点清单输入</b>					</div>
				</td>
			</tr>
		</table>


	<div id="dataGrid"><label><a	href='javascript: parent.addTab("餐厅盘点清单输入确认", "<%=appRoot %>/jsp/restaurant/prototype/checkstorage/inputcheckitembill/commitCheckItemBill.jsp");'
					>餐厅盘点清单输入确认</a> </label>
	  <label>
	  <input name="printBill" type="submit" id="printBill" value="空白盘点清单打印" />
	  </label>
	</div>	
   
	</form>
</body>

</html>
