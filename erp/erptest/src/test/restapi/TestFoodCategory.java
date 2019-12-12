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
public class TestFoodCategory
{
	@Test
    public void test_FOOD_CATEGORY()
    {
    	JSONArray jsonArr = new JSONArray();
		JSONObject oneJson = new JSONObject();
		SimpleDateFormat xixi = new SimpleDateFormat("yyMMddHHmmSS");
//			SimpleDateFormat dtcreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date dt = new java.util.Date();
//			String createTime = dtcreateTime.format(dt);
		String hehe = xixi.format(dt);
		oneJson.put("brandId","bv");
		oneJson.put("id","124");
		oneJson.put("name","牛腩大钞");
		oneJson.put("parentId","*");
		oneJson.put("level","");
		oneJson.put("path","");
		jsonArr.add(oneJson);
		
		String jsonStr = jsonArr.toString();
		RestApiTest.httpTest("food_category", jsonStr);
    }
}
