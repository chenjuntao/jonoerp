<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>	
	<script src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>	
	<script type="text/javascript"	src="<%=appRoot%>/jsp/businessquery/paymentquery/export_one_shop_pay.js"></script>
</head>

<body bgcolor="white" class="claro">
	<form id="queryForm">
		<table class="hovertable" width="100%" align="center">
			<tr valign="middle">
				<td  colspan="8">
					<div align="left">
						<b>付款方式查询</b>
					</div>
				</td>
			</tr>
			

			<tr valign="middle">
				
				<td>&nbsp;开始日期：${startDate}</td>
				
				<td>&nbsp;结束日期：${endDate}</td>
				<td>
					<input type="button" onclick="exportXls();" value="导出EXCEL">
					<input type="button" onclick="exportCsv();" value="导出文本">
				</td>
					
			</tr>
		</table>
	</form>
	
	<input type="hidden"  id="startDate" name="startDate" value="${startDate}"/>
	<input type="hidden"  id="endDate"	name="endDate" value="${endDate}"/>
	<input type="hidden"  id="shopC"	name="shopC" value="${shopC}"/>
	
	<div style="width:100%;overflow:auto;">
	<table  class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2" >
		<tr>
			<th style="text-align:center;">门店编号</th>
			<th style="text-align:center;"><div style="width:60px;">门店名称</div></th>

			<c:forEach var="item" items="${paySumMaps}">
				<c:set var="index" value="${fn:indexOf(item.key, 'separator')}"/>
				<c:set var="length" value="${fn:length(item.key)}"/>
				<c:set var="separator_length" value="${fn:length('separator')}"/>
				<c:set var="index_str" value="${fn:substring(item.key, index +separator_length , length)}"/>
				
				<th style="text-align:center;">${index_str}</th>
			</c:forEach>
		</tr>
		
		<c:forEach var="shopBean" items="${shops}">
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td >${shopBean.shopC}</td>
				<td >${shopBean.shopName}</td>

				<c:forEach var="item" items="${paySumMaps}">
				
				<c:set var="index" value="${fn:indexOf(item.key, 'separator')}"/>
				<c:set var="index_str" value="${fn:substring(item.key, 0, index)}"/>
				
				<c:set var="length" value="${fn:length(item.key)}"/>
				<c:set var="separator_length" value="${fn:length('separator')}"/>
				<c:set var="head_index_str" value="${fn:substring(item.key, index +separator_length , length)}"/>
				
				<td><a href='javascript: gotoShopBill("${head_index_str}", "${shopBean.shopC}", "${shopBean.shopName}", "${startDate}", "${endDate}", "${index_str}");'
					><fmt:formatNumber type="number" maxFractionDigits="2" value="${shopBean.pay[item.key]}" /></a>
			    </td>
				</c:forEach>

		</c:forEach>
		
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td ><b>合计：</b></td>
				<td></td>
				<c:forEach var="item" items="${paySumMaps}">
					<td ><b><fmt:formatNumber type="number" value="${item.value}" maxFractionDigits="2"/></b></td>
				</c:forEach>
		</tr>
	</table>
</div>

<script type="text/javascript">
	function gotoShopBill(_payType, _shopC, _shopName, _startDate, _endDate, _typeCode) {
		var _url = appRoot + '/jsp/businessquery/payShopBillList.action?shopC=' + _shopC 
				+ "&shopName=" + encodeURI(_shopName) + "&startDate=" + _startDate + "&endDate=" + _endDate 
				+ "&type=" + _typeCode + "&currentPage=0";
		parent.addTab(_shopName + _payType, _url);
	}
</script>

</body>

</html>

