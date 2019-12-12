<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/jsp/common/jsp/path.jsp"%>
</head>

<body class="claro">
	<form id="queryForm">
		<table class="hovertable" width="100%" border="1">
			<tr>
				<td colspan="4">
					<div style="padding-left: 8px;">
						<b>物流中心装箱单管理</b>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<p> 根据物流中心配送单、货品体积和箱体积，辅助计算装箱方式，辅助生成装箱单。</p>
	<p class="area_blank">&nbsp;</p>
	<form id="form1" name="form1" method="post" action="">
  <p align="left">　　　　　　　　　　　　　　　　　物流中心装箱单 　　　
  （注：制单人确定后状态为未审核，点击即为审核状态，不能再修改，只有后台可以进行修改）　　　　　　
      <label> 　　　 </label>
  </p>
  <p align="left">
    <input type="submit" name="Submit4222" value="捡货单" />
（注：点击“捡货单”按钮即可链接所以已经审核过的物流中心捡货单，根据不同门店将单据添加进来，已添加的门店状态改变，以免重复添加，一个门店一个装箱单。） </p>
  <p align="left">
    <label for="textfield">单据编号</label>
    <input name="textfield" type="text" id="textfield" value="YH031304210319" size="18" />
    （注：保存时自动生成流水号，组成=英文简称+部门序号+日期+流水号）</p>
  <p>
    <label></label>
    装箱人员
    <select name="select4" onchange="MM_jumpMenu('parent',this,0)">
      <option value="0101张三">0101张三</option>
      <option value="0102李四">0102李四</option>
      <option value="0101王五">0103王五</option>
    </select>
  （根据登陆的用户自动产生） </p>
  <p>装箱日期
    <label for="label"></label>
      <input name="textfield2" type="text" id="label" value="2012-04-21" size="15" />
      <label for="Submit"></label>
      <input type="submit" name="Submit3" value="日历控件" id="Submit" />
      <label for="imageField"></label>
    （注：制定日期即捡货日期为默认当天，不能改，可修改权限在后台）</p>
  <p>
    <label for="label2">箱子编号</label>
    <input name="textfield3" type="text" id="label2" value="ZX0001" size="18" />
    (根据装箱单流水顺序来排序，自动生成装箱单流水编号)
  </p>
  <p>订货部门
    <input name="textfield323" type="text" id="textfield322" value="002侯家塘店" size="15" />
(默认原始捡货单门店，且不能修改)</p>
  <p>审核人员
    <select name="select11" onchange="MM_jumpMenu('parent',this,0)">
        <option value="0101张三">0101张三</option>
        <option value="0102李四">0102李四</option>
        <option value="0101王五">0103王五</option>
    </select>
    （注：点击审核后，根据系统登陆用户默认生成审核人员。）</p>
  <p>审核日期
    <label for="label5"></label>
      <input name="textfield22" type="text" id="label5" value="2012-04-21" size="15" />
      <label for="label6"></label>
      <input type="submit" name="Submit33" value="日历控件" id="label6" />
      <label for="imageField"></label>
  （注：制定日期为默认的，以日结为准，可以修改。）</p>
  <p>
    <label></label>　　　　　　　　　　　　　　   
    <input type="submit" name="Submit" value="确定" />　　　　
    
    <input name="Submit2" type="submit" value="取消" />
  </p>
</form>
  <p>&nbsp;</p>
  <table width="738" border="1">
    <tr>
      <th width="80" scope="col">序号</th>
      <th width="80" scope="col">原料编码</th>
      <th width="80" scope="col">原料名称</th>
      <th width="70" scope="col">库存单位</th>
      <th width="70" scope="col">包装单位</th>
      <th width="70" scope="col">单位体积</th>
      <th width="70" scope="col">数量</th>
      <th width="70" scope="col">体积</th>
      <th width="90" scope="col">类别</th>
    </tr>
    <tr>
      <th scope="row">1</th>
      <td>&nbsp;</td>
      <td>牛肉</td>
      <td>斤</td>
      <td>包</td>
      <td>9</td>
      <td>10</td>
      <td>90</td>
      <td>肉类</td>
    </tr>
    <tr>
      <th scope="row">2</th>
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
      <th scope="row">3</th>
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
      <th scope="row">......</th>
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
      <th scope="row">本页小计</th>
      <th scope="row">&nbsp;</th>
      <th scope="row">&nbsp;</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th scope="row">本单合计</th>
      <th scope="row">&nbsp;</th>
      <th scope="row">&nbsp;</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</body>

</html>
