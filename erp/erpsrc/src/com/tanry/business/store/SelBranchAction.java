/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 2, 2015 by liyzh
 * Last edited on Sep 2, 2015 by liyzh
 * 
 * 说明： 选择所有物流中心、央厂、配送分组（含下属餐厅）
 */
package com.tanry.business.store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.BillPayBean;
import logic.module.hq.BranchQueryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;

public class SelBranchAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchQueryBean branchQueryBean;
	private BillPayBean billPayBean;
	private String month;// 月份

	// 查询所有物流中心、央厂、配送分组（含下属餐厅），树形结构
	public void queryAllTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有部门");
		arr.add(root);

		List<Map> branchLst = branchQueryBean.queryAllTree();
		int rownumber = getStart();
		for (Map branch : branchLst) {
			JSONObject json = JSONObject.fromObject(branch);
			if (!branch.get("parent").equals("root")) {
				json.put("branchType", BranchType.RESTAURANT);
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

	// 查询所有配送分组（含下属餐厅），树形结构
	public void queryGroupTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有部门");
		arr.add(root);

		List<Map> branchLst = branchQueryBean.queryGroupTree();
		int rownumber = getStart();
		for (Map branch : branchLst) {
			JSONObject json = JSONObject.fromObject(branch);
			if (!branch.get("parent").equals("root")) {
				json.put("branchType", BranchType.RESTAURANT);
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

	// 查询所有餐厅和加盟店，树形结构
	public void queryStore() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有餐厅");
		arr.add(root);

		List<Map> groupLst = branchQueryBean.queryStore();
		int rownumber = getStart();
		for (Map region : groupLst) {
			JSONObject json = JSONObject.fromObject(region);
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

	// 查询所有餐厅和加盟店（有营业日期的），树形结构
	public void queryTheStore() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有餐厅");
		arr.add(root);

		Date yearMonthDate = DateTimeUtil.parse(month, "yyyy-MM");
		Date startDate = DateTimeUtil.getMonthFristDay(yearMonthDate);
		Date endDate = DateTimeUtil.getMonthLastDay(yearMonthDate);

		List<Map> groupLst = branchQueryBean.queryStore();
		int rownumber = getStart();
		for (Map region : groupLst) {
			JSONObject json = JSONObject.fromObject(region);
			String branchId = (String) region.get("id");
			int count = billPayBean.count(startDate, endDate, branchId);
			if (count > 0) {
				json.put("disabled", false);
			} else {
				json.put("disabled", true);
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

	public void setBranchQueryBean(BranchQueryBean branchQueryBean) {
		this.branchQueryBean = branchQueryBean;
	}

	public void setBillPayBean(BillPayBean billPayBean) {
		this.billPayBean = billPayBean;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
