<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- 该 DTD 包含所有 HTML 元素和属性，包括展示性的和弃用的元素（比如 font）,不允许框架集（Framesets） -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 如果需要使用符合 XML 规范的 XHTML 文档，则应该在文档中的<html> 标签中至少使用一个 xmlns 属性，以指定整个文档所使用的主要命名空间：<html xmlns="http://www.w3.org/1999/xhtml"> -->
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>create allocate bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/allocateitem/create/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/allocateitem/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var jsonData = '${jsonData }';
		var gridData = [];
		if (jsonData != '') {
			gridData = JSON.parse('${jsonData }');
		}
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<input type="hidden" id="branchType" name="branchType" value="${branchType}" />
<input type="hidden" id="transferType" name="transferType" value="${transferType}" />
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="12">
					<div style="padding-left: 8px;">
						<b>调拨原料选择</b>
					</div>
				</td>
			</tr>
			
			<td colspan="12">
			    	<input type="button" id="DIYwithnoModel" onclick="selMaterial();" value="根据原料自选" style="margin-left: 8px;" />
			    	<input type="button" onclick="delMaterial();" value="删除原料" style="margin-left: 10px;">
			</td>
			
			<tr>
				<td class="label_right" style="width: 100px;">调入部门：</td>
				<td class="text_left" >
					<s:select list="inShopLst" listKey="code" listValue="name" theme="simple"  style="width: 150px;" id="inBranchId" name="transferHeader.inBranchId" onchange= "refreshStorage1(inBranchId,inStorageId)"></s:select>
					<input type="hidden" id="inBranch" name="transferHeader.inBranch"  />
				</td>
				
				<td class="label_right" style="width: 120px;">调入仓库：</td>
				<td>
					<s:select list="inStorageList" listKey="storageId" listValue="storageName" theme="simple" style="width: 200px;" id="inStorageId" name="transferHeader.inStorageId" ></s:select>
					<input type="hidden" id="inStorage" name="transferHeader.inStorage" />
				</td>
				
				<td class="label_right" rowspan="2">备注：</td>
				<td class="text_left" colspan="3"  rowspan="2">
	  				<input type="text" name="transferHeader.formNote" value="${transferHeader.formNote}" style="width: 320px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 100px;">调出部门：</td>
				<td class="text_left" >
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"  onchange= "refreshStorage1(outBranchId,outStorageId)" style="width: 150px;" id="outBranchId" name="transferHeader.outBranchId"></s:select>
					<input type="hidden" id="outBranch" name="transferHeader.outBranch"  />
				</td>
				
				<td class="label_right" style="width: 120px;">调出仓库：</td>
				<td>
					<s:select list="outStorageList" listKey="storageId" listValue="storageName" theme="simple" style="width: 200px;" id="outStorageId" name="transferHeader.outStorageId"  ></s:select>
					<input type="hidden" id="outStorage" name="transferHeader.outStorage" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top:125px;bottom: 33px"></div>
		
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
			
		<p class="area_blank">&nbsp;</p>
		
		<table class="hovertable" id="commandTable" width="100%" border="1">
			<tr>
				<td class="text_left" colspan="4" style="text-align: center;">
				    <input type="button" id="btn_submit" value="生成调拨单" style="padding-left: 8px;" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>
