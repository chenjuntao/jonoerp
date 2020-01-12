/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 24, 2015 by liyzh
 * Last edited on Mar 24, 2015 by liyzh
 * 
 * 说明： 餐厅、物流中心等进行月结操作
 */
package com.tanry.business.module.hq.settle.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.module.hq.MonthlySettleRecordBean;
import logic.store.BranchBean;
import logic.store.OptionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.business.module.hq.settle.pojo.MonthlySettleRecord;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.SysOption;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class MonthlySettleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private MonthlySettleRecordBean monthlySettleRecordBean;
	private BranchBean branchBean;
	private OptionBean optionBean;

	private String branchId;
	private Date monthDate;
	private List<String> monthLst;
	private Date lastMonth;
	private String nowMonth;

	private Date operatingTime;
	private MonthlySettleRecord settleRecord;

	/**
	 * 关账日
	 */
	private Integer closeDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String dateStr = optionBean.getOption(SysOption.CLOSE_DATE);

		closeDate = Integer.parseInt(dateStr);

		lastMonth = monthlySettleRecordBean.queryLastSettleMonth();
		Date nMonth = DateTimeUtil.getMonthLastDay();
		nowMonth = DateTimeUtil.getDateTime(nMonth, "yyyy-MM");
		Integer lastDate = nMonth.getDate();
		if (closeDate > lastDate) {
			closeDate = lastDate;
		}
		if (lastMonth == null) {
			lastMonth = DateTimeUtil.parse("2015-10", "yyyy-MM");
		}

		return SUCCESS;
	}

	public String settleView() throws NoPrivilegeException, SQLException, NoConnection {
		settleRecord = new MonthlySettleRecord();

		settleRecord.setOperatorId(getLoginUserId());
		settleRecord.setOperatorName(getLoginUsername());

		operatingTime = new Date();
		return SUCCESS;
	}

	public void checkStatus() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = monthlySettleRecordBean.exist(monthDate);

		JSONObject result = new JSONObject();
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询哪些部门没有按时日结
	 */
	public void queryOvertime() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();

		List<Branch> branchLst = branchBean.queryByTypeName(BranchTypeEnum.R_C_L, "");
		int rownumber = getStart();
		for (Branch branch : branchLst) {
			if (branch.getBusinessDate().compareTo(monthDate) <= 0) {
				JSONObject json = JSONObject.fromObject(branch);
				String businessDate = DateTimeUtil.getDateStr(branch.getBusinessDate());
				json.put("businessDate", businessDate);
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

	/**
	 * 查询月结记录
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<MonthlySettleRecord> recordLst = monthlySettleRecordBean.query(null, null);
		int rownumber = getStart();
		for (MonthlySettleRecord record : recordLst) {
			JSONObject json = JSONObject.fromObject(record);
			String monthDateStr = DateTimeUtil.getDateStr(record.getMonthDate());
			json.put("monthDate", monthDateStr);

			String operatingTime = DateTimeUtil.getDateStr(record.getOperatingTime());
			json.put("operatingTime", operatingTime);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveRecord() throws NoPrivilegeException, SQLException, NoConnection {
		settleRecord.setMonthDate(monthDate);
		settleRecord.setOperatingTime(new Date());
		settleRecord.setSettleStatus("月结成功！");
		monthlySettleRecordBean.saveEntity(settleRecord);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMonthDate(Date monthDate) {
		this.monthDate = monthDate;
	}

	public List<String> getMonthLst() {
		return monthLst;
	}

	public String getOperatingTime() {
		return DateTimeUtil.getDateStr(operatingTime);
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public MonthlySettleRecord getSettleRecord() {
		return settleRecord;
	}

	public void setSettleRecord(MonthlySettleRecord settleRecord) {
		this.settleRecord = settleRecord;
	}

	public Integer getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Integer closeDate) {
		this.closeDate = closeDate;
	}

	public void setMonthlySettleRecordBean(MonthlySettleRecordBean monthlySettleRecordBean) {
		this.monthlySettleRecordBean = monthlySettleRecordBean;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setLastMonth(Date lastMonth) {
		this.lastMonth = lastMonth;
	}

	public String getLastMonth() {
		return DateTimeUtil.getDateStr(lastMonth);
	}

	public String getNowMonth() {
		return nowMonth;
	}

	public void setNowMonth(String nowMonth) {
		this.nowMonth = nowMonth;
	}

	public String getMonthDate() {
		return DateTimeUtil.getDateStr(monthDate);
	}

}
