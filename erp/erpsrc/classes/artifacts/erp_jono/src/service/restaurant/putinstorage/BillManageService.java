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
 * 说明： 外部入库单管理
 */
package service.restaurant.putinstorage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputDetailBean;
import logic.form.InputHeaderBean;
import logic.form.StorageInOutBean;
import logic.store.BranchStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.InputDetail;
import pojo.form.InputHeader;
import pojo.form.OperationVersion;
import pojo.form.StorageInOut;
import pojo.store.BranchStorage;

import com.tanry.business.form.StatusConst;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.LogType;

public class BillManageService {

	private InputHeaderBean inputHeaderBean;
	private InputDetailBean inputDetailBean;
	private FormStatusBean formStatusBean;

	private StorageInOutBean storageInOutBean;

	private StorageBean storageBean;
	private BranchStorageBean branchStorageBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveBill(String userId, InputHeader inputHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		BranchStorage branchStorage = branchStorageBean.queryMainStore(inputHeader.getInputDepartmentId());
		inputHeader.setStorage(branchStorage.getStorageName());
		inputHeader.setStorageId(branchStorage.getStorageId());

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		List<InputDetail> detailLst = new ArrayList<InputDetail>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			InputDetail detail = new InputDetail();
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);

			Object itemCategory = json.get("itemCategory");
			if (!JSONNull.getInstance().equals(itemCategory)) {
				detail.setItemCategory((String) itemCategory);
			}

			detail.setItemDimension(json.getString("itemDimension"));

			Object itemSpecification = json.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				detail.setItemSpecification((String) itemSpecification);
			}

			if (json.get("orderCount") != null) {// 手动入库单不使用这个字段
				detail.setOrderCount(Double.parseDouble(json.get("orderCount") + ""));
				detail.setReceivedCount(Double.parseDouble(json.get("receivedCount") + ""));
			}
			detail.setItemUnitPrice(json.getDouble("itemUnitPrice"));

			if (json.get("receiveCount") != null) {
				detail.setReceiveCount(json.getDouble("receiveCount"));
			}
			if (json.get("differentCount") != null) {// 手动入库单不使用这个字段
				detail.setDifferentCount(Double.parseDouble(json.get("differentCount") + ""));
			}
			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);

			try {
				detail.setReceivePrice(json.getDouble("receivePrice"));
			} catch (Exception e) {
				detail.setReceivePrice(null);
			}

			try {
				detail.setReceiveAmt(json.getDouble("receiveAmt"));
			} catch (Exception e) {
				detail.setReceiveAmt(null);
			}

			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			if (json.get("expiredTime") != null) {
				Date expiredTime = DateTimeUtil.parse((String) json.get("expiredTime"));
				detail.setExpiredTime(expiredTime);
			}

			detailLst.add(detail);
		}

		inputHeader.setAllPayAmt(allPayAmt);
		inputHeader.setMaxPayItem(maxPayItem);

		// 入库单生成时包含了取最大单号加一的逻辑
		inputHeaderBean.saveEntity(inputHeader);

		String formId = inputHeader.getFormId();
		for (InputDetail detail : detailLst) {
			detail.setFormId(formId);
			inputDetailBean.saveEntity(detail);
		}

		formStatusBean.saveEntity(new FormStatus(formId, StatusConst.UN_AUDIT, userId));

		OperateLogUtil.record(formId, LogType.CREATE, "新增采购入库单");
		OperateVersionUtil.save(formId);

		return formId;
	}

	/**
	 * 餐厅入库单明细修改
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(InputHeader header, String jsonData, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			InputDetail detail = new InputDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			detail.setReceiveCount(json.getDouble("receiveCount"));

			// 手动入库单没有前置单据，则采购数据与已入库数据计算不出来
			try {
				detail.setDifferentCount(json.getDouble("differentCount"));
			} catch (Exception e) {
				detail.setDifferentCount(null);
			}

			detail.setReceivedCount(json.getDouble("receivedCount"));// 采购入库修改时不应该修改已入库数

			Double payAmt = null;
			Double receiveAmt = null;

			try {
				payAmt = json.getDouble("payAmt");
			} catch (Exception e) {
				payAmt = null;
			}

			try {
				receiveAmt = json.getDouble("receiveAmt");
			} catch (Exception e) {
				receiveAmt = null;
			}

			detail.setPayAmt(payAmt);
			detail.setReceiveAmt(receiveAmt);

			allPayAmt = allPayAmt + payAmt;

			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}

			inputDetailBean.updateEntity(detail);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		inputHeaderBean.updateEntity(header);

		OperateLogUtil.record(header.getFormId(), LogType.UPDATE, "采购入库单编辑");
		OperateVersionUtil.update(header.getFormId(), currentVersion, OperationVersion.EDITED);
	}

	// 因为审核数据是需要修改已入库数，所以跟修改数据有差异，所以添加新的方法auditBill
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(InputHeader header, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			InputDetail detail = new InputDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			detail.setReceiveCount(json.getDouble("receiveCount"));

			// 手动入库单没有前置单据，则采购数据与已入库数据计算不出来
			try {
				detail.setDifferentCount(json.getDouble("differentCount"));
			} catch (Exception e) {
				detail.setDifferentCount(null);
			}

			try {
				detail.setReceivedCount(json.getDouble("receivedCount") + json.getDouble("receiveCount"));
			} catch (Exception e) {
				detail.setReceivedCount(json.getDouble("receiveCount"));
			}

			Double payAmt = null;
			Double receiveAmt = null;

			try {
				payAmt = json.getDouble("payAmt");
			} catch (Exception e) {
				payAmt = null;
			}

			try {
				receiveAmt = json.getDouble("receiveAmt");
			} catch (Exception e) {
				receiveAmt = null;
			}

			detail.setPayAmt(payAmt);
			detail.setReceiveAmt(receiveAmt);

			allPayAmt = allPayAmt + payAmt;

			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}

			inputDetailBean.updateEntity(detail);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		inputHeaderBean.updateEntity(header);
	}

	/* 判断是否满足自动结案 */
	public boolean isFinish(String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		boolean flag = true;
		JSONArray arr = JSONArray.fromObject(jsonData);

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			double receivedCount = json.getDouble("receivedCount") + json.getDouble("receiveCount");
			if (receivedCount < json.getDouble("orderCount")) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 餐厅入库单审核
	 * 
	 * 暂时取消补单的情况
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, InputHeader header, String nextTag, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		auditBill(header, jsonData);

		// 更新采购单状态
		String status = "已结案";
		if (nextTag != null) {// 说明已勾选，允许下次入库，即不结案
			status = "已入库";
			if (isFinish(jsonData)) {// 判断是否符合自动结案条件
				status = "已结案";
			}
		}
		// 单据编号
		String formId = header.getFormId();

		// -----------------------------------------
		// 入库部门ID
		String inputDepartmentId = header.getInputDepartmentId();

		// 入库部门对应主仓库
		String mainStorageId = branchStorageBean.queryMainStore(inputDepartmentId).getStorageId();

		Date inputTime = header.getInputTime();

		// 操作时间
		Date operationTime = DateTimeUtil.getNow();

		// 业务类型 餐厅采购入库
		String reason = "CGRK";
		// -----------------------------------------

		inputHeaderBean.audit(formId, userId, header.getAuditTime());

		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;

			// -----------------------------------------
			// 出入库记录
			String itemId = json.getString("itemId");
			double receiveCount = json.getDouble("receiveCount");

			StorageInOut storageInOut = new StorageInOut();
			storageInOut.setBranchId(inputDepartmentId);
			storageInOut.setStorageId(mainStorageId);
			storageInOut.setFormId(formId);
			storageInOut.setBusinessDate(inputTime);
			storageInOut.setOperationTime(operationTime);

			// 原料编码
			storageInOut.setItemId(itemId);

			// 原料单价
			storageInOut.setItemUnitPrice(json.getDouble("itemUnitPrice"));

			// 入库数量
			storageInOut.setItemCountIn(receiveCount);

			// 出库数量
			storageInOut.setItemCountOut(0.0);

			// 期初数量
			Double storage = storageBean.getItemCount(itemId, mainStorageId);

			storageInOut.setOrgiCount(storage == null ? 0 : storage);

			Double resultCount = (storage == null ? 0 : storage) + receiveCount;
			// 结存数量
			storageInOut.setResultCount(resultCount);

			// 业务类型
			storageInOut.setReason(reason);

			storageInOutBean.saveEntity(storageInOut);

			// 库存所属的基本单位是门店的仓库
			storageBean.updateItemCount(json.getString("itemId"), mainStorageId, storage,
					json.getDouble("receiveCount"));
		}

		formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));
		if (!TextUtil.isEmpty(header.getFormRefId())) {
			formStatusBean.saveEntity(new FormStatus(header.getFormRefId(), status, userId));
		}

		OperateLogUtil.record(formId, LogType.AUDIT, "入库单审核");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.AUDITED);
		OperateLogUtil.record(formId, LogType.AUDIT, "审核采购单");
	}

	/**
	 * 主要用于手机端，审核后也要同时改变入库单和采购单的状态，并且增加出入库记录
	 * 
	 * @param formId
	 * @param userId
	 * @param businessDate
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditById(String formId, String userId, Date businessDate) throws NoPrivilegeException, SQLException,
			NoConnection {
		inputHeaderBean.audit(formId, userId, businessDate);

		formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));
	}

	/**
	 * 餐厅入库单刪除
	 * 
	 * @param formId
	 */
	public void deleteBill(String formId, Integer currentVersion) throws NoPrivilegeException, SQLException,
			NoConnection {
		inputDetailBean.deleteByForm(formId);
		inputHeaderBean.deleteEntity(formId);

		OperateLogUtil.record(formId, LogType.DELETE, "采购入库单删除");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.DELETED);
	}

	public void setInputDetailBean(InputDetailBean inputDetailBean) {
		this.inputDetailBean = inputDetailBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

}
