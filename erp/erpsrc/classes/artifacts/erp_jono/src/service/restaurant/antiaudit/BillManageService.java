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
 * 说明： 配送反审核单管理
 */
package service.restaurant.antiaudit;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.ShippingAntiauditDetailBean;
import logic.form.ShippingAntiauditHeaderBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.form.StorageInOutBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.ShippingAntiauditDetail;
import pojo.form.ShippingAntiauditHeader;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.form.StorageInOut;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;

public class BillManageService {

	private ShippingAntiauditHeaderBean shippingAntiauditHeaderBean;
	private ShippingAntiauditDetailBean shippingAntiauditDetailBean;
	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private FormStatusBean formStatusBean;
	private StorageBean storageBean;

	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private StorageInOutBean storageInOutBean;

	private BranchStorageBean branchStorageBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveBill(String userId, ShippingAntiauditHeader antiauditHeader, String jsonData, String branchType,
			ShippingHeader shippingHeader) throws NoPrivilegeException, SQLException, NoConnection {
		String formRefId = antiauditHeader.getFormRefId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingAntiauditDetail detail = new ShippingAntiauditDetail();
			detail.setFormRefId(formRefId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			detail.setReceiveCount(json.getDouble("receiveCount"));
			detail.setPayAmt(json.getDouble("payAmt"));
			detail.setAntiauditReceiveCount(json.getDouble("antiauditReceiveCount"));
			detail.setAntiauditPayAmt(json.getDouble("antiauditPayAmt"));
			shippingAntiauditDetailBean.saveEntity(detail);
		}

		if (BranchType.LOGISTICSCENTER.equals(branchType)) {
			antiauditHeader.setAntiauditBranchId(shippingHeader.getProviderId());
			antiauditHeader.setAntiauditBranch(shippingHeader.getProvider());
		}

		shippingAntiauditHeaderBean.saveEntity(antiauditHeader);

		shippingHeaderBean.saveAntiStatus(formRefId, "反审核中");

		OperateLogUtil.record(formRefId, null, "配送单反审核");
	}

	/**
	 * 餐厅配送反审核单修改
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(String userId, ShippingAntiauditHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formRefId = header.getFormRefId();

		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingAntiauditDetail detail = new ShippingAntiauditDetail();
			detail.setFormRefId(formRefId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			detail.setAntiauditReceiveCount(json.getDouble("antiauditReceiveCount"));
			detail.setAntiauditPayAmt(json.getDouble("antiauditPayAmt"));

			shippingAntiauditDetailBean.updateEntity(detail);
		}
		shippingAntiauditHeaderBean.updateEntity(header);

		String status = formStatusBean.getCurrentStatus(formRefId);
		if ("撤销反审核".equals(status)) {
			shippingHeaderBean.saveAntiStatus(formRefId, "反审核中");
		}

		OperateLogUtil.record(formRefId, null, "餐厅配送单反审核编辑");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void confirmBill(String userId, ShippingAntiauditHeader header, String branchId, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		// 修改反审核单状态
		FormStatus fstatus = new FormStatus();
		String formRefId = header.getFormRefId();
		fstatus.setFormId(formRefId);
		fstatus.setStatus("已确认");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	// 撤销反审核
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doRepeal(String userId, ShippingAntiauditHeader header, String branchId) throws NoPrivilegeException,
			SQLException, NoConnection {
		// 修改反审核单状态
		String formRefId = header.getFormRefId();
		formStatusBean.saveEntity(new FormStatus(formRefId, "撤销反审核", userId));

		OperateLogUtil.record(formRefId, null, "餐厅撤销配送反审核");
	}

	/**
	 * 餐厅配送反审核确认操作
	 * 
	 * @param header
	 * @param branchId
	 *            配送单所在的门店id，即订货部门，用于更新库存
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, ShippingAntiauditHeader header, String branchId, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		// 操作时间
		Date operationTime = DateTimeUtil.getNow();

		// 业务类型 餐厅配送反审核
		String reason = "PSFSH";
		// -----------------------------------------

		String formRefId = header.getFormRefId();

		Date restaurantDate = branchBean.GetBranchById(branchId).getBusinessDate();
		String storeId = branchStorageBean.queryMainStore(branchId).getStorageId();

		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;

			// 修改配送单的实收数和金额、差异数
			ShippingDetail sdetail = new ShippingDetail();
			sdetail.setFormId(formRefId);
			String itemId = json.getString("itemId");
			sdetail.setItemId(itemId);
			Double antiauditReceiveCount = json.getDouble("antiauditReceiveCount");
			Double deliveryCount = json.getDouble("deliveryCount");
			sdetail.setDeliveryCount(deliveryCount);
			sdetail.setReceiveCount(antiauditReceiveCount);
			sdetail.setDifferentCount(deliveryCount - antiauditReceiveCount);
			sdetail.setPayAmt(json.getDouble("antiauditPayAmt"));
			shippingDetailBean.updateEntity(sdetail);

			Double receiveCount = json.getDouble("receiveCount");

			Double storage = storageBean.getItemCount(itemId, storeId);

			// diff 大于零 餐厅加库存，物流中心减库存
			Double diff = antiauditReceiveCount - receiveCount;

			// 入库数量
			Double itemCountIn = diff > 0 ? diff : 0;

			// 出库数量
			Double itemCountOut = diff < 0 ? -diff : 0;

			// 期初数量
			Double orgiCount = (storage == null ? 0 : storage);

			// 结存数量
			Double resultCount = orgiCount + diff;

			StorageInOut storageInOut = new StorageInOut(branchId, storeId, restaurantDate, operationTime, itemId,
					json.getDouble("itemUnitPrice"), orgiCount, itemCountIn, itemCountOut, resultCount, formRefId,
					reason);

			storageInOutBean.saveEntity(storageInOut);

			// 反审核只影响餐厅的实收数，所以反审核只影响餐厅的库存
			storageBean.updateItemCount(itemId, storeId, antiauditReceiveCount - receiveCount);
		}

		// 修改反审核单状态
		shippingHeaderBean.saveAntiStatus(formRefId, "已反审核");

		shippingAntiauditHeaderBean.audit(header.getFormRefId(), header.getAuditTime());

		OperateLogUtil.record(formRefId, null, "配送反审核确认");
	}

	/**
	 * 餐厅配送反审核单刪除
	 * 
	 * @param formId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String formRefId) throws NoPrivilegeException, SQLException, NoConnection {
		shippingAntiauditHeaderBean.deleteEntity(formRefId);
		shippingAntiauditDetailBean.deleteByForm(formRefId);
	}

	/**
	 * 撤消反审核
	 * 
	 * @param formRefId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void revertBill(String formRefId, String userId) throws NoPrivilegeException, SQLException, NoConnection {
		deleteBill(formRefId);

		// 修改反审核状态为空
		shippingHeaderBean.saveAntiStatus(formRefId, "");

		OperateLogUtil.record(formRefId, null, "餐厅撤销配送反审核");
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setShippingAntiauditHeaderBean(ShippingAntiauditHeaderBean shippingAntiauditHeaderBean) {
		this.shippingAntiauditHeaderBean = shippingAntiauditHeaderBean;
	}

	public void setShippingAntiauditDetailBean(ShippingAntiauditDetailBean shippingAntiauditDetailBean) {
		this.shippingAntiauditDetailBean = shippingAntiauditDetailBean;
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

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}
}
