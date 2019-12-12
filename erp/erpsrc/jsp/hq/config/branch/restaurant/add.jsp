<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit branch</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src="<%=dojoBase%>/dojo/dojo.js"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot%>/jsp/hq/config/branch/restaurant/add.js?Version=<%=currenttime %>"></script>
	
	<style type="text/css">
		@import "<%=dojoBase%>/dojo/resources/dojo.css";
		@import "<%=dojoBase%>/dgrid/css/dgrid.css";
		@import "<%=dojoBase%>/dgrid/css/skins/claro.css";
	</style>

	<link rel="stylesheet"	href="<%=appRoot%>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot%>/jsp/common/css/common.css" />

	<script type="text/javascript">
		var oldBranchId = '${branchId }';
		var g_branchType = '${branchType }';
		var tag = 1;
	</script>
</head>

<body class="claro" style="overflow-y: auto; overflow-x: hidden;">
	<form id="dataForm" method="post">
		<input type='hidden' name='branch.branchType' value='${branchType}' />

		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">编号：</td>

				<td class="text_left" style="width: 180px;"><c:choose>
						<c:when test="${branchId != null}">
							<span>${branchId}</span>
							<input type="hidden" id="branchId" name="branch.branchId"
								value="${branch.branchId}" />
						</c:when>

						<c:otherwise>
							<input type="text" id="branchId" name="branch.branchId"
								value="${branch.branchId}" style="width: 80px;" />
						</c:otherwise>
					</c:choose></td>

				<td class="label_right" style="width: 80px;">名称：</td>
				<td class="text_left" style="width: 80px;"><input type="text"
					id="branchName" name="branch.branchName"
					value="${branch.branchName}" style="width: 180px;" /></td>

				<td class="label_right" style="width: 80px;">助记码：</td>
				<td class="text_left"><input type="text" id="queryCode"
					name="branch.queryCode" value="${branch.queryCode}"
					style="width: 80px;" /></td>
			</tr>

			<tr>
				<td class="label_right" style="width: 100px;">门店分组：</td>
				<td class="text_left" style="width: 180px;"><s:select
						list="groupLst" listKey="id" listValue="name" theme="simple"
						style="width: 150px;" id="groupId" name="branch.regionId"
						value="%{branch.regionId}"></s:select></td>
				<td class="label_right">位置：</td>
				<td class="text_left" colspan="3"><input type="text"
					name="branch.branchAddress" value="${branch.branchAddress}"
					style="width: 380px;" /></td>


			</tr>

			<tr>
				<td class="label_right">电子邮箱：</td>
				<td class="text_left"><input type="text" id="email"
					name="branch.email" value="${branch.email}" style="width: 180px;" />
				</td>

				<td class="label_right">电话：</td>
				<td class="text_left"><input type="text" id="telephone"
					name="branch.telephone" value="${branch.telephone}"
					style="width: 180px;" /></td>

				<td class="label_right">传真：</td>
				<td class="text_left"><input type="text" id="fax"
					name="branch.fax" value="${branch.fax}" style="width: 180px;" /></td>
			</tr>
<%-- 			<c:choose>
				<c:when test="${branchId == null}">
					<tr>
						<td class="label_right">仓库编码：</td>
						<td class="text_left"><input type="text" id="storageId"
							name="storageId" value="" style="width: 180px;" /></td>

						<td class="label_right">仓库名称：</td>
						<td class="text_left"><input type="text" id="storageName"
							name="storageName" value="" style="width: 180px;" /></td>
						<td class="label_right">备注：</td>
						<td class="text_left"><input type="text" name="branch.remark"
							value="${branch.remark}" style="width: 180px;" /></td>
					</tr>
				</c:when>
				<c:otherwise> --%>
					<tr >
						<td class="label_right">备注：</td>
						<td class="text_left" colspan="5"><input type="text" name="branch.remark"
							value="${branch.remark}" style="width: 280px;" /></td>
					</tr>
<%-- 				</c:otherwise>
			</c:choose> --%>
		</table>
	</form>

	<p class="area_blank">&nbsp;</p>

	<table class="hovertable" width="100%" border="1">
		<tr>
			<td style="text-align: center;"><input type="button" onclick="doSave();" value="保存" /></td>
		</tr>
	</table>
</body>

</html>
