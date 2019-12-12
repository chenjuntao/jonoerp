/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月4日 by cjt
 * Last edited on 2016年3月4日 by cjt
 * 
 * 说明： 
 */
package test.restapi;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author cjt
 *
 */
public class TestBillPay
{	
	public static void test_Food_Pay(String orderId)
	{
		JSONArray jsonArr = new JSONArray();
		
		for (int i = 0 ; i < 3; i++ )
		{
			JSONObject oneJson = new JSONObject();
			oneJson.put("orderId",orderId);
			if(i==0){
				oneJson.put("payTypeId", "pay01");
				oneJson.put("payTypeName", "现金");
			}else if(i==1){
				oneJson.put("payTypeId", "pay02");
				oneJson.put("payTypeName", "银行卡付款");
			}else if(i==2){
				oneJson.put("payTypeId", "pay03");
				oneJson.put("payTypeName", "支付宝");
			}
			oneJson.put("price","100");
			oneJson.put("unit","元");
			oneJson.put("brandId","test");
			jsonArr.add(oneJson);
		}
		
		String jsonStr = jsonArr.toString();
		
		RestApiTest.httpTest("pay", jsonStr);
	}
}
