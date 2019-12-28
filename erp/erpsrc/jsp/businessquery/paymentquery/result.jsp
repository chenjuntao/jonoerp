<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<%
	String appRoot = request.getContextPath();
%>

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
				<td>${shopBean.shopC}</td>
				<td>${shopBean.shopName}</td>

				<c:forEach var="item" items="${paySumMaps}">
				
				<c:set var="index" value="${fn:indexOf(item.key, 'separator')}"/>
				<c:set var="index_str" value="${fn:substring(item.key, 0, index)}"/>
				
				<c:set var="length" value="${fn:length(item.key)}"/>
				<c:set var="separator_length" value="${fn:length('separator')}"/>
				<c:set var="head_index_str" value="${fn:substring(item.key, index +separator_length , length)}"/>
				
				<td><a
					href='javascript: parent.addTab("${shopBean.shopName}-${head_index_str}", "<%=appRoot %>/jsp/businessquery/payShopBillList.action?shopC=${shopBean.shopC}&startDate=${startDate}&endDate=${endDate}&type=${index_str}&currentPage=0");'
					><fmt:formatNumber type="number" maxFractionDigits="2" value="${shopBean.pay[item.key]}" /></a>
			    </td>
				</c:forEach>

		</c:forEach>
		
		<tr  onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td ><b>合计：</b></td>
				<td></td>
				<c:forEach var="item" items="${paySumMaps}">
					<td ><b><fmt:formatNumber type="number" value="${item.value}" maxFractionDigits="2"/></b></td>
				</c:forEach>
		</tr>
	</table>
</div>
