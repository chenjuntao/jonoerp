/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 22, 2014 by liyzh
 * Last edited on Dec 22, 2014 by liyzh
 * 
 * 说明：中央工厂盘点锁库管理
 */
package com.tanry.business.module.cf.stock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckHeaderBean;
import logic.form.CheckLockBean;
import logic.form.InputHeaderBean;
import logic.form.LossHeaderBean;
import logic.form.RequisitionHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CheckLock;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.restaurant.checkstorage.LockStorageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class LockStoreAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BranchStorageBean branchStorageBean;
	private InputHeaderBean inputHeaderBean;
	private LossHeaderBean lossHeaderBean;
	private CheckLockBean checkLockBean;
	private RequisitionHeaderBean requisitionHeaderBean;
	private LockStorageService lockStorageService;
	private CheckHeaderBean checkHeaderBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private Date lockDate;
	private Date lockTime;

	private String checkBatchId;
	private CheckLock lock;

	private List<Map> shopLst;
	private List<BranchStorage> storeLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		return SUCCESS;
	}

	public String lockView() throws NoPrivilegeException, SQLException, NoConnection {
		lockDate = branchBean.GetBranchById(branchId).getBusinessDate();

		lock = new CheckLock();
		lock.setLockBranchId(branchId);
		Branch branch = branchBean.GetBranchById(branchId);
		lock.setLockBranch(branch.getBranchName());

		lock.setLockManId(getLoginUserId());
		lock.setLockMan(getLoginUsername());
		lockTime = new Date();

		storeLst = branchStorageBean.query(getLoginBranchId(), BranchType.CENTRALFACTORY);
		return SUCCESS;
	}

	/**
	 * 检查是否还存在未审核或处理的单据，这些单据没有处理完原则上是不能盘点锁库的
	 */
	public void checkBill() throws NoPrivilegeException, SQLException, NoConnection {
		boolean finished = true;
		Integer total = inputHeaderBean.count(startDate, endDate, branchId, "unaudit", null);
		if (total > 0) {
			finished = false;
		} else {
			total = lossHeaderBean.countByStatus(startDate, endDate, branchId, "", "RAWLOSS", "ap",
					BranchType.CENTRALFACTORY);
			if (total > 0) {
				finished = false;
			} else {
				total = lossHeaderBean.countByStatus(startDate, endDate, branchId, "", "DISHLOSS", "ap",
						BranchType.CENTRALFACTORY);
				if (total > 0) {
					finished = false;
				} else {
					total = requisitionHeaderBean.count(startDate, endDate, "produce", "unreceive");
					if (total > 0) {
						finished = false;
					} else {
						total = requisitionHeaderBean.count(startDate, endDate, "manual", "unaudit");
						if (total > 0) {
							finished = false;
						} else {
							total = checkHeaderBean.count(startDate, endDate, branchId, "form", "unaudit");
							if (total > 0) {
								finished = false;
							} else {
								// total = antiauditHeaderBean.count(startDate,
								// endDate, branchId, null,
								// BillStatus.UNADUIT_US,
								// BranchType.CENTRALFACTORY);
								// if (total > 0) {
								// finished = false;
								// }
							}
						}
					}
				}
			}
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

	/**
	 * 根据餐厅查询盘点批次
	 */
	public void queryBatch() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> batchLst = checkLockBean.queryBatch(branchId);
		JSONArray arr = JSONArray.fromObject(batchLst);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断未完成/已完成（old:是否由盘点清单生成盘点单,new:盘点单是否已审核）
	 */
	public void checkFinish() throws NoPrivilegeException, SQLException, NoConnection {
		boolean finished = false;
		CheckLock batch = checkLockBean.queryById(checkBatchId);
		String status = batch.getCheckBatchStatus();
		if ("已完成".equals(status)) {
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

	/**
	 * 生成盘点锁库记录
	 */
	public void saveLock() throws NoPrivilegeException, SQLException, NoConnection {
		lockStorageService.saveLock(lock);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public String getCheckBatchId() {
		return checkBatchId;
	}

	public void setCheckBatchId(String checkBatchId) {
		this.checkBatchId = checkBatchId;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
	}

	public void setCheckLockBean(CheckLockBean checkLockBean) {
		this.checkLockBean = checkLockBean;
	}

	public void setLockStorageService(LockStorageService lockStorageService) {
		this.lockStorageService = lockStorageService;
	}

	public String getLockTime() {
		return DateTimeUtil.getDateStr(lockTime);
	}

	public String getLockDate() {
		return DateTimeUtil.getDateStr(lockDate);
	}

	public CheckLock getLock() {
		return lock;
	}

	public void setLock(CheckLock lock) {
		this.lock = lock;
	}

	public void setRequisitionHeaderBean(RequisitionHeaderBean requisitionHeaderBean) {
		this.requisitionHeaderBean = requisitionHeaderBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

}
