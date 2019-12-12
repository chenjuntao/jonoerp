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
 * 说明： 配送入库管理
 */
package action.restaurant.putinstorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.module.restaurant.DistriStockInBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.InputHeader;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.restaurant.putinstorage.DistributionService;
import action.common.BaseAction;

import com.tanry.business.form.FormType;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class DistributionBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private DistributionService distributionService;
	private ShippingHeaderBean shippingHeaderBean;
	private DistriStockInBean distriStockInBean;
	private ShippingDetailBean shippingDetailBean;
	private BranchBean branchBean;
	private BranchStorageBean branchStorageBean;
	private List<Map> shopLst;
	private String branchId;
	private String branchName;
	private Date startDate;
	private Date endDate;

	private String formId;

	private ShippingHeader shippingHeader;
	private InputHeader inputHeader;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;

	private String jsonData;

	private String branchType;

	private List<BranchStorage> storageList;

	private Date businessDate;

	private String isAuditTime;
	private String itemName;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		endDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		businessDate = endDate;
		shopLst = branchBean.queryById(getLoginBranchId());
		if (shopLst.size() > 0) {
			storageList = branchStorageBean.query("", branchType);
		}

		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			shippingHeader = shippingHeaderBean.queryById(formId);

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
		}
		return SUCCESS;
	}

	/**
	 * 确认页面
	 * 
	 * @return
	 */
	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		String formNote = "";
		if (!TextUtil.isEmpty(formId)) {

			if (shippingHeader != null) {
				formNote = shippingHeader.getFormNote();
			}

			shippingHeader = shippingHeaderBean.queryById(formId);

			shippingHeader.setFormNote(formNote);
			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchType)) {
			branchType = BranchType.RESTAURANT;
		}
		int total = !TextUtil.isEmpty(isAuditTime) ? distriStockInBean.countByAuditTime(startDate, endDate, branchId,
				branchType) : distriStockInBean.count(startDate, endDate, branchId);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			// isAuditTime不为空执行第一个sql ，itemName为空执行第二个sql，itemName不为空执行第三个sql
			List<ShippingHeader> headerLst = !TextUtil.isEmpty(isAuditTime) ? distriStockInBean.queryByAuditTime(
					startDate, endDate, branchId, branchType, getStart(), getEnd())
					: TextUtil.isEmpty(itemName) ? distriStockInBean.query(startDate, endDate, branchId, getStart(),
							getEnd()) : distriStockInBean.query(startDate, endDate, branchId, itemName, getStart(),
							getEnd());
			int rownumber = getStart();
			for (ShippingHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);

				String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);
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

	/* 已入库的配送单查询 */
	public void doInputQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchType)) {
			branchType = BranchType.RESTAURANT;
		}
		int total = distriStockInBean.inputCount(startDate, endDate, branchId, branchType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ShippingHeader> headerLst = distriStockInBean.inputQuery(startDate, endDate, branchId, branchType,
					getStart(), getEnd());
			int rownumber = getStart();
			for (ShippingHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);

				String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);
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
		List<ShippingDetail> detailLst = shippingDetailBean.query(formId);
		int rownumber = 1;
		for (ShippingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			// 下面几个字段用于生成反审核单时初始化
			json.put("antiauditReceiveCount", detail.getReceiveCount());// 与原实收数保持一致
			json.put("antiauditPayAmt", 0);

			if (detail.getDeliveryCount() == null) {
				json.put("deliveryCount", detail.getShippingCount());// 出货单没有实发数
			}

			// 下面几个字段用于生成退货单时初始化
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

	/**
	 * 填充配送单
	 * 
	 */
	public void fillBill() throws Exception {
		String userId = getLoginUserId();
		shippingHeader.setInputerId(userId);
		shippingHeader.setInputer(getLoginUsername());
		String branchId = shippingHeader.getRequesterId();
		String branchType = branchBean.GetBranchById(branchId).getBranchType();
		if (branchType.equals(BranchType.OUTERORDER)) {
			distributionService.outerFillBill(userId, shippingHeader, jsonData);
		} else {
			distributionService.fillBill(userId, shippingHeader, jsonData);
		}

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 央厂半成品入库，同时生成一个已审核入库单
	 * 
	 */
	public void inputBill() throws Exception {
		String userId = getLoginUserId();
		String userName = getLoginUsername();
		shippingHeader.setInputerId(userId);
		shippingHeader.setInputer(userName);
		String branchId = shippingHeader.getRequesterId();
		Branch branch = branchBean.GetBranchById(branchId);
		String branchType = branchBean.GetBranchById(branchId).getBranchType();
		Date restaurantDate = branchBean.GetBranchById(branchId).getBusinessDate();

		inputHeader = new InputHeader();

		if (branchType.equals(BranchType.OUTERORDER)) {
			inputHeader.setFormType(FormType.INPUT_OUTER);
			distributionService.outerFillBill(userId, shippingHeader, jsonData);
		} else {
			inputHeader.setFormType(FormType.INPUT_PRODUCE);
			distributionService.fillBill(userId, shippingHeader, jsonData);
		}
		String formId = FormUtil.generateFormId("LK", branchId, restaurantDate);
		inputHeader.setFormId(formId);
		inputHeader.setFormRefId(shippingHeader.getFormId());
		inputHeader.setInputer(userName);
		inputHeader.setInputerId(userId);
		inputHeader.setInputDepartmentId(branchId);
		inputHeader.setInputDepartment(branch.getBranchName());
		BranchStorage branchStorage = branchStorageBean.queryMainStore(branchId);
		inputHeader.setStorage(branchStorage.getStorageName());
		inputHeader.setStorageId(branchStorage.getStorageId());
		inputHeader.setProviderId(shippingHeader.getProviderId());
		inputHeader.setProvider(shippingHeader.getProvider());
		inputHeader.setInputTime(restaurantDate);
		inputHeader.setAuditor(userName);
		inputHeader.setAuditorId(userId);
		inputHeader.setAuditTime(restaurantDate);

		formId = distributionService.inputBill(userId, inputHeader, jsonData);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", formId);
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

	public String getBranchName() {
		return branchName;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setDistributionService(DistributionService distributionService) {
		this.distributionService = distributionService;
	}

	public void setDistriStockInBean(DistriStockInBean distriStockInBean) {
		this.distriStockInBean = distriStockInBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setIsAuditTime(String isAuditTime) {
		this.isAuditTime = isAuditTime;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

}
