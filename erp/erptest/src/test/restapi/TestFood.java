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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * @author cjt
 *
 */
public class TestFood
{
	@Test
    public void test_FOOD_META()
    {
    	JSONArray jsonArr = new JSONArray();
		JSONObject oneJson = new JSONObject();
//			SimpleDateFormat xixi = new SimpleDateFormat("yyMMddHHmmSS");
		SimpleDateFormat dtcreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new java.util.Date();
		String createTime = dtcreateTime.format(dt);
//			String hehe = xixi.format(dt);
		oneJson.put("brandId","bv");
		oneJson.put("foodId","ddddddddd1");
		oneJson.put("foodName","С���ƶ�");
		oneJson.put("shortName","��");
		oneJson.put("foodDimension","003");
		oneJson.put("shortHandName","003");
		oneJson.put("categoryId","DEFAULT_FOOD_CATEGORY");
		oneJson.put("image","0002");
		oneJson.put("description","����");
		oneJson.put("status","1");
		oneJson.put("lastModify",createTime);
		jsonArr.add(oneJson);
		
		String jsonStr = jsonArr.toString();
		RestApiTest.httpTest("food", jsonStr);
    }
}
