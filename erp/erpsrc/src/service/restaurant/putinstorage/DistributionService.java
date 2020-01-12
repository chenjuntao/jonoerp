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
 * 说明： 配送入库管理
 */
package service.restaurant.putinstorage;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputDetailBean;
import logic.form.InputHeaderBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.form.StorageInOutBean;
import logic.store.BranchBean;
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
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.form.StorageInOut;

import com.tanry.business.form.FormType;
import com.tanry.business.form.StatusConst;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;

public class DistributionService {

	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private InputHeaderBean inputHeaderBean;
	private InputDetailBean inputDetailBean;
	private FormStatusBean formStatusBean;
	private StorageBean storageBean;
	private StorageInOutBean storageInOutBean;
	private BranchStorageBean branchStorageBean;
	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	/**
	 * 餐厅配送单填充
	 * 
	 * @param header
	 * @param jsonData
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fillBill(String userId, ShippingHeader header, String jsonData) throws Exception {
		String formId = header.getFormId();
		String branchId = header.getRequesterId();
		Date restaurantDate = branchBean.GetBranchById(branchId).getBusinessDate();
		header.setInputTime(restaurantDate);

		String mainStorageId = branchStorageBean.queryMainStore(branchId).getStorageId();

		// -----------------------------------------
		// 操作时间
		Date operationTime = DateTimeUtil.getNow();

		// 业务类型 餐厅配送入库
		String reason = "PSRK";
		// -----------------------------------------

		JSONArray arr = JSONArray.fromObject(jsonData);
		if (arr == null || arr.size() == 0) {
			throw new Exception("明细解析json数组后为空!");
		}

		Double allPayAmt = 0.0, maxPayAmt = -0.1;
		DecimalFormat df = new DecimalFormat("######0.00");
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			Double receiveCount = json.getDouble("receiveCount");
			detail.setReceiveCount(receiveCount);
			detail.setDifferentCount(json.getDouble("differentCount"));
			detail.setDifferentReason((String) json.get("differentReason"));

			detail.setDeliveryCount(json.getDouble("deliveryCount"));
			Double itemUnitPrice = json.getDouble("itemUnitPrice");

			Double payAmt = Double.parseDouble(df.format(receiveCount * itemUnitPrice));
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}

			if (detail.getReceiveCount() == null) {
				throw new Exception("receiveCount is null!");
			}

			shippingDetailBean.updateEntity(detail);

			// -----------------------------------------
			// 出入库记录

			// -------------- 餐厅
			Double storage = storageBean.getItemCount(itemId, mainStorageId);

			// 入库数量
			Double itemCountIn = receiveCount;

			// 出库数量
			Double itemCountOut = 0.0;

			// 期初数量
			Double orgiCount = (storage == null ? 0 : storage);

			// 结存数量
			Double resultCount = orgiCount + receiveCount;

			StorageInOut storageInOut = new StorageInOut(branchId, mainStorageId, restaurantDate, operationTime,
					itemId, json.getDouble("itemUnitPrice"), orgiCount, itemCountIn, itemCountOut, resultCount, formId,
					reason);

			storageInOutBean.saveEntity(storageInOut);

			// -----------------------------------------

			// 确认之后，餐厅库存增加实收数量的货物
			storageBean.updateItemCount(itemId, mainStorageId, receiveCount);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		shippingHeaderBean.updateEntity(header);
		shippingHeaderBean.saveInputStatus(formId, BillStatus.STORED_CN);
		shippingHeaderBean.saveOnStatus(formId, "");

		OperateLogUtil.record(formId, null, "配送单入库");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String inputBill(String userId, InputHeader inputHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
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

			detail.setItemUnitPrice(json.getDouble("itemUnitPrice"));

			if (json.get("receiveCount") != null) {
				detail.setReceiveCount(json.getDouble("receiveCount"));
				detail.setReceivedCount(json.getDouble("receiveCount"));
			}
			detail.setDifferentCount(0.0);
			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);

			// 央厂对物流中心半成品使用标准价，将标准价存到进货价中，退货取价
			detail.setReceivePrice(json.getDouble("itemUnitPrice"));

			detail.setReceiveAmt(json.getDouble("payAmt"));

			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
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

		formStatusBean.saveEntity(new FormStatus(formId, StatusConst.AUDITED, userId));

		String inputType = "半成品入库单";
		if (inputHeader.getFormType().equals(FormType.INPUT_OUTER)) {
			inputType = "外部出货单入库";
		}
		OperateLogUtil.record(formId, LogType.CREATE, inputType);
		OperateVersionUtil.save(formId);

		return formId;
	}

	/**
	 * 外部出货单填充
	 * 
	 * @param header
	 * @param jsonData
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void outerFillBill(String userId, ShippingHeader header, String jsonData) throws Exception {
		String formId = header.getFormId();
		String branchId = header.getRequesterId();
		Date restaurantDate = branchBean.GetBranchById(branchId).getBusinessDate();
		header.setInputTime(restaurantDate);

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -0.1;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ShippingDetail detail = new ShippingDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			Double receiveCount = json.getDouble("receiveCount");
			detail.setReceiveCount(receiveCount);
			detail.setDifferentCount(json.getDouble("differentCount"));
			detail.setDifferentReason((String) json.get("differentReason"));

			detail.setDeliveryCount(json.getDouble("deliveryCount"));
			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + json.getString("itemName");
			}

			if (detail.getReceiveCount() == null) {
				throw new Exception("receiveCount is null!");
			}

			shippingDetailBean.updateEntity(detail);

		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		shippingHeaderBean.updateEntity(header);
		shippingHeaderBean.saveInputStatus(formId, BillStatus.STORED_CN);
		shippingHeaderBean.saveOnStatus(formId, "");

		OperateLogUtil.record(formId, null, "出货单入库");
	}

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
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

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setInputDetailBean(InputDetailBean inputDetailBean) {
		this.inputDetailBean = inputDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

}
