/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 20, 2016 by czc
 * Last edited on Sep 20, 2016 by czc
 * 
 * 说明： 半成品入库单审核
 */

package action.restaurant.production;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.OperationVersionBean;
import logic.form.SelfSemisHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONObject;
import pojo.form.SelfSemisHeader;
import pojo.store.BranchStorage;
import service.restaurant.production.ManageSelfBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class ConfirmSelfSemisAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private SelfSemisHeaderBean selfSemisHeaderBean;
	private ManageSelfBillService manageSelfBillService;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String formId;
	private String auditTime;
	private String formTime;

	private SelfSemisHeader selfSemisHeader;

	private String jsonData; // 保存修改的数据
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

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		endDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取门店当前的营业时间
		businessDate = endDate;
		startDate = DateTimeUtil.addDays(endDate, -1);
		shopList = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {

		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			selfSemisHeader = selfSemisHeaderBean.queryById(formId);

			String branch_id = selfSemisHeader.getBranchId();

			Date businessDate = branchBean.GetBranchById(branch_id).getBusinessDate();
			auditTime = getDateStr(businessDate);
			formTime = getDateStr(selfSemisHeader.getFormTime());

			selfSemisHeader.setAuditorId(getLoginUserId());
			selfSemisHeader.setAuditor(getLoginUsername());
		}
		return SUCCESS;
	}

	public String auditCommitView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			selfSemisHeader = selfSemisHeaderBean.queryById(formId);
			selfSemisHeader.setAuditTime(DateTimeUtil.parse(auditTime));
			formTime = getDateStr(selfSemisHeader.getFormTime());
			selfSemisHeader.setAuditorId(getLoginUserId());
			selfSemisHeader.setAuditor(getLoginUsername());
		}
		rawJsonData = manageSelfBillService.getRawFromDishs2(jsonData);

		return SUCCESS;
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();
		Date businessDate = branchBean.GetBranchById(branchId).getBusinessDate();
		selfSemisHeader.setAuditTime(businessDate);
		String storageId = branchStorageBean.queryMainStore(branchId).getStorageId();

		manageSelfBillService.doAudit(selfSemisHeader, jsonData, rawJsonData, currentVersion, storageId);

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

	public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public SelfSemisHeader getSelfSemisHeader() {
		return selfSemisHeader;
	}

	public void setSelfSemisHeader(SelfSemisHeader selfSemisHeader) {
		this.selfSemisHeader = selfSemisHeader;
	}

	public void setSelfSemisHeaderBean(SelfSemisHeaderBean selfSemisHeaderBean) {
		this.selfSemisHeaderBean = selfSemisHeaderBean;
	}

	public void setManageSelfBillService(ManageSelfBillService manageSelfBillService) {
		this.manageSelfBillService = manageSelfBillService;
	}

}
