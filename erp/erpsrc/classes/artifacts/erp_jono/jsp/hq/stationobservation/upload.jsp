<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>upload item photo</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
		<script type="text/javascript" src="<%=appRoot %>/jsp/hq/stationobservation/upload.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="uploadForm" method="post" enctype="multipart/form-data">
		<input type='hidden'  id="itemId" name='itemId' value='${itemId}' />
	    <input type='hidden' id="partId" name='partId' value='bPartTitle' />
	    <input type='hidden' id="processName" name='processName' value='${processName}' />
	    <input type='hidden' id="processId" name='processId' value='${processId}' />
	    
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width:90px;"> 
					操作步骤：
				</td>
				<td>
					<span>${processName}</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width:90px;">示范图片：</td>
				<td class="text_left">
			    	<input type="file" id="fileurl" name="attachment" />
				</td>
			</tr>
			<tr>
				<td class="label_right"  style="width:90px;">示范说明：</td>
				<td >
				<textarea style="width: 500px;height: 88px"id="instruction" name="observationDetail.stepRemark">
</textarea>
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="uploadFile();" value="确定" />
			</td>
		</tr>
	</table>
</body>

</html>