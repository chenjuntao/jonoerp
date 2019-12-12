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
package action.restaurant.antiaudit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.ShippingAntiauditDetailBean;
import logic.form.ShippingAntiauditHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.module.hq.MonthlySettleRecordBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ShippingAntiauditDetail;
import pojo.form.ShippingAntiauditHeader;
import pojo.form.ShippingHeader;
import pojo.store.BranchStorage;
import service.restaurant.antiaudit.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BillManageService antiBillManageService;
	private BranchBean branchBean;
	private ShippingAntiauditHeaderBean shippingAntiauditHeaderBean;
	private ShippingAntiauditDetailBean shippingAntiauditDetailBean;
	private MonthlySettleRecordBean monthlySettleRecordBean;
	private ShippingHeaderBean shippingHeaderBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String queryType;

	private String formRefId;

	private ShippingAntiauditHeader antiauditHeader;
	private ShippingHeader shippingHeader;
	private Date antiauditTime;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private Date inputTime;

	private String jsonData;
	private List<Map> shopLst;

	private String branchType;

	private String branchCondition;

	private List<BranchStorage> storageList;

	private BranchStorageBean branchStorageBean;

	private String storageId;

	private Date businessDate;
	private Date lastDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		if (BranchTypeEnum.LOGISTICSCENTER.toString().equals(branchType)) {
			shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
		} else {
			shopLst = branchBean.queryById(getLoginBranchId());
		}

		branchCondition = "订货部门";
		if (BranchTypeEnum.LOGISTICSCENTER.toString().equals(branchType)) {
			branchCondition = "配送部门";
		}

		if (shopLst.size() > 0) {
			storageList = branchStorageBean.query(getLoginBranchId(), branchType);
		}

		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formRefId)) {
			antiauditHeader = shippingAntiauditHeaderBean.queryById(formRefId);
			antiauditTime = antiauditHeader.getAntiauditTime();

			shippingHeader = shippingHeaderBean.queryById(antiauditHeader.getFormRefId());

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getInputTime();

		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formRefId)) {
			antiauditHeader = shippingAntiauditHeaderBean.queryById(formRefId);
			antiauditTime = antiauditHeader.getAntiauditTime();

			shippingHeader = shippingHeaderBean.queryById(antiauditHeader.getFormRefId());

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getInputTime();
		}
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formRefId)) {
			antiauditHeader = shippingAntiauditHeaderBean.queryById(formRefId);
			antiauditTime = antiauditHeader.getAntiauditTime();
			String lcCode = getLoginBranchId();
			auditTime = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取当前的营业时间
			shippingHeader = shippingHeaderBean.queryById(antiauditHeader.getFormRefId());

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			inputTime = shippingHeader.getInputTime();
			lastDate = monthlySettleRecordBean.queryLastSettleMonth();
		}
		return SUCCESS;
	}

	public String confirmView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formRefId)) {
			antiauditHeader = shippingAntiauditHeaderBean.queryById(formRefId);
			antiauditTime = antiauditHeader.getAntiauditTime();

			shippingHeader = shippingHeaderBean.queryById(antiauditHeader.getFormRefId());

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getInputTime();
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = shippingAntiauditHeaderBean.count(startDate, endDate, branchId, storageId, queryType, branchType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			shippingAntiauditHeaderBean.formQuery(branchId, storageId, queryType, branchType);
			List<ShippingAntiauditHeader> headerLst = shippingAntiauditHeaderBean.query(startDate, endDate, branchId,
					storageId, queryType, branchType, getStart(), getEnd());
			int rownumber = getStart();
			for (ShippingAntiauditHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String antiauditTime = DateTimeUtil.getDateStr(header.getAntiauditTime());
				json.put("antiauditTime", antiauditTime);

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

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<ShippingAntiauditDetail> detailLst = shippingAntiauditDetailBean.query(formRefId);
		int rownumber = 1;
		for (ShippingAntiauditDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		if (true) {

		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		antiBillManageService.updateBill(getLoginUserId(), antiauditHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		antiBillManageService.deleteBill(formRefId);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除反审核数据，修改相应的配送单为已入库状态
	 */
	public void doRevert() throws NoPrivilegeException, SQLException, NoConnection {
		antiBillManageService.revertBill(formRefId, getLoginUserId());
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		antiBillManageService.auditBill(getLoginUserId(), antiauditHeader, branchId, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public void doRepeal() throws NoPrivilegeException, SQLException, NoConnection {
		antiBillManageService.doRepeal(getLoginUserId(), antiauditHeader, branchId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		antiBillManageService.confirmBill(getLoginUserId(), antiauditHeader, branchId, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
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

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public ShippingAntiauditHeader getAntiauditHeader() {
		return antiauditHeader;
	}

	public void setAntiauditHeader(ShippingAntiauditHeader antiauditHeader) {
		this.antiauditHeader = antiauditHeader;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
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

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setShippingAntiauditHeaderBean(ShippingAntiauditHeaderBean shippingAntiauditHeaderBean) {
		this.shippingAntiauditHeaderBean = shippingAntiauditHeaderBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setShippingAntiauditDetailBean(ShippingAntiauditDetailBean shippingAntiauditDetailBean) {
		this.shippingAntiauditDetailBean = shippingAntiauditDetailBean;
	}

	public void setAntiBillManageService(BillManageService antiBillManageService) {
		this.antiBillManageService = antiBillManageService;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
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
