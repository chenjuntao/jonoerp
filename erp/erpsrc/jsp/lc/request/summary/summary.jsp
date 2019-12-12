<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>request bill summary</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/summary/summary.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/summary/summary.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='commonHeader.formId' value='${commonHeader.formId }' />
	  	<input type="hidden" id="ids" name="ids" value='${ids }' />
		<input type="hidden" name="commonHeader.requesterId" value='${commonHeader.requesterId }' />
		<input type='hidden' name='commonHeader.formMakerId' value='${commonHeader.formMakerId }' />
		<input type='hidden' name='commonHeader.formMaker' value='${commonHeader.formMaker }' />
		<input type='hidden' name='commonHeader.formTime' value='${formTime }' />
		<input type='hidden' name='commonHeader.receiveTime' value='${receiveTime }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">汇总部门：</td>
				<td class="text_left">
					<span>物流中心</span>
				</td>
				
				<td class="label_right">收货仓库：</td>
				<td class="text_left" colspan="3">
					<s:select list="storeLst" listKey="storageId" listValue="storageName" theme="simple"
						style="width: 180px;" id="storageId" name="commonHeader.storageId"></s:select>
					<input type="hidden" id="storage" name="commonHeader.storage" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left" style="width: 120px;">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right" style="width: 60px;">制单日期：</td>
				<td class="text_left" style="width: 120px;">
					<span>${formTime }</span>
				</td>
				
				<td class="label_right" style="width: 60px;">到货日期：</td>
				<td class="text_left">
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="commonHeader.formNote" value="${commonHeader.formNote}" style="width: 380px;" />
			    	<input type="button" onclick="doSubmit();" value="生成采购单" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>
	
	<div id="deliveryTc">
		<div id="directCp">
			<table class="hovertable" width="100%" border="1">
<!-- 						<b style="padding-left: 8px;">餐厅要货（直配）</b> -->
				<tr>
					<td colspan="4">
						<table style="width: 100%;border-top: 0px;" class="hovertable">
							<tr>
								<th style="width: 20px;">序号</th>
								<th style="width: 140px;">供应商</th>
<!-- 								<th style="width: 140px;">门店</th> -->
								<th></th>
							</tr>
						
							<c:forEach var="bill" items="${summary.direct2}" varStatus="status">
								<tr class="highlight" align="center">
									<td style="width: 80px;">${status.index + 1}</td>
									<td>${bill.provider}</td>
<%-- 									<td>${bill.buyerName}</td> --%>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td colspan="3">
										<table style="width: 100%;" class="hovertable">
											<tr>
												<th>序号</th>
												<th>原料编码</th>
												<th>原料名称</th>
												<th>原料单位</th>
												<th>规格</th>
												<th>门店</th>
												<th>订单编号</th>
												<th>订货数量</th>
												<th>合计</th>
												<th></th>
											</tr>
											<c:forEach var="item" items="${bill.details}" varStatus="istatus">
												<c:set var="rSpan" value="${fn:length(item.details) }" />
												<tr class="highlight" align="center">
													<td style="width: 80px;" rowspan="${rSpan }">${istatus.index + 1}</td>
													<td rowspan="${rSpan }">${item.itemId}</td>
													<td rowspan="${rSpan }">${item.itemName}</td>
													<td rowspan="${rSpan }">${item.itemDimension}</td>
													<td rowspan="${rSpan }">${item.itemSpecification}</td>
													<td rowspan="${rSpan }">${item.buyerName}</td>
													<td>${item.details[0].formId}</td>
													<td>${item.details[0].orderCount}</td>
													<td rowspan="${rSpan }">${item.total}</td>
													<td rowspan="${rSpan }"></td>
												</tr>
												<c:forEach var="detail" items="${item.details}" varStatus="dstatus">
													<c:if test="${dstatus.index gt 0}"> <!-- 从第二项开始遍历 -->
														<tr align="center">
															<td>${detail.formId}</td>
															<td>${detail.orderCount}</td>
														</tr> 
													</c:if>
												</c:forEach>
											</c:forEach>
										</table>
									</td>
								</tr>
							</c:forEach>
						</table>
		<!-- 						未分组表格 -->
		<!-- 					<table style="width: 100%;border-top: 0px;" class="hovertable"  border="1" cellpadding="5" -->
		<!-- 						cellspacing="1"> -->
		<!-- 						<tr> -->
		<!-- 							<th>序号</th> -->
		<!-- 							<th>供应商</th> -->
		<!-- 							<th>门店</th> -->
		<!-- 							<th>原料编码</th> -->
		<!-- 							<th>原料名称</th> -->
		<!-- 							<th>原料单位</th> -->
		<!-- 							<th>规格</th> -->
		<!-- 							<th>订货数量</th> -->
		<!-- 						</tr> -->
		<%-- 						<c:forEach var="item" items="${summary.direct}" varStatus="status"> --%>
		<!-- 							<tr class="highlight" align="center"> -->
		<%-- 								<td style="width: 80px;">${status.index + 1}</td> --%>
		<%-- 								<td>${item.provider}</td> --%>
		<%-- 								<td>${item.buyerName}</td> --%>
		<%-- 								<td>${item.itemId}</td> --%>
		<%-- 								<td>${item.itemName}</td> --%>
		<%-- 								<td>${item.itemDimension}</td> --%>
		<%-- 								<td>${item.itemSpecification}</td> --%>
		<%-- 								<td>${item.orderCount}</td> --%>
		<!-- 								<td></td> -->
		<!-- 							</tr> -->
		<%-- 						</c:forEach> --%>
		<!-- 					</table> -->
					</td>
				</tr>
			</table>
		</div>
		
		<div id="crossCp">
			<table class="hovertable" width="100%" border="1">
<!-- 						<b style="padding-left: 8px;">餐厅要货（越库）</b> -->
				<tr>
					<td colspan="4">
						<table style="width: 100%;border-top: 0px;" class="hovertable">
							<tr>
								<th style="width: 20px;">序号</th>
								<th style="width: 140px;">供应商</th>
								<th></th>
							</tr>
						
							<c:forEach var="bill" items="${summary.cross}" varStatus="status">
								<tr class="highlight" align="center">
									<td style="width: 80px;">${status.index + 1}</td>
									<td>${bill.provider}</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td colspan="3">
										<table style="width: 100%;" class="hovertable">
											<tr>
												<th>序号</th>
												<th>原料编码</th>
												<th>原料名称</th>
												<th>原料单位</th>
												<th>规格</th>
												<th>门店</th>
												<th>订单编号</th>
												<th>订货数量</th>
												<th>合计</th>
												<th></th>
											</tr>
											<c:forEach var="item" items="${bill.details}" varStatus="istatus">
												<c:set var="rSpan" value="${fn:length(item.details) }" />
												<tr class="highlight" align="center">
													<td style="width: 80px;" rowspan="${rSpan }">${istatus.index + 1}</td>
													<td rowspan="${rSpan }">${item.itemId}</td>
													<td rowspan="${rSpan }">${item.itemName}</td>
													<td rowspan="${rSpan }">${item.itemDimension}</td>
													<td rowspan="${rSpan }">${item.itemSpecification}</td>
													<td rowspan="${rSpan }">${item.buyerName}</td>
													<td>${item.details[0].formId}</td>
													<td>${item.details[0].orderCount}</td>
													<td rowspan="${rSpan }">${item.total}</td>
													<td rowspan="${rSpan }"></td>
												</tr>
												<c:forEach var="detail" items="${item.details}" varStatus="dstatus">
													<c:if test="${dstatus.index gt 0}"> <!-- 从第二项开始遍历 -->
														<tr align="center">
															<td>${detail.formId}</td>
															<td>${detail.orderCount}</td>
														</tr> 
													</c:if>
												</c:forEach>
											</c:forEach>
										</table>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="unifiedCp">
			<table class="hovertable" width="100%" border="1">
<!-- 						<b style="padding-left: 8px;">餐厅要货（统配）、外部订货、需求预估</b> -->
				<tr>
					<td colspan="4">
						<table style="width: 100%;border-top: 0px;" class="hovertable">
							<tr>
								<th style="width: 20px;">序号</th>
								<th style="width: 140px;">供应商</th>
								<th></th>
							</tr>
						
							<c:forEach var="bill" items="${summary.unified2}" varStatus="status">
								<tr class="highlight" align="center">
									<td style="width: 80px;">${status.index + 1}</td>
									<td>${bill.provider}</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td colspan="3">
										<table style="width: 100%;" class="hovertable">
											<tr>
												<th>序号</th>
												<th>原料编码</th>
												<th>原料名称</th>
												<th>原料单位</th>
												<th>规格</th>
												<th>订单编号</th>
												<th>门店</th>
												<th>订货数量</th>
												<th>毛需求</th>
												<th>已分配量</th>
												<th>安全库存</th>
												<th>实际库存</th>
												<th>在途量</th>
												<th>净需求</th>
												<th></th>
											</tr>
											<c:forEach var="item" items="${bill.details}" varStatus="istatus">
												<c:set var="rSpan" value="${fn:length(item.details) }" />
												<tr class="highlight" align="center">
													<td style="width: 80px;" rowspan="${rSpan }">${istatus.index + 1}</td>
													<td rowspan="${rSpan }">${item.itemId}</td>
													<td rowspan="${rSpan }">${item.itemName}</td>
													<td rowspan="${rSpan }">${item.itemDimension}</td>
													<td rowspan="${rSpan }">${item.itemSpecification}</td>
													<td>${item.details[0].formId}</td>
													<td>${item.details[0].buyerName}</td>
													<td>${item.details[0].orderCount}</td>
													<td rowspan="${rSpan }">${item.total}</td>
													<td rowspan="${rSpan }">${item.allocatedCount}</td>
													<td rowspan="${rSpan }">${item.safeCount}</td>
													<td rowspan="${rSpan }">${item.realCount}</td>
													<td rowspan="${rSpan }">${item.roadCount}</td>
													<td rowspan="${rSpan }">${item.netCount}</td>
													<td rowspan="${rSpan }"></td>
												</tr>
												<c:forEach var="detail" items="${item.details}" varStatus="dstatus">
													<c:if test="${dstatus.index gt 0}"> <!-- 从第二项开始遍历 -->
														<tr align="center">
															<td>${detail.formId}</td>
															<td>${detail.buyerName}</td>
															<td>${detail.orderCount}</td>
														</tr> 
													</c:if>
												</c:forEach>
											</c:forEach>
										</table>
									</td>
								</tr>
							</c:forEach>
						</table>
					
		<!-- 						未分组表格 -->
		<!-- 					<table style="width: 100%;border-top: 0px;" class="hovertable"  border="1" cellpadding="5" -->
		<!-- 						cellspacing="1"> -->
		<!-- 						<tr> -->
		<!-- 							<th>序号</th> -->
		<!-- 							<th>供应商</th> -->
		<!-- 							<th>原料编码</th> -->
		<!-- 							<th>原料名称</th> -->
		<!-- 							<th>原料单位</th> -->
		<!-- 							<th>规格</th> -->
		<!-- 							<th>门店</th> -->
		<!-- 							<th>订货数量</th> -->
		<!-- 						</tr> -->
		<%-- 						<c:forEach var="item" items="${summary.unified}" varStatus="status"> --%>
		<!-- 							<tr class="highlight" align="center"> -->
		<%-- 								<td style="width: 80px;">${status.index + 1}</td> --%>
		<%-- 								<td>${item.provider}</td> --%>
		<%-- 								<td>${item.itemId}</td> --%>
		<%-- 								<td>${item.itemName}</td> --%>
		<%-- 								<td>${item.itemDimension}</td> --%>
		<%-- 								<td>${item.itemSpecification}</td> --%>
		<%-- 								<td>${item.buyerName}</td> --%>
		<%-- 								<td>${item.orderCount}</td> --%>
		<!-- 								<td></td> -->
		<!-- 							</tr> -->
		<%-- 						</c:forEach> --%>
		<!-- 					</table> -->
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>

</html>
