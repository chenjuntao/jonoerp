/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月1日 by cjt
 * Last edited on 2016年3月1日 by cjt
 */
package service.restapi.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.restapi.RestApiErrJsonBean;
import logic.restapi.RestApiErrMsgBean;
import logic.restapi.RestApiLogBean;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * 记录RESTAPI的输出日志等信息
 */
public class RestApiLog {
	// 专门用来输出RESTAPI的日志
	public Logger log = Logger.getLogger("rest");

	public static RestApiLog restUtil;

	public void setRestUtil(RestApiLog restUtil) {
		RestApiLog.restUtil = restUtil;
	}

	// 写日志到数据库的javabean
	private RestApiLogBean restApiLogBean;

	public void setRestApiLogBean(RestApiLogBean restApiLogBean) {
		this.restApiLogBean = restApiLogBean;
	}

	private RestApiErrJsonBean restApiErrJsonBean;

	public void setRestApiErrJsonBean(RestApiErrJsonBean restApiErrJsonBean) {
		this.restApiErrJsonBean = restApiErrJsonBean;
	}

	private RestApiErrMsgBean restApiErrMsgBean;

	public void setRestApiErrMsgBean(RestApiErrMsgBean restApiErrMsgBean) {
		this.restApiErrMsgBean = restApiErrMsgBean;
	}

	/**
	 * 调用完API后记录调用具体信息（包括各种错误和警告）的日志
	 * 
	 * @param api
	 *            具体是哪个接口比如"bill"、"pay"等
	 * @param sender
	 *            发送方，这里是指companyId
	 * @return 返回给客户端显示的json信息
	 */
	public JSONObject LogRestResult(String api, String sender, JsonHelper jsonHelper) {
		// 判断返回码
		int code = ReturnCode(jsonHelper.warns.size(), jsonHelper.errs.size(), jsonHelper.successCount);

		// 生成唯一的日志ID
		String logId = GuidHelper.generateId();

		// 写日志---------------------------------------------------------------------------
		Map logMap = new HashMap();
		logMap.put("logId", logId);
		logMap.put("api", api);
		logMap.put("code", code);
		logMap.put("receive", jsonHelper.allCount);
		logMap.put("success", jsonHelper.successCount);
		logMap.put("fail", jsonHelper.allCount - jsonHelper.successCount);
		logMap.put("logTime", new Date());
		logMap.put("sender", sender);
		// 出错的Json数据行
		List<Map> errJsonMaps = new ArrayList<Map>();
		for (Map.Entry<String, String> entry : jsonHelper.errJson.entrySet()) {
			Map errJsonMap = new HashMap();
			errJsonMap.put("logId", logId);
			errJsonMap.put("errId", entry.getKey());
			String jsonStr = entry.getValue();
			if (jsonStr.length() > 799) {
				jsonStr = jsonStr.substring(0, 799);
			}
			errJsonMap.put("jsonStr", jsonStr);
			errJsonMaps.add(errJsonMap);
		}
		// 具体的出错提示信息
		List<Map> errMsgMaps = new ArrayList<Map>();
		for (int i = 0; i < jsonHelper.warns.size(); i++) {
			String[] warn = jsonHelper.warns.get(i).toString().split(":", 4);
			if (warn.length >= 4) {
				Map warnMap = new HashMap();
				warnMap.put("logId", logId);
				warnMap.put("code", warn[0]);
				warnMap.put("errId", warn[1]);
				warnMap.put("msgId", warn[2]);
				String jsonStr = warn[3];
				if (jsonStr.length() > 199) {
					jsonStr = jsonStr.substring(0, 199);
				}
				warnMap.put("message", jsonStr);
				errMsgMaps.add(warnMap);
			}
		}
		for (int i = 0; i < jsonHelper.errs.size(); i++) {
			String[] err = jsonHelper.errs.get(i).toString().split(":", 4);
			if (err.length >= 4) {
				Map errMap = new HashMap();
				errMap.put("logId", logId);
				errMap.put("code", err[0]);
				errMap.put("errId", err[1]);
				errMap.put("msgId", err[2]);
				String jsonStr = err[3];
				if (jsonStr.length() > 199) {
					jsonStr = jsonStr.substring(0, 199);
				}
				errMap.put("message", jsonStr);
				errMsgMaps.add(errMap);
			}
		}

		try {
			restApiLogBean.saveEntity(logMap);
			restApiErrJsonBean.saveEntity(errJsonMaps);
			restApiErrMsgBean.saveEntity(errMsgMaps);
			log.debug(api + ":restapi log has been saved in db!");
		} catch (Exception e) {
			log.debug(api + ":restapiLogErr:" + e.toString());
			e.printStackTrace();
		}

		/*
		 * log.debug(api+":received"+jsonHelper.allCount+",success"+jsonHelper.
		 * successCount
		 * +",warn"+jsonHelper.warns.size()+",err"+jsonHelper.errs.size());
		 */
		// 给客户端返回的json信息-----------------------------------------------------------------
		JSONObject result = new JSONObject(); // 返回给客户端的信息
		result.put("api", api);
		result.put("code", code);
		result.put("receive", jsonHelper.allCount);
		result.put("success", jsonHelper.successCount);
		result.put("fail", jsonHelper.allCount - jsonHelper.successCount);
		result.put("warn", jsonHelper.warns);
		result.put("err", jsonHelper.errs);

		return result;
	}

	// 根据具体的调用成功情况判断返回码
	private int ReturnCode(int warnCount, int errCount, int successCount) {
		int code = 200;
		if (warnCount > 0) {
			code = 201;
		}

		if (errCount > 0) {
			if (successCount == 0) {
				code = 203;
			} else {
				code = 202;
			}
		}
		return code;
	}
}
