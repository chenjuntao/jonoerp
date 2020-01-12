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
 * 说明： 餐厅配送退货生成
 */
package action.restaurant.preject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.InputHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONObject;
import pojo.form.InputHeader;
import pojo.form.ReturnGoodsHeader;
import pojo.store.BranchStorage;
import service.restaurant.dreject.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BillManageService djBillManageService;
	private InputHeaderBean inputHeaderBean;

	private List<Map> shopLst;

	private String formId;

	private InputHeader inputHeader;
	private ReturnGoodsHeader returnHeader;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private Date inputTime;
	private Date returnTime;

	private String jsonData;
	private String branchType;
	private String formType;

	private List<BranchStorage> storageList;

	private BranchStorageBean branchStorageBean;

	private String storageId;
	private String storage;

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchType() {
		return branchType;
	}

	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		shopLst = branchBean.queryById(getLoginBranchId());

		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {

		if (!TextUtil.isEmpty(formId)) {
			storageList = branchStorageBean.query("", branchType);

			inputHeader = inputHeaderBean.queryById(formId);

			auditTime = inputHeader.getAuditTime();
			inputTime = inputHeader.getInputTime();
		}
		return SUCCESS;
	}

	/**
	 * 确认页面
	 * 
	 * @return
	 */
	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		inputHeader = inputHeaderBean.queryById(formId);

		auditTime = inputHeader.getAuditTime();
		inputTime = inputHeader.getInputTime();

		String branchId;
		if (BranchType.OUTERORDER.equals(branchType)) {
			branchId = getLoginBranchId();
		} else {
			branchId = inputHeader.getInputDepartmentId();
		}

		Date settleTime = branchBean.GetBranchById(branchId).getBusinessDate();
		Date now = new Date();
		// 自动生成，拼音缩写+部门编号+日期+流水号
		String formId = "CTH" + branchId + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");
		returnHeader.setFormId(formId);

		returnHeader.setReturnerId(getLoginUserId());
		returnHeader.setReturner(getLoginUsername());
		returnTime = branchBean.GetBranchById(branchId).getBusinessDate();
		return SUCCESS;
	}

	/**
	 * 生成采购退货单
	 */
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String userId = getLoginUserId();
		djBillManageService.saveBill(userId, returnHeader, formType, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", returnHeader.getFormId());
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

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public String getReturnTime() {
		return DateTimeUtil.getDateStr(returnTime);
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public ReturnGoodsHeader getReturnHeader() {
		return returnHeader;
	}

	public void setReturnHeader(ReturnGoodsHeader returnHeader) {
		this.returnHeader = returnHeader;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setDjBillManageService(BillManageService djBillManageService) {
		this.djBillManageService = djBillManageService;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

}
