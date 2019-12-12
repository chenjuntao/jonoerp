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

import java.text.SimpleDateFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * @author cjt
 *
 */
public class TestFoodPrice
{
	@Test
    public void test_FOOD_PRICE()
    {
    	JSONArray jsonArr = new JSONArray();
    	for (int i = 0 ; i < 1 ; i++){
			JSONObject oneJson = new JSONObject();
			SimpleDateFormat xixi = new SimpleDateFormat("yyMMddHHmmSS");
			SimpleDateFormat dtcreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date dt = new java.util.Date();
			String createTime = dtcreateTime.format(dt);
			String hehe = xixi.format(dt);
			oneJson.put("brandId","JOIN");
			oneJson.put("foodId",hehe+i);
			oneJson.put("price","100");
			oneJson.put("priceType","价格组1");
			oneJson.put("status","1");
			oneJson.put("lastModify",createTime);
			jsonArr.add(oneJson);
    	}
		
		String jsonStr = jsonArr.toString();
		RestApiTest.httpTest("food_price", jsonStr);
    }
}
