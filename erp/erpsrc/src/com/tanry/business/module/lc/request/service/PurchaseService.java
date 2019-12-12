package com.tanry.business.module.lc.request.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CollectDetailBean;
import logic.form.CollectRefFormBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.form.PurchasingMappingBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.module.lc.CrossDistributionBean;
import logic.module.lc.PurchaseManageBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.CollectDetail;
import pojo.form.CollectHeader;
import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.store.Branch;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormType;
import com.tanry.business.form.FormUtil;
import com.tanry.business.form.StatusConst;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.DoubleJsonValueProcessor;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.constant.LogType;

public class PurchaseService {

	private FormStatusBean formStatusBean;

	private BranchBean branchBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private PurchasingDetailBean purchasingDetailBean;
	private PurchasingMappingBean purchasingMappingBean;
	private PurchaseManageBean purchaseManageBean;
	private CrossDistributionBean crossDistributionBean;
	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private CollectDetailBean collectDetailBean;
	private CollectRefFormBean collectRefFormBean;

	private OperationVersionBean operationVersionBean;

	/**
	 * 查询直配大小单汇总信息
	 * 
	 * @param formId
	 * @return
	 */
	public JSONObject queryDirect(String pFormId, String lcCode) throws NoPrivilegeException, SQLException,
			NoConnection {

		PurchasingHeader header = purchasingHeaderBean.queryById(pFormId);
		List<Map> detailLst = purchaseManageBean.queryDirect(pFormId, lcCode);
		String status = formStatusBean.getCurrentStatus(pFormId);

		JSONObject result = new JSONObject();
		JSONObject jHeader = JSONObject.fromObject(header);
		jHeader.put("receiveTime", DateTimeUtil.getDateStr(header.getReceiveTime()));
		jHeader.put("formTime", DateTimeUtil.getDateStr(header.getFormTime()));
		String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		jHeader.put("formTimeActual", formTimeActual);
		jHeader.put("auditTime", DateTimeUtil.getDateStr(header.getAuditTime()));
		jHeader.put("status", status);
		result.put("header", jHeader);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			String refIds = collectRefFormBean.queryRefs((String) detail.get("formId"), (String) detail.get("itemId"));
			// String refIds = (String) detail.get("refFormId");
			json.put("refIds", refIds);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		result.put("detailLst", arr);
		result.put("msg", "ok");
		return result;
	}

	/**
	 * 显示采购单信息，主从表信息一次性查出来
	 * 
	 * @param formId
	 * @return
	 */
	public JSONObject queryBill(String pFormId, String lcCode, String hasSum) throws NoPrivilegeException,
			SQLException, NoConnection {
		PurchasingHeader header = purchasingHeaderBean.queryById(pFormId);
		List<PurchasingDetail> detailLst = purchasingDetailBean.query(pFormId, lcCode, hasSum);
		String status = formStatusBean.getCurrentStatus(pFormId);

		JSONObject result = new JSONObject();
		JSONObject jHeader = JSONObject.fromObject(header);
		jHeader.put("receiveTime", DateTimeUtil.getDateStr(header.getReceiveTime()));
		jHeader.put("formTime", DateTimeUtil.getDateStr(header.getFormTime()));
		String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		jHeader.put("formTimeActual", formTimeActual);
		jHeader.put("auditTime", DateTimeUtil.getDateStr(header.getAuditTime()));
		jHeader.put("status", status);
		result.put("header", jHeader);

		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());

		JSONArray arr = new JSONArray();
		for (PurchasingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail, config);
			json.put("expiredTime", DateTimeUtil.getDateStr(detail.getExpiredTime()));
			String refIds = collectRefFormBean.queryRefs(detail.getFormId(), detail.getItemId());
			json.put("refIds", refIds);
			arr.add(json);
		}
		result.put("detailLst", arr);
		result.put("msg", "ok");
		return result;
	}

	@Deprecated
	public void createPurchaseBill(String userId, CollectHeader header) throws NoPrivilegeException, SQLException,
			NoConnection {
		String formId = header.getFormId();
		String lcCode = BranchCode.DEFAULT_LOGISTICSCENTER;
		Date businessDate = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取物流中心当前的营业时间
		Branch branch = branchBean.GetBranchById(lcCode);
		String requester = branch.getBranchName();
		String requestAddress = branch.getBranchAddress();
		String formMaker = header.getFormMaker();
		List<Map> providerLst = collectDetailBean.queryProvider(formId);
		String commonFormId = FormUtil.generateFormId("CG", lcCode, businessDate);// 采购CG；计算机同时生成多张采购单，要用序号加以区分
		int count = 0;
		double payAmt = -1;
		String itemName = null;
		for (Map provider : providerLst) {
			PurchasingHeader pHeader = new PurchasingHeader();
			String pFormId = commonFormId + count++;
			pHeader.setFormId(pFormId);
			pHeader.setFormRefId(formId);
			pHeader.setFormTime(businessDate);
			String providerId = (String) provider.get("providerId");
			pHeader.setProviderId(providerId);
			pHeader.setProvider((String) provider.get("provider"));
			pHeader.setRequesterId(lcCode);
			pHeader.setRequester(requester);
			pHeader.setReceiveAddress(requestAddress);
			pHeader.setFormMakerId(userId);
			pHeader.setFormMaker(formMaker);

			List<CollectDetail> detailLst = collectDetailBean.query(formId, providerId);
			for (CollectDetail detail : detailLst) {
				PurchasingDetail pDetail = new PurchasingDetail();
				pDetail.setFormId(pFormId);
				pDetail.setItemId(detail.getItemId());
				pDetail.setItemName(detail.getItemName());
				pDetail.setItemDimension(detail.getItemDimension());
				pDetail.setItemSpecification(detail.getItemSpecification());
				pDetail.setItemCount(detail.getItemCount());
				pDetail.setItemUnitPrice(detail.getItemUnitPrice());
				pDetail.setPayAmt(detail.getPayAmt());
				if (detail.getPayAmt() > payAmt) {
					payAmt = detail.getPayAmt();
					itemName = detail.getItemId() + detail.getItemName();
				}
				purchasingDetailBean.saveEntity(pDetail);
			}
			pHeader.setMaxPayItem(itemName);
			purchasingHeaderBean.saveEntity(pHeader);

			formStatusBean.saveEntity(new FormStatus(pFormId, "已审核", userId));
		}

		// 设置汇总单的状态为已下发采购单
		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("已下发");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	/**
	 * 采购单修改
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(PurchasingHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		String deliveryType = header.getDeliveryType();
		// 计算总额
		double allPayAmt = 0.0;
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			PurchasingDetail detail = new PurchasingDetail();
			detail.setFormId(json.getString("formId"));
			if (FormConstant.CROSS_DELIVERY.equals(deliveryType)) {// 越库的明细与门店相关
				detail.setReceiverId((String) json.get("receiverId"));
			}
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			detail.setItemCount(json.getDouble("itemCount"));
			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);
			allPayAmt += payAmt;
			Double receiveAmt = json.getDouble("receiveAmt");
			detail.setReceiveAmt(receiveAmt);
			purchasingDetailBean.updateEntity(detail);
		}
		header.setAllPayAmt(allPayAmt);
		purchasingHeaderBean.updateEntity(header);
	}

	public void saveOperation(String formId, String logType, String msg) throws NoPrivilegeException, SQLException,
			NoConnection {
		OperateLogUtil.record(formId, logType, msg);
	}

	/**
	 * 采购单审核
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, PurchasingHeader header, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		updateBill(header, jsonData);
		String formId = header.getFormId();
		purchasingHeaderBean.audit(formId, userId, header.getAuditor(), header.getAuditTime());

		operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				currentVersion + 1, OperationVersion.AUDITED, formId));

		FormStatus fstatus = new FormStatus(formId, StatusConst.AUDITED, userId);
		formStatusBean.saveEntity(fstatus);

		// 如果是越库，直接由采购单生成配送单
		if (FormConstant.CROSS_DELIVERY.equals(header.getDeliveryType())) {
			Date receiveTime = header.getReceiveTime();
			header = purchasingHeaderBean.queryById(formId);
			header.setReceiveTime(receiveTime);
			List<Map> itemLst = crossDistributionBean.queryDetail(formId);
			createCrossDistriBill(userId, header, itemLst);
		}
		// 如果是直配，同步改变直配小单的审核状态
		if (FormConstant.DIRECT_DELIVERY.equals(header.getDeliveryType())) {
			List<String> smallIdLst = purchasingMappingBean.querySmallFormId(formId);
			for (String smallFormId : smallIdLst) {
				FormStatus status = new FormStatus(smallFormId, StatusConst.AUDITED, userId);
				formStatusBean.saveEntity(status);
				purchasingHeaderBean.updateTime(smallFormId, header);

				// 如果是小单，则还要向小单插入版本号
				operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
						currentVersion + 1, OperationVersion.AUDITED, smallFormId));
			}
		}
		saveOperation(header.getFormId(), LogType.AUDIT, "审核采购单");
	}

	/**
	 * 采购单更新
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(String userId, PurchasingHeader header, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		updateBill(header, jsonData);

		String formId = header.getFormId();
		operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				currentVersion + 1, OperationVersion.EDITED, formId));

		if (FormConstant.DIRECT_DELIVERY.equals(header.getDeliveryType())) {
			List<String> smallIdLst = purchasingMappingBean.querySmallFormId(formId);
			for (String smallFormId : smallIdLst) {

				operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
						currentVersion + 1, OperationVersion.EDITED, smallFormId));
			}
		}

		saveOperation(header.getFormId(), LogType.UPDATE, "编辑采购单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String formId, String userId, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		// formId 对于直配的单就是大单单号

		if (formId.toUpperCase().indexOf("ZP") != -1) {
			List<String> smallFormIdsList = purchasingMappingBean.querySmallFormId(formId);
			for (int i = 0; i < smallFormIdsList.size(); i++) {
				String smallFormId = smallFormIdsList.get(i);
				deleteSingleForm(smallFormId);

				operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
						currentVersion + 1, OperationVersion.DELETED, smallFormId));
			}

			purchasingMappingBean.deleteEntity(formId);

		}

		deleteSingleForm(formId);
		operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				currentVersion + 1, OperationVersion.DELETED, formId));

		saveOperation(formId, LogType.DELETE, "删除采购单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void invalidBill(String formId, String userId, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {

		if (formId.toUpperCase().indexOf("ZP") != -1) {
			List<String> smallFormIdsList = purchasingMappingBean.querySmallFormId(formId);
			for (int i = 0; i < smallFormIdsList.size(); i++) {
				String smallFormId = smallFormIdsList.get(i);
				formStatusBean.saveEntity(new FormStatus(smallFormId, BillStatus.CANCELED, userId));
				operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
						currentVersion + 1, OperationVersion.CANCELED, smallFormId));
			}
		}

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.CANCELED, userId));
		operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				currentVersion + 1, OperationVersion.CANCELED, formId));

		saveOperation(formId, LogType.CANCELED, "作废采购单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void deleteSingleForm(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		purchasingHeaderBean.deleteEntity(formId);
		purchasingDetailBean.deleteByForm(formId);
	}

	public static void main(String[] args) {
		BigDecimal packagingCount = BigDecimal.valueOf(221.52)
				.divide(BigDecimal.valueOf(36.82), 0, BigDecimal.ROUND_UP); // 默认向上取整，以后可能修改为参数可配置
		System.out.println(packagingCount);
	}

	public void createCrossDistriBill(String userId, PurchasingHeader header, List<Map> itemLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		Map<String, List<Map<String, Object>>> branchMap = new HashMap<String, List<Map<String, Object>>>();// 用来暂存每个门店下的多个原料明细，相当于配送单从表数据
		List<Map<String, Object>> billLst = new ArrayList<Map<String, Object>>();// 配送单主表数据
		for (Map<String, Object> item : itemLst) {
			String receiverId = (String) item.get("receiverId");
			String key = receiverId;
			if (branchMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("receiverId", receiverId);
				bill.put("receiver", item.get("receiver"));
				bill.put("branchAddress", item.get("branchAddress"));
				bill.put("key", key);
				billLst.add(bill);
				branchMap.put(key, new ArrayList<Map<String, Object>>());
			}
			branchMap.get(key).add(item);
		}

		String lcCode = header.getRequesterId();
		Branch lcBranch = branchBean.GetBranchById(lcCode);
		String lcName = lcBranch.getBranchName();

		String formType = FormType.DISTRI_CROSS;
		String userName = header.getAuditor();
		Date formTime = header.getAuditTime();
		Date receiveTime = header.getReceiveTime();
		for (Map<String, Object> bill : billLst) {
			String branchId = (String) bill.get("key");

			String prefix = "PSY";
			String formBody = FormUtil.genFormIdBody(prefix, branchId, formTime);
			Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
			String dFormId = formBody + FormUtil.formatSerial(newSerial);

			ShippingHeader sHeader = new ShippingHeader();
			sHeader.setFormId(dFormId);
			sHeader.setFormRefId(header.getFormId());
			sHeader.setFormType(formType);
			sHeader.setProviderId(lcCode);
			sHeader.setProvider(lcName);
			sHeader.setRequesterId(branchId);
			sHeader.setRequester((String) bill.get("receiver"));
			sHeader.setRequestAddress((String) bill.get("branchAddress"));
			sHeader.setReceiveTime(receiveTime);
			sHeader.setFormMakerId(userId);
			sHeader.setFormMaker(userName);
			sHeader.setFormTime(formTime);
			sHeader.setAllPayAmt(header.getAllPayAmt());
			sHeader.setMaxPayItem(header.getMaxPayItem());
			shippingHeaderBean.saveEntity(sHeader);

			OperateVersionUtil.save(dFormId);
			OperateLogUtil.record(dFormId, LogType.CREATE, "新增越库配送单");

			for (Map<String, Object> item : branchMap.get(bill.get("key"))) {
				ShippingDetail detail = new ShippingDetail();
				detail.setFormId(dFormId);
				detail.setItemId((String) item.get("itemId"));
				detail.setItemName((String) item.get("itemName"));
				detail.setItemCategory((String) item.get("itemCategory"));
				detail.setItemDimension((String) item.get("itemDimension"));
				detail.setItemSpecification((String) item.get("itemSpecification"));

				Double itemUnitPrice = (Double) item.get("itemUnitPrice");
				itemUnitPrice = itemUnitPrice == null ? 0.0 : itemUnitPrice;

				detail.setItemUnitPrice(itemUnitPrice);
				Double itemCount = (Double) item.get("itemCount");
				detail.setRequestCount(itemCount);

				String deliveryUnit = (String) item.get("deliveryUnit");
				BigDecimal unitConvertFactor = BigDecimal.valueOf((Double) item.get("unitConvertFactor"));

				if (unitConvertFactor != null) {
					BigDecimal packagingCount = BigDecimal.valueOf(itemCount).divide(unitConvertFactor, 0,
							BigDecimal.ROUND_UP); // 默认向上取整，以后可能修改为参数可配置
					BigDecimal shippingCount = packagingCount.multiply(unitConvertFactor);

					detail.setPackagingUnit(deliveryUnit);
					detail.setPackagingFactor(unitConvertFactor.doubleValue());
					detail.setPackagingCount(packagingCount.doubleValue());

					detail.setDeliveryCount(shippingCount.doubleValue());
					detail.setShippingCount(shippingCount.doubleValue());
					BigDecimal payAmt = shippingCount.multiply(BigDecimal.valueOf(itemUnitPrice)).setScale(2,
							BigDecimal.ROUND_HALF_UP);
					detail.setPayAmt(payAmt.doubleValue());
				}
				shippingDetailBean.saveEntity(detail);
			}
			// 设置配送单状态
			formStatusBean.saveEntity(new FormStatus(dFormId, StatusConst.UN_AUDIT, userId));
		}
	}

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setCollectDetailBean(CollectDetailBean collectDetailBean) {
		this.collectDetailBean = collectDetailBean;
	}

	public void setCollectRefFormBean(CollectRefFormBean collectRefFormBean) {
		this.collectRefFormBean = collectRefFormBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setPurchasingMappingBean(PurchasingMappingBean purchasingMappingBean) {
		this.purchasingMappingBean = purchasingMappingBean;
	}

	public void setPurchaseManageBean(PurchaseManageBean purchaseManageBean) {
		this.purchaseManageBean = purchaseManageBean;
	}

	public void setCrossDistributionBean(CrossDistributionBean crossDistributionBean) {
		this.crossDistributionBean = crossDistributionBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

}
