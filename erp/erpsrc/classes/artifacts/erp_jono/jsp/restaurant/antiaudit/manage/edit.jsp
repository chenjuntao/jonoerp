<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>shipping anti audit edit</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/antiaudit/manage/edit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/antiaudit/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formRefId = '${formRefId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		
		<input type='hidden' id="formRefId" name='antiauditHeader.formRefId' value='${antiauditHeader.formRefId }' />
			<table class="hovertable" width="100%" border="1">
				<tr>
					<td class="label_right" style="width: 120px;">配送单编号：</td>
					<td class="text_left" style="width: 180px;">
						<span>${shippingHeader.formId }</span>
					</td>
					
					<td class="label_right" style="width: 90px;">配送部门：</td>
					<td class="text_left" style="width: 180px;">
						<span>${shippingHeader.provider }</span>
					</td>
					
					<td class="label_right" style="width: 120px;">出库仓库：</td>
					<td class="text_left">
						<span>${shippingHeader.outStorage }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right" style="width: 120px;">订货部门：</td>
					<td class="text_left">
						<span>${shippingHeader.requester }</span>
					</td>
					
					<td class="label_right" style="width: 120px;">入库仓库：</td>
					<td class="text_left">
						<span>${shippingHeader.inStorage }</span>
					</td>
					
					<td class="label_right">订货地址：</td>
					<td class="text_left" >
						<span>${shippingHeader.requestAddress }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">制单人员：</td>
					<td class="text_left">
						<span>${shippingHeader.formMaker }</span>
					</td>
					
					<td class="label_right">制单日期：</td>
					<td class="text_left" >
						<span>${formTime }</span>
					</td>
					
					<td class="label_right" style="width: 90px;">配送日期：</td>
					<td class="text_left">
						<span>${receiveTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">审核人员：</td>
					<td class="text_left">
						<span>${shippingHeader.auditor }</span>
					</td>
					
					<td class="label_right">审核日期：</td>
					<td class="text_left" colspan="3">
						<span>${auditTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">入库人员：</td>
					<td class="text_left">
						<span>${shippingHeader.inputer }</span>
					</td>
					
					<td class="label_right">入库日期：</td>
					<td class="text_left" colspan="3">
						<span>${inputTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">配送单备注：</td>
					<td class="text_left" colspan="5">
						<span id="l_formNote">${shippingHeader.formNote }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">反审核人员：</td>
					<td class="text_left">					
						<span>${antiauditHeader.antiauditor }</span>
					</td>
					
					<td class="label_right">反审核部门：</td>
					<td class="text_left">
						<span>${antiauditHeader.antiauditBranch }</span>
					</td>
					
					<td class="label_right">反审核日期：</td>
					<td class="text_left">
						<span>${antiauditTime }</span>
					</td>
				</tr>
				
				<tr>
					<td class="label_right">反审核备注：</td>
					<td class="text_left" colspan="5">
		  				<input type="text" name="antiauditHeader.formNote" 	value="${antiauditHeader.formNote}" style="width: 380px;" />
					</td>
				</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
	  	
		<div id="dataGrid" class="auditGrid" style="top:243px">
		</div>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doRevert();" value="撤消反审核" style="margin-left: 8px;" />
			    <input type="button" onclick="doSave();" value="确认修改" style="margin-left: 8px;" />
			    <input type="button" onclick="doClose();" value="取消修改" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
