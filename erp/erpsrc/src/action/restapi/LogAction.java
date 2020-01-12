/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月16日 by cjt
 * Last edited on 2016年3月16日 by cjt
 */
package action.restapi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.restapi.CompanyBean;
import logic.restapi.RestApiLogBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

/**
 * 说明：查看数据传输情况日志的Action
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public class LogAction extends BaseAction {
	private RestApiLogBean restApiLogBean;
	private CompanyBean companyBean;

	private String sender;
	private String api;
	private String code;
	private String startTime;
	private String endTime;

	private List<Map> senderLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		senderLst = new ArrayList<Map>();
		List<Map> comLst = companyBean.listCompany();
		for (int i = 0; i < comLst.size(); i++) {
			Map comKV = new HashMap();
			comKV.put("code", comLst.get(i).get("companyId"));
			comKV.put("name", comLst.get(i).get("companyName"));
			senderLst.add(comKV);
		}

		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "");
		allItem.put("name", "--请选择--");
		senderLst.add(0, allItem);

		return SUCCESS;
	}

	public void listLog() throws NoPrivilegeException, SQLException, NoConnection {
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");
		if (!TextUtil.isEmpty(sender)) {
			sb.append(" AND SENDER = '" + sender + "'");
		}
		if (!TextUtil.isEmpty(api)) {
			sb.append(" AND API = '" + api + "'");
		}
		if (!TextUtil.isEmpty(code)) {
			sb.append(" AND CODE = '" + code + "'");
		}
		if (!TextUtil.isEmpty(startTime)) {
			sb.append(" AND LOG_TIME >= to_date('" + startTime + "', 'yyyy-mm-dd HH24:mi:ss')");
		}
		if (!TextUtil.isEmpty(endTime)) {
			sb.append(" AND LOG_TIME <= to_date('" + endTime + "', 'yyyy-mm-dd HH24:mi:ss')");
		}
		int total = restApiLogBean.countLog(sb.toString());
		setTotal(total);
		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<Map> logLst = restApiLogBean.listLog(sb.toString(), getStart(), getEnd());

			int rownumber = 1;
			for (Map header : logLst) {
				JSONObject json = JSONObject.fromObject(header);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setRestApiLogBean(RestApiLogBean restApiLogBean) {
		this.restApiLogBean = restApiLogBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		if (TextUtil.isEmpty(startTime)) {
			startTime = DateTimeUtil.getDateTimeOff(DateTimeUtil.getDateTime(), -1);
		}
		return startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		if (TextUtil.isEmpty(endTime)) {
			endTime = DateTimeUtil.getDateTime();
		}
		return endTime;
	}

	public List<Map> getSenderLst() {
		return senderLst;
	}
}
