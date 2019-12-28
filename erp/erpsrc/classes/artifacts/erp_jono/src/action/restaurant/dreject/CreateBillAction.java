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
package action.restaurant.dreject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ShippingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import pojo.form.ReturnGoodsHeader;
import pojo.form.ShippingHeader;
import service.restaurant.dreject.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ShippingHeaderBean shippingHeaderBean;
	private BranchBean branchBean;
	private BillManageService djBillManageService;

	private List<Map> shopLst;

	private String formId;

	private ShippingHeader shippingHeader;
	private ReturnGoodsHeader returnHeader;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private Date inputTime;
	private Date returnTime;
	private Date startDate;
	private Date endDate;
	private String jsonData;
	private String itemName;
	private String brandType;

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		endDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		startDate = DateTimeUtil.addDays(endDate, -1);
		shopLst = branchBean.queryById(getLoginBranchId());
		if (shippingHeader != null) {
			receiveTime = shippingHeader.getReceiveTime();
		}
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			shippingHeader = shippingHeaderBean.queryById(formId);

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getInputTime();
		}
		return SUCCESS;
	}

	/**
	 * 确认页面
	 * 
	 * @return
	 */
	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		shippingHeader = shippingHeaderBean.queryById(formId);

		receiveTime = shippingHeader.getReceiveTime();
		formTime = shippingHeader.getFormTime();
		auditTime = shippingHeader.getAuditTime();
		inputTime = shippingHeader.getInputTime();

		String branchId = shippingHeader.getRequesterId();
		Date settleTime = branchBean.GetBranchById(branchId).getBusinessDate();
		Date now = new Date();
		// 自动生成，拼音缩写+部门编号+日期+流水号
		String formId = "PTH" + branchId + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");
		returnHeader.setFormId(formId);

		returnHeader.setReturnerId(getLoginUserId());
		returnHeader.setReturner(getLoginUsername());
		returnTime = branchBean.GetBranchById(branchId).getBusinessDate();
		return SUCCESS;
	}

	/**
	 * 生成配送退货单
	 */
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String userId = getLoginUserId();
		djBillManageService.saveBill(userId, returnHeader, "distribution", jsonData);

		JSONObject result = new JSONObject();
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

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public ReturnGoodsHeader getReturnHeader() {
		return returnHeader;
	}

	public void setReturnHeader(ReturnGoodsHeader returnHeader) {
		this.returnHeader = returnHeader;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setDjBillManageService(BillManageService djBillManageService) {
		this.djBillManageService = djBillManageService;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
