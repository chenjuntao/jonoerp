/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 10, 2014 by liyzh
 * Last edited on Dec 10, 2014 by liyzh
 * 
 * 说明： 物流中心拣货单查询、修改、删除、审核等
 */
package com.tanry.business.module.lc.stock.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PickingDetailBean;
import logic.form.PickingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PickingDetail;
import pojo.form.PickingHeader;
import action.common.BaseAction;

import com.tanry.business.module.lc.stock.service.PickingService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class PickingManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
	private PickingHeaderBean pickingHeaderBean;
	private PickingDetailBean pickingDetailBean;
	private FormStatusBean formStatusBean;

	private PickingService pickingService;
	private Date businessDate;

	private PickingHeader pickingHeader;
	private String branchIds;
	private String ids;
	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;

	private String jsonData;

	private String formId;
	private String queryType;
	private Date startDate;
	private Date endDate;
	private String itemName;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		endDate = DateTimeUtil.getMonthLastDay(businessDate);
		startDate = DateTimeUtil.getMonthFristDay(businessDate);
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = endDate = startDate = null;
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		pickingHeader = pickingHeaderBean.queryById(formId);
		preVersion = operationVersionBean.queryVersion(formId).getVersion();
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	/**
	 * 查询拣货单，以树形控件组织，制单日期-〉拣货单
	 */
	public void queryTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "采购单");
		arr.add(root);
		List<Map> nodeLst = !TextUtil.isEmpty(itemName) ? pickingHeaderBean.queryTree(startDate, endDate, queryType,
				itemName) : pickingHeaderBean.queryTree(startDate, endDate, queryType);
		for (Map node : nodeLst) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			String status = (String) node.get("status");
			if ("已审核".equals(status)) {
				jsonObject.put("status", "audit");
			} else if ("未审核".equals(status)) {
				jsonObject.put("status", "unaudit");
			}
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void auditTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "采购单");
		arr.add(root);
		List<Map> nodeLst = pickingHeaderBean.auditTree(startDate, endDate, queryType);
		for (Map node : nodeLst) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			String status = (String) node.get("status");
			if ("已审核".equals(status)) {
				jsonObject.put("status", "audit");
			} else if ("未审核".equals(status)) {
				jsonObject.put("status", "unaudit");
			}
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据拣货单查询表头和表明细数据
	 */
	public void queryById() throws NoPrivilegeException, SQLException, NoConnection {
		PickingHeader header = pickingHeaderBean.queryById(formId);
		List<PickingDetail> detailLst = pickingDetailBean.query(formId);

		String status = formStatusBean.getCurrentStatus(formId);

		JSONObject jHeader = JSONObject.fromObject(header);
		jHeader.put("formTime", DateTimeUtil.getDateStr(header.getFormTime()));
		String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		jHeader.put("formTimeActual", formTimeActual);
		jHeader.put("auditTime", DateTimeUtil.getDateStr(header.getAuditTime()));

		JSONObject result = groupDept(detailLst);
		result.put("header", jHeader);
		result.put("msg", "ok");
		result.put("status", status);
		try {
			this.outJS(result.toString());
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

	private JSONObject groupDept(List<PickingDetail> itemLst) {
		Map<String, Map<String, Object>> branchMap = new HashMap<String, Map<String, Object>>();
		List<Map<String, Object>> branchLst = new ArrayList<Map<String, Object>>();
		for (PickingDetail item : itemLst) {
			String branchId = item.getBranchId();
			String key = branchId;
			if (branchMap.get(key) == null) {
				Map<String, Object> branch = new HashMap<String, Object>();
				branch.put("branchId", branchId);
				branch.put("branchName", item.getBranchName());
				branch.put("key", key);
				branchLst.add(branch);
				branchMap.put(key, branch);
			}
		}
		class BranchComparator implements Comparator<Map> {
			@Override
			public int compare(Map a, Map b) {
				String keya = (String) a.get("key");
				String keyb = (String) b.get("key");
				return keya.compareToIgnoreCase(keyb);
			}
		}
		Collections.sort(branchLst, new BranchComparator());

		List<String> branchArr = new ArrayList<String>();
		JSONArray branchCols = new JSONArray();
		for (Map<String, Object> branch : branchLst) {
			branchArr.add((String) branch.get("key"));
			JSONObject col = new JSONObject();
			col.put("label", branch.get("branchName"));
			col.put("field", branch.get("key"));
			col.put("className", "grid-number");
			branchCols.add(col);
		}

		List<Map<String, Object>> itemLst2 = new ArrayList<Map<String, Object>>();
		Map<String, Map<String, Object>> itemMap = new HashMap<String, Map<String, Object>>();
		int rownumber = getStart();
		for (PickingDetail item : itemLst) {
			String itemId = (String) item.getItemId();
			String key = itemId;
			if (itemMap.get(key) == null) {
				Map<String, Object> detail = new HashMap<String, Object>();
				detail.put("shelfId", item.getShelfId());
				detail.put("shelfName", item.getShelfName());
				detail.put("itemId", itemId);
				detail.put("itemName", item.getItemName());
				detail.put("sumCount", item.getSumCount());
				detail.put("storageCount", item.getStorageCount());
				detail.put("itemDimension", item.getItemDimension());
				detail.put("itemCategory", item.getItemCategory());
				detail.put("itemSpecification", item.getItemSpecification());
				detail.put("key", key);
				detail.put("rownumber", rownumber);
				itemLst2.add(detail);
				itemMap.put(key, detail);
				rownumber++;
			}
			itemMap.get(key).put(item.getBranchId(), item.getItemCount());// 根据门店存入相应的列数据
		}

		JSONObject result = new JSONObject();
		result.put("branchCols", branchCols);
		result.put("branchIds", branchArr);
		result.put("itemLst", itemLst2);
		result.put("msg", "ok");
		return result;
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		pickingService.auditBill(getLoginUserId(), pickingHeader, branchIds, jsonData);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		pickingService.updateBill(pickingHeader, branchIds, jsonData);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void batchAudit() throws NoPrivilegeException, SQLException, NoConnection {
		pickingService.batchAudit(getLoginUserId(), ids);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		pickingService.deleteBill(formId, getLoginUserId(), currentVersion);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doInvalid() throws NoPrivilegeException, SQLException, NoConnection {
		pickingService.invalidBill(formId, getLoginUserId(), currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setPickingService(PickingService pickingService) {
		this.pickingService = pickingService;
	}

	public PickingHeader getPickingHeader() {
		return pickingHeader;
	}

	public void setPickingHeader(PickingHeader pickingHeader) {
		this.pickingHeader = pickingHeader;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public void setPickingHeaderBean(PickingHeaderBean pickingHeaderBean) {
		this.pickingHeaderBean = pickingHeaderBean;
	}

	public void setPickingDetailBean(PickingDetailBean pickingDetailBean) {
		this.pickingDetailBean = pickingDetailBean;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Integer getPreVersion() {
		return preVersion;
	}

	public void setPreVersion(Integer preVersion) {
		this.preVersion = preVersion;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
