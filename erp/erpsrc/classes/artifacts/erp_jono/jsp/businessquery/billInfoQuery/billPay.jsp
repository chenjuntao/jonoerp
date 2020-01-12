<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<table width="100%" class="hovertable" border="1" align="center"
	cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
	<tr onmouseover="this.style.backgroundColor='#ffff66';"
		onmouseout="this.style.backgroundColor='#d4e3e5';" align="center"
		bgcolor="#006600">
		<th style="text-align: center;">单据编号</th>
		<th style="text-align: center;">付款方式编码</th>
		<th style="text-align: center;">付款方式名称</th>
		<th style="text-align: center;">付款方式金额</th>
	</tr>

	<c:forEach var="itemMap" items="${payBillLists}">
		<tr onmouseover="this.style.backgroundColor='#ffff66';"
			onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			<td>${itemMap.CBILL_C}</td>
			<td>${itemMap.CPAY_C}</td>
			<td>${itemMap.CPAY_N}</td>
			<td>${itemMap.payTypeAmt}</td>
		</tr>
	</c:forEach>
</table>
