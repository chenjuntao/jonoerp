/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 6, 2015 by liyzh
 * Last edited on Jan 6, 2015 by liyzh
 * 
 * 说明： 产出记录管理
 */
package com.tanry.business.module.cf.production.service;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputDetailBean;
import logic.form.InputHeaderBean;
import logic.form.OperationVersionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.FormStatus;
import pojo.form.InputDetail;
import pojo.form.InputHeader;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class OutputRecordService {

	private InputHeaderBean inputHeaderBean;
	private InputDetailBean inputDetailBean;
	private FormStatusBean formStatusBean;
	private OperationVersionBean operationVersionBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void putinStorage(String userId, InputHeader inputHeader, InputDetail inputDetail)
			throws NoPrivilegeException, SQLException, NoConnection {
		inputHeaderBean.saveEntity(inputHeader);

		String formId = inputHeader.getFormId();
		inputDetail.setFormId(formId);

		inputDetailBean.saveEntity(inputDetail);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void manualInStorage(String userId, InputHeader inputHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String prefix = "YH";
		Date formTime = inputHeader.getInputTime();
		String branchId = inputHeader.getInputerId();
		String formBody = FormUtil.genFormIdBody(prefix, branchId, formTime);

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		// head
		for (Object obj : arr) {

			JSONObject json = (JSONObject) obj;
			InputDetail inputDetail = new InputDetail();
			String itemId = json.getString("itemId");
			inputDetail.setItemId(itemId);
			String itemName = json.getString("itemName");
			inputDetail.setItemName(itemName);

			// 计算总额
			double itemPrice = json.getDouble("itemPrice");
			double payAmt = json.getDouble("itemCount") * itemPrice;
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			inputDetail.setItemDimension(json.getString("itemDimension"));

			// 入库量
			double inputCount = json.getDouble("itemCount");
			inputDetail.setReceiveCount(inputCount);
			inputDetail.setItemUnitPrice(itemPrice);
			inputDetail.setPayAmt(allPayAmt);
			inputDetail.setFormId(formId);
			inputDetailBean.saveEntity(inputDetail);
		}

		inputHeader.setAllPayAmt(allPayAmt);
		inputHeader.setMaxPayItem(maxPayItem);
		inputHeader.setFormId(formId);
		inputHeaderBean.saveEntity(inputHeader);
		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
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

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

}
