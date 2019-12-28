/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 22, 2015 by liyzh
 * Last edited on Apr 22, 2015 by liyzh
 * 
 * 说明： 由物流中心的采购要货单生成汇总单
 */
package action.centralfactory.productionDemand.orderSummary;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CollectHeader;
import pojo.form.FormStatus;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import service.centralfactory.productionDemand.OrderSummaryService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.constant.LogType;

public class CreateOrderSummaryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private OrderSummaryService cfOrderSummaryService;

	private PurchasingDetailBean purchasingDetailBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private FormStatusBean formStatusBean;

	private CollectHeader collectHeader;

	private Date startDate;
	private Date endDate;

	private String jsonData;
	private String formId;

	private PurchasingHeader purchasingHeader;

	public PurchasingHeader getPurchasingHeader() {
		return purchasingHeader;
	}

	public void setPurchasingHeader(PurchasingHeader purchasingHeader) {
		this.purchasingHeader = purchasingHeader;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	@Deprecated
	public void doSummaryQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();

		String lcCode = BranchCode.DEFAULT_LOGISTICSCENTER;
		String cfCode = BranchCode.DEFAULT_CENTRALFACTORY;
		List<PurchasingDetail> detailLst = purchasingDetailBean.queryAllSummary(lcCode, cfCode, startDate, endDate);

		int rownumber = getStart();
		for (PurchasingDetail detail : detailLst) {
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

	private String receiveTime;
	private String formTime;
	private String auditTime;
	private String deliveryType;

	public String getDeliveryType() {
		return "UNIFIED".equals(deliveryType) ? "统配" : deliveryType;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public String getFormTime() {
		return formTime;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			purchasingHeader = purchasingHeaderBean.queryById(formId);
			receiveTime = DateTimeUtil.getDateStr(purchasingHeader.getReceiveTime());
			formTime = DateTimeUtil.getDateStr(purchasingHeader.getFormTime());
			auditTime = DateTimeUtil.getDateStr(purchasingHeader.getAuditTime());
			deliveryType = purchasingHeader.getDeliveryType();
		}
		return SUCCESS;
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<PurchasingDetail> detailLst = purchasingDetailBean.query(formId);

		int rownumber = 1;
		for (PurchasingDetail detail : detailLst) {
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
	 * 由物流中心的采购要货单生成汇总单
	 */
	@Deprecated
	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		cfOrderSummaryService.doCommit(getLoginUserId(), getLoginUsername(), collectHeader, jsonData, startDate,
				endDate);
		cfOrderSummaryService.formIdToStatus(getLoginUserId(), startDate, endDate);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doOrder() throws NoPrivilegeException, SQLException, NoConnection {
		FormStatus fstatus = new FormStatus(formId, "已结案", getLoginUserId());
		formStatusBean.saveEntity(fstatus);

		OperateLogUtil.record(formId, LogType.FINISH, "生产工单结案");

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setCfOrderSummaryService(OrderSummaryService cfOrderSummaryService) {
		this.cfOrderSummaryService = cfOrderSummaryService;
	}

	public CollectHeader getCollectHeader() {
		return collectHeader;
	}

	public void setCollectHeader(CollectHeader collectHeader) {
		this.collectHeader = collectHeader;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

}
