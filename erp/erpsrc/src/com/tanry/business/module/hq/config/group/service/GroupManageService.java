/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jun 2, 2015 by liyzh
 * Last edited on Jun 2, 2015 by liyzh
 * 
 * 说明： 物流中心配送分组配置
 */
package com.tanry.business.module.hq.config.group.service;

import java.sql.SQLException;

import logic.NoConnection;
import logic.store.DeliveryRegionBean;
import logic.store.DeliveryTypeBean;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tanry.framework.acl.NoPrivilegeException;

public class GroupManageService {

	private DeliveryRegionBean deliveryRegionBean;
	private DeliveryTypeBean deliveryTypeBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void enableGroup(String regionId, String refRegionId, Integer enabled) throws NoPrivilegeException,
			SQLException, NoConnection {
		deliveryRegionBean.enableRegion(regionId, enabled);

		// 参考其它分组，初始化原料的配送方式
		if (refRegionId != null) {
			deliveryTypeBean.updateByRefRegion(regionId, refRegionId);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteGroup(String regionIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] regionArr = regionIds.split(",");
		for (String regionId : regionArr) {
			deliveryRegionBean.deleteRegion(regionId);
		}
	}

	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}

	public void setDeliveryTypeBean(DeliveryTypeBean deliveryTypeBean) {
		this.deliveryTypeBean = deliveryTypeBean;
	}
}
