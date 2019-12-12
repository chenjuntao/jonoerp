<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit shelf</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/shelf/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var oldShelfId = '${shelfId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">编号：</td>
				<td class="text_left" style="width: 180px;">
					<c:choose>
					   <c:when test="${shelfId != null}">
						  	<span>${shelfId}</span>
				 			<input type="hidden" id="shelfId" name="shelf.shelfId"
				 				 value="${shelf.shelfId}" />
					   </c:when>
					   <c:otherwise>
				 			<input type="text" id="shelfId" name="shelf.shelfId"
				 				 value="${shelf.shelfId}" style="width: 80px;" />
					   </c:otherwise>
					</c:choose>
				</td>
				<td class="label_right" style="width: 80px;">名称：</td>
				<td class="text_left">
		 			<input type="text" id="shelfName" name="shelf.shelfName"
		 				 value="${shelf.shelfName}" style="width: 180px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">描述：</td>
				<td class="text_left" colspan="3">
	  				<input type="text" id="description" name="shelf.description" 
	  					value="${shelf.description}" style="width: 280px;" />
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doSave();" value="保    存" style="width: 60px"/>
			</td>
		</tr>
	</table>
</body>

</html>
