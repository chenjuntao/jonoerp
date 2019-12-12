<!--
	Copyright (c) 2006
	National University of Defence Technology
	ChangSha, China
	
	All Rights Reserved
	
	Authors: Hu Jie
	
	$Id: admin_modify.jsp,v 1.13 2012/03/24 04:41:38 HuangJie Exp $
	
	描述：修改用户信息界面
-->
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="/tags/struts-bean" prefix="bean" %> --%>
<%@ page import="logic.module.setting.*"%>
<%@ page import="action.setting.*"%>
<%@ page import="java.util.*"%>

<html>
	<head>
	<link href="css/usermanager.css" rel="stylesheet" type="text/css">
		<title>系统配置 >> 安全管理 >> 修改用户信息</title>
<script type="text/javascript">
function check()
{
if(document.form1.realname.value=="")
  {
    alert("请输真实姓名");
    document.form1.realname.focus();
    return false;
  }else if(document.form1.department.value=="")
  {
    alert("请输入所属部门");
    document.form1.department.focus();
    return false;
  }else if(document.form1.phone.value=="")
  {
    alert("请输入联系电话");
    document.form1.phone.focus();
    return false;
  
  }else
  {
    return true;
  }
}

</script>

	    <style type="text/css">
<!--
.STYLE1 {color: #FF0000
          f}

.STYLE5 {color: #CC0000}
-->
        </style>
</head>
	<body bgcolor="white">
	<div>
   <table width="100%" cellspacing="0" cellpadding="1" border="0" class="fillcolor1">
	<tr>
	<td class="label">	<img src="../img/Xbutton39.gif" width="1" align="absmiddle" id="Image1"> 系统配置 >> 安全管理 >> 修改用户信息</td>
	 </tr>
  </table>
</div>

		<br>
		<br>
		<h3 align="center" class="label1">修改用户信息</h3>
	    <form action="useraccount.action" method="post"  name="form1">
		<table class="label6"  width="43%" border="1" align="center" cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
		<tr  class="label">
		<td  class="label" width="30%"  align="center" bgcolor="#006600" class="label">
		用 户 名</td>
		 
		<td   class="label6" width="70%"  align="center">
           <span class="STYLE5">${param.username}</span>
          <input name="username" type="hidden"  maxlength="26" value="${param.username}"></td>
		</tr>
		<tr>
			  <td  class="label" align="center" bgcolor="#006600" class="label"><b>真实姓名</b></td>
			  <td  class="label6"  align="center">
		    <input type="text" name="realname" value="${userInfo.realname}"></td>
          </tr>
		<tr>
			  <td class="label"   align="center" bgcolor="#006600" class="label"><b>所属部门</b></td>
			  <td  class="label6"  align="center"> 
			    <input type="text" name="department" value="${userInfo.department}">		      </td>
		 </tr>
		<tr>
			  <td  class="label"   align="center" bgcolor="#006600" class="label"><b>联系电话</b></td>
			  <td class="label6"   align="center">
			   <input type="text"  name="phone" value="${userInfo.phone}"></td>
		  </tr>
		  <tr> 
			  <td  class="label"   align="center" bgcolor="#006600" class="label"><b>新 密 码</b></td>
			  <td class="label6"   align="center">
			   <input type="password" name="passwd" maxlength="25" value="${userInfo.passwd}"></td>
		  </tr>
		  <tr> 
			  <td  class="label"   align="center" bgcolor="#006600" class="label"><b>确 认 新 密 码</b></td>
			  <td class="label6"   align="center">
			   <input type="password" name="reigthpasswd" maxlength="25" value="${userInfo.passwd}"></td>
		  </tr>
		  <input name="method" type="hidden" value="updateuser">
			<tr>
			  <td   colspan="2"  align="center" class="label">
			    
			      <input type="submit" onClick="return check()" value="确定"> <!--onClick="javascript:window.close(this);"-->              </td>
		  </tr>
	</table>		 
		<div align="center"><span  class="label2">${failure}</span>
		  </div>
    </form>	
	</body>
</html>
