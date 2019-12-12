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
 * 说明： 餐厅配送反审核生成
 */
package action.restaurant.antiaudit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ShippingHeaderBean;
import logic.module.hq.MonthlySettleRecordBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ShippingAntiauditHeader;
import pojo.form.ShippingHeader;
import pojo.store.BranchStorage;
import service.restaurant.antiaudit.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ShippingHeaderBean shippingHeaderBean;
	private MonthlySettleRecordBean monthlySettleRecordBean;
	private BranchBean branchBean;
	private BranchStorageBean branchStorageBean;
	private BillManageService billManageService;

	private List<Map> shopLst;
	private String branchId;

	private Date startDate;
	private Date endDate;

	private String formId;
	private String queryType;

	private ShippingHeader shippingHeader;
	private ShippingAntiauditHeader antiauditHeader;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private Date inputTime;
	private Date antiauditTime;
	private Date lastDate;
	private String itemName;
	private String jsonData;

	private String branchType;

	private String branchCondition;

	private List<BranchStorage> storageList;

	private String storageId;

	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		lastDate = monthlySettleRecordBean.queryLastSettleMonth();
		lastDate = DateTimeUtil.addDays(lastDate, 1);
		if (BranchTypeEnum.LOGISTICSCENTER.toString().equals(branchType)) {
			shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
		} else {
			shopLst = branchBean.queryById(getLoginBranchId());
		}

		branchCondition = "订货部门";
		if (BranchTypeEnum.LOGISTICSCENTER.toString().equals(branchType)) {
			branchCondition = "配送部门";
		}
		if (shippingHeader != null) {
			receiveTime = shippingHeader.getReceiveTime();
		}

		if (shopLst.size() > 0) {
			storageList = branchStorageBean.query(getLoginBranchId(), branchType);
		}
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			shippingHeader = shippingHeaderBean.queryById(formId);

			String branchId = shippingHeader.getRequesterId();
			String branch = shippingHeader.getRequester();

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getInputTime();

			antiauditTime = branchBean.GetBranchById(branchId).getBusinessDate();
			antiauditHeader = new ShippingAntiauditHeader();
			antiauditHeader.setAntiauditBranchId(branchId);
			antiauditHeader.setAntiauditBranch(branch);
			antiauditHeader.setAntiauditorId(getLoginUserId());
			antiauditHeader.setAntiauditor(getLoginUsername());
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
		antiauditTime = antiauditHeader.getAntiauditTime();
		return SUCCESS;
	}

	/**
	 * 获取适合反审核或退货的配送单
	 * 
	 * 1. 已经进行差异处理的配送单不能再反审核 2. 反审核只影响餐厅的库存
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(queryType)) {
			queryType = "antiaudit";
		}
		int total = shippingHeaderBean.count(startDate, endDate, branchId, queryType, "request", branchType, storageId);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ShippingHeader> headerLst;
			if (TextUtil.isEmpty(itemName)) {
				headerLst = shippingHeaderBean.query(startDate, endDate, branchId, queryType, "request", branchType,
						storageId, getStart(), getEnd()); // 已入库
			} else {
				headerLst = shippingHeaderBean.query(startDate, endDate, branchId, queryType, "request", branchType,
						storageId, itemName, getStart(), getEnd()); // 已入库
			}
			int rownumber = getStart();
			for (ShippingHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);
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

	/**
	 * 生成配送反审核单
	 */
	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String userId = getLoginUserId();
		billManageService.saveBill(userId, antiauditHeader, jsonData, branchType, shippingHeader);

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

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
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

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public String getAntiauditTime() {
		return DateTimeUtil.getDateStr(antiauditTime);
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

	public ShippingAntiauditHeader getAntiauditHeader() {
		return antiauditHeader;
	}

	public void setAntiauditHeader(ShippingAntiauditHeader antiauditHeader) {
		this.antiauditHeader = antiauditHeader;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public String getBranchCondition() {
		return branchCondition;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setBillManageService(BillManageService billManageService) {
		this.billManageService = billManageService;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getLastDate() {
		return DateTimeUtil.getDateStr(lastDate);
	}

	public void setMonthlySettleRecordBean(MonthlySettleRecordBean monthlySettleRecordBean) {
		this.monthlySettleRecordBean = monthlySettleRecordBean;
	}

}
