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
  &nbsp;          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐厅要货单审核页面 　　　　
<p align="left">
    <label> </label>
    <label></label>
    <label for="textfield">单据编号</label>
    <input name="billNumber" type="text" id="billNumber" value="YH031304210319" size="18" />
  （无法修改）</p>
  <p align="left">订货部门
    <label>
    <input name="orderRestaurant" type="text" id="orderRestaurant" size="15" />
    </label>
  （无法修改）</p>
  <p>订货地址
    
    <input name="distributionAddress" type="text" id="distributionAddress" size="12" />
  （无法修改）</p>
    <p align="left">到货日期
    <label for="label2"></label>
  <input name="dateOfArrival" type="text" id="label2" value="2012-04-22 16:20" size="20" />
  （可以修改）
</p>
    <p>制单人员
    <label>
    <input name="orderPeople" type="text" id="orderPeople" size="12" />
    </label>
    制单日期
    <label for="label"></label>
    <input name="orderDate" type="text" id="label" value="2012-04-21 16:30" size="20" />
    <label for="Submit"></label>
    <label for="imageField"></label>
（无法修改）</p>
    <p>备注
    <input name="remarks" type="text" id="remarks" />
  （可以修改）</p>
    <p>参考日期时间段1
      <label for="label5"></label>
      <input name="referenceStartTime1" type="text" id="referenceStartTime1" value="2012-04-11" size="15" />
      <label for="label6"></label>
-
<input name="referenceEndTime1" type="text" id="referenceEndTime1" value="2012-04-20" size="15" />
<label for="label7"></label>
（无法修改）</p>
    <p>参考日期时间段2
      <label for="label8"></label>
        <input name="referenceStartTime2" type="text" id="referenceStartTime2" value="2012-03-01" size="15" />
        <label for="label9"></label>
      -
  <input name="referenceEndTime2" type="text" id="referenceEndTime2" value="2012-04-21" size="15" />
  <label for="label10"></label>
    （无法修改）</p>
    <p>参考日期时间段3
      <label for="label11"></label>
        <input name="referenceStartTime3" type="text" id="referenceStartTime3" value="2012-04-21" size="15" />
        <label for="label12"></label>
      -
  <input name="referenceEndTime3" type="text" id="referenceEndTime3" value="2012-04-21" size="15" />
  <label for="label13"></label>
    （无法修改）</p>
    <p>延滞期金额
      <input name="delayPredict" type="text" id="delayPredict" value="300" size="10" />
      （万元）
      进货周期金额
  <input name="purchasePredict" type="text" id="purchasePredict" value="100" size="10" />
      （万元）
      安全存量
  <input name="safetyStock" type="text" id="safetyStock" value="100" size="10" />
      （万元）
      预估销售额
  <input name="sellPredict" type="text" id="sellPredict" value="500" size="10" />
    （只有预估销售额可以修改）</p>
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
    <td style="text-align:center;">订货量</td>
    <td style="text-align:center;">单价</td>
    <td style="text-align:center;">金额</td>
  </tr>
  <tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';"  align="center">
    <td>无法修改</td>
    <td>无法修改</td>
    <td>无法修改</td>
    <td>无法修改</td>
    <td>无法修改</td>
    <td>无法修改</td>
    <td>无法修改</td>
    <td>可以修改</td>
    <td>无法修改</td>
    <td><p>无法修改自动计算</p>
    </td>
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
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>

</table>
    <p>
审核日期输入
  <label for="label14"></label>
<input name="textfield23" type="text" id="textfield2" value="2012-04-21 21:30" size="20" />
<label for="label15"></label>
<input type="submit" name="Submit34" value="日历控件" id="label15" />
<label for="imageField"></label>
</p>
<p>
  <label>
  <input name="commitGoodsBill" type="submit" id="commitGoodsBill" value="要货单审核确认" />  
  </label>
  <label>
  <input name="backToModify" type="reset" id="backToModify" value="返回" />
  </label>
</p>
<p></p>
</body>

</html>
