<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit sample info</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/sample/manage/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var oldId = '${id }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<input type='hidden' id="id" name="sample.id" value='${sample.id}' />
			<tr>
				<td class="label_right" style="width: 100px;">产品名称：</td>
				<td class="text_left" style="width: 180px;">
		 			<input type="text" id="productName" name="sample.productName"
		 				 value="${sample.productName}" style="width: 180px;" />
				</td>
				<td class="label_right" style="width: 100px;">起订量：</td>
				<td class="text_left">
		 			<input type="text" id="minimumOrderQuantity" name="sample.minimumOrderQuantity"
		 				 value="${sample.minimumOrderQuantity}" style="width: 80px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">产地：</td>
				<td class="text_left">
		 			<input type="text" id="producePlace" name="sample.producePlace"
		 				 value="${sample.producePlace}" style="width: 180px;" />
				</td>
				<td class="label_right">订货周期(天)：</td>
				<td class="text_left">
		 			<input type="text" id="orderCycle" name="sample.orderCycle"
		 				 value="${sample.orderCycle}" style="width: 80px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">使用周期(天)：</td>
				<td class="text_left">
		 			<input type="text" id="useCycle" name="sample.useCycle"
		 				 value="${sample.useCycle}" style="width: 80px;" />
				</td>
				<td class="label_right">价格(元)：</td>
				<td class="text_left">
		 			<input type="text" id="price" name="sample.price"
		 				 value="${sample.price}" style="width: 80px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">毛价(元)：</td>
				<td class="text_left">
		 			<input type="text" id="grossPrice" name="sample.grossPrice"
		 				 value="${sample.grossPrice}" style="width: 80px;" />
				</td>
				<td class="label_right">毛重(克)：</td>
				<td class="text_left">
		 			<input type="text" id="grossWeight" name="sample.grossWeight"
		 				 value="${sample.grossWeight}" style="width: 80px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">净重(克)：</td>
				<td class="text_left">
		 			<input type="text" id="netWeight" name="sample.netWeight"
		 				 value="${sample.netWeight}" style="width: 80px;" />
				</td>
				<td class="label_right">出净率：</td>
				<td class="text_left">
		 			<input type="text" id="netRatio" name="sample.netRatio"
		 				 value="${sample.netRatio}" style="width: 80px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">保质期(月)：</td>
				<td class="text_left">
		 			<input type="text" id="shelfLife" name="sample.shelfLife"
		 				 value="${sample.shelfLife}" style="width: 80px;" />
				</td>
				<td class="label_right">送样时间(天)：</td>
				<td class="text_left">
		 			<input type="text" id="deliveryTime" name="sample.deliveryTime"
		 				 value="${sample.deliveryTime}" style="width: 80px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">验收标准：</td>
				<td class="text_left" colspan="3">
		 			<input type="text" id="accptanceCriteria" name="sample.accptanceCriteria"
		 				 value="${sample.accptanceCriteria}" style="width: 380px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">供应商：</td>
				<td class="text_left" colspan="3">
		 			<input type="text" id="supplier" name="sample.supplier"
		 				 value="${sample.supplier}" style="width: 380px;" />
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doSave();" value="保存" />
			</td>
		</tr>
	</table>
</body>

</html>
