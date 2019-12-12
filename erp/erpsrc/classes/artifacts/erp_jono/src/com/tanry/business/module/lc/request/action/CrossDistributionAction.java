/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 9, 2015 by liyzh
 * Last edited on Jan 9, 2015 by liyzh
 * 
 * 说明： 越库配送单审核
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ShippingHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ShippingHeader;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.form.FormType;
import com.tanry.business.module.lc.request.service.DistributionService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.LogType;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class CrossDistributionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private DistributionService lcDistributionService;
	private ShippingHeaderBean shippingHeaderBean;
	private BranchStorageBean branchStorageBean;

	private String branchId;
	private String branchName;
	private Date startDate;
	private Date endDate;

	private String formId;
	private String formRefId;

	private ShippingHeader shippingHeader;

	private String jsonData;
	private List<Map> shopLst;
	private List<BranchStorage> storeLst;

	private Date formTime;
	private Date auditTime;
	private Date receiveTime;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			endDate = startDate = null;
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		shippingHeader = shippingHeaderBean.queryById(formId);

		receiveTime = shippingHeader.getReceiveTime();// 默认配送日期为要货单的到货日期
		formTime = shippingHeader.getFormTime();
		auditTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 物流中心的营业日期
		shippingHeader.setAuditorId(getLoginUserId());
		shippingHeader.setAuditor(getLoginUsername());
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = shippingHeaderBean.countByCross(startDate, endDate, branchId, "unaudit", FormType.DISTRI_CROSS,
				null, null);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ShippingHeader> headerLst = shippingHeaderBean.queryByCross(startDate, endDate, branchId, "unaudit",
					FormType.DISTRI_CROSS, null, null);
			int rownumber = getStart();
			for (ShippingHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				// 配送日期
				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);

				// 制单日期
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);
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

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		String formId = shippingHeader.getFormId();
		ShippingHeader header = shippingHeaderBean.queryById(formId);
		String theId = header.getProviderId();
		header.setAuditor(shippingHeader.getAuditor());
		header.setAuditorId(shippingHeader.getAuditorId());
		header.setAuditTime(shippingHeader.getAuditTime());
		BranchStorage branchStorage = branchStorageBean.queryMainStore(theId);
		header.setOutStorageId(branchStorage.getStorageId());
		header.setOutStorage(branchStorage.getStorageName());

		// 出库单审核影响进销存
		lcDistributionService.auditBill(getLoginUserId(), header, jsonData);

		OperateLogUtil.record(formId, LogType.AUDIT, "审核越库配送单");
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public void setLcDistributionService(DistributionService lcDistributionService) {
		this.lcDistributionService = lcDistributionService;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public BranchStorageBean getBranchStorageBean() {
		return branchStorageBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}
}
