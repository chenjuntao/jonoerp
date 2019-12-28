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
 * 说明： 物流中心装箱单查询、修改、删除、审核等
 */
package com.tanry.business.module.lc.stock.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.PackingDetailBean;
import logic.form.PackingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PackingDetail;
import pojo.form.PackingHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class PackingManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
	private PackingHeaderBean packingHeaderBean;
	private PackingDetailBean packingDetailBean;
	private Date businessDate;
	private Date startDate;
	private Date endDate;
	private String formId;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		endDate = DateTimeUtil.getMonthLastDay(businessDate);
		startDate = DateTimeUtil.getMonthFristDay(businessDate);
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	/**
	 * 查询装箱单，以树形控件组织，制单日期-〉装箱单
	 */
	public void queryTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "装箱单");
		arr.add(root);
		List<Map> nodeLst = packingHeaderBean.queryTree(startDate, endDate);
		for (Map node : nodeLst) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			String dtype = (String) node.get("dtype");
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据装箱单编号查询表头和表明细数据
	 */
	public void queryById() throws NoPrivilegeException, SQLException, NoConnection {
		PackingHeader header = packingHeaderBean.queryById(formId);
		List<PackingDetail> detailLst = packingDetailBean.query(formId);

		JSONObject jHeader = JSONObject.fromObject(header);
		jHeader.put("formTime", DateTimeUtil.getDateStr(header.getFormTime()));
		String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		jHeader.put("formTimeActual", formTimeActual);
		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (PackingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		JSONObject result = new JSONObject();
		result.put("header", jHeader);
		result.put("detailLst", arr);
		result.put("msg", "ok");
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
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
	
	public void setPackingHeaderBean(PackingHeaderBean packingHeaderBean) {
		this.packingHeaderBean = packingHeaderBean;
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

	public void setPackingDetailBean(PackingDetailBean packingDetailBean) {
		this.packingDetailBean = packingDetailBean;
	}
}
