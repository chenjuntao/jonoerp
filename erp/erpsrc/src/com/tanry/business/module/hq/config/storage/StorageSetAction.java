package com.tanry.business.module.hq.config.storage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchStorageBean;
import logic.store.StorageSetBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.storage.service.StorageManageService;
import com.tanry.framework.acl.NoPrivilegeException;

public class StorageSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private StorageManageService storageManageService;

	private StorageSetBean storageSetBean;
	private BranchStorageBean branchStorageBean;

	private String storageId;
	private String storageName;
	private Integer priority;
	private String branchId;

	/**
	 * 验证编号是否重复
	 */
	public void checkId() throws NoPrivilegeException, SQLException,
			NoConnection {
		boolean exist = false;
		BranchStorage branchStorage = branchStorageBean
				.queryByStorageId(storageId);
		JSONObject result = new JSONObject();
		if (branchStorage != null) {
			exist = true;
			result.put("storageName", branchStorage.getStorageName());
		}
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String addView() throws NoPrivilegeException, SQLException,
			NoConnection {
		return SUCCESS;
	}

	public void queryStorage() throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "部门");
		root.put("isLeaf", "N");
		jsonArray.add(root);

		List<Map> storageInfos = storageSetBean.query();

		for (int i = 0; i < storageInfos.size(); i++) {
			Map map = storageInfos.get(i);
			JSONObject jsonObject = JSONObject.fromObject(map);

			jsonArray.add(jsonObject);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() throws NoPrivilegeException, SQLException, NoConnection {
		BranchStorage bs = new BranchStorage();
		bs.setStorageId(storageId);
		bs.setStorageName(storageName);
		bs.setPriority(priority);

		storageManageService.save(bs);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveStorage() throws NoPrivilegeException, SQLException,
			NoConnection {
		BranchStorage branchStorage = new BranchStorage();
		branchStorage.setStorageId(storageId);
		branchStorage.setStorageName(storageName);
		branchStorage.setBranchId(branchId);
		int maxPriority = branchStorageBean.getMaxPriority(branchId);
		branchStorage.setPriority(maxPriority);

		storageManageService.insert(branchStorage);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMain() throws NoPrivilegeException, SQLException,
			NoConnection {
		BranchStorage bs = new BranchStorage();
		bs.setStorageId(storageId);
		bs.setStorageName(storageName);
		bs.setPriority(priority);
		bs.setBranchId(branchId);

		storageManageService.setMain(bs);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void del() throws NoPrivilegeException, SQLException, NoConnection {
		int count = storageManageService.del(storageId);
		JSONObject result = new JSONObject();
		result.put("count", count);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStorageSetBean(StorageSetBean storageSetBean) {
		this.storageSetBean = storageSetBean;
	}

	public void setStorageManageService(
			StorageManageService storageManageService) {
		this.storageManageService = storageManageService;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

}
