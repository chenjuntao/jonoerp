<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>create distribution bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/distribution/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/distribution/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=dojoBase %>/dojox/form/resources/BusyButton.css">
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		
	  	<input type="hidden" id="ids" name="ids" value='' />
		<input type='hidden' name='shippingHeader.formType' value='INNER_UNIFIED' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">要货部门：</td>
				<td class="text_left" style="width: 180px;">
					<s:select list="shopLst" listKey="code" listValue="name" theme="simple"	 style="width: 150px;" id="branchId" headerKey="-1" headerValue="--请选择--" ></s:select>
				</td>
				
				<td class="label_right" style="width: 70px;">审核日期：</td>
				<td class="text_left">
					<input type="text" class="Wdate" id="startDate"  value="${businessDate}" style="width: 110px;" onFocus="WdatePicker()" />
					<span style="padding: 0 5px;">至</span>
					<input type="text" class="Wdate" id="endDate"  value="${businessDate}"	style="width: 110px;" onFocus="WdatePicker()" />
					
			   	 	<input type="button" onclick="doQuery();" value="查    询" style="margin-left: 58px;width: 60px;margin-right: 90px" />
			   	 	<div id="placeHolder"></div>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top:33px"></div>
	</form>
</body>

</html>
