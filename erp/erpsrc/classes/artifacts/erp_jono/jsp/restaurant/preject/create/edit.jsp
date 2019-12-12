<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create purchase return bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/preject/create/edit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/preject/create/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var formId = '${formId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<input type="hidden" id="branchType" value='${branchType}'/>
	
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name="formId" value='${formId }' />
		<input type='hidden' name='returnHeader.antiauditorId' value='${returnHeader.antiauditorId }' />
		<input type='hidden' name='returnHeader.antiauditor' value='${returnHeader.antiauditor }' />
		<input type='hidden' name='returnHeader.antiauditBranchId' value='${returnHeader.antiauditBranchId }' />
		<input type='hidden' name='returnHeader.antiauditBranch' value='${returnHeader.antiauditBranch }' />
		<input type='hidden' name='returnHeader.antiauditTime' value='${antiauditTime }' />
		
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">入库单编号：</td>
				<td class="text_left" style="width: 180px;">
					<span>${inputHeader.formId }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">供应商：</td>
				<td class="text_left" colspan="5">
					<span>${inputHeader.provider }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">入库部门：</td>
				<td class="text_left">
					<span>${inputHeader.inputDepartment }</span>
				</td>
				
				<td class="label_right" >入库仓库：</td>
				<td>
				 	<span>${inputHeader.storage }</span> 
			 	</td>	
			 	
				<td class="label_right">入库人员：</td>
				<td class="text_left" style="width: 150px;">
					<span>${inputHeader.inputer }</span>
				</td>
				
				<td class="label_right" style="width: 80px;">入库日期：</td>
				<td class="text_left">
					<span>${inputTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">退货单备注：</td>
				<td class="text_left" colspan="7">
	  				<input type="text" name="returnHeader.formNote"	value="${returnHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
		</table>
		
		<div id="dataGrid" class="editGrid" ></div>
	</form>
		
	<table id="commandTable" class="hovertable">
		<tr>
			<td class="text_left" colspan="4" style="text-align: center;">
			    <input type="button" value="生成采购退货单" onclick="doSubmit();" />
			</td>
		</tr>
	</table>
</body>

</html>
