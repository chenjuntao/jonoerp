<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>check page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/restaurant/goodsbill/create/commit.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/restaurant/goodsbill/create/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
	    var gridData = JSON.parse('${jsonData }');
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">
	<form id="billForm" method="post" >
		<input type='hidden' name='requestHeader.formId' value='${requestHeader.formId }' />
		<input type='hidden' name='requestHeader.formType' value='estimate' />
		<input type='hidden' name='requestHeader.arrivePeriod' value='0' />
		<input type='hidden' name='requestHeader.buyerId' value='${lcCode }' />
		<input type='hidden' name='requestHeader.receiveTime' value='${receiveTime }' />
		<input type='hidden' name='requestHeader.formMaker' value='${requestHeader.formMaker }' />
		<input type='hidden' name='requestHeader.formTime' value='${formTime }' />
		<input type='hidden' name='requestHeader.formNote' value='${requestHeader.formNote }' />
		<input type='hidden' name='requestHeader.refDateStart1' value='${refDateStart1 }' />
		<input type='hidden' name='requestHeader.refDateEnd1' value='${refDateEnd1 }' />
		<input type='hidden' name='requestHeader.refDateStart2' value='${refDateStart2 }' />
		<input type='hidden' name='requestHeader.refDateEnd2' value='${refDateEnd2 }' />
		<input type='hidden' name='requestHeader.refDateStart3' value='${refDateStart3 }' />
		<input type='hidden' name='requestHeader.refDateEnd3' value='${refDateEnd3 }' />
		<input type='hidden' name='requestHeader.delayPredict' value='${requestHeader.delayPredict}' />
		<input type='hidden' name='requestHeader.purchasePredict' value='${requestHeader.purchasePredict}' />
		<input type='hidden' name='requestHeader.safetyStock' value='${requestHeader.safetyStock }' />
		<input type='hidden' name='requestHeader.sellPredict' value='${requestHeader.sellPredict }' />
		
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td class="label_right" style="width: 120px;">单据编号：</td>
				<td class="text_left" style="width: 150px;">
					<span>${requestHeader.formId }</span>
				</td>
				<td class="label_right" style="width: 100px;">到货日期：</td>
				<td class="text_left" colspan="3">
					<span>${receiveTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">制单人员：</td>
				<td class="text_left">
					<span>${loginUsername}</span>
				</td>
				<td class="label_right">制单日期：</td>
				<td class="text_left" colspan="3">
					<span>${formTime }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">参考日期区间1：</td>
				<td class="text_left">
					<span>${refDateStart1 } - ${refDateEnd1 }</span>
				</td>
				<td class="label_right">参考日期区间2：</td>
				<td class="text_left" colspan="3">
					<span>${refDateStart2 } - ${refDateEnd2 }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">参考日期区间3：</td>
				<td class="text_left">
					<span>${refDateStart3 } - ${refDateEnd3 }</span>
				</td>
				<td class="label_right">延滞期金额：</td>
				<td class="text_left" colspan="3">
					<span>${requestHeader.delayPredict }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">进货周期金额：</td>
				<td class="text_left">
					<span>${requestHeader.purchasePredict }</span>
				</td>
				<td class="label_right">安全存量：</td>
				<td class="text_left" style="width: 60px;">
					<span>${requestHeader.safetyStock }</span>
				</td>
				<td class="label_right" style="width: 70px;">预估销售额：</td>
				<td class="text_left">
					<span>${requestHeader.sellPredict }</span>
				</td>
			</tr>
			<tr>
				<td class="label_right">备注：</td>
				<td class="text_left" colspan="5">
					<span style="display: inline-block;width: 300px;">${requestHeader.formNote }</span>
					
			    	<input type="button" id="btn_submit" value="确定提交预估单" style="margin-left: 58px;" />
				</td>
			</tr>
		</table>
	  
		<p class="area_blank">&nbsp;</p>
	  	<input type="hidden" id="jsonData" name="jsonData" value='${jsonData }' />
		<div id="dataGrid" class="checkGrid" style="top: 182px;">
		</div>
	</form>	
</body>

</html>
