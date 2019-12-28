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
						<b>物流中心入库单审核</b>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<p>对生成的物流中心入库单进行审核，通过后对应采购单状态变为已收货。如发现问题，则对入库单进行修改。入库单必须当天进行审核，否则次日无法进行入库操作。</p>
	<p class="area_blank">&nbsp;</p>
</body>

</html>
