<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit item</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/meta/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var oldItemId = '${itemId }';
		var oldItemName = '${itemMeta.itemName }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;height: 328px;">
	<form id="dataForm" method="post">
		<input type='hidden' name='itemMeta.itemType' value='${itemMeta.itemType}' />
		<input type='hidden' name='itemMeta.categoryId' value='${itemMeta.categoryId}' />
		<input type='hidden' name='itemMeta.itemPic' value='${itemMeta.itemPic}' />
		<input type='hidden' name='itemMeta.itemBarCode' value='${itemMeta.itemBarCode}' />
		<input type='hidden' name='itemMeta.itemNameEng' value='${itemMeta.itemNameEng}' />
		<input type='hidden' name='itemMeta.queryCode' value='${itemMeta.queryCode}' />
		<input type='hidden' name='itemMeta.itemNote' value='${itemMeta.itemNote}' />
		<input type='hidden' name='itemMeta.effectDate' value='${itemMeta.effectDate}' />
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">编号：</td>
				<td class="text_left" style="width: 120px;">
					<span>${itemMeta.itemId}</span>
		 			<input type="hidden" id="itemId" name="itemMeta.itemId"
		 				 value="${itemMeta.itemId}" />
				</td>
				<td class="label_right" style="width: 80px;">名称：</td>
				<td class="text_left">
		 			<input type="text" id="itemName" name="itemMeta.itemName"
		 				 value="${itemMeta.itemName}" style="width: 180px;" />
				</td>
			</tr>
			<tr>
<!-- 				<td class="label_right">助记码：</td> -->
<!-- 				<td class="text_left"> -->
<!-- 		 			<input type="text" id="queryCode" name="itemMeta.queryCode" -->
<%-- 		 				 value="${itemMeta.queryCode}" style="width: 80px;" /> --%>
<!-- 				</td> -->
				<td class="label_right">规格：</td>
				<td class="text_left">
		 			<input type="text" id="itemSpecification" name="itemMeta.itemSpecification"
		 				 value="${itemMeta.itemSpecification}" style="width: 80px;" />
				</td>
				<td class="label_right">最小订货倍量：</td>
				<td class="text_left" >
	  				<input type="text" id="minOrderCount" name="itemParam.minOrderCount" 
	  					value="${itemParam.minOrderCount}" style="width: 80px;" />(库存单位)
					<input type='hidden' id='oldMinOrderCount' name='itemParam.oldMinOrderCount' value='${itemParam.minOrderCount}' />	
				</td>
			</tr>
			<tr>
				<td class="label_right">库存单位：</td>
				<td class="text_left">
	  				<input type="text" id="itemDimension" name="itemMeta.itemDimension" 
	  					value="${itemMeta.itemDimension}" style="width: 80px;" />
				</td>
				<td class="label_right">超收率：</td>
				<td class="text_left">
	  				<input type="text" id="outReceiveRate" name="itemParam.outReceiveRate" 
	  					value="${itemParam.outReceiveRate}" style="width: 80px;" />
				</td>
			</tr>
			
			 <c:if test="${itemMeta.itemType=='SEMIS'}">
				<tr>
					<td class="label_right">所属库位：</td>
					<td class="text_left" >
						<s:select list="shelfLst" listKey="shelfId" listValue="shelfName" theme="simple" style="width: 80px;" id="boxType" name="shelfItem.shelfId" value="%{shelfItem.shelfId}"></s:select>
						<input type="hidden" id="itemId" name="shelfItem.itemId" value="${shelfItem.itemId }"/>
					</td>
					
	             	<td class="label_right">车间：</td>
					<td class="text_left" colspan="3">
						<s:select list="factoryWorkShopLst" listKey="workOrderId" listValue="workshop" theme="simple" style="width: 80px;" id="boxType" name="itemWorkShop.workOrderId" value="%{itemWorkShop.workOrderId}"></s:select>
						<input type="hidden" id="factoryId" name="itemWorkShop.factoryId" value="F00"/>
						<input type="hidden" id="itemId" name="itemWorkShop.itemId" value="${itemMeta.itemId }"/>
					</td>
				</tr>
			</c:if>
			 <c:if test="${itemMeta.itemType!='SEMIS'}">
				<tr>
				<td class="label_right">所属库位：</td>
					<td class="text_left" colspan="3">
						<s:select list="shelfLst" listKey="shelfId" listValue="shelfName" theme="simple" style="width: 80px;" id="boxType" name="shelfItem.shelfId" value="%{shelfItem.shelfId}"></s:select>
						<input type="hidden" id="itemId" name="shelfItem.itemId" value="${shelfItem.itemId }"/>
					</td>
				</tr>
			</c:if>	
				
			<tr>
				<td class="label_right">配方单位：</td>
				<td class="text_left">
	  				<input type="text" id="recipeUnit" name="itemParam.recipeUnit" 
	  					value="${itemParam.recipeUnit}" style="width: 80px;" />
				</td>
				<td class="label_right">转换因子：</td>
				<td class="text_left">
	  				<input type="text" id="recipeFactor" name="itemParam.recipeFactor" 
	  					value="${itemParam.recipeFactor}" style="width: 80px;" />(库存单位=配方单位*转换因子)
				</td>
					<input type="hidden" id="oldRecipeFactor" name="itemParam.oldRecipeFactor" 
	  					value="${itemParam.recipeFactor}" />
			</tr>
			<tbody style="border: 2px solid #FEF0C9;">
				<tr>
					<td class="label_right">包装单位：</td>
					<td class="text_left">
		  				<input type="text" id="deliveryUnit" name="itemParam.deliveryUnit" 
		  					value="${itemParam.deliveryUnit}" style="width: 80px;" />
					</td>
					<td class="label_right">转换因子：</td>
					<td class="text_left">
		  				<input type="text" id="deliveryFactor" name="itemParam.deliveryFactor" 
		  					value="${itemParam.deliveryFactor}" style="width: 80px;" />(包装单位=库存单位*转换因子)
					</td>
	  				<input type='hidden' id='oldDeliveryFactor' name='itemParam.oldDeliveryFactor' value='${itemParam.deliveryFactor}' />
				</tr>
				<tr>
					<td class="label_right">单位体积(升)：</td>
					<td class="text_left">
		  				<input type="text" id="unitVolume" name="itemParam.unitVolume" 
		  					value="${itemParam.unitVolume}" style="width: 80px;" />
					</td>
					<td class="label_right">单位重量(克)：</td>
					<td class="text_left">
		  				<input type="text" id="unitWeight" name="itemParam.unitWeight" 
		  					value="${itemParam.unitWeight}" style="width: 80px;" />
					</td>
				</tr>
			</tbody>
			<tr>
				<td class="label_right">箱子类型：</td>
				<td class="text_left"  colspan="3">
					<s:select list="boxTypeLst" listKey="typeId" listValue="typeName" theme="simple"
						 style="width: 80px;" id="boxType" name="itemMeta.boxType" value="%{itemMeta.boxType}"></s:select>
<!-- 					<select id="boxType" name="itemMeta.boxType"  -->
<%-- 	  					value="${itemMeta.boxType}" style="width: 80px;"> --%>
<%-- 	  					<c:choose> --%>
<%-- 						   <c:when test="${itemMeta.boxType=='10'}"> --%>
<!-- 							  	<option value="10" selected="selected">非冷藏</option> -->
<!-- 							  	<option value="20">冷藏</option> -->
<%-- 						   </c:when> --%>
<%-- 						   <c:otherwise> --%>
<!-- 							  	<option value="10">非冷藏</option> -->
<!-- 							  	<option value="20" selected="selected">冷藏</option> -->
<%-- 						   </c:otherwise> --%>
<%-- 						</c:choose> --%>
<!-- 					</select> -->
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
		<tr>
			<td >
			</td>
		</tr>
			<tr>
			<td >
			</td>
		</tr>
	</table>
</body>

</html>
