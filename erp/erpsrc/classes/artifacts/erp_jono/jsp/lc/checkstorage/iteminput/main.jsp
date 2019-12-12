<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:0"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/lc/checkstorage/iteminput/main.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/checkstorage/icreate/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="6">
					<div style="padding-left: 8px;">
						<b>盘点清单选择</b>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="label_right" style="width: 90px;">盘点批次：</td>
				<td class="text_left" style="width: 120px;">
					<input type="hidden" id="branchId" name="checkHeader.checkBranchId" value='${lcCode }' />
					<input type="hidden" name="checkHeader.checkBranch" value='物流中心' />
					
					<select id="checkBatchId" name="checkHeader.checkBatchId">
					</select>
				</td>
				
				<td class="label_right" style="width: 90px;">盘点清单：</td>
				<td class="text_left">
					<select id="formId" name="checkHeader.formId">
					</select>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
	
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>盘点清单输入</b>
					</div>
				</td>
			</tr>
		</table>
		
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="createGrid2"></div>
	</form>

	<p class="area_blank">&nbsp;</p>
	
	<table class="hovertable" id="commandTable" width="100%" border="1">
		<tr>
			<td class="text_left" colspan="4" style="text-align: center;">
			    <input type="button" id="btn_submit" value="盘点清单输入确认" style="margin-left: 8px;" />
		    	<input type="button" value="空白盘点清单打印" style="margin-left: 8px;" />
			</td>
		</tr>
	</table>
</body>

</html>
