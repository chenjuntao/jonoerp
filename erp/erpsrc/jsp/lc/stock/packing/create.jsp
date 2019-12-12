<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create packing bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/packing/create.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/packing/create.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
	
		<input type='hidden' name="formRefId" value='${formRefId }' />
		<input type='hidden' name='packingHeader.formMakerId' value='${packingHeader.formMakerId }' />
		<input type='hidden' name='packingHeader.formMaker' value='${packingHeader.formMaker }' />
		<input type='hidden' name='packingHeader.formTime' value='${formTime }' />
		
	 	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 70px;">制单人员：</td>
				<td class="text_left" style="width: 140px;">
					<span>${packingHeader.formMaker }</span>
				</td>
				
				<td class="label_right" style="width: 70px;">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="packingHeader.formNote" value="${packingHeader.formNote}" style="width: 380px;" />
	  				
			    	<input type="button" value="确认生成装箱单" onclick="doSubmit();" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
		
		<div id="dataGrid">	</div>
	</form>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="4">
				<table style="width: 100%;border-top: 0px;" class="hovertable">
					<tr>
						<th style="width: 20px;">序号</th>
						<th style="width: 140px;">箱子编号</th>
						<th style="width: 140px;">餐厅</th>
						<th style="width: 140px;">箱子类型</th>
						<th style="width: 140px;">重量</th>
						<th style="width: 140px;">累计重量</th>
						<th style="width: 140px;">体积</th>
						<th style="width: 140px;">累计体积</th>
						<th></th>
					</tr>
				
					<c:forEach var="box" items="${boxMap}" varStatus="status">
						<tr class="highlight" align="center">
							<td style="width: 80px;">${status.index + 1}</td>
							<td>${box.value.boxId}</td>
							<td>${box.value.branchName}</td>
							<td>${box.value.boxTypeName}</td>
							<td>${box.value.boxWeight}</td>
							<td>${box.value.weightSum}</td>
							<td>${box.value.boxVolume}</td>
							<td>${box.value.volSum}</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="6">
								<table style="width: 100%;" class="hovertable">
									<tr>
										<th>序号</th>
										<th>原料编码</th>
										<th>原料名称</th>
										<th>箱子编号</th>
										<th>箱子类型</th>
										<th>原料单位</th>
										<th>数量</th>
										<th>单位重量</th>
										<th>重量</th>
										<th>单位体积</th>
										<th>体积</th>
										<th></th>
									</tr>
									<c:forEach var="item" items="${box.value.detailLst}" varStatus="istatus">
										<c:set var="rSpan" value="1" />
										<tr class="highlight" align="center">
											<td style="width: 80px;" rowspan="${rSpan }">${istatus.index + 1}</td>
											<td rowspan="${rSpan }">${item.itemId}</td>
											<td rowspan="${rSpan }">${item.itemName}</td>
											<td>${item.boxId}</td>
											<td rowspan="${rSpan }">${item.boxTypeName}</td>
											<td rowspan="${rSpan }">${item.itemDimension}</td>
											<td rowspan="${rSpan }">${item.itemCount}</td>
											<td rowspan="${rSpan }">${item.unitWeight}</td>
											<td rowspan="${rSpan }">${item.weight}</td>
											<td rowspan="${rSpan }">${item.unitVolume}</td>
											<td rowspan="${rSpan }">${item.volume}</td>
											<td rowspan="${rSpan }"></td>
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
</body>

</html>
