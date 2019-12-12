<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>order summary</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/summary/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/summary/request.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/summary/outer.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript">
		var g_busFlag = ${busFlag };
	</script>
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/query/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/summary/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="text_left" style="width: 220px;height: 40px">
					<div style="padding-left: 8px;">
						<b>营业日期：${businessDate }</b>
						<input type="button" onclick="doInvalid();" value="作    废" style="margin-left: 8px;width: 60px" />
					</div>
				</td>
				
				<td class="label_right" style="width: 60px;">审核日期：</td>
				<td class="text_left" style="width: 230px;">
					<input type="text" class="Wdate" id="startDate" name="startDate" value="${businessDate}"	style="width: 90px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate" name="endDate" value="${businessDate}" style="width: 90px;" onFocus="WdatePicker()" />
				</td>
				
				<td class="label_right" style="width: 60px;">配送方式：</td>
					<td class="text_left" style="width: 170px;"  >
					<input type="radio" name="deliveryType" value="DIRECT">直配</input>
					<input type="radio" name="deliveryType"  value="UNIFIED">统配</input>
					<input type="radio" name="deliveryType" value="CROSS">越库</input>
					<input type="radio" name="deliveryType" checked="checked" value="">全部</input>
				</td>
				
				<td>
				 	<input type="button" onclick="doQuery();" value="查   询" style="margin-left: 8px;width: 60px" />
			   	 	<input type="button" onclick="doAggregate();" value="汇总预估" style="margin-left: 18px;" />
				</td>
			</tr>
		</table>
	
		<p class="area_blank">&nbsp;</p>
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>已审核餐厅要货单列表</strong></div>
				</td>
			</tr>
	 	</table>
 	
		<div id="requestGrid" class="dataGrid"></div>
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>已审核外部订货单列表</strong></div>
				</td>
			</tr>
	 	</table>
	 	
		<div id="outerGrid" class="dataGrid"></div>
	
	  	<input type="hidden" id="ids" name="ids" value='' />
	  	<input type="hidden" id="businessDate" name="businessDate" value='${businessDate }' />
	</form>
</body>

</html>
