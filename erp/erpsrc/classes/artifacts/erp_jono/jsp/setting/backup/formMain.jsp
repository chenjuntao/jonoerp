<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<%@ include file="/jsp/common/jsp/path.jsp"%>

	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css"/>

    <link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">

    <link rel="stylesheet" href="<%=dojoBase %>/dojox/grid/enhanced/resources/claro/EnhancedGrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
    <script src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:true"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/backup/formExport.js?Version=<%=currenttime %>"></script>
	
	<script type="text/javascript">
		var bnDate = '${bnDate}';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<form id="queryForm" method="post">
		<input id="branchType"   name="branchType" type="hidden" value="RESTAURANT"/>
		<input id="lastMonth"  type="hidden" value=""/>
		<input type="hidden" id="downloadTokenValue" name="downloadTokenValue"/>
		
		<table class="hovertable" width="100%" border="2">
			<tr>
				<td class="label_right" style="width: 220px; font-size: 16px;">
					表单备份内容：
				</td>
				
				<td class="text_left" colspan="4">
					<div style="padding-left: 8px; font-weight: 900; font-size: 15px; color: blue;">
							<li>1.出入库表 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.捡货单 </li>
							<li>3.装箱单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.餐厅退货单 </li>
							<li>5.餐厅调拨单 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6.盘点锁库记录 </li>
							<li>7.盘点单/清单 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;8.餐厅配送反审核单</li>
							<li>9.中央工厂生产工单 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;10.中央工厂生产计划/排程单 </li>
							<li>11.餐厅要货、外部订货商订货需求 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;12.物流中心、中央工厂要货汇总单 </li>
							<li>13.物流中心采购单、中央工厂采购单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;14.餐厅、物流中心、中央工厂入库单</li>
							<li>15.餐厅、中央工厂、物流中心报损单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;16.餐厅配送单、物流中心出货单、中央工厂出货单 </li>
							<li>17.供应商总部财务 外部订货方总部财务 对账单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;18.中央工厂生产领料单、中央工厂超领单、中央工厂非工单领料单、中央工厂生产退料单 </li>
							<li>19.中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单 </li>
					</div>
					</td>
			</tr>
			
			<tr>
				<td class="label_right">备份月份：</td>
				<td class="text_left" style="width:35%"  >
					<input type="text" class="Wdate" id="month" name="month" value="${bnDate}"  onFocus="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,readOnly:true})"  />
				</td>
				
				<td>
					<span style="width:30%">
					<input type="button" onclick="exportXls();" value="备份">
				</td>
			</tr>
			
		</table>
	</form>
	
    <div id="gridDiv"></div>
</body>

</html>