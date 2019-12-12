<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit group</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/group/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var oldRegionId = '${regionId }';
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">编号：</td>
				<td class="text_left" style="width: 180px;">
					<c:choose>
					   <c:when test="${group.enabled == 1}">
						  	<span>${regionId}</span>
				 			<input type="hidden" id="regionId" name="group.regionId"
		 				 		value="${group.regionId}" />
					   </c:when>
					   <c:otherwise>
				 			<input type="text" id="regionId" name="group.regionId"
				 				 value="${group.regionId}" style="width: 80px;" />
					   </c:otherwise>
					</c:choose>
				</td>
				<td class="label_right" style="width: 80px;">名称：</td>
				<td class="text_left">
		 			<input type="text" id="regionName" name="group.regionName"
		 				 value="${group.regionName}" style="width: 180px;" />
				</td>
			</tr>
			<tr>
				<td class="label_right">所属物流中心ID：</td>
				<td class="text_left">
					<s:select list="lcCenterLst" listKey="code" listValue="name" theme="simple"
						 style="width: 150px;" id="branchId" name="group.branchId"
		 				 value="%{group.branchId}"></s:select> 
				</td>
				<td class="label_right">配送周期(天)：</td>
				<td class="text_left">
	  				<input type="text" id="deliveryCycle" name="group.deliveryCycle" 
	  					value="${group.deliveryCycle}" style="width: 80px;" />
				</td>
			</tr>
		</table>
	</form>
		
	<p class="area_blank">&nbsp;</p>
	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;">
			    <input type="button" onclick="doSave();" value="保存" />
			</td>
		</tr>
	</table>
</body>

</html>
