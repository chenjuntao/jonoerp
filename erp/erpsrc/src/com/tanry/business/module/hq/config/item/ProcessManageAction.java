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
 * 说明： 半成品工序/制程表增删改
 */
package com.tanry.business.module.hq.config.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.form.MakingProcessBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.MakingProcess;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.service.ProcessManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class ProcessManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private MakingProcessBean makingProcessBean;
	private ProcessManageService procecssManageService;

	private String itemId;
	private String step;
	private String steps;

	private MakingProcess makingProcess;

	public String stepEditView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(step)) {
			makingProcess = makingProcessBean.query(itemId, step);
		}
		return SUCCESS;
	}

	/**
	 * 验证步骤是否重复
	 */
	public void checkStep() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		makingProcess = makingProcessBean.query(itemId, step);
		if (makingProcess != null) {
			exist = true;
		}

		JSONObject result = new JSONObject();
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<MakingProcess> detailLst = makingProcessBean.queryByItem(itemId);

		for (MakingProcess detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(step)) {
			makingProcessBean.updateEntity(step, makingProcess);
		} else {
			makingProcessBean.saveEntity(makingProcess);
		}

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		procecssManageService.deleteSteps(itemId, steps);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public MakingProcess getMakingProcess() {
		return makingProcess;
	}

	public void setMakingProcess(MakingProcess makingProcess) {
		this.makingProcess = makingProcess;
	}

	public void setMakingProcessBean(MakingProcessBean makingProcessBean) {
		this.makingProcessBean = makingProcessBean;
	}

	public void setProcessManageService(ProcessManageService procecssManageService) {
		this.procecssManageService = procecssManageService;
	}

}
