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

		<%-- the variable for total --%>
		
		<c:set var="foodAmtTal_Last" value="${0}"/>  
		<c:set var="billNumTal_Last" value="${0}"/>  
		<c:set var="guestNumTal_Last" value="${0}"/>  
		<c:set var="tableNumTal_Last" value="${0}"/>  
		
		<c:set var="disAmtTal_Last" value="${0}"/>  
		<c:set var="roundAmtTal_Last" value="${0}"/>  
		<c:set var="presentAmtTal_Last" value="${0}"/>  
		<c:set var="oughtAmtTal_Last" value="${0}"/>  
		<c:set var="payAmtTal_Last" value="${0}"/>

<%
	String appRoot = request.getContextPath();
%>

<div style="width:100%; height: 100%;overflow:auto;">

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
	
	
	<table class="hovertable" width="1800" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr width="100" align="center" bgcolor="#006600">
		
			<th style="text-align:center;">日期</th>
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
		
		<c:forEach var="entry" items="${paySumMaps}" varStatus="status">
				<c:set var="rowspanH" value="${fn:length(entry.value) + 1}" />

		<%-- the variable for sub-total --%>
		
		<c:set var="foodAmtTal" value="${0}"/>  
		<c:set var="billNumTal" value="${0}"/>  
		<c:set var="guestNumTal" value="${0}"/>  
		<c:set var="tableNumTal" value="${0}"/>  
		
		<c:set var="disAmtTal" value="${0}"/>  
		<c:set var="roundAmtTal" value="${0}"/>  
		<c:set var="presentAmtTal" value="${0}"/>  
		<c:set var="oughtAmtTal" value="${0}"/>  
		<c:set var="payAmtTal" value="${0}"/>  
		
		
			<c:forEach var="item" items="${entry.value}" varStatus="istatus" >
				<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
  					<c:if test="${istatus.index==0}">
						<th rowspan="${rowspanH}" style="text-align:center;">${entry.key}</th>
					</c:if>
  					
					<td>${item.shopC}</td>
					<td><a
						href='javascript: parent.addTab("${item.shopName}","<%=appRoot %>/businessquery/tableListQuery/listTable.action?shopC=${item.shopC}&startDate=${entry.key}&endDate=${entry.key}");'
						>${item.shopName}</a>
					</td>
					
					<td><a
						href='javascript: parent.addTab("${item.shopName}-付款方式","<%=appRoot %>/businessquery/paymentquery/doShopPayQuery.action?shopC=${item.shopC}&startDate=${entry.key}&endDate=${entry.key}");'>付款方式
					</a></td>
					<td ><fmt:formatNumber type="number" value="${item.foodAmt}" maxFractionDigits="2"/></td>
					<td >${item.billNum}</td>
					<td ><fmt:formatNumber type="number" value="${item.amtPerBill}" maxFractionDigits="2"/></td>
					<td >${item.guestNum}</td>
					<td ><fmt:formatNumber type="number" value="${item.amtPerGuest}" maxFractionDigits="2"/></td>
					<td >${item.tableNum}</td>
					<td ><fmt:formatNumber type="number" value="${item.amtPerTable}" maxFractionDigits="2"/></td>
					<td >${item.guestPerTable}</td>
					
					 <td ><fmt:formatNumber type="number" value="${item.disAmt}" maxFractionDigits="2"/></td>
					 <td ><fmt:formatNumber type="number" value="${item.roundAmt}" maxFractionDigits="2"/></td>
					 <td ><fmt:formatNumber type="number" value="${item.presentAmt}" maxFractionDigits="2"/></td>
					 <td ><fmt:formatNumber type="number" value="${item.oughtAmt}" maxFractionDigits="2"/></td>
					 <td ><fmt:formatNumber type="number" value="${item.payAmt}" maxFractionDigits="2"/></td>
					 
				 </tr>
				 
			 	<c:set var="foodAmtTal" value="${ foodAmtTal + item.foodAmt}"/>  
				<c:set var="billNumTal" value="${ billNumTal + item.billNum}"/>  
				<c:set var="guestNumTal" value="${ guestNumTal + item.guestNum}"/>  
				<c:set var="tableNumTal" value="${ tableNumTal + item.tableNum}"/>  
				
				<c:set var="disAmtTal" value="${ disAmtTal + item.disAmt}"/>  
				<c:set var="roundAmtTal" value="${ roundAmtTal + item.roundAmt}"/>  
				<c:set var="presentAmtTal" value="${ presentAmtTal + item.presentAmt}"/>  
				<c:set var="oughtAmtTal" value="${ oughtAmtTal + item.oughtAmt}"/>  
				<c:set var="payAmtTal" value="${ payAmtTal + item.payAmt}"/>  
				
			</c:forEach>
			
			
				<tr  onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td ><b>小计：</b></td>
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
		
		<c:set var="foodAmtTal_Last" value="${ foodAmtTal_Last + foodAmtTal}"/>  
		<c:set var="billNumTal_Last" value="${ billNumTal_Last + billNumTal}"/>  
		<c:set var="guestNumTal_Last" value="${ guestNumTal_Last + guestNumTal}"/>  
		<c:set var="tableNumTal_Last" value="${ tableNumTal_Last + tableNumTal}"/>  
		<c:set var="disAmtTal_Last" value="${ disAmtTal_Last + disAmtTal}"/>  
		<c:set var="roundAmtTal_Last" value="${ roundAmtTal_Last + roundAmtTal}"/>  
		<c:set var="presentAmtTal_Last" value="${ presentAmtTal_Last + presentAmtTal}"/>  
		<c:set var="oughtAmtTal_Last" value="${ oughtAmtTal_Last + oughtAmtTal}"/>  
		<c:set var="payAmtTal_Last" value="${ payAmtTal_Last + payAmtTal}"/>  
		
		</c:forEach>
		
		
		<tr  onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
				<td ><b>合计：</b></td>
				<td ></td>
				<td ></td>
				<td ></td>
				<td ><b><fmt:formatNumber type="number" value="${foodAmtTal_Last}" maxFractionDigits="2"/></b></td>
				<td ><b><c:out value="${billNumTal_Last}"/></b></td>
				<td ></td>
				<td ><b><c:out value="${guestNumTal_Last}"/></b></td>
				<td ></td>
				<td ><b><c:out value="${tableNumTal_Last}"/></b></td>
				<td ></td>
				<td ></td>
				<td ><b><fmt:formatNumber type="number" value="${disAmtTal_Last}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${roundAmtTal_Last}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${presentAmtTal_Last}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${oughtAmtTal_Last}" maxFractionDigits="2"/></b></td>
				<td ><b><fmt:formatNumber type="number" value="${payAmtTal_Last}" maxFractionDigits="2"/></b></td>
		</tr>
	</table>
</div>