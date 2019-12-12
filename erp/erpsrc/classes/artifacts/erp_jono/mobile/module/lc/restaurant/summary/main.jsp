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
	src="<%=appRoot%>/mobile/module/lc/restaurant/summary/main.js?Version=<%=currenttime %>"></script>
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
					<i class="glyphicon glyphicon-user"></i>物流中心仓库汇总查询
				</h2>
			</div>
			<div class="box-content">
				<form id="queryForm">
					<table class="hovertable" width="100%" align="center">
					<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
						<input type="hidden" id="branchId" value="${branchId}" name="branchId"/>
						<tr>
							<td class="label_right" style="width: 80px;">日期：</td>
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
						<th>仓库</th>
						<th>原料编码</th>
						<th>原料名称</th>
						<th>单位</th>
						<th>期初数量</th>
						<th>期初金额</th>
						<th>入库数量合计</th>
						<th>入库金额合计</th>
						<th>出库数量合计</th>
						<th>出库金额合计</th>
						<th>结存数量</th>
						<th>结存金额</th>
						<th>采购入库数量</th>
						<th>配送入库数量</th>
						<th>配送反审核入库数量</th>
						<th>配送反审核出库数量</th>
						<th>配送退货数量</th>
						<th>采购退货数量</th>
						<th>原料报损数量</th>
						<th>出品报损数量</th>
						<th>调拨入库数量</th>
						<th>调拨出库数量</th>
						<th>盘盈数量</th>
						<th>盘亏数量</th>
						<th>理论扣库数量</th>
						<th>门店名称</th>
						<th>原料类型</th>
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