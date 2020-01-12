/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 14, 2014 by liyzh
 * Last edited on Aug 14, 2014 by liyzh
 * 
 * 说明： 餐厅要货生成
 */
package action.restaurant.goodsbill;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.OptionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.RequestHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.restaurant.goodsbill.CreateBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private CreateBillService createBillService;
	private BranchBean branchBean;
	private OptionBean optionBean;

	private List<Map> shopLst;
	private String branchId;
	/**
	 * 传递配方模式中文文本
	 */
	private String deliveryTypeName;

	private RequestHeader requestHeader;
	private Date receiveTime;
	private Date formTime;

	private Date auditTime;
	private Date refDateStart1;
	private Date refDateEnd1;
	private Date refDateStart2;
	private Date refDateEnd2;
	private Date refDateStart3;
	private Date refDateEnd3;

	private String jsonData;
	private String isWelcome;

	/** 上传的文件 */
	private File attachment;

	private BranchStorageBean branchStorageBean;

	private String addForm;
	private String branchType;
	private boolean autonomyFlag;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());
		if (shopLst.size() > 0) {
			branchType = shopLst.get(0).get("branchType").toString();
		}

		if (requestHeader != null) {
			receiveTime = requestHeader.getReceiveTime();
			auditTime = requestHeader.getAuditTime();
			refDateStart1 = requestHeader.getRefDateStart1();
			refDateEnd1 = requestHeader.getRefDateEnd1();
			refDateStart2 = requestHeader.getRefDateStart2();
			refDateEnd2 = requestHeader.getRefDateEnd2();
			refDateStart3 = requestHeader.getRefDateStart3();
			refDateEnd3 = requestHeader.getRefDateEnd3();
		}

		String flag = "";
		try {
			flag = optionBean.getOption("AUTONOMY");
		} catch (Exception e) {
			flag = "";
		}

		autonomyFlag = false;

		if (!TextUtil.isEmpty(flag) && flag.toUpperCase().equals("TRUE")) {
			autonomyFlag = true;
		}
		return SUCCESS;
	}

	public String commitView() throws NoPrivilegeException, SQLException, NoConnection {
		String buyerId = requestHeader.getBuyerId();
		Date settleTime = branchBean.GetBranchById(buyerId).getBusinessDate();

		Branch branchObj = branchBean.GetBranchById(buyerId);
		requestHeader.setBuyerAddress(branchObj.getBranchAddress());

		formTime = settleTime;
		requestHeader.setFormMaker(getLoginUserId());

		receiveTime = requestHeader.getReceiveTime();
		refDateStart1 = requestHeader.getRefDateStart1();
		refDateEnd1 = requestHeader.getRefDateEnd1();
		refDateStart2 = requestHeader.getRefDateStart2();
		refDateEnd2 = requestHeader.getRefDateEnd2();
		refDateStart3 = requestHeader.getRefDateStart3();
		refDateEnd3 = requestHeader.getRefDateEnd3();

		return SUCCESS;
	}

	/**
	 * 保存新的要货单
	 */
	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		BranchStorage branchStorage = branchStorageBean.queryMainStore(getLoginBranchId());

		requestHeader.setStorageId(branchStorage.getStorageId());
		requestHeader.setStorage(branchStorage.getStorageName());

		String formId = createBillService.doCommit(getLoginUserId(), getLoginUsername(), requestHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("formId", formId);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void calcSuggest() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = createBillService.calcSuggest(branchId, getRefDateStart1(), getRefDateEnd1(),
				getRefDateStart2(), getRefDateEnd2(), getRefDateStart3(), getRefDateEnd3(), jsonData);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析导入文件
	 */
	public void doImport() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = createBillService.doImport(attachment);
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getReceiveTime() {
		return getDateStr(receiveTime);
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getFormTime() {
		return getDateStr(formTime);
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAuditTime() {
		return getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getRefDateStart1() {
		return getDateStr(refDateStart1);
	}

	public void setRefDateStart1(Date refDateStart1) {
		this.refDateStart1 = refDateStart1;
	}

	public String getRefDateEnd1() {
		return getDateStr(refDateEnd1);
	}

	public void setRefDateEnd1(Date refDateEnd1) {
		this.refDateEnd1 = refDateEnd1;
	}

	public String getRefDateStart2() {
		return getDateStr(refDateStart2);
	}

	public void setRefDateStart2(Date refDateStart2) {
		this.refDateStart2 = refDateStart2;
	}

	public String getRefDateEnd2() {
		return getDateStr(refDateEnd2);
	}

	public void setRefDateEnd2(Date refDateEnd2) {
		this.refDateEnd2 = refDateEnd2;
	}

	public String getRefDateStart3() {
		return getDateStr(refDateStart3);
	}

	public void setRefDateStart3(Date refDateStart3) {
		this.refDateStart3 = refDateStart3;
	}

	public String getRefDateEnd3() {
		return getDateStr(refDateEnd3);
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setRefDateEnd3(Date refDateEnd3) {
		this.refDateEnd3 = refDateEnd3;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		System.out.println(jsonData);
		this.jsonData = jsonData;
	}

	public void setCreateBillService(CreateBillService createBillService) {
		this.createBillService = createBillService;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getDeliveryTypeName() {
		return deliveryTypeName;
	}

	public void setDeliveryTypeName(String deliveryTypeName) {
		this.deliveryTypeName = deliveryTypeName;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getAddForm() {
		return addForm;
	}

	public void setAddForm(String addForm) {
		this.addForm = addForm;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public boolean isAutonomyFlag() {
		return autonomyFlag;
	}

	public void setAutonomyFlag(boolean autonomyFlag) {
		this.autonomyFlag = autonomyFlag;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}

}
