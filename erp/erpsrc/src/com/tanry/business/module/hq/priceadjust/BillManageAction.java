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

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormLockBean;
import logic.form.FormStatusBean;
import logic.form.PriceAdjustDetailBean;
import logic.form.PriceAdjustHeaderBean;
import logic.store.BranchBean;
import logic.store.PriceGroupBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.PriceAdjustDetail;
import pojo.form.PriceAdjustHeader;
import pojo.store.PriceGroup;
import action.common.BaseAction;

import com.tanry.business.module.hq.priceadjust.service.BillManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.NumberUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.constant.PriceType;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PriceAdjustHeaderBean priceAdjustHeaderBean;
	private PriceAdjustDetailBean priceAdjustDetailBean;
	private BillManageService pBillManageService;
	private FormLockBean formLockBean;
	private PriceGroupBean priceGroupBean;

	/**
	 * 中央工厂售价调价单、中央工厂采购调价单、物流中心采购调价单、物流中心标准调价单、物流中心加盟调价单、物流中心零售调价单表头
	 */
	private String adjustType;
	private String queryType;
	private Date startDate;
	private Date endDate;
	private List<Map> PGroupList;

	private String formId;
	private String PGroupID;
	private String PGroupName;
	private String adjustTypeName;

	private PriceAdjustHeader adjustHeader;
	private Date effectTime;
	private Date formTime;
	private Date auditTime;

	private String jsonData;

	private String itemType;
	private String itemName;

	private FormStatusBean formStatusBean;
	private String status;
	private String supplierId;
	/** 上传的文件 */
	private File attachment;
	private String priceType;// 价格类型
	private String therapyIds;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		startDate = endDate = getLoginBranchDate();
		PGroupList = priceAdjustHeaderBean.queryPGroup(adjustType);
		return SUCCESS;
	}

	public Date getLoginBranchDate() throws NoPrivilegeException, SQLException, NoConnection {
		return branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
	}

	public String addView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = getLoginBranchDate();
		effectTime = formTime;
		adjustHeader = new PriceAdjustHeader();
		PriceGroup priceGroup = priceGroupBean.queryById(adjustType);
		adjustHeader.setAdjustType(adjustType);
		adjustHeader.setAdjustTypeName(priceGroup.getPriceGroupName());
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			adjustHeader = priceAdjustHeaderBean.queryById(formId);
			formTime = adjustHeader.getFormTime();
			effectTime = adjustHeader.getEffectTime();
			adjustTypeName = adjustHeader.getAdjustTypeName();
		}
		startDate = getLoginBranchDate();
		formLockBean.addLock(formId);// 加锁
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			status = formStatusBean.getCurrentStatus(formId);
			adjustHeader = priceAdjustHeaderBean.queryById(formId);
			formTime = adjustHeader.getFormTime();
			auditTime = adjustHeader.getAuditTime();
			effectTime = adjustHeader.getEffectTime();
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = priceAdjustHeaderBean.count(startDate, endDate, adjustType, queryType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<PriceAdjustHeader> headerLst = priceAdjustHeaderBean.query(startDate, endDate, adjustType, queryType,
					getStart(), getEnd());
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
				String adjustScope = header.getAdjustType();
				json.put("adjustScope", adjustScope);
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

	// 物流中心日结时查询生效日期所有未审核的调价单
	public void queryEffect() throws NoPrivilegeException, SQLException, NoConnection {
		startDate = getLoginBranchDate();
		endDate = DateTimeUtil.addDays(startDate, 1);
		List<PriceGroup> priLst = priceGroupBean.queryAll();
		adjustType = "";
		for (PriceGroup priceGroup : priLst) {
			adjustType += priceGroup.getPriceGroupId() + ",";
		}
		int total = priceAdjustHeaderBean.countEffect(startDate, endDate, adjustType, queryType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<PriceAdjustHeader> headerLst = priceAdjustHeaderBean.queryEffect(startDate, endDate, adjustType,
					queryType, getStart(), getEnd());
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

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<PriceAdjustDetail> detailLst = priceAdjustDetailBean.query(formId);
		int rownumber = 1;
		for (PriceAdjustDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			json.put("differentPrice", NumberUtil.scale(detail.getNewPrice() - detail.getOldPrice(), 4));
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
	 * 解析导入文件
	 */
	public void doImport() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(priceType)) {
			priceType = PriceType.BENCHMARK;
		}
		JSONObject result = pBillManageService.doImport(attachment, priceType);
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
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

		if (PriceType.SALE.equals(adjustType)) {// 售卖价是针对出品的
			itemType = ItemType.PRODUCT;
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

	public void queryBySupplier() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(itemType)) {
			itemType = ItemType.RAW;
		}

		if (adjustType.indexOf("SALE") != -1) {
			itemType = ItemType.PRODUCT;
		}
		List<PriceAdjustDetail> detailLst = !TextUtil.isEmpty(supplierId) ? priceAdjustDetailBean.queryBySupplier(
				supplierId, itemType, itemName, adjustType) : priceAdjustDetailBean.queryItem(itemType, itemName,
				adjustType);

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

	public void computeBill() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(therapyIds)) {
			therapyIds = "";
		}
		pBillManageService.computePrice(therapyIds, itemType);

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

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		adjustHeader.setAuditor(getLoginUsername());
		adjustHeader.setAuditTime(new Date());
		JSONObject result = new JSONObject();
		if (adjustHeader.getFormId().indexOf("TJS") != -1)
			if (pBillManageService.checkEnabled(adjustHeader) == 0) {
				result.put("msg", "no");
				try {
					this.outJS(result.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		pBillManageService.auditBill(getLoginUserId(), adjustHeader, jsonData);

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

	public void doInvalid() throws NoPrivilegeException, SQLException, NoConnection {
		String[] formIds = formId.split(",");
		String userId = getLoginUserId();
		pBillManageService.doInvalid(formIds, userId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAdjustTypeName() {
		return adjustTypeName;
	}

	public void setAdjustTypeName(String adjustTypeName) {
		this.adjustTypeName = adjustTypeName;
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

	public String getItemType() {
		return itemType;
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

	public String getFormTime() throws NoPrivilegeException, SQLException, NoConnection {
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

	public void setpBillManageService(BillManageService pBillManageService) {
		this.pBillManageService = pBillManageService;
	}

	public void setPriceAdjustHeaderBean(PriceAdjustHeaderBean priceAdjustHeaderBean) {
		this.priceAdjustHeaderBean = priceAdjustHeaderBean;
	}

	public void setPriceAdjustDetailBean(PriceAdjustDetailBean priceAdjustDetailBean) {
		this.priceAdjustDetailBean = priceAdjustDetailBean;
	}

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public String getStatus() {
		return status;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public List<Map> getPGroupList() {
		return PGroupList;
	}

	public String getPGroupName() {
		return PGroupName;
	}

	public void setPGroupName(String PGroupName) {
		this.PGroupName = PGroupName;
	}

	public String getPGroupID() {
		return PGroupID;
	}

	public void setPGroupID(String PGroupID) {
		this.PGroupID = PGroupID;
	}

	public void setPriceGroupBean(PriceGroupBean priceGroupBean) {
		this.priceGroupBean = priceGroupBean;
	}

	public void setTherapyIds(String therapyIds) {
		this.therapyIds = therapyIds;
	}

}
