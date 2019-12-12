/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 28, 2014 by liyzh
 * Last edited on Sep 28, 2014 by liyzh
 * 
 * 说明： 盘点锁库记录管理
 */
package service.restaurant.checkstorage;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.CheckLockBean;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.CheckLock;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;

public class LockStorageService {

	private CheckLockBean checkLockBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveLock(CheckLock checkLock) throws NoPrivilegeException, SQLException, NoConnection {

		Date lockDate = checkLock.getLockDate();
		String branchId = checkLock.getLockBranchId();

		int newSerial = checkLockBean.newSerial(lockDate, branchId);
		// CB, check batch
		String checkBatchId = FormUtil.genFormIdBody("CB", branchId, lockDate) + FormUtil.formatSerial(newSerial);
		checkLock.setCheckBatchId(checkBatchId);

		checkLockBean.saveEntity(checkLock);

		OperateLogUtil.record("生成盘点批次" + checkBatchId);

		return checkBatchId;
	}

	public void setCheckLockBean(CheckLockBean checkLockBean) {
		this.checkLockBean = checkLockBean;
	}

}
