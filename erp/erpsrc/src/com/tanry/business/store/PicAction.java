/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Oct 23, 2014 by liyzh
 * Last edited on Oct 23, 2014 by liyzh
 * 
 * 说明：  图片表
 */
package com.tanry.business.store;

import java.io.OutputStream;

import logic.store.PicBean;
import action.common.BaseAction;

import com.tanry.framework.util.TextUtil;

public class PicAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PicBean picBean;
	private String picId;
	private String objectId;

	public void loadImage() throws Exception {
		byte[] picData = null;
		if (TextUtil.isEmpty(picId)) {
			picData = picBean.getMaterialPic(objectId);
		} else {
			picData = picBean.getPicData(picId);
		}
		if (picData != null) {
			String fileName = "pic" + System.currentTimeMillis();
			this.getResponse().reset();
			this.getResponse().setContentType("image/jpeg");
			this.getResponse().setHeader("Content-disposition", "inline; filename=" + fileName);
			OutputStream outFile = getResponse().getOutputStream();
			outFile.write(picData);
			outFile.flush();
			outFile.close();
		} else {
			outBlank("不存在");
		}
	}

	public void setPicBean(PicBean picBean) {
		this.picBean = picBean;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}
}
