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
 * 说明： 餐厅配送反审核管理
 */
package action.restaurant.checkstorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckDetailBean;
import logic.form.CheckHeaderBean;
import logic.form.CheckLockBean;
import logic.form.FormStatusBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.CheckHeader;
import pojo.form.CheckLock;
import service.restaurant.checkstorage.BillManageService;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.DoubleJsonValueProcessor;
import com.tanry.framework.util.TextUtil;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BillManageService csBillManageService;
	private BranchBean branchBean;
	private CheckHeaderBean checkHeaderBean;
	private CheckDetailBean checkDetailBean;
	private FormStatusBean formStatusBean;

	private CheckLockBean checkLockBean;

	private String branchId;
	private Date startDate;
	private Date endDate;
	private String formType;
	private String queryType;

	private String formId;
	private String batchId;

	private CheckHeader checkHeader;
	private Date formTime;
	private Date auditTime;

	private String jsonData;
	private List<Map> shopLst;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		startDate = endDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			startDate = endDate = null;

		shopLst = branchBean.queryById(getLoginBranchId());
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public void auditById() throws NoPrivilegeException, SQLException, NoConnection {
		Date businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		csBillManageService.auditById(formId, getLoginUserId(), getLoginUsername(), businessDate);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			checkHeader = checkHeaderBean.queryById(formId);

			CheckLock checkLock = checkLockBean.queryById(checkHeader.getCheckBatchId());
			checkHeader.setCheckStorageId(checkLock.getLockStorageId());
			checkHeader.setCheckStorage(checkLock.getLockStorage());

			formTime = checkHeader.getFormTime();

			auditTime = checkHeader.getAuditTime();
			if (auditTime == null) {
				auditTime = branchBean.GetBranchById(checkHeader.getCheckBranchId()).getBusinessDate();
			}
			checkHeader.setAuditorId(getLoginUserId());
			checkHeader.setAuditor(getLoginUsername());
		}

		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			checkHeader = checkHeaderBean.queryById(formId);
			if (checkHeader != null) {
				formTime = checkHeader.getFormTime();

				auditTime = checkHeader.getAuditTime();
				if (TextUtil.isEmpty(checkHeader.getAuditorId())) {
					checkHeader.setAuditorId(getLoginUserId());
					checkHeader.setAuditor(getLoginUsername());
				}
			}
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(formType)) {
			formType = FormConstant.CHECK_FORM;
		}
		int total = checkHeaderBean.count(startDate, endDate, branchId, formType, queryType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<CheckHeader> headerLst = checkHeaderBean.query(startDate, endDate, branchId, formType, queryType,
					getStart(), getEnd());
			int rownumber = getStart();
			for (CheckHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formStatus", formStatus);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile()) {
				double allPayAmt = checkHeaderBean.sum(startDate, endDate, branchId, formType, queryType);
				CheckHeader item = new CheckHeader();
				JSONObject sumJObject = JSONObject.fromObject(item);
				sumJObject.put("rownumber", "合计");
				sumJObject.put("allPayAmt", allPayAmt);
				arr.add(sumJObject);
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

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());
		JSONArray arr = JSONArray.fromObject(checkDetailBean.query(formId, hasSum), config);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		csBillManageService.deleteBill(getLoginUserId(), formId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		csBillManageService.auditBill(getLoginUserId(), checkHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除批次及相关盘点数据
	 */
	public void deleteBatch() throws NoPrivilegeException, SQLException, NoConnection {
		csBillManageService.deleteBatch(batchId);

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

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public CheckHeader getCheckHeader() {
		return checkHeader;
	}

	public void setCheckHeader(CheckHeader checkHeader) {
		this.checkHeader = checkHeader;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

	public void setCheckDetailBean(CheckDetailBean checkDetailBean) {
		this.checkDetailBean = checkDetailBean;
	}

	public void setCsBillManageService(BillManageService csBillManageService) {
		this.csBillManageService = csBillManageService;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setCheckLockBean(CheckLockBean checkLockBean) {
		this.checkLockBean = checkLockBean;
	}

}
