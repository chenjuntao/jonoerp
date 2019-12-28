/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 *
 * All Rights Reserved.
 *
 * First created on Sep 16, 2014 by cjt
 * Last edited on Sep 16, 2014 by cjt
 *
 * 说明： 原料报损单生成
 */
package action.restaurant.reportdamage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONObject;
import pojo.form.LossHeader;
import pojo.store.BranchStorage;
import service.restaurant.reportdamage.ManageLossBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;

public class CreateLossAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BranchStorageBean branchStorageBean;
	private ManageLossBillService manageLossBillService;

	private List<Map> shopList;
	private List<BranchStorage> storageList;
	private String lossTime;

	private String jsonData;
	private String rawJsonData;

	private String branchType;

	private String brandWord;

	private LossHeader lossHeader;

	public LossHeader getLossHeader() {
		return lossHeader;
	}

	public void setLossHeader(LossHeader lossHeader) {
		this.lossHeader = lossHeader;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getLossTime() {
		return lossTime;
	}

	public void setLossTime(String lossTime) {
		this.lossTime = lossTime;
	}

	public String getBrandWord() {
		return brandWord;
	}

	public void setBrandWord(String brandWord) {
		this.brandWord = brandWord;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		brandWord = BranchType.getBrandWord(branchType);
		shopList = branchBean.queryById(getLoginBranchId());

		if (shopList.size() > 0) {
			storageList = branchStorageBean.query(shopList.get(0).get("code").toString(), branchType);
		}
		return SUCCESS;
	}

	public String commitView() throws NoPrivilegeException, SQLException, NoConnection {
		brandWord = BranchType.getBrandWord(branchType);

		String lossBranchId = lossHeader.getLossBranchId();
		Date businessDate = branchBean.GetBranchById(lossBranchId).getBusinessDate();

		lossHeader.setLossManId(getLoginUserId());
		lossHeader.setLossMan(getLoginUsername());

		lossTime = DateTimeUtil.getDateStr(businessDate);

		return SUCCESS;
	}

	public String commitDishView() throws NoPrivilegeException, SQLException, NoConnection {
		commitView();
		rawJsonData = manageLossBillService.getRawFromDishs(jsonData);
		return SUCCESS;
	}

	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		commit("RAWLOSS");
	}

	public void doDishCommit() throws NoPrivilegeException, SQLException, NoConnection {
		commit("DISHLOSS");
	}

	public void commit(String lossType) throws NoPrivilegeException, SQLException, NoConnection {
		String formId = "";
		lossHeader.setLossTime(DateTimeUtil.parse(lossTime));

		if (lossType == "RAWLOSS") {
			formId = manageLossBillService.CreateLossBill(lossHeader, jsonData);
		} else {
			formId = manageLossBillService.CreateDishLossBill(lossHeader, jsonData, rawJsonData);
		}

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", formId);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setManageLossBillService(ManageLossBillService manageLossBillService) {
		this.manageLossBillService = manageLossBillService;
	}

	public List<Map> getShopList() {
		return shopList;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getRawJsonData() {
		return rawJsonData;
	}

	public void setRawJsonData(String rawJsonData) {
		this.rawJsonData = rawJsonData;
	}
}
