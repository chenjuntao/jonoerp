<!--
	Copyright (c) 2006
	National University of Defence Technology
	ChangSha, China
	
	All Rights Reserved
	
	Authors: Hu Jie
	
	$Id: login.jsp,v 1.7 2007/01/26 02:27:14 XuJingYu Exp $
	
	描述：监控子系统用户登录界面
-->
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*" %>
<%@ page session="true" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/usermanager.css" rel="stylesheet" type="text/css">

<title>监控子系统用户登录</title>
<style type="text/css">
<!--
.STYLE6 {font-size: 12pt; color: #000033; }
-->
</style>
</head>
<script type="text/javascript">
<!--
window.onload=function()
{
	if(window.name != '登录')
	{
		window.open(location, '登录', 'fullScreen=yes, scrollbars=no');
	}
}
-->
</script>
<body>
<center>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
 <br>
  <table width="685" height="366" border="0" cellpadding="0" cellspacing="0" background="img/login2.5.gif">
  <tr>
    <td height="187" colspan="2">&nbsp;</td>
    </tr>
  <form name="form1" method="get" action="useraccount.action">
    <br> 
	         
        <tr><td >  <table width="300" border="0" align="center" cellpadding="4" cellspacing="1"  class="STYLE6">
        
            <tr> 
              <td  align="right">用户名：</td>
              <td> 
              <input name="username" type="text" size="15" maxlength="25"/></td>
            </tr>
            <tr> 
              <td align="right">密 &nbsp;码：</td>
              <td> 
                <input name="passwd" type="password" size="15" maxlength="25"/> </td>
            </tr>
            <tr> 
              <td colspan="2" align="center"> 
              <input type="image" src="img/login001.jpg"/> </td>
            </tr>
            <tr>     
           	<td colspan="2" align="center">     
           	<input type="hidden" name="method" value="login"/>           	        	</td>
   	    </tr>
         </table>   
         </td></tr>
         
</form>
</table><p class="label2">${result}</p>
</center>
</body>
</html>
