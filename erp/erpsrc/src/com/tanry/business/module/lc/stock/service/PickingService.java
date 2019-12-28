/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 11, 2014 by liyzh
 * Last edited on Dec 11, 2014 by liyzh
 * 
 * 说明： 物流中心拣货单管理
 */
package com.tanry.business.module.lc.stock.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PickingDetailBean;
import logic.form.PickingHeaderBean;
import logic.form.PickingRefBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.form.StorageInOutBean;
import logic.module.lc.CreatePickingBean;
import logic.store.BranchBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.PickingDetail;
import pojo.form.PickingHeader;
import pojo.form.PickingRef;
import pojo.form.ShippingHeader;
import pojo.form.StorageInOut;

import com.tanry.business.form.FormUtil;
import com.tanry.business.module.lc.stock.util.PickInstance;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;

public class PickingService {

	private CreatePickingBean createPickingBean;
	private PickingHeaderBean pickingHeaderBean;
	private PickingDetailBean pickingDetailBean;
	private PickingRefBean pickingRefBean;
	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private FormStatusBean formStatusBean;
	private StorageBean storageBean;
	private StorageInOutBean storageInOutBean;
	private OperationVersionBean operationVersionBean;
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
	
	/**
	 * @param loginUserId
	 * @param pickingHeader
	 * @param shippingIds
	 * @param jsonData
	 */
	@Deprecated
	public void createBill(String userId, PickingHeader header, String shippingIds, String branchIds, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		Date formTime = header.getFormTime();
		int newSerial = pickingHeaderBean.newSerial(formTime, header.getPickingBranchId());
		String formId = FormUtil.genFormIdBody("JH", "", formTime) + FormUtil.formatSerial(newSerial);
		header.setFormId(formId);
		JSONArray arr = JSONArray.fromObject(jsonData);
		String[] branchArr = branchIds.split(",");
		List<PickingDetail> detailLst = new ArrayList<PickingDetail>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			PickingDetail detail = new PickingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("deliveryUnit"));
			detail.setUnitVolume(json.getDouble("unitVolume"));
			for (String branchId : branchArr) {
				detail.setBranchId(branchId);
				if (json.get(branchId) != null) {
					detail.setItemCount(Double.parseDouble(json.get(branchId) + ""));
					pickingDetailBean.saveEntity(detail);
					detailLst.add(detail);
				}
			}
		}
		pickingHeaderBean.saveEntity(header);

		// 设置捡货单的状态
		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.UNADUIT_CN, userId));

		// 设置配送单的状态
		String[] shippingIdArr = shippingIds.split(",");
		for (String sId : shippingIdArr) {
			shippingHeaderBean.savePickStatus(sId, "已捡货");
		}
	}

	/**
	 * @param loginUserId
	 * @param pickingHeader
	 * @param shippingIds
	 * @param jsonData
	 */
	@Deprecated
	public void createBillCode(String userId, PickingHeader header, String shippingIds) throws NoPrivilegeException,
			SQLException, NoConnection {
		Date formTime = header.getFormTime();
		String lcBranchId = header.getPickingBranchId();

		String prefix = "JH";
		String formBody = FormUtil.genFormIdBody(prefix, lcBranchId, formTime);

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);
		header.setFormId(formId);
		// 首先生成单据版本记录
		OperateVersionUtil.save(formId);

		List<Map> itemLst = createPickingBean.query(shippingIds);
		List<JSONObject> itemLst2 = PickInstance.group(itemLst);
		for (JSONObject json : itemLst2) {
			PickingDetail detail = new PickingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("deliveryUnit"));
			detail.setUnitVolume(json.getDouble("unitVolume"));

			String branchId = json.getString("requesterId");
			detail.setBranchId(branchId);
			detail.setItemCount(json.getDouble("packagingCount"));
			pickingDetailBean.saveEntity(detail);

			// 保存捡货与配送的关联关系
			List<JSONObject> refLst = (List<JSONObject>) json.get("refLst");
			for (JSONObject ref : refLst) {
				PickingRef pickingRef = new PickingRef();
				pickingRef.setFormId(formId);
				pickingRef.setFormRefId(ref.getString("formRefId"));
				pickingRef.setItemId(itemId);
				pickingRef.setBranchId(branchId);
				pickingRef.setItemCount(ref.getDouble("itemCount"));
				pickingRefBean.saveEntity(pickingRef);
			}
		}
		pickingHeaderBean.saveEntity(header);

		// 设置捡货单的状态
		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.UNADUIT_CN, userId));

		// 设置配送单的状态
		String[] shippingIdArr = shippingIds.split(",");
		for (String sId : shippingIdArr) {
			shippingHeaderBean.savePickStatus(sId, "已捡货");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String createBill(String userId, PickingHeader header, String shippingIds) throws NoPrivilegeException,
			SQLException, NoConnection {
		Date formTime = header.getFormTime();
		String lcBranchId = header.getPickingBranchId();

		String prefix = "JH";
		String formBody = FormUtil.genFormIdBody(prefix, lcBranchId, formTime);
		String shelfName = "";
		String formId = "";
		String formIds = "";

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;

		List<Map> itemLst = createPickingBean.query(shippingIds);
		List<JSONObject> itemLst2 = PickInstance.group(itemLst);
		Collections.sort(itemLst2, new Comparator<JSONObject>() {

			@Override
			public int compare(JSONObject o1, JSONObject o2) {
				return o1.getString("shelfId").compareTo(o2.getString("shelfId"));
			}
		});

		for (JSONObject json : itemLst2) {

			PickingDetail detail = new PickingDetail();
			String newShelfName = json.getString("shelfName");
			if (!shelfName.equals(newShelfName)) {
				formId = formBody + FormUtil.formatSerial(newSerial);
				header.setFormId(formId);
				// 首先生成单据版本记录
				OperateVersionUtil.save(formId);
				shelfName = newShelfName;
				newSerial++;
				formIds += formId + ",";
				pickingHeaderBean.saveEntity(header);
				// 设置捡货单的状态
				formStatusBean.saveEntity(new FormStatus(formId, BillStatus.UNADUIT_CN, userId));
			}
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("deliveryUnit"));
			detail.setUnitVolume(json.getDouble("unitVolume"));

			String branchId = json.getString("requesterId");
			detail.setBranchId(branchId);
			detail.setItemCount(json.getDouble("packagingCount"));
			pickingDetailBean.saveEntity(detail);

			// 保存捡货与配送的关联关系
			List<JSONObject> refLst = (List<JSONObject>) json.get("refLst");
			for (JSONObject ref : refLst) {
				PickingRef pickingRef = new PickingRef();
				pickingRef.setFormId(formId);
				pickingRef.setFormRefId(ref.getString("formRefId"));
				pickingRef.setItemId(itemId);
				pickingRef.setBranchId(branchId);
				pickingRef.setItemCount(ref.getDouble("itemCount"));
				pickingRefBean.saveEntity(pickingRef);
			}
		}

		// 设置配送单的状态
		String[] shippingIdArr = shippingIds.split(",");
		for (String sId : shippingIdArr) {
			shippingHeaderBean.savePickStatus(sId, "已捡货");
		}
		return formIds;
	}

	/**
	 * 暂存捡货单，主要修改D_T1_PICKING_REF关联表数量
	 * 
	 * @param userId
	 * @param header
	 * @param branchIds
	 * @param jsonData
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(PickingHeader header, String branchIds, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = header.getFormId();

		JSONArray arr = JSONArray.fromObject(jsonData);

		// 一个配送单对应一个收货部门
		String[] branchArr = branchIds.split(",");

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			PickingDetail detail = new PickingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			for (String branchId : branchArr) {
				detail.setBranchId(branchId);
				if (json.get(branchId) != null) {
					if (json.get(branchId).equals("null")) {
						continue;
					}
					Double itemCount = Double.parseDouble(json.get(branchId) + "");
					detail.setItemCount(itemCount);

					// 更新捡货单明细，因捡货单在审核时可能会修改明细
					pickingDetailBean.updateEntity(detail);
				}
			}
		}

	}

	/**
	 * 1. 捡货单审核，对应的配送单单据状态更新为已审核 2. 更新实时库存 配送出库 3. 插入数据到出入库表
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, PickingHeader header, String branchIds, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();

		String lcBranchId = header.getPickingBranchId();
		String lcStorageId = header.getStorageId();
		Date lcDate = branchBean.GetBranchById(lcBranchId).getBusinessDate();

		// 捡货配送单对应：捡货单号 配送单号 商品编码 门店编码 配送单包装数量
		List<PickingRef> allRefLst = pickingRefBean.query(formId);
		JSONArray arr = JSONArray.fromObject(jsonData);

		// 一个配送单对应一个收货部门
		String[] branchArr = branchIds.split(",");

		Long myDate = (new Date()).getTime();
		int index = 0;

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			PickingDetail detail = new PickingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			for (String branchId : branchArr) {
				detail.setBranchId(branchId);
				if (json.get(branchId) != null) {
					if (json.get(branchId).equals("null")) {
						continue;
					}
					Double itemCount = Double.parseDouble(json.get(branchId) + "");
					detail.setItemCount(itemCount);

					// 更新捡货单明细，因捡货单在审核时可能会修改明细
					pickingDetailBean.updateEntity(detail);

					List<PickingRef> refLst = findRef(allRefLst, branchId, itemId);
					// 优先满足前面配送单的数量，如果有差异，在后面的配送单上体现出来
					Double remainCount = itemCount;
					int size = refLst.size();
					if (size > 0) {// 非空处理，兼容老数据
						for (int i = 0; i < size - 1; i++) {// 除了最后一个（因为多出来的数量都会追加到最后一个配送单上）
							PickingRef ref = refLst.get(i);
							Double oldCount = ref.getItemCount();
							if (remainCount < oldCount) {// 数量不足，需要更新
								ref.setOutCount(remainCount);
							} else if (remainCount >= oldCount) {
								remainCount = remainCount - oldCount;
							}
						}
						PickingRef lastRef = refLst.get(size - 1);
						if (!lastRef.getItemCount().equals(remainCount)) {// 最后一个不论比原来的配送量多还是少，都要更新包装数量和实发数
							lastRef.setOutCount(remainCount);
						}

						for (PickingRef ref : refLst) {
							index++;
							String myDateStr = (myDate + index) + "";

							Double outCount = ref.getOutCount();
							Double deliveryFactor = ref.getDeliveryFactor();
							Double deliveryCount = ref.getItemCount() * deliveryFactor;// 实发数，库存单位
							if (outCount != null) { // 只在有变化时才更新配送单的包装数量和实发数
								deliveryCount = outCount * deliveryFactor;// 实发数，库存单位
								shippingDetailBean.updatePackageCount(ref.getFormRefId(), itemId, outCount,
										deliveryCount);
							}
							updateInOut(itemId, lcBranchId, lcStorageId, lcDate, deliveryCount, ref.getItemPrice(),
									ref.getFormRefId(), myDateStr);

							// 审核之后，物流中心库存减少
							storageBean.updateItemCount(itemId, lcStorageId, -deliveryCount);
						}
					}
				}
			}
		}
		pickingHeaderBean.updateEntity(header);

		// 设置配送单的状态
		Set<String> sidSet = findShippingIds(allRefLst);
		ShippingHeader shippingHeader = new ShippingHeader();
		shippingHeader.setAuditorId(header.getAuditorId());
		shippingHeader.setAuditor(header.getAuditor());
		shippingHeader.setAuditTime(header.getAuditTime());
		for (String sId : sidSet) {
			formStatusBean.saveEntity(new FormStatus(sId, BillStatus.AUDITED_CN, userId));
			shippingHeader.setFormId(sId);
			shippingHeaderBean.saveOnStatus(shippingHeader, "在途");
		}

		// 设置捡货单的状态
		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.AUDITED_CN, userId));

	}

	/**
	 * 插入记录到进出入表（物流中心出库）
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void updateInOut(String itemId, String lcBranchId, String lcStorageId, Date lcDate, Double countOut,
			Double itemPrice, String formId, String myDateStr) throws NoPrivilegeException, SQLException, NoConnection {
		Double centreStorage = storageBean.getItemCount(itemId, lcStorageId);

		// 入库数量
		Double centreItemCountIn = 0.0;

		// 期初数量
		Double centreOrgiCount = (centreStorage == null ? 0 : centreStorage);

		// 结存数量
		Double centreResultCount = centreOrgiCount - countOut;

		// 业务类型 物流中心配送出库
		String reason = "PSCK";
		StorageInOut centreStorageInOut = new StorageInOut(lcBranchId, lcStorageId, lcDate, new Date(), itemId,
				itemPrice, centreOrgiCount, centreItemCountIn, countOut, centreResultCount, formId, reason, myDateStr);
		storageInOutBean.saveEntity(centreStorageInOut);
	}

	private Set<String> findShippingIds(List<PickingRef> allRefLst) {
		Map<String, String> idMap = new HashMap<String, String>();
		for (PickingRef ref : allRefLst) {
			String sId = ref.getFormRefId();
			if (idMap.get(sId) == null) {
				idMap.put(sId, sId);
			}
		}
		return idMap.keySet();
	}

	private List<PickingRef> findRef(List<PickingRef> allRefLst, String branchId, String itemId) {
		List<PickingRef> refLst = new ArrayList<PickingRef>();
		for (PickingRef ref : allRefLst) {
			if (branchId.equals(ref.getBranchId()) && itemId.equals(ref.getItemId())) {
				refLst.add(ref);
			}
		}
		return refLst;
	}

	public void batchAudit(String userId, String ids) throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = ids.split(",");
		for (String formId : idArr) {
			// 设置捡货单的状态
			formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String formId, String userId, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		pickingDetailBean.deleteByForm(formId);
		pickingHeaderBean.deleteEntity(formId);
		operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				currentVersion + 1, OperationVersion.DELETED, formId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void invalidBill(String formId, String userId, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.CANCELED, userId));
		operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				currentVersion + 1, OperationVersion.CANCELED, formId));
	}

	public void setCreatePickingBean(CreatePickingBean createPickingBean) {
		this.createPickingBean = createPickingBean;
	}

	public void setPickingDetailBean(PickingDetailBean pickingDetailBean) {
		this.pickingDetailBean = pickingDetailBean;
	}

	public void setPickingHeaderBean(PickingHeaderBean pickingHeaderBean) {
		this.pickingHeaderBean = pickingHeaderBean;
	}

	public void setPickingRefBean(PickingRefBean pickingRefBean) {
		this.pickingRefBean = pickingRefBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

}
