/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 20, 2014 by cjt
 * Last edited on Sep 20, 2014 by cjt
 * 
 * 说明： 报损单审核
 */

package action.restaurant.reportdamage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.LossHeaderBean;
import logic.form.OperationVersionBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.LossHeader;
import pojo.store.BranchStorage;
import service.restaurant.reportdamage.ManageLossBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchType;

public class ConfirmLossAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private LossHeaderBean lossHeaderBean;
	private ManageLossBillService manageLossBillService;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String formId;
	private String auditTime;
	private String lossTime;

	private LossHeader lossHeader;

	private String jsonData; // 保存修改的数据
	private String dishJsonData; // 保存出品数据
	private String rawJsonData; // 保存原料数据
	private List<Map> shopList;

	private String storageId;
	private String storageName;

	private List<BranchStorage> storageList;

	private String branchType;

	private String brandWord;

	private BranchStorageBean branchStorageBean;

	private Integer preVersion;
	private Integer currentVersion;
	private OperationVersionBean operationVersionBean;
	private Date businessDate;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		endDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取中央工厂当前的营业时间
		businessDate = endDate;
		startDate = DateTimeUtil.addDays(endDate, -1);
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = endDate = startDate = null;
		brandWord = BranchType.getBrandWord(branchType);
		shopList = branchBean.queryById(getLoginBranchId());

		if (shopList.size() > 0) {
			storageList = branchStorageBean.query(getLoginBranchId(), branchType);
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		QueryAudit("RAWLOSS");
	}

	public void doQueryDish() throws NoPrivilegeException, SQLException, NoConnection {
		QueryAudit("DISHLOSS");
	}

	public void auditById() throws NoPrivilegeException, SQLException, NoConnection {
		Date businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		manageLossBillService.auditById(formId, getLoginUserId(), getLoginUsername(), businessDate);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void QueryAudit(String lossType) throws NoPrivilegeException, SQLException, NoConnection {
		if ("-1".equals(branchId)) {
			branchId = "";// 统一非空条件
		}
		int total = lossHeaderBean.countByStatus(startDate, endDate, branchId, storageId, lossType,
				BillStatus.UNADUIT_CN, branchType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<LossHeader> headerLst = lossHeaderBean.queryByStatus(startDate, endDate, branchId, storageId,
					lossType, BillStatus.UNADUIT_CN, branchType);

			int rownumber = getStart();

			for (LossHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String lossTime = getDateStr(header.getLossTime());
				json.put("lossTime", lossTime);

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

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		brandWord = BranchType.getBrandWord(branchType);

		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			lossHeader = lossHeaderBean.queryById(formId);

			String branch_id = lossHeader.getLossBranchId();

			Date businessDate = branchBean.GetBranchById(branch_id).getBusinessDate();
			auditTime = getDateStr(businessDate);
			lossTime = getDateStr(lossHeader.getLossTime());

			lossHeader.setAuditorId(getLoginUserId());
			lossHeader.setAuditor(getLoginUsername());
		}
		return SUCCESS;
	}

	private Double allPayAmt;
	private String maxPayItem;

	public void setAllPayAmt(Double allPayAmt) {
		this.allPayAmt = allPayAmt;
	}

	public void setMaxPayItem(String maxPayItem) {
		this.maxPayItem = maxPayItem;
	}

	public String auditCommitView() throws NoPrivilegeException, SQLException, NoConnection {
		brandWord = BranchType.getBrandWord(branchType);

		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			lossHeader = lossHeaderBean.queryById(formId);
			lossHeader.setAuditTime(DateTimeUtil.parse(auditTime));
			lossTime = getDateStr(lossHeader.getLossTime());
			lossHeader.setAuditorId(getLoginUserId());
			lossHeader.setAuditor(getLoginUsername());
			lossHeader.setAllPayAmt(allPayAmt);
			lossHeader.setMaxPayItem(maxPayItem);
		}
		rawJsonData = manageLossBillService.getRawFromDishs(dishJsonData);

		return SUCCESS;
	}

	public String editCommitView() throws NoPrivilegeException, SQLException, NoConnection {
		brandWord = BranchType.getBrandWord(branchType);

		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			lossHeader = lossHeaderBean.queryById(formId);
			lossTime = getDateStr(lossHeader.getLossTime());
			lossHeader.setAllPayAmt(allPayAmt);
			lossHeader.setMaxPayItem(maxPayItem);
		}
		rawJsonData = manageLossBillService.getRawFromDishs(dishJsonData);

		return SUCCESS;
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		manageLossBillService.doAudit(lossHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAuditDish() throws NoPrivilegeException, SQLException, NoConnection {
		manageLossBillService.doAuditDish(lossHeader, jsonData, rawJsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getLossTime() {
		return lossTime;
	}

	public LossHeader getLossHeader() {
		return lossHeader;
	}

	public void setLossHeader(LossHeader lossHeader) {
		this.lossHeader = lossHeader;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getDishJsonData() {
		return dishJsonData;
	}

	public void setDishJsonData(String dishJsonData) {
		this.dishJsonData = dishJsonData;
	}

	public String getRawJsonData() {
		return rawJsonData;
	}

	public void setRawJsonData(String rawJsonData) {
		this.rawJsonData = rawJsonData;
	}

	public List<Map> getShopList() {
		return shopList;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
	}

	public void setManageLossBillService(ManageLossBillService manageLossBillService) {
		this.manageLossBillService = manageLossBillService;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
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

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
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

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}
}
