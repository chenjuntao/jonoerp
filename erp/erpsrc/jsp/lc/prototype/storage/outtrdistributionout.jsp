<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
</head>

<body class="claro">
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>内部配送出库管理</b>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<p>按物流中心出货单检查库存，根据实际库存情况进行出库，外部配送需要生成出货单。同时会更改外部订货单的状态为已发货。</p>
	<p class="area_blank">&nbsp;</p>
</body>

</html>
