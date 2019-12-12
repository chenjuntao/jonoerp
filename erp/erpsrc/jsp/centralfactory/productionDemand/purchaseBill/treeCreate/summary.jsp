<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>request bill summary</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/summary.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/treeSummaryGrid.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
     @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/purchaseBill/treeCreate/summary.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" >
	
	<form id="billForm" method="post" >
		<input type='hidden' id="formId" name='commonHeader.formId' value='${commonHeader.formId }' />
	  	<input type="hidden" id="ids" name="ids" value='${ids }' />
		<input type="hidden" name="commonHeader.requesterId" value='${commonHeader.requesterId }' />
		<input type='hidden' name='commonHeader.formMakerId' value='${commonHeader.formMakerId }' />
		<input type='hidden' name='commonHeader.formMaker' value='${commonHeader.formMaker }' />
		<input type='hidden' name='commonHeader.formTime' value='${formTime }' />
		<input type='hidden' name='commonHeader.receiveTime' value='${receiveTime }' />
		
		<table class="hovertable" width="100%" border="1" >
			<tr>
				<td class="label_right" style="width: 80px;">汇总部门：</td>
				<td class="text_left" colspan="3">
					<span>中央工厂</span>
				</td>
				<td class="label_right">收货仓库：</td>
				<td class="text_left" colspan="2">
					<s:select list="storeLst" listKey="storageId" listValue="storageName" theme="simple"
						style="width: 180px;" id="storageId" name="commonHeader.storageId"></s:select> 
					<input type="hidden" id="storage" name="commonHeader.storage" />
				</td>
			</tr>
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left" style="width: 120px;" colspan="3">
					<span>${loginUsername}</span>
				</td>
				<td class="label_right" style="width: 60px;">制单日期：</td>
				<td class="text_left" style="width: 120px;" colspan="2">
					<span>${formTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
	  				<input type="text" name="commonHeader.formNote" value="${commonHeader.formNote}" style="width: 380px;" />
	  				
			    	<input type="button" onclick="doSubmit();" value="确认采购单" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>
	
	<div id="treeSummaryGrid" class="dataGrid">
	</div>
	
</body>

</html>
