<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create picking bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/picking/summary.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/stock/picking/summary.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro">
	<form id="billForm" method="post" >
		<s:token/>
		
	  	<input type="hidden" id="ids" name="ids" value='${ids }' />
	  	<input type="hidden" id="branchIds" name="branchIds" value='' />
		<input type='hidden' name='pickingHeader.formMakerId' value='${pickingHeader.formMakerId }' />
		<input type='hidden' name='pickingHeader.formMaker' value='${pickingHeader.formMaker }' />
		<input type='hidden' name='pickingHeader.formTime' value='${formTime }' />
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">汇总部门：</td>
				<td class="text_left">
					<span>物流中心</span>
				</td>
				
				<td class="label_right">捡货仓库：</td>
				<td class="text_left">
					<s:select list="storeLst" listKey="storageId" listValue="storageName" theme="simple" style="width: 180px;" id="storageId" name="pickingHeader.storageId"></s:select>
					<input type="hidden" id="storage" name="pickingHeader.storage" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left" style="width: 120px;">
					<span>${loginUsername}</span>
				</td>
				
				<td class="label_right" style="width: 60px;">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="pickingHeader.formNote" value="${pickingHeader.formNote}" style="width: 380px;" />
			    	<input type="button" onclick="doSubmit();" value="生成捡货单" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
	</form>
	
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid" style="top: 92px;"></div>
</body>

</html>
