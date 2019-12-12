/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * pw
 * 
 * 说明： 岗位观察表
 */
package com.tanry.business.module.hq.so.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.ObservationDetailBean;
import logic.form.ObservationHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ObservationDetail;
import pojo.form.ObservationHeader;

import com.tanry.framework.acl.NoPrivilegeException;

public class StationObservationService {

	private ObservationHeaderBean observationHeaderBean;
	private ObservationDetailBean observationDetailBean;

	/**
	 * @param itemId
	 * @param sysFile
	 *            文件对象
	 * @param params
	 *            保存缩略图所需的信息
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void uploadPhoto(ObservationDetail observationDetail) throws NoPrivilegeException, SQLException,
			NoConnection {
		Integer count = observationDetailBean.count(observationDetail);

		if (count != 0) {
			observationDetailBean.deleteEntity(observationDetail);
		} else {
			int maxStep = observationDetailBean.maxStep(observationDetail);
			observationDetail.setStep(maxStep + 1);
		}

		observationDetailBean.saveEntity(observationDetail);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void uploadEditPhoto(ObservationDetail observationDetail) throws NoPrivilegeException, SQLException,
			NoConnection {
		observationDetailBean.updateStep(observationDetail);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void uploadDeletePhoto(ObservationDetail observationDetail) throws NoPrivilegeException, SQLException,
			NoConnection {
		String itemId = observationDetail.getItemId();
		String processId = observationDetail.getProcessId();

		observationDetailBean.deleteEntity(observationDetail);
		List<ObservationDetail> lists = observationDetailBean.queryByProcessId(itemId, processId);

		for (int i = 0; i < lists.size(); i++) {
			ObservationDetail oDetail = lists.get(i);
			observationDetailBean.updateStepByDelete(itemId, processId, i + 1, oDetail.getStep());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTemplate(String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		Integer count = observationHeaderBean.countTemplate();

		List<ObservationHeader> oList = new ArrayList<ObservationHeader>();

		oList.add(new ObservationHeader("", "leftTitle", "leftTitle", jsonObject.getString("leftTitle"), "Y"));
		oList.add(new ObservationHeader("", "aPartTitle", "aPartTitle", jsonObject.getString("aPartTitle"), "Y"));
		oList.add(new ObservationHeader("", "aPartContent", "aPartContent", jsonObject.getString("aPartContent"), "Y"));
		oList.add(new ObservationHeader("", "costPartContent", "costPartContent", jsonObject
				.getString("costPartContent"), "Y"));
		oList.add(new ObservationHeader("", "bPartTitle", "bPartTitle", jsonObject.getString("bPartTitle"), "Y"));
		oList.add(new ObservationHeader("", "bPartContent", "bPartContent", jsonObject.getString("bPartContent"), "Y"));

		oList.add(new ObservationHeader("", "cPartTitle", "cPartTitle", jsonObject.getString("cPartTitle"), "Y"));
		oList.add(new ObservationHeader("", "cPartContent", "cPartContent", jsonObject.getString("cPartContent"), "Y"));
		oList.add(new ObservationHeader("", "dPartTitle", "dPartTitle", jsonObject.getString("dPartTitle"), "Y"));
		oList.add(new ObservationHeader("", "dPartContent", "dPartContent", jsonObject.getString("dPartContent"), "Y"));

		if (count > 0) {
			updateTemplate(oList);
		} else {
			insert(oList);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveData(String jsonData, String itemId) throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		Integer count = observationHeaderBean.count(itemId);

		List<ObservationHeader> oList = new ArrayList<ObservationHeader>();

		oList.add(new ObservationHeader(itemId, "leftTitle", "leftTitle", jsonObject.getString("leftTitle"), "N"));
		oList.add(new ObservationHeader(itemId, "aPartTitle", "aPartTitle", jsonObject.getString("aPartTitle"), "N"));
		oList.add(new ObservationHeader(itemId, "aPartContent", "aPartContent", jsonObject.getString("aPartContent"),
				"N"));
		oList.add(new ObservationHeader(itemId, "costPartContent", "costPartContent", jsonObject
				.getString("costPartContent"), "N"));

		oList.add(new ObservationHeader(itemId, "bPartTitle", "bPartTitle", jsonObject.getString("bPartTitle"), "N"));
		oList.add(new ObservationHeader(itemId, "bPartContent", "bPartContent", jsonObject.getString("bPartContent"),
				"N"));

		oList.add(new ObservationHeader(itemId, "cPartTitle", "cPartTitle", jsonObject.getString("cPartTitle"), "N"));
		oList.add(new ObservationHeader(itemId, "cPartContent", "cPartContent", jsonObject.getString("cPartContent"),
				"N"));
		oList.add(new ObservationHeader(itemId, "dPartTitle", "dPartTitle", jsonObject.getString("dPartTitle"), "N"));
		oList.add(new ObservationHeader(itemId, "dPartContent", "dPartContent", jsonObject.getString("dPartContent"),
				"N"));

		if (count > 0) {
			updateData(oList);
		} else {
			insert(oList);
		}
	}

	public List<ObservationHeader> loadTemplate() throws NoPrivilegeException, SQLException, NoConnection {
		Integer count = observationHeaderBean.countTemplate();

		if (count == 0) {
			return null;
		}

		return observationHeaderBean.loadTemplate();

	}

	public List<ObservationHeader> loadData(String itemId) throws NoPrivilegeException, SQLException, NoConnection {
		Integer count = observationHeaderBean.count(itemId);

		if (count == 0) {
			return observationHeaderBean.loadTemplate();
		}

		return observationHeaderBean.loadData(itemId);

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insert(List<ObservationHeader> oList) throws NoPrivilegeException, SQLException, NoConnection {
		for (int i = 0; i < oList.size(); i++) {
			ObservationHeader observationHeader = oList.get(i);
			observationHeaderBean.saveEntity(observationHeader);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTemplate(List<ObservationHeader> oList) throws NoPrivilegeException, SQLException, NoConnection {
		for (int i = 0; i < oList.size(); i++) {
			ObservationHeader observationHeader = oList.get(i);
			observationHeaderBean.updateTemplate(observationHeader);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateData(List<ObservationHeader> oList) throws NoPrivilegeException, SQLException, NoConnection {
		for (int i = 0; i < oList.size(); i++) {
			ObservationHeader observationHeader = oList.get(i);
			observationHeaderBean.updateEntity(observationHeader);
		}
	}

	public void setObservationHeaderBean(ObservationHeaderBean observationHeaderBean) {
		this.observationHeaderBean = observationHeaderBean;
	}

	public void setObservationDetailBean(ObservationDetailBean observationDetailBean) {
		this.observationDetailBean = observationDetailBean;
	}

}
