<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/distribution/manual/dlg.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/distribution/manual/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/distribution/manual/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<s:token/>
		
	 	<input type="hidden" id="jsonData" name="jsonData" value='' />
	 	
		<table id="dataForm" class="hovertable" width="100%">
			<tr>
			<td class="label_right" style="width: 70px;">到货日期：</td>
				<td class="text_left">
						<input type="text" class="Wdate" id="receiveTime" value="${businessDate}" name="shippingHeader.receiveTime" style="width: 110px;" onFocus="WdatePicker()" />
				</td>
				<td class="label_right" style="width: 80px;">备注：</td>
				<td class="text_left" colspan="1">
	  				<input type="text" name="shippingHeader.formNote" value="" style="width: 380px;" />
	  			</td>
			</tr>
			
			<tr>
				<td class="label_right">门店：</td>
				<td class="text_left" colspan="3">
					<input type='hidden' id="branchId"  name="branchIds" value='' />
					<span id="branchText" style="display: inline-block; height: 58px;">&nbsp;</span>
				</td>
	 		</tr>
	 		
	 		<tr>
  				<td class="text_left" colspan="4">	
	  				<input type="button" onclick="selBranch()" value="选择门店" style="margin-left: 58px;" />
			    	<input type="button" onclick="selMaterial();" value="自选原料" style="margin-left: 58px;" />
				    <input type="button" onclick="doSubmit()" value="生成配送单" style="margin-left: 8px;" />
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" style="top: 121px;"></div>
	</form>
</body>

</html>
