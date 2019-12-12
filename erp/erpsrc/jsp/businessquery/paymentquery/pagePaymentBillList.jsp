<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div style="width:100%;overflow:auto;">
	<table width="2500" class="hovertable"  border="1" align="center" cellpadding="5"
		cellspacing="1" bordercolor="#BBEAC2">
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center" bgcolor="#006600">
			<th style="text-align:center;">单据编号</th>
			<th style="text-align: center;">付款方式</th>
			<th style="text-align:center;">市别</th>
			<th style="text-align:center;">班次</th>
			<th style="text-align:center;">人数</th>
			<th style="text-align:center;">开台时间</th>
			<th style="text-align:center;">结账时间</th>
			<th style="text-align:center;">开台人</th>
			<th style="text-align:center;">结账人</th>
			
			<th style="text-align:center;">消费金额</th>
			<th style="text-align:center;">赠送金额</th>
			<th style="text-align:center;">应付款金额</th>
			<th style="text-align:center;">舍尾金额</th>
			<th style="text-align:center;">付款金额</th>
			<th style="text-align:center;">总折扣额</th>
			<th style="text-align:center;">照单折扣人</th>
			<th style="text-align:center;">照单折扣原因</th>
			<th style="text-align:center;">会员卡号</th>
			<th style="text-align:center;">会员名字</th>
			<th style="text-align:center;">备注信息</th>
		</tr>
		
		<c:set var="foodAmtTal" value="${0}" />
		<c:set var="guestNumTal" value="${0}" />

		<c:forEach var="bill" items="${bills}">
		
			<c:set var="guestNumTal" value="${ guestNumTal + bill.guestNum}" />
			<c:set var="foodAmtTal" value="${ foodAmtTal + bill.foodAmt}" />
			
			<c:set var="roundAmtTal" value="${ roundAmtTal + bill.roundAmt}" />
			<c:set var="presentAmtTal" value="${ presentAmtTal + bill.presentAmt}" />
			<c:set var="oughtAmtTal" value="${ oughtAmtTal + bill.oughtAmt}" />
			<c:set var="payAmtTal" value="${ payAmtTal + bill.payAmt}" />
			
			<c:set var="disAmtTal" value="${ disAmtTal + bill.disAmt}" />
			
			<c:set var="disCurAmtTal" value="${ disCurAmtTal + bill.disCurAmt}" />
			

			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td><a href='javascript: parent.addTab("${shopName}-${bill.billC}", "/businessquery/foodlist.action?billC=${bill.billC}&shopC=${shopC}&shopName=${shopName}&startDate=${startDate}&endDate=${endDate}");'
					>${bill.billC}</a>
				</td>
				<td><a href="#" onclick="showLog('${bill.billC}');">付款方式</a></td>
				<td>${bill.period}</td>
				<td>${bill.shift}</td>
				<td>${bill.guestNum}</td>
				
				<c:set var="billTime" />
				<c:set var="settleTime" />
				<fmt:parseDate value="${bill.billTime}" var="billTime" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:parseDate value="${bill.settleTime}" var="settleTime" pattern="yyyy-MM-dd HH:mm:ss"/>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${billTime}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${settleTime}" /></td>	
				
				<td>${bill.createMan}</td>
				<td>${bill.settleMan}</td>
				
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.foodAmt}" /></td> 
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.presentAmt}" /></td>  
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.oughtAmt}" /></td> 
				<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.roundAmt}" /></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.payAmt}" /></td> 
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.disAmt}" /></td> 
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.disCurAmt}" /></td> 
				
				<td>${bill.disCurMan}</td> 
				<td>${bill.disCurWhy}</td>
				<td>${bill.vipC}</td>
				<td>${bill.vipN}</td>
				<td>${bill.remark}</td>
				

		</c:forEach>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			<td><b>合计：</b></td>
			<td></td>
			<td></td>
			<td></td>
			<td><b><c:out value="${guestNumTal}" /></b></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			
			<td><b><fmt:formatNumber type="number" value="${foodAmtTal}"  maxFractionDigits="2"/></b></td> 
			
			<td><b><fmt:formatNumber type="number" value="${roundAmtTal}"  maxFractionDigits="2"/></b></td> 
			<td><b><fmt:formatNumber type="number" value="${presentAmtTal}" maxFractionDigits="2" /></b></td> 
			<td><b><fmt:formatNumber type="number" value="${oughtAmtTal}"  maxFractionDigits="2"/></b></td> 
			<td><b><fmt:formatNumber type="number" value="${payAmtTal}" maxFractionDigits="2" /></b></td> 
			
			<td></td>
			
			<td><b><fmt:formatNumber type="number" value="${disAmtTal}"  maxFractionDigits="2"/></b></td> 
			
			<td></td>
			
			<td><b><fmt:formatNumber type="number" value="${disCurAmtTal}"  maxFractionDigits="2"/></b></td> 
			
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	</div>
