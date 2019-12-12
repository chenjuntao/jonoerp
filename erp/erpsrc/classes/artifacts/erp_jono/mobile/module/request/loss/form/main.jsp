<!--
 	Copyright (c) 2015
 	Tanry Electronic Technology Co., Ltd.
	ChangSha, China

	All Rights Reserved.
	
	说明： 移动端单号查询，主界面
-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/mobile/common/jsp/header.jsp"%>
<script type="text/javascript"	src="<%=appRoot%>/jsp/common/js/codeFmt.js"></script>
<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
<script type="text/javascript"
	src="<%=appRoot%>/mobile/module/request/loss/form/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript">
		var startDate = '${startDate}';
		var endDate = '${endDate}';
		var loginBranchId = '${loginBranchId}';
	</script>
<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-user"></i>餐厅报损单查询
				</h2>
			</div>
			<div class="box-content">
				<form id="queryForm">
					<table class="hovertable" width="100%" align="center">
					<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
						<input type="hidden" id="branchId" value="${branchId}" name="branchId"/>
						<tr>
							<td class="label_right" style="width: 80px;">制单日期：</td>
							<td><input type="text" id="startDate" value="" style="width: 130px;" />
								<span style="padding: 0 5px;">至</span>
								<input type="text" id="endDate" value="" style="width: 130px;" />
							</td>
						</tr>
						<tr>
							<td colspan="4">
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
						<th>报损日期</th>
						<th>单据编号</th>
						<th>审核操作</th>
						<th>审核日期</th>
						<th>报损部门</th>
						<th>报损仓库</th>
						<th>制单人员</th>
						<th>审核人员</th>
						<th>备注信息</th>
						<th>主要报损品</th>
						<th>总金额</th>
						<th>审核状态</th>
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