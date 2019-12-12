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
public class TestBillFood
{
	public static void test_Food_Bills(String orderId)
	{
		JSONArray jsonArr = new JSONArray();
		
		for (int i = 0 ; i < 3; i++ )
		{
			JSONObject oneJson = new JSONObject();
			oneJson.put("orderId",orderId);
			oneJson.put("foodId", "food" + i);
			if(i==0){
				oneJson.put("foodName", "ţ��");
				oneJson.put("discountReason","VIP�ۿ�");
				oneJson.put("foodSpecName","���");
				oneJson.put("made","�߷���");
			}else if(i==1){
				oneJson.put("foodName", "����");
				oneJson.put("discountReason","�����Ż�");
				oneJson.put("foodSpecName","�з�");
				oneJson.put("made","������");
			}else if(i==2){
				oneJson.put("foodName", "���");
				oneJson.put("discountReason","֧�����ۿ�");
				oneJson.put("foodSpecName","С��");
				oneJson.put("made","");
			}
			
			oneJson.put("foodTypeId","API");
			oneJson.put("foodTypeName", "���Գ�Ʒ���");
			double nqty = Math.random()*5;
			oneJson.put("amount",nqty);
			double nPrc = Math.random()*10;
			oneJson.put("originPrice",nPrc);
			oneJson.put("totalOriginalPrice",nqty*nPrc);
			oneJson.put("discountPrice", Math.random());
			oneJson.put("isCombo","0");
			oneJson.put("comboId","");
			oneJson.put("comboName","");
			oneJson.put("isDiscount",1);
			oneJson.put("brandId","test");
			jsonArr.add(oneJson);
		}
		
		String jsonStr = jsonArr.toString();

		RestApiTest.httpTest("bills", jsonStr);
	}
}
