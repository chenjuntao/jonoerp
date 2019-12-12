/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 18, 2014 by liyzh
 * Last edited on Sep 18, 2014 by liyzh
 * 
 * 说明： 配送退货单管理
 */
package service.restaurant.dreject;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputHeaderBean;
import logic.form.ReturnGoodsDetailBean;
import logic.form.ReturnGoodsHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.form.StorageInOutBean;
import logic.module.restaurant.InputReturnBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.ReturnGoodsDetail;
import pojo.form.ReturnGoodsHeader;
import pojo.form.StorageInOut;
import pojo.store.BranchStorage;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.LogType;

public class BillManageService {

	private ReturnGoodsHeaderBean returnGoodsHeaderBean;
	private ReturnGoodsDetailBean returnGoodsDetailBean;
	private InputHeaderBean inputHeaderBean;
	private InputReturnBean inputReturnBean;
	private ShippingHeaderBean shippingHeaderBean;
	private FormStatusBean formStatusBean;
	private StorageBean storageBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private StorageInOutBean storageInOutBean;

	private BranchStorageBean branchStorageBean;
	private BranchStorage branchStorage;

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveBill(String userId, ReturnGoodsHeader returnHeader, String returnType, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = returnHeader.getFormId();
		String formRefId = returnHeader.getFormRefId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double maxPayAmt = -1.0;
		String maxPayItem = "";
		Double allPayAmt = 0.0;
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ReturnGoodsDetail detail = new ReturnGoodsDetail();
			detail.setFormId(formId);
			detail.setFormRefId(formRefId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			detail.setReturnCount(json.getDouble("returnCount"));
			detail.setReturnPayAmt(json.getDouble("returnPayAmt"));
			detail.setReturnReason(json.getString("returnReason"));

			String itemName = json.getString("itemName");
			Double returnPayAmt = json.getDouble("returnPayAmt");
			if (returnPayAmt > maxPayAmt) {
				maxPayAmt = returnPayAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}
			allPayAmt += returnPayAmt;
			returnGoodsDetailBean.saveEntity(detail);
		}

		returnHeader.setMaxPayItem(maxPayItem);
		returnHeader.setAllPayAmt(allPayAmt);
		returnGoodsHeaderBean.saveEntity(returnHeader);

		OperateLogUtil.record(formId, LogType.CREATE, "生成采购退货单");

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
		if ("PURCHASE".equals(returnType)) {
			// 设置采购入库单的退货状态
			inputHeaderBean.saveReturnStatus(formRefId, "退货中");
			OperateLogUtil.record(formId, LogType.CREATE, "生成采购退货单");
		} else if ("OUTER".equals(returnType)) {
			// 设置出货单的退货状态
			shippingHeaderBean.saveReturnStatus(formRefId, "退货中");
			OperateLogUtil.record(formId, LogType.CREATE, "生成出货退货单");
		} else {
			// 设置配送单状态INNER
			shippingHeaderBean.saveReturnStatus(formRefId, "退货中");
			OperateLogUtil.record(formId, LogType.CREATE, "生成配送退货单");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditById(String formId, String userId, Date businessDate) throws NoPrivilegeException, SQLException,
			NoConnection {
		// returnHeaderBean.audit(formId, userId, businessDate);

		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("已审核");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	/**
	 * 餐厅配送退货单修改
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(ReturnGoodsHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		// String mainStorageId =
		// branchStorageBean.queryMainStore(header.getProviderId()).getStorageId();
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0;
		Double maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ReturnGoodsDetail detail = new ReturnGoodsDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			detail.setReturnCount(json.getDouble("returnCount"));
			detail.setReturnPayAmt(json.getDouble("returnPayAmt"));
			detail.setReturnReason(json.getString("returnReason"));

			String itemName = json.getString("itemName");
			Double returnPayAmt = json.getDouble("returnPayAmt");
			if (returnPayAmt > maxPayAmt) {
				maxPayAmt = returnPayAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}
			allPayAmt += returnPayAmt;
			returnGoodsDetailBean.updateEntity(detail);
		}
		header.setMaxPayItem(maxPayItem);// 主要退货品
		header.setAllPayAmt(allPayAmt);
		returnGoodsHeaderBean.updateEntity(header);

		OperateLogUtil.record(formId, LogType.UPDATE, "编辑配送退货单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateOutBill(ReturnGoodsHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String mainStorageId = branchStorageBean.queryMainStore(header.getProviderId()).getStorageId();
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0;
		Double maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ReturnGoodsDetail detail = new ReturnGoodsDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			detail.setReturnCount(json.getDouble("returnCount"));
			detail.setReturnPayAmt(json.getDouble("returnPayAmt"));
			detail.setReturnReason(json.getString("returnReason"));

			String itemName = json.getString("itemName");
			Double returnPayAmt = json.getDouble("returnPayAmt");
			if (returnPayAmt > maxPayAmt) {
				maxPayAmt = returnPayAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}
			allPayAmt += returnPayAmt;
			returnGoodsDetailBean.updateEntity(detail);

			storageBean.updateItemCount(itemId, mainStorageId, json.getDouble("returnCount"));
		}
		header.setMaxPayItem(maxPayItem);// 主要退货品
		header.setAllPayAmt(allPayAmt);
		returnGoodsHeaderBean.updateEntity(header);

		OperateLogUtil.record(formId, LogType.UPDATE, "编辑出货退货单");
	}

	/**
	 * 餐厅配送退货审核操作
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, ReturnGoodsHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		updateBill(header, jsonData);
		String formId = header.getFormId();
		returnGoodsDetailBean.deleteByCount(formId);

		// 修改退货单状态
		formStatusBean.saveEntity(new FormStatus(formId, "未处理", userId));

		OperateLogUtil.record(formId, LogType.AUDIT, "审核配送退货单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditOutBill(String userId, ReturnGoodsHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		updateOutBill(header, jsonData);
		String formId = header.getFormId();
		returnGoodsDetailBean.deleteByCount(formId);

		// 修改退货单状态
		formStatusBean.saveEntity(new FormStatus(formId, "未处理", userId));

		OperateLogUtil.record(formId, LogType.AUDIT, "审核出货退货单");
	}

	@Deprecated
	public void doConfirm(String userId, ReturnGoodsHeader header) throws NoPrivilegeException, SQLException,
			NoConnection {
		FormStatus fstatus = new FormStatus();
		String formId = header.getFormId();
		fstatus.setFormId(formId);
		fstatus.setStatus("已确认");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	/**
	 * 餐厅配送退货确认操作
	 * 
	 * @param header
	 * @param storageId
	 *            配送单所在的门店id，即订货部门，用于更新库存
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doReturn(String userId, ReturnGoodsHeader header, String branchId, String returnType, String reason)
			throws NoPrivilegeException, SQLException, NoConnection {
		Date restaurantDate = branchBean.GetBranchById(branchId).getBusinessDate();
		String storeId = branchStorageBean.queryMainStore(branchId).getStorageId();
		String proStoId = "";
		String providerId = header.getProviderId();
		branchStorage = branchStorageBean.queryMainStore(providerId);
		if (branchStorage != null) {
			proStoId = branchStorage.getStorageId();
		}
		Date operationTime = DateTimeUtil.getNow();
		String formId = header.getFormId();

		if (returnType == null) {
			// 餐厅配送退货
			// 配送部门ID，即相应的物流中心
			String lcCode = header.getProviderId();
			Date lcDate = branchBean.GetBranchById(lcCode).getBusinessDate();
			String lcStoreId = branchStorageBean.queryMainStore(lcCode).getStorageId();

			List<ReturnGoodsDetail> detailLst = returnGoodsDetailBean.query(formId);

			for (ReturnGoodsDetail detail : detailLst) {
				if (detail.getReturnCount() == 0) {
					returnGoodsDetailBean.deleteByItem(formId, detail.getItemId());
					continue;
				}
				// -----------------------------------------
				// 出入库记录

				// -------------- 餐厅
				String itemId = detail.getItemId();
				Double storage = storageBean.getItemCount(itemId, storeId);

				// 入库数量
				Double itemCountIn = 0.0;

				// 出库数量
				Double itemCountOut = detail.getReturnCount();

				// 期初数量
				Double orgiCount = (storage == null ? 0 : storage);

				// 结存数量
				Double resultCount = orgiCount - itemCountOut;

				StorageInOut storageInOut = new StorageInOut(branchId, storeId, restaurantDate, operationTime, itemId,
						detail.getItemUnitPrice(), orgiCount, itemCountIn, itemCountOut, resultCount,
						detail.getFormId(), "PSTH");

				// -------------- 物流中心
				Double centreStorage = storageBean.getItemCount(itemId, lcStoreId);

				// 入库数量
				Double centreItemCountIn = detail.getReturnCount();

				// 出库数量
				Double centreItemCountOut = 0.0;

				// 期初数量
				Double centreOrgiCount = (centreStorage == null ? 0 : centreStorage);

				// 结存数量
				Double centreResultCount = centreOrgiCount + centreItemCountIn;

				StorageInOut centreStorageInOut = new StorageInOut(lcCode, lcStoreId, lcDate, operationTime, itemId,
						detail.getItemUnitPrice(), centreOrgiCount, centreItemCountIn, centreItemCountOut,
						centreResultCount, detail.getFormId(), reason);

				storageInOutBean.saveEntity(storageInOut);
				storageInOutBean.saveEntity(centreStorageInOut);

				// -----------------------------------------

				// 餐厅库存减掉退货数，物流中心库存加上退货数
				storageBean.storageInOut(detail.getItemId(), storeId, lcStoreId, -detail.getReturnCount());
			}

			OperateLogUtil.record(header.getFormId(), "", "配送退货单审核");
		} else {// 采购退货
			List<ReturnGoodsDetail> detailLst = inputReturnBean.queryDetail(formId);
			for (ReturnGoodsDetail detail : detailLst) {
				if (detail.getReturnCount() == 0) {
					returnGoodsDetailBean.deleteByItem(formId, detail.getItemId());
					continue;
				}
				// -----------------------------------------
				// 出入库记录

				// -------------- 餐厅或物流中心
				boolean isOut = "WBTH".equals(reason) ? true : false;

				String itemId = detail.getItemId();
				Double storage = storageBean.getItemCount(itemId, storeId);

				Double diff = isOut ? detail.getReturnCount() : -detail.getReturnCount();

				// 入库数量
				Double itemCountIn = diff > 0 ? diff : 0;

				// 出库数量
				Double itemCountOut = diff < 0 ? -diff : 0;

				// 期初数量
				Double orgiCount = (storage == null ? 0 : storage);

				// 结存数量
				Double resultCount = orgiCount + diff;

				StorageInOut storageInOut = new StorageInOut(branchId, storeId, restaurantDate, operationTime, itemId,
						detail.getItemUnitPrice(), orgiCount, itemCountIn, itemCountOut, resultCount,
						detail.getFormId(), reason);

				storageInOutBean.saveEntity(storageInOut);
				// 库存减掉退货数
				storageBean.updateItemCount(detail.getItemId(), storeId, diff);

				if (!TextUtil.isEmpty(proStoId)) {
					Double proStorage = storageBean.getItemCount(itemId, proStoId);
					itemCountIn = itemCountOut;
					itemCountOut = 0.0;
					orgiCount = (proStorage == null ? 0 : proStorage);
					resultCount = orgiCount + itemCountIn;

					Date cfDate = branchBean.GetBranchById(providerId).getBusinessDate();
					storageInOut = new StorageInOut(providerId, proStoId, cfDate, operationTime, itemId,
							detail.getItemUnitPrice(), orgiCount, itemCountIn, itemCountOut, resultCount,
							detail.getFormId(), reason);

					storageInOutBean.saveEntity(storageInOut);
					// 央厂加库存
					storageBean.updateItemCount(detail.getItemId(), proStoId, itemCountIn);
				}
				// -----------------------------------------

			}

			OperateLogUtil.record(header.getFormId(), "", "采购退货单审核");

		}

		returnGoodsHeaderBean.audit(header.getFormId(), header.getAuditTime());

		// 修改退货单状态
		formStatusBean.saveEntity(new FormStatus(header.getFormId(), "已审核", userId));

		// 修改相应的配送单的状态
		shippingHeaderBean.saveReturnStatus(header.getFormRefId(), "已退货");
	}

	/**
	 * 餐厅配送退货单刪除
	 * 
	 * @param formId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String userId, String formId) throws NoPrivilegeException, SQLException, NoConnection {
		returnGoodsHeaderBean.deleteEntity(formId);
		returnGoodsDetailBean.deleteByForm(formId);

		// 修改退货单状态
		formStatusBean.saveEntity(new FormStatus(formId, "已删除", userId));

		OperateLogUtil.record(formId, LogType.DELETE, "删除配送退货单");
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setReturnGoodsDetailBean(ReturnGoodsDetailBean returnGoodsDetailBean) {
		this.returnGoodsDetailBean = returnGoodsDetailBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setInputReturnBean(InputReturnBean inputReturnBean) {
		this.inputReturnBean = inputReturnBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setReturnGoodsHeaderBean(ReturnGoodsHeaderBean returnGoodsHeaderBean) {
		this.returnGoodsHeaderBean = returnGoodsHeaderBean;
	}

	public void setBranchStorage(BranchStorage branchStorage) {
		this.branchStorage = branchStorage;
	}

}
