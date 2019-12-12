<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/allocateitem/confirm/confirm.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    <!-- 引用新增要货单页面create的样式 -->
<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/allocateitem/confirm/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
		var preVersion = '${preVersion }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input id="currentVersion" name="currentVersion" type="hidden" / >
		<input id = "preVersion"type = "hidden" / >
		<input type='hidden' id="formId" name='transferHeader.formId'		  value='${transferHeader.formId }' />
		<input type='hidden'  name='transferHeader.inBranchId'   value='${transferHeader.inBranchId }' />
		<input type='hidden'  name='transferHeader.inStorageId' value='${transferHeader.inStorageId }' />
		<input type='hidden'  name='transferHeader.outBranchId'   value='${transferHeader.outBranchId }' />
		<input type='hidden'  name='transferHeader.outStorageId' value='${transferHeader.outStorageId }' />
		 <s:token/>
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="8">
					<div style="padding-left: 8px;">
						<b>调拨单审核</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 180px;" colspan="7">
					<span>${transferHeader.formId }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">调入部门：</td>
				<td class="text_left">
					<span>${transferHeader.inBranch }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">调入仓库：</td>
				<td class="text_left">
					<span>${transferHeader.inStorage }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">调出部门：</td>
				<td class="text_left">
					<span>${transferHeader.outBranch }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">调出仓库：</td>
				<td class="text_left">
					<span>${transferHeader.outStorage }</span>
				</td>
			</tr>
				
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${transferHeader.fromMaker}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
				
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<span>${transferHeader.formNote }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">确认人员：</td>
				<td class="text_left" style="width: 180px;">
					<span>${transferHeader.confirmer }</span>
				</td>
				
				<td class="label_right" style="width: 120px;">确认日期：</td>
				<td class="text_left" colspan="5">
				
				<input type="text" class="Wdate" id="confirmTime" name="confirmTime" value="${confirmTime}"	 onFocus="WdatePicker({minDate:'${confirmTimeOffMonth}',maxDate:'${confirmTime}'})" />
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  	
		<div id="confirmGrid"></div>
		
		<input type='hidden' id="allPayAmt" name='transferHeader.allPayAmt' value='${transferHeader.allPayAmt }' />
		<input type='hidden' id="maxPayItem" name='transferHeader.maxPayItem' value='${transferHeader.maxPayItem }' />
	</form>
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doConfirm();" id="btn_submit" value="调拨单确认通过" style="margin-left: 8px;" />
			    <input type="button" onclick="doClose();" value="取消" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
