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
						<b>物流中心捡货单管理</b>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<p>根据实际发货情况，辅助生成捡货单，并可以进行打印，方便进行仓库的捡货操作。</p>
	<p class="area_blank">&nbsp;</p>
	<form id="form1" name="form1" method="post" action="">
  <p align="left">　　　　　　　　　　　　　　　　　物流中心捡货单 　　
    <input type="submit" name="Submit423" value="未审核" />
　
  （注：制单人确定后状态为未审核，点击即为审核状态，不能再修改，只有后台可以进行修改）　　　　　
      <label> 　　　 </label>
  </p>
  <p align="left">
    <label for="textfield">单据编号</label>
    <input name="textfield" type="text" id="textfield" value="YH031304210319" size="18" />
（注：保存时自动生成流水号，组成=英文简称+部门序号+日期+流水号）</p>
  <p>
    <label></label>
    捡货仓库
    <input name="textfield322" type="text" id="textfield32" value="001物流中心" size="15" />
(注：默认为系统登陆用户所属部门，且不能修改)&nbsp;</p>
  <p>捡货人员
    <select name="select4" onchange="MM_jumpMenu('parent',this,0)">
      <option value="0101张三">0101张三</option>
      <option value="0102李四">0102李四</option>
      <option value="0101王五">0103王五</option>
    </select>
（根据登陆的用户自动产生） </p>
  <p>捡货日期
    <label for="label"></label>
      <input name="textfield2" type="text" id="label" value="2012-04-21" size="15" />
      <label for="Submit"></label>
      <input type="submit" name="Submit3" value="日历控件" id="Submit" />
      <label for="imageField"></label>
    （注：制定日期即捡货日期为默认当天，不能改，可修改权限在后台）</p>
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
  <p>原料编码
    <select name="select" onchange="MM_jumpMenu('parent',this,0)">
      <option value="A001">A001</option>
      <option value="B001">B001</option>
    </select>
原料名称

（注：根据配送单导入相应的）</p>
  <p>注：店名需要客户的实际门店名称，此处先一律以店名表示，所有配送单中有的原料，单位，数量全部显示出来,打印捡货，数量相同就勾上，不同就在旁边修改，捡货后，交一份纸质单到仓库，修改实际数据，再审核，相应配送单的配送数也自动修改了。</p>
  <p>&nbsp;</p>
  <p>　　　　　　　　　　　　　　
    <input type="submit" name="Submit" value="确定" />
    　　　　
    <input name="Submit2" type="submit" value="取消" />
  </p>
</form>
  <p>注：单据表格可以导入导出，原料名称的排序可以根据实际捡货顺序的方便来排列，或方便将已修改实际数量的excel表格导入，考虑一下排版的问题。</p>
  <table width="950" border="1">
    <tr>
      <th width="30" scope="col">序号</th>
      <th width="60" scope="col">原料编码</th>
      <th width="60" scope="col">原料名称</th>
      <th width="60" scope="col">单位</th>
      <th width="60" scope="col">规格</th>
      <th width="60" scope="col">店名</th>
      <th width="60" scope="col">来源单据</th>
      <th width="60" scope="col">源单数量</th>
      <th width="60" scope="col">货位编码</th>
      <th width="60" scope="col">货位名称</th>
      <th width="60" scope="col">捡货数量</th>
      <th width="100" scope="col">......</th>
    </tr>

    <tr>
      <th scope="row">1</th>
      <td>鸡蛋</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>海东青店</td>
      <td>&nbsp;</td>
      <td>20</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>东塘店</td>
      <td>&nbsp;</td>
      <td>30</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>老母鸡</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>八一店</td>
      <td>&nbsp;</td>
      <td>5</td>
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
      <td>奥克斯店</td>
      <td>&nbsp;</td>
      <td>8</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <th scope="row">本页小计</th>
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
      <th scope="row">本单合计</th>
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
  <p>&nbsp;</p>
</body>

</html>
