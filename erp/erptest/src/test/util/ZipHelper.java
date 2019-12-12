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
package test.util;

import java.io.ByteArrayOutputStream;  
import java.io.UnsupportedEncodingException;
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
        
        Double a = 9999.22;
        System.out.println(Math.pow(10,4));
        
        
    }  
}  