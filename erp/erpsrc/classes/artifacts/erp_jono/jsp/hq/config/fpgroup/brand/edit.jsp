<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>edit price group</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
    <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/config/fpgroup/brand/edit.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	var oldGroupId = '${priceGroupId }';
	var tag = 1;
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="dataForm" method="post" >
		<input type='hidden' name='priceGroup.priceGroupType' value='LC' />
		<table class="hovertable" width="100%" border="1">
			 <tr>
				<td class="label_right" style="width: 100px;">编号：</td>
				<td class="label_left" style="width: 180px;">
					<s:if test="%{priceGroupId == null}">
						<span id="preId">JOIN_</span>
<!-- 						<input type="label" value="JOIN_" id="preId" style="width: 60px;" readonly/> -->
			 			<input type="text" id="priceGroupId" name="priceGroup.priceGroupId"
			 				 value="${priceGroup.priceGroupId}" style="width: 80px;" />
					</s:if>
	     			<s:else>
						<input type='hidden' id="priceGroupId" name="priceGroup.priceGroupId" value='${priceGroup.priceGroupId}' />
						<span>${priceGroup.priceGroupId }</span>
	     			</s:else>
				</td>
				
				<td class="label_right" style="width: 80px;">名称：</td>
				<td class="text_left">
		 			<input type="text" id="priceGroupName" name="priceGroup.priceGroupName"
		 				 value="${priceGroup.priceGroupName}" style="width: 180px;" />
				</td>
		</tr>
		
		<tr>
			<td class="label_right">备注：</td>
			<td class="text_left" colspan="3">
  				<input type="text" name="priceGroup.priceGroupNote" value="${priceGroup.priceGroupNote}" style="width: 380px;" />
			</td>
		</tr>
		
		<tr>
			<td class="label_right">价格组类型：</td>
			
			<td >
				<s:if test="%{priceGroupId == null}">
							<s:select  id="type" style="width: 150px;"  theme="simple" list="#{1:'加盟价',2:'标准价',3:'零售价',4:'售卖价'}" labelposition="lable_right" listKey="key" listValue="value"  headerKey="0" 
								onchange="categoryChange()"></s:select>
				</s:if>
			</td>
			
			<td  colspan="3"></td>
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
