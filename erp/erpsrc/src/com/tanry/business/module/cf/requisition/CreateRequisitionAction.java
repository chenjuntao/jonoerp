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
 * 说明： 领料单生成
 */
package com.tanry.business.module.cf.requisition;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.RequisitionBatchExportBean;
import logic.form.RequisitionDetailBean;
import logic.form.WorkOrderHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.FactoryWorkShopBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.RequisitionDetail;
import pojo.form.RequisitionHeader;
import pojo.form.WorkOrderHeader;
import pojo.store.BranchStorage;
import pojo.store.FactoryWorkShop;
import service.centralfactory.productionDemand.RequisitionService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class CreateRequisitionAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;

	private String jsonData;

	private String formId;
	private Date businessDate;
	private String formTime;
	private String formType;

	private WorkOrderHeaderBean workOrderHeaderBean;
	private RequisitionDetailBean requisitionDetailBean;
	private RequisitionService requisitionService;
	private BranchStorageBean branchStorageBean;
	private FactoryWorkShopBean factoryWorkShopBean;
	private FactoryWorkShop factoryWorkShop;
	private RequisitionHeader requisitionHeader;

	private String queryStr;
	private Integer currentVersion;
	private String type;
	private String itemName;
	private String workshop;
	private List<BranchStorage> storeLst;
	private List<FactoryWorkShop> workshopLst;

	private RequisitionBatchExportBean requisitionBatchExportBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		storeLst = branchStorageBean.query(cfCode, BranchType.CENTRALFACTORY);
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();
		workshopLst = factoryWorkShopBean.queryById(cfCode);
		if (workshopLst == null) {
			workshopLst = new ArrayList<FactoryWorkShop>();
			factoryWorkShop = new FactoryWorkShop();
			factoryWorkShop.setWorkshop("管理员仓库");
			workshopLst.add(factoryWorkShop);
		}
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		List<BranchStorage> branchStorages = branchStorageBean.query(cfCode, BranchType.CENTRALFACTORY);

		requisitionHeader = new RequisitionHeader();

		WorkOrderHeader workOrderHeader = workOrderHeaderBean.queryById(formId);
		requisitionHeader.setWorkshop(workOrderHeader.getWorkshop());
		if (branchStorages.size() > 0) {
			requisitionHeader.setStorage(branchStorages.get(0).getStorageName());
			requisitionHeader.setStorageId(branchStorages.get(0).getStorageId());
		}
		requisitionHeader.setItemName("[" + workOrderHeader.getItemId() + "]" + workOrderHeader.getItemName());

		formTime = DateTimeUtil.getDateStr(branchBean.GetBranchById(cfCode).getBusinessDate());

		requisitionHeader.setFormMaker(getLoginUsername());
		requisitionHeader.setFormMakerId(getLoginUserId());

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (!"".equals(itemName) && itemName != null) {
			itemName = "%" + itemName + "%";
			queryStr = queryStr + " AND (d.ITEM_ID LIKE '" + itemName + "' OR  d.ITEM_NAME LIKE '" + itemName + "')";
		}
		int total = workOrderHeaderBean.count(startDate, endDate, queryStr);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<WorkOrderHeader> headerLst = workOrderHeaderBean.query(startDate, endDate, queryStr);

			int rownumber = getStart();
			for (WorkOrderHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				json.put("rownumber", rownumber);

				int count = requisitionDetailBean.count(header.getFormId());
				json.put("count", count);
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);
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

	public void doBatchQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (!"".equals(itemName) && itemName != null) {
			itemName = "%" + itemName + "%";
			queryStr = queryStr + " AND (d.ITEM_ID LIKE '" + itemName + "' OR  d.ITEM_NAME LIKE '" + itemName + "')";
		}

		if (TextUtil.isEmpty(workshop)) {
			String cfCode = getLoginBranchId();
			factoryWorkShop = factoryWorkShopBean.queryByOrder(cfCode, 0);
			workshop = factoryWorkShop.getWorkshop();
		}
		queryStr = queryStr + " AND h.workshop ='" + workshop + "' AND h.BATCH_FLAG IS NULL";
		int total = workOrderHeaderBean.count(startDate, endDate, queryStr);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<WorkOrderHeader> headerLst = workOrderHeaderBean.query(startDate, endDate, queryStr);
			int rownumber = getStart();
			for (WorkOrderHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				json.put("rownumber", rownumber);

				int count = requisitionDetailBean.count(header.getFormId());
				json.put("count", count);
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);
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

	@SuppressWarnings("rawtypes")
	public void doBatchExportQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = requisitionBatchExportBean.count(startDate, endDate, workshop);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<Map> headerLst = requisitionBatchExportBean.query(startDate, endDate, workshop);
			int rownumber = getStart();
			for (Map tempMap : headerLst) {
				JSONObject json = JSONObject.fromObject(tempMap);
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

	/* 批量生成领料单 */
	public void doBatch() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		List<BranchStorage> branchStorages = branchStorageBean.query(cfCode, BranchType.CENTRALFACTORY);

		requisitionHeader = new RequisitionHeader();

		if (branchStorages.size() > 0) {
			requisitionHeader.setStorage(branchStorages.get(0).getStorageName());
			requisitionHeader.setStorageId(branchStorages.get(0).getStorageId());
		}

		Date formTime = branchBean.GetBranchById(cfCode).getBusinessDate();
		requisitionHeader.setFormTime(formTime);
		requisitionHeader.setFormBranchId(getLoginBranchId());
		requisitionHeader.setFormMaker(getLoginUsername());
		requisitionHeader.setFormMakerId(getLoginUserId());
		requisitionHeader.setFormType(formType);
		requisitionService.doBatch(getLoginUserId(), getLoginUsername(), requisitionHeader, jsonData);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void transformToDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<RequisitionDetail> headerLst = requisitionDetailBean.transformToDetail(formId, queryStr);
		int rownumber = getStart();
		for (RequisitionDetail header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			double num = header.getItemCount() - header.getReceiveCount() - header.getReceivedCount();
			json.put("differentCount", num);
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

	public void getCounts() throws NoPrivilegeException, SQLException, NoConnection {
		int count = workOrderHeaderBean.getCounts(formId, type);

		JSONObject result = new JSONObject();
		result.put("count", count);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		if (formTime != null) {
			requisitionHeader.setFormTime(DateTimeUtil.parse(formTime));
		}
		BranchStorage branchStorage = branchStorageBean.queryByStorageId(requisitionHeader.getStorageId());
		if (branchStorage != null) {
			requisitionHeader.setStorage(branchStorage.getStorageName());
		}
		requisitionHeader.setFormBranchId(getLoginBranchId());
		requisitionService.doCommit(getLoginUserId(), getLoginUsername(), requisitionHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		requisitionService.updateBill(requisitionHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		requisitionService.deleteBill(formId, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public RequisitionHeader getRequisitionHeader() {
		return requisitionHeader;
	}

	public void setRequisitionHeader(RequisitionHeader requisitionHeader) {
		this.requisitionHeader = requisitionHeader;
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

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public void setRequisitionDetailBean(RequisitionDetailBean requisitionDetailBean) {
		this.requisitionDetailBean = requisitionDetailBean;
	}

	public void setRequisitionService(RequisitionService requisitionService) {
		this.requisitionService = requisitionService;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public void setFactoryWorkShopBean(FactoryWorkShopBean factoryWorkShopBean) {
		this.factoryWorkShopBean = factoryWorkShopBean;
	}

	public void setFactoryWorkShop(FactoryWorkShop factoryWorkShop) {
		this.factoryWorkShop = factoryWorkShop;
	}

	public List<FactoryWorkShop> getWorkshopLst() {
		return workshopLst;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setRequisitionBatchExportBean(RequisitionBatchExportBean requisitionBatchExportBean) {
		this.requisitionBatchExportBean = requisitionBatchExportBean;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setStoreLst(List<BranchStorage> storeLst) {
		this.storeLst = storeLst;
	}

	public void setBranchStorage(BranchStorage branchStorage) {
	}

}
