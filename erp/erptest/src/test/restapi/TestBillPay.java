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
				oneJson.put("payTypeName", "�ֽ�");
			}else if(i==1){
				oneJson.put("payTypeId", "pay02");
				oneJson.put("payTypeName", "���п�����");
			}else if(i==2){
				oneJson.put("payTypeId", "pay03");
				oneJson.put("payTypeName", "֧����");
			}
			oneJson.put("price","100");
			oneJson.put("unit","Ԫ");
			oneJson.put("brandId","test");
			jsonArr.add(oneJson);
		}
		
		String jsonStr = jsonArr.toString();
		
		RestApiTest.httpTest("pay", jsonStr);
	}
}
