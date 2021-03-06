<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/iteminput/check.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/checkstorage/icreate/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' name='checkHeader.formId' value='${checkHeader.formId }' />
		<input type="hidden" name="checkHeader.formType" value='list' />
		<input type="hidden" name="checkHeader.checkBranchId" value='${checkHeader.checkBranchId }' />
		<input type="hidden" name="checkHeader.checkBranch" value='${checkHeader.checkBranch }' />
		<input type='hidden' name='checkHeader.formMakerId' value='${checkHeader.formMakerId }' />
		<input type='hidden' name='checkHeader.formMaker' value='${checkHeader.formMaker }' />
		<input type='hidden' name='checkHeader.formTime' value='${formTime }' />
		<input type='hidden' name='checkHeader.auditor' value='${checkHeader.auditor }' />
		<input type='hidden' name='checkHeader.auditTime' value='${auditTime }' />
		<input type='hidden' name='checkHeader.formNote' value='${checkHeader.formNote }' />
		
		<table class="hovertable" width="100%" border="1">
				<td class="label_right" style="width: 120px;">盘点清单编号：</td>
				<td class="text_left" colspan="3">
					<span>${checkHeader.formId }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">盘点部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${checkHeader.checkBranch }</span>
				</td>
				<td class="label_right" style="width: 120px;">对应盘点批次：</td>
				<td class="text_left">
					<span>${checkHeader.checkBatchId }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${loginUsername}</span>
				</td>
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
					<span style="display: inline-block;width: 300px;">${checkHeader.formNote }</span>
					
				    <input type="button" id="btn_submit" value="盘点清单输入确认" style="margin-left: 58px;" />
				    <input type="button" value="盘点清单打印" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<div id="dataGrid" class="checkGrid">
		</div>
	</form>	
</body>

</html>
