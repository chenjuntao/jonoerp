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
public class TestRestaurant
{
    @Test
    public void test_RESTAURANT()
    {
    	JSONArray jsonArr = new JSONArray();
    	for (int i = 0 ; i < 1; i++){
			JSONObject oneJson = new JSONObject();
			SimpleDateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date dt = new java.util.Date();
			String createTime = dtFmt.format(dt);
			oneJson.put("brandId","bv");
			oneJson.put("restaurantId","localTestId" + i);
			oneJson.put("rName","���ز����ŵ�" + i);
			oneJson.put("queryCode","bdcs" + i);
			oneJson.put("address","����һ����");
			oneJson.put("contactMan","Ԭ��");
			oneJson.put("email","xxx@xxx.com");
			oneJson.put("phone","13100001111");
			oneJson.put("fax","0731-88888888");
			oneJson.put("brief","���ڲ���API���������");
			oneJson.put("state","1");
			oneJson.put("park","200");
			oneJson.put("tag","rest test");
			oneJson.put("lon",74.0);
			oneJson.put("lat",80.0);
			oneJson.put("createTime",createTime);
			oneJson.put("lastModify",createTime);
			jsonArr.add(oneJson);
    	}
		
		String jsonStr = jsonArr.toString();
		
		RestApiTest.httpTest("restaurant", jsonStr);
    }
}
