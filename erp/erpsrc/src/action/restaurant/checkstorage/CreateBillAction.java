/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 28, 2014 by liyzh
 * Last edited on Sep 28, 2014 by liyzh
 * 
 * 说明： 餐厅盘点清单生成
 */
package action.restaurant.checkstorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckDetailBean;
import logic.form.CheckLockBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CheckDetail;
import pojo.form.CheckHeader;
import pojo.form.CheckLock;
import service.restaurant.checkstorage.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BillManageService csBillManageService;
	private CheckDetailBean checkDetailBean;
	private CheckLockBean checkLockBean;

	private List<Map> shopLst;
	private String branchId;
	private String branchType;

	private String formId;
	private String batchId;
	private String storageId;

	private CheckHeader checkHeader;
	private Date formTime;
	private String checkCategory;

	private String jsonData;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {

		branchId = checkHeader.getCheckBranchId();
		formTime = branchBean.GetBranchById(branchId).getBusinessDate();
		CheckLock batch = checkLockBean.queryById(checkHeader.getCheckBatchId());
		checkHeader.setFormId(formId);

		checkHeader.setCheckStorageId(batch.getLockStorageId());
		checkHeader.setCheckStorage(batch.getLockStorage());
		checkHeader.setFormMakerId(getLoginUserId());
		checkHeader.setFormMaker(getLoginUsername());

		// 销售扣仓
		String branchId = getLoginBranchId();
		csBillManageService.SettleStorage(branchId, batch.getLockStorageId(),
				branchBean.GetBranchById(branchId).getBusinessDate());
		return SUCCESS;
	}

	/**
	 * 餐厅盘点 1、根据批次查询盘点清单明细，分组求和，同时关联查询相应的理论库存，用于计算生成盘点单
	 * 2、根据批次限定的原料类别，检查漏盘原料，标志状态
	 */
	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<CheckDetail> detailLst;
		if (branchType.equals(BranchType.RESTAURANT)) {
			detailLst = checkDetailBean.queryByBatch(batchId, storageId);
		} else {
			detailLst = checkDetailBean.queryLc(batchId, storageId);
		}

		int rownumber = 1;
		for (CheckDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
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

	/**
	 * 生成盘点单
	 */
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String userId = getLoginUserId();
		formId = csBillManageService.createCheckBill(userId, checkHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("formId", formId);
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

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public CheckHeader getCheckHeader() {
		return checkHeader;
	}

	public void setCheckHeader(CheckHeader checkHeader) {
		this.checkHeader = checkHeader;
	}

	public void setCheckDetailBean(CheckDetailBean checkDetailBean) {
		this.checkDetailBean = checkDetailBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setCsBillManageService(BillManageService csBillManageService) {
		this.csBillManageService = csBillManageService;
	}

	public String getCheckCategory() {
		return checkCategory;
	}

	public void setCheckCategory(String checkCategory) {
		this.checkCategory = checkCategory;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setCheckLockBean(CheckLockBean checkLockBean) {
		this.checkLockBean = checkLockBean;
	}

}
