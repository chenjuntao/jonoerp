<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create request bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.js?Version=<%=currenttime%>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var jsonData = '${jsonData }';
		var gridData = [];
		if (jsonData != '') {
			gridData = JSON.parse('${jsonData }');
		}
		
		var branchType = '${branchType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
		<div id="customDialog" style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"餐厅要货原料导入"'>
			<form id="uploadForm" method="post" enctype="multipart/form-data">
				<table class="hovertable" width="100%" border="1">
					<tr>
						<td class="label_right" style="width: 60px;">文件：</td>
						
						<td class="text_left">
							<input type="file" id="fileurl"	name="attachment" />
							<input type="button" onclick="importMaterial();" value="导入确认" style="margin-left: 48px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>

	<form id="billForm" method="post">
		<input type="hidden" id="deliveryType" name="requestHeader.deliveryType" value='' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 140px;">门店：</td>
				<td class="text_left" style="width: 180px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"  style="width: 150px;" id="shopId" name="requestHeader.buyerId"></s:select>
						 
					<input type="hidden" id="shopName" name="requestHeader.buyerName" value='' />
					<input type="hidden" id="regionId" value='' />
				</td>
				
				<td class="label_right" style="width: 120px;">制单日期：</td>
				<td class="text_left">
					<span id="formTimeText"></span>
					<input type="hidden" id="formTime" value='${formTime }' />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">到货周期：</td>
				<td class="text_left">
					<span id="arrivePeriodText">0</span>
					<input type="hidden" id="arrivePeriod" name="requestHeader.arrivePeriod" value='0' />
				</td>
				
				<td class="label_right">到货日期：</td>
				<td class="text_left">
					<span id="receiveTimeText"></span>
					<input type="hidden" id="receiveTime" name="requestHeader.receiveTime" value='${formTime }' />
				</td>
			</tr>
		</table>
		
		<table class="hovertable" width="100%">
			<tr>
				<td class="label_right" style="width: 140px; border-top: 0px;">参考日期区间1：</td>
				
				<td class="text_left" style="width: 240px; border-top: 0px;">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart1" name="requestHeader.refDateStart1" value="${refDateStart1}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd1" name="requestHeader.refDateEnd1" value="${refDateEnd1}" />
				</td>
				
				<td class="label_right" style="width: 140px; border-top: 0px;">参考日期区间2：</td>
				
				<td class="text_left" style="border-top: 0px;">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart2" name="requestHeader.refDateStart2" value="${refDateStart2}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd2" name="requestHeader.refDateEnd2" value="${refDateEnd2}" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">参考日期区间3：</td>
				
				<td class="text_left">
					<input type="text" class="Wdate" style="width: 100px;" id="refDateStart3" name="requestHeader.refDateStart3" value="${refDateStart3}" />
					<span style="padding: 0px 5px;">至</span>		 
					<input type="text" class="Wdate" style="width: 100px;" id="refDateEnd3" name="requestHeader.refDateEnd3" value="${refDateEnd3}" />
				</td>
				
				<td class="label_right">延滞期金额：</td>
				
				<td class="text_left">
		 			<input type="text" id="delayPredict" name="requestHeader.delayPredict" value="${requestHeader.delayPredict}" style="width: 100px;" />(万元)
				</td>
			</tr>
		</table>
		
		<table class="hovertable" width="100%">
			<tr>
				<td class="label_right" style="width: 140px; border-top: 0px;">进货周期金额：</td>
				
				<td class="text_left" style="width: 110px; border-top: 0px;">
		 			<input type="text" id="purchasePredict" name="requestHeader.purchasePredict" value="${requestHeader.purchasePredict}" style="width: 60px;" />(万元)
				</td>
				
				<td class="label_right" style="width: 120px; border-top: 0px;">安全存量：</td>
				
				<td class="text_left" style="width: 120px; border-top: 0px;">
		 			<input type="text" id="safetyStock" name="requestHeader.safetyStock" value="${requestHeader.safetyStock}" style="width: 60px;" />(万元)
				</td>
				
				<td class="label_right" style="width: 120px; border-top: 0px;">预估销售额：</td>
				
				<td class="text_left" style="border-top: 0px;" style="width: 120px;">
					<span id="sellPredictText">${requestHeader.sellPredict}</span>(万元)
					<input type='hidden' id="sellPredict" name='requestHeader.sellPredict' 	value='${requestHeader.sellPredict }' />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				
				<td class="text_left" colspan="5">
	  				<input type="text" name="requestHeader.formNote" value="${requestHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
			
			<tr>
				<td colspan="6">
<!-- 			   	 	<input type="button" onclick="show();" value="导入原料" style="margin-left: 58px;" /> -->
			   	 	<input type="button" onclick="pickModel();" value="使用模板" style="margin-left: 8px;" />
					<c:if test="${autonomyFlag}">
						<input type="button" onclick="addMaterial();" value="增加原料"
							style="margin-left: 8px;" />
					</c:if> 
					<input type="button" onclick="delMaterial();" value="删除原料" style="margin-left: 8px;">
					<input type="button" onclick="calcSuggest();" value="计算建议值" style="margin-left: 8px;">
				    <input type="button" id="btn_submit" value="提交要货单" style="margin-left: 10px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top:212px"></div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
  		<input type="hidden" name="branchType" value='${branchType }' />
  		<input type="hidden" id="addForm" name="requestHeader.isAddForm" value='Y' />
	</form>
</body>

</html>
