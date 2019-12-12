<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<div  style="width:100%;overflow:auto;">
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
	<table id="billTable" width="1200" class="hovertable"  border="1" align="center" cellpadding="5"
		cellspacing="1" bordercolor="#BBEAC2">
		<thead>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center" bgcolor="#006600">
			<th style="text-align:center;">单据编号</th>
			<th style="text-align:center;">市别</th>
			<th style="text-align:center;">班次</th>
			<th style="text-align:center;">人数</th>
			<th style="text-align:center;">开台时间</th>
			<th style="text-align:center;">结账时间</th>
			<th style="text-align:center;">开台人</th>

			<th style="text-align:center;">结账人</th>
			<th style="text-align:center;">消费金额</th>
			<th style="text-align:center;">舍尾金额</th>
			<th style="text-align:center;">赠送金额</th>
			<th style="text-align:center;">付款金额</th>
			<th style="text-align:center;">总折扣额</th>
		</tr>
		</thead>
		
		<tbody>
		<c:set var="foodAmtTal" value="${0}" />
		<c:set var="guestNumTal" value="${0}" />

		<c:forEach var="billBean" items="${peopleBills}">
		
			<c:set var="guestNumTal" value="${ guestNumTal + billBean.guestNum}" />
			<c:set var="foodAmtTal" value="${ foodAmtTal + billBean.foodAmt}" />
			
			<c:set var="roundAmtTal" value="${ roundAmtTal + billBean.roundAmt}" />
			<c:set var="presentAmtTal" value="${ presentAmtTal + billBean.presentAmt}" />
			<c:set var="oughtAmtTal" value="${ oughtAmtTal + billBean.oughtAmt}" />
			<c:set var="payAmtTal" value="${ payAmtTal + billBean.payAmt}" />
			<c:set var="disAmtTal" value="${ disAmtTal + billBean.disAmt}" />
			
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td><a href='javascript: gotoURL("${billBean.billC}");'
					>${billBean.billC}</a>
				</td>
				<td>${billBean.period}</td>
				<td>${billBean.shift}</td>
				<td>${billBean.guestNum}</td>
				<c:set var="billTime" />
				<c:set var="settleTime" />
				<fmt:parseDate value="${billBean.billTime}" var="billTime" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:parseDate value="${billBean.settleTime}" var="settleTime" pattern="yyyy-MM-dd HH:mm:ss" />
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${billTime}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${settleTime}" /></td>
				
				<td>${billBean.createMan}</td>
				<td>${billBean.settleMan}</td>
				
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${billBean.foodAmt}" /></td> 
				<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${billBean.roundAmt}" /></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${billBean.presentAmt}" /></td>  
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${billBean.payAmt}" /></td> 
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${billBean.disAmt}" /></td> 
				
		</c:forEach>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			<td><b>合计：</b></td>
			<td></td>
			<td></td>
			<td><b><c:out value="${guestNumTal}" /></b></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			
			<td><b><fmt:formatNumber type="number" value="${foodAmtTal}"  maxFractionDigits="2"/></b></td> 
			<td><b><fmt:formatNumber type="number" value="${roundAmtTal}"  maxFractionDigits="2"/></b></td> 
			<td><b><fmt:formatNumber type="number" value="${presentAmtTal}" maxFractionDigits="2" /></b></td> 
			<td><b><fmt:formatNumber type="number" value="${payAmtTal}" maxFractionDigits="2" /></b></td> 			 
			<td><b><fmt:formatNumber type="number" value="${disAmtTal}"  maxFractionDigits="2"/></b></td> 
		</tr>
		</tbody>
	</table>
</div>
