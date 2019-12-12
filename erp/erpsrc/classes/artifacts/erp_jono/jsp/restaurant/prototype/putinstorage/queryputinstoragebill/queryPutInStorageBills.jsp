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
</style>

<html><head>
</head>

<%
	String appRoot = request.getContextPath();
%>

<body bgcolor="white">
<p align="left">对应采购单编号
          <input name="initialBill" type="text" id="textfield2" value="CG031304210319" size="18" />
<p align="left"> 入库单单据编号
  <input name="billNumber" type="text" id="billNumber" value="RK031304210319" size="18" />
  单据状态
  <label>
  <input name="billState" type="text" id="billState">
  </label>
  </p>
  <p align="left">入库部门  
    <label>
    <input name="putInStorageRestaurant" type="text" id="putInStorageRestaurant">
    </label>
  入库人员
        
    <label>
    <input name="putInStoragePeople" type="text" id="putInStoragePeople">
    </label>
入库日期
    <input name="putInStorageDate" type="text" id="label2" value="2012-04-21" size="15" />
</p>
  <p>
    <label></label>
    供应商
    <label>
    <input name="supplier" type="text" id="supplier">
    </label>
备注
    <input name="remarks" type="text" id="remarks" />
</p>
  <table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
				<tr valign="middle">
				<td width="800">
				<div align="Left"><b>入库明细</b></div></td>
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
			<th style="text-align:center;">要货数量</th>
			<th style="text-align:center;">实收数量</th>
			<th style="text-align:center;">实收差异</th>
			<th style="text-align:center;">单价</th>
			<th style="text-align:center;">金额</th>
			<th style="text-align:center;">批次</th>
			<th style="text-align:center;">有效期</th>
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
      <td>显示的是给餐厅的标准价，不是供应商的供货价</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
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
    </tr>
  </table></p>
  <p>

</p>
</body>
</html>
