<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>setting close date</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/settle/closedate/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="queryForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 60px;">关账日：</td>
				<td class="text_left">
					<select id="closeDate" name="closeDate" value="${closeDate }"
						style="width: 50px;">
						<c:forEach var="i" begin="1" end="31" step="1"> 
							<c:choose>
							   <c:when test="${i==closeDate}">
						  			<option selected="selected" value ="${i}">${i}</option>
							   </c:when>
							   <c:otherwise>
						  			<option value ="${i}">${i}</option>
							   </c:otherwise>
							</c:choose>
    					</c:forEach>
					</select>
					
			   	 	<input type="button" id="saveBtn" onclick="doSave();" value="保存" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
	</form>
	<p class="area_blank">&nbsp;</p>
	
	<div id="dataGrid" style="top: 62px;">
	</div>
</body>

</html>
