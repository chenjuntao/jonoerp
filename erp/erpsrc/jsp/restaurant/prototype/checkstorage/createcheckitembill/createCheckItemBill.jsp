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
	src="<%=appRoot%>/jsp/restaurant/prototype/checkstorage/createcheckitembill/createCheckItemBill.js?Version=<%=currenttime %>"></script>

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
					<div style="padding-left: 8px;">
						<b>对应盘点批次选择</b>					</div>
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
						  （此处也可以采用树形控件来进行选择，两层：店名-〉盘点批次）</td>
			</tr>
		</table>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>盘点清单原料选择</b>					</div>
				</td>
			</tr>
			<tr>
	    <td>
	    <input name="useModel" type="submit" id="useModel" value="使用模板" />
		</td>
		<td>
	    <input name="saveModel" type="submit" id="saveModel" value="保存模板" />
	    </td>
	    <td>
	    <input name="DIYchoose" type="submit" id="DIYchoose" value="根据原料自选" />（注：弹出窗口显示原料列表，可多选原料）
	    </td>
	
	</tr>
		</table>
		
	输入备注
	<label>
	<input name="remarks" type="text" id="remarks" />
	</label>
	<div id="dataGrid"><label><a	href='javascript: parent.addTab("餐厅盘点清单生成确认", "<%=appRoot %>/jsp/restaurant/prototype/checkstorage/createcheckitembill/commitCheckItemBill.jsp");'
					>生成餐厅盘点清单</a> </label></div>	
   
	</form>
</body>

</html>
