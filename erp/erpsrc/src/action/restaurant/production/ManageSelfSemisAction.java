/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 23, 2014 by cjt
 * Last edited on Sep 23, 2014 by cjt
 * 
 * 说明：餐厅报损单管理
 */
package action.restaurant.production;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.OperationVersionBean;
import logic.form.SelfSemisDetailBean;
import logic.form.SelfSemisHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.SelfSemisDetail;
import pojo.form.SelfSemisHeader;
import pojo.store.BranchStorage;
import service.restaurant.production.ManageSelfBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class ManageSelfSemisAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ManageSelfBillService manageSelfBillService;
	private BranchBean branchBean;
	private SelfSemisHeaderBean selfSemisHeaderBean;
	private SelfSemisDetailBean selfSemisDetailBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String queryType;
	private String statusType;

	private String formId;

	private List<BranchStorage> storageList;

	private SelfSemisHeader selfSemisHeader;
	private String formTime;
	private String auditTime;

	private String jsonData;
	private String rawJsonData; // 保存原料数据
	private List<Map> shopList;

	private String branchType;

	private Integer preVersion;
	private Integer currentVersion;
	private OperationVersionBean operationVersionBean;

	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		endDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取中央工厂当前的营业时间
		businessDate = endDate;
		startDate = DateTimeUtil.addDays(endDate, -1);
		shopList = branchBean.queryById(getLoginBranchId());

		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			selfSemisHeader = selfSemisHeaderBean.queryById(formId);
			formTime = getDateStr(selfSemisHeader.getFormTime());
			auditTime = getDateStr(selfSemisHeader.getAuditTime());
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if ("-1".equals(branchId)) {
			branchId = "";// 统一非空条件
		}

		if (TextUtil.isEmpty(statusType)) {
			statusType = "";
		}
		int total = selfSemisHeaderBean.count(startDate, endDate, branchId, statusType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<SelfSemisHeader> headerLst = selfSemisHeaderBean.query(startDate, endDate, branchId, statusType,
					getStart(), getEnd());

			int rownumber = getStart();

			for (SelfSemisHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);

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

	/**
	 * 半成品和原料信息
	 */
	public void queryAllDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<SelfSemisDetail> semisLst = selfSemisDetailBean.queryDetail(formId, "SEMIS");
		List<SelfSemisDetail> rawLst = selfSemisDetailBean.queryDetail(formId, "RAW");

		JSONObject result = new JSONObject();
		result.put("jsonData", semisLst);
		result.put("rawJsonData", rawLst);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<SelfSemisDetail> semisLst = selfSemisDetailBean.queryDetail(formId, queryType);
		if (semisLst.isEmpty()) {
			semisLst = manageSelfBillService.getRawFromDishs1(formId);
		}
		int rownumber = 1;

		for (SelfSemisDetail detail : semisLst) {
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

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			selfSemisHeader = selfSemisHeaderBean.queryById(formId);

			formTime = getDateStr(selfSemisHeader.getFormTime());
		}

		return SUCCESS;
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		manageSelfBillService.UpdateSelfBill(selfSemisHeader, currentVersion, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		String formId = selfSemisHeader.getFormId();
		manageSelfBillService.deleteSelfBill(formId, currentVersion);

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

	public String getJsonData() {
		return jsonData;
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

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public List<Map> getShopList() {
		return shopList;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
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

	public String getRawJsonData() {
		return rawJsonData;
	}

	public void setRawJsonData(String rawJsonData) {
		this.rawJsonData = rawJsonData;
	}

	public SelfSemisHeader getSelfSemisHeader() {
		return selfSemisHeader;
	}

	public void setSelfSemisHeader(SelfSemisHeader selfSemisHeader) {
		this.selfSemisHeader = selfSemisHeader;
	}

	public void setManageSelfBillService(ManageSelfBillService manageSelfBillService) {
		this.manageSelfBillService = manageSelfBillService;
	}

	public void setSelfSemisHeaderBean(SelfSemisHeaderBean selfSemisHeaderBean) {
		this.selfSemisHeaderBean = selfSemisHeaderBean;
	}

	public void setSelfSemisDetailBean(SelfSemisDetailBean selfSemisDetailBean) {
		this.selfSemisDetailBean = selfSemisDetailBean;
	}

	public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

}
