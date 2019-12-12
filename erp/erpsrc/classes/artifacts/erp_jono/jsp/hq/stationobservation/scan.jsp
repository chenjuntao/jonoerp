<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.net.InetAddress" %>
<!-- 该 DTD 包含所有 HTML 元素和属性，包括展示性的和弃用的元素（比如 font）,不允许框架集（Framesets） -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 如果需要使用符合 XML 规范的 XHTML 文档，则应该在文档中的<html> 标签中至少使用一个 xmlns 属性，以指定整个文档所使用的主要命名空间：<html xmlns="http://www.w3.org/1999/xhtml"> -->
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>main page</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	
	<script type="text/javascript" src='<%=dojoBase %>/dojo/dojo.js'></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/dgrid-util.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=dojoBase %>/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/hq/stationobservation/scan.js?Version=<%=currenttime %>"></script>
	
    <style type="text/css">
      @import "<%=dojoBase %>/dojo/resources/dojo.css";
      @import "<%=dojoBase %>/dgrid/css/dgrid.css";
      @import "<%=dojoBase %>/dgrid/css/skins/claro.css";
      @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
    </style>
    
    <link rel="stylesheet" href="<%=appRoot %>/jsp/hq/stationobservation/edit.css">
	<link rel="stylesheet" href="<%=appRoot %>/jsp/common/css/common.css"/>
	
	<script type="text/javascript">
		var item_id = ${itemMeta.itemId};
	</script>
</head>

<body class="claro" style="overflow-y: auto;overflow-x: hidden;">

	<form id="billForm" method="post" >
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td>
					 <div id="leftTitle" name="leftTitle" data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/Editor',editing:true, renderAsHtml:true, autoSave:true,
   							 editorParams:{ extraPlugins: []}">${leftTitle} </div>
				</td>
				<td colspan="3">
						<div style="text-align: center;">
							<div style="text-align: center;">
								<div>
									<span style="font-size: 1.5em;">
										<b>编号：${itemMeta.itemId} &nbsp 名称：${itemMeta.itemName} &nbsp 操作标准 <br /> </b>
									</span>
								</div>
								
							<div><span style="font-size: 1.2em;"> 岗位观察核对表<br /></span></div></div>
						</div>
				</td>
				<td >
					<input type="button" onclick="exportXLS();" value="导出">
					</td>
			</tr>
			
			<tr>
				<td style="width: 100px">
					<div style="text-align: center;" >
						<span style="font-size: 1.2em;">
							观察得分
						</span>
					</div> 
				</td>
				
				<td colspan="4">
					<div style="text-align: center;" >
						<span style="font-size: 2em;">
							工作步骤
						</span>
					</div> 
				</td>
			</tr>
			
<!-- 			第一部分：准备工作 -->
			<tr>
				<td rowspan="12"></td>
				<td colspan="4">
					 <div  id="aPartTitle" data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/form/Textarea',editing:true,autoSave:true" >${aPartTitle }</div>
				</td>
			</tr>
			
			<tr>
				<td rowspan="2" colspan="1">
					 <div style="height: 300px" id="aPartContent" data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/Editor', editing:true,renderAsHtml:true, autoSave:true,
   							 editorParams:{ extraPlugins: []}"></div>
				</td>
				
				<td >
					<div id="dataGrid" style="height: 300px;width:350px"></div>
				</td>
				
				<td>
					<img id="itemPic" style="height: 300px;width:380px"  src="<%=appRoot %>/common/loadImage.action?objectId=${itemMeta.itemId}"  alt="[${itemMeta.itemId}]${itemMeta.itemName}" />
				</td>
				<td></td>
			</tr>
			
			<tr>
				<td colspan="3">
					 <div  id="costPartContent"  data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/form/Textarea',editing:true,autoSave:true">${costPartContent }</div>
				</td>
			</tr>
			
			
<!-- 			第二部分：示范说明           （按照1份的量配备制作） -->
			<tr>
				<td colspan="4">
					 <div  id="bPartTitle" data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/form/Textarea',editing:true,autoSave:true">${bPartTitle }</div>
				</td>
			</tr>
			
			<tr>
				<td rowspan="1">
					<div style="text-align: center;" >
						<span style="font-size: 1.2em;">
							初加工
						</span>
					</div> 
				</td>
				
				<td colspan="3">
					<div style="height: 350px;width:800px ;overflow-x:scroll;">
						<table id="aTable" >
						${aTable.stepRemark }
						</table>
					</div> 
				</td>
			</tr>
			<tr>
				<td rowspan="1">
					<div style="text-align: center;" >
						<span style="font-size: 1.2em;">
							深加工
						</span>
					</div> 
				</td>
				
				<td colspan="3">
					<div style="height: 350px;width:800px ;overflow-x:scroll;">
						<table id="bTable" >
						${bTable.stepRemark }
						</table>
					</div> 
				</td>
			</tr>
			<tr>
				<td rowspan="1">
					<div style="text-align: center;" >
						<span style="font-size: 1.2em;">
							见单制作
						</span>
					</div> 
				</td>
				
				<td colspan="3">
					<div style="text-align: center;" >
						<div style="height: 350px;width:800px ;overflow-x:scroll;">
							<table id="cTable" >
							${cTable.stepRemark }
							</table>
					</div> 
					</div> 
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
					 <div  id="bPartContent"  data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/Editor', editing:true,renderAsHtml:true, autoSave:true,
   							 editorParams:{ extraPlugins: []}">${bPartContent }</div>
				</td>
			</tr>
			
			
<!-- 			第三部分：练习 -->
			<tr>
				<td colspan="4">
					  <div  id="cPartTitle" data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/form/Textarea',editing:true,autoSave:true">${cPartTitle }</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
					 <div id="cPartContent" data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/Editor', renderAsHtml:true,editing:true, autoSave:true,
   							 editorParams:{ extraPlugins: []}">${cPartContent }</div>
				</td>
			</tr>
			
<!-- 			第四部分：追踪考核 -->
			<tr>
				<td colspan="4">
					  <div  id="dPartTitle" data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/form/Textarea',editing:true,autoSave:true">${dPartTitle }</div>
				</td>
			</tr>
			
			<tr>
				<td colspan="4">
					 <div  id="dPartContent"  data-dojo-type="dijit/InlineEditBox" data-dojo-props="editor:'dijit/Editor', renderAsHtml:true,editing:true, autoSave:true,
   							 editorParams:{ extraPlugins: []}">${dPartContent }</div>
				</td>
			</tr>
			
		</table>
</body>

</html>
