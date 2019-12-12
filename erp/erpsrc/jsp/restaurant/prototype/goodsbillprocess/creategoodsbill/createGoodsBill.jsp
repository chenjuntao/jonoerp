<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>main page</title>
</head>
<%
	String appRoot = request.getContextPath();
%>

<body bgcolor="white" class="claro">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="2">
					<div style="padding-left: 8px;">
						<b>要货选择方式</b>
					</div>
				</td>
			</tr>
			<tr>
				<td width="120"class="label_right">门店选择：</td>
				<td class="text_left">
				</td>
			</tr>

		</table>
  <p align="left">
    <input type="submit" name="useHistoryModel" value="使用模板" />
    （注：链接弹出页面，显示历史保存的模板，一般按类别使用模板订货，按物料类别要货，方便生成采购单，点击选择某个模板后，下面的要货表将列出模板中列出的原料）</p>
<p align="left">
    <input type="submit" name="saveModel" value="存为模板" />
  （注：点击可以保存为模板,弹出对话框，输入名称进行保存。）    </p>
  <p align="left">
    <input type="submit" name="DIYwithnoModel" value="自选原料" />
    （注：点击，链接弹出原料选择窗口，可以根据原料、半成品类型种类批量添加)</p>

  <table width="100%" border="1" class="hovertable">
    <tr>
      <td colspan="4"><div style="padding-left: 8px;"> <b>万元用量辅助要货</b> </div></td>
    </tr>
  </table>
      <form id="form1" name="form1" method="post" action="">

  <p>参考日期时间段1
    <label for="label5"></label>
      <input name="referenceStartTime1" type="text" id="referenceStartTime1" value="2012-04-11" size="15" />
      <label for="label6"></label>
      <input type="submit" name="Submit33" value="日历控件" id="label6" />
    -
  <input name="referenceEndTime1" type="text" id="referenceEndTime1" value="2012-04-20" size="15" />
  <label for="label7"></label>
  <input type="submit" name="Submit332" value="日历控件" id="label7" />
  </p>
  <p>参考日期时间段2
    <label for="label8"></label>
      <input name="referenceStartTime2" type="text" id="referenceStartTime2" value="2012-03-01" size="15" />
      <label for="label9"></label>
      <input type="submit" name="Submit333" value="日历控件" id="label9" />
    -
  <input name="referenceEndTime2" type="text" id="referenceEndTime2" value="2012-04-21" size="15" />
  <label for="label10"></label>
  <input type="submit" name="Submit3322" value="日历控件" id="label10" />
  </p>
  <p>参考日期时间段3
    <label for="label11"></label>
      <input name="referenceStartTime3" type="text" id="referenceStartTime3" value="2012-04-21" size="15" />
      <label for="label12"></label>
      <input type="submit" name="Submit334" value="日历控件" id="label12" />
    -
  <input name="referenceEndTime3" type="text" id="referenceEndTime3" value="2012-04-21" size="15" />
  <label for="label13"></label>
  <input type="submit" name="Submit3323" value="日历控件" id="label13" />
    （注：日期可以选择，可以为一组、或两组、三组，也可不选，选了才显示该列）</p>
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
    （前面三者之和，自动计算不需要填）
  <input type="submit" name="Submit3324" value="计算预估销售额" id="Submit332" />
  （注：上面选择了参考日期，从参考日期计算了某种原料的万元用量平均值后，预估存量，从而万元用量平均值*预估销售额-库存量=建议值） </p>
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
<table width="100%" border="1" class="hovertable">
    <tr>
      <td colspan="4"><div style="padding-left: 8px;"> <b>其他要货信息输入</b> </div></td>
    </tr>
  </table>

&nbsp;到货日期
    <label for="label2"></label>
    <input name="dateOfArrival" type="text" id="dateOfArrival" value="2012-04-22 16:20" size="20" />
    <input type="submit" name="Submit32" value="日历控件" id="Submit3" />
    （注：日期可以选择，必须在制单日之后）
    <p>备注
      <input type="text" name="remarks" />
  </p>

  <p> 　　
 		<label>	<a href='javascript: parent.addTab("外部入库管理-采购单+编号", "<%=appRoot %>/jsp/restaurant/prototype/goodsbillprocess/creategoodsbill/commitGoodsBill.jsp");'
					>确认提交要货单</a>
</label>
   <input name="resetData" type="reset" id="resetData" value="清除数据" />
  </p>
</form>
</body>

</html>
