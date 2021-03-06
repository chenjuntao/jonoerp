<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/arrangement.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="text_left" style="width: 220px;">
				<div style="padding-left: 8px;">
					<b>当前营业日期为${businessDate }</b>
				</div>
			</td>
			
			<td class="label_right" style="width: 60px;">制单日期：</td>
			<td class="text_left">
				<input type="text" class="Wdate" id="startDate" value="${businessDate}"	style="width: 110px;" onFocus="WdatePicker()" />
				<span style="padding: 0 5px;">至</span>
				<input type="text" class="Wdate" id="endDate" value="${businessDate}"	style="width: 110px;" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" />
				
		   	 	<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 58px;width: 60px" />
		   	 	<input type="button" onclick="doAggregate();" value="汇总订货数据" style="margin-left: 28px;" />
			</td>
		</tr>
	</table>
	
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="4">
				<div style="padding-left: 8px;"><strong>已审核中央工厂生产计划单列表</strong></div>
			</td>
		</tr>
	</table>
 		
	<div id="arrangeGrid"></div>
	
	<form id="dataForm" method="post" >
	  	<input type="hidden" id="ids" name="ids" value='' />
	  	<input type="hidden" id="businessDate" name="businessDate" value='${businessDate }' />
	</form>
</body>

</html>
