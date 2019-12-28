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
 * 说明： 用于压缩和解压缩字符串
 */
package service.restapi.util;

import java.io.ByteArrayOutputStream;  
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.DeflaterOutputStream;  
import java.util.zip.InflaterOutputStream;  

import org.apache.commons.codec.binary.Base64;  

/**
 * @author cjt
 * 说明：对字符串进行压缩的帮助类，如果数据量较大，可以使用该方法进行压缩
 */
public class ZipHelper {  
      
	//  压缩字符串  
    public static String zip(String data, boolean isUrlEncode) {  
        try {  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            DeflaterOutputStream zos = new DeflaterOutputStream(bos);  
            zos.write(data.getBytes("UTF-8"));  
            zos.close();  
            String result = new String(getenBASE64inCodec(bos.toByteArray())); 
           
            if(isUrlEncode){
            	result = urlEncode(result);
            }
            return result;
        } catch (Exception ex) {  
            ex.printStackTrace();  
            return "ZIP_ERR";  
        }  
    }  
      
    //  使用apche codec对数组进行encode  
    private static String getenBASE64inCodec(byte [] b) {  
        if (b == null)  
            return null;  
        return new String((new Base64()).encode(b));  
    }
    
    //urlencode编码
    private static String urlEncode(String str){
    	String finalStr = "";

		try {
			finalStr = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		
		return finalStr;
    }
    
    //urldecode编码
    private static String urlDecode(String str){
    	String finalStr = "";
		try {
			finalStr = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		
		return finalStr;
    }
  
    //  base64转码为string 
    private static byte[] getdeBASE64inCodec(String s) {  
        if (s == null)  
            return null;  
        return new Base64().decode(s.getBytes());  
    }  
      
    //  解码字符串  
    public static String unzip(String encdata, boolean isUrlDecode) {  
        try {
        	if(isUrlDecode){
        		encdata = urlDecode(encdata);
        	}
        	
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            InflaterOutputStream zos = new InflaterOutputStream(bos);  
            zos.write(getdeBASE64inCodec(encdata));   
            zos.close();  
            return new String(bos.toByteArray(), "UTF-8");  
        } catch (Exception ex) {
            ex.printStackTrace();  
            return "UNZIP_ERR";  
        }  
    }  
    
    public static void main(String[] args) {
        String compString = ZipHelper.zip("测试用的字符串", true);  
        System.out.println(compString);  
          
        String decompString = ZipHelper.unzip(compString, true);  
        System.out.println(decompString);
        
        try {
        	//得到对象
        	Class c = Class.forName("logic.businessquery.BillBean");
        	Object yourObj = c.newInstance();
        	//得到方法
        	Method methlist[] = c.getDeclaredMethods();
        	for (int i = 0; i < methlist.length; i++) {
        		Method m = methlist[i];
        		
        		System.out.println(m.toString());//返回类型
        	    System.out.println(m.getModifiers());//公共状态
                System.out.println(m.getGenericReturnType());//返回类型
                System.out.println(m.getName());//方法名
                
                System.out.println("/service");//方法名
                
//                Type[] paramTypeList = m.toGenericString();// 参数列表
               /* for (Type paramType : paramTypeList) {}*/
    
        	}
        	//获取到方法对象,假设方法的参数是一个int,method名为setAge
        	Method sAge = c.getMethod("setAge", new Class[] {int.class});
        	//获得参数Object
        	Object[] arguments = new Object[] { new Integer(37)};
        	//执行方法
        	sAge.invoke(yourObj , arguments);
        } catch (Exception e) {
        }  
    }  
} 