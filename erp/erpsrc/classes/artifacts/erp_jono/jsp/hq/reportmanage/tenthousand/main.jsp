<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>bill manage</title>
<%@ include file="/jsp/common/jsp/path.jsp"%>

<script src='<%=dojoBase%>/dojo/dojo.js'></script>
<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/gridx-util.js"></script>
<script type="text/javascript" src="<%=dojoBase%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=appRoot %>/jsp/hq/reportmanage/tenthousand/main.js?Version=<%=currenttime %>"></script>
<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>


<style type="text/css">
      @import "<%=dojoBase%>/dojo/resources/dojo.css";
      @import "<%=dojoBase%>/dgrid/css/skins/claro.css";
       @import "<%=dojoBase%>/dijit/themes/claro/claro.css";
  	  @import "<%=dojoBase%>/dijit/themes/claro/document.css";
       @import "<%=dojoBase%>/gridx/resources/claro/Gridx.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot%>/jsp/hq/pricechange/main.css">
	
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/reportmanage/tenthousand/main.css">
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">营业日期：</td>
				<td class="text_left" style="width: 260px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${startDate}"
						style="width: 110px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${endDate}"
						style="width: 110px;" onFocus="WdatePicker()" />
			   	 
				</td>
				
				<td>
					 门店：
					<s:select list="shopList" listKey="code" listValue="name" theme="simple"
						style="width: 180px;" id="branchId" name="branchId" ></s:select>
				</td>
				
				<td style="width: 250px;">
					<input type="button" id="DIYwithnoModel" onclick="selMaterial();" value="选择出品" style="margin-left:10px;" />
					<input type="hidden" id=itemId  name="itemId" value=""/>
					<input type="hidden" id=itemName  name="itemName" value=""/>
					<span id="displayItemName"></span>
				</td>
				
				<td style="width: 200px;">	<input type="button" onclick="doQuery();" value="查询" /></td>
			</tr>
		</table>
		
		 <div id="chartNode" style="width:100%;height:450px;"></div>
        <div id="legend"></div>
	</form>

</body>

</html>
