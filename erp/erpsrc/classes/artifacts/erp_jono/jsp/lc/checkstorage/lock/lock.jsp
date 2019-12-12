<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/checkstorage/lock/lock.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="lockForm" method="post" >
		<input type='hidden' name="lock.checkBatchId" value='${checkBatchId }' />
		<input type='hidden' name='lock.lockBranchId' value='${lock.lockBranchId }' />
		<input type='hidden' name='lock.lockBranch' value='${lock.lockBranch }' />
		<input type='hidden' name='lock.lockManId' value='${lock.lockManId }' />
		<input type='hidden' name='lock.lockMan' value='${lock.lockMan }' />
		<input type='hidden' name='lock.lockDate' value='${lockDate }' />
		<input type='hidden' name='lock.lockTime' value='${lockTime }' />
		<input type='hidden' id="itemCategory" name='lock.itemCategory' value='' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right">锁库仓库：</td>
				<td class="text_left" colspan="3">
					<s:select list="storeLst" listKey="storageId" listValue="storageName" theme="simple" style="width: 180px;" id="storageId" name="lock.lockStorageId"></s:select>
					<input type="hidden" id="storage" name="lock.lockStorage" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 90px;">锁库部门：</td>
				<td class="text_left" style="width: 180px;">
					<span>${lock.lockBranch }</span>
				</td>
				
				<td class="label_right" style="width: 90px;">锁库人员：</td>
				<td class="text_left">
					<span>${lock.lockMan }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">营业日期：</td>
				<td class="text_left">
					<span>${lockDate }</span>
				</td>
				
				<td class="label_right">锁库时间：</td>
				<td class="text_left">
					<span>${lockTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="lock.lockNote" value="${lock.lockNote}" style="width: 380px;" />
	  					
					<span style="padding-left: 30px;">
						<input name="lock.itemRepeatable" type="checkbox"  checked="checked"	style="vertical-align: middle; margin: 0px;padding:0px;" />
						允许多个清单中出现相同原料
					</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">盘点原料类别：</td>
				<td class="text_left" colspan="3">
					<span id="cateSpan" style="width: 300px; display: inline-block;"></span>
	  					
			    	<input type="button" value="选择原料类别" onclick="selCategory();" />
				</td>
			</tr>
		</table>
	<table style="width: 100%;">
		<tr>
			<td class="text_left" colspan="4" style="text-align: center;">
			    <input type="button" value="确认进行盘点锁库" onclick="doSubmit();" />
			</td>
		</tr>
	</table>
	</form>
		
</body>

</html>
