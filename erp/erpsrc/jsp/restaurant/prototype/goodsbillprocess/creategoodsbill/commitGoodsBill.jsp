<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
</head>

<body bgcolor="white" class="claro">
  &nbsp;          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐厅要货单提交页面 　　　　
<p align="left">
    <label> </label>
    <label></label>
    <label for="textfield">单据编号</label>
    <input name="billNumber" type="text" id="billNumber" value="YH031304210319" size="18" />
  （自动生成，拼音缩写+部门编号+日期+流水号，进入此页面时自动生成，无法修改）</p>
  <p align="left">订货部门
    <label>
    <input name="orderRestaurant" type="text" id="orderRestaurant" size="15" />
    </label>
  （根据登录用户所属的餐厅，自动生成）订货地址
    
    <input name="distributionAddress" type="text" id="distributionAddress" size="12" />
（根据登录用户所属的餐厅，自动生成）</p>
  <p>到货日期
    <label for="label2"></label>
    <input name="dateOfArrival" type="text" id="label2" value="2012-04-22 16:20" size="20" />
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
（自动生成）</p>
  <p>备注
    <input name="remarks" type="text" id="remarks" />
  </p>
  <p>参考日期时间段1
    <label for="label5"></label>
    <input name="referenceStartTime1" type="text" id="referenceStartTime1" value="2012-04-11" size="15" />
    <label for="label6"></label>
    
-
<input name="referenceEndTime1" type="text" id="referenceEndTime1" value="2012-04-20" size="15" />
<label for="label7"></label>
</p>
  <p>参考日期时间段2
    <label for="label8"></label>
      <input name="referenceStartTime2" type="text" id="referenceStartTime2" value="2012-03-01" size="15" />
      <label for="label9"></label>
      
    -
  <input name="referenceEndTime2" type="text" id="referenceEndTime2" value="2012-04-21" size="15" />
  <label for="label10"></label>
</p>
  <p>参考日期时间段3
    <label for="label11"></label>
      <input name="referenceStartTime3" type="text" id="referenceStartTime3" value="2012-04-21" size="15" />
      <label for="label12"></label>
      
    -
  <input name="referenceEndTime3" type="text" id="referenceEndTime3" value="2012-04-21" size="15" />
  <label for="label13"></label>
  </p>
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
  </p>
	  <table width="1052" border="1">
  <tr>
    <td width="43">序号</td>
    <td width="60">原料编码</td>
    <td width="60">国际条码</td>
    <td width="60">原料名称</td>
    <td width="35">类别</td>
    <td width="43">单位</td>
    <td width="43">规格</td>
    <td width="43" bgcolor="#999999">万用1</td>
    <td width="43" bgcolor="#999999">万用2</td>
    <td width="43" bgcolor="#999999">万用3</td>
    <td width="43" bgcolor="#999999">需求1</td>
    <td width="43" bgcolor="#999999">需求2</td>
    <td width="43" bgcolor="#999999">需求3</td>
    <td width="50">库存量</td>
    <td width="43" bgcolor="#999999">建议1</td>
    <td width="43" bgcolor="#999999">建议2</td>
    <td width="43" bgcolor="#999999">建议3</td>
    <td width="50">订货量</td>
    <td width="35">单价</td>
    <td width="62">金额</td>
  </tr>
  <tr>
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
  <tr>
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
  <tr>
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
  <tr>
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
  <tr>
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
  <tr>
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
  <tr>
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
  <tr>
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
  <tr>
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
  <label>
  <input name="commitGoodsBill" type="submit" id="commitGoodsBill" value="确定提交要货单" />  
  </label>
  <label>
  <input name="backToModify" type="reset" id="backToModify" value="返回进行修改" />
  </label>
</p>
<p></p>
</body>

</html>
