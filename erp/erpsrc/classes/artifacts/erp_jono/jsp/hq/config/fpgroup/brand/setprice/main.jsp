<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>brand config group price</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/fpgroup/brand/setprice/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/fpgroup/brand/setprice/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/fpgroup/brand/setprice/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var g_priceGroupId = '${priceGroupId }';
		var g_priceGroupName = '${priceGroupName}';
		var tag = 1;
	</script>
</head>

<body class="claro">
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出"'>
			<table style="position:relative;">
				<tr>
					<td>
						<label for="type">导出格式: </label>
					</td>
					
					<td>
						<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;"name="typeSelection" required="true">
							<option value="excel">excel</option>
							<option value="csv">csv</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" style="text-align:right;">
						<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
						<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button>
					</td>
				</tr>
			</table>
	</div>
	
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1" >
			<tr>
			   	<td class="label_right" style="width: 60px;">名称：</td>
				<td class="label_left" style="width: 60px;">${priceGroupName}</td>
				<td class="label_right" style="width: 80px;">编码或名称：</td>
				<td class="text_left" style="width: 120px;">
		 			<input type="text"  id="itemIdorName" onkeydown="if(event.keyCode==13){doQuery();return false;}" /><span style="display: inline-block;width: 20px;"></span>
		 		</td>
		 		<td>
		 			<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: doQuery'>查询</button>
				</td>
				<td>
		   	 		<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: showDialog'>导出</button>
		 		</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>
	<div id="dataGrid"></div>
</body>

</html>
