/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年2月8日 by cjt
 * Last edited on 2016年2月8日 by cjt
 */

package test.util;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

/**
 * HttpUnit测试样例代码
 */
public class HttpUnitExample {

	@Test
	public void test() {
		testWebExists();
		// testWebContenctWithParameterPost();
	}

	// 测试页面是否存在1
	public void testWebExists() {
		// 模拟浏览器对象,拥有一个浏览器
		WebConversation webConversation = new WebConversation();
		try {
			webConversation.getResponse("http://localhost:8080/erp/");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试页面是否存在2
	public void testWebExists2() {
		// 模拟浏览器对象,拥有一个浏览器
		WebConversation webConversation = new WebConversation();
		// 用get方法得到 一个请求对象
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/erp");
		try {
			webConversation.getResponse(request);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试获取页面的内容 (源代码)
	public void testWebContenct() {
		WebConversation webConversation = new WebConversation();
		try {
			WebResponse response = webConversation.getResponse("http://localhost:8080/erp");
			System.out.println(response.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试获取页面内容,并传递参数(get方式)
	public void testWebContenctWithParameterGet() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/Httpunit/");
		request.setParameter("username", "xaiobai");
		try {
			WebResponse response = webConversation.getResponse(request);
			System.out.println(response.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试获取页面的内容,并传递参数(post方式 )
	public void testWebContenctWithParameterPost() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new PostMethodWebRequest("http://localhost:8080/erp");
		request.setParameter("pw", "pw");
		try {
			WebResponse response = webConversation.getResponse(request);
			System.out.println(response.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试页面成功跳转
	public void testWebPageRedirectSuccess() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new PostMethodWebRequest("http://localhost:8080/Httpunit/validate.jsp");
		request.setParameter("username", "xiaobai");
		request.setParameter("password", "123");
		try {
			WebResponse response = webConversation.getResponse(request);
			String expectedUrl = "http://localhost:8080/Httpunit/success.jsp";
			Assert.assertEquals("验证失败", expectedUrl, response.getURL().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试页面失败跳转
	public void testWebPageRedirectFailed() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new PostMethodWebRequest("http://localhost:8080/Httpunit/validate.jsp");
		request.setParameter("username", "xiaobai");
		request.setParameter("password", "123111");
		try {
			WebResponse response = webConversation.getResponse(request);
			String expectedUrl = "http://localhost:8080/Httpunit/failed.jsp";
			Assert.assertEquals("验证失败", expectedUrl, response.getURL().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试网页表格
	public void testWebTable() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/Httpunit/tableTest.jsp");
		try {
			WebResponse response = webConversation.getResponse(request);
			// 获取外层表格的第一个
			WebTable table = response.getTables()[0];
			Assert.assertEquals(2, table.getColumnCount());
			// 行由0开始
			TableCell cell = table.getTableCell(2, 0);
			Assert.assertEquals("版次", cell.getText());
			// this.assertEquals("", table.g)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 测试网络中的连接
	public void testWebClickPage() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/Httpunit/");
		try {
			WebResponse response = webConversation.getResponse(request);
			System.out.println(response.getText());
			WebLink link = response.getLinkWith("HttpUnit ABC");
			request = link.getRequest();
			String expetedURL = request.getURL().toString();
			Assert.assertEquals(expetedURL, "http://localhost:8080/Httpunit/tableTest.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试网络中的form
	public void testWebForm() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/Httpunit/formTest.jsp");
		try {
			WebResponse response = webConversation.getResponse(request);
			System.out.println(response.getText());
			WebForm form = response.getForms()[0];
			String name = form.getParameterValue("Name");
			String food = form.getParameterValue("Food");
			String location = form.getParameterValue("Location");
			String CreditCard = form.getParameterValue("CreditCard");
			Assert.assertEquals("La Cerentolia", name);
			Assert.assertEquals("Chinese", food);
			Assert.assertEquals("CenterCity", location);
			Assert.assertEquals("on", CreditCard);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	// 测试表单提交
	public void testFormSubmit() {
		WebConversation webConversation = new WebConversation();
		WebRequest request = new GetMethodWebRequest("http://localhost:8080/Httpunit/formTest.jsp");
		try {
			WebResponse response = webConversation.getResponse(request);
			System.out.println(response.getText());
			WebForm form = response.getForms()[0];

			form.setParameter("Name", "xiaobai");
			form.setParameter("Food", "Italian");
			form.setParameter("Location", "MiddleCity");
			form.setParameter("CreditCard", "on");
			// 获取提交的url
			response = form.submit();

			String expetedURL = response.getURL().toString();
			Assert.assertEquals("http://localhost:8080/Httpunit/doForm.jsp", expetedURL);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
