<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>

<div style="width: 100%;height: 100%;overflow:auto;">
	<table id="datazone" style="width: 100%;border-top: 0px;" class="hovertable"  border="1" cellpadding="5"
		cellspacing="1">
		<c:forEach var="record" items="${importLst}">
			<tr class="highlight" align="center">
				<td style="width: 180px;">${record.fileId}</td>
				<td style="width: 80px;">${record.shopId}</td>
				<td style="width: 200px;">${record.importTime}</td>
				<td style="width: 200px;">${record.createTime}</td>
				<td style="width: 180px;">${record.reason}</td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
</div>