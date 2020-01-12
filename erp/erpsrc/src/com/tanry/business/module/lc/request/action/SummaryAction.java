/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 6, 2014 by liyzh
 * Last edited on Nov 6, 2014 by liyzh
 * 
 * 说明： 物流中心需求汇总
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.lc.RequestSummaryBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.OptionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PurchasingHeader;
import pojo.form.RequestHeader;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.business.module.lc.request.service.OrderSummaryService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class SummaryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchStorageBean branchStorageBean;
	private RequestSummaryBean lcRequestSummaryBean;
	private OptionBean optionBean;
	private OrderSummaryService orderSummaryService;
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private String formId;
	private String ids;
	private Date businessDate;
	private Date startDate;
	private Date endDate;
	private Date formTime;
	private Date receiveTime;
	private Date refDateStart1;
	private Date refDateEnd1;
	private Date refDateStart2;
	private Date refDateEnd2;
	private Date refDateStart3;
	private Date refDateEnd3;

	private String deliveryType;

	private PurchasingHeader commonHeader;

	private String jsonData;
	private Map<String, Object> summary;
	private List<BranchStorage> storeLst;
	private boolean busFlag;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		String flag = "";
		try {
			flag = optionBean.getOption("SAFE_STORAGE");
		} catch (Exception e) {
			flag = "";
		}

		busFlag = false;

		if (!TextUtil.isEmpty(flag) && flag.toUpperCase().equals("STATIC")) {
			busFlag = true;
		}
		return SUCCESS;
	}

	public String estimateView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = businessDate;
		return SUCCESS;
	}

	@Deprecated
	public String aggregateView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = businessDate;
		commonHeader = new PurchasingHeader();
		commonHeader.setFormId(formId);

		String cfCode = getLoginBranchId();
		commonHeader.setRequesterId(cfCode);
		commonHeader.setFormMakerId(getLoginUserId());
		commonHeader.setFormMaker(getLoginUsername());

		summary = orderSummaryService.querySummaryBill(getLoginBranchId(), businessDate, ids, jsonData);

		storeLst = branchStorageBean.query(cfCode, BranchType.LOGISTICSCENTER);

		receiveTime = lcRequestSummaryBean.queryReceiveTime(ids);
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = businessDate;
		commonHeader = new PurchasingHeader();
		commonHeader.setFormId(formId);
		commonHeader.setRequesterId(getLoginBranchId());
		commonHeader.setFormMakerId(getLoginUserId());
		commonHeader.setFormMaker(getLoginUsername());

		String lcCode = getLoginBranchId();
		summary = orderSummaryService.querySummaryBill(lcCode, businessDate, ids, jsonData);

		storeLst = branchStorageBean.query(lcCode, null);
		return SUCCESS;
	}

	/**
	 * 同时查询餐厅要货、外部订货
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		String lcCode = getLoginBranchId();
		List<RequestHeader> requestLst = lcRequestSummaryBean.query(lcCode, startDate, endDate,
				FormConstant.REQUEST_TEMPLATE, deliveryType);

		List<RequestHeader> outerLst = lcRequestSummaryBean.query(lcCode, startDate, endDate,
				FormConstant.OUTER_TEMPLATE, deliveryType);
		JSONObject result = new JSONObject();
		result.put("request", formJson(requestLst));
		result.put("outer", formJson(outerLst));
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据选择的单据号查询MPR计算所需的各个值
	 */
	public void queryMRP() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = lcRequestSummaryBean.queryMRP(getLoginBranchId(), ids);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			if (item.get("itemUnitPrice") == null) {
				json.put("itemUnitPrice", 0);
			}
			if (item.get("itemPrice") == null) {
				json.put("itemPrice", 0);
			}
			if (item.get("realCount") == null) {
				json.put("realCount", 0);
			}
			if (item.get("allocation") == null) {
				json.put("allocation", 0);
			}
			if (item.get("roadCount") == null) {
				json.put("roadCount", 0);
			}
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

	/**
	 * 查询日均要货量
	 */
	public void queryAverageRequest() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray itemLst = orderSummaryService.queryAverageRequest(refDateStart1, refDateEnd1, refDateStart2,
				refDateEnd2, refDateStart3, refDateEnd3, jsonData);

		int rownumber = 1;
		for (Object item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			rownumber++;
		}

		try {
			this.outJS(itemLst.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JSONArray formJson(List<RequestHeader> headerLst) {
		int rownumber = 1;
		JSONArray arr = new JSONArray();
		for (RequestHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);
			String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
			json.put("receiveTime", receiveTime);
			String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
			json.put("auditTime", auditTime);

			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		return arr;
	}

	/**
	 * 一次性创建统配、直配、越库单据，返回所有单据号
	 */
	public void createSummaryBill() throws NoPrivilegeException, SQLException, NoConnection {
		commonHeader.setRequesterId(getLoginBranchId());
		Map<String, List<String>> idMap = orderSummaryService.createSummaryBill(commonHeader, ids, jsonData);
		JSONObject result = new JSONObject();
		result.put("idMap", idMap);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setLcRequestSummaryBean(RequestSummaryBean lcRequestSummaryBean) {
		this.lcRequestSummaryBean = lcRequestSummaryBean;
	}

	public void setOrderSummaryService(OrderSummaryService orderSummaryService) {
		this.orderSummaryService = orderSummaryService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Map<String, Object> getSummary() {
		return summary;
	}

	public PurchasingHeader getCommonHeader() {
		return commonHeader;
	}

	public void setCommonHeader(PurchasingHeader commonHeader) {
		this.commonHeader = commonHeader;
	}

	public void setRefDateStart1(Date refDateStart1) {
		this.refDateStart1 = refDateStart1;
	}

	public void setRefDateEnd1(Date refDateEnd1) {
		this.refDateEnd1 = refDateEnd1;
	}

	public void setRefDateStart2(Date refDateStart2) {
		this.refDateStart2 = refDateStart2;
	}

	public void setRefDateEnd2(Date refDateEnd2) {
		this.refDateEnd2 = refDateEnd2;
	}

	public void setRefDateStart3(Date refDateStart3) {
		this.refDateStart3 = refDateStart3;
	}

	public void setRefDateEnd3(Date refDateEnd3) {
		this.refDateEnd3 = refDateEnd3;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public boolean isBusFlag() {
		return busFlag;
	}

	public void setBusFlag(boolean busFlag) {
		this.busFlag = busFlag;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}

}
