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
 * 说明：调价单管理
 */
package com.tanry.business.module.hq.priceadjust;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PriceAdjustDetailBean;
import logic.module.hq.PurchasePriceAdjustBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PriceAdjustDetail;
import pojo.form.PriceAdjustHeader;
import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.business.module.hq.priceadjust.service.BillManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.constant.PriceType;

import dao.price.PriceAdjustDao;

/**
 * @author charles_1018
 *
 */
public class PurchasePriceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private PurchasePriceAdjustBean purchasePriceAdjustBean;
	private PriceAdjustDetailBean priceAdjustDetailBean;
	private BillManageService pBillManageService;
	private PriceAdjustDao priceAdjustDao;

	/**
	 * 中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单表头
	 */
	private String adjustType;
	/**
	 * 供应商或者中央工厂售价调价单，即物流中心的采购价，中央工厂只对自己所有的半成品调价
	 */
	private String branchType;
	private String queryType;
	private Date startDate;
	private Date endDate;

	private String formId;

	private PriceAdjustHeader adjustHeader;
	private Date effectTime;
	private Date formTime;
	private Date auditTime;
	private String branchId;

	private String jsonData;

	private String itemType;
	private String itemName;

	private FormStatusBean formStatusBean;
	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;
	private String status;

	private String itemId;
	private List<Map> shopLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		startDate = endDate = getLoginBranchDate();
		String branchType = "RESTAURANT,CENTRALFACTORY,LOGISTICSCENTER";
		shopLst = branchBean.listShopByType(branchType);
		return SUCCESS;
	}

	private Date getLoginBranchDate() throws NoPrivilegeException, SQLException, NoConnection {
		return branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
	}

	public String addView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = getLoginBranchDate();
		effectTime = formTime;
		adjustType = PriceType.PURCHASE;
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			adjustHeader = purchasePriceAdjustBean.queryById(formId);
			formTime = adjustHeader.getFormTime();
			effectTime = adjustHeader.getEffectTime();
			String supplierId = adjustHeader.getSupplierId();

			if (PriceType.PURCHASE.equals(adjustHeader.getAdjustType()) && !TextUtil.isEmpty(supplierId)) {
				Branch supplier = branchBean.GetBranchById(supplierId);
				branchType = supplier.getBranchType();
			}
		}
		startDate = getLoginBranchDate();
		preVersion = operationVersionBean.queryVersion(formId).getVersion();
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			status = formStatusBean.getCurrentStatus(formId);
			adjustHeader = purchasePriceAdjustBean.queryById(formId);

			formTime = adjustHeader.getFormTime();
			auditTime = adjustHeader.getAuditTime();
			effectTime = adjustHeader.getEffectTime();
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = purchasePriceAdjustBean.count(startDate, endDate, adjustType, itemId, queryType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<PriceAdjustHeader> headerLst = purchasePriceAdjustBean.query(startDate, endDate, adjustType,
					queryType, itemId, getStart(), getEnd());
			int rownumber = getStart();
			for (PriceAdjustHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String effectTime = DateTimeUtil.getDateStr(header.getEffectTime());
				json.put("effectTime", effectTime);
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

	public void queryPItem() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(purchasePriceAdjustBean.queryPItem(formId));

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryZItem() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(purchasePriceAdjustBean.queryZItem(formId));

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {

		JSONArray arr = new JSONArray();
		List<PriceAdjustDetail> detailLst = priceAdjustDetailBean.query(formId);
		int rownumber = 1;
		for (PriceAdjustDetail detail : detailLst) {
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
	 * 查询商品信息及相应的价格、包装因子
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(itemType)) {
			itemType = ItemType.RAW;
		}

		List<PriceAdjustDetail> detailLst = priceAdjustDetailBean.queryItem(itemType, itemName, adjustType);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (PriceAdjustDetail detail : detailLst) {
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
	 * 根据物料Id查询当天进出库情况
	 * 
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	public void queryItemId() throws NoPrivilegeException, SQLException, NoConnection {
		String sDate = DateTimeUtil.getDateStr(startDate);
		List<Map> detailLst = priceAdjustDao.queryItem(sDate, itemId, branchId);
		JSONArray arr = new JSONArray();
		int rownumber = 1;
		int total = detailLst.size();
		setTotal(total);
		if (total > 0) {
			for (Map detail : detailLst) {
				JSONObject json = JSONObject.fromObject(detail);
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

	public void createBill() throws NoPrivilegeException, SQLException, NoConnection {
		pBillManageService.createBill(getLoginUserId(), adjustHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBill() throws NoPrivilegeException, SQLException, NoConnection {
		pBillManageService.updateBill(getLoginUserId(), adjustHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpdate() throws NoPrivilegeException, SQLException, NoConnection {
		pBillManageService.doUpdate();

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doTest() throws NoPrivilegeException, SQLException, NoConnection {
		String contents = priceAdjustDao.computeStorage();// 邮件内容查询

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("contents", contents);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doBack() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchId) || branchId.equals("-1")) {
			branchId = getLoginBranchId();
		}
		String branchType = branchBean.GetBranchById(branchId).getBranchType();
		pBillManageService.doBack(DateTimeUtil.getDateTime(startDate, ""), DateTimeUtil.getDateTime(endDate, ""),
				branchId, branchType);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		adjustHeader.setAuditor(getLoginUsername());
		adjustHeader.setAuditTime(new Date());
		pBillManageService.auditBill(getLoginUserId(), adjustHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		pBillManageService.deleteBill(formId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPriceAdjustDao(PriceAdjustDao priceAdjustDao) {
		this.priceAdjustDao = priceAdjustDao;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getEffectTime() {
		return DateTimeUtil.getDateStr(effectTime);
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public PriceAdjustHeader getAdjustHeader() {
		return adjustHeader;
	}

	public void setAdjustHeader(PriceAdjustHeader adjustHeader) {
		this.adjustHeader = adjustHeader;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setpBillManageService(BillManageService pBillManageService) {
		this.pBillManageService = pBillManageService;
	}

	public void setPurchasePriceAdjustBean(PurchasePriceAdjustBean purchasePriceAdjustBean) {
		this.purchasePriceAdjustBean = purchasePriceAdjustBean;
	}

	public void setPriceAdjustDetailBean(PriceAdjustDetailBean priceAdjustDetailBean) {
		this.priceAdjustDetailBean = priceAdjustDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
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

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

}
