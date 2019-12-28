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
						<b>物流中心日结管理</b>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<p>进入此页面进行日常结算操作，要求每日一结。系统会对当天物流中心相关的单据进行排查，
	例如查询是否存在已打印但未审核的入库单等等，如果排查未通过，强制要求进行对应单据的处理，
	直到所有排查通过，则显示日结结果，以此日结结果作为物流中心一日工作的认可结果。 </p>
	<p class="area_blank">&nbsp;</p>
</body>

</html>
