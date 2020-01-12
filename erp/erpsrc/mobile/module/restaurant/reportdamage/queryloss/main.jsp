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
	src="<%=appRoot %>/mobile/common/js/form.js"></script>
<script type="text/javascript"
	src="<%=appRoot%>/mobile/module/restaurant/reportdamage/queryloss/main.js?Version=<%=currenttime %>"></script>


<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-user"></i>原料报损查询
				</h2>
			</div>
			<div class="box-content">
				<form id="queryForm">
					<table class="hovertable" width="100%" align="center">
						<tr>
							<td class="label_right" style="width: 80px;">制单日期：</td>
							<td><input type="text" id="startDate" value="" style="width: 80px;" />
								<span style="padding: 0 5px;">至</span>
								<input type="text" id="endDate" value="" style="width: 80px;" />
							</td>
						</tr>
						<tr>
							<td class="label_right">部门：</td>
							<td class="text_left">
								<s:select list="shopList" listKey="code" listValue="name" theme="simple"
									 style="width: 120px;" id="branchId" headerKey="" headerValue="--请选择--" ></s:select>
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
						<th>序号</th>
						<th>报损单编号</th>
						<th>审核</th>
						<th>报损部门</th>
						<th>报损人员</th>
						<th>报损日期</th>
						<th>审核人员</th>
						<th>审核日期</th>
						<th>处理人员</th>
						<th>处理日期</th>
						<th>备注</th>
						<th>主要报损品</th>
						<th>报损总额</th>
						<th>单据状态</th>
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