<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/centralfactory/productionDemand/summary/check.js?Version=<%=currenttime %>"></script>
		<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/centralfactory/productionDemand/summary/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
  		<input type="hidden" id="ids" name="ids" value='${ids}' />
  		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">制单人员：</td>
				<td class="text_left" style="width: 180px;">
					<span>${arrangmentHeader.formMaker }</span>
					<input type="hidden"  name="arrangmentHeader.formMaker" value='${arrangmentHeader.formMaker }' />
					<input type="hidden"  name="arrangmentHeader.formMakerId" value='${arrangmentHeader.formMakerId }' />
				</td>
				
				<td class="label_right" style="width: 120px;">制单日期：</td>
				<td class="text_left" style="width: 180px;">
					<span>${formTime }</span>
					<input type="hidden" id="formTime" name="formTime" value='${formTime }' />
				</td>
					
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="4">
					<span>${formNote }</span>
	  				<input type="hidden"  name="arrangmentHeader.formNote" value='${formNote}' />
	  				
	  				 <input type="button" onclick="doSubmit();" value="生成生产计划单" style="margin-left: 100px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="createGrid"></div>
	</form>
</body>

</html>
