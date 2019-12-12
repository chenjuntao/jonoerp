<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>功能查询</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript' src="<%=appRoot %>/jsp/setting/menu/query/main.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript'>
		var filterHidden = '${filter}';
		console.log(filterHidden)
	</script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/cbtree/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/menu/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/menu/query/main.css"/>
</head>

<body class="claro" style="overflow: hidden; margin: 0px;">
	<div id="treeWrapper"></div>
	
	<div id="editArea">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">菜单名称：</td>
				<td class="text_left">
		 			<input type="text" id="menuName" onkeydown="if(event.keyCode==13)doQuery();" style="width: 280px;" />
				</td>
			</tr>
		</table>
			
		<p class="area_blank">&nbsp;</p>
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td style="text-align: center;">
				    <input type="button" onclick="doQuery();" value="查询" />
				</td>
			</tr>
		</table>
		
		<div>
			<div class="tagTitle">标签筛选</div>
			<div id="tagArea">
				<span>要货</span>
				<span>配送</span>
				<span>采购</span>
				<span>报损</span>
				<span>调拨</span>
				<span>盘点</span>
				<span>日结</span>
				<span>库存</span>
			</div>
		</div> 
	</div>
</body>
</html>
