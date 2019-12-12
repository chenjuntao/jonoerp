/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 3, 2015 by liyzh
 * Last edited on Feb 3, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.item.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.MakingProcessBean;

import com.tanry.framework.acl.NoPrivilegeException;

public class ProcessManageService {

	private MakingProcessBean makingProcessBean;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteSteps(String itemId, String steps) throws NoPrivilegeException, SQLException, NoConnection {
		String[] stepArr = steps.split(",");
		for (String step : stepArr) {
			makingProcessBean.delete(itemId, step);
		}
	}

	public void setMakingProcessBean(MakingProcessBean makingProcessBean) {
		this.makingProcessBean = makingProcessBean;
	}
}
