<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div style="float:left; width:150px">
	<table style="width:150px" class="hovertable" border="1" cellpadding="5" cellspacing="1">
		<tr class="highlight" align="center" >
			<td>总要货金额</td>
		</tr>
		<tr class="highlight" align="center" >
			<td>${payamt}</td>
		</tr>
	</table>
</div>


<div style="margin-left:5px; height: 100%;overflow:auto;">
	<table id="datazone" style="width: 1800px;border-top: 0px" class="hovertable"  border="1" cellpadding="5" cellspacing="1">
		<tr class="highlight" align="center">
			<c:forEach var="item" items="${headMap}">
				<td style="width: auto;" colspan="${item.value}">${item.key}</td>
			</c:forEach>
		</tr>
		
		<tr class="highlight" align="center">
			<c:forEach var="item" items="${categoryLst}">
				<td style="width: auto;">
					<a href="#" onclick="queryDetail('${item.subCategoryId}','${item.subCategoryName}');">
						${item.subCategoryName}
					</a>
				</td>
			</c:forEach>
		</tr>
  </table>
</div>