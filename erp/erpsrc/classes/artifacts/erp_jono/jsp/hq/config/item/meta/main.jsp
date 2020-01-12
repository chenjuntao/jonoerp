<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>item config</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/meta/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/meta/export.js?Version=<%=currenttime %>"></script>
	
	<style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/item/meta/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<script type="text/javascript">
		var itemType = '${itemType }';
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
	<table class="hovertable" width="100%" border="1">
	
		<tr>
			<td class="label_right" style="width: 100px;">物料名称或编码：</td>
			<td class="text_left">
	 			<input type="text" id="itemName" placeholder="输入%,查询所有" onkeydown="if(event.keyCode==13){doQuery();return false;}" />
	 			
				<span style="display: inline-block;width: 20px;"></span>
				<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: doQuery'>查询物料</button>
				<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: doAdd'>新增</button>
		   	 	<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: doDelete'>删除</button>
		   	 	<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: showDialog'>导出</button>
	 		</td>
		</tr>
	</table>
	
	<p class="area_blank">&nbsp;</p>
	
	<div id="categoryTree">
	</div>
	
	<div id="dataGrid" >
	</div>
</body>

</html>
