/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 9, 2014 by liyzh
 * Last edited on Dec 9, 2014 by liyzh
 * 
 * 说明： 物流中心外部配送出库管理
 */
package com.tanry.business.module.lc.stock.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.PackingDetailBean;
import logic.form.PackingHeaderBean;
import logic.form.PickingHeaderBean;
import pojo.form.PackingDetail;
import pojo.form.PackingHeader;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class PackingService {

	private PackingHeaderBean packingHeaderBean;
	private PackingDetailBean packingDetailBean;
	private PickingHeaderBean pickingHeaderBean;

	/**
	 * 创建装箱单
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String createBill(String userId, PackingHeader packingHeader, Map<String, Map<String, Object>> boxMap)
			throws NoPrivilegeException, SQLException, NoConnection {
		Date formTime = packingHeader.getFormTime();
		String lcBranchId = packingHeader.getPackingBranchId();
		int newSerial = packingHeaderBean.newSerial(formTime, lcBranchId);
		String formId = FormUtil.genFormIdBody("ZX", lcBranchId, formTime) + FormUtil.formatSerial(newSerial);
		packingHeader.setFormId(formId);
		for (String key : boxMap.keySet()) {
			Map<String, Object> boxInfo = boxMap.get(key);
			List<PackingDetail> detailLst = (List<PackingDetail>) boxInfo.get("detailLst");
			for (PackingDetail detail : detailLst) {
				detail.setFormId(formId);
				packingDetailBean.saveEntity(detail);
			}
		}
		packingHeaderBean.saveEntity(packingHeader);
		pickingHeaderBean.savePackStatus(packingHeader.getFormRefId(), "已生成");
		return formId;
	}

	public void setPackingHeaderBean(PackingHeaderBean packingHeaderBean) {
		this.packingHeaderBean = packingHeaderBean;
	}

	public void setPackingDetailBean(PackingDetailBean packingDetailBean) {
		this.packingDetailBean = packingDetailBean;
	}

	public void setPickingHeaderBean(PickingHeaderBean pickingHeaderBean) {
		this.pickingHeaderBean = pickingHeaderBean;
	}
}
