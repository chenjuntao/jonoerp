<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/checkstorage/icreate/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/checkstorage/icreate/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>盘点批次选择</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">餐厅：</td>
				<td class="text_left" style="width: 120px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 style="width: 150px;" id="branchId" name="checkHeader.checkBranchId"></s:select>
					<input type="hidden" id="checkBranch" name="checkHeader.checkBranch" value='' />
				</td>
				
				<td class="label_right" style="width: 90px;">盘点批次：</td>
				<td class="text_left">
					<select id="checkBatchId" name="checkHeader.checkBatchId">
					</select>
					
					<span id="itemRepeatable"></span>
					
				    <input type="button" id="btn_delete" value="删除当前批次" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>盘点清单原料选择</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
			   	 	<input type="button" onclick="pickModel();" value="使用模板" style="margin-left: 8px;" />
			    	<input type="button" onclick="saveAsModel();" value="存为模板" style="margin-left: 8px;" />
			    	<input type="button" onclick="selMaterial();" value="自选原料" style="margin-left: 8px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 120px;">备注：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" name="checkHeader.formNote" value="${checkHeader.formNote}" style="width: 380px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top: 155px;bottom=35px"></div>
		
		<p class="area_blank">&nbsp;</p>
		
		<table class="hovertable" id="commandTable" width="100%" border="1">
			<tr>
				<td class="text_left" colspan="4" style="text-align: center;">
				    <input type="button" id="btn_submit" value="生成餐厅盘点清单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
