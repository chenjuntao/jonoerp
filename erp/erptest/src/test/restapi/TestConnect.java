/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年1月26日 by cjt
 * Last edited on 2016年1月26日 by cjt
 * 
 * 测试单据post信息
 */

package test.restapi;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
/**
 * 测试数据交互接口中的单据post信息
 */
public class TestConnect {

	private int a, b;

	@Before
	public void initObj() {
		a = 2;
		b = 2;
	}

	//@Test
	public void test() {
		// fail("Not yet implemented");
		Assert.assertEquals("测试到不相等！", a, b);
	}

	@Test
	public void testPostConnect() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new PostMethodWebRequest(RestApiTest.baseUrl + "testConnect");
		request.setParameter("bill", "test");
		try {
			WebResponse response = webConversation.getResponse(request);
			System.out.println(response.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
   
}
