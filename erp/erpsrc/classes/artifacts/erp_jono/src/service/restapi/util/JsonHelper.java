/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年2月23日 by cjt
 * Last edited on 2016年2月23日 by cjt
 */
package service.restapi.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tanry.framework.util.DateTimeUtil;

//说明： 解析json字符串公共方法
public class JsonHelper
{
	public int allCount = 0; //总共接收处理的数据个数
	public int successCount = 0; //数据处理入库成功的个数
	
	private int currentIndex; //表示当前正在处理第几个数据
	private JSONObject json; //当前正在被解析的json对象
	
	public JSONArray warns = new JSONArray(); //警告信息列表
	public JSONArray errs = new JSONArray(); //错误信息列表
	
	private int warnCount = 0; //记录每次json对象解析时是否出现警告
	private int errCount = 0; //记录每次json对象解析时是否出现错误
	public Map<String,String> errJson = new HashMap<String,String>(); //出错的json数据集合
	
	/**
	 * 一个循环中每次解析一个json对象开始时调用该方法----------------------
	 * @param newIndex 设置新的索引位置
	 * @param newJson 设置新的json数据
	 */
	public void start(int newIndex, JSONObject newJson){
		currentIndex = newIndex;
		json = newJson;
	}
	
	/**
	 * 一个循环中每次解析一个json对象结束时调用该方法----------------------
	 */
	public void end(){
		//总个数加1
		allCount++;
		
		//判断该次json对象解析时是否出现错误
		if(errs.size()==errCount){
			successCount++;
		}
		
		//判断该次json对象解析时是否出现警告或者错误
		if(errs.size()+warns.size()>warnCount+errCount){
			errJson.put(Integer.toString(currentIndex), json.toString());
			warnCount = warns.size();
			errCount = errs.size();
		}
	}
	
	/**
	 * 根据URL获取到的字符串进行一系列步骤之后得到JSONArray，具体的步骤
	 * @param originalStr
	 * @return 返回生成的JsonArray
	 */
	public JSONArray getJsonArr(String originalStr)
	{
		//注意：这里tomcat已经自动对字符串进行了URLDecode，所以不需要进行Decode了
		//----------------------------------------------------------------------------------
		//第一步：解压字符串，获取得到原始JSON字符串
		String jsonStr = ZipHelper.unzip(originalStr, false);
		
//		RestUtil.log.debug(originalStr);
//		RestUtil.log.debug("--------------------------------");
//		RestUtil.log.debug(jsonStr);
		
		if(jsonStr.startsWith("unzip_err:")){
			errs.add("400:0:UnZipErr:"+jsonStr);
			return null;
		}
		
		//----------------------------------------------------------------------------------
		//第二步：用JSON引擎对字符串进行反序列化，得到JSONArray
		try {
			return JSONArray.fromObject(jsonStr);
		} catch (Exception ex) {
			errs.add("400:0:JsonFormatErr:"+ex.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取Json字符串格式的ID
	 * @param key Json对象中要获取值的键
	 * @param maxLength 最大长度
	 * @return 如果不存在，则返回""
	 */
	public String getJsonVId(String key, int maxLength)
	{
		if(json.containsKey(key)){
			try{
				String result = json.getString(key);
				if(result.length() > maxLength){
					errs.add("403:"+currentIndex+":"+key+":too long to write in db!");
				}else{
					return result;
				}
			}
			catch (Exception ex){
				errs.add("400:"+currentIndex+":"+key+"ParseException:" + ex.getMessage());
			}
		}
		else{
			errs.add("401:"+currentIndex+":"+key+":can not be null!");
		}
		
		return "";
	}
	
	/**
	 * 获取Json字符串格式的值
	 * @param key Json对象中要获取值的键
	 * @param maxLength 最大长度
	 * @return 如果不存在，则返回""
	 */
	public String getJsonVStr(String key, int maxLength)
	{
		if(json.containsKey(key)){
			try{
				String result = json.getString(key);
				if(result.length() > maxLength){
					errs.add("303:"+currentIndex+":"+key+":data is too long and truncate!");
					return result.substring(0, maxLength-1);
				}else{
					return result;
				}
			}
			catch (Exception ex){
				warns.add("300:"+currentIndex+":parseException：" + ex.getMessage());
			}
		}
		else{
			warns.add("301:"+currentIndex+":"+key+":json key not found!");
		}
		
		return "";
	}
	
	/**
	 * 获取Json整形数Integer格式的值
	 * @param key Json对象中要获取值的键
	 * @param maxValue 最大值
	 * @return 如果不存在，则返回0
	 */
	public Integer getJsonVInt(String key, int maxValue)
	{
		if(json.containsKey(key)){
			try{
				int result = json.getInt(key);
				if(result > maxValue){
					errs.add("303:"+currentIndex+":"+key+":data exceeds the max value!");
					return maxValue;
				}else{
					return result;
				}
			}
			catch (Exception ex){
				warns.add("300:"+currentIndex+":parseException：" + ex.getMessage());
			}
		}
		else{
			warns.add("301:"+currentIndex+":"+key+":json key not found!");
		}
		
		return 0;
	}
	
	/**
	 * 获取Json双精度浮点数Double格式的值
	 * @param key Json对象中要获取值的键
	 * @param @param maxValue 最大值
	 * @return 如果不存在，则返回0.0
	 */
	public Double getJsonVDouble(String key, double maxValue)
	{
		if(json.containsKey(key)){
			try{
				double result = json.getDouble(key);
				if(result > maxValue){
					errs.add("303:"+currentIndex+":"+key+":data exceeds the max value!");
					return maxValue;
				}else{
					return result;
				}
			}
			catch (Exception ex){
				warns.add("300:"+currentIndex+":parseException：" + ex.getMessage());
			}
		}
		else{
			warns.add("301:"+currentIndex+":"+key+":json key not found!");
		}
		
		return 0.0;
	}

	/**
	 * 获取Json日期格式的值（只包含年月日，日期字符串格式为"yyyy-MM-dd"）
	 * @param key Json对象中要获取值的键
	 * @param maxLength 最大长度
	 * @return 如果不存在或无法解析，则返回当前日期
	 */
	public Date getJsonVDate(String key)
	{
		String dateStr = getJsonVStr(key, 10);
		Date dt =  DateTimeUtil.parse(dateStr,  DateTimeUtil.DEFAULT_DATE_FORMAT);
		if(dt == null){
			warns.add("302:"+currentIndex+":"+key+":date format not correct," + dateStr);
			dateStr = DateTimeUtil.getDate();
		}
		dt =  DateTimeUtil.parse(dateStr, DateTimeUtil.DEFAULT_DATE_FORMAT);

		return dt;
	}
	
	/**
	 * 获取Json日期时间格式的值（包含年月日时分秒，日期字符串格式为"yyyy-MM-dd HH:mm:ss"）
	 * @param key Json对象中要获取值的键
	 * @param maxLength 最大长度
	 * @return 如果不存在或无法解析，则返回当前时间
	 */
	public Date getJsonVDateTime(String key)
	{
		String dateStr = getJsonVStr(key, 19);
		Date dt =  DateTimeUtil.parse(dateStr,  DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		if(dt == null){
			warns.add("302:"+currentIndex+":"+key+":date format not correct," + dateStr);
			dateStr = DateTimeUtil.getDateTime();
		}
		dt =  DateTimeUtil.parse(dateStr, DateTimeUtil.DEFAULT_DATETIME_FORMAT);

		return dt;
	}
	
}
