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
			<th style="text-align: center; width: auto;" colspan="6"><span
				id="categoryName"></span></th>
		</tr>

		<tr align="center" bgcolor="#006600">
			<th style="text-align: center; width: auto;">原材料编号</th>
			<th style="text-align: center; width: auto;">原材料名称</th>
			<th style="text-align: center; width: auto;">原材料单位</th>
			<th style="text-align: center; width: auto;">原材料总用量</th>
			<th style="text-align: center; width: auto;">原材料每万元需求量</th>
		</tr>

		<c:forEach var="item" items="${detailLst}">
			<tr class="highlight" align="center">
				<td style="width: auto;">${item.itemId}</td>
				<td style="width: auto;">${item.itemName}</td>
				<td style="width: auto;">${item.unit}</td>
				<td style="width: auto;">${item.nqty}</td>
				<td style="width: auto;">${item.unitQty}</td>
			</tr>
		</c:forEach>
	</table>
</div>