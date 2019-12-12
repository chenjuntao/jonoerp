<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/tableListQuery/export.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript">
		function doQuery(tbName){
			var _title = "${shopName}" + tbName;
			
			tbName = encodeURI(tbName);
			var _url = appRoot
			+ "/businessquery/billInfoQuery/listBill.action?shopC=${shopC}"
			+ "&startDate=${startDate}&endDate=${endDate}&currentPage=0&table=" + tbName;
		
			parent.addTab(_title, _url);
		}
	</script>
</head>

<body bgcolor="white">

<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
	bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
	
		<tr valign="middle">
			<td  colspan="6">
			<div align="left">
					<b>台位营业统计信息</b>
				</div>
			</td>
		</tr>
		
		<tr valign="middle">
		<td  width="200" >&nbsp;开始日期：${startDate}</td>
		<td  width="200" >&nbsp;结束日期：${endDate}</td>
		<td  width="200" >&nbsp;门店编号：${shopC}</td>
		<td  width="200">&nbsp;门店名称：${shopName}</td>
		<td colspan="2">
				<div align="center">
					<input type="button" onclick="exportXls();" value="导出EXCEL">
					<input type="button" onclick="exportCsv();" value="导出文本">
				</div>
		</td>
	</tr>
	
	<input type="hidden"  id="startDate" name="startDate" value="${startDate}"/>
	<input type="hidden"  id="endDate"	name="endDate" value="${endDate}"/>
	<input type="hidden"  id="shopC"	name="shopC" value="${shopC}"/>
		
</table>
		
<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th style="text-align:center;">台位名称</th>
			<th style="text-align:center;">消费金额</th>
			<th style="text-align:center;">单数</th>
			<th style="text-align:center;">人数</th>
			<th style="text-align:center;">单均消费</th>
			<th style="text-align:center;">人均消费</th>
		</tr>

		<c:set var="foodAmtTal" value="${0}" />
		<c:set var="billNumTal" value="${0}" />
		<c:set var="guestNumTal" value="${0}" />
		<c:set var="billAmt" value="${0}" />
		<c:set var="guestAmt" value="${0}" />

		<c:forEach var="tableBean" items="${tables}">

			<c:set var="foodAmtTal" value="${foodAmtTal + tableBean.foodAmt}" />
			<c:set var="billNumTal" value="${billNumTal + tableBean.billNum}" />
			<c:set var="guestNumTal" value="${guestNumTal + tableBean.guestNum}" />
			<c:set var="billAmt" value="${foodAmtTal/billNumTal}" />
			<c:set var="guestAmt" value="${foodAmtTal/guestNumTal}" />

			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center">
			    <td>
<%-- 			    	<a href="javascript: parent.addTab('${shopName}-${tableBean.table}', '<%=appRoot %>/businessquery/billInfoQuery/listBill.action?shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${tableBean.table}&currentPage=0');" --%>
<%-- 					>${tableBean.table}</a> --%>
					<a href="javascript: doQuery('${tableBean.table}')">${tableBean.table}</a>
			    </td>
				<td>${tableBean.foodAmt}</td>
				<td>${tableBean.billNum}</td>
				<td>${tableBean.guestNum}</td>
				<td>
					<fmt:formatNumber type="number" value="${tableBean.foodAmt/tableBean.billNum}" maxFractionDigits="2"/>
				</td>
				<td>
					<fmt:formatNumber type="number" value="${tableBean.foodAmt/tableBean.guestNum}" maxFractionDigits="2"/>
				</td>
		</c:forEach>

		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			<td><b>合计：</b></td>
			<td><b><fmt:formatNumber type="number" value="${foodAmtTal}" maxFractionDigits="2"/></b></td>
			<td><b><c:out value="${billNumTal}" /></b></td>
			<td><b><c:out value="${guestNumTal}" /></b></td>
			<td><b><fmt:formatNumber type="number" value="${billAmt}" maxFractionDigits="2"/></b></td>
			<td><b><fmt:formatNumber type="number" value="${guestAmt}" maxFractionDigits="2"/></b></td>
		</tr>
	</table>
</body>
</html>
