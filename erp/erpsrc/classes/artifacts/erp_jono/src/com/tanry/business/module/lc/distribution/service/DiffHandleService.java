/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 6, 2015 by liyzh
 * Last edited on Aug 6, 2015 by liyzh
 * 
 * 说明： 物流中心处理配送差异
 */
package com.tanry.business.module.lc.distribution.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputDetailBean;
import logic.form.InputHeaderBean;
import logic.form.LossDetailBean;
import logic.form.LossHeaderBean;
import logic.form.OperationVersionBean;
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
import pojo.form.InputDetail;
import pojo.form.InputHeader;
import pojo.form.LossDetail;
import pojo.form.LossHeader;
import pojo.form.ShippingHeader;
import pojo.form.StorageInOut;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.DifferentReason;

public class DiffHandleService {

	private LossHeaderBean lossHeaderBean;
	private LossDetailBean lossDetailBean;
	private FormStatusBean formStatusBean;
	private StorageBean storageBean;
	private StorageInOutBean storageInOutBean;
	private OperationVersionBean operationVersionBean;
	private ShippingHeaderBean shippingHeaderBean;
	private BranchStorageBean branchStorageBean;
	private InputDetailBean inputDetailBean;
	private InputHeaderBean inputHeaderBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	/**
	 * 遍历明细列表，原来代码是如果是报损，则生成报损单（各个物料一起生成一个报损单）；如果是拒收，则增加库存数并创建入库记录；
	 * 
	 * @param sHeader
	 * @param jsonData
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String doHandle1(String userId, ShippingHeader sHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);

		String shippingId = sHeader.getFormId();
		String lcBranchId = sHeader.getProviderId();
		String lcStorageId = branchStorageBean.queryMainStore(lcBranchId).getStorageId();
		String lcBranch = sHeader.getProvider();
		String lcStorage = branchStorageBean.queryMainStore(lcBranchId).getStorageName();
		String lossNote = "";// 记录报损原因到报损单表头
		String jsNote = "";// 记录拒受原因到入库单表头

		Date businessDate = branchBean.GetBranchById(lcBranchId).getBusinessDate();
		List<LossDetail> lossDetailLst = new ArrayList<LossDetail>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			String itemId = json.getString("itemId");
			String itemName = json.getString("itemName");
			Double storageCount = storageBean.getItemCount(itemId, lcStorageId);

			String differentReason = json.getString("differentReason");
			Double differentCount = json.getDouble("differentCount");
			Double itemUnitPrice = json.getDouble("itemUnitPrice");
			if (DifferentReason.LOSS.equals(differentReason)) {
				LossDetail detail = new LossDetail();
				detail.setItem_id(itemId);
				String differentNote = itemName + "-" + json.getString("differentNote") + ",";
				lossNote += differentNote;
				detail.setItem_name(itemName);
				detail.setItem_category(json.getString("itemCategory"));
				detail.setItem_dimension(json.getString("itemDimension"));
				String itemSpecification = (String) json.get("itemSpecification");
				detail.setItem_specification(itemSpecification);
				detail.setItem_count(differentCount);
				detail.setStorage_count(storageCount);
				detail.setUnit_price(itemUnitPrice);
				double payAmt = json.getDouble("payAmt");
				detail.setPay_amt(payAmt);
				lossDetailLst.add(detail);
			} else {
				String differentNote = itemName + "-" + json.getString("differentNote") + ",";
				jsNote += differentNote;
				// 入库数量
				Double itemCountIn = differentCount;
				// 出库数量
				Double itemCountOut = 0.0;
				// 期初数量
				Double orgiCount = (storageCount == null ? 0 : storageCount);
				// 结存数量
				Double resultCount = orgiCount + itemCountIn;

				StorageInOut storageInOut = new StorageInOut(lcBranchId, lcStorageId, businessDate, new Date(), itemId,
						itemUnitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, shippingId, "JSRK");// 拒收入库
				storageInOutBean.saveEntity(storageInOut);

				storageBean.updateItemCount(itemId, lcStorageId, itemCountIn);
			}
		}

		String lossFormId = null;
		if (lossDetailLst.size() > 0) {
			LossHeader lossHeader = new LossHeader();
			String prefix = "BSR";
			String formBody = FormUtil.genFormIdBody(prefix, lcBranchId, businessDate);
			Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
			lossFormId = formBody + FormUtil.formatSerial(newSerial);

			lossHeader.setFormId(lossFormId);
			lossHeader.setLossBranchId(lcBranchId);
			lossHeader.setStorageId(lcStorageId);
			lossHeader.setLossManId(userId);
			lossHeader.setLossTime(businessDate);
			lossHeader.setFormNote(lossNote);
			lossHeader.setLossBranch(lcBranch);
			lossHeader.setStorage(lcStorage);
			createLossBill(lossHeader, lossDetailLst);
		}

		shippingHeaderBean.saveDifferentStatus(shippingId, "已处理差异");
		OperateVersionUtil.save(lossFormId);
		return lossFormId;
	}

	/**
	 * 遍历明细列表，原来代码是如果是报损，则生成报损单（各个物料一起生成一个报损单）；如果是拒收，则增加库存数并创建入库记录；
	 * 现在修改为如果拒收则生成拒收入库单，同时增加库存数并创建入库记录；报损则不管；
	 * 
	 * @param sHeader
	 * @param jsonData
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String doHandle(String userId, ShippingHeader sHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);

		String shippingId = sHeader.getFormId();
		String lcBranchId = sHeader.getProviderId();
		String lcStorageId = branchStorageBean.queryMainStore(lcBranchId).getStorageId();
		String lcBranch = sHeader.getProvider();
		String lcStorage = branchStorageBean.queryMainStore(lcBranchId).getStorageName();
		String lossNote = "";// 记录报损原因到报损单表头
		String jsNote = "";// 记录拒受原因到入库单表头
		String inputFormId = null;
		String prefix = "LKJ";
		Date businessDate = branchBean.GetBranchById(lcBranchId).getBusinessDate();
		String formBody = FormUtil.genFormIdBody(prefix, lcBranchId, businessDate);
		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		inputFormId = formBody + FormUtil.formatSerial(newSerial);

		List<InputDetail> inputDetailLst = new ArrayList<InputDetail>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			String itemId = json.getString("itemId");
			String itemName = json.getString("itemName");
			Double storageCount = storageBean.getItemCount(itemId, lcStorageId);

			String differentReason = json.getString("differentReason");
			Double differentCount = json.getDouble("differentCount");
			Double itemUnitPrice = json.getDouble("itemUnitPrice");
			if (DifferentReason.REFUSE.equals(differentReason)) {
				InputDetail detail = new InputDetail();
				detail.setItemId(itemId);
				String differentNote = itemName + "-" + json.getString("differentNote") + ",";
				lossNote += differentNote;
				detail.setItemName(itemName);
				detail.setItemCategory(json.getString("itemCategory"));
				detail.setItemDimension(json.getString("itemDimension"));
				String itemSpecification = (String) json.get("itemSpecification");
				detail.setItemSpecification(itemSpecification);
				detail.setReceiveCount(differentCount);
				detail.setItemUnitPrice(itemUnitPrice);
				double payAmt = json.getDouble("payAmt");
				detail.setPayAmt(payAmt);

				inputDetailLst.add(detail);

				// 入库数量
				Double itemCountIn = differentCount;
				// 出库数量
				Double itemCountOut = 0.0;
				// 期初数量
				Double orgiCount = (storageCount == null ? 0 : storageCount);
				// 结存数量
				Double resultCount = orgiCount + itemCountIn;

				StorageInOut storageInOut = new StorageInOut(lcBranchId, lcStorageId, businessDate, new Date(), itemId,
						itemUnitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, inputFormId, "JSRK");// 拒收入库
				storageInOutBean.saveEntity(storageInOut);

				storageBean.updateItemCount(itemId, lcStorageId, itemCountIn);
			}
		}

		if (inputDetailLst.size() > 0) {
			InputHeader inputHeader = new InputHeader();
			inputHeader.setFormId(inputFormId);
			inputHeader.setFormRefId(sHeader.getFormId());
			inputHeader.setInputerId(userId);
			inputHeader.setInputDepartmentId(lcBranchId);
			inputHeader.setInputDepartment(lcBranch);
			inputHeader.setStorage(lcStorage);
			inputHeader.setStorageId(lcStorageId);
			inputHeader.setProviderId(sHeader.getProviderId());
			inputHeader.setProvider(sHeader.getProvider());
			inputHeader.setInputTime(businessDate);
			inputHeader.setAuditorId(userId);
			inputHeader.setAuditTime(businessDate);
			Date auditTimeActual = DateTimeUtil.getNow();
			inputHeader.setAuditTimeActual(auditTimeActual);
			inputHeader.setFormNote(lossNote);
			createInputBill(inputHeader, inputDetailLst);
		}

		shippingHeaderBean.saveDifferentStatus(shippingId, "已处理差异");
		OperateVersionUtil.save(inputFormId);
		return inputFormId;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void createLossBill(LossHeader lossHeader, List<LossDetail> detailLst) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = lossHeader.getFormId();

		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";
		for (LossDetail detail : detailLst) {
			detail.setForm_id(formId);
			lossDetailBean.saveEntity(detail);

			Double payAmt = detail.getPay_amt();
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + detail.getItem_id() + "]" + detail.getItem_name();
			}
		}
		lossHeader.setAllPayAmt(allPayAmt);
		lossHeader.setMaxPayItem(maxPayItem);
		lossHeader.setLossType("RAWLOSS");
		lossHeaderBean.saveEntity(lossHeader);

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.AUDITED_CN, lossHeader.getLossManId()));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void createInputBill(InputHeader inputHeader, List<InputDetail> detailLst) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = inputHeader.getFormId();

		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";
		for (InputDetail detail : detailLst) {
			detail.setFormId(formId);
			inputDetailBean.saveEntity(detail);

			Double payAmt = detail.getPayAmt();
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + detail.getItemId() + "]" + detail.getItemName();
			}
		}
		inputHeader.setAllPayAmt(allPayAmt);
		inputHeader.setMaxPayItem(maxPayItem);
		inputHeader.setFormType("JSRK");
		inputHeaderBean.saveEntity(inputHeader);

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.AUDITED_CN, inputHeader.getInputerId()));
	}

	public void setLossDetailBean(LossDetailBean lossDetailBean) {
		this.lossDetailBean = lossDetailBean;
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
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

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setInputDetailBean(InputDetailBean inputDetailBean) {
		this.inputDetailBean = inputDetailBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

}
