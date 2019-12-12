//===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014年9月18日 by pw
// Last edited on 2014年9月18日 by pw
//
// Comments:调拨单据管理
//
//===============================================
package action.restaurant.allocateitem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.TransferDetailBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.TransferDetail;
import pojo.form.TransferHeader;
import service.restaurant.allocateitem.CreateBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private TransferDetailBean transferDetailBean;

	private CreateBillService createBillService;

	private String branchName;

	private String formId;

	private TransferHeader transferHeader;
	private String jsonData;
	private Date receiveTime;
	private Date formTime;

	private Date auditTime;

	private List<Map> shopLst;

	private Integer preVersion;
	private Integer currentVersion;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "");
		allItem.put("name", "--请选择--");
		shopLst.add(0, allItem);
		return SUCCESS;
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		createBillService.deleteBill(formId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<TransferDetail> detailLst = transferDetailBean.query(formId);
		int rownumber = 1;
		for (TransferDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			json.put("actualCount", detail.getApplyCount());

			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		createBillService.updateAllocateBill(transferHeader, currentVersion, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public TransferHeader getTransferHeader() {
		return transferHeader;
	}

	public void setTransferHeader(TransferHeader transferHeader) {
		this.transferHeader = transferHeader;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setTransferDetailBean(TransferDetailBean transferDetailBean) {
		this.transferDetailBean = transferDetailBean;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public CreateBillService getCreateBillService() {
		return createBillService;
	}

	public void setCreateBillService(CreateBillService createBillService) {
		this.createBillService = createBillService;
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

}
