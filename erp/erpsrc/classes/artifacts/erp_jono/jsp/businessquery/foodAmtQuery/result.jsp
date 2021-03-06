<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<div style="width: 100%;height: 100%;overflow:auto;">

	<table id="datazone" style="width: 1800px;border-top: 0px;" class="hovertable"  border="1" cellpadding="5" cellspacing="1">
			<tr>
				<td colspan="30">当前选择的门店在指定的时间段内总营业额为：${allAmt}，以下为按照类别进行分类的各种出品数量：</td>
			</tr>
			<tr class="highlight" align="center">
				<c:forEach var="item" items="${headMap}">
						<td style="width: auto;" colspan="${item.value}">${item.key}</td>
				</c:forEach>
			</tr>
			
			<tr class="highlight" align="center">
				<c:forEach var="item" items="${foodAmtCategoryList}">
						<td style="width: auto;"><a href="#" onclick="doDetailCategoryQuery('${item.subCategoryId}','${item.subCategoryName}');">${item.subCategoryName}</a></td>
				</c:forEach>
			</tr>
			
			<tr class="highlight" align="center">
				<c:forEach var="item" items="${foodAmtCategoryList}">
						<td style="width: auto;"><fmt:formatNumber type="number" value="${item.foodCount}" maxFractionDigits="2"/></td>
				</c:forEach>
			</tr>
	</table>

</div>