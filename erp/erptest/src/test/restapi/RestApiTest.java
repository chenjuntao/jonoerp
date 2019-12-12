/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月10日 by cjt
 * Last edited on 2016年3月10日 by cjt
 */
package test.restapi;

import test.util.TestUtil;

/**
 * @author cjt
 * 说明：测试RestAPI公共类
 */
public class RestApiTest
{
//	public static String baseUrl = "http://10.1.1.152:81/erp/api/";
//	public static String baseUrl = "http://127.0.0.1:8080/erp/api/";
	public static String baseUrl = "http://114.55.33.130:8081/erp/api/";
	
	public static void httpTest(String key, String json){
		TestUtil.HttpURLConnTest(baseUrl+key, key, json, true);
	}
	
//	public static void httpTest(String key, String json){
//		TestUtil.HttpUnitTest(baseUrl+key, key, json, true);
//	}
}
