<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/lock/input.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/lock/antiaudit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/lock/loss.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/lock/dishloss.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/dailysettlement/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/dailysettlement/checkstorage.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/dailysettlement/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/putinstorage/outside/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/audit/main.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/confirmloss/main.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/restaurant/reportdamage/confirmdishloss/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/checkstorage/audit/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var branchType = '${branchType }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

<form id="billForm" method="post" >
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
<input type="hidden" id="startDate" name="startDate" value="${businessDate}" />
<input type="hidden" id="endDate" name="endDate" value="${businessDate}" />
		<table class="hovertable" width="100%" border="1">
			<tr>
			<td class="text_left" style="width: 220px;">
					<div style="padding-left: 8px;">
						<b id="businessDate">当前营业日期为${businessDate }</b>
					</div>
				</td>
				<td class="label_right" style="width: 80px;">门店：</td>
				<td class="text_left">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 style="width: 150px;" id="branchId"></s:select>
						 
					<input type="button" onclick="doQuery();" value="查    询" style="width: 60px;margin-left: 50px" >
					<input type="button" onclick="checkBill();" value="进 行 日 结" style="width: 80px;margin-left: 20px">
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>未审核入库单列表</strong></div>
				</td>
			</tr>
  		</table>
  		
		<div id="inputGrid" class="dataGrid"></div>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>未处理配送反审核单列表</strong></div>
				</td>
			</tr>
  		</table>
  		
		<div id="antiauditGrid" class="dataGrid"></div>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>未审核原料报损单列表</strong></div>
				</td>
			</tr>
  		</table>
  		
		<div id="lossGrid" class="dataGrid"></div>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>未审核出品报损单列表</strong></div>
				</td>
			</tr>
  		</table>
  		
		<div id="dishLossGrid" class="dataGrid"></div>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;"><strong>未审核盘点单列表</strong></div>
				</td>
			</tr>
  		</table>
  		
		<div id="checkstorageGrid" class="dataGrid"></div>
	</form>
</body>

</html>
