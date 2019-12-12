/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 6, 2015 by liyzh
 * Last edited on Jan 6, 2015 by liyzh
 * 
 * 说明： 中央工厂产品入库管理（与对外部供应商下的采购单入库不是一回事）
 */
package com.tanry.business.module.cf.stock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputDetailBean;
import logic.form.InputHeaderBean;
import logic.form.RequisitionHeaderBean;
import logic.form.StorageInOutBean;
import logic.form.WorkOrderHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.FormStatus;
import pojo.form.InputDetail;
import pojo.form.InputHeader;
import pojo.form.StorageInOut;
import pojo.form.WorkOrderHeader;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.form.StatusConst;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class StockInAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchStorageBean branchStorageBean;
	private FormStatusBean formStatusBean;
	private StorageInOutBean storageInOutBean;
	private RequisitionHeaderBean requisitionHeaderBean;
	private InputHeaderBean inputHeaderBean;
	private StorageBean storageBean;
	private InputHeader inputHeader;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private String formId;
	private List<BranchStorage> storeLst;

	private String nextTag;
	private String jsonData;
	private String formRefId;
	private String formType;
	private String queryType;
	private String storageId;

	private WorkOrderHeaderBean workOrderHeaderBean;
	private InputDetailBean inputDetailBean;

	private Date businessDate;
	private Date startDate;
	private Date endDate;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			startDate = endDate = businessDate = null;
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		storeLst = branchStorageBean.query(getLoginBranchId(), BranchType.LOGISTICSCENTER);
		return SUCCESS;
	}

	/**
	 * 根据工单号判断退料审核情况未完成/已完成
	 */
	public void checkFinish() throws NoPrivilegeException, SQLException, NoConnection {
		boolean finished = false;

		int total = requisitionHeaderBean.count(queryType, formRefId);
		if (total == 0) {
			finished = true;
		}
		JSONObject result = new JSONObject();
		result.put("finished", finished);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		String lcCode = getLoginBranchId();
		Date businessDate = branchBean.GetBranchById(lcCode).getBusinessDate();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;

			InputDetail inputDetail = new InputDetail();

			Double orderCount = json.getDouble("orderCount");
			Double receiveCount = json.getDouble("receiveCount");
			Double receivedCount = json.getDouble("receivedCount");

			Double sum = receiveCount + receivedCount;
			inputDetail.setReceivedCount(sum);
			String formId = json.getString("formId");
			inputDetail.setFormId(formId);
			String itemId = json.getString("itemId");
			inputDetail.setItemId(itemId);
			inputDetail.setPayAmt(json.getDouble("payAmt"));
			inputDetail.setReceiveAmt(json.getDouble("receiveAmt"));

			inputDetail.setDifferentCount(orderCount - sum);
			inputDetail.setReceiveCount(receiveCount);
			inputDetail.setReceivedCount(receivedCount);
			inputDetailBean.updateEntity(inputDetail);

			// 操作时间
			Date operationTime = DateTimeUtil.getNow();
			Double unitPrice = json.getDouble("itemUnitPrice");
			Double itemCountIn = inputDetail.getReceiveCount();
			Double itemCountOut = 0.0;
			Double orgiCount = storageBean.getItemCount(itemId, storageId);
			Double resultCount = (orgiCount != null ? orgiCount : 0) + itemCountIn;
			String reason = "CPRK";
			StorageInOut storageInOut = new StorageInOut(lcCode, storageId, businessDate, operationTime, itemId,
					unitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, formId, reason);

			storageInOutBean.saveEntity(storageInOut);
			storageBean.updateItemCount(inputDetail.getItemId(), storageId, itemCountIn);
			if (!TextUtil.isEmpty(nextTag) && (sum + receivedCount < orderCount)) {
				// 允许下次入库
			} else {
				formStatusBean.saveEntity(new FormStatus(formRefId, StatusConst.CLOSED, lcCode));
			}

			WorkOrderHeader workOrderHeader = new WorkOrderHeader();
			workOrderHeader.setFormId(formRefId);
			workOrderHeader.setInputedCount(sum);

			workOrderHeaderBean.updateInputCount(workOrderHeader);

		}
		inputHeaderBean.audit(formId, getLoginUserId(), businessDate);
		formStatusBean.saveEntity(new FormStatus(formId, StatusConst.AUDITED, lcCode));

		JSONObject result = new JSONObject();
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

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setNextTag(String nextTag) {
		this.nextTag = nextTag;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public void setInputDetailBean(InputDetailBean inputDetailBean) {
		this.inputDetailBean = inputDetailBean;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setRequisitionHeaderBean(RequisitionHeaderBean requisitionHeaderBean) {
		this.requisitionHeaderBean = requisitionHeaderBean;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

}
