<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>bill manage</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/stock/picking/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/delivery/manage/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
	 	<input type="hidden" id="bDate" value='' />
	  	<input type="hidden" id="ids" name="ids" value='' />
	  	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 80px;">配送日期：</td>
				<td class="text_left"  style="width: 120px;">
					<input type="text" class="Wdate" id="businessDate"  name="businessDate" value="${businessDate}"	style="width: 110px;" onFocus="WdatePicker()" />
				</td>
				
				<td class="label_right" style="width: 80px;">是否加单：</td>
				<td class="text_left" style="width: 200px;"  >
					<input type="radio" name="status" value="0">全部</input>
					<input type="radio" name="status" checked="checked" value="1">正常要货</input>
					<input type="radio" name="status" value="2">加单</input>
				</td>
					<td class="label_right" style="width: 100px;">商品名称或编码：</td>
				<td class="text_left" style="width: 100px;">
	 				<input type="text" id="itemName" name="itemName" style="width: 80px;"	onkeydown="if(event.keyCode==13){doQuery();return false;}" />
	 			</td>
				<td>
			   	 	<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 58px;width: 60px;" />
			   	 	<input type="button" onclick="doAggregate();" value="汇总配送数据" style="margin-left: 18px;" />
				</td>
			</tr>
		</table>
	
		<p class="area_blank">&nbsp;</p>
	
		<div id="dataGrid" style="top: 32px;"></div>
	</form>
</body>

</html>
