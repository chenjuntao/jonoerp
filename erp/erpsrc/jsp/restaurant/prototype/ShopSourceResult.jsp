<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*,java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="logic.businessquery.*"%>
<%@ page import="java.util.*"%>
<!-- 
作者：吴鹏
时间：2014-07-15
说明：显示餐厅某特定原材料的详细信息
    -->
<div style="width:100%;overflow:auto;">
  <form id="form1" name="form1" method="post" action="">
    原料编号<label>
      <input name="sourceNum" type="text" id="sourceNum" size="10" readonly="true" />
    </label>
    国际条码
    <label>
    <input name="interCode" type="text" id="interCode" size="10" readonly="true" />
    </label>
    原料全称
    <label>
    <input name="fullName" type="text" id="fullName" size="30" readonly="true" />
    </label>
    原料简称
    <label>
    <input name="simpleName" type="text" id="simpleName" size="30" readonly="true" />
    <br />
    <br />
    规格
    <input name="former" type="text" id="former" size="15" readonly="true" />
    单位
    <input name="danwei" type="text" id="danwei" size="15" readonly="true" />
    助记码
    <input name="helpRem" type="text" id="helpRem" size="20 readonly="true"" />
    大类别
    <input name="type1" type="text" id="type1" size="15" readonly="true" />
    小类别
    <input name="type2" type="text" id="type2" size="15" readonly="true" />
    <br />
    <br />
    进价
    <input name="price" type="text" id="price" size="12" readonly="true" />
    供应商
    <input name="supply" type="text" id="supply" size="30" readonly="true" />
    <br />
    <br />
    检验标准
    <textarea name="checkStandard" cols="30" rows="4" readonly="true" id="checkStandard"></textarea>
     图示（从数据库中读出原材料的图片显示）<br />
    </label>
  </form>
</div>
