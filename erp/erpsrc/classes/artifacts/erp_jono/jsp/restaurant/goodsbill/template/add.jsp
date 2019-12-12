<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create template</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/goodsbill/template/add.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/common/template/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var templateType = '${templateType }';
		var lcCode = '${lcCode }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<div id="customDialog" style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"餐厅物料导入"'>
			<form id="uploadForm" method="post" enctype="multipart/form-data">
				<table class="hovertable" width="100%" border="1">
					<tr>
						<td class="label_right" style="width: 60px;">文件：</td>
						
						<td class="text_left">
							<input type="file" id="fileurl"	name="attachment" />
							<input type="button" onclick="importMaterial();" value="导入确认" style="margin-left: 48px;" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		
	<form id="templateForm" method="post" >
		<input type='hidden' name='templateMeta.branchId' id="branchId" value='${lcCode }' />
		<input type='hidden' name='templateMeta.templateType' id ="templateType" value='${templateType }' />
		
		<s:token/>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="6">
					<div style="padding-left: 8px;">
						<b>新增要货单模板</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="6">
					<input type="button" onclick="show();" value="导入原料" style="margin-left: 18px;" />
			   	 	<input type="button" onclick="selMaterial();" value="新增原料" style="margin-left: 8px;" />
			    	<input type="button" onclick="doSave();" value="保存模板" style="margin-left: 8px;" />
			    	<input type="button" onclick="doClose();" value="取消" style="margin-left: 8px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width: 100px;">门店分组：</td>
				<td class="text_left" style="width: 180px;">
					<s:select list="regionLst" listKey="id" listValue="name" theme="simple" style="width: 150px;" id="regionId" name="templateMeta.deliveryRegion"></s:select> 
				</td>
				
				<td class="label_right">最小到货周期：</td>
				<td class="text_left" colspan="5">
		 			<input type="text" id="arrivePeriod" name="templateMeta.arrivePeriod" value="" style="width: 80px;" />
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 100px;">模板名称：</td>
				<td class="text_left" style="width: 180px;">
		 			<input type="text" id="templateName" name="templateMeta.templateName" value="${template.templateName }" style="width: 180px;" />
				</td>
				
				<td class="label_right" style="width: 90px;">按物料编码排序:</td>
				<td class="text_left" style="width: 80px;"><input name="isSord" id="isSord"
					type="checkbox"
					style="vertical-align: middle; margin: 0px; padding: 0px;" /></td>
					
				<td class="label_right" style="width: 90px;">模板要货类别：</td>
				<td class="text_left" colspan="5">
					<span id="categoryId">${templateMeta.categoryId }</span>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid"></div>
	</form>
</body>

</html>
