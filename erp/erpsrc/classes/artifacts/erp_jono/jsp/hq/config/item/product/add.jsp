<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>add item</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/product/add.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var oldItemId = '${itemId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post">
		<input type='hidden' name='itemMeta.itemType' value='PRODUCT' />
		<input type='hidden' name='itemMeta.enabled' value='0' />
		<input type='hidden' name='itemMeta.cBranchC' value='00' />
		<input type='hidden' name='itemMeta.categoryName' value='${itemMeta.categoryName}' />
		<input type='hidden' name='itemMeta.categoryId' value='${categoryId}' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">编号：</td>
				<td class="text_left" style="width: 180px;">
		 			<input type="text" id="itemId" name="itemMeta.itemId" value="${itemMeta.itemId}" style="width: 80px;" />
				</td>
				
				<td class="label_right" style="width: 80px;">名称：</td>
				<td class="text_left">
		 			<input type="text" id="itemName" name="itemMeta.itemName" value="${itemMeta.itemName}" style="width: 180px;" />
				</td>
			</tr>

			<tr>
				<td class="label_right">例牌：</td>
				<td class="text_left">
	  				<input type="text" id="itemDimension" name="itemMeta.itemDimension" value="${itemMeta.itemDimension}" style="width: 80px;" />
				</td>
				
				<td class="label_right">售卖价：</td>
				<td class="text_left">
	  				<input type="text" id="salePrice" name="salePrice"	value="${salePrice}" style="width: 80px;" />
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
					<input name="binhandpro" type="checkbox" style="margin-left: 150px; " />
					<span style="color: blue; font-size: 12px;">手持下载</span>
					<input name="bdisfood" type="checkbox" style="margin-left: 50px; " />
					<span style="color: blue; font-size: 12px;">出品折扣</span>
					<input name="bdiscount" type="checkbox" style="margin-left: 50px; " />
					<span style="color: blue; font-size: 12px;">照单折扣</span>
					<input name="bwaimai" type="checkbox" style="margin-left: 50px; " />
					<span style="color: blue; font-size: 12px;">外卖出品</span>
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
