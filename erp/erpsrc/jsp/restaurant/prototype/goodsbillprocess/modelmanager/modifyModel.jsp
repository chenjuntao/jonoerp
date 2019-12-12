<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>

<head>
	<title>main page</title>
</head>

<body bgcolor="white" class="claro">
		<table class="hovertable" width="100%" border="1" cellpadding="6" cellspacing="2"
			bordercolor="#FEB5AE" bgcolor="#E9EFF5" align="center">
			
				<tr valign="middle">
				<td width="800">
				<div align="left"><b>要货单模板管理</b></div></td>
			</tr>
	
		</table>

<table class="hovertable" width="100%" border="1" align="center"
		cellpadding="4" cellspacing="1" bordercolor="#BBEAC2">
          <tr  align="center" bgcolor="#006600">
            <th style="text-align:center;">模板编号</th>
            <th style="text-align:center;">模板名称</th>
            <th style="text-align:center;">模板所属部门</th>
            <th style="text-align:center;">模板要货类别</th>
          </tr>
          <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"  align="center">
            <td>不可修改</td>
            <td>可修改</td>
            <td>不可修改</td>
            <td>不可修改，根据包含原料自动变化</td>
          </tr>
</table>

  <table class="hovertable" width="100%" border="1" align="center"
		cellpadding="5" cellspacing="1" bordercolor="#BBEAC2">
  <tr align="center" bgcolor="#006600">
    <td style="text-align:center;">序号</td>
    <td style="text-align:center;">原料编码</td>
    <td style="text-align:center;">国际条码</td>
    <td style="text-align:center;">原料名称</td>
    <td style="text-align:center;">类别</td>
    <td style="text-align:center;">单位</td>
    <td style="text-align:center;">规格</td>
    <td style="text-align:center;">单价</td>
  </tr>
  <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
 </tr>
  <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
 </tr>
  <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<p>
  <label>
  <input name="modifyResource" type="submit" id="modifyResource" value="修改原料" />
  （弹出窗口，树形控件修改所选的原料/半成品）
  <input name="deleteModel" type="submit" id="deleteModel" value="删除模板" />
  （弹出窗口进行确认）
  <input name="copyModel" type="submit" id="copyModel" value="复制模板" />
  （弹出窗口，输入另存为模板名）  </label>
  <label>
  <input name="reset" type="reset" id="reset" value="取消修改" />
  </label>
（点击所有修改取消）</p>
<p></p>
</body>

</html>
