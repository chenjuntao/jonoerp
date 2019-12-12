<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>product config</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
   	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
    <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/product/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/item/product/export.js?Version=<%=currenttime %>"></script>
	
	<style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/config/item/product/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var itemType = '${itemType }';
		var g_suitCateId = '${suitCateId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
<!-- 导出 打印对话框 -->
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出成本卡"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
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
	
	<!-- 导出 打印对话框 -->
	<div id="customDialog1"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出成本卡"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				
				<td>
					<select id="typeSelection1" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:right;">
					<button id="export1" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport1'>导出</button>
					<button id="cancel1" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog1' >取消</button>
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 导出 打印对话框 -->
	<div id="customDialog2"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出售卖价"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				
				<td>
					<select id="typeSelection2" data-dojo-id="typeSelection"	data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:right;">
					<button id="export2" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport2'>导出</button>
					<button id="cancel2" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog2' >取消</button>
				</td>
			</tr>
		</table>
	</div>
	
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<input type="radio" name="searchType" value="raw" /> 按原料
					<input type="radio" name="searchType" value="product" checked="checked" /> 按出品
					<input type="text" id="condition" name="condition" placeholder="输入%,查询所有" onkeydown="if(event.keyCode==13){doQuery();return false;}" style="margin-left: 8px;" />
					<input type="text" style="display: none;" />
					<input type="hidden" name="displayAllFlag" value="Y"/>
					<button  data-dojo-type="dijit/form/Button" style="margin-left: 50px;" data-dojo-props='onClick: doQuery'>查  询</button>
<!-- 					<button  data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: showDialog'>批量导出成本卡</button> -->
					<button  data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: showDialog1'>批量导出成本卡</button>
					<button  data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: showDialog2'>批量导出售卖价</button>
					<button  data-dojo-type="dijit/form/Button" style="margin-left: 50px;" onclick="doAdd();">新  增</button>
			   	 	<button  data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doDelete'>删  除</button>
<!-- 			   	 	<button  data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doPush'>出品信息即时推送</button> -->
<!-- 			   	 	<button  data-dojo-type="dijit/form/Button" style="margin-left: 10px;" data-dojo-props='onClick: doCompute'>出品成本价即时计算</button> -->
			   	 	
				</td>
			</tr>
		</table>
	</form>
	
	<p class="area_blank">&nbsp;</p>
	
	<div id="categoryTree"></div>
	
	<div id="dataGrid">
	 <input type="hidden" id="downloadTokenValue" name="downloadTokenValue"/>
	</div>
	
    <div id="dateDlg" style="display: none;">
		<input type="text" class="Wdate" onFocus="WdatePicker();" style="width: 120px;"
			id="effectDate" onkeydown="if(event.keyCode==13)setEffectDate();">
        <input type="button" onclick="setEffectDate();" style="margin-left: 38px;" value="确定" /> 
    </div>
</body>

</html>
