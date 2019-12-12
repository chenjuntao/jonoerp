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
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import pojo.form.CheckHeader;
import service.restaurant.checkstorage.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class CreateItemAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BillManageService billManageService;

	private List<Map> shopLst;
	private String branchId;

	private String formId;

	private CheckHeader checkHeader;
	private Date formTime;

	private String jsonData;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	/**
	 * 确认页面
	 * 
	 * @return
	 */
	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		branchId = checkHeader.getCheckBranchId();
		formTime = branchBean.GetBranchById(branchId).getBusinessDate();
		checkHeader.setFormId(formId);

		checkHeader.setFormMakerId(getLoginUserId());
		checkHeader.setFormMaker(getLoginUsername());
		return SUCCESS;
	}

	/**
	 * 生成盘点清单
	 */
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String userId = getLoginUserId();
		formId = billManageService.createItemList(userId, checkHeader, jsonData);

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

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBillManageService(BillManageService billManageService) {
		this.billManageService = billManageService;
	}

}
