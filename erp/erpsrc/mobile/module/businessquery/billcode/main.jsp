<!--
 	Copyright (c) 2014
 	Tanry Electronic Technology Co., Ltd.
	ChangSha, China

	All Rights Reserved.
	
	First created on Aug 20, 2014 by liyzh
	Last edited on Aug 20, 2014 by liyzh
	
	说明： 移动端单号查询，主界面
-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/mobile/common/jsp/header.jsp"%>

<script type="text/javascript"
	src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
<script type="text/javascript"
	src="<%=appRoot%>/mobile/module/businessquery/billcode/main.js?Version=<%=currenttime %>"></script>

<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-user"></i>单号查询
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
							<td class="label_right">门店：</td>
							<td class="text_left">
								<s:select list="shopLst" listKey="code" listValue="name" theme="simple"
									 style="width: 120px;" id="shopC" name="shopC"></s:select>
							</td>
						</tr>
						<tr>
							<td class="label_right">单据号：</td>
							<td class="text_left">
								<input type="text" id="billCode" name="billCode" value="">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div style="padding-left: 50px;">
									<input class="btn btn-primary" type="button"
										 onclick="doQuery();" value="查询" />
								</div>
							</td>
						</tr>
					</table>
				</form>
				
				<table id="dataTable">
				  <thead>
				    <tr>
					    <th>单据编号</th>
					    <th>市别</th>
					    <th>班次</th>
					    <th>人数</th>
					    <th>开台时间</th>
					    <th>结账时间</th>
					    <th>开台人</th>
					    <th>结账人</th>
					    <th>消费金额</th>
					    <th>舍尾金额</th>
					    <th>赠送金额</th>
					    <th>付款金额</th>
					    <th>总折扣额</th>
				    </tr>
				  </thead>
				  <tbody id="databody">
				  </tbody>
				</table>

			</div>
		</div>
	</div>
	<!--/span-->
</div>
<!--/row-->
<%@ include file="/mobile/common/jsp/footer.jsp"%>