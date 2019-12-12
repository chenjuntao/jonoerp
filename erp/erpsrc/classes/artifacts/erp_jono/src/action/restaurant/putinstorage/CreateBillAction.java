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
 * 说明： 外部入库单生成
 */
package action.restaurant.putinstorage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputHeaderBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.module.restaurant.DirectStockInBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.FormStatus;
import pojo.form.InputHeader;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.restaurant.putinstorage.BillManageService;
import action.common.BaseAction;

import com.tanry.business.form.FormType;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.LogType;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BillManageService billManageService;
	private BranchBean branchBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private PurchasingDetailBean purchasingDetailBean;
	private DirectStockInBean directStockInBean;
	private FormStatusBean formStatusBean;
	private InputHeaderBean inputHeaderBean;
	private List<Map> shopLst;
	private String shopName;
	private String branchId;

	private String formId;
	private String formNote;
	private String deliveryType;

	private PurchasingHeader purchasingHeader;
	private InputHeader inputHeader;
	private Date inputTime;
	private Date formTime;
	private String supplierId;

	private Date auditTime;

	private String jsonData;
	private String itemName;

	private BranchStorageBean branchStorageBean;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String estimateView() throws NoPrivilegeException, SQLException, NoConnection {
		inputHeader = new InputHeader();
		inputHeader.setInputer(getLoginUsername());
		inputHeader.setInputerId(getLoginUserId());
		formTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();

		return SUCCESS;
	}

	public String commitView() throws NoPrivilegeException, SQLException, NoConnection {
		PurchasingHeader header = purchasingHeaderBean.queryById(formId);
		if (inputHeader == null) {
			inputHeader = new InputHeader();
		}
		inputHeader.setFormRefId(header.getFormId());
		Date businessDate = branchBean.GetBranchById(branchId).getBusinessDate();
		String formId = FormUtil.generateFormId("LK", branchId, businessDate);
		inputHeader.setFormId(formId);

		inputHeader.setInputDepartmentId(branchId);
		Branch branch = branchBean.GetBranchById(branchId);
		inputHeader.setInputDepartment(branch.getBranchName());
		inputHeader.setInputerId(getLoginUserId());
		inputHeader.setInputer(getLoginUsername());
		inputTime = businessDate;
		inputHeader.setProviderId(header.getProviderId());
		inputHeader.setProvider(header.getProvider());

		BranchStorage branchStorage = branchStorageBean.queryMainStore(getLoginBranchId());
		inputHeader.setStorageId(branchStorage.getStorageId());// 设置仓库
		inputHeader.setStorage(branchStorage.getStorageName());
		return SUCCESS;
	}

	/**
	 * 显示当前门店所有未入库的采购单，以树形控件组织，供应商-〉采购单
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "采购单");
		arr.add(root);
		List<Map> nodeLst;
		if (TextUtil.isEmpty(itemName)) {
			nodeLst = directStockInBean.queryUnStorage(branchId);
		} else {
			nodeLst = directStockInBean.queryUnStorage(branchId, itemName);
		}

		for (Map node : nodeLst) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			jsonObject.put("type", "direct");
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示采购单信息，主从表信息一次性查出来 以后使用采购单中的方法
	 * 
	 * @see com.tanry.business.module.lc.request.action.PurchaseManageAction.
	 *      queryBill()
	 */
	@Deprecated
	public void queryBill() throws NoPrivilegeException, SQLException, NoConnection {
		PurchasingHeader header = purchasingHeaderBean.queryById(formId);
		List<PurchasingDetail> detailLst = purchasingDetailBean.query(formId);
		int tager = inputHeaderBean.count(formId);

		JSONObject result = new JSONObject();
		JSONObject jHeader = JSONObject.fromObject(header);
		jHeader.put("receiveTime", getDateStr(header.getReceiveTime()));
		jHeader.put("formTime", getDateStr(header.getFormTime()));
		jHeader.put("auditTime", getDateStr(header.getAuditTime()));
		if (tager > 0) {
			jHeader.put("tager", "已生成");
		} else {
			jHeader.put("tager", "未生成");
		}
		result.put("header", jHeader);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (PurchasingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			json.put("expiredTime", getDateStr(detail.getExpiredTime()));
			arr.add(json);
			rownumber++;
		}
		result.put("detailLst", arr);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取采购单明细列表，生成入库单时使用
	 */
	@Deprecated
	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		List<PurchasingDetail> detailLst = purchasingDetailBean.query(formId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (PurchasingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			Double orderCount = detail.getItemCount();
			json.put("orderCount", orderCount);
			json.put("receiveCount", orderCount);
			json.put("differentCount", 0);
			json.put("payAmt", 0);
			json.put("expiredTime", getDateStr(detail.getExpiredTime()));
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		JSONObject result = new JSONObject();
		result.put("rows", arr);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String formId = billManageService.saveBill(getLoginUserId(), inputHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", formId);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 手动生成入库单
	 */
	public void manualSave() throws NoPrivilegeException, SQLException, NoConnection {
		inputHeader.setFormType(FormType.INPUT_MANUAL);

		String branchId = getLoginBranchId();
		Branch branch = branchBean.GetBranchById(branchId);
		inputHeader.setInputDepartmentId(branchId);
		inputHeader.setInputDepartment(branch.getBranchName());

		inputHeader.setInputerId(getLoginUserId());
		inputHeader.setInputer(getLoginUsername());
		inputTime = branchBean.GetBranchById(branchId).getBusinessDate();
		inputHeader.setInputTime(inputTime);

		billManageService.saveBill(getLoginUserId(), inputHeader, jsonData);
		String formId = inputHeader.getFormId();
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", formId);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断一个采购单是否有对应的入库单正在审核中（即未审核），这时不允许下次入库
	 */
	public void checkStatus() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = directStockInBean.checkAudit(formId);
		JSONObject result = new JSONObject();
		result.put("isInAudit", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 采购单直接入库结案
	 */
	public void doFinish() throws NoPrivilegeException, SQLException, NoConnection {
		FormStatus fstatus = new FormStatus(formId, "已结案", getLoginUserId());
		formStatusBean.saveEntity(fstatus);

		OperateLogUtil.record(formId, LogType.FINISH, "采购入库单结案");

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

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBillManageService(BillManageService billManageService) {
		this.billManageService = billManageService;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setDirectStockInBean(DirectStockInBean directStockInBean) {
		this.directStockInBean = directStockInBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public PurchasingHeader getPurchasingHeader() {
		return purchasingHeader;
	}

	public void setPurchasingHeader(PurchasingHeader purchasingHeader) {
		this.purchasingHeader = purchasingHeader;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public String getInputTime() {
		return getDateStr(inputTime);
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getFormNote() {
		return formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
