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
package action.restaurant.preject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormLockBean;
import logic.form.FormStatusBean;
import logic.form.InputHeaderBean;
import logic.form.ReturnGoodsHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.module.restaurant.InputReturnBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.FormStatus;
import pojo.form.InputHeader;
import pojo.form.ReturnGoodsDetail;
import pojo.form.ReturnGoodsHeader;
import pojo.form.ShippingHeader;
import pojo.form.TransferHeader;
import pojo.store.BranchStorage;
import service.restaurant.dreject.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BillManageService djBillManageService;
	private BranchBean branchBean;
	private InputReturnBean inputReturnBean;
	private InputHeaderBean inputHeaderBean;
	private ShippingHeaderBean shippingHeaderBean;
	private ReturnGoodsHeaderBean returnGoodsHeaderBean;
	private FormStatusBean formStatusBean;
	private FormLockBean formLockBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String queryType;
	private String formType;
	private String formId;

	private ReturnGoodsHeader returnHeader;
	private InputHeader inputHeader;
	private ShippingHeader shippingHeader;

	private Date returnTime;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private Date inputTime;
	private String branchFlag;

	/**
	 * 用于浏览
	 */
	private String formStatus;

	private String jsonData;
	private List<Map> shopLst;

	private String branchType;

	private List<BranchStorage> storageList;

	private BranchStorageBean branchStorageBean;

	private String storageId;

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchType() {
		return branchType;
	}

	private Date businessDate;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		endDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取中央工厂当前的营业时间
		businessDate = endDate;
		startDate = DateTimeUtil.addDays(endDate, -1);
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = startDate = endDate = null;
		// shopLst = branchBean.listShop(branchType);
		shopLst = branchBean.queryById(getLoginBranchId());

		if (shopLst.size() > 0) {
			storageList = branchStorageBean.query(getLoginBranchId(), branchType);
		}

		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public void auditById() throws NoPrivilegeException, SQLException, NoConnection {
		Date businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		djBillManageService.auditById(formId, getLoginUserId(), businessDate);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = inputReturnBean.queryById(formId);
			returnTime = returnHeader.getReturnTime();

			inputHeader = inputHeaderBean.queryById(returnHeader.getFormRefId());

			auditTime = inputHeader.getAuditTime();
			inputTime = inputHeader.getInputTime();
		}
		formLockBean.addLock(formId);// 加锁
		return SUCCESS;
	}

	public String outView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = inputReturnBean.queryOutById(formId);
			returnTime = returnHeader.getReturnTime();
			shippingHeader = shippingHeaderBean.queryById(returnHeader.getFormRefId());
			auditTime = shippingHeader.getAuditTime();
			receiveTime = shippingHeader.getReceiveTime();
			formStatus = formStatusBean.getCurrentStatus(formId);
		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = inputReturnBean.queryById(formId);
			returnTime = returnHeader.getReturnTime();

			inputHeader = inputHeaderBean.queryById(returnHeader.getFormRefId());

			auditTime = inputHeader.getAuditTime();
			inputTime = inputHeader.getInputTime();

			formStatus = formStatusBean.getCurrentStatus(formId);
		}
		return SUCCESS;
	}

	public String rejectView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = inputReturnBean.queryById(formId);
			returnTime = returnHeader.getReturnTime();
			String lcCode = getLoginBranchId();
			auditTime = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取当前的营业时间
			returnHeader.setAuditTime(auditTime);
			inputHeader = inputHeaderBean.queryById(returnHeader.getFormRefId());

			inputTime = inputHeader.getInputTime();

			formStatus = formStatusBean.getCurrentStatus(formId);
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = inputReturnBean.count(startDate, endDate, branchId, storageId, queryType, branchType);
		setTotal(total);
		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ReturnGoodsHeader> headerLst = inputReturnBean.query(startDate, endDate, branchId, storageId,
					queryType, branchType, getStart(), getEnd());
			int rownumber = getStart();
			for (ReturnGoodsHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String returnTime = DateTimeUtil.getDateStr(header.getReturnTime());
				json.put("returnTime", returnTime);

				String inputTime = DateTimeUtil.getDateStr(header.getInputTime());
				json.put("inputTime", inputTime);

				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formStatus", formStatus);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile()) {
				double allPayAmt = inputReturnBean.sum(startDate, endDate, branchId, storageId, queryType, branchType);
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

	public void doOutQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = inputReturnBean.countOut(startDate, endDate, formType, queryType);
		setTotal(total);
		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ReturnGoodsHeader> headerLst = inputReturnBean.queryOut(startDate, endDate, formType, queryType,
					getStart(), getEnd());
			int rownumber = getStart();
			for (ReturnGoodsHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String returnTime = DateTimeUtil.getDateStr(header.getReturnTime());
				json.put("returnTime", returnTime);

				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);

				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formStatus", formStatus);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile()) {
				double allPayAmt = inputReturnBean.sum(startDate, endDate, branchId, storageId, queryType, branchType);
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

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<ReturnGoodsDetail> detailLst = inputReturnBean.queryDetail(formId);
		int rownumber = 1;
		for (ReturnGoodsDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			// 餐厅使用的是标准价，物流中心使用采购价
			String branchType = getLoginBranchType();
			if (!BranchType.RESTAURANT.equals(branchType)) {
				json.put("itemUnitPrice", detail.getReceivePrice());
			}
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryOutDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<ReturnGoodsDetail> detailLst = inputReturnBean.queryOutDetail(formId);

		int rownumber = 1;
		for (ReturnGoodsDetail detail : detailLst) {
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
	 * 确认退货
	 */
	public void doReturn() throws NoPrivilegeException, SQLException, NoConnection {
		if (BranchType.OUTERORDER.equals(branchType)) {
			djBillManageService.doReturn(getLoginUserId(), returnHeader, getLoginBranchId(), "inputReturn", "WBTH");
		} else {
			djBillManageService.doReturn(getLoginUserId(), returnHeader, getLoginBranchId(), "inputReturn", "CGTH");
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
	 * 物流中心退货，退货单状态变为已处理，中央工厂确认
	 */
	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		formStatusBean.saveEntity(new FormStatus(returnHeader.getFormId(), "已确认", getLoginUserId()));

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

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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

	public ReturnGoodsHeader getReturnHeader() {
		return returnHeader;
	}

	public void setReturnHeader(ReturnGoodsHeader returnHeader) {
		this.returnHeader = returnHeader;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
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

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setInputReturnBean(InputReturnBean inputReturnBean) {
		this.inputReturnBean = inputReturnBean;
	}

	public void setDjBillManageService(BillManageService djBillManageService) {
		this.djBillManageService = djBillManageService;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}

	public String getFormStatus() {
		return formStatus;
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

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

	public void setReturnGoodsHeaderBean(ReturnGoodsHeaderBean returnGoodsHeaderBean) {
		this.returnGoodsHeaderBean = returnGoodsHeaderBean;
	}

}
