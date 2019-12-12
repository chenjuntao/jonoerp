<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>transfer err view</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restapi/log/err.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript">
		var logId = '${logId }';
	</script>

 	<link rel="stylesheet" href="<%=dojoBase %>/dojo/resources/dojo.css">
  	<link rel="stylesheet" href="<%=dojoBase %>/dijit/themes/claro/claro.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/dgrid.css">
    <link rel="stylesheet" href="<%=dojoBase %>/dgrid/css/skins/claro.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restapi/log/err.css">
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<fieldset id="gpJson">
     	<legend>出错的Json数据：</legend>
     	<div class="grid" id="errJsonGrid" />
    </fieldset>
	<fieldset id="gpMsg">
     	<legend>具体的警告/出错信息：</legend>
     	<div class="grid" id="errMsgGrid" />
    </fieldset>
</body>

</html>
