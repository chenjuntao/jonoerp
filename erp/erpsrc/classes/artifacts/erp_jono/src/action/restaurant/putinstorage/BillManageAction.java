/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 5, 2014 by liyzh
 * Last edited on Sep 5, 2014 by liyzh
 * 
 * 说明：外部入库单管理
 */
package action.restaurant.putinstorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputDetailBean;
import logic.form.InputHeaderBean;
import logic.form.OperationVersionBean;
import logic.form.WorkOrderHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.InputDetail;
import pojo.form.InputHeader;
import pojo.form.TransferHeader;
import service.restaurant.putinstorage.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.DoubleJsonValueProcessor;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BillManageService billManageService;
	private BranchBean branchBean;
	private InputHeaderBean inputHeaderBean;
	private InputDetailBean inputDetailBean;
	private FormStatusBean formStatusBean;

	private WorkOrderHeaderBean workOrderHeaderBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String branchName;
	private String queryType;
	private String branchType;
	private String branchFlag;

	private String formId;

	private InputHeader inputHeader;
	private Date inputTime;

	private Date auditTime;

	private String jsonData;
	private List<Map> shopLst;

	private String storageId;

	private Double receivedCount;

	private String status;
	private String itemName;

	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;

	private String condition;
	private String loginBranchId;

	private Date businessDate;

	private String hasSum;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = endDate = startDate = null;
		loginBranchId = cfCode;
		shopLst = branchBean.queryById(cfCode);
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			inputHeader = inputHeaderBean.queryById(formId);
			inputTime = inputHeader.getInputTime();
			inputHeader.setAuditor(getLoginUsername());
		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			inputHeader = inputHeaderBean.queryById(formId);
			status = formStatusBean.getCurrentStatus(formId);
			branchType = getLoginBranchType();
			// 入库单 与 工单都会访问这个方法，如果是入库单则不会执行该代码
			if (formId.toUpperCase().indexOf("LK") == -1) {
				receivedCount = workOrderHeaderBean.queryById(inputHeader.getFormRefId()).getInputedCount();
			}

			inputTime = inputHeader.getInputTime();
		}
		return SUCCESS;
	}

	@Deprecated
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (storageId == null) {
			storageId = "";
		}
		JSONArray arr = new JSONArray();
		if (TextUtil.isEmpty(branchId)) {
			branchId = "-1";// 统一参数为空时branchId=-1
		}
		int total = inputHeaderBean.count(startDate, endDate, branchId, storageId, queryType, branchType);
		setTotal(total);
		List<InputHeader> headerLst;
		if (total > 0) {
			if (TextUtil.isEmpty(itemName)) {
				headerLst = inputHeaderBean.query(startDate, endDate, branchId, storageId, getStart(), getEnd(),
						queryType, branchType);
			} else {
				headerLst = inputHeaderBean.query(startDate, endDate, branchId, storageId, getStart(), getEnd(),
						queryType, branchType, itemName);
			}
			int rownumber = getStart();
			int end = getEnd();
			for (InputHeader header : headerLst) {
				String provider = "[" + header.getProviderId() + "]" + header.getProvider();
				header.setProvider(provider);
				JSONObject json = JSONObject.fromObject(header);
				String inputTime = DateTimeUtil.getDateStr(header.getInputTime());
				json.put("inputTime", inputTime);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile() && TextUtil.isEmpty(queryType)) {
				double allPayAmt = inputHeaderBean.sum(startDate, endDate, branchId, branchType);
				TransferHeader item = new TransferHeader();
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

	public void doQueryNew() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		int total = inputHeaderBean.countNew(condition);
		setTotal(total);

		if (total > 0) {
			List<InputHeader> headerLst = inputHeaderBean.queryNew(getStart(), getEnd(), condition);
			int rownumber = getStart();
			for (InputHeader header : headerLst) {
				String provider = "[" + header.getProviderId() + "]" + header.getProvider();
				header.setProvider(provider);
				JSONObject json = JSONObject.fromObject(header);
				String inputTime = DateTimeUtil.getDateStr(header.getInputTime());
				json.put("inputTime", inputTime);
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

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<InputDetail> detailLst = inputDetailBean.query(formId, hasSum);

		int rownumber = 1;
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());
		for (InputDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail, config);
			json.put("expiredTime", DateTimeUtil.getDateStr(detail.getExpiredTime()));
			json.put("rownumber", rownumber);

			// 下面几个字段用于生成退货单时初始化
			json.put("returnCount", 0);
			json.put("returnPayAmt", 0);
			json.put("returnReason", "");
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryReturnDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<InputDetail> detailLst = inputDetailBean.query(formId, hasSum);

		int rownumber = 1;
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());
		for (InputDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail, config);
			json.put("expiredTime", DateTimeUtil.getDateStr(detail.getExpiredTime()));
			json.put("rownumber", rownumber);
			// 餐厅使用的是标准价，物流中心使用采购价
			String branchType = getLoginBranchType();
			if (!BranchType.RESTAURANT.equals(branchType)) {
				json.put("itemUnitPrice", detail.getReceivePrice());
			}

			// 下面几个字段用于生成退货单时初始化
			json.put("returnCount", 0);
			json.put("returnPayAmt", 0);
			json.put("returnReason", "");
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
		billManageService.updateBill(inputHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		billManageService.deleteBill(formId, currentVersion);

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

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setBillManageService(BillManageService billManageService) {
		this.billManageService = billManageService;
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

	public void setInputDetailBean(InputDetailBean inputDetailBean) {
		this.inputDetailBean = inputDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public Double getReceivedCount() {
		return receivedCount;
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

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setHasSum(String hasSum) {
		this.hasSum = hasSum;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

	public String getBranchType() {
		return branchType;
	}

}
