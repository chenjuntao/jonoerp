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
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/backup/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/backup/export.js?Version=<%=currenttime %>"></script>
	       <script type="text/javascript" src="<%=appRoot%>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
<script type="text/javascript">
		var bnDate = '${bnDate}';
		function freshStore(){
			dojo.byId('branchId').value ='';
			dojo.byId('branchText').innerHTML ='';
		}
	</script>
</head>
<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<form id="queryForm" method="post">
	 <input type="hidden" id="downloadTokenValue" name="downloadTokenValue"/>
		<input id="branchType"   name="branchType" type="hidden" value="RESTAURANT"/>
		<input id="lastMonth"  type="hidden" value=""/>
		
		<table class="hovertable" width="100%" border="2">
		<tr>
		<td class="label_right" style="width: 220px;">
		业务信息备份内容：
		</td>
			<td class="text_left" colspan="3">
			<div style="padding-left: 8px; font-weight: 900; font-size: 15px; color: blue;">
						<b>1. 单据明细表  2. 出品明细表   3. 付款明细表  </b>
					</div>
				</td>
			</tr>
			<tr>
			<td class="label_right">备份月份：</td>
			<td class="text_left" style="width:15%"  >
					<input type="text" class="Wdate" id="month" name="month" value="${bnDate}"
							 onFocus="WdatePicker({Mchanged:freshStore,dateFmt:'yyyy-MM',isShowClear:false,readOnly:true})"  />
				</td>
				
			<!-- 	<td colspan="3">删除数据：
				<input  type="checkbox" id="stuta" name="stuta" ></td> -->
					<td class="label_left" style="width:10%" >
			<input type="button" onclick="selBranch()" value="选择门店"/></td>
	 	</tr>
			<tr valign="middle">
				<td class="label_right" style="width:15%" >门店：</td>
				<td class="text_left">
				<input type='hidden' id="branchId"  name="branchIds" value='' />
				<span id="branchText" style="display: inline-block; height: 58px;">&nbsp;</span>
			</td>
			
				
				
				<td><span style="width:30%">
						<input type="button" onclick="exportXls();" value="备份">
						</td>
			</tr>
			
		</table>
	</form>
	
	
    <div id="gridDiv"></div>
</body>

</html>