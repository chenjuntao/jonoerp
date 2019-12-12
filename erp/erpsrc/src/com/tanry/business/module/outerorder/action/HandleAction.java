package com.tanry.business.module.outerorder.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormLockBean;
import logic.form.FormStatusBean;
import logic.form.RequestHeaderBean;
import logic.module.outer.OutOrderHandleBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.FormStatus;
import pojo.form.RequestHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class HandleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 制单开始时间
	private Date startDate;

	// 制单结束时间
	private Date endDate;

	// 单据状态
	private String status;

	// 操作
	private String operate;

	private String formId;

	private BranchBean branchBean;

	private List<Map> shopLst;

	private Date receiveTime;
	private Date formTime;
	private Date auditTime;

	private String branchId;
	private String outOrderId;

	private FormLockBean formLockBean;

	private OutOrderHandleBean outOrderHandleBean;
	private FormStatusBean formStatusBean;
	private RequestHeaderBean requestHeaderBean;

	private String branchType;

	private RequestHeader requestHeader;

	private String initStatus;

	public String execute() throws NoPrivilegeException, SQLException,
			NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);

		return SUCCESS;
	}

	public String operate() throws NoPrivilegeException, SQLException,
			NoConnection {
		requestHeader = requestHeaderBean.queryById(formId);
		formTime = requestHeader.getFormTime();
		receiveTime = requestHeader.getReceiveTime();

		auditTime = formTime;
		requestHeader.setAuditor(getLoginUsername());

		formLockBean.addLock(formId);// 加锁

		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException,
			NoConnection {
		requestHeader = requestHeaderBean.queryById(formId);
		formTime = requestHeader.getFormTime();
		receiveTime = requestHeader.getReceiveTime();

		auditTime = formTime;
		requestHeader.setAuditor(getLoginUsername());

		return SUCCESS;
	}

	public void doOperate() throws NoPrivilegeException, SQLException,
			NoConnection {
		formStatusBean.saveEntity(new FormStatus(formId, status,
				getLoginUserId()));

		JSONObject result = new JSONObject();
		result.put("msg", "ok");

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void query() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<Map> supplierList = outOrderHandleBean.query(startDate, endDate,
				branchId, outOrderId, initStatus, status);

		int rownumber = getStart();
		for (Map itemMap : supplierList) {
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.sql.Date.class,
					new DateJsonValueProcessor());
			JSONObject json = JSONObject.fromObject(itemMap, config);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		// 合计
		JSONObject json = new JSONObject();
		json.put("rownumber", "合计");
		json.put("form_id", "");

		Double all_pay_amt = outOrderHandleBean.sum(startDate, endDate,
				branchId, outOrderId, initStatus, status);
		json.put("all_pay_amt", all_pay_amt == null ? 0 : all_pay_amt);

		arr.add(json);

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormId() {
		return formId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public void setOutOrderHandleBean(OutOrderHandleBean outOrderHandleBean) {
		this.outOrderHandleBean = outOrderHandleBean;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setInitStatus(String initStatus) {
		this.initStatus = initStatus;
	}

}
