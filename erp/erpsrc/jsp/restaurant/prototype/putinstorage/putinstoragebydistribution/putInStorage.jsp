<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>

<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.STYLE1 {color: #FF0000}
</style>

<html><head>
</head>

<%
	String appRoot = request.getContextPath();
%>

<body bgcolor="white">
  <form name="form1" method="post" action="">
		<p align="left">
    <label for="textfield">配送单编号</label>
    <input name="billNumber" type="text" id="billNumber" value="PS031304210319" size="18" />
  配送部门
    <label>
    <input name="supplier" type="text" id="supplier" value="001物流中心" size="15">
    </label>
配送日期
    <label for="label2"></label>
    <input name="dateOfArrival" type="text" id="label2" value="2012-04-22 16:20" size="20" />
</p>
  <p align="left">订货部门
    <input name="orderRestaurant" type="text" id="textfield22" value="001海东青店" size="15" />
  订货地址
    <input name="distributionAddress" type="text" id="distributionAddress" value="001五一路49号 " size="20" />
</p>
<p align="left">制单人员
    <label>
    <input name="orderPeople" type="text" id="orderPeople" size="15">
    </label>
制单日期
    <label for="label"></label>
    <input name="orderDate" type="text" id="label" value="2012-04-21" size="15" />
    <label for="Submit"></label>
</p>
<p>审核人员
    
  <label>
  <input name="confirmPeople" type="text" id="confirmPeople" size="15">
  </label>
  审核日期
    <label for="label5"></label>
    <input name="confirmDate" type="text" id="label5" value="2012-04-21" size="15" />
    <label for="label6"></label>
</p>
<p>
  <label for="label3"></label>
  备注
  <input name="remarks" type="text" id="remarks" />
</p>

  <table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
				<tr valign="middle">
				<td width="800">
				<div align="Left"><b>配送明细</b></div></td>
			</tr>
	
</table>
		
	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th style="text-align:center;">序号</th>
			<th style="text-align:center;">原料编码</th>
			<th style="text-align:center;">原料名称</th>
			<th style="text-align:center;">单位</th>
			<th style="text-align:center;">规格</th>
			<th style="text-align:center;">类别</th>
			<th style="text-align:center;">包装因子</th>
			<th style="text-align:center;">包装单位</th>
			<th style="text-align:center;">包装数量</th>
			<th style="text-align:center;">订货数</th>
			<th style="text-align:center;">配送数</th>
			<th style="text-align:center;">实发数</th>
			<th style="text-align:center;">实收数</th>
			<th style="text-align:center;">差异数</th>
			<th style="text-align:center;">单价</th>
			<th style="text-align:center;">金额</th>
		</tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
 
      <th scope="row">1</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td><span class="STYLE1">输入实际入库数目</span></td>
      <td>实发数-实收数</td>
      <td>餐厅的标准价</td>
      <td>实收数*单价</td>
    </tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">2</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">3</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">......</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
       <td>&nbsp;</td>
      <td>&nbsp;</td>
     <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>

			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
      <th scope="row">本单合计</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
    <p>
      <label></label>
      <label>				<a
					href='javascript: parent.addTab("配送单填充确认", "<%=appRoot %>/jsp/restaurant/prototype/putinstorage/putinstoragebydistribution/commitDistributionBill.jsp");'
					>确认填充配送单</a></label>
      <input name="resetData" type="reset" id="resetData" value="取消输入">
</form>
    </p>
</body>
</html>
