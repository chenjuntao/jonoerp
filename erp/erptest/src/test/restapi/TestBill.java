/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016��3��4�� by cjt
 * Last edited on 2016��3��4�� by cjt
 * 
 * ˵���� 
 */
package test.restapi;

import java.text.SimpleDateFormat;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import test.restapi.RestApiTest;

/**
 * 
 * ˵�������Զ���/Ӫҵ����
 */
public class TestBill
{
	@Test
    public void test_Food_Bill(){
		test_Food_Bill(3);
	}	
 	
	public void test_Food_Bill(int num){
		JSONObject oneJson;
		JSONArray jsonArr = new JSONArray();
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat dBuFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dtBillFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dBusiness = dBuFormat.format(dt);
		String dtBillTime = dtBillFormat.format(dt);
		String dtSettleTime = dtBillFormat.format(dt);
		for (int i = 0 ; i < num ; i++ )
		{
			SimpleDateFormat xixi = new SimpleDateFormat("yyMMddHHmmss");
			String orderId = xixi.format(dt) + i;
			
			TestBillFood.test_Food_Bills(orderId);
			TestBillPay.test_Food_Pay(orderId);
			
			oneJson = new JSONObject();
			oneJson.put("orderId",orderId);
			oneJson.put("businessDate",dBusiness);
			if(i%2==0){
				oneJson.put("period","���");
				oneJson.put("deskName","01��");
			}else if(i%3==0){
				oneJson.put("period","���");
				oneJson.put("deskName","02��");
			}else{
				oneJson.put("period","���");
				oneJson.put("deskName","03��");
			}
			oneJson.put("restId","00");
			oneJson.put("restName","�����ŵ�");
			oneJson.put("personCount",i%3+1);
			oneJson.put("consumerId","0000011");
			oneJson.put("memo","���Ե�");
			oneJson.put("confirmTime",dtBillTime);
			oneJson.put("payTime",dtSettleTime);
			double food_amt = Math.random()*80;
			oneJson.put("totalPrice",food_amt);
			double nDisAmt = Math.random()*5;
			oneJson.put("discountPrice",nDisAmt);
			oneJson.put("discountReason","��Ա�ۿ�");
			oneJson.put("realPrice",food_amt - nDisAmt);
			oneJson.put("roundPrice",0);
			oneJson.put("paidPrice",food_amt - nDisAmt);
			oneJson.put("employeeName","����");
			oneJson.put("cSettleMan","����");
			oneJson.put("brandId","test");
			oneJson.put("brandName","test");
			jsonArr.add(oneJson);
		}

		String jsonStr = jsonArr.toString();
		RestApiTest.httpTest("bill", jsonStr);
	}
}
