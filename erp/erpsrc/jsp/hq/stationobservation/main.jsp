<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/form.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/stationobservation/order.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/stationobservation/observationGrid.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/stationobservation/main.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
	<link rel="stylesheet" href="<%=appRoot %>/jsp/hq/stationobservation/main.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
 		var itemId = '${itemMeta.itemId}';
	</script>
</head>
	
	<body class="claro">
		<div id="appLayout" class="demoLayout" data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="design: 'headline'">
			<div class="centerPanel" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region: 'center'">
				<div data-dojo-type="dijit/layout/ContentPane" title="Group 1">
					<h4>工作步骤</h4>
					<div id="observationGrid"></div>
				</div>
			</div>
			<div class="edgePanel" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region: 'top'" style="text-align: center ">
				<h1><b>${itemMeta.itemName}/${itemMeta.itemDimension}</b></h1>
				<h4>岗位观察核对表</h4>
			</div>
			<div id="leftCol" class="edgePanel" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region: 'left', splitter: true"></div>
			
			<div id="rightCol"  data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="region: 'right', splitter: true">
			<div class="centerPanel" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region: 'center'" style="height: 50%">
					<h4>成本卡/${itemMeta.itemDimension}</h4>
					<div id="costCardGrid"></div>
			</div>
			<div class="centerPanel" data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region: 'bottom'" style="height: 50%">
					<h4>参考图片</h4>
					<img id="itemPic" src="<%=appRoot %>/common/loadImage.action?objectId=${itemMeta.itemId}"  alt="[${itemMeta.itemId}]${itemMeta.itemName}" />
			</div>
		</div>
		</div>
	</body>
</html>
