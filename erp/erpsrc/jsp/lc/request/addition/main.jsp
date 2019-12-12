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
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/addition/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/addition/request.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/request/addition/outer.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/query/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/lc/request/outsummary/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td class="text_left">
				<div style="padding-left: 8px;">
					<b>当前营业日期为${businessDate }</b>
					
		   	 		<input type="button" onclick="doQuery();" value="刷新" style="margin-left: 58px;" />
				</div>
			</td>
		</tr>
	</table>
	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="4">
				<div style="padding-left: 8px;"><strong>已审核餐厅要货单列表</strong></div>
			</td>
		</tr>
 		</table>
	<div id="requestGrid" class="dataGrid">
	</div>
	
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td colspan="4">
				<div style="padding-left: 8px;"><strong>已审核外部订货单列表</strong></div>
			</td>
		</tr>
 		</table>
	<div id="outerGrid" class="dataGrid">
	</div>
	
	<form id="dataForm" method="post" >
	  	<input type="hidden" id="ids" name="ids" value='' />
	  	<input type="hidden" id="businessDate" name="businessDate" value='${businessDate }' />
	</form>
</body>

</html>
