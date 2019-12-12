<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>


<div style="width: 100%; overflow: auto;">
	<table width="1200" class="hovertable" border="1" align="left"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr onmouseover="this.style.backgroundColor='#ffff66';"
			onmouseout="this.style.backgroundColor='#d4e3e5';" align="center"
			bgcolor="#006600">
			<th style="text-align: center;">单据编号</th>
			<th style="text-align: center;">付款方式</th>
			<th style="text-align: center;">市别</th>
			<th style="text-align: center;">班次</th>
			<th style="text-align: center;">人数</th>
			<th style="text-align: center;">开台时间</th>
			<th style="text-align: center;">结账时间</th>
			<th style="text-align: center;">开台人</th>
			<th style="text-align: center;">结账人</th>
			
			<th style="text-align: center;">消费金额</th>
			<th style="text-align: center;">总折扣额</th>
			<th style="text-align: center;">赠送金额</th>
			<th style="text-align: center;">应付金额</th>
			<th style="text-align: center;">舍尾金额</th>
			<th style="text-align: center;">付款金额</th>
			
		</tr>

		<c:set var="foodAmtTal" value="${0}" />
		<c:set var="guestNumTal" value="${0}" />

		<c:forEach var="bill" items="${bills}">

			<c:set var="guestNumTal" value="${ guestNumTal + bill.guestNum}" />
			<c:set var="foodAmtTal" value="${ foodAmtTal + bill.foodAmt}" />
			<c:set var="disAmtTal" value="${ disAmtTal + bill.disAmt}" />
			<c:set var="presentAmtTal" value="${ presentAmtTal + bill.presentAmt}" />
			<c:set var="oughtAmtTal" value="${ oughtAmtTal + bill.oughtAmt}" />
			<c:set var="roundAmtTal" value="${ roundAmtTal + bill.roundAmt}" />
			<c:set var="payAmtTal" value="${ payAmtTal + bill.payAmt}" />

			<tr onmouseover="this.style.backgroundColor='#ffff66';"
				onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td><a
					href='javascript: parent.addTab("${shopName}-${table}-${bill.billC}", "businessquery/foodlist.action?billC=${bill.billC}&shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${table}");'>${bill.billC}</a></td>
				<td><a href="#" onclick="showLog('${bill.billC}');">付款方式</a></td>
				<td>${bill.period}</td>
				<td>${bill.shift}</td>
				<td>${bill.guestNum}</td>
				
				<c:set var="billTime" />
				<c:set var="settleTime" />
				<fmt:parseDate value="${bill.billTime}" var="billTime"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:parseDate value="${bill.settleTime}" var="settleTime"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
						value="${billTime}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
						value="${settleTime}" /></td>

				<td>${bill.createMan}</td>
				<td>${bill.settleMan}</td>

				<td><fmt:formatNumber type="number" value="${bill.foodAmt}"	maxFractionDigits="2" /></td>
				<td><fmt:formatNumber type="number" value="${bill.disAmt}"
						maxFractionDigits="2" /></td>
				<td><fmt:formatNumber type="number" value="${bill.presentAmt}"
						maxFractionDigits="2" /></td>
				<td><fmt:formatNumber type="number"
						value="${bill.oughtAmt}" maxFractionDigits="2" /></td>
				<td><fmt:formatNumber type="number"
						value="${bill.roundAmt}" maxFractionDigits="2" /></td>
				<td><fmt:formatNumber type="number" value="${bill.payAmt}"
						maxFractionDigits="2" /></td>
				
		</c:forEach>
		<tr onmouseover="this.style.backgroundColor='#ffff66';"
			onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			<td><b>合计：</b></td>
			<td></td>
			<td></td>
			<td></td>
			<td><b><c:out value="${guestNumTal}" /></b></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>

			<td><b><fmt:formatNumber type="number" value="${foodAmtTal}"
						maxFractionDigits="2" /></b></td>
			<td><b><fmt:formatNumber type="number" value="${disAmtTal}"
						maxFractionDigits="2" /></b></td>
			<td><b><fmt:formatNumber type="number"
						value="${presentAmtTal}" maxFractionDigits="2" /></b></td>
			<td><b><fmt:formatNumber type="number"
						value="${oughtAmtTal}" maxFractionDigits="2" /></b></td>
			<td><b><fmt:formatNumber type="number"
						value="${roundAmtTal}" maxFractionDigits="2" /></b></td>
			<td><b><fmt:formatNumber type="number" value="${payAmtTal}"
						maxFractionDigits="2" /></b></td>
		</tr>
	</table>
</div>
