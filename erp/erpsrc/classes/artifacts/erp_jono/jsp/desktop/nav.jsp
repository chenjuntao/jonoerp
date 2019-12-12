<%@ page contentType="text/html;charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>连锁餐饮ERP系统</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<script type='text/javascript' src='<%=dojoBase %>/dojo/dojo.js' data-dojo-config="async:1"></script>
	<script type="text/javascript" src="<%=appRoot %>/jsp/common/js/common.js?Version=<%=currenttime %>"></script>
	<script type='text/javascript' src='<%=appRoot %>/jsp/desktop/nav.js?Version=<%=currenttime %>'></script>
	<script type='text/javascript'>
	</script>
	
	<link href="<%=appRoot %>/jsp/common/leftmenu/rico.css" rel="stylesheet">
    <style type="text/css">
		@import "<%=dojoBase %>/dojo/resources/dojo.css";
	    @import "<%=dojoBase %>/dijit/themes/claro/claro.css";
      
      	#treeWrapper {
	      	position: absolute;
			top: 42px;
			bottom: 0px;
			width: 100%;
			height: auto;
      	}
      
		#menuTree {
			width: 100%;
			height: 100%;
		}
    </style>
</head>

<body class="claro" style="overflow: hidden; margin: 0px;">
	<div id="divOpenNav" class="nav">
		<table id="tabToc" width="100%" class="navTocColor" style="margin-bottom:1px;" cellspacing="0" cellpadding="0" border="0" height="20">
			<tr>
				<td width="2">
					<a href="javascript:toggleNav();" title="折叠导航框架" id="linkNavOpen">
						<img src="<%=appRoot %>/jsp/common/img/toc.gif" alt="折叠导航框架" width="22" height="17" border="0">
					</a>
				</td>
				<td align="left">
					<p class="p1">
						<a href="javascript:toggleNav();" title="折叠导航框架" class="a1">
							<span class="STYLE1">隐藏</span>
						</a>
					</p>
				</td>
				<td align="right">
					<p class="p1">
						<a href="javascript:widgets.toggleAll();" id="textExp" class="a1">
							<span class="STYLE1">全部折叠</span>
						</a>
					</p>
				</td>
				<td width="2">
					<div style="padding:2pt,1.5pt,0pt,0pt">
						<a href="javascript:widgets.toggleAll();" title="全部折叠" id="expparent00">
							<img id="exp00" alt="全部折叠" src="<%=appRoot %>/jsp/common/img/minimize.gif" width="15" height="15" border="0">
						</a>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<div id="divCollapsedNav" class="navTocColor" style="display:none;width:100%;height:100%;">
		<a href="javascript:toggleNav();" title="展开导航框架" id="linkNavClosed">
		<img src="<%=appRoot %>/jsp/common/img/toc2.gif" alt="展开导航框架" border="0"></a>
	</div>

	<div id="treeWrapper">
	</div>
</body>
</html>
