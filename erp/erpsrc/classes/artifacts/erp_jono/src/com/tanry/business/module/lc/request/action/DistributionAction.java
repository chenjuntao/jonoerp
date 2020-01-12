/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 3, 2014 by liyzh
 * Last edited on Nov 3, 2014 by liyzh
 * 
 * 说明：  物流中心配送单生成
 */
package com.tanry.business.module.lc.request.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.RequestHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.module.lc.CreateDistributionBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import pojo.form.RequestHeader;
import pojo.form.ShippingHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.business.module.lc.request.service.DistributionService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class DistributionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BranchStorageBean branchStorageBean;
	private RequestHeaderBean requestHeaderBean;
	private DistributionService lcDistributionService;
	private CreateDistributionBean createDistributionBean;
	private ShippingHeaderBean shippingHeaderBean;

	private String branchId;
	private String branchName;
	private BranchStorage branchStorage;
	private Date startDate;
	private Date endDate;
	private Date businessDate;
	private Integer currentVersion;

	private String formId;
	private String formRefId;

	private ShippingHeader shippingHeader;

	private String ids;

	private String jsonData;
	private List<Map> shopLst;
	private List<BranchStorage> storeLst;

	private Date formTime;
	private Date receiveTime;
	private String parmFlag;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		shopLst = branchBean.listShopByType(BranchTypeEnum.R_F_O);
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		RequestHeader rHeader = requestHeaderBean.queryById(formRefId);
		shippingHeader = new ShippingHeader();
		shippingHeader.setRequesterId(rHeader.getBuyerId());
		shippingHeader.setRequester(rHeader.getBuyerName());
		shippingHeader.setRequestAddress(rHeader.getBuyerAddress());

		String lcCode = getLoginBranchId();
		Branch lcBranch = branchBean.GetBranchById(lcCode);
		shippingHeader.setProviderId(lcCode);
		shippingHeader.setProvider(lcBranch.getBranchName());

		receiveTime = rHeader.getReceiveTime();// 默认配送日期为要货单的到货日期

		storeLst = branchStorageBean.query(lcCode, BranchTypeEnum.LOGISTICSCENTER.toString());
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		branchId = shippingHeader.getRequesterId();// 根据要货餐厅编号生成单据号
		formTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间

		receiveTime = shippingHeader.getReceiveTime();

		shippingHeader.setFormMakerId(getLoginUserId());
		shippingHeader.setFormMaker(getLoginUsername());
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		// 应测试人员要求改成按制单时间查询
		List<RequestHeader> headerLst = createDistributionBean.query(startDate, endDate, branchId);
		int rownumber = getStart();
		JSONArray arr = new JSONArray();
		for (RequestHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);
			String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
			json.put("auditTime", auditTime);
			String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
			json.put("receiveTime", receiveTime);

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

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> detailLst = createDistributionBean.queryDetail(formId);

		int rownumber = 1;
		JSONArray darr = new JSONArray();
		JSONArray uarr = new JSONArray();
		for (Map detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);

			String deliveryType = (String) detail.get("deliveryType");
			if (FormConstant.UNIFIED_DELIVERY.equals(deliveryType)) {
				String deliveryUnit = (String) detail.get("deliveryUnit");
				json.put("packagingUnit", deliveryUnit);
				BigDecimal unitConvertFactor = BigDecimal.valueOf((Double) detail.get("unitConvertFactor"));
				json.put("packagingFactor", unitConvertFactor);

				BigDecimal orderCount = BigDecimal.valueOf((Double) detail.get("orderCount"));
				BigDecimal packagingCount = orderCount.divide(unitConvertFactor, 0, BigDecimal.ROUND_UP); // 默认向上取整，以后可能修改为参数可配置
				json.put("packagingCount", packagingCount);
				BigDecimal shippingCount = packagingCount.multiply(unitConvertFactor);
				json.put("shippingCount", shippingCount);
				// 按配送数重新计算金额
				BigDecimal itemUnitPrice = BigDecimal.valueOf((Double) detail.get("itemUnitPrice"));
				BigDecimal payAmt = itemUnitPrice.multiply(shippingCount);
				json.put("payAmt", payAmt);
				uarr.add(json);
			} else {
				darr.add(json);
			}
			rownumber++;
		}
		JSONObject result = new JSONObject();
		result.put("darr", darr);
		result.put("uarr", uarr);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询某天某门店的配送单是否已经存在
	 */
	public void exist() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = shippingHeaderBean.exist(businessDate, branchId);

		JSONObject result = new JSONObject();
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		formId = lcDistributionService.createBill(getLoginUserId(), formRefId, shippingHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("formId", formId);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void batchCreate() throws NoPrivilegeException, SQLException, NoConnection {
		String lcCode = getLoginBranchId();

		formTime = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取物流中心当前的营业时间
		branchStorage = branchStorageBean.queryMainStore(lcCode);

		Branch lcBranch = branchBean.GetBranchById(lcCode);
		shippingHeader.setProviderId(lcCode);
		shippingHeader.setProvider(lcBranch.getBranchName());

		shippingHeader.setFormTime(formTime);
		shippingHeader.setOutStorage(branchStorage.getStorageName());
		shippingHeader.setOutStorageId(branchStorage.getStorageId());
		shippingHeader.setFormMakerId(getLoginUserId());
		shippingHeader.setFormMaker(getLoginUsername());

		List<String> sidLst = lcDistributionService.batchCreate(ids, shippingHeader);
		String formIds = StringUtils.join(sidLst, ",");

		JSONObject result = new JSONObject();
		result.put("sidLst", formIds);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		lcDistributionService.deleteBill(formId, getLoginUserId(), currentVersion);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doInvalid() throws NoPrivilegeException, SQLException, NoConnection {
		lcDistributionService.invalidBill(formId, getLoginUserId(), currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpdate() throws NoPrivilegeException, SQLException, NoConnection {
		shippingHeader.setReceiveTime(receiveTime);
		lcDistributionService.updateBill(getLoginUserId(), shippingHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 中央工厂出库修改
	public void updateDeliveryCount() throws NoPrivilegeException, SQLException, NoConnection {
		lcDistributionService.updateDeliveryCount(getLoginUserId(), shippingHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 中央工厂出库审核
	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		shippingHeader.setAuditTime(branchBean.GetBranchById(getLoginBranchId()).getBusinessDate());
		shippingHeader.setProviderId(getLoginBranchId());
		shippingHeader.setFormType("PRODUCT");
		lcDistributionService.auditBill(getLoginUserId(), shippingHeader, jsonData);
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

	public List<Map> getShopLst() {
		return shopLst;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getFormRefId() {
		return formRefId;
	}

	public void setFormRefId(String formRefId) {
		this.formRefId = formRefId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public void setLcDistributionService(DistributionService lcDistributionService) {
		this.lcDistributionService = lcDistributionService;
	}

	public void setCreateDistributionBean(CreateDistributionBean createDistributionBean) {
		this.createDistributionBean = createDistributionBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public BranchStorage getBranchStorage() {
		return branchStorage;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public Integer getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public void setParmFlag(String parmFlag) {
		this.parmFlag = parmFlag;
	}

}
