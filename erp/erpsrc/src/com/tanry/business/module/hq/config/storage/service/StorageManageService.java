package com.tanry.business.module.hq.config.storage.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.BranchStorageBean;
import pojo.store.BranchStorage;

import com.tanry.framework.acl.NoPrivilegeException;

public class StorageManageService {
	private BranchStorageBean branchStorageBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(BranchStorage branchStorage) throws NoPrivilegeException,
			SQLException, NoConnection {
		branchStorageBean.updateEntity(branchStorage);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insert(BranchStorage branchStorage)
			throws NoPrivilegeException, SQLException, NoConnection {
		branchStorageBean.saveEntity(branchStorage);
	}

	public void setMain(BranchStorage branchStorage)
			throws NoPrivilegeException, SQLException, NoConnection {
		branchStorageBean.setMainStorage(branchStorage);

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int del(String storageId) throws NoPrivilegeException, SQLException,
			NoConnection {
		int count = branchStorageBean.count(storageId);

		if (count > 0) {
			return count;
		} else {
			branchStorageBean.delete(storageId);
			return 0;
		}

	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

}
