<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*"%>
<!-- 
作者：吴鹏
时间：2014-07-16
说明：显示餐厅出品信息
    -->
<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>

<html>
<head>
</head>

<body bgcolor="white">
<!--此界面部分参照天天系统的出品查询界面实现，请参见restaurant\imp\出品信息.jpg-->

<!--此界面分为两部分，左边是一个树形控件，显示出品的类别，选取后右方区域显示该类别的出品信息列表；
目前shopDish.jsp文件仅列出了右方区域的内容，左方树形控件还未实现
如遇详细问题不清楚，请与小陈协商解决-->



<!--右方区域，显示特定类型的一批出品的基础信息列表-->

		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800">
				<div align="Left">
				  <b>餐厅出品信息</b>				  </div></td>
			</tr>
		</table>
		
	<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  align="center" bgcolor="#006600">
			<th width="7%" style="text-align:center;">序号</th>
			<th width="9%" style="text-align:center;">出品编码</th>
			<th width="14%" style="text-align:center;">出品名称</th>
			<th width="10%" style="text-align:center;">单位</th>
			<th width="10%" style="text-align:center;">助记码</th>
			<th width="10%" style="text-align:center;">大类</th>
			<th width="10%" style="text-align:center;">小类</th>
			<th width="10%" style="text-align:center;">成本价</th>
			<th width="10%" style="text-align:center;">销售价</th>
			<th width="10%" style="text-align:center;">成本卡</th>
		</tr>
			<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><a
					href='javascript: require(["dijit/registry", "dijit/layout/ContentPane"], function(registry, ContentPane){
                        var tabs = registry.byId("TabContainer2");
                        var pane = new ContentPane({title:"成本卡信息",closable: true, href:"shopCostCard.action", selected:true});
                        tabs.addChild(pane);
                        tabs.selectChild(pane);
                        });
        document.innerHTML'
					class="btn"><i class="icon icon-eye-open"></i>查看 </a></td>
				</tr>
	</table>
	
	
</body>
</html>
