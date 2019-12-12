<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>import check item</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/setting/import/import.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	
	<form id="uploadForm" method="post" enctype="multipart/form-data">
		<table class="hovertable" width="100%" border="1">
		<tr>
		<td class="label_right" style="width: 220px;">
		业务信息备份内容：
		</td>
			<td class="text_left" colspan="3">
			<div style="padding-left: 8px; font-weight: 900; font-size: 15px; color: blue;">
						<b>1. 单据明细表  2. 出品明细表   3. 付款明细表 </b>
					</div>
				</td>
			</tr>
			<tr>
				<td class="label_right" style="width:90px;">文件：</td>
				<td class="text_left">
			    	<input type="file" id="fileurl" name="attachment" />
			    	
			   	 	<input type="button" onclick="importMaterial();" value="还原" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>