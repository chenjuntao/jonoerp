/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 16, 2014 by liyzh
 * Last edited on Sep 16, 2014 by liyzh
 * 
 * 说明：盘点锁库记录管理
 */
package action.restaurant.checkstorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckHeaderBean;
import logic.form.CheckLockBean;
import logic.form.FoodBillBean;
import logic.form.InputHeaderBean;
import logic.form.LossHeaderBean;
import logic.form.ShippingAntiauditHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.DailySettleRecordBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CheckLock;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.restaurant.checkstorage.LockStorageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchType;

public class LockStorageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private InputHeaderBean inputHeaderBean;
	private ShippingAntiauditHeaderBean antiauditHeaderBean;
	private LossHeaderBean lossHeaderBean;
	private CheckLockBean checkLockBean;
	private FoodBillBean foodBillBean;
	private LockStorageService lockStorageService;
	private DailySettleRecordBean dailySettleRecordBean;
	private CheckHeaderBean checkHeaderBean;

	private BranchStorageBean branchStorageBean;

	private Date businessDate;
	private Date startDate;
	private Date endDate;
	private String branchId;
	private Date lockDate;
	private Date lockTime;

	private String checkBatchId;
	private CheckLock lock;

	private String jsonData;
	private List<Map> shopLst;
	private List<BranchStorage> storeLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public String lockView() throws NoPrivilegeException, SQLException, NoConnection {
		lockDate = branchBean.GetBranchById(branchId).getBusinessDate();
		storeLst = branchStorageBean.query(getLoginBranchId(), BranchType.RESTAURANT);

		lock = new CheckLock();
		lock.setLockBranchId(branchId);
		Branch branch = branchBean.GetBranchById(branchId);
		lock.setLockBranch(branch.getBranchName());

		lock.setLockManId(getLoginUserId());
		lock.setLockMan(getLoginUsername());
		lockTime = new Date();
		return SUCCESS;
	}

	/**
	 * 检查是否还存在未审核或处理的单据，这些单据没有处理完原则上是不能盘点锁库的
	 */
	public void checkBill() throws NoPrivilegeException, SQLException, NoConnection {
		boolean finished = true;
		int total = inputHeaderBean.count(startDate, endDate, branchId, BillStatus.UNADUIT_US, null);
		if (total > 0) {
			finished = false;
		} else {
			total = antiauditHeaderBean.count(startDate, endDate, branchId, null, "unaudit", BranchType.RESTAURANT);
			if (total > 0) {
				finished = false;
			} else {
				// ap,audit&process 未审核,未处理
				total = lossHeaderBean.countByStatus(startDate, endDate, branchId, "", "RAWLOSS", "ap",
						BranchType.RESTAURANT);
				if (total > 0) {
					finished = false;
				} else {
					total = lossHeaderBean.countByStatus(startDate, endDate, branchId, "", "DISHLOSS", "ap",
							BranchType.RESTAURANT);
					if (total > 0) {
						finished = false;
					} else {
						total = checkHeaderBean.count(startDate, endDate, branchId, "form", "unaudit");
						if (total > 0) {
							finished = false;
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
	 * 通过检查当天营业数据，来判断天天系统是否日结
	 */
	public void checkSettle() throws NoPrivilegeException, SQLException, NoConnection {
		boolean finished = false;
		startDate = endDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();

		int cout = dailySettleRecordBean.queryCount(getLoginBranchId(), startDate);
		if (cout > 0) {
			finished = true;
		} else {
			int total = foodBillBean.count(startDate, endDate, getLoginBranchId());
			if (total > 0) {
				finished = true;
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
		String checkBatchId = lockStorageService.saveLock(lock);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("checkBatchId", checkBatchId);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public List<Map> getShopLst() {
		return shopLst;
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

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setAntiauditHeaderBean(ShippingAntiauditHeaderBean antiauditHeaderBean) {
		this.antiauditHeaderBean = antiauditHeaderBean;
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

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setFoodBillBean(FoodBillBean foodBillBean) {
		this.foodBillBean = foodBillBean;
	}

	public void setDailySettleRecordBean(DailySettleRecordBean dailySettleRecordBean) {
		this.dailySettleRecordBean = dailySettleRecordBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

}
