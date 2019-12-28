<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>ten thousand materail usage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/businessquery/foodRawQuery/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      @import "<%=dojoBase %>/cbtree/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/businessquery/foodRawQuery/main.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow: hidden;">
	<table class="hovertable" width="100%" border="1">
		<tr valign="middle">
			<td class="label_right" style="width: 100px;">选择门店：</td>
			
			<td class="text_left" style="width: 180px;">
				<s:select list="shopLst" listKey="code" listValue="name" theme="simple" name="shopC" />
			</td>
			
			<td class="label_right" style="width: 100px;">营业日期：</td>
			
			<td class="text_left">
				<input type="text" class="Wdate" id="startDate" value="${startDate}" style="width: 110px;" onFocus="WdatePicker()" />
				
				<span style="padding: 0 5px;">至</span>
				
				<input type="text" class="Wdate" id="endDate" value="${endDate}" style="width: 110px;" onFocus="WdatePicker()" />
					
				<input type="button" onclick="queryAmount();" value="查询营业额" style="margin-left: 58px;">
			</td>
		</tr>
	</table>
	
	<div id="categoryTree"></div>
	
	<div id="amountArea" style="overflow: auto">
		<table class="hovertable" width="100%" border="1">
			<tr valign="middle">
				<td class="label_right" style="width: 100px;">总营业额：</td>
				
				<td class="text_left">
					<span id="payAmt"></span>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="detailGrid" style="overflow: auto"></div>
</body>

</html>
