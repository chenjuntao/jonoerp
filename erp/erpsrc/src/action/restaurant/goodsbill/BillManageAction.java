/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明： 餐厅要货单查询修改
 */
package action.restaurant.goodsbill;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.RequestDetailBean;
import logic.form.RequestHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.RequestDetail;
import pojo.form.RequestHeader;
import service.restaurant.goodsbill.CreateBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.DoubleJsonValueProcessor;
import com.tanry.framework.util.TextUtil;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private CreateBillService createBillService;
	private BranchBean branchBean;
	private RequestHeaderBean requestHeaderBean;
	private RequestDetailBean requestDetailBean;
	private FormStatusBean formStatusBean;

	/**
	 * 单据类型（餐厅要货/外部订货）
	 */
	private String formType;
	private String queryType;
	private Date startDate;
	private Date endDate;
	private String branchId;

	private String formId;

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
	private String auditor;
	private String formMaker;

	private String jsonData;
	private List<Map> shopLst;

	private String status;

	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;

	private String cancelMsg;

	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		shopLst = branchBean.queryById(getLoginBranchId());
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			requestHeader = requestHeaderBean.queryById(formId);
			formTime = requestHeader.getFormTime();
			formMaker = requestHeader.getFormMaker();
			receiveTime = requestHeader.getReceiveTime();
			refDateStart1 = requestHeader.getRefDateStart1();
			refDateEnd1 = requestHeader.getRefDateEnd1();
			refDateStart2 = requestHeader.getRefDateStart2();
			refDateEnd2 = requestHeader.getRefDateEnd2();
			refDateStart3 = requestHeader.getRefDateStart3();
			refDateEnd3 = requestHeader.getRefDateEnd3();

			preVersion = operationVersionBean.queryVersion(formId).getVersion();
		}

		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			requestHeader = requestHeaderBean.queryById(formId);
			status = requestHeader.getFormStatus();
			formTime = requestHeader.getFormTime();
			auditor = requestHeader.getAuditor();
			formMaker = requestHeader.getFormMaker();
			receiveTime = requestHeader.getReceiveTime();
			auditTime = requestHeader.getAuditTime();
			refDateStart1 = requestHeader.getRefDateStart1();
			refDateEnd1 = requestHeader.getRefDateEnd1();
			refDateStart2 = requestHeader.getRefDateStart2();
			refDateEnd2 = requestHeader.getRefDateEnd2();
			refDateStart3 = requestHeader.getRefDateStart3();
			refDateEnd3 = requestHeader.getRefDateEnd3();
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if ("-1".equals(branchId)) {
			branchId = "";// 统一非空条件
		}
		JSONArray arr = new JSONArray();
		int total = requestHeaderBean.count(startDate, endDate, branchId, formType, queryType);
		setTotal(total);

		if (total > 0) {
			List<RequestHeader> headerLst = requestHeaderBean.query(startDate, endDate, branchId, getStart(), getEnd(),
					formType, queryType);
			for (RequestHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				String receiveTime = getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);
				String auditTime = getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);
				arr.add(json);
			}
		}

		if (isMobile()) {
			JSONObject result = new JSONObject();
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("iTotalDisplayRecords", total);
			result.put("data", arr);// for DataTables
			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		List<RequestDetail> detailLst = new ArrayList<RequestDetail>();
		detailLst = requestDetailBean.query(formId, hasSum);

		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());
		JSONArray arr = JSONArray.fromObject(detailLst, config);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		createBillService.updateGoodsBill(requestHeader, jsonData, currentVersion);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		createBillService.deleteGoodsBill(formId, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doInvalid() throws NoPrivilegeException, SQLException, NoConnection {
		String[] formIds = formId.split(",");
		String userId = getLoginUserId();
		createBillService.doInvalid(formIds, currentVersion, userId, cancelMsg);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑审核等操作时先判断单据的状态
	 * 
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	public void getCurrentStatus() throws NoPrivilegeException, SQLException, NoConnection {
		String formStatus = formStatusBean.getCurrentStatus(formId);
		JSONObject result = new JSONObject();
		result.put("formStatus", formStatus);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑审核等操作结束后对单据解锁
	 * 
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	public void releaseLock() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
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

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setCreateBillService(CreateBillService createBillService) {
		this.createBillService = createBillService;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setRequestDetailBean(RequestDetailBean requestDetailBean) {
		this.requestDetailBean = requestDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getStatus() {
		return status;
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

	public void setCancelMsg(String cancelMsg) {
		this.cancelMsg = cancelMsg;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getFormMaker() {
		return formMaker;
	}

	public void setFormMaker(String formMaker) {
		this.formMaker = formMaker;
	}

}
