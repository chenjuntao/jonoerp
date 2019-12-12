<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>scan request bill</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript">
		var formId = '${formId }';
		var branchType = '${branchType }';
	</script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/goodsbill/query/scan.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/goodsbill/query/export.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
       @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<!-- 导出 打印对话框 -->
	<div id="customDialog"  style="display: none;" data-dojo-type="dijit/Dialog" data-dojo-props='title:"导出/打印"'>
		<table style="position:relative;">
			<tr>
				<td>
					<label for="type">导出格式: </label>
				</td>
				
				<td>
					<select id="typeSelection" data-dojo-id="typeSelection" data-dojo-type="dijit/form/Select" style="width: 162px;" name="typeSelection" required="true">
						<option value="excel">excel</option>
						<option value="csv">csv</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" style="text-align:right;">
					<button id="export" data-dojo-type="dijit/form/Button" data-dojo-props='onClick: customExport'>导出</button>
					<button id="cancel" data-dojo-type="dijit/form/Button"  data-dojo-props='onClick: hideDialog' >取消</button>
				</td>
			</tr>
		</table>
	</div>
	
	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 100px;">单据编号：</td>
				<td class="text_left" style="width: 250px;">
					<span>${requestHeader.formId }</span>
					<span style="margin-left: 50px"><b style='border: 1px green solid;'>${status }</b></span>
				</td>
				
				<td class="label_right" style="width: 100px;">要货部门：</td>
				<td class="text_left"  style="width: 200px;">
					<span>${requestHeader.buyerName }</span>
				</td>
				<td class="label_right" style="width: 100px;">到货日期：</td>
				<td class="text_left">
					<span>${receiveTime }</span>
				</td>
			</tr>
			
			<tr>
				<td class="label_right">订货地址：</td>
				<td class="text_left">
					<span>${requestHeader.buyerAddress }</span>
				</td>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${formMaker}</span>
				</td>
				
				<td class="label_right">制单日期：</td>
				<td class="text_left">
					<span>${formTime }</span>
				</td>
				
			</tr>
			
			<tr>
				<td class="label_right">安全存量：</td>
				<td class="text_left">
					<span>${requestHeader.safetyStock }</span>
				</td>
				<td class="label_right">审核人员：</td>
				<td class="text_left">
					<span>${auditor}</span>
				</td>
				
				<td class="label_right">审核日期：</td>
				<td class="text_left">
					<span>${auditTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">预估销售额：</td>
				<td class="text_left">
					<span>${requestHeader.sellPredict }</span>
				</td>
				<td class="label_right">延滞期金额：</td>
				<td class="text_left">
					<span>${requestHeader.delayPredict }</span>
				</td>
				
				<td class="label_right">进货周期金额：</td>
				<td class="text_left">
					<span>${requestHeader.purchasePredict }</span>
				</td>
			</tr>
			<tr>
				
				<td class="label_right">参考日期区间1：</td>
				<td class="text_left">
					<span>${refDateStart1 } - ${refDateEnd1 }</span>
				</td>

				<td class="label_right">参考日期区间2：</td>
				<td class="text_left">
					<span>${refDateStart2 } - ${refDateEnd2 }</span>
				</td>
				
				<td class="label_right">参考日期区间3：</td>
				<td class="text_left">
					<span>${refDateStart3 } - ${refDateEnd3 }</span>
				</td>
			</tr>
			
			<tr>
				
				
				<td class="label_right">备注：</td>
				<td class="text_left">
					<span>${requestHeader.formNote }</span>
				</td>
				<td class="label_right">操作：</td>
				<td colspan="3">
					<button  data-dojo-type="dijit/form/Button"   data-dojo-props='onClick: showDialog'>导出明细</button>
					<button  data-dojo-type="dijit/form/Button"  style="margin-left: 50px;" data-dojo-props='onClick: doClose'>关闭页面</button>
				</td>
			</tr>
			
		</table>
	  
		<p class="area_blank">&nbsp;</p>
		
		<div id="dataGrid" class="commonGrid" style="top: 182px;" ></div>
	</form>	
</body>

</html>
