<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>menu</title>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
	<link href="<%=appRoot %>/jsp/common/leftmenu/rico.css" rel="stylesheet">
	<script src="<%=appRoot %>/jsp/common/leftmenu/expandmenu.js" type="text/javascript" ></script>
</head>

<body>
	<div id="divOpenNav" class="nav">
		<table id="tabToc" width="100%" class="navTocColor" style="margin-bottom:1px;" cellspacing="0" cellpadding="0" border="0" height="20">
			<tr>
				<td width="2">
					<a href="javascript:ToggleNav();" title="折叠导航框架" id="linkNavOpen">
						<img src="<%=appRoot %>/jsp/common/img/toc.gif" alt="折叠导航框架" width="22" height="17" border="0">
					</a>
				</td>
				<td align="left">
					<p class="p1">
						<a href="javascript:ToggleNav();" title="折叠导航框架" class="a1">
							<span class="STYLE1">隐藏</span>
						</a>
					</p>
				</td>
				<td align="right">
					<p class="p1">
						<a href="javascript:widgets.ToggleAll()" id="textExp" class="a1">
							<span class="STYLE1">全部折叠</span>
						</a>
					</p>
				</td>
				<td width="2">
					<div style="padding:2pt,1.5pt,0pt,0pt">
						<a href="javascript:widgets.ToggleAll()" title="全部折叠" id="expparent00">
							<img id="exp00" alt="全部折叠" src="<%=appRoot %>/jsp/common/img/minimize.gif" width="15" height="15" border="0">
						</a>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<div id="divCollapsedNav" class="navTocColor" style="display:none;width:100%;height:100%;">
		<a href="javascript:ToggleNav();" title="展开导航框架" id="linkNavClosed">
		<img src="<%=appRoot %>/jsp/common/img/toc2.gif" alt="展开导航框架" border="0"></a>
	</div>