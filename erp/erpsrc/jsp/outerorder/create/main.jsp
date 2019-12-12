
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>outer request bill create</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/outerorder/create/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/outerorder/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var lcCode = '${lcCode }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<div id="customDialog" style="display: none;"	data-dojo-type="dijit/Dialog" data-dojo-props='title:"外部订货原料导入"'>
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
		
	<form id="billForm" method="post" >
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">外部订货方：</td>
				<td class="text_left" style="width: 160px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 onchange="refreshAddress();" style="width: 150px;" id="buyerId" name="requestHeader.buyerId"></s:select>
					<input type="hidden" id="buyerName" name="requestHeader.buyerName" value='' />
				</td>
				
				<td class="label_right" style="width: 60px;">订货地址：</td>
				<td class="text_left" colspan="3">
					<span id="buyerAddressText">${buyerAddress }</span>
					<input type="hidden" id="buyerAddress" name="requestHeader.buyerAddress" value='${buyerAddress }' />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
					<input type="hidden" id="formTime" value='${formTime }' />
				</td>
				
				<td class="label_right">到货周期：</td>
				<td class="text_left" style="width: 60px;">
					<span id="arrivePeriodText"></span>
					<input type="hidden" id="arrivePeriod" name="requestHeader.arrivePeriod" value='0' />
				</td>
				
				<td class="label_right" style="width: 60px;">到货日期：</td>
				<td class="text_left">
				<span id="receiveTimeText"></span>
				<input type="hidden" id="receiveTime" name="requestHeader.receiveTime" value='' />
<!-- 					<input type="text" class="Wdate" style="width: 100px;" id="receiveTime" name="requestHeader.receiveTime" value="" /> -->
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
<!-- 					<input type="button" onclick="show();" value="导入原料" style="margin-left: 58px;" /> -->
			   	 	<input type="button" id="useHistoryModel" onclick="pickModel();" value="使用模板" style="margin-left: 8px;" />
			    	
					<input type="button" onclick="delMaterial();" value="删除原料" style="margin-left: 10px;">
				    <input type="button" id="btn_submit" value="提交订货单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="createGrid"></div>
	</form>
</body>

</html>
