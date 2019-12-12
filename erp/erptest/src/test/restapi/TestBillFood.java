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
				oneJson.put("foodName", "牛排");
				oneJson.put("discountReason","VIP折扣");
				oneJson.put("foodSpecName","大份");
				oneJson.put("made","七分熟");
			}else if(i==1){
				oneJson.put("foodName", "咖啡");
				oneJson.put("discountReason","美团优惠");
				oneJson.put("foodSpecName","中份");
				oneJson.put("made","不加糖");
			}else if(i==2){
				oneJson.put("foodName", "甜点");
				oneJson.put("discountReason","支付宝折扣");
				oneJson.put("foodSpecName","小份");
				oneJson.put("made","");
			}
			
			oneJson.put("foodTypeId","API");
			oneJson.put("foodTypeName", "测试出品类别");
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
