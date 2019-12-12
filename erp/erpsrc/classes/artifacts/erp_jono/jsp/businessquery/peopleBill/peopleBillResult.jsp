<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div  style="width:100%;overflow:auto;">
<div id="pagelist">
		<button onclick='require([ "dojo/_base/xhr" ], function(xhr) {  
	        xhr.post({
		    	url : "peopleBillListResult.action?startDate=${startDate}&endDate=${endDate}&createMan=${createMan}&settleMan=${settleMan}&disCurMan=${disCurMan}&currentPage=1&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("peopleBillResult").innerHTML = data;
					return;
	            },  
	            error : function(error) {
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>首页</button>
		<button onclick='require([ "dojo/_base/xhr" ], function(xhr) {
			var newPage = ${currentPage} - 1;
	        xhr.post({
		    	url : "peopleBillListResult.action?startDate=${startDate}&endDate=${endDate}&createMan=${createMan}&settleMan=${settleMan}&disCurMan=${disCurMan}&currentPage="+newPage+"&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("peopleBillResult").innerHTML = data;
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>上一页</button>
		<input id="currentPageTxt" type="text" value="${currentPage}" style="width:50px;" />
		<button onclick='require([ "dojo/_base/xhr" ], function(xhr) { 
			if(${currentPage} == ${pageCount}) {
				return;
			}
			var newPage = ${currentPage} + 1;
	        xhr.post({
		    	url : "peopleBillListResult.action?startDate=${startDate}&endDate=${endDate}&createMan=${createMan}&settleMan=${settleMan}&disCurMan=${disCurMan}&currentPage="+newPage+"&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("peopleBillResult").innerHTML = data;
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>下一页</button>
    	<button onclick='require([ "dojo/_base/xhr" ], function(xhr) {  
	        xhr.post({
		    	url : "peopleBillListResult.action?startDate=${startDate}&endDate=${endDate}&createMan=${createMan}&settleMan=${settleMan}&disCurMan=${disCurMan}&currentPage=${pageCount}&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("peopleBillResult").innerHTML = data;
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>尾页</button>
    	共${pageCount}页
    	<button onclick='require([ "dojo/_base/xhr" ], function(xhr) {  
    		var newPage = dojo.byId("currentPageTxt").value;
    		if(newPage == null || newPage == ""){
    			alert("请输入正确的数值！");
    			return;
    		}
    		
    		if(newPage >= ${pageCount}){
    			newPage = ${pageCount};
    			dojo.byId("currentPageTxt").value = newPage;
    		}
    		
	        xhr.post({
		    	url : "peopleBillListResult.action?startDate=${startDate}&endDate=${endDate}&createMan=${createMan}&settleMan=${settleMan}&disCurMan=${disCurMan}&currentPage="+newPage+"&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("peopleBillResult").innerHTML = data;
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>跳转</button>
	</div>
<table width="1200" class="hovertable"  border="1" align="center" cellpadding="5"
		cellspacing="1" bordercolor="#BBEAC2">
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
		
		<c:set var="foodAmtTal" value="${0}" />
		<c:set var="guestNumTal" value="${0}" />

		<c:forEach var="bill" items="${peopleBills}">
		
			<c:set var="guestNumTal" value="${ guestNumTal + bill.guestNum}" />
			<c:set var="foodAmtTal" value="${ foodAmtTal + bill.foodAmt}" />
			
			<c:set var="roundAmtTal" value="${ roundAmtTal + bill.roundAmt}" />
			<c:set var="presentAmtTal" value="${ presentAmtTal + bill.presentAmt}" />
			<c:set var="oughtAmtTal" value="${ oughtAmtTal + bill.oughtAmt}" />
			<c:set var="payAmtTal" value="${ payAmtTal + bill.payAmt}" />
			<c:set var="disAmtTal" value="${ disAmtTal + bill.disAmt}" />
			

			<tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td><a href='javascript: parent.addTab("${bill.billC}", "businessquery/foodlist.action?billC=${bill.billC}");'
					>${bill.billC}</a>
				</td>
				<td>${bill.period}</td>
				<td>${bill.shift}</td>
				<td>${bill.guestNum}</td>
				<c:set var="billTime" />
				<c:set var="settleTime" />
				<fmt:parseDate value="${bill.billTime}" var="billTime" pattern="yyyy-MM-dd HH:mm:ss" />
				<fmt:parseDate value="${bill.settleTime}" var="settleTime" pattern="yyyy-MM-dd HH:mm:ss" />
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${billTime}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${settleTime}" /></td>
				
				<td>${bill.createMan}</td>
				<td>${bill.settleMan}</td>
				
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.foodAmt}" /></td> 
				<td> <fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.roundAmt}" /></td>
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.presentAmt}" /></td>  
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.payAmt}" /></td> 
				
				<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${bill.disAmt}" /></td> 
				
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
	</table>
</div>
