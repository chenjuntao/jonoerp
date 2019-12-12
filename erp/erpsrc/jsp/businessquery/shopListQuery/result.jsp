<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<%
	String appRoot = request.getContextPath();
%>

<div style="width:100%;height: 100%;overflow:auto;">
	<table  id="shopTable" class="hovertable table-striped" width="1300" border="1" align="center" bordercolor="#BBEAC2">
		<thead>
		<tr align="center" bgcolor="#006600">
			<th style="text-align:center;">门店编号</th>
			<th style="text-align:center;">门店名称</th>
			<th style="text-align:center;">付款方式</th>
			<th style="text-align:center;">消费金额</th>
			<th style="text-align:center;">单数</th>
			<th style="text-align:center;">单均消费</th>
			<th style="text-align:center;">人数</th>
			<th style="text-align:center;">人均消费</th>
			<th style="text-align:center;">台位数</th>
			<th style="text-align:center;">台均消费</th>
			<th style="text-align:center;">台位周转率</th>
			
			<th style="text-align:center;">总折扣额</th>
			<th style="text-align:center;">舍尾金额</th>
			<th style="text-align:center;">赠送金额</th>
			<th style="text-align:center;">应付金额</th>
			<th style="text-align:center;">付款金额</th>
		</tr>
		</thead>
		<c:set var="foodAmtTal" value="${0}"/>  
		<c:set var="billNumTal" value="${0}"/>  
		<c:set var="guestNumTal" value="${0}"/>  
		<c:set var="tableNumTal" value="${0}"/>  
		
		<c:set var="disAmtTal" value="${0}"/>  
		<c:set var="roundAmtTal" value="${0}"/>  
		<c:set var="presentAmtTal" value="${0}"/>  
		<c:set var="oughtAmtTal" value="${0}"/>  
		<c:set var="payAmtTal" value="${0}"/>  
		
		<tbody>
		<c:forEach var="shopBean" items="${shops}">
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			
				<c:set var="foodAmtTal" value="${ foodAmtTal + shopBean.foodAmt}"/>  
				<c:set var="billNumTal" value="${ billNumTal + shopBean.billNum}"/>  
				<c:set var="guestNumTal" value="${ guestNumTal + shopBean.guestNum}"/>  
				<c:set var="tableNumTal" value="${ tableNumTal + shopBean.tableNum}"/>  
				
				<c:set var="disAmtTal" value="${ disAmtTal + shopBean.disAmt}"/>  
				<c:set var="roundAmtTal" value="${ roundAmtTal + shopBean.roundAmt}"/>  
				<c:set var="presentAmtTal" value="${ presentAmtTal + shopBean.presentAmt}"/>  
				<c:set var="oughtAmtTal" value="${ oughtAmtTal + shopBean.oughtAmt}"/>  
				<c:set var="payAmtTal" value="${ payAmtTal + shopBean.payAmt}"/>  
			
				<td>${shopBean.shopC}</td>
				
				<td>
					<a href='javascript: parent.addTab("${shopBean.shopName}","<%=appRoot %>/businessquery/tableListQuery/listTable.action?shopC=${shopBean.shopC}&startDate=${startDate}&endDate=${endDate}");'>
						${shopBean.shopName} 
					</a>
				</td>
				
				<td>
					<a href='javascript: parent.addTab("${shopBean.shopName}-付款方式","<%=appRoot %>/businessquery/paymentquery/doShopPayQuery.action?shopC=${shopBean.shopC}&startDate=${startDate}&endDate=${endDate}");'>
						付款方式 
					</a>
				</td>
				<td >${shopBean.foodAmt}</td>
				<td >${shopBean.billNum}</td>
				<td >${shopBean.amtPerBill}</td>
				<td >${shopBean.guestNum}</td>
				<td >${shopBean.amtPerGuest}</td>
				<td >${shopBean.tableNum}</td>
				<td >${shopBean.amtPerTable}</td>
				<td >${shopBean.guestPerTable}</td>
				<td >${shopBean.disAmt}</td>
				<td >${shopBean.roundAmt}</td>
				<td >${shopBean.presentAmt}</td>
				<td >${shopBean.oughtAmt}</td>
				<td >${shopBean.payAmt}</td>
			</tr>
		</c:forEach>
		
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td ><b>合计：</b></td>
				<td ></td>
				<td ></td>
				<td ><b><fmt:formatNumber type="number" value="${foodAmtTal}" maxFractionDigits="2"/></b></td>
				<td ><b><c:out value="${billNumTal}"/></b></td>
				<td ></td>
				<td ><b><c:out value="${guestNumTal}"/></b></td>
				<td ></td>
				<td ><b><c:out value="${tableNumTal}"/></b></td>
				<td ></td>
				<td ></td>
				
				<td ><b><fmt:formatNumber type="number" value="${disAmtTal}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${roundAmtTal}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${presentAmtTal}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${oughtAmtTal}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${payAmtTal}" maxFractionDigits="2"/></b></td>
			</tr>
		</tbody>
	</table>
</div>
