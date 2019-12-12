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
						<b>物流中心采购退货单创建</b>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<p>物流中心仓库管理员生成采购退货单。 </p>
	<p class="area_blank">&nbsp;</p>
	<form id="form1" name="form1" method="post" action="">
  <p align="left">　　　　　　　　　　　　　　　　　物流中心采购退货单 　　　　
    <input type="submit" name="Submit423" value="未审核" />
　
  （注：制单人确定后状态为未审核，点击即为审核状态，不能再修改）　　　　
      <label> 　　　 </label>
  </p>
  <p align="left">原始单据编号
    <input name="textfield4" type="text" id="textfield2" value="YH031304210319" size="18" />
（注：加个可选择控件，可以模糊查询单据编号，链接到原始采购单） </p>
  <p align="left">单据编号
    <input name="textfield" type="text" id="textfield" value="YH031304210319" size="18" />
    （注：保存时自动生成流水号） </p>
    <label></label>供应商
    <select name="select" onchange="MM_jumpMenu('parent',this,0)">
      <option value="001步步高">001步高</option>
    </select>
  (注：可以模糊选择)</p>
  <p>退货仓库
    <select name="menu1" onchange="MM_jumpMenu('parent',this,0)">
      <option value="001物流中心">001物流中心</option>
    </select>
  (注：默认自己部门，且不能修改)  </p>
  <p>采购人员
    <select name="select" id="menu1" onchange="MM_jumpMenu('parent',this,0)">
      <option value="0101张三" selected="selected">0101张三</option>
      <option value="0102李四">0102李四</option>
      <option value="0103王五">0103王五</option>
    </select>
   （采购单默认的，不能修改）  </p>
  <p>
    
  退货人员
    <select name="select2" id="select" onchange="MM_jumpMenu('parent',this,0)">
      <option value="0101张三" selected="selected">0101张三</option>
      <option value="0102李四">0102李四</option>
      <option value="0103王五">0103王五</option>
    </select>
  （注：根据登陆的用户自动产生）</p>
  <p>退货日期
    <input name="textfield322" type="text" id="textfield32" value="2012-04-21" size="15" />
  （注：日期为默认的，不能修改）</p>
  <p>审核人员
    <select name="select4" onchange="MM_jumpMenu('parent',this,0)">
      <option value="0101张三">0101张三</option>
      <option value="0102李四">0102李四</option>
      <option value="0101王五" selected="selected">0103王五</option>
    </select>
（点击审核后，根据系统登陆用户默认生成审核人员。）</p>
  <p>审核日期
    <input name="textfield32" type="text" id="textfield3" value="2012-04-21" size="15" />
    <input type="submit" name="Submit322" value="日历控件" id="Submit32" />
  （注：以日结为准，可以修改。）</p>
  <p>原料编号
    <input name="textfield42222" type="text" id="textfield422" value="A0010" size="10" />
    原料名称
<select name="select14" onchange="MM_jumpMenu('parent',this,0)">
  <option value="河虾" selected="selected">河虾</option>
  <option value="小鱼头">小鱼头</option>
  <option value="牛蛙">牛蛙</option>
  <option value="牛排">牛排</option>
  <option value="苹果">苹果</option>
  <option value="    "> </option>
</select>
退货数量
<label for="label3"></label>
<input name="textfield42" type="text" id="label3" value="0" size="10" />
<label for="checkbox"></label>
（注：默认退货数量为0，实际数量不得大于采购单数量，可手动单个修改）退货原因
<label for="label"></label>
<input name="textfield422" type="text" id="label" value="过期" size="20" />
<label for="checkbox"></label>
  <input type="submit" name="Submit5" value="添加" />
  <input type="submit" name="Submit52" value="删除" />
  </p>
  <p>备注
    <input type="text" name="textfield5" />
</p>
  <p>　　　　　　　　　
    <input type="submit" name="Submit" value="确定" />　　　　
    
    <input name="Submit2" type="submit" value="取消" />
  （注： 信息填完整才可以确定，只要状态为未审核，确定后除默认值，其他还能进行删除、添加或修改）</p>
</form>
  <table width="964" border="1">
    <tr>
      <th width="62" scope="col">序号</th>
      <th width="58" scope="col">原料编码</th>
      <th width="58" scope="col">国际编码</th>
      <th width="58" scope="col">原料名称</th>
      <th width="51" scope="col">单位</th>
      <th width="51" scope="col">规格</th>
      <th width="51" scope="col">类别</th>
      <th width="61" scope="col">退货数量</th>
      <th width="48" scope="col">单价</th>
      <th width="48" scope="col">金额</th>
      <th width="68" scope="col">库存数量</th>
      <th width="83" scope="col">原料有效期</th>
      <th width="68" scope="col">退货原因</th>
      <th width="111" scope="col">原始单据编号</th>
    </tr>
    <tr>
      <th scope="row">1</th>
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
      <th scope="row">2</th>
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
      <th scope="row">3</th>
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
      <th scope="row">......</th>
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
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
</table>
</body>

</html>
