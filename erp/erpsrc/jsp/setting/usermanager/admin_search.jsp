<!--
	Copyright (c) 2006
	National University of Defence Technology
	ChangSha, China
	
	All Rights Reserved
	
	Authors: Hu Jie
	
	$Id: admin_search.jsp,v 1.28 2012/02/17 17:06:54 HuangJie Exp $
	
	描述：用户查询界面
-->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib uri="/tags/struts-bean" prefix="bean" %> --%>
<%@ page import="logic.module.setting.*"%>
<%@ page import="action.setting.*"%>
<%@ page import="java.util.*"%>

<link href="css/usermanager.css" rel="stylesheet" type="text/css">
<html>
	<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
    </script>
<head> 
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>系统配置 >> 安全管理 >> 用户查询</title>
		<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
	</head>
	<body bgcolor="white">
	<div>
   <table width="100%" cellspacing="0" cellpadding="1" border="0" class="fillcolor1">
	<tr>
	<td class="label">	<img src="../img/Xbutton39.gif" width="1" align="absmiddle" id="Image1"> 系统配置 >> 安全管理 >> 用户查询</td>
	 </tr>
  </table>
</div>
		<br>
		<h3 align="center" class="label1">用户列表</h3>
	
		<table  class="label6" width="95%" border="1" align="center" cellpadding="5" cellspacing="1" bordercolor="#BBEAC2" >
          <tr class="label" align="center" bgcolor="#006600">
            <th  class="label">用户名</th>
            <th class="label">真实姓名</th>
            <th  class="label">所属部门</th>
            <th class="label">联系电话</th>
            <th class="label">用户角色</th>
 
            <th class="label">用户操作</th>
          </tr>
          <c:forEach var="userAccountBean" items="${userInfos}">
            <tr class="label6" align="center">
              <td class="label6">&nbsp;${userAccountBean.username} </td>
              <td class="label6">&nbsp;${userAccountBean.realname} </td>
              <td class="label6">&nbsp;${userAccountBean.department} </td>
              <td class="label6">&nbsp;${userAccountBean.phone} </td>
              <td class="label6">&nbsp;
              <c:forEach var="roleInfo" items="${userAccountBean.roleInfos}">      
              	${roleInfo.desc}&nbsp;<br/>      
              </c:forEach>
              
              </td>
              
              <!--<td>&nbsp;${roleInfo.desc}<a href="del_role.jsp?username=${userAccountBean.username}">删除</a> </td>-->
              <!--<td class="label6"><a href="add_roles.jsp?username=${userAccountBean.username} ">添加</a> </td>-->
              <td class="label6"><a href="useraccount.action?method=listuser&username=${userAccountBean.username}">修改</a> <a href="del_user.jsp?username=${userAccountBean.username}">删除</a></td>
            </tr>
          </c:forEach>
          <input type="hidden" name="method" value="listuser">
    </table>
	<br><br>
    <div align="center"><span class="label2">${success}
    ${failure}</span>
    </div>
    
	</body>
</html>
