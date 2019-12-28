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
 * 说明： 箱子类型(冷藏、非冷藏等)删除等
 */
package com.tanry.business.module.hq.config.box.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.BoxTypeBean;

import com.tanry.framework.acl.NoPrivilegeException;

public class BoxTypeManageService {

	private BoxTypeBean boxTypeBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteType(String typeIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] typeArr = typeIds.split(",");
		for (String typeId : typeArr) {
			boxTypeBean.deleteEntity(typeId);
		}
	}

	public void setBoxTypeBean(BoxTypeBean boxTypeBean) {
		this.boxTypeBean = boxTypeBean;
	}

}
