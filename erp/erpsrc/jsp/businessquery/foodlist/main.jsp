<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/jsp/common/jsp/path.jsp"%>
		<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
		<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
		<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
			
		<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/foodlist/export.js?Version=<%=currenttime %>"></script>
		<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/foodlist/main.js?Version=<%=currenttime %>"></script>
</head>

<body bgcolor="white">
		<table class= "hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td  colspan="6">
					<div align="left">
						<b>出品明细统计信息</b>
					</div>
				</td>
			</tr>
			
			<input type="hidden"  id="billC" name="billC" value="${billC}"/>

			<tr valign="middle">
				<td width="200">&nbsp;开始日期：${startDate}</td>
				<td width="200">&nbsp;结束日期：${endDate}</td>
				<td width="200">&nbsp;门店编号：${shopC}</td>
				<td width="200">&nbsp;门店名称：${bill.shopName}</td>
				<td width="200">&nbsp;台位名称：${bill.table}</td>
				<td width="200">&nbsp;单据编号：${billC}</td>
			</tr>

			<tr valign="middle">
				
				<td width="200">&nbsp;应付款金额：${bill.oughtAmt}</td>
				<td width="200">&nbsp;照单折扣额：${bill.disAmt}</td>
				<td width="200">&nbsp;照单折扣人：${bill.disCurMan}</td>
				<td width="200">&nbsp;照单折扣原因：${bill.disCurWhy}</td>
				<td colspan="2">&nbsp;备注信息：${bill.remark}</td>
			</tr>
			
			<tr valign="middle">	
				<td width="200">&nbsp;会员卡号：${bill.vipC}</td>
				<td width="200">&nbsp;会员名字：${bill.vipN}</td>
				<td colspan="3">&nbsp;付款方式：${payStr }</td>
				<td>
					<div align="center">
						<input type="button" onclick="exportXls();" value="导出EXCEL">
						<input type="button" onclick="exportCsv();" value="导出文本">
					</div>
				</td>
			</tr>
		</table>

<div id="detailGrid" style="width:100%;overflow:auto;">
	<table width="1200" class="hovertable"  border="1" align="center" cellpadding="5"
		cellspacing="1" bordercolor="#BBEAC2">
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center" bgcolor="#006600">
			<th style="text-align:center;">出品码</th>
			<th style="text-align:center;">出品名称</th>
			
			<th style="text-align:center;">类别编码</th>
			<th style="text-align:center;">类别名称</th>
			
			<th style="text-align:center;">例牌</th>
			<th style="text-align:center;">单价</th>
			<th style="text-align:center;">数量</th>

			<th style="text-align:center;">消费金额</th>
			<th style="text-align:center;">折扣金额</th>
			
			<th style="text-align:center;">套餐标志</th>
			<th style="text-align:center;">套餐编号</th>
			<th style="text-align:center;">套餐名称</th>
			
			<th style="text-align:center;">退品或赠送标志</th>
			<th style="text-align:center;">退品或赠送原因</th>
			<th style="text-align:center;">退品或赠送人</th>
		</tr>
		

		<c:set var="amtTal" value="${0}"/>  
		<c:set var="disAmtTal" value="${0}"/>  

		<c:forEach var="foodBean" items="${foods}">
		
			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center">
			
				<c:set var="amtTal" value="${ amtTal + foodBean.amt}"/>  
				<c:set var="disAmtTal" value="${ disAmtTal + foodBean.disAmt}"/>  
				
				<td>${foodBean.foodC}</td>
				<td>${foodBean.foodN}</td>
				
				<td>${foodBean.litClsC}</td>
				<td>${foodBean.litClsN}</td>
				
				<td>${foodBean.unit}</td>
				<td><fmt:formatNumber type="number" value="${foodBean.price}" maxFractionDigits="4"/></td> 
				<td>${foodBean.quantity}</td>

				<td><fmt:formatNumber type="number" value="${foodBean.amt}" maxFractionDigits="2"/></td> 
				<td><fmt:formatNumber type="number" value="${foodBean.disAmt}" maxFractionDigits="2"/></td> 
				
				<td>${foodBean.suitFlag}</td>
				<td>${foodBean.suitBill}</td>
				<td>${foodBean.suitName}</td>
				
				<td>${foodBean.retSendFlag}</td>
				<td>${foodBean.retSendRemark}</td>
				<td>${foodBean.presentBackMan}</td>
				
		</c:forEach>
		<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
			<td><b>合计：</b></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><b><fmt:formatNumber type="number" value="${amtTal}" maxFractionDigits="2"/></b></td> 
			<td><b><fmt:formatNumber type="number" value="${disAmtTal}" maxFractionDigits="2"/></b></td> 
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
	</div>
</body>
</html>
