<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>check summary</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/summary/check.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/summary/summary.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var noProvider = '${summary.noProvider}';
	</script>
</head>

<body class="claro">
	<form id="billForm" method="post" >
		<s:token/>
		
		<input type='hidden' id="formId" name='commonHeader.formId' value='${commonHeader.formId }' />
	  	<input type="hidden" id="ids" name="ids" value='${ids }' />
	  	<input type="hidden" name="jsonData" value='${jsonData }' />
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
				<td class="text_left">
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
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="commonHeader.formNote" value="${commonHeader.formNote}" style="width: 380px;" />
			    	<input type="button" onclick="doSave();" value="确认生成采购单" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
	</form>
	
	<p class="area_blank">&nbsp;</p>
			
	<div id="deliveryTc">
		<div id="directCp">
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td colspan="4">
						<table style="width: 100%;border-top: 0px;" class="hovertable">
							<tr>
								<th style="width: 20px;">序号</th>
								<th style="width: 140px;">供应商</th>
								<th style="width: 140px;">到货日期</th>
								<th></th>
							</tr>
						
							<c:forEach var="bill" items="${summary.direct2}" varStatus="status">
								<tr class="highlight" align="center">
									<td style="width: 80px;">${status.index + 1}</td>
									<td>${bill.provider}</td>
									<td>${bill.receiveTime}</td>
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
												<th>订货数量</th>
												<th>标准单价</th>
												<th>标准金额</th>
												<th>进货单价</th>
												<th>进货金额</th>
												<th></th>
											</tr>
											<c:forEach var="item" items="${bill.details}" varStatus="istatus">
												<c:set var="rSpan" value="${fn:length(item.details) }" />
												<tr class="highlight" align="center">
													<td style="width: 80px;">${istatus.index + 1}</td>
													<td>${item.itemId}</td>
													<td>${item.itemName}</td>
													<td>${item.itemDimension}</td>
													<td>${item.itemSpecification}</td>
													<td>${item.buyerName}</td>
													<td>${item.orderCount}</td>
													<td>${item.itemUnitPrice}</td>
													<td>${item.payAmt}</td>
													<td>${item.itemPrice}</td>
													<td>${item.unitAmt}</td>
													<td></td>
												</tr>
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
		
		<div id="crossCp">
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td colspan="4">
						<table style="width: 100%;border-top: 0px;" class="hovertable">
							<tr>
								<th style="width: 20px;">序号</th>
								<th style="width: 140px;">供应商</th>
								<th style="width: 140px;">到货日期</th>
								<th></th>
							</tr>
						
							<c:forEach var="bill" items="${summary.cross}" varStatus="status">
								<tr class="highlight" align="center">
									<td style="width: 80px;">${status.index + 1}</td>
									<td>${bill.provider}</td>
									<td>${bill.receiveTime}</td>
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
												<th>订货数量</th>
												<th>标准单价</th><!-- 进货价还需再修改显示 -->
												<th>标准金额</th>
												<th>进货单价</th>
												<th>进货金额</th>
												<th></th>
											</tr>
											<c:forEach var="item" items="${bill.details}" varStatus="istatus">
												<c:set var="rSpan" value="${fn:length(item.details) }" />
												<tr class="highlight" align="center">
													<td style="width: 80px;">${istatus.index + 1}</td>
													<td>${item.itemId}</td>
													<td>${item.itemName}</td>
													<td>${item.itemDimension}</td>
													<td>${item.itemSpecification}</td>
													<td>${item.buyerName}</td>
													<td>${item.orderCount}</td>
													<td>${item.itemUnitPrice}</td>
													<td>${item.payAmt}</td>
													<td>${item.itemPrice}</td>
													<td>${item.unitAmt}</td>
													<td></td>
												</tr>
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
				<tr>
					<td colspan="4">
						<table style="width: 100%;border-top: 0px;" class="hovertable">
							<tr>
								<th style="width: 20px;">序号</th>
								<th style="width: 140px;">供应商</th>
								<th style="width: 140px;">到货日期</th>
								<th></th>
							</tr>
						
							<c:forEach var="bill" items="${summary.unified2}" varStatus="status">
								<tr class="highlight" align="center">
									<td style="width: 80px;">${status.index + 1}</td>
									<td>${bill.provider}</td>
									<td>${bill.receiveTime}</td>
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
												<th>订货数量</th>
												<th>标准单价</th><!-- 相应供应商的价格还需再修改 -->
												<th>标准金额</th>
												<th>进货单价</th>
												<th>进货金额</th>
												<th></th>
											</tr>
											<c:forEach var="item" items="${bill.details}" varStatus="istatus">
												<c:set var="rSpan" value="${fn:length(item.details) }" />
												<tr class="highlight" align="center">
													<td style="width: 80px;">${istatus.index + 1}</td>
													<td>${item.itemId}</td>
													<td>${item.itemName}</td>
													<td>${item.itemDimension}</td>
													<td>${item.itemSpecification}</td>
													<td>${item.orderCount}</td>
													<td>${item.itemUnitPrice}</td>
													<td>${item.payAmt}</td>
													<td>${item.itemPrice}</td>
													<td>${item.unitAmt}</td>
													<td></td>
												</tr>
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
	</div>
</body>

</html>
