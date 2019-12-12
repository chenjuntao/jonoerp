<!--
 	Copyright (c) 2014
 	Tanry Electronic Technology Co., Ltd.
	ChangSha, China

	All Rights Reserved.
	
	First created on Aug 4, 2014 by liyzh
	Last edited on Aug 6, 2014 by liyzh
	
	说明： 移动端付款方式查询，主界面
-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/mobile/common/jsp/header.jsp"%>

<script type="text/javascript"
	src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
<script type="text/javascript"
	src="<%=appRoot%>/mobile/module/businessquery/paymentquery/main.js?Version=<%=currenttime %>"></script>

<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-user"></i>付款方式查询
				</h2>
			</div>
			<div class="box-content">
				<form id="queryForm">
					<table class="hovertable" width="100%" align="center">
						<tr>
							<td class="label_right" style="width: 120px;">开始日期：</td>
							<td><input id="startDate" name="startDate"
								value="${startDate}" /></td>
						</tr>
						<tr>
							<td class="label_right">结束日期：</td>
							<td><input id="endDate" name="endDate" value="${endDate}" /></td>
						</tr>
						<tr>
							<td class="label_right">统计方式：</td>
							<td><select id="type">
									<option value="1">累计</option>
									<option value="2">按天</option>
							</select></td>
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