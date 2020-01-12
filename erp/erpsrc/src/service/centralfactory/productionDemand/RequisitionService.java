/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 15, 2014 by pw
 * Last edited on Jan 5, 2015 by liyzh
 * 
 * 说明： 领料单生成
 */
package service.centralfactory.productionDemand;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.RequisitionDetailBean;
import logic.form.RequisitionHeaderBean;
import logic.form.StorageInOutBean;
import logic.form.WorkOrderHeaderBean;
import logic.form.WorkOrderItemBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.RequisitionDetail;
import pojo.form.RequisitionHeader;
import pojo.form.StorageInOut;
import pojo.form.WorkOrderItem;
import pojo.store.BranchStorage;

import com.tanry.business.form.FormType;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;

public class RequisitionService {
	private FormStatusBean formStatusBean;

	private RequisitionDetailBean requisitionDetailBean;
	private RequisitionHeaderBean requisitionHeaderBean;
	private WorkOrderHeaderBean workOrderHeaderBean;
	private BranchBean branchBean;
	private StorageInOutBean storageInOutBean;
	private StorageBean storageBean;

	private BranchStorageBean branchStorageBean;
	private WorkOrderItemBean workOrderItemBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doBatch(String userId, String userName, RequisitionHeader header, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		String cfCode = header.getFormBranchId();
		Date businessDate = header.getFormTime();
		String formType = header.getFormType();

		for (Object obj : arr) {
			int newSerial = requisitionHeaderBean.newSerial(businessDate, formType, cfCode);
			String prefix = "CL";
			if (FormType.REQUISITION_PRODUCE.equals(formType)) {
				prefix = "LL";
			} else if (FormType.REQUISITION_RETURN.equals(formType)) {
				prefix = "TL";
			} else if (FormType.REQUISITION_MANUAL.equals(formType)) {
				prefix = "NLL";
			}
			String formId = FormUtil.genFormIdBody(prefix, cfCode, header.getFormTime())
					+ FormUtil.formatSerial(newSerial);
			header.setFormId(formId);

			JSONObject json = (JSONObject) obj;
			String formRefId = json.getString("formId");
			header.setFormRefId(formRefId);
			header.setWorkshop(json.getString("workshop"));
			workOrderHeaderBean.updateBatchFlag(formRefId);// 修改批量生成状态
			String queryStr = " h.FORM_ID = '" + formRefId + "'  AND i.ITEM_COUNT > i.RECEIVED_COUNT ";
			List<RequisitionDetail> detailLst = requisitionDetailBean.transformToDetail(formRefId, queryStr);
			for (RequisitionDetail detail : detailLst) {
				// 单据编码
				detail.setFormId(header.getFormId());
				requisitionDetailBean.saveEntity(detail);
			}
			requisitionHeaderBean.saveEntity(header);

			formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doCommit(String userId, String userName, RequisitionHeader header, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = header.getFormBranchId();

		Date businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();
		header.setFormTime(businessDate);
		// request,extra,notwork,return
		String formType = header.getFormType();
		int newSerial = requisitionHeaderBean.newSerial(businessDate, formType, cfCode);
		String prefix = "CL";
		if (FormType.REQUISITION_PRODUCE.equals(formType)) {
			prefix = "LL";
		} else if (FormType.REQUISITION_RETURN.equals(formType)) {
			prefix = "TL";
		} else if (FormType.REQUISITION_MANUAL.equals(formType)) {
			prefix = "NLL";
		}
		String formId = FormUtil.genFormIdBody(prefix, cfCode, header.getFormTime()) + FormUtil.formatSerial(newSerial);
		header.setFormId(formId);

		JSONArray arr = JSONArray.fromObject(jsonData);
		// detail
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			RequisitionDetail detail = new RequisitionDetail();

			// 单据编码
			detail.setFormId(header.getFormId());

			// 商品编码
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 商品名称
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);

			// 商品单位
			detail.setItemDimension(json.getString("itemDimension"));

			// 商品规格
			Object itemSpecification = json.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				detail.setItemSpecification((String) itemSpecification);
			}

			// 计划领料数
			if (json.get("itemCount") != null) {
				detail.setItemCount(json.getDouble("itemCount"));
			}

			// 领料数
			if (json.get("receiveCount") != null) {
				detail.setReceiveCount(json.getDouble("receiveCount"));
			}

			// 未领料数
			if (json.get("differentCount") != null) {
				detail.setDifferentCount(json.getDouble("differentCount"));
			}

			requisitionDetailBean.saveEntity(detail);
		}

		header.setFormMakerId(userId);
		header.setFormMaker(userName);
		requisitionHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doConfirm(RequisitionHeader requisitionHeader, String jsonData, String branchId, String loginUserId,
			String loginUserName) throws NoPrivilegeException, SQLException, NoConnection {

		String formType = requisitionHeader.getFormType();
		String formId = requisitionHeader.getFormId();
		Date formTime = requisitionHeader.getFormTime();

		JSONArray arr = JSONArray.fromObject(jsonData);

		/******************************************/
		// 进销存
		Date operationTime = DateTimeUtil.getNow();
		String reason = "SCLL";
		String mainStorageId = branchStorageBean.queryMainStore(branchId).getStorageId();
		/******************************************/

		requisitionHeader.setAuditTime(branchBean.GetBranchById(branchId).getBusinessDate());
		requisitionHeader.setAuditorId(loginUserId);
		requisitionHeader.setAuditor(loginUserName);

		requisitionHeaderBean.audit(requisitionHeader);

		JSONArray orginCountArr = JSONArray.fromObject(requisitionDetailBean.getItemOrginCount(formId, mainStorageId));

		// detail
		int index = 0;
		for (Object obj : arr) {
			// 审核时筛选出领料数大于0的原料
			JSONObject json = (JSONObject) obj;
			Double receiveCount = json.getDouble("receiveCount");
			if (receiveCount > 0) {
				String itemId = json.getString("itemId");

				WorkOrderItem workOrderItem = new WorkOrderItem();
				workOrderItem.setFormId(requisitionHeader.getFormRefId());
				workOrderItem.setItemId(itemId);
				RequisitionDetail detail = new RequisitionDetail();
				detail.setItemId(itemId);
				detail.setFormId(formId);

				// 期初
				Double orgiCount = JSONNull.getInstance().equals(orginCountArr.getJSONObject(index).get("item_count")) ? null
						: Double.valueOf(orginCountArr.getJSONObject(index).get("item_count").toString());

				if ("produce".equals(formType) || "extra".equals(formType)) {
					if ("extra".equals(formType)) {
						reason = "SCCL";
					}

					detail.setReceiveCount(receiveCount);
					// 入库数量
					Double itemCountIn = 0.0;

					// 出库数量
					Double itemCountOut = receiveCount;

					// 结存数量
					Double resultCount = (orgiCount == null ? 0 : orgiCount) - receiveCount;

					// 结存单价
					Double itemUnitPrice = json.getDouble("itemPrice");

					// 已领料数
					workOrderItem.setReceivedCount(receiveCount + json.getDouble("receivedCount"));

					requisitionDetailBean.updateReceiveCount(detail);// 更新领料数
					StorageInOut storageInOut = new StorageInOut(branchId, mainStorageId, formTime, operationTime,
							itemId, itemUnitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, formId, reason);

					storageInOutBean.saveEntity(storageInOut);
					storageBean.updateItemCount(itemId, mainStorageId, orgiCount, -receiveCount);

					workOrderItemBean.updateEntity(workOrderItem);
				} else if ("return".equals(formType)) {
					reason = "SCTL";

					Double itemCount = json.getDouble("itemCount");

					// 入库数量
					Double itemCountIn = itemCount;

					detail.setItemCount(itemCount);
					requisitionDetailBean.updateItemCount(detail);// 更新退料数
					// 出库数量
					Double itemCountOut = 0.0;

					// 结存数量
					Double resultCount = (orgiCount == null ? 0 : orgiCount) + itemCountIn;

					// 已退料数
					workOrderItem.setReturnedCount(json.getDouble("returnedCount") + json.getDouble("itemCount"));

					// 结存单价
					Double itemUnitPrice = json.getDouble("itemPrice");

					StorageInOut storageInOut = new StorageInOut(branchId, mainStorageId, formTime, operationTime,
							itemId, itemUnitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, formId, reason);

					storageInOutBean.saveEntity(storageInOut);

					storageBean.updateItemCount(itemId, mainStorageId, orgiCount, itemCount);

				}
			}
			index++;
		}

		if ("produce".equals(formType)) {
			// 如果领料单所关联的工单的所有原料都已领完料，那么就更新对于工单 RECEIVED_STATUS 字段
			requisitionDetailBean.updateReceiveStatus(requisitionHeader.getFormRefId());
		}

		formStatusBean.saveEntity(new FormStatus(requisitionHeader.getFormId(), "已审核", loginUserId));

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doAudit(RequisitionHeader requisitionHeader, String jsonData, String branchId, String loginUserId,
			String loginUserName) throws NoPrivilegeException, SQLException, NoConnection {
		String formId = requisitionHeader.getFormId();
		Date formTime = requisitionHeader.getFormTime();
		String cfCode = branchId;
		Date cfDate = branchBean.GetBranchById(cfCode).getBusinessDate();
		requisitionHeader.setAuditTime(cfDate);
		requisitionHeader.setAuditorId(cfCode);
		requisitionHeader.setAuditor(loginUserName);
		String storageId = requisitionHeader.getStorageId();
		BranchStorage branchStorage = branchStorageBean.queryByStorageId(requisitionHeader.getStorageId());
		if (branchStorage != null) {
			requisitionHeader.setStorage(branchStorage.getStorageName());
		}
		JSONArray arr = JSONArray.fromObject(jsonData);

		// 进销存
		Date operationTime = DateTimeUtil.getNow();
		String reason = "FGLL";

		// detail
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			String itemId = json.getString("itemId");
			RequisitionDetail detail = new RequisitionDetail();
			detail.setItemId(itemId);
			detail.setFormId(formId);

			// 期初
			Double storage = storageBean.getItemCount(itemId, storageId);

			Double receiveCount = json.getDouble("receiveCount");
			detail.setReceiveCount(receiveCount);
			requisitionDetailBean.updateReceiveCount(detail);// 更新领料数
			// 入库数量
			Double itemCountIn = 0.0;

			// 出库数量
			Double itemCountOut = receiveCount;

			// 期初数量
			Double orgiCount = (storage == null ? 0 : storage);

			// 结存数量
			Double resultCount = orgiCount - receiveCount;

			// 结存单价
			Double itemUnitPrice = json.getDouble("itemPrice");

			StorageInOut storageInOut = new StorageInOut(branchId, storageId, formTime, operationTime, itemId,
					itemUnitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, formId, reason);

			storageInOutBean.saveEntity(storageInOut);
			storageBean.updateItemCount(itemId, storageId, -receiveCount);

		}
		requisitionHeaderBean.audit(requisitionHeader);
		formStatusBean.saveEntity(new FormStatus(requisitionHeader.getFormId(), BillStatus.AUDITED_CN, loginUserId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(RequisitionHeader requisitionHeader, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String formId = requisitionHeader.getFormId();
		BranchStorage branchStorage = branchStorageBean.queryByStorageId(requisitionHeader.getStorageId());
		if (branchStorage != null) {
			requisitionHeader.setStorage(branchStorage.getStorageName());
		}
		JSONArray arr = JSONArray.fromObject(jsonData);

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			String itemId = json.getString("itemId");
			RequisitionDetail detail = new RequisitionDetail();
			detail.setItemId(itemId);
			detail.setFormId(formId);

			Double receiveCount = json.getDouble("receiveCount");
			detail.setReceiveCount(receiveCount);
			requisitionDetailBean.updateReceiveCount(detail);// 更新领料数

		}
		requisitionHeaderBean.updateEntity(requisitionHeader);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doDelete(RequisitionHeader requisitionHeader, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = requisitionHeader.getFormId();
		requisitionDetailBean.deleteByForm(formId);
		requisitionHeaderBean.deleteEntity(formId);
		OperateLogUtil.record(formId, LogType.DELETE, "删除非工单领料单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.DELETED);
	}

	/**
	 * 中央工厂领料单修改
	 * 
	 * @param header
	 * @param jsonData
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(RequisitionHeader header, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			RequisitionDetail detail = new RequisitionDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemName(json.getString("itemName"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = json.getString("itemSpecification");
			detail.setItemSpecification(itemSpecification);
			detail.setItemCount(json.getDouble("itemCount"));

			detail.setItemId(itemId);
			detail.setReceiveCount(json.getDouble("receiveCount"));
			detail.setDifferentCount(json.getDouble("differentCount"));
			/*
			 * detail未加expiredTime
			 */

			requisitionDetailBean.updateEntity(detail);
		}

		OperateLogUtil.record(header.getFormId(), LogType.UPDATE, "领料单编辑");
		OperateVersionUtil.update(header.getFormId(), currentVersion, OperationVersion.EDITED);
	}

	/**
	 * 领料单刪除
	 * 
	 * @param formId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String formId, Integer currentVersion) throws NoPrivilegeException, SQLException,
			NoConnection {
		requisitionHeaderBean.deleteEntity(formId);
		requisitionDetailBean.deleteByForm(formId);

		OperateLogUtil.record(formId, LogType.DELETE, "领料单删除");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.DELETED);
	}

	public void setRequisitionHeaderBean(RequisitionHeaderBean requisitionHeaderBean) {
		this.requisitionHeaderBean = requisitionHeaderBean;
	}

	public void setRequisitionDetailBean(RequisitionDetailBean requisitionDetailBean) {
		this.requisitionDetailBean = requisitionDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setWorkOrderItemBean(WorkOrderItemBean workOrderItemBean) {
		this.workOrderItemBean = workOrderItemBean;
	}

}
