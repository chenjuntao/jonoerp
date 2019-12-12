<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<%
	String appRoot = request.getContextPath();
%>

<div style="width: 100%; height: 100%; overflow: auto;">
	<table class="hovertable" style="width: 100%;" border="1"
		cellpadding="5" cellspacing="1">

		<tr align="center" bgcolor="#006600">
			<th style="text-align: center; width: auto;" colspan="5"><span
				id="categoryName"></span></th>
		</tr>

		<tr align="center" bgcolor="#006600">
			<th style="text-align: center; width: auto;">出品编号</th>
			<th style="text-align: center; width: auto;">出品名称</th>
			<th style="text-align: center; width: auto;">出品例牌</th>
			<th style="text-align: center; width: auto;">出品数量</th>
			<th style="text-align: center; width: auto;">每万元出品数量</th>
		</tr>

		<c:set var="foodNumTal" value="${0}" />

		<c:forEach var="item" items="${foodSubAmtList}">
			<tr class="highlight" align="center">
				<!-- <td style="width: auto;"><a
					href='javascript: parent.addTab("${item.foodId}-${item.foodName}","<%=appRoot %>/businessquery/foodAmtQuery/dofoodRawMaterialQuery.action?foodRawMaterialId=${item.foodId}&amtTTCNY=${item.amtTTCNY}");'
					class="btn"><i class="icon icon-eye-open"></i>${item.foodId}</a></td> -->
				<td style="width: auto;">${item.foodId}</td>
				<td style="width: auto;">${item.foodName}</td>
				<td style="width: auto;">${item.foodUnit}</td>
				<c:set var="foodNumTal" value="${ foodNumTal + item.foodNum}" />
				<td style="width: auto;">${item.foodNum}</td>
				<td style="width: auto;">${item.amtTTCNY}</td>
			</tr>

		</c:forEach>

		<tr onmouseover="this.style.backgroundColor='#ffff66';"
			onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			<td><b>合计：</b></td>
			<td></td>
			<td></td>
			<td ><b><fmt:formatNumber type="number" value="${foodNumTal}" maxFractionDigits="2"/></b></td>
			<td></td>

		</tr>

	</table>
</div>