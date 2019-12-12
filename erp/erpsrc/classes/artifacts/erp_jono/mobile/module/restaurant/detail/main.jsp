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

<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />
<script type="text/javascript"
	src="<%=appRoot%>/mobile/module/restaurant/detail/main.js?Version=<%=currenttime %>"></script>
<div class="row">
	<div class="box col-md-12">
		<div class="box-inner">
			<div class="box-header well" data-original-title="">
				<h2>
					<i class="glyphicon glyphicon-user"></i>餐厅仓库明细帐
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
							<td  class="label_right" style="width: 120px;">业务类型:	</td>
				<td>
				<select id="reason">
					  <option value ="head" selected="selected"><---请选择---></option>
					  <option value ="CGRK">餐厅采购入库</option>
					  <option value="PSRK">餐厅配送入库</option>
					  <option value="PSFSH">餐厅配送反审核</option>
					  <option value="PSTH">餐厅配送退货</option>
					  <option value="CGTH">餐厅采购退货</option>
					  <option value="CPBS">餐厅出品报损</option>
					  <option value="YLBS">餐厅原料报损</option>
					  <option value="DB">餐厅调拨</option>
					  <option value="PD">餐厅盘点</option>
					  <option value="LLKU">餐厅理论扣库</option>
				</select>
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
						<th>门店</th>
						<th>操作时间</th>
						<th>单据日期</th>
						<th>业务类型</th>
						<th>期初数量</th>
						<th>入库数量（盘盈）</th>
						<th>入库金额</th>
						<th>出库数量（盘亏）</th>
						<th>出库金额</th>
						<th>结存数量</th>
						<th>结存单价</th>
						<th>结存金额</th>
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