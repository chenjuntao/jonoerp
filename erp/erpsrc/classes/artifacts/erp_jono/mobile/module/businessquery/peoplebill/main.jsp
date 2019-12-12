<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/mobile/common/jsp/header.jsp"%>

<script type="text/javascript"
	src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
<script language="javascript" type="text/javascript"
	src="<%=appRoot%>/mobile/module/businessquery/peoplebill/main.js?Version=<%=currenttime %>"></script>

<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-user"></i>根据人员查询单据统计信息
				</h2>
			</div>
			<div class="box-content">

				<form id="queryForm">
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<table class="hovertable" width="100%" align="center">
						<tr>
							<td class="label_right" style="width: 120px;">开始日期：</td>
							<td><input id="startDate" name="startDate"
								value="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" 
								 /></td>
						</tr>
						<tr>
							<td class="label_right" style="width: 120px;">结束日期：</td>
							<td><input id="endDate" name="endDate" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
								 /></td>
						</tr>
						<tr>
							<td class="label_right">开台人：</td>
							<td class="text_left">
								<select id="createMan" name="createMan">
									<option data="" value="all">--请选择--</option>
 									<c:forEach var="people" items="${createMans}" varStatus="status">
									<option data="${people.peopleC}" value="${status.index}">${people.peopleName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="label_right">结账人：</td>
							<td class="text_left">
								<select id="settleMan" name="settleMan">
									<option data="" value="all">--请选择--</option>
 									<c:forEach var="people" items="${settleMans}" varStatus="status">
									<option data="${people.peopleC}" value="${status.index}">${people.peopleName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td class="label_right">照单折扣人：</td>
							<td class="text_left">
								<select id="disCurMan" name="disCurMan">
									<option data="" value="all">--请选择--</option>
 									<c:forEach var="people" items="${disCurMans}" varStatus="status">
									<option data="${people.peopleC}" value="${status.index}">${people.peopleName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div style="padding-left: 50px;">
									<button class="btn btn-primary" onclick="doQuery();return false;">查询</button>
								</div>
							</td>
						</tr>
					</table>
				</form>
				<div id="dataGrid"></div>

			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<%@ include file="/mobile/common/jsp/footer.jsp"%>