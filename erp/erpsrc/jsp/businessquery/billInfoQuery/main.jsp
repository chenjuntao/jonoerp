<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script src='<%=appRoot%>/jsp/common/lib/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/businessquery/billInfoQuery/main.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/billInfoQuery/export.js?Version=<%=currenttime %>"></script>
	
	<link id="themeStyles" rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
</head>

<body bgcolor="white" class="claro">
		<table class="hovertable" width="100%" border="1" cellpadding="6"
			cellspacing="2" bordercolor="#FEB5AE" bgcolor="#E9EFF5"
			align="center">

			<tr valign="middle">
				<td colspan="5">
					<div align="left">
						<b>单据统计信息</b>
					</div>
				</td>
			</tr>

			<tr valign="middle">
				<td width="200">&nbsp;开始日期：${startDate}</td>
				<td width="200">&nbsp;结束日期：${endDate}</td>
				<td width="200">&nbsp;门店编号：${shopC}</td>
				<td width="200">&nbsp;门店名称：${shopName}</td>
				<td width="200">&nbsp;台位名称：${table}</td>
			</tr>
				<input type="hidden"  id="startDate" name="startDate" value="${startDate}"/>
				<input type="hidden"  id="endDate"	name="endDate" value="${endDate}"/>
				<input type="hidden"  id="shopC"	name="shopC" value="${shopC}"/>
				<input type="hidden"  id="table"	name="table" value="${table}"/>
		</table>

		<div id="pagelist">
			<button
				onclick='require([ "dojo/_base/xhr" ], function(xhr) {  
	        xhr.post({
		    	url : "listPageBill.action?shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${table}&currentPage=1&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("billListResult").innerHTML = data;
					dojo.byId("currentPageTxt").value = ${currentPage};
					return;
	            },  
	            error : function(error) {
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>首页</button>
	    
			<button
				onclick='require([ "dojo/_base/xhr" ], function(xhr) {
			var newPage = parseInt(dojo.byId(currentPageTxt).value) -1;
			if(newPage <1) {
				newPage = 1;
				return;
			}
	        xhr.post({
		    	url : "listPageBill.action?shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${table}&currentPage="+newPage+"&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("billListResult").innerHTML = data;
					dojo.byId("currentPageTxt").value = newPage;
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>上一页</button>
	    
			<input id="currentPageTxt" type="text" value="${currentPage}" 	style="width: 50px;" />
				
			<button
				onclick='require([ "dojo/_base/xhr" ], function(xhr) { 
				var newPage = parseInt(dojo.byId(currentPageTxt).value) +1;
				if(newPage > ${pageCount}){
					newPage = ${pageCount};
					return;
				}
	        xhr.post({
		    	url : "listPageBill.action?shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${table}&currentPage="+ newPage +"&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("billListResult").innerHTML = data;
					dojo.byId("currentPageTxt").value = newPage;
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>下一页</button>
	    
			<button
				onclick='require([ "dojo/_base/xhr" ], function(xhr) { 
	        xhr.post({
		    	url : "listPageBill.action?shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${table}&currentPage=${pageCount}&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("billListResult").innerHTML = data;
					dojo.byId("currentPageTxt").value = ${pageCount};
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>尾页</button>
	    
			共${pageCount}页
			<button
				onclick='require([ "dojo/_base/xhr" ], function(xhr) {  
    		var newPage = dojo.byId("currentPageTxt").value;
    		if(isNaN(newPage)){
    			alert("请输入正确的数值！");
    			return;
    		}
    		
    		if(newPage >= ${pageCount}){
    			newPage = ${pageCount};
    			dojo.byId("currentPageTxt").value = newPage;
    		}
    		
    		if(newPage < 1){
    			newPage = 1;
    			dojo.byId("currentPageTxt").value = newPage;
    		}
    		
	        xhr.post({
		    	url : "listPageBill.action?shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${table}&currentPage="+newPage+"&pageCount=${pageCount}",
	            handleAs : "text",  
	            load : function(data) {
					dojo.byId("billListResult").innerHTML = data;
					return;
	            },  
	            error : function(error) {  
	                showDojoDialog(error);
	            }  
	        });  
	    }); '>跳转</button>
	    
	    	<input type="button" onclick="exportXls();" value="导出EXCEL">
			<input type="button" onclick="exportCsv();" value="导出文本">
		</div>

		<div  id="billListResult" style="width: 100%; overflow: auto;">
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

					<c:set var="roundAmtTal" value="${ roundAmtTal + bill.roundAmt}" />
					<c:set var="disAmtTal" value="${ disAmtTal + bill.disAmt}" />
					<c:set var="presentAmtTal" value="${ presentAmtTal + bill.presentAmt}" />
					<c:set var="oughtAmtTal" value="${ oughtAmtTal + bill.oughtAmt}" />
					<c:set var="roundAmtTal" value="${ roundAmtTal + bill.roundAmt}" />
					<c:set var="payAmtTal" value="${ payAmtTal + bill.payAmt}" />

					<tr onmouseover="this.style.backgroundColor='#ffff66';"
						onmouseout="this.style.backgroundColor='#d4e3e5';" align="center">
						<td><a
							href='javascript: parent.addTab("${shopName}-${table}-${bill.billC}", "businessquery/foodlist.action?billC=${bill.billC}&shopC=${shopC}&startDate=${startDate}&endDate=${endDate}&table=${table}");'
							>${bill.billC}</a></td>
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

						<td><fmt:formatNumber type="number"
								value="${bill.foodAmt}"  maxFractionDigits="2"/></td>
						<td><fmt:formatNumber type="number"
								value="${bill.disAmt}"  maxFractionDigits="2"/></td>
						<td><fmt:formatNumber type="number"
								value="${bill.presentAmt}" maxFractionDigits="2"/></td>
						<td><fmt:formatNumber type="number"
								value="${bill.oughtAmt}" maxFractionDigits="2" /></td>
						<td><fmt:formatNumber type="number"
								value="${bill.roundAmt}" maxFractionDigits="2" /></td>
						<td><fmt:formatNumber type="number"
								value="${billBean.payAmt}"  maxFractionDigits="2"/></td>
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

					<td><b><fmt:formatNumber type="number"
								value="${foodAmtTal}" maxFractionDigits="2" /></b></td>
					<td><b><fmt:formatNumber type="number"
								value="${disAmtTal}" maxFractionDigits="2" /></b></td>
					<td><b><fmt:formatNumber type="number"
								value="${presentAmtTal}" maxFractionDigits="2" /></b></td>
					<td><b><fmt:formatNumber type="number"
								value="${oughtAmtTal}" maxFractionDigits="2" /></b></td>
					<td><b><fmt:formatNumber type="number"
								value="${roundAmtTal}" maxFractionDigits="2" /></b></td>
					<td><b><fmt:formatNumber type="number"
								value="${payAmtTal}" maxFractionDigits="2" /></b></td>
				</tr>
			</table>
		</div>

		<div id="msgDlg"
			style="height: 300px; overflow: scroll; display: none;">
			<div id="msgContent" style="height: 100%; overflow: scroll;"></div>
		</div>
	
</body>
</html>
