<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tanry.framework.util.DateTimeUtil"%>
	
<%
	String appRoot = request.getContextPath();
%>

<div style="width:100%;overflow:auto;">

	<div id="pagelist">
		<button onclick='firstPage();'>首页</button>
		<button onclick='prevPage();'>上一页</button>
		<input type="hidden" style="display: none;" id="currentPage" value="${currentPage}" />
		<input id="gotoPage" type="text" value="${currentPage}" style="width:50px;" />
		<button onclick='nextPage();'>下一页</button>
    	<button onclick='lastPage();'>尾页</button>
		<input type="hidden" style="display: none;" id="pageCount" value="${pageCount}" />
    	共${pageCount}页
    	<button onclick='gotoPage();'>跳转</button>
	</div>
	
	<table id="payTable" class="hovertable table-striped" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<thead>
		<tr>
			<th style="text-align:center;">日期</th>
			<th style="text-align:center;">门店编号</th>
			<th style="text-align:center;">门店名称</th>
			<!-- 表头 -->
			<c:forEach var="item" items="${paySumMap}">
			    <c:set var="index" value="${fn:indexOf(item.key, 'separator')}"/>
				<c:set var="length" value="${fn:length(item.key)}"/>
				<c:set var="separator_length" value="${fn:length('separator')}"/>
				<c:set var="index_str" value="${fn:substring(item.key, index +separator_length , length)}"/>
				
				<th style="text-align:center;">${index_str}</th>
			</c:forEach>
		</tr>
		</thead>
		
		<tbody>
		<!-- 遍历每一天 -->
		<c:forEach var="entry" items="${shops}" varStatus="status">
			<c:forEach var="item" items="${entry.value}" ><!-- 遍历每个店 -->
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td style="text-align:center;">${entry.key}</td>
				
				<td >${item.shopC}</td>
				<td >${item.shopName}</td>
				 
				 <c:forEach var="temp" items="${paySumMap}"><!-- 遍历每种付款方式 -->
				    	<c:set var="index" value="${fn:indexOf(temp.key, 'separator')}"/>
						<c:set var="index_str" value="${fn:substring(temp.key, 0, index)}"/>
						
						<c:set var="length" value="${fn:length(temp.key)}"/>
						<c:set var="separator_length" value="${fn:length('separator')}"/>
						<c:set var="head_index_str" value="${fn:substring(temp.key, index +separator_length , length)}"/>
				
				 	<td>
				 		<a href='javascript: gotoURL("<%=appRoot %>/jsp/businessquery/payShopBillList.action?shopC=${item.shopC}&shopName=${item.shopName}&startDate=${startDate}&endDate=${endDate}&type=${index_str}");'>
				 			<fmt:formatNumber type="number" maxFractionDigits="2" value="${item.pay[temp.key]}" />
				 		</a>
				   </td>
				</c:forEach>
			 </tr>
			</c:forEach>
			
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td style="text-align:center;">${entry.key}</td>
				<td ><b>小计：</b></td><!-- 一天一个小计 -->
				<td ></td>
				<c:set var="subSumMap" value="${paySubSumList[status.index]}" />
				<c:forEach var="item" items="${paySumMap}">
				 	<td>
				 		<fmt:formatNumber type="number" maxFractionDigits="2" value="${subSumMap[item.key]}" /><!-- 通过键获取值，保证一致性 -->
				   </td>
				</c:forEach>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>