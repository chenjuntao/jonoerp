//===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014年9月18日 by pw
// Last edited on 2014年9月18日 by pw
//
// Comments:餐厅调拨
//
//===============================================
package service.restaurant.allocateitem;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.StorageInOutBean;
import logic.form.TransferDetailBean;
import logic.form.TransferHeaderBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.StorageInOut;
import pojo.form.TransferDetail;
import pojo.form.TransferHeader;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.LogType;
import com.tanry.framework.util.constant.PrefixEnum;

public class CreateBillService {

	private TransferHeaderBean transferHeaderBean;;
	private TransferDetailBean transferDetailBean;

	private FormStatusBean formStatusBean;
	private StorageBean storageBean;

	private StorageInOutBean storageInOutBean;

	private Integer preVersion;
	private Integer currentVersion;
	private OperationVersionBean operationVersionBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String doCommit(String userId, TransferHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String inBranchId = header.getInBranchId();
		Date formTime = header.getFormTime();
		String formBody = FormUtil.genFormIdBody("DB", inBranchId, formTime);

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);

		header.setFormId(formId);
		JSONArray arr = JSONArray.fromObject(jsonData);

		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			TransferDetail detail = new TransferDetail();

			// 表单ID
			detail.setFormId(formId);

			// 商品编码
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 商品名称
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);

			// 商品类别
			detail.setItemCategory(json.getString("itemCategory"));

			// 商品单位
			detail.setItemDimension(json.getString("itemDimension"));

			// 商品规格
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);

			// 申请调拨数量
			if (json.get("applyCount") != null) {
				detail.setApplyCount(json.getDouble("applyCount"));
			}

			// 单价
			if (json.get("itemUnitPrice") != null) {
				detail.setUnitPrice(json.getDouble("itemUnitPrice"));
			}

			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);

			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			transferDetailBean.saveEntity(detail);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);

		transferHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));

		OperateLogUtil.record(formId, LogType.CREATE, "新增调拨单");
		OperateVersionUtil.save(formId);

		return formId;
	}

	/**
	 * 餐厅调拨单修改
	 * 
	 * @param header
	 * @param jsonData
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateAllocateBill(TransferHeader header, Integer currentVersion, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			TransferDetail detail = new TransferDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 申请调拨数量
			Double applyCount = json.getDouble("applyCount");
			detail.setApplyCount(applyCount);

			// 金额
			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);

			transferDetailBean.auditUpdateEntity(detail);
		}
		transferHeaderBean.updateEntity(header);

		OperateLogUtil.record(formId, LogType.UPDATE, "编辑调拨单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.EDITED);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateOutBill(TransferHeader header, String jsonData, Date outbusinessDate)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);

		Date confirmDate = header.getConfirmTime();

		// -----------------------------------------

		// 操作时间
		Date operationTime = DateTimeUtil.getNow();

		String reason = PrefixEnum.DB;

		String inBranchId = header.getInBranchId();
		String inStorageId = header.getInStorageId();

		String outBranchId = header.getOutBranchId();
		String outStorageId = header.getOutStorageId();
		// -----------------------------------------

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			TransferDetail detail = new TransferDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			Double actualCount = json.getDouble("applyCount");
			detail.setActualCount(actualCount);

			detail.setDifferentCount(0.0);

			transferDetailBean.outUpdateEntity(detail);

			// -----------------------------------------
			// 出入库记录

			// -------------- 调入餐厅

			StorageInOut inCondition = null;

			Double inStorage = (inCondition != null) ? inCondition.getOrgiCount() : storageBean.getItemCount(itemId,
					inStorageId);

			// 入库数量
			Double inItemCountIn = actualCount;

			// 出库数量
			Double inItemCountOut = 0.0;

			// 期初数量
			Double inOrgiCount = (inStorage == null ? 0 : inStorage);

			// 结存数量
			Double inResultCount = inOrgiCount + inItemCountIn;

			StorageInOut inStorageInOut = new StorageInOut(inBranchId, inStorageId, confirmDate, operationTime, itemId,
					json.getDouble("unitPrice"), inOrgiCount, inItemCountIn, inItemCountOut, inResultCount, formId,
					reason);

			storageInOutBean.saveEntity(inStorageInOut);

			// -------------- 调出餐厅
			StorageInOut outCondition = null;
			Double outStorage = (outCondition != null) ? outCondition.getOrgiCount() : storageBean.getItemCount(itemId,
					outStorageId);

			// 入库数量
			Double outItemCountIn = 0.0;

			// 出库数量
			Double outItemCountOut = actualCount;

			// 期初数量
			Double outOrgiCount = (outStorage == null ? 0 : outStorage);

			// 结存数量
			Double outResultCount = outOrgiCount - outItemCountOut;

			StorageInOut outStorageInOut = new StorageInOut(outBranchId, outStorageId, confirmDate, operationTime,
					itemId, json.getDouble("unitPrice"), outOrgiCount, outItemCountIn, outItemCountOut, outResultCount,
					formId, reason);

			storageInOutBean.saveEntity(outStorageInOut);

			storageBean.storageInOut(itemId, inStorageId, outStorageId, actualCount);
		}
	}

	/**
	 * 餐厅调拨单确认
	 * 
	 * @param header
	 * @param jsonData
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void confirmBill(String userId, String userName, TransferHeader header, String jsonData,
			Date outbusinessDate, Integer currentVersions) throws NoPrivilegeException, SQLException, NoConnection {
		updateOutBill(header, jsonData, outbusinessDate);

		String formId = header.getFormId();
		transferHeaderBean.confirm(formId, userId, userName, header.getConfirmTime());

		formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));
		OperateLogUtil.record(formId, null, "审核调拨单");
		OperateVersionUtil.update(formId, currentVersions, OperationVersion.AUDITED);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		transferHeaderBean.deleteEntity(formId);
		transferDetailBean.deleteByForm(formId);

		OperateLogUtil.record(formId, LogType.DELETE, "删除调拨单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.DELETED);
	}

	public void setTransferHeaderBean(TransferHeaderBean transferHeaderBean) {
		this.transferHeaderBean = transferHeaderBean;
	}

	public void setTransferDetailBean(TransferDetailBean transferDetailBean) {
		this.transferDetailBean = transferDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
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

}
