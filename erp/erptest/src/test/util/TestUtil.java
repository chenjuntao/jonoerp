/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月4日 by cjt
 * Last edited on 2016年3月4日 by cjt
 */
package test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.SAXException;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * 用于模拟发送json数据，提供两种方法进行测试：HttpUnit和HttpURLConnection
 */
public class TestUtil
{
	/**
	 * 用HttpUnit进行发送json数据http测试，发送单个键值对
	 */
	public static void HttpUnitTest(String url, String key, String json,  boolean isZip)
	{
		  Map<String, String> paramsMap = new HashMap<String, String>();  
		  paramsMap.put(key, json);  
		  HttpUnitTest(url, paramsMap, isZip);
	}
	
	/**
	 * 用HttpUnit进行发送json数据http测试，发送多个键值对
	 */
	public static void HttpUnitTest(String url, Map<String, String> paramsMap,  boolean isZip)
	{
        WebConversation webConversation = new WebConversation();
		WebRequest request = new PostMethodWebRequest(url);
		for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
			String data = entry.getValue();
			if(isZip){	//压缩字符串
				data = ZipHelper.zip(data, true);
			}
			request.setParameter(entry.getKey(), data);
		}
		try {
			WebResponse response = webConversation.getResponse(request);
			System.out.println(response.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用HttpURLConnection进行发送json数据http测试，发送单个键值对
	 */
	public static void HttpURLConnTest(String url, String key, String json,  boolean isZip)
	{
		  Map<String, String> paramsMap = new HashMap<String, String>();  
		  paramsMap.put(key, json);  
		  HttpURLConnTest(url, paramsMap, isZip);
	}
	
	 /**
     * 用HttpURLConnection进行发送json数据http测试，发送多个键值对
     */
    public static void HttpURLConnTest(String url, Map<String, String> paramsMap, boolean isZip)
    {
    	// 组织请求参数---------------------------------------------------
    	StringBuffer params = new StringBuffer();
    	for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
    		String key = entry.getKey();
    		String data = entry.getValue();
            if(isZip){	//压缩字符串
             	data = ZipHelper.zip(data, true);
     		}
    		
            params.append(key);
            params.append("=");
            params.append(data);  
            params.append("&");  
        }  
        if (params.length() > 0) {  
            params.deleteCharAt(params.length() - 1);  
        }  

    	//调用HttpURLConnection--------------------------------------------
    	StringBuffer resultData = new StringBuffer();
        HttpURLConnection conn = null;
        try {
            URL Url = new URL(url);
            // 打开和URL之间的连接 
            conn = (HttpURLConnection)Url.openConnection();
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);         
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(1800000); //设置连接超时为1800s
            conn.setRequestMethod("POST"); //默认是GET请求
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//表示接受表单键值对
            conn.setRequestProperty("Cache-Control","no-cache");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.connect();

    		// 获取URLConnection对象对应的输出流  
            OutputStreamWriter outsw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            // 发送请求参数  
            outsw.write(params.toString());
            // flush输出流的缓冲  
            outsw.flush();
            outsw.close();

            // 根据ResponseCode判断连接是否成功  
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            	 // 定义BufferedReader输入流来读取URL的ResponseData  
                BufferedReader insr = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String inLine;
                while ((inLine = insr.readLine()) != null){
                	resultData.append(inLine);  
                }
                insr.close();
            }else{
            	resultData.append(conn.getResponseMessage());
            }
        } catch (Exception e) {             
        	System.out.println(e.getMessage());
        } finally {
            if (conn!=null){
                conn.disconnect();
            }
        }

        System.out.println(resultData.toString());
    }
}
