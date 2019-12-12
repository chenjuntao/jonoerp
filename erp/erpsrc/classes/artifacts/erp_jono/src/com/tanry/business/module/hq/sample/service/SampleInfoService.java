/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 18, 2015 by liyzh
 * Last edited on Sep 18, 2015 by liyzh
 * 
 * 说明： 研发样品信息表
 */
package com.tanry.business.module.hq.sample.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.module.hq.SampleInfoBean;
import logic.store.PicBean;
import pojo.form.FormStatus;
import pojo.form.SampleInfo;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;

public class SampleInfoService {

	private SampleInfoBean sampleInfoBean;
	private PicBean picBean;
	private FormStatusBean formStatusBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(String oldId, SampleInfo sample) throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(oldId)) {
			sampleInfoBean.updateEntity(sample);
		} else {
			sampleInfoBean.saveEntity(sample);

			formStatusBean.saveEntity(new FormStatus(sample.getId(), BillStatus.UNADUIT_CN, sample.getCreateUserId()));
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void audit(String id, String userId) throws NoPrivilegeException, SQLException, NoConnection {
		formStatusBean.saveEntity(new FormStatus(id, BillStatus.AUDITED_CN, userId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteSample(String ids) throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			sampleInfoBean.deleteEntity(id);
		}
	}

	/**
	 * @param id
	 * @param picData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void uploadPhoto(String id, byte[] picData, String picType) throws NoPrivilegeException, SQLException,
			NoConnection {
		SampleInfo sample = sampleInfoBean.queryById(id);
		String oldPicId = sample.getSamplePic();
		String picId = TextUtil.uuid();
		if (!TextUtil.isEmpty(oldPicId)) {
			picBean.deleteEntity(oldPicId);// 替换老的图片
		}
		if ("sample".equals(picType)) {
			sampleInfoBean.updateSamplePic(id, picId);
		} else {
			sampleInfoBean.updateQualificationPic(id, picId);
		}
		picBean.saveEntity(picId, picData);
	}

	public void setSampleInfoBean(SampleInfoBean sampleInfoBean) {
		this.sampleInfoBean = sampleInfoBean;
	}

	public void setPicBean(PicBean picBean) {
		this.picBean = picBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}
}
