/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 17, 2014 by liyzh
 * Last edited on Dec 17, 2014 by liyzh
 * 
 * 说明： 物流中心采购单管理，包括统配、直配、越库
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CollectRefFormBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingHeaderBean;
import logic.module.lc.PurchaseManageBean;
import logic.store.BranchBean;
import logic.store.PrintTimesBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PurchasingHeader;
import pojo.store.PrintTimes;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.business.module.lc.request.service.PurchaseService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchType;

public class PurchaseManageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private PurchaseManageBean purchaseManageBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private PurchaseService purchaseService;
	private FormStatusBean formStatusBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private String formId;
	private String itemId;
	private String deliveryType;
	private PurchasingHeader purchasingHeader;
	private CollectRefFormBean collectRefFormBean;
	private Date auditTime;
	private String jsonData;
	private Date businessDate;
	private Date startDate;
	private Date endDate;
	private String branchId;

	private String queryType;
	private String branchType;
	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;

	private PrintTimesBean printTimesBean;
	private String hasSum;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		endDate = businessDate == null ? DateTimeUtil.getNow() : DateTimeUtil.getMonthLastDay(businessDate);
		startDate = businessDate == null ? DateTimeUtil.getNow() : DateTimeUtil.getMonthFristDay(businessDate);
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = endDate = startDate = null;
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		auditTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();

		preVersion = operationVersionBean.queryVersion(formId).getVersion();
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		preVersion = operationVersionBean.queryVersion(formId).getVersion();
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String refView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void queryByStatus() throws NoPrivilegeException, SQLException, NoConnection {
		if ("-1".equals(branchId)) {
			branchId = "";// 统一非空条件
		}
		int total = purchasingHeaderBean.count(startDate, endDate, branchId, queryType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<PurchasingHeader> headerLst = purchasingHeaderBean.query(startDate, endDate, branchId, queryType);
			int rownumber = getStart();
			for (PurchasingHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String receiveTime = getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);
				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formStatus", formStatus);
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
	 * 显示物流中心所有采购单，以树形控件组织，日期-〉采购单
	 */
	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "采购单");
		arr.add(root);

		String branchId = getLoginBranchId();
		List<Map> nodeLst = new ArrayList<Map>();
		if (branchType.equals(BranchType.LOGISTICSCENTER)) {
			nodeLst = purchaseManageBean.queryTree(startDate, endDate, branchId, queryType);
		} else if (branchType.equals(BranchType.CENTRALFACTORY)) {
			nodeLst = purchaseManageBean.queryCFTree(startDate, endDate, branchId, queryType);
		} else {
			nodeLst = purchaseManageBean.queryTree(startDate, endDate, branchId, queryType);
		}
		for (Map node : nodeLst) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			String dtype = (String) node.get("dtype");
			if (FormConstant.DIRECT_DELIVERY.equals(dtype)) {
				jsonObject.put("type", "direct");
			} else if (FormConstant.CROSS_DELIVERY.equals(dtype)) {
				jsonObject.put("type", "cross");
			} else if (FormConstant.UNIFIED_DELIVERY.equals(dtype)) {
				jsonObject.put("type", "unified");
			}

			String status = (String) node.get("status");
			if (BillStatus.UNADUIT_CN.equals(status)) {
				jsonObject.put("status", BillStatus.UNADUIT_US);
			} else if (BillStatus.AUDITED_CN.equals(status)) {
				jsonObject.put("status", BillStatus.AUDITED_US);
			} else if (BillStatus.SETTLED_CN.equals(status)) {
				jsonObject.put("status", BillStatus.SETTLED_US);
			} else if (BillStatus.STORED_CN.equals(status)) {
				jsonObject.put("status", BillStatus.STORED_US);
			} else if (BillStatus.AGGREGATED_CN.equals(status)) {
				jsonObject.put("status", BillStatus.AGGREGATED_US);
			} else if (BillStatus.DISTRIBUTED_CN.equals(status)) {
				jsonObject.put("status", BillStatus.DISTRIBUTED_US);
			} else if (BillStatus.CANCELED.equals(status)) {
				jsonObject.put("status", "canceled");
			}
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryPurch() throws NoPrivilegeException, SQLException, NoConnection {
		if (formId == null) {
			formId = "";
		}
		int total = purchasingHeaderBean.count(formId);
		setTotal(total);

		JSONArray jsonArray = new JSONArray();
		if (total > 0) {
			int rownumber = getStart();
			List<PurchasingHeader> headerLst = purchasingHeaderBean.query(formId, getStart(), getEnd());
			for (PurchasingHeader item : headerLst) {
				JSONObject jObject = JSONObject.fromObject(item);
				jObject.put("rownumber", rownumber);
				jObject.put("formTime", DateTimeUtil.getDateStr(item.getFormTime()));
				jsonArray.add(jObject);
				rownumber++;
			}

		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询直配大小单汇总信息
	 */
	public void queryDirect() throws NoPrivilegeException, SQLException, NoConnection {
		String lcCode = getLoginBranchId();
		JSONObject result = purchaseService.queryDirect(formId, lcCode);
		String status = formStatusBean.getCurrentStatus(formId);
		result.put("status", status);
		result.put("formRefId", collectRefFormBean.queryDirectRefs(formId));
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示采购单信息，主从表信息一次性查出来，包括前置单据信息
	 */
	public void queryBill() throws NoPrivilegeException, SQLException, NoConnection {
		String lcCode = getLoginBranchId();
		JSONObject result = purchaseService.queryBill(formId, lcCode, hasSum);
		String status = formStatusBean.getCurrentStatus(formId);
		result.put("status", status);
		result.put("formRefId", collectRefFormBean.queryRefs(formId));
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 审核单个采购单单据
	 */
	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();// 获得当前门店，用于mrp中保存门店id
		purchasingHeader.setRequesterId(branchId);
		purchaseService.auditBill(getLoginUserId(), purchasingHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新单个采购单单据
	 */
	public void doUpdate() throws NoPrivilegeException, SQLException, NoConnection {
		purchaseService.updateBill(getLoginUserId(), purchasingHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		purchaseService.deleteBill(formId, getLoginUserId(), currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryLittleFormIds() throws NoPrivilegeException, SQLException, NoConnection {
		try {
			this.outJS(JSONArray.fromObject(purchaseManageBean.queryLittleFormIds(formId)).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doInvalid() throws NoPrivilegeException, SQLException, NoConnection {
		purchaseService.invalidBill(formId, getLoginUserId(), currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 采购单打印，并显示打印次数
	 * 
	 * @return
	 * */
	public String preview() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			JSONObject result = new JSONObject();
			result.put("msg", "ok");

			// 更新打印次数
			printTimesBean.updateEntity(new PrintTimes(formId));

			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	/**
	 * 批量审核采购单
	 */
	public void batchAudit() throws NoPrivilegeException, SQLException, NoConnection {
		PurchasingHeader header = purchasingHeaderBean.queryById(formId);
		List<Map> detailLst = purchaseManageBean.queryDirect(formId);

		JSONObject result = new JSONObject();
		JSONObject jHeader = JSONObject.fromObject(header);
		jHeader.put("receiveTime", DateTimeUtil.getDateStr(header.getReceiveTime()));
		jHeader.put("formTime", DateTimeUtil.getDateStr(header.getFormTime()));
		jHeader.put("auditTime", DateTimeUtil.getDateStr(header.getAuditTime()));
		result.put("header", jHeader);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		result.put("detailLst", arr);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchaseManageBean(PurchaseManageBean purchaseManageBean) {
		this.purchaseManageBean = purchaseManageBean;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public PurchasingHeader getPurchasingHeader() {
		return purchasingHeader;
	}

	public void setPurchasingHeader(PurchasingHeader purchasingHeader) {
		this.purchasingHeader = purchasingHeader;
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setCollectRefFormBean(CollectRefFormBean collectRefFormBean) {
		this.collectRefFormBean = collectRefFormBean;
	}

	public void setHasSum(String hasSum) {
		this.hasSum = hasSum;
	}

	public void setPrintTimesBean(PrintTimesBean printTimesBean) {
		this.printTimesBean = printTimesBean;
	}

}
