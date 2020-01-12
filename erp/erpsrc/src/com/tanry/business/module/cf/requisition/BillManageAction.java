/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 5, 2015 by liyzh
 * Last edited on Jan 5, 2015 by liyzh
 * 
 * 说明： 领料单管理
 */
package com.tanry.business.module.cf.requisition;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.RequisitionDetailBean;
import logic.form.RequisitionHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.FactoryWorkShopBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.RequisitionDetail;
import pojo.form.RequisitionHeader;
import pojo.store.BranchStorage;
import pojo.store.FactoryWorkShop;
import service.centralfactory.productionDemand.RequisitionService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class BillManageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private FormStatusBean formStatusBean;
	private RequisitionHeaderBean requisitionHeaderBean;
	private RequisitionDetailBean requisitionDetailBean;

	private String formId;
	private Date startDate;
	private Date endDate;
	private String formType;
	private String queryType;

	private RequisitionHeader requisitionHeader;
	private Date formTime;
	private Date auditTime;

	private String jsonData;
	private String withOutSum;
	private String status;
	private Integer preVersion;
	private Integer currentVersion;
	private List<BranchStorage> storeLst;
	private List<FactoryWorkShop> workshopLst;
	private FactoryWorkShopBean factoryWorkShopBean;
	private OperationVersionBean operationVersionBean;

	private BranchStorageBean branchStorageBean;
	private BranchBean branchBean;

	private RequisitionService requisitionService;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private Date businessDate;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		endDate = startDate = businessDate;
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			startDate = endDate = businessDate = null;
		if (isMobile()) {
			return "mobile";
		}

		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		setRequisitionHeader(requisitionHeaderBean.queryById(formId));
		preVersion = operationVersionBean.queryVersion(formId).getVersion();

		formTime = requisitionHeader.getFormTime();
		storeLst = branchStorageBean.query(cfCode, BranchType.CENTRALFACTORY);
		workshopLst = factoryWorkShopBean.queryById(cfCode);
		status = formStatusBean.getCurrentStatus(formId);
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();
			setRequisitionHeader(requisitionHeaderBean.queryById(formId));
			formTime = requisitionHeader.getFormTime();

		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		int total = requisitionHeaderBean.count(startDate, endDate, formType, queryType);
		setTotal(total);

		if (total > 0) {
			List<RequisitionHeader> headerLst = requisitionHeaderBean.query(startDate, endDate, getStart(), getEnd(),
					formType, queryType);
			int rownumber = getStart();
			for (RequisitionHeader header : headerLst) {
				JsonConfig config = new JsonConfig();
				config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
				JSONObject json = JSONObject.fromObject(header, config);
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}

		if (isMobile()) {
			JSONObject result = new JSONObject();
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("iTotalDisplayRecords", total);
			result.put("data", arr);// for DataTables
			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<RequisitionDetail> detailLst = requisitionDetailBean.query(formId);
		int rownumber = 1;
		for (RequisitionDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			if (detail.getItemCount() != null && detail.getReceiveCount() != null) {
				double differentCount = detail.getItemCount() - detail.getReceiveCount();
				if (differentCount < 0) {
					json.put("differentCount", 0);
				} else {
					json.put("differentCount", differentCount);
				}
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

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		requisitionService.doAudit(requisitionHeader, jsonData, getLoginBranchId(), getLoginUserId(),
				getLoginUsername());

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		requisitionService.doSave(requisitionHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		requisitionService.doDelete(requisitionHeader, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		requisitionService.doConfirm(requisitionHeader, jsonData, getLoginBranchId(), getLoginUserId(),
				getLoginUsername());
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public RequisitionHeader getRequisitionHeader() {
		return requisitionHeader;
	}

	public void setRequisitionHeader(RequisitionHeader requisitionHeader) {
		this.requisitionHeader = requisitionHeader;
	}

	public void setRequisitionHeaderBean(RequisitionHeaderBean requisitionHeaderBean) {
		this.requisitionHeaderBean = requisitionHeaderBean;
	}

	public void setRequisitionDetailBean(RequisitionDetailBean requisitionDetailBean) {
		this.requisitionDetailBean = requisitionDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getStatus() {
		return status;
	}

	public String getWithOutSum() {
		return withOutSum;
	}

	public void setWithOutSum(String withOutSum) {
		this.withOutSum = withOutSum;
	}

	public Integer getPreVersion() {
		return preVersion;
	}

	public void setPreVersion(Integer preVersion) {
		this.preVersion = preVersion;
	}

	public Integer getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setStoreLst(List<BranchStorage> storeLst) {
		this.storeLst = storeLst;
	}

	public List<FactoryWorkShop> getWorkshopLst() {
		return workshopLst;
	}

	public void setWorkshopLst(List<FactoryWorkShop> workshopLst) {
		this.workshopLst = workshopLst;
	}

	public void setFactoryWorkShopBean(FactoryWorkShopBean factoryWorkShopBean) {
		this.factoryWorkShopBean = factoryWorkShopBean;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setRequisitionService(RequisitionService requisitionService) {
		this.requisitionService = requisitionService;
	}

}
