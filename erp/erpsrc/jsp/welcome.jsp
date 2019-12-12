<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>欢迎</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
	<link rel="stylesheet" href="common/lib/dojo/resources/dojo.css" />
	<link rel="stylesheet" href="common/lib/dijit/themes/claro/claro.css">
	<script>dojoConfig = {parseOnLoad: true}</script>
	<script src='common/lib/dojo/dojo.js'></script>
	<script type='text/javascript' src='common/lib/util/Math.uuid.js'></script>
	<script type='text/javascript' src='common/js/tab.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type='text/javascript' src='<%=appRoot %>/jsp/desktop/nav.js?Version=<%=currenttime %>'></script>
	
<!-- 	<style type="text/css"> -->
	
<%--     <script type="text/javascript" src="<%=dojoBase %>/dojo/dojo.js" ></script> --%>
<%-- 	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script> --%>
<%--     <script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script> --%>
	<script type="text/javascript" src="<%=appRoot %>/jsp/welcome.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
    
    
	#TabContainer {
		position: absolute;
		right: 0;
		top: 0;
		bottom: 0;
		width: 100%;
		height: 100%;
	}
	
	.loading{
		position: absolute;left: 0px;top: 0px;z-index: 300;
		width: 100%;height: 100%;
		padding: 8px 0px 0px 8px;
 		background: white; 
	}
	
	.iframe_wrapper {
		width: 100%;height: 100%;
	}
	
	.tab_iframe {
		border: 0px;padding: 0px;margin: 0px;
		width: 100%;height: 100%;
	}
	
	.dijitTabPane {
		padding: 0px !important;
	}
    
<%--       @import "<%=dojoBase %>/dojo/resources/dojo.css"; --%>
<%--       @import "<%=dojoBase %>/dgrid/css/dgrid.css"; --%>
<%--       @import "<%=dojoBase %>/dgrid/css/skins/claro.css"; --%>
<%--       @import "<%=dojoBase %>/dijit/themes/claro/claro.css"; --%>
    </style>
    
<%-- 	<link rel="stylesheet" href="<%=appRoot %>/jsp/setting/user/main.css"/> --%>
<%-- 	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/> --%>
</head>

<body class="claro" style="overflow: hidden;">
<!-- 	<table class="hovertable" width="100%"> -->
		
<!-- 	</table> -->
	
	<div data-dojo-type="dijit/layout/TabContainer" id="TabContainer" style="width: 100%; height: 100%;">
		<div data-dojo-type="dijit/layout/ContentPane" title="欢迎" data-dojo-props="selected:true ">
		
			<div align="center">
				<div align="left">
					<input type="button" name="gotoRequest" onclick="gotoRequest()" id="gotoRequest" value="未处理要货单" hidden="true"/>
					<input type="button" name="gotoInput" onclick="gotoInput()" id="gotoInput" value="未处理入库单" hidden="true"/>
<!-- 					<input type="button" name="gotoReturnGoods" onclick="gotoReturnGoods()" id="gotoReturnGoods" value="未处理配送退货单" hidden="true"/> -->
					<input type="button" name="gotoInputReturn" onclick="gotoInputReturn()" id="gotoInputReturn" value="未处理采购退货单" hidden="true"/>
					<input type="button" name="gotoLossItem" onclick="gotoLossItem()" id="gotoLossItem" value="未处理原料报损单" hidden="true"/>
					<input type="button" name="gotoLossSale" onclick="gotoLossSale()" id="gotoLossSale" value="未处理出品报损单" hidden="true"/>
					<input type="button" name="gotoTransfer" onclick="gotoTransfer()" id="gotoTransfer" value="未处理调拨单" hidden="true"/>
					<input type="button" name="gotoCheck" onclick="gotoCheck()" id="gotoCheck" value="未处理餐厅盘点单" hidden="true"/>
					<input type="button" name="gotoPurchase" onclick="gotoPurchase()" id="gotoPurchase" value="未处理采购单" hidden="true"/>
					<input type="button" name="gotoIn" onclick="gotoIn()" id="gotoIn" value="未处理验收入库单" hidden="true"/>
					<input type="button" name="gotoDistribution" onclick="gotoDistribution()" id="gotoDistribution" value="未处理越库配送单" hidden="true"/>
					<input type="button" name="gotoPicking" onclick="gotoPicking()" id="gotoPicking" value="未处理捡货单" hidden="true"/>
					<input type="button" name="gotoConfirmloss" onclick="gotoConfirmloss()" id="gotoConfirmloss" value="未处理报损单" hidden="true"/>
					<input type="button" name="gotoOutReturn" onclick="gotoOutReturn()" id="gotoOutReturn" value="未处理外部订货方退货单" hidden="true"/>
					<input type="button" name="gotoGoodsReturn" onclick="gotoGoodsReturn()" id="gotoGoodsReturn" value="未处理采购退货单" hidden="true"/>
					<input type="button" name="gotoCheckstorage" onclick="gotoCheckstorage()" id="gotoCheckstorage" value="未处理外物流中心盘点单" hidden="true"/>
					
					<input type="button" name="gotoPlan" onclick="gotoPlan()" id="gotoPlan" value="未处理生产计划单" hidden="true"/>
					<input type="button" name="gotoItemBuy" onclick="gotoItemBuy()" id="gotoItemBuy" value="未处理原料采购单" hidden="true"/>
<!-- 					<input type="button" name="gotoWork" onclick="gotoWork()" id="gotoWork" value="生产工单审核" hidden="true"/> -->
<!-- 					<input type="button" name="gotoSupperGet" onclick="gotoSupperGet()" id="gotoSupperGet" value="超领单审核" hidden="true"/> -->
					<input type="button" name="gotoCfInput" onclick="gotoCfInput()" id="gotoCfInput" value="未处理采购入库单" hidden="true"/>
					<input type="button" name="gotoItemInput" onclick="gotoItemInput()" id="gotoItemInput" value="未处理产品入库" hidden="true"/>
					<input type="button" name="gotoWorkReturn" onclick="gotoWorkReturn()" id="gotoWorkReturn" value="未处理生产退料" hidden="true"/>
					<input type="button" name="gotoWorkGet" onclick="gotoWorkGet()" id="gotoWorkGet" value="未处理生产领料" hidden="true"/>
					<input type="button" name="gotoWorkSupper" onclick="gotoWorkSupper()" id="gotoWorkSupper" value="未处理生产超领" hidden="true"/>
					<input type="button" name="gotoNoWorkGet" onclick="gotoNoWorkGet()" id="gotoNoWorkGet" value="未处理非工单领料" hidden="true"/>
					<input type="button" name="gotoItemOut" onclick="gotoItemOut()" id="gotoItemOut" value="未处理产品出库单" hidden="true"/>
					<input type="button" name="gotoCfItemLoss" onclick="gotoCfItemLoss()" id="gotoCfItemLoss" value="未处理原料报损单" hidden="true"/>
					<input type="button" name="gotoCfSaleLoss" onclick="gotoCfSaleLoss()" id="gotoCfSaleLoss" value="未处理出品报损单" hidden="true"/>
					<input type="button" name="gotoGetReturn" onclick="gotoGetReturn()" id="gotoGetReturn" value="未处理采购退货" hidden="true"/>
					<input type="button" name="gotoCfCheck" onclick="gotoCfCheck()" id="gotoCfCheck" value="未处理盘点单" hidden="true"/>
					<input type="button" name="gotoCfTransfer" onclick="gotoCfTransfer()" id="gotoCfTransfer" value="未处理调拨单" hidden="true"/>
				</div>
				<img src="common/img/welcome.jpg">
			</div>
		</div>
	</div>
		
</body>

</html>
