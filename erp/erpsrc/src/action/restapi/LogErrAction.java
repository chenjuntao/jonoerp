/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月22日 by cjt
 * Last edited on 2016年3月22日 by cjt
 */
package action.restapi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.restapi.RestApiErrJsonBean;
import logic.restapi.RestApiErrMsgBean;
import net.sf.json.JSONArray;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 说明：查看数据传输出错信息的Action
 */
@SuppressWarnings({"serial","rawtypes"})
public class LogErrAction extends BaseAction
{
	private RestApiErrJsonBean restApiErrJsonBean;
	private RestApiErrMsgBean restApiErrMsgBean;
	
	private String errJsonList;

	private String logId;
	private String errId;
	
	public String execute() {
		return SUCCESS;
	}
	
	public void listErrJson() throws NoPrivilegeException, SQLException, NoConnection {

		List<Map> errJsonLst = restApiErrJsonBean.listErrJson(logId);
		JSONArray jsonArray = JSONArray.fromObject(errJsonLst);
		
		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listErrMsg() throws NoPrivilegeException, SQLException, NoConnection {

		List<Map> errMsgLst = restApiErrMsgBean.listErrMsg(logId, errId);
		JSONArray jsonArray = JSONArray.fromObject(errMsgLst);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setRestApiErrJsonBean(RestApiErrJsonBean restApiErrJsonBean)
	{
		this.restApiErrJsonBean = restApiErrJsonBean;
	}

	public void setRestApiErrMsgBean(RestApiErrMsgBean restApiErrMsgBean)
	{
		this.restApiErrMsgBean = restApiErrMsgBean;
	}

	public void setLogId(String logId)
	{
		this.logId = logId;
	}
	
	public String getLogId()
	{
	    return this.logId;
	}

	public void setErrId(String errId)
	{
		this.errId = errId;
	}

	public String getErrJsonList()
	{
		return errJsonList;
	}
}
